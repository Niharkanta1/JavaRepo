package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
public @interface Accessors {
  boolean fluent() default false;
  
  boolean chain() default false;
  
  String[] prefix() default {};
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\experimental\Accessors.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */