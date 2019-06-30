package com.sun.jna;

public interface FromNativeConverter {
  Object fromNative(Object paramObject, FromNativeContext paramFromNativeContext);
  
  Class nativeType();
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\FromNativeConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */