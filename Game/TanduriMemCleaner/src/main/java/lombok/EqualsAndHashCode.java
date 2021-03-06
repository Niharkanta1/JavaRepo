package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface EqualsAndHashCode {
  String[] exclude() default {};
  
  String[] of() default {};
  
  boolean callSuper() default false;
  
  boolean doNotUseGetters() default false;
  
  AnyAnnotation[] onParam() default {};
  
  @Deprecated
  @Retention(RetentionPolicy.SOURCE)
  @Target({})
  public static @interface AnyAnnotation {}
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\EqualsAndHashCode.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */