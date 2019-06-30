package lombok.extern.java;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.TYPE})
public @interface Log {
  String topic() default "";
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\extern\java\Log.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */