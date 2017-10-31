package ${basepackage}.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * @version 1.0
 * @author Eric.wang
 * @date ${nowdate}
 * @email 1595905476(a)qq.com
 */
@Component("redisClient")
public class RedisClient<K,V> {
 
	@Resource(name="redisTemplate")
    protected RedisTemplate<K,V> redisTemplate;
     
    /**
     * merge字符串
     * @param key
     * @param value
     * @param seconds 失效时间
     * @return
     */
    public Boolean merge(final String key,final String value,final long seconds) {
        boolean resultBoolean = false;
        resultBoolean = redisTemplate.execute(new RedisCallback<Boolean>() { 
            public Boolean doInRedis(RedisConnection connection) 
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys  = serializer.serialize(key); 
                byte[] values = serializer.serialize(value); 
                connection.setEx(keys, seconds, values);
                return true;
            } 
        }); 
        return resultBoolean;
    }
    
    public void pipelineHMSet(final Map<String,Map<String,String>> redisMap){
      RedisCallback<List<Object>> pipelineCallback = new RedisCallback<List<Object>>() {
        @Override
        public List<Object> doInRedis(RedisConnection connection) throws DataAccessException {
          RedisSerializer<String> serializer = getRedisSerializer();
          connection.openPipeline();
          Iterator<Map.Entry<String, Map<String, String>>> iter = redisMap.entrySet().iterator();
          while (iter.hasNext()) {
              Map.Entry<String, Map<String, String>> entry = iter.next();
              byte[] keys  = serializer.serialize(entry.getKey());
              Map<String,String> mapOri = entry.getValue();
              Map<byte[],byte[]> mapNew = new HashMap<byte[],byte[]>();
              Iterator<Map.Entry<String, String>>  tmpiter = mapOri.entrySet().iterator();
              while(tmpiter.hasNext()){
                Map.Entry<String,String> tmpentry = tmpiter.next();
                mapNew.put(serializer.serialize(tmpentry.getKey()), serializer.serialize(String.valueOf(tmpentry.getValue())));
              }
              connection.hMSet(keys, mapNew);
          }
          return connection.closePipeline();
        }
      };
      redisTemplate.execute(pipelineCallback);
    }
    
    /**
     * merge字符串
     * @param key
     * @param value
     * @return
     */
    public Boolean merge(final String key,final String value) {
        boolean resultBoolean = false;
        
        resultBoolean = redisTemplate.execute(new RedisCallback<Boolean>() { 
            public Boolean doInRedis(RedisConnection connection) 
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys  = serializer.serialize(key); 
                byte[] values = serializer.serialize(value); 
                connection.set(keys,values); 
                return true;
            } 
        }); 
        return resultBoolean;
    }
    
    /**
     * 字符串增长
     * @param key
     * @return
     */
    public void increment(final String key) {
    	increment(key,1);
    }
    
    
    /**
     * 字符串增长
     * @param key
     * @param num 步数
     * @return
     */
    public void increment(final String key,final long num) {
        redisTemplate.execute(new RedisCallback<Boolean>() { 
            public Boolean doInRedis(RedisConnection connection) 
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys  = serializer.serialize(key); 
            	connection.incrBy(keys, num);
				return null; 
            } 
        }); 
    }
    
    /**
     * 字符串减少
     * @param key
     * @return
     */
    public void decrement(final String key) {
    	decrement(key,1);
    }
    
    
    /**
     * 字符串减少
     * @param key
     * @param num 步数
     * @return
     */
    public void decrement(final String key,final long num) {
        redisTemplate.execute(new RedisCallback<Boolean>() { 
            public Boolean doInRedis(RedisConnection connection) 
                    throws DataAccessException { 
                RedisSerializer<String> serializer = getRedisSerializer();
                byte[] keys  = serializer.serialize(key); 
            	connection.decrBy(keys, num);
				return null; 
            } 
        }); 
    }
    
    /**
     * 获取String值
     * @param key
     * @return
     */
    public String get(final String key) {
        String resultStr = null;
        if(redisTemplate != null) {
            resultStr = redisTemplate.execute(new RedisCallback<String>() {
                public String doInRedis(RedisConnection connection) 
                        throws DataAccessException { 
                    RedisSerializer<String> serializer = getRedisSerializer(); 
                    byte[] keys = serializer.serialize(key); 
                    byte[] values = connection.get(keys); 
                    if (values == null) { 
                        return null; 
                    } 
                    String value = serializer.deserialize(values); 
                    return value; 
                } 
            }); 
        }
        return resultStr; 
    } 
    
    /**
     * 删除String值
     * @param key
     */
    public void delete(final String key) {  
        redisTemplate.execute(new RedisCallback<Object>() {  
            public Object doInRedis(RedisConnection connection) {  
                connection.del(redisTemplate.getStringSerializer().serialize(key));  
                return null;  
            }  
        });  
    }  
    
    /**
     * 设置list集合
     * @param listname 
     * @param list 集合
     * @param seconds 失效时间
     */
    public void setList(final String listname,final List<V> list,final long seconds) {
    	redisTemplate.delete((K) listname);
    	BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        for(V v:list){
        	listBounds.leftPush(v);
        }
        listBounds.expire(seconds, TimeUnit.SECONDS);
    }
    
    /**
     * 设置list集合
     * @param listname 
     * @param list 集合
     */
    public void setList(final String listname,final List<V> list) {
    	redisTemplate.delete((K) listname);
    	BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        for(V v:list){
        	listBounds.leftPush(v);
        }
    }
    
    /**
     * 设置list集合
     * @param listname 
     * @param list 集合
     * @param seconds 失效时间
     */
    public void setListJSON(final String listname,final List<V> list) {
    	redisTemplate.delete((K) listname);
		redisTemplate.execute(new RedisCallback<List<Object>>() {
			@Override
			public List<Object> doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				connection.openPipeline();
				for (V v: list) {
					connection.rPush(serializer.serialize(listname), serializer.serialize(JSONObject.toJSONString(v)));
				}
				return connection.closePipeline();
			}
		});
    }
    
    /**
     * 设置list集合
     * @param listname 
     * @param list 集合
     * @param seconds 失效时间
     */
    public void setListJSON(final String listname,final List list,final long seconds) {
    	redisTemplate.delete((K) listname);
		redisTemplate.execute(new RedisCallback<List<Object>>() {
			@Override
			public List<Object> doInRedis(RedisConnection connection)
					throws DataAccessException {
				RedisSerializer<String> serializer = getRedisSerializer();
				connection.openPipeline();
				for (Object o: list) {
					connection.rPush(serializer.serialize(listname), serializer.serialize(JSONObject.toJSONString(o)));
				}
				connection.expire(serializer.serialize(listname), seconds);
				return connection.closePipeline();
			}
		});
    }
    
    /**
     * 左侧压入list值
     * @param listname 
     * @param value
     */
    public void lpush(final String listname,final String value) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        listBounds.leftPush((V) value);
    }
    
    /**
     * 左侧压入list值
     * @param listname 
     * @param V v 对象
     */
    public void lpushJSON(final String listname,final V v) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        listBounds.leftPush((V)JSONObject.toJSONString(v));
    }
    
    /**
     * 左侧取出list值
     * @param listname
     * @return
     */
    public V lpop(final String listname) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return listBounds.leftPop();
    }
    
    /**
     * 左侧取出list值
     * @param listname
     * @param clazz 对象class
     * @return
     */
    public V lpopJSON(final String listname,Class clazz) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return (V) JSONObject.parseObject((String) listBounds.leftPop(), clazz);
    }
    
    /**
     * 右侧压入list
     * @param listname
     * @param value
     */
    public void rpush(final String listname,final String value) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        listBounds.rightPush((V) value);
    }
    
    /**
     * 右侧压入list值
     * @param listname 
     * @param V v 对象
     */
    public void rpushJSON(final String listname,final V v) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        listBounds.rightPush((V)JSONObject.toJSONString(v));
    }
    
    /**
     * 右侧取出list
     * @param listname
     * @return
     */
    public V rpop(final String listname) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return listBounds.rightPop();
    }
    
    /**
     * 右侧侧取出list值
     * @param listname
     * @param clazz 对象class
     * @return
     */
    public V rpopJSON(final String listname,Class clazz) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return (V) JSONObject.parseObject((String) listBounds.rightPop(), clazz);
    }
    /**
     * 取出list集合
     * @param listname
     * @param start 开始位置下标
     * @param end 结束位置下标 等于-1 为全部取出
     * @return
     */
    public List<V> lrange(final String listname,final long start,final long end) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return listBounds.range(start, end);
    }
    
    
    /**
     * 取出list集合
     * @param listname
     * @param clazz class类文件
     * @param start 开始位置下标
     * @param end 结束位置下标 等于-1 为全部取出
     * @return
     */
    public List<V> lrangeJSON(final String listname,Class clazz,final long start,final long end) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        if(StringUtils.isEmpty(listBounds.range(start, end))){
        	return null;
        }else{
    	    return JSONObject.parseArray(listBounds.range(start, end).toString(), clazz);
        }
      }
    
    /**
     * 获取list长度
     * @param listname
     * @return
     */
    public long llen(final String listname) {
        BoundListOperations<K,V> listBounds = redisTemplate.boundListOps((K) listname);
        return listBounds.size();
    }
    
    /**
     * 插入hashmap值
     * @param hashmapname
     * @param key
     * @param value
     */
    public void hmset(final String hashmapname,final String key,final String value) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
    	hashBounds.put(key, value);
    }
    
    /**
     * 插入hashmap
     * @param hashmapname
     * @param map
     */
    public void hmset(final String hashmapname,final Map<K,V> map) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
        for(Map.Entry<K,V> entry:map.entrySet()){
        	hashBounds.put(entry.getKey(), entry.getValue());
        }
    }
    
    /**
     * 插入hashmap
     * @param hashmapname
     * @param map
     */
//    public void hmset(final Map<String,Map<K,V>> map) {
//        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
//        redisTemplate.boundListOps(key)
//        for(Map.Entry<String,Map<K,V>> entry:map.entrySet()){
//          hashBounds.put(entry.getKey(), entry.getValue());
//        }
//    }
    
    /**
     * 插入hashmap
     * @param hashmapname
     * @param map
     * @param seconds 失效时间
     */
    public void hmset(final String hashmapname,final Map<K,V> map,final long seconds) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
        for(Map.Entry<K,V> entry:map.entrySet()){
        	hashBounds.put(entry.getKey(), entry.getValue());
        }
        hashBounds.expire(seconds, TimeUnit.SECONDS);
    }
    
    /**
     * 获取hashmap值
     * @param hashmapname
     * @param key
     * @return
     */
    public Object hmget(final String hashmapname,final String key) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
        return hashBounds.get(key);
    }
    
    /**
     * 获取hashmap集合
     * @param hashmapname
     * @return
     */
    public Map<Object, Object> hmgetAll(final String hashmapname) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
        return hashBounds.entries();
    }
    
    /**
     * hashmap值增长1
     * @param hashmapname
     * @param key
     * @return
     */
    public long hmincrement(final String hashmapname,final String key) {
    	return hmincrement(hashmapname,key,1);
    }	
    
    /**
     * hashmap值增长
     * @param hashmapname
     * @param key
     * @param delta 增长频度
     * @return
     */
    public long hmincrement(final String hashmapname,final String key,final long delta) {
        BoundHashOperations<K,Object,Object> hashBounds = redisTemplate.boundHashOps((K) hashmapname);
        return hashBounds.increment(key, delta);
    }
    
    /**
     * hashmap值减少1
     * @param hashmapname
     * @param key
     * @return
     */
    public long hmdecrement(final String hashmapname,final String key) {
    	return hmincrement(hashmapname,key,-1);
    }	
    
    
    /**
     * SET增加
     * @param setname
     * @param list
     */
    public void sadd(final String setname,final List<String> list) {
    	BoundSetOperations<K,String> setBounds = (BoundSetOperations<K, String>) redisTemplate.boundSetOps((K) setname);
    	for(String o:list){
    		setBounds.add(o);
    	}
    }
    
    /**
     * SET取出
     * @param setname
     * @return
     */
    public String spop(final String setname) {
    	BoundSetOperations<K,String> setBounds = (BoundSetOperations<K, String>) redisTemplate.boundSetOps((K) setname);
    	return setBounds.pop();
    }
    
    /**
     * key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(final String key) {
      return redisTemplate.hasKey((K) key);
    }
    
    /**
     * 查找listkey
     * @param key
     * @return
     */
    public String findListKey(final String key) {
      String listKey = "";
      Set<K> set = redisTemplate.keys((K) (key + "*"));
      Iterator<K> it = set.iterator();
      if(it.hasNext()){
        listKey = (String) it.next();
      }
      return listKey;
    }
    
    /**
     * 获取SET所有数据
     * @param setname
     * @return Set<String>
     */
    public Set<String> smembers(final String setname) {
    	BoundSetOperations<K,String> setBounds = (BoundSetOperations<K, String>) redisTemplate.boundSetOps((K) setname);
    	return setBounds.members();
    }
     
    protected RedisSerializer<String> getRedisSerializer() { 
        return redisTemplate.getStringSerializer(); 
    } 
 
    public RedisTemplate<K,V> getRedisTemplate() {
        return redisTemplate;
    }
 
    public void setRedisTemplate(RedisTemplate<K,V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
}