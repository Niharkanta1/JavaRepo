package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
public @interface Builder {
  String builderMethodName() default "builder";
  
  String buildMethodName() default "build";
  
  String builderClassName() default "";
  
  boolean toBuilder() default false;
  
  @Target({ElementType.FIELD, ElementType.PARAMETER})
  @Retention(RetentionPolicy.SOURCE)
  public static @interface ObtainVia {
    String field() default "";
    
    String method() default "";
    
    boolean isStatic() default false;
  }
  
  @Target({ElementType.FIELD})
  @Retention(RetentionPolicy.SOURCE)
  public static @interface Default {}
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\Builder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */