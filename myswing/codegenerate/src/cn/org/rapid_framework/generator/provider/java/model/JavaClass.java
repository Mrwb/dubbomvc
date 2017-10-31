package cn.org.rapid_framework.generator.provider.java.model;

import java.beans.BeanInfo;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import cn.org.rapid_framework.generator.util.StringHelper;
import cn.org.rapid_framework.generator.util.typemapping.ActionScriptDataTypesUtils;
import cn.org.rapid_framework.generator.util.typemapping.JavaPrimitiveTypeMapping;

public class JavaClass {
	private Class clazz;
	public JavaClass(Class clazz) {
		this.clazz = clazz;
	}
	
	public String getClassName() {
		return this.clazz.getSimpleName();
	}
	
	public String getPackageName() {
		return clazz.getPackage().getName();
	}
	
	//最后一个包名
	public String getLastPackageName() {
		return StringHelper.getExtension(getPackageName());
	}
	

	//最后一个包名，首字母大写
	public String getLastPackageNameFirstUpper() {
		return getLastPackageName() == null ? "" : StringHelper.capitalize(getLastPackageName());
	}
	
	//是否是默认构造方法
	public boolean isHasDefaultConstructor() {
	    for(Constructor c : clazz.getConstructors()) {
	        if(c.isAccessible() && Modifier.isPublic(c.getModifiers())) {
	            if(c.getParameterTypes().length == 0) {
	                return true;
	            }
	        }
	    }
	    return false;
	}
	
	
	
	
	
	
	public Set<JavaClass> getImportClasses() {
	    Set<JavaClass> set = new LinkedHashSet<JavaClass>();
	    for(Method m :clazz.getMethods()) {
	        addImportClass(set, m.getReturnType());
	        for(Class<?> paramType : m.getParameterTypes()) {
	            addImportClass(set, paramType);
	        }
	    }
	    for(Field f :clazz.getFields()) {
            addImportClass(set, f.getType());
        }
	    return set;
	}

    private boolean addImportClass(Set<JavaClass> set, Class<?> clazz) {
        if(clazz == null) return false;
        if(clazz.getName().startsWith("java.lang.")) return false;
        if(clazz.isPrimitive()) return false;
        if("void".equals(clazz.getName())) return false;
        return set.add(new JavaClass(clazz));
    }
	

    
    //父类名字
	public String getSuperclassName() {
		return clazz.getSuperclass() != null ? clazz.getSuperclass().getName() : null;
	}
	
	//构造方法不算，
	public JavaMethod[] getMethods() {
		return toJavaMethods(clazz.getDeclaredMethods());
	}
	
	
	public JavaMethod[] getPublicMethods() {
		Method[] methods = clazz.getDeclaredMethods();
		return toJavaMethods(filterByModifiers(methods,Modifier.PUBLIC));
	}

	//共有的或静态方法
	public JavaMethod[] getPublicStaticMethods() {
		Method[] methods = clazz.getDeclaredMethods();
		return toJavaMethods(filterByModifiers(methods,Modifier.PUBLIC,Modifier.STATIC));
	}

	//共有的或非静态方法
	public JavaMethod[] getPublicNotStaticMethods() {
		Method[] staticMethods = filterByModifiers(clazz.getDeclaredMethods(),Modifier.STATIC);
		Method[] publicMethods = filterByModifiers(clazz.getDeclaredMethods(),Modifier.PUBLIC);
		Method[] filtered = exclude(publicMethods,staticMethods).toArray(new Method[0]);
		return toJavaMethods(filtered);
	}
	
	public JavaProperty[] getProperties() throws Exception {
		List<JavaProperty> result = new ArrayList<JavaProperty>();
		BeanInfo beanInfo = Introspector.getBeanInfo(clazz);
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		for(PropertyDescriptor pd : pds) {
			
			System.out.println("//////////////////////////////////////////////////");
			System.out.println("****getName*******************" + pd.getName());
			System.out.println("****getDisplayName*******************" + pd.getDisplayName());
			System.out.println("****getShortDescription*******************" + pd.getShortDescription());
			System.out.println("****getClass*******************" + pd.getClass());
			System.out.println("****getPropertyEditorClass*******************" + pd.getPropertyEditorClass());
			System.out.println("****getPropertyType*******************" + pd.getPropertyType());
			System.out.println("****getReadMethod*******************" + pd.getReadMethod());
			System.out.println("****getWriteMethod*******************" + pd.getWriteMethod());
			System.out.println("****getValue*******************" + pd.getValue(pd.getName()));

			
			result.add(new JavaProperty(pd,this));
		}
		return (JavaProperty[])result.toArray(new JavaProperty[0]);
	}
	
	public List<JavaField> getFields() {
		Field[] fields = clazz.getDeclaredFields();
		List result = new ArrayList();
		for(Field f : fields) {
			result.add(new JavaField(f,this));
		}
		return result;
	}
	
	
	
	
    
	
	
	
	//me/org/model
	public String getPackagePath(){
		return getPackageName().replace(".", "/");
	}
	
	//me.org
	public String getParentPackageName() {
		return getPackageName().substring(0,getPackageName().lastIndexOf("."));
	}

	//me/org
	public String getParentPackagePath() {
		return getParentPackageName().replace(".", "/");
	}
	
	///D:/ws_hgxs1_trunk/rapid_generator_me/bin/me/org/model/UserRole.class
	public String getClassFile() {
	    return clazz.getClassLoader().getResource(clazz.getName().replace('.', '/')+".class").getFile();
	}

	//me/org/model/UserRole.java
	public String getJavaSourceFile() {
	        return clazz.getName().replace('.', '/')+".java";
	}
	
	/**
	 *  /D:/ws_hgxs1_trunk/rapid_generator_me/bin/
	 * 得到class是在那个classpath路径装载
	 * @return
	 */
    public String getLoadedClasspath() {
        return getClassFile().substring(0,getClassFile().length() - (clazz.getName()+".class").length());
    }
	
    //me.org.model.UserRole
	public String getAsType() {
		return ActionScriptDataTypesUtils.getPreferredAsType(clazz.getName());
	}
	
	// me.org.model.UserRole
	public String getJavaType() {
	    if(isArray()) {
	        return clazz.getComponentType().getName();
	    }else {
	        return clazz.getName();
	    }
	}

	//me.org.model.UserRole
	public String getPrimitiveJavaType() {
	    return JavaPrimitiveTypeMapping.getPrimitiveType(getJavaType());
	}
	
	
	/*
	public static void main(String[] args) throws Exception {
		JavaClass javaClass  = new JavaClass(UserRole.class);
		System.out.println("getClassName :    " + javaClass.getClassName());
		System.out.println("getPackageName :   " + javaClass.getPackageName());
		System.out.println("getLastPackageName  :    " + javaClass.getLastPackageName());
		System.out.println("getLastPackageNameFirstUpper  :    " + javaClass.getLastPackageNameFirstUpper());
		System.out.println("isHasDefaultConstructor  :    " + javaClass.isHasDefaultConstructor());
		System.out.println("getSuperclassName  :    " + javaClass.getSuperclassName());
		
		Set<JavaClass> sets =  javaClass.getImportClasses();
		for (JavaClass c : sets) {
			System.out.println(c.toString());
		}
		
		
		System.out.println("javaClass.getMethods()  :    " + javaClass.getMethods().length);
		System.out.println("javaClass.getPublicMethods()  :    " + javaClass.getPublicMethods().length);
		System.out.println("javaClass.getPublicStaticMethods()  :    " + javaClass.getPublicStaticMethods().length);
		System.out.println("javaClass.getPublicNotStaticMethods()  :    " + javaClass.getPublicNotStaticMethods().length);
		System.out.println("javaClass.getProperties()  :    " + javaClass.getProperties().length);
		System.out.println("javaClass.getMethods()  :    " + javaClass.getMethods().length);
		System.out.println("javaClass.getMethods()  :    " + javaClass.getMethods().length);
		
		
		System.out.println("javaClass.getPackagePath()  :    " + javaClass.getPackagePath());
		System.out.println("javaClass.getParentPackageName()  :    " + javaClass.getParentPackageName());
		System.out.println("javaClass.getParentPackagePath()  :    " + javaClass.getParentPackagePath());
		System.out.println("javaClass.getClassFile()  :    " + javaClass.getClassFile());
		System.out.println("javaClass.getJavaSourceFile()  :    " + javaClass.getJavaSourceFile());
		System.out.println("javaClass.getLoadedClasspath()  :    " + javaClass.getLoadedClasspath());
		
		
		System.out.println("javaClass.getAsType()  :    " + javaClass.getAsType());
		System.out.println("javaClass.getJavaType()  :    " + javaClass.getJavaType());
		
		System.out.println("javaClass.getPrimitiveJavaType()  :    " + javaClass.getPrimitiveJavaType());
		
		List list = new ArrayList();
		Set  set = new HashSet();
		Map map = new HashMap();
		
		
		System.out.println(list.getClass().getSuperclass().toString());
		System.out.println(set.getClass().getSuperclass().toString());
		System.out.println(map.getClass().getSuperclass().toString());
		
	}
	*/
	
	
	public static void main(String[] args) throws Exception {
		
		/*JavaClass javaClass  = new JavaClass(UserRole.class);
		System.out.println("****类的名称是：" + javaClass.getClassName());
		JavaMethod[]  methods = javaClass.getMethods();
		System.out.println("****类下面的方法有：");
		for (JavaMethod javaMethod : methods) {
			System.out.println("********方法名称是：" + javaMethod.getMethodName());
			List<MethodParameter>  methodParameters = javaMethod.getParameters();
			System.out.println("********方法的参数有：");
			for (MethodParameter methodParameter : methodParameters) {
				System.out.println("************参数getName是：" + methodParameter.getName());
				System.out.println("************参数getJavaType是：" + methodParameter.getJavaType());
				System.out.println("************参数getPackageName是：" + methodParameter.getPackageName());
				System.out.println("************参数getParamIndex是：" + methodParameter.getParamIndex());
				//System.out.println("************lookupParameterNamesByParanamer:" + methodParameter.lookupParameterNamesByParanamer());
			
			}
			
			JavaClass returnType = javaMethod.getReturnType();
			System.out.println("********方法的返回类型有：" + returnType.getClassName());
			
		}
		
		
		
		List<JavaField>  fields =  javaClass.getFields();
		
		*/
		
		
		
		
		
		
		
	}
	
	
	
	
	public String getCanonicalName() {
		return clazz.getCanonicalName();
	}

	public JavaField getField(String name) throws NoSuchFieldException,SecurityException {
		return new JavaField(clazz.getField(name),this);
	}

	public JavaClass getSuperclass() {
		return new JavaClass(clazz.getSuperclass());
	}

	public boolean isAnnotation() {
		return clazz.isAnnotation();
	}

	public boolean isAnonymousClass() {
		return clazz.isAnonymousClass();
	}

	public boolean isArray() {
		return clazz.isArray();
	}

	public boolean isEnum() {
		return clazz.isEnum();
	}

	public boolean isInstance(Object obj) {
		return clazz.isInstance(obj);
	}

	public boolean isInterface() {
		return clazz.isInterface();
	}

	public boolean isLocalClass() {
		return clazz.isLocalClass();
	}

	public boolean isMemberClass() {
		return clazz.isMemberClass();
	}

	public boolean isPrimitive() {
		return clazz.isPrimitive();
	}

	public boolean isSynthetic() {
		return clazz.isSynthetic();
	}

	public Class getClazz() {
	    return clazz;
	}
	
	
	//根据Modifier过滤方法
	private Method[] filterByModifiers(Method[] methods,int... filteredModifiers) {
		List<Method> filtered = new ArrayList<Method>();
		for(int i = 0; i < methods.length; i++) {
			for(int j = 0; j < filteredModifiers.length; j++) {
				if((filteredModifiers[j] & methods[i].getModifiers()) != 0) {
					filtered.add(methods[i]);
				}
			}
		}
		return filtered.toArray(new Method[0]);
	}
	
	private JavaMethod[] toJavaMethods(Method[] declaredMethods) {
		JavaMethod[] methods = new JavaMethod[declaredMethods.length];
		for(int i = 0; i < declaredMethods.length; i++) {
			methods[i] = new JavaMethod(declaredMethods[i],this);
		}
		return methods;
	}
	
	private <T> List<T> exclude(T[] methods, T[] excludeMethods) {
		List<T> result = new ArrayList<T>();
		outerLoop:
		for(int i = 0; i < methods.length; i++) {
			for(int j = 0;j < excludeMethods.length; j++) {
				if(methods[i].equals(excludeMethods[j])) {
					break outerLoop;
				}
			}
			result.add(methods[i]);
		}
		return result;
	}
	
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((clazz == null) ? 0 : clazz.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        JavaClass other = (JavaClass) obj;
        if (clazz == null) {
            if (other.clazz != null)
                return false;
        } else if (!clazz.equals(other.clazz))
            return false;
        return true;
    }

    public String toString() {
		return "JavaClass:"+clazz.getName();
	}
}
