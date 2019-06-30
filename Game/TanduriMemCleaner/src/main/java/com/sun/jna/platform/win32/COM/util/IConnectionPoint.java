package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.COMException;

public interface IConnectionPoint {
  IComEventCallbackCookie advise(Class<?> paramClass, IComEventCallbackListener paramIComEventCallbackListener) throws COMException;
  
  void unadvise(Class<?> paramClass, IComEventCallbackCookie paramIComEventCallbackCookie);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\IConnectionPoint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */