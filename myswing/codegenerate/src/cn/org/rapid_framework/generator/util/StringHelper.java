package cn.org.rapid_framework.generator.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.org.rapid_framework.generator.provider.db.table.model.Column.EnumMetaDada;

/**
 * 
 * @author badqiu
 * @email badqiu(a)gmail.com
 */
public class StringHelper {
	
	public static String removeCrlf(String str) {
		if(str == null) return null;
		return StringHelper.join(StringHelper.tokenizeToStringArray(str,"\t\n\r\f")," ");
	}
	
	private static final Map<String,String> XML = new HashMap<String,String>();
	static{
		XML.put("apos", "'");
		XML.put("quot", "\"");
		XML.put("amp", "&");
		XML.put("lt", "<");
		XML.put("gt", ">");
	}
	
	public static String unescapeXml(String str) {
		if(str == null) return null;
		for(String key : XML.keySet()) {
			String value = XML.get(key);
			str = StringHelper.replace(str, "&"+key+";", value);
		}
		return str;
	}
	
	
		 
	/**
	 * 去前缀
	 * @param str  t_user
	 * @param prefix  t_
	 * @return  user
	 */
	public static String removePrefix(String str,String prefix) {
		return removePrefix(str,prefix,false);
	}
	
	
	/**
	 * 忽略大小写去前缀
	 * @param str
	 * @param prefix
	 * @param ignoreCase
	 * @return
	 */
	public static String removePrefix(String str,String prefix,boolean ignoreCase) {
		if(str == null) return null;
		if(prefix == null) return str;
		if(ignoreCase) {
			if(str.toLowerCase().startsWith(prefix.toLowerCase())) {
				return str.substring(prefix.length());
			}
		}else {
			if(str.startsWith(prefix)) {
				return str.substring(prefix.length());
			}
		}
		return str;
	}
	
    public static boolean isBlank(String str) {
        return str == null || str.trim().length() == 0;
    }

    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
    
    /**
     * 获取后缀
     * @param str com.org.aa.A.java
     * @return java
     */
    public static String getExtension(String str) {
    	if(str == null) return null;
    	int i = str.lastIndexOf('.');
    	if(i >= 0) {
    		return str.substring(i+1);
    	}
    	return null;
    }
    
    
    
   

	/**
	 * Count the occurrences of the substring in string s.
	 * @param str string to search in. Return 0 if this is null.  com.org.aa.Aaa.javaa
	 * @param sub string to search for. Return 0 if this is null.  aa
	 * @return 3
	 */
	public static int countOccurrencesOf(String str, String sub) {
		if (str == null || sub == null || str.length() == 0 || sub.length() == 0) {
			return 0;
		}
		int count = 0;
		int pos = 0;
		int idx;
		while ((idx = str.indexOf(sub, pos)) != -1) {
			++count;
			pos = idx + sub.length();
		}
		return count;
	}
	
	

	
	public static boolean contains(String str,String... keywords) {
		if(str == null) return false;
		if(keywords == null) throw new IllegalArgumentException("'keywords' must be not null");
		
		for(String keyword : keywords) {
			if(str.contains(keyword.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

   public static String defaultString(Object value) {
        if(value == null) {
            return "";
        }
        return value.toString();
    }
	   
   
   /**
    * 如果是空返回默认值，如果非空就转化成字符串
    * @param value
    * @param defaultValue
    * @return
    */
	public static String defaultIfEmpty(Object value,String defaultValue) {
		if(value == null || "".equals(value)) {
			return defaultValue;
		}
		return value.toString();
	}
	
	
	/**
	 * 将数据库的命名转成成对象的命名
	 * @param sqlName  clap_user_name
	 * @return  ClapUserName
	 */
	public static String makeAllWordFirstLetterUpperCase(String sqlName) {
		String[] strs = sqlName.toLowerCase().split("_");
		String result = "";
		String preStr = "";
		for(int i = 0; i < strs.length; i++) {
			if(preStr.length() == 1) {
				result += strs[i];
			}else {
				result += capitalize(strs[i]);
			}
			preStr = strs[i];
		}
		return result;
	}
	
	
	
	
	
	
	
	/**
	 * 返回regex在input中的位置，如果重复返回第一个位置
	 * @param input  clap_user_name
	 * @param regex  a
	 * @return  2
	 */
	public static int indexOfByRegex(String input,String regex) {
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(input);
		if(m.find()) {
			return m.start();
		}
		return -1;
	}
	
	
	
	/**
	 * 转化成java变量名
	 * @param str
	 * @return
	 */
	public static String toJavaVariableName(String str) {
		return uncapitalize(toJavaClassName(str));
	}

	
	/**
	 * 转化成java类名
	 * @param str
	 * @return
	 */
	public static String toJavaClassName(String str) {
		return makeAllWordFirstLetterUpperCase(StringHelper.toUnderscoreName(str));
	}
	
	

	/**
	 * 从inString中去掉所有keywords
	 * @param inString
	 * @param keywords
	 * @return
	 */
	public static String removeMany(String inString, String... keywords) {
		if (inString == null) {
			return null;
		}
		for(String k : keywords) {
			inString = replace(inString, k, "");
		}
		return inString;
	}
	
	
	/**
	 * inString中的oldPattern字符串用newPattern替换
	 * @param inString
	 * @param oldPattern
	 * @param newPattern
	 * @return
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (inString == null) {
			return null;
		}
		if (oldPattern == null || newPattern == null) {
			return inString;
		}

		StringBuffer sbuf = new StringBuffer();
		// output StringBuffer we'll build up
		int pos = 0; // our position in the old string
		int index = inString.indexOf(oldPattern);
		// the index of an occurrence we've found, or -1
		int patLen = oldPattern.length();
		while (index >= 0) {
			sbuf.append(inString.substring(pos, index));
			sbuf.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}
		sbuf.append(inString.substring(pos));

		// remember to append any characters to the right of a match
		return sbuf.toString();
	}
	
	
	/**
	 * 第一个字符转化成大写
	 * @param str
	 * @return
	 */
	public static String capitalize(String str) {
		return changeFirstCharacterCase(str, true);
	}
	
	
	/**
	 * 第一个字符转化成小写
	 * @param str
	 * @return
	 */
	public static String uncapitalize(String str) {
		return changeFirstCharacterCase(str, false);
	}
	
	/**
	 *capitalize是true 第一个字符大写,否则就是小写
	 * @param str
	 * @param capitalize
	 * @return
	 */
	private static String changeFirstCharacterCase(String str, boolean capitalize) {
		if (str == null || str.length() == 0) {
			return str;
		}
		StringBuffer buf = new StringBuffer(str.length());
		if (capitalize) {
			buf.append(Character.toUpperCase(str.charAt(0)));
		}
		else {
			buf.append(Character.toLowerCase(str.charAt(0)));
		}
		buf.append(str.substring(1));
		return buf.toString();
	}
	
	
	
	
	
	
	private static final Random RANDOM = new Random();
	
	/**
	 * 产生count个数字字符串
	 * @param count  6
	 * @return  371093
	 */
	public static String randomNumeric(int count) {
        return random(count, false, true);
    }
	
	
	
	
	/**
	 * 产生count个字符个数（字符或数字）
	 * @param count  字符创长度
	 * @param letters  字符
	 * @param numbers  数字
	 * @return
	 */
	public static String random(int count, boolean letters, boolean numbers) {
	    return random(count, 0, 0, letters, numbers);
	}
	
	/**
	 * StringHelper.random(6,0,0,false, true);   697916
	 * StringHelper.random(6,0,0,true, false);   YoLHpz
	 * @param count
	 * @param start
	 * @param end
	 * @param letters
	 * @param numbers
	 * @return
	 */
    public static String random(int count, int start, int end, boolean letters, boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }
	 
	public static String random(int count, int start, int end, boolean letters,
			boolean numbers, char[] chars, Random random) {
		if (count == 0) {
			return "";
		} else if (count < 0) {
			throw new IllegalArgumentException(
					"Requested random string length " + count
							+ " is less than 0.");
		}
		if ((start == 0) && (end == 0)) {
			end = 'z' + 1;
			start = ' ';
			if (!letters && !numbers) {
				start = 0;
				end = Integer.MAX_VALUE;
			}
		}

		char[] buffer = new char[count];
		int gap = end - start;

		while (count-- != 0) {
			char ch;
			if (chars == null) {
				ch = (char) (random.nextInt(gap) + start);
			} else {
				ch = chars[random.nextInt(gap) + start];
			}
			if ((letters && Character.isLetter(ch))
					|| (numbers && Character.isDigit(ch))
					|| (!letters && !numbers)) {
				if (ch >= 56320 && ch <= 57343) {
					if (count == 0) {
						count++;
					} else {
						// low surrogate, insert high surrogate after putting it
						// in
						buffer[count] = ch;
						count--;
						buffer[count] = (char) (55296 + random.nextInt(128));
					}
				} else if (ch >= 55296 && ch <= 56191) {
					if (count == 0) {
						count++;
					} else {
						// high surrogate, insert low surrogate before putting
						// it in
						buffer[count] = (char) (56320 + random.nextInt(128));
						count--;
						buffer[count] = ch;
					}
				} else if (ch >= 56192 && ch <= 56319) {
					// private high surrogate, no effing clue, so skip it
					count++;
				} else {
					buffer[count] = ch;
				}
			} else {
				count++;
			}
		}
		return new String(buffer);
	}
	
	/**
	 * 相当于类名变表名的意思
	 * UserName-->user_name
	 * Convert a name in camelCase to an underscored name in lower case.
	 * Any upper case letters are converted to lower case with a preceding underscore.
	 * @param filteredName the string containing original name
	 * @return the converted name
	 */
	public static String toUnderscoreName(String name) {
		if(name == null) return null;
		
		String filteredName = name;
		if(filteredName.indexOf("_") >= 0 && filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}
		if(filteredName.indexOf("_") == -1 && filteredName.equals(filteredName.toUpperCase())) {
			filteredName = filteredName.toLowerCase();
		}
		
		StringBuffer result = new StringBuffer();
		if (filteredName != null && filteredName.length() > 0) {
			result.append(filteredName.substring(0, 1).toLowerCase());
			for (int i = 1; i < filteredName.length(); i++) {
				String preChart = filteredName.substring(i - 1, i);
				String c = filteredName.substring(i, i + 1);
				if(c.equals("_")) {
					result.append("_");
					continue;
				}
				if(preChart.equals("_")){
					result.append(c.toLowerCase());
					continue;
				}
				if(c.matches("\\d")) {
					result.append(c);
				}else if (c.equals(c.toUpperCase())) {
					result.append("_");
					result.append(c.toLowerCase());
				}
				else {
					result.append(c);
				}
			}
		}
		return result.toString();
	}
	
	
	
	
	/**
	 * 去掉结尾的扩展名
	 * @param inputString   cn.org.AA.java
	 * @param endWiths  .java
	 * @return
	 */
	public static String removeEndWiths(String inputString,String... endWiths) {
	    for(String endWith : endWiths) {
    	    if(inputString.endsWith(endWith)) {
                return inputString.substring(0,inputString.length() - endWith.length());
            }
	    }
	    return inputString;
	}
	
	
	
	public static void main(String[] args) {
		//List<EnumMetaDada> list = string2EnumMetaData("M(1,男)");
		List<EnumMetaDada> list = string2EnumMetaData("F(女)");
		for (EnumMetaDada enumMetaDada : list) {
			System.out.println(enumMetaDada.getEnumAlias());
			System.out.println(enumMetaDada.getEnumKey());
			System.out.println(enumMetaDada.getEnumDesc());
			
		}
	}
	
	
	/**
	 * 将string转换为List<ColumnEnum> 格式为: "enumAlias(enumKey,enumDesc)"
	 * M(1,男);F(0,女) 或者是:M(男);F(女) 
	 */
	static Pattern three = Pattern.compile("(.*)\\((.*),(.*)\\)");
	static Pattern two = Pattern.compile("(.*)\\((.*)\\)");
	public static List<EnumMetaDada> string2EnumMetaData(String data) {
		if(data == null || data.trim().length() == 0) return new ArrayList();
		//enumAlias(enumKey,enumDesc),enumAlias(enumDesc)
		
		List<EnumMetaDada> list = new ArrayList();
		Pattern p = Pattern.compile("\\w+\\(.*?\\)");
		Matcher m = p.matcher(data);
		while (m.find()) {
			String str = m.group();
            Matcher three_m = three.matcher(str);
			if(three_m.find()) {
				list.add(new EnumMetaDada(three_m.group(1),three_m.group(2),three_m.group(3)));
				continue;
			}
			Matcher two_m = two.matcher(str);
			if(two_m.find()) {
				list.add(new EnumMetaDada(two_m.group(1),two_m.group(1),two_m.group(2)));
				continue;
			}			
			throw new IllegalArgumentException("error enumString format:"+data+" expected format:F(1,Female);M(0,Male) or F(Female);M(Male)");
		}
		return list;
	}
	
	/**
	 * Test whether the given string matches the given substring
	 * at the given index.
	 * @param str the original string (or StringBuilder)
	 * @param index the index in the original string to start matching against
	 * @param substring the substring to match at the given index
	 */
	public static boolean substringMatch(CharSequence str, int index, CharSequence substring) {
		for (int j = 0; j < substring.length(); j++) {
			int i = index + j;
			if (i >= str.length() || str.charAt(i) != substring.charAt(j)) {
				return false;
			}
		}
		return true;
	}
	
	
	
	/**
	 * 将字符串拆分成数组
	 * @param str  "aaa,bbb,ccc"
	 * @param seperators   ","
	 * @return 数组
	 */
	public static String[] tokenizeToStringArray(String str,String seperators) {
		if(str == null) return new String[0];
		StringTokenizer tokenlizer = new StringTokenizer(str,seperators);
		List result = new ArrayList();
		
		while(tokenlizer.hasMoreElements()) {
			Object s = tokenlizer.nextElement();
			result.add(s);
		}
		return (String[])result.toArray(new String[result.size()]);
	}
	
	

	
	/**
	 * 将List中的元素用seperator拼接起来
	 * @param list
	 * @param seperator  ","
	 * @return  aaa,bbb,ccc
	 */
    public static String join(List list, String seperator) {
        return join(list.toArray(new Object[0]),seperator);
    }
    
    /**
     * 替换指定位置的字符串
     * @param start
     * @param end
     * @param str
     * @param replacement
     * @return
     */
    public static String replace(int start, int end, String str,String replacement) {
        String before = str.substring(0,start);
        String after = str.substring(end);
        return before + replacement + after;
    }
    
	
	
    
	/**
	 * 将数组用分割符连接起来
	 * @param array new Object[]{"aa","bb",1,2}
	 * @param seperator  ","
	 * @return  aa,bb,1,2
	 */
	public static String join(Object[] array, String seperator) {
		if(array == null) return null;
		StringBuffer result = new StringBuffer();
		for(int i = 0; i < array.length; i++) {
			result.append(array[i]);
			if(i != array.length - 1)  {
				result.append(seperator);
			}
		}
		return result.toString();
	}

	
	/**
	 * string中包含keyword的次数
	 * @param string
	 * @param keyword
	 * @return
	 */
	public static int containsCount(String string, String keyword) {
		if(string == null) return 0;
		int count = 0;
		for(int i = 0; i < string.length(); i++ ) {
			int indexOf = string.indexOf(keyword,i);
			if(indexOf < 0) {
				break;
			}
			count ++;
			i = indexOf;
		}
		return count;
	}
}
