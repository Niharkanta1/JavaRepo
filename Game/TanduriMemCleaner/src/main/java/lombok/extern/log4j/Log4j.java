package lombok.extern.log4j;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
public @interface Log4j {
  String topic() default "";
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\extern\log4j\Log4j.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */