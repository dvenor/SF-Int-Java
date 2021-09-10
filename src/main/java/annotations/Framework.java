package annotations;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Framework {
  public static void main(String[] args) throws Throwable {
    System.setSecurityManager(new SecurityManager());
    String classname = "annotations.SomeTests";
    Class<?> theClass = Class.forName(classname);

    Constructor<?> cons = theClass.getConstructor();
    Object obj = cons.newInstance();

    System.out.println("Type of created object is " + obj.getClass().getName());
//    Method[] methods =  theClass.getMethods();
    Method[] methods =  theClass.getDeclaredMethods();

    for (Method m : methods) {
      System.out.println("> " + m);
      RunMe annotation = m.getAnnotation(RunMe.class);
      if (annotation != null) {
        System.out.println("RunMe Annotation found!!! name is " + annotation.name());
        m.setAccessible(true);
        m.invoke(obj);
      }
    }
  }
}
