package annotations;

//public @ interface RunMe {

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//@Target({ElementType.METHOD, ElementType.CONSTRUCTOR})
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface RunMe {
  // types of elements are strictly limited
  // compiler must be able to "fill in" the value at compilation (i.e. from sourcecode)
  // primitives, String, Class, other annotations (but circularity is prohibited)
  // and arrays of the above (no multi-dimensional arrays!)
  String name() default "Unknown";
  int value();
}
