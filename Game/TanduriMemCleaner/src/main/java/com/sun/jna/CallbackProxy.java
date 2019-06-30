package com.sun.jna;

public interface CallbackProxy extends Callback {
  Object callback(Object[] paramArrayOfObject);
  
  Class[] getParameterTypes();
  
  Class getReturnType();
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\CallbackProxy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */