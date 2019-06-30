/*     */ package com.sun.jna.platform.win32;
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
/*     */ public abstract class W32Errors
/*     */   implements WinError
/*     */ {
/*  30 */   public static final boolean SUCCEEDED(int hr) { return (hr >= 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  40 */   public static final boolean FAILED(int hr) { return (hr < 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public static final boolean SUCCEEDED(WinNT.HRESULT hr) { return (hr == null || SUCCEEDED(hr.intValue())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public static final boolean FAILED(WinNT.HRESULT hr) { return (hr != null && FAILED(hr.intValue())); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  70 */   public static final int HRESULT_CODE(int hr) { return hr & 0xFFFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  80 */   public static final int SCODE_CODE(int sc) { return sc & 0xFFFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   public static final int HRESULT_FACILITY(int hr) { return hr >>= 16 & 0x1FFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static final int SCODE_FACILITY(short sc) { return (sc = (short)(sc >> 16)) & 0x1FFF; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 110 */   public static short HRESULT_SEVERITY(int hr) { return (short)(hr >>= 31 & true); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public static short SCODE_SEVERITY(short sc) { return (short)((sc = (short)(sc >> 31)) & true); }
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
/* 132 */   public static int MAKE_HRESULT(short sev, short fac, short code) { return sev << 31 | fac << 16 | code; }
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
/* 144 */   public static final int MAKE_SCODE(short sev, short fac, short code) { return sev << 31 | fac << 16 | code; }
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
/*     */   public static final WinNT.HRESULT HRESULT_FROM_WIN32(int x) {
/* 156 */     int f = 7;
/* 157 */     return new WinNT.HRESULT((x <= 0) ? x : (x & 0xFFFF | f <<= 16 | 0x80000000));
/*     */   }
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
/*     */   public static final int FILTER_HRESULT_FROM_FLT_NTSTATUS(int x) {
/* 170 */     int f = 31;
/* 171 */     return x & 0x8000FFFF | f <<= 16;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\W32Errors.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */