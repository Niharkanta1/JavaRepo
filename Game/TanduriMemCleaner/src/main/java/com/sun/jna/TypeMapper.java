package com.sun.jna;

public interface TypeMapper {
  FromNativeConverter getFromNativeConverter(Class paramClass);
  
  ToNativeConverter getToNativeConverter(Class paramClass);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\TypeMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */