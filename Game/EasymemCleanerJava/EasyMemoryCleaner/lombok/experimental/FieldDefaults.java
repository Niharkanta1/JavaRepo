package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import lombok.AccessLevel;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface FieldDefaults {
  AccessLevel level() default AccessLevel.NONE;
  
  boolean makeFinal() default false;
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\experimental\FieldDefaults.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */