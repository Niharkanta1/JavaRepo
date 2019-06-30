package lombok;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.LOCAL_VARIABLE})
@Retention(RetentionPolicy.CLASS)
@Documented
public @interface NonNull {}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\NonNull.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */