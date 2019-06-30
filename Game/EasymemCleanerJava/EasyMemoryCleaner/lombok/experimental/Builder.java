package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.CONSTRUCTOR})
@Retention(RetentionPolicy.SOURCE)
@Deprecated
public @interface Builder {
  String builderMethodName() default "builder";
  
  String buildMethodName() default "build";
  
  String builderClassName() default "";
  
  boolean fluent() default true;
  
  boolean chain() default true;
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\experimental\Builder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */