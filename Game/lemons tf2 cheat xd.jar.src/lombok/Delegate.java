package lombok;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.SOURCE)
@Deprecated
public @interface Delegate {
  Class<?>[] types() default {};
  
  Class<?>[] excludes() default {};
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\Delegate.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */