package lombok.experimental;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface ExtensionMethod {
  Class<?>[] value();
  
  boolean suppressBaseMethods() default true;
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\experimental\ExtensionMethod.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */