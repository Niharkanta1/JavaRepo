package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface Setter {
  AccessLevel value() default AccessLevel.PUBLIC;
  
  AnyAnnotation[] onMethod() default {};
  
  AnyAnnotation[] onParam() default {};
  
  @Deprecated
  @Retention(RetentionPolicy.SOURCE)
  @Target({})
  public static @interface AnyAnnotation {}
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\Setter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */