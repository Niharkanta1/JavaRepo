package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ToString {
  boolean includeFieldNames() default true;
  
  String[] exclude() default {};
  
  String[] of() default {};
  
  boolean callSuper() default false;
  
  boolean doNotUseGetters() default false;
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\ToString.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */