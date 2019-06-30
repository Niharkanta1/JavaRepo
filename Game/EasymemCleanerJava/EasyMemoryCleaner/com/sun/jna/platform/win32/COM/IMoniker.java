package com.sun.jna.platform.win32.COM;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WTypes;
import com.sun.jna.platform.win32.WinNT;

public interface IMoniker extends IPersistStream {
  void BindToObject();
  
  void BindToStorage();
  
  void Reduce();
  
  void ComposeWith();
  
  void Enum();
  
  void IsEqual();
  
  void Hash();
  
  void IsRunning();
  
  void GetTimeOfLastChange();
  
  void Inverse();
  
  void CommonPrefixWith();
  
  WinNT.HRESULT GetDisplayName(Pointer paramPointer1, Pointer paramPointer2, WTypes.BSTRByReference paramBSTRByReference);
  
  void ParseDisplayName();
  
  void IsSystemMoniker();
  
  void RelativePathTo();
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\IMoniker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */