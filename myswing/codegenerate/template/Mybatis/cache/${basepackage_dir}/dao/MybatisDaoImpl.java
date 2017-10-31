package ${basepackage}.dao;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.annotation.Resource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import com.alibaba.fastjson.JSONObject;
import ${basepackage}.util.EncryptUtil;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
public class MybatisDaoImpl extends SqlSessionTemplate implements MybatisDao {
  @Resource(name = "sqlSessionFactory")
  private SqlSessionFactory sqlSessionFactory;
  @Resource(name = "redisClient")
  private RedisClient<Object, ?> redisclient;
  @Resource
  private AmqpTemplate amqpTemplate;

  static private ResourceBundle rb = ResourceBundle.getBundle("config");

  private static int expire_time = 60 * 2;
  static {
    if (rb.containsKey("cache.expire_time")) {
      expire_time = Integer.parseInt(rb.getString("cache.expire_time"));
    }
  }

  public MybatisDaoImpl(SqlSessionFactory sqlSessionFactory) {
    super(sqlSessionFactory);
  }
  
  public int deleteObjById(String key, Object obj) {
    String redisKey = "";
    redisKey = key+":"+ String.valueOf(obj);
    Map m = new HashMap();
    String prefix = key.split("\\.")[0];
    m.put("prefix", "Collection:" + prefix);
    m.put("key", obj);
    amqpTemplate.convertAndSend("${basepackage}.removeCache", m);
    redisclient.delete(redisKey);
    return delete(key, obj);
  }
  
  public int deleteObj(String key, Object obj) {
    return delete(key, obj);
  }

  public int insertObj(String key, Object obj) {
    int res = 0;
    res = insert(key, obj);
    String prefix = key.split("\\.")[0];
    Map m = new HashMap();
    m.put("key", "Collection:" + prefix);
    amqpTemplate.convertAndSend("${basepackage}.removeCache", m);
    return res;
  }

  public List queryForList(String key) {
    List resList = null;
    List tempres = (List) redisclient.lrange(key, 0, -1);
    if (!tempres.isEmpty()) {
      resList = tempres;
    } else {
      resList = selectList(key);
      redisclient.setListJSON(getRedisKeyByList(key, resList), resList, expire_time);
    }
    return resList;
  }

  public List queryForList(String key, Object obj, Class clazz) {
    List<Object> resList = null;
    String listKey = redisclient.findListKey(getRedisKeyByList(obj, key));
    if (!"".equals(listKey)) {
      List<Object> tempres = (List<Object>) redisclient.lrangeJSON(listKey, clazz, 0, -1);
      if(!tempres.isEmpty()){
    	  resList = tempres;
      }else{
    	  resList = selectList(key, obj);
          redisclient.setListJSON(getRedisKeyByList(obj, key, resList), resList, expire_time);
      }
    } else {
    	resList = selectList(key, obj);
        redisclient.setListJSON(getRedisKeyByList(obj, key, resList), resList, expire_time);
       }
    return resList;
  }

  public Object queryForObject(String key, Class clazz) {
    Object objres = null;
    String tempres = redisclient.get(key);
    if (tempres != null) {
      objres = (Object) JSONObject.parseObject(tempres, clazz);
    } else {
      objres = (Object) selectOne(key);
      redisclient.merge(getRedisKeyByObject(objres, key), JSONObject.toJSONString(objres),
          expire_time);
    }
    return objres;
  }

  public Object queryForObject(String key, Object obj, Class clazz) {
    Object objres = null;
    try {
      String tempres = null;
      String redisKey = null;
      redisKey = getRedisKeyByObject(obj, key);
      if (redisclient.hasKey(redisKey)) {
        tempres = redisclient.get(redisKey);
      } else {
        // do nothing
      }

      if (tempres != null) {
        objres = (Object) JSONObject.parseObject(tempres, clazz);
      } else {
        objres = selectOne(key, obj);
        redisclient.merge(redisKey, JSONObject.toJSONString(objres), expire_time);
      }
    } catch (IllegalArgumentException | SecurityException e) {
      e.printStackTrace();
    }
    return objres;
  }

  public Object queryForObject(String key, Object obj) {
    return selectOne(key, obj);
  }
  
  public int updateObj(String key, Object obj) {
    int result = 0;
    try {
      String redisKey = getRedisKeyById(obj, key);
      redisclient.delete(redisKey);
      result = update(key, obj);
//      System.out.println(String.valueOf(obj.getClass().getDeclaredMethod("getId").invoke(obj)));
      Map m = new HashMap();
      m.put("key", obj.getClass().getDeclaredMethod("getId").invoke(obj));
      amqpTemplate.convertAndSend("${basepackage}.removeCache", m);
    } catch (IllegalArgumentException | SecurityException | AmqpException | IllegalAccessException
        | InvocationTargetException | NoSuchMethodException e) {
      e.printStackTrace();
    }
    return result;
  }

  public Object queryForId(String key, String id, Class clazz) {
    Object objres = null;
    String tempres = redisclient.get(getRedisKeyById(id, key));
    if (tempres != null) {
      objres = (Object) JSONObject.parseObject(tempres, clazz);
    } else {
      objres = selectOne(key, id);
      redisclient.merge(getRedisKeyById(id, key), JSONObject.toJSONString(objres), expire_time);
    }
    return objres;
  }

  public static String getRedisKeyById(Object obj, String key) {
    String redisKey = "";
    try {
      // redisKey = EncryptUtil.encrypt(String.valueOf(obj.getClass().getDeclaredMethod("getId")
      // .invoke(obj)),
      // EncryptUtil.SHA_512) + ":" + key;
      redisKey = String.valueOf(obj.getClass().getDeclaredMethod("getId").invoke(obj)) + ":" + key;
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }
    return redisKey;
  }

  public static String getRedisKeyByList(Object obj, String key, List resList) {
    String redisKey = "";
    StringBuffer sb = new StringBuffer(":");
    try {
      Object tmpObj = null;
      for (int i = 0; i < resList.size(); i++) {
        tmpObj = resList.get(i);
        if (tmpObj.getClass().getName().equals("java.util.HashMap")) {
          Map m = (Map) tmpObj;
          sb.append(m.get("id") + ":");
        } else {
          sb.append(tmpObj.getClass().getDeclaredMethod("getId").invoke(tmpObj) + ":");
        }
      }
      redisKey = "Collection:" + key + ":" + JSONObject.toJSONString(obj) + sb.toString();
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }
    return redisKey;
  }

  public static String getRedisKeyByList(Object obj, String key) {
    String redisKey = "Collection:" + key + ":" + JSONObject.toJSONString(obj);
    return redisKey;
  }

  public static String getRedisKeyById(String id, String key) {
    String redisKey = "";
    try {
      // redisKey = EncryptUtil.encrypt(id,EncryptUtil.SHA_512) + ":" + key;
      redisKey = key + ":" + id;
    } catch (IllegalArgumentException | SecurityException e) {
      e.printStackTrace();
    }
    return redisKey;
  }

  public static String getRedisKeyByObject(Object obj, String key) {
    // return EncryptUtil.encrypt(JSONObject.toJSONString(obj) +":"+ key,EncryptUtil.SHA_512)
    return "Collection:" + key + ":" +JSONObject.toJSONString(obj);
  }

  private String getRedisKeyByObject(String key) {
    return key;
  }

  private String getRedisKeyByList(String key, List resList) {
    String redisKey = "";
    StringBuffer sb = new StringBuffer(":");
    try {
      for (int i = 0; i < resList.size(); i++) {
    	  Object obj = resList.get(i);
    	  Class clazz = obj.getClass();
    	  if(clazz.getName().equals("java.util.HashMap")){
    		  Map m = (Map) obj;
    		  sb.append(m.get("id") + ":");
    	  }else{
    		  sb.append(clazz.getDeclaredMethod("getId").invoke(resList.get(i)) + ":");
    	  }
      }
      redisKey = "Collection:" + key + sb.toString();
    } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
        | NoSuchMethodException | SecurityException e) {
      e.printStackTrace();
    }
    return redisKey;
  }

  public int queryForCount(String key, Object obj) {
    int resNum = 0;
    String tempres = redisclient.get(getRedisKeyByObject(obj, key));
    if (tempres != null) {
      resNum = Integer.parseInt(tempres);
    } else {
      resNum = selectOne(key, obj);
      redisclient.merge(getRedisKeyByObject(obj, key), String.valueOf(resNum), expire_time);
    }
    return resNum;
  }

}
