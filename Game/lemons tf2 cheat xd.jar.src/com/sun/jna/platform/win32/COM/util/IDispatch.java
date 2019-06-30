package com.sun.jna.platform.win32.COM.util;

public interface IDispatch extends IUnknown {
  <T> void setProperty(String paramString, T paramT);
  
  <T> T getProperty(Class<T> paramClass, String paramString, Object... paramVarArgs);
  
  <T> T invokeMethod(Class<T> paramClass, String paramString, Object... paramVarArgs);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\IDispatch.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */