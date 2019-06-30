package com.sun.jna.platform.win32.COM.util;

import com.sun.jna.platform.win32.COM.IDispatchCallback;

public interface IComEventCallbackListener {
  void setDispatchCallbackListener(IDispatchCallback paramIDispatchCallback);
  
  void errorReceivingCallbackEvent(String paramString, Exception paramException);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\IComEventCallbackListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */