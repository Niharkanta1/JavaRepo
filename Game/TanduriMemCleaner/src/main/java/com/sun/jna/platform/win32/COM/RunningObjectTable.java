/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.platform.win32.WinBase;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RunningObjectTable
/*     */   extends Unknown
/*     */   implements IRunningObjectTable
/*     */ {
/*     */   public static class ByReference
/*     */     extends RunningObjectTable
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public RunningObjectTable() {}
/*     */   
/*  33 */   public RunningObjectTable(Pointer pointer) { super(pointer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT Register(WinDef.DWORD grfFlags, Pointer punkObject, Pointer pmkObjectName, WinDef.DWORDByReference pdwRegister) {
/*  42 */     int vTableId = 3;
/*     */     
/*  44 */     return (WinNT.HRESULT)_invokeNativeObject(3, new Object[] { getPointer(), grfFlags, punkObject, pmkObjectName, pdwRegister }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT Revoke(WinDef.DWORD dwRegister) {
/*  52 */     int vTableId = 4;
/*     */     
/*  54 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] { getPointer(), dwRegister }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT IsRunning(Pointer pmkObjectName) {
/*  62 */     int vTableId = 5;
/*     */     
/*  64 */     return (WinNT.HRESULT)_invokeNativeObject(5, new Object[] { getPointer(), pmkObjectName }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetObject(Pointer pmkObjectName, PointerByReference ppunkObject) {
/*  72 */     int vTableId = 6;
/*     */     
/*  74 */     return (WinNT.HRESULT)_invokeNativeObject(6, new Object[] { getPointer(), pmkObjectName, ppunkObject }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT NoteChangeTime(WinDef.DWORD dwRegister, WinBase.FILETIME pfiletime) {
/*  82 */     int vTableId = 7;
/*     */     
/*  84 */     return (WinNT.HRESULT)_invokeNativeObject(7, new Object[] { getPointer(), dwRegister, pfiletime }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetTimeOfLastChange(Pointer pmkObjectName, WinBase.FILETIME.ByReference pfiletime) {
/*  92 */     int vTableId = 8;
/*     */     
/*  94 */     return (WinNT.HRESULT)_invokeNativeObject(8, new Object[] { getPointer(), pmkObjectName, pfiletime }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT EnumRunning(PointerByReference ppenumMoniker) {
/* 102 */     int vTableId = 9;
/*     */     
/* 104 */     return (WinNT.HRESULT)_invokeNativeObject(9, new Object[] { getPointer(), ppenumMoniker }, WinNT.HRESULT.class);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\RunningObjectTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */