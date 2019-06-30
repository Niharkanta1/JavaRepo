package com.sun.jna.platform.win32.COM.util.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Inherited
public @interface ComObject {
  String clsId() default "";
  
  String progId() default "";
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\annotation\ComObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */