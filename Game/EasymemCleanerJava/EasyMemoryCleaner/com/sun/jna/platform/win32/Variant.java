/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.Union;
/*     */ import com.sun.jna.platform.win32.COM.Dispatch;
/*     */ import com.sun.jna.platform.win32.COM.IDispatch;
/*     */ import com.sun.jna.platform.win32.COM.Unknown;
/*     */ import com.sun.jna.ptr.ByteByReference;
/*     */ import com.sun.jna.ptr.DoubleByReference;
/*     */ import com.sun.jna.ptr.FloatByReference;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.ShortByReference;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.List;
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
/*     */ public interface Variant
/*     */ {
/*     */   public static final int VT_EMPTY = 0;
/*     */   public static final int VT_NULL = 1;
/*     */   public static final int VT_I2 = 2;
/*     */   public static final int VT_I4 = 3;
/*     */   public static final int VT_R4 = 4;
/*     */   public static final int VT_R8 = 5;
/*     */   public static final int VT_CY = 6;
/*     */   public static final int VT_DATE = 7;
/*     */   public static final int VT_BSTR = 8;
/*     */   public static final int VT_DISPATCH = 9;
/*     */   public static final int VT_ERROR = 10;
/*     */   public static final int VT_BOOL = 11;
/*     */   public static final int VT_VARIANT = 12;
/*     */   public static final int VT_UNKNOWN = 13;
/*     */   public static final int VT_DECIMAL = 14;
/*     */   public static final int VT_I1 = 16;
/*     */   public static final int VT_UI1 = 17;
/*     */   public static final int VT_UI2 = 18;
/*     */   public static final int VT_UI4 = 19;
/*     */   public static final int VT_I8 = 20;
/*     */   public static final int VT_UI8 = 21;
/*     */   public static final int VT_INT = 22;
/*     */   public static final int VT_UINT = 23;
/*     */   public static final int VT_VOID = 24;
/*     */   public static final int VT_HRESULT = 25;
/*     */   public static final int VT_PTR = 26;
/*     */   public static final int VT_SAFEARRAY = 27;
/*     */   public static final int VT_CARRAY = 28;
/*     */   public static final int VT_USERDEFINED = 29;
/*     */   public static final int VT_LPSTR = 30;
/*     */   public static final int VT_LPWSTR = 31;
/*     */   public static final int VT_RECORD = 36;
/*     */   public static final int VT_INT_PTR = 37;
/*     */   public static final int VT_UINT_PTR = 38;
/*     */   public static final int VT_FILETIME = 64;
/*     */   public static final int VT_BLOB = 65;
/*     */   public static final int VT_STREAM = 66;
/*     */   public static final int VT_STORAGE = 67;
/*     */   public static final int VT_STREAMED_OBJECT = 68;
/*     */   public static final int VT_STORED_OBJECT = 69;
/*     */   public static final int VT_BLOB_OBJECT = 70;
/*     */   public static final int VT_CF = 71;
/*     */   public static final int VT_CLSID = 72;
/*     */   public static final int VT_VERSIONED_STREAM = 73;
/*     */   public static final int VT_BSTR_BLOB = 4095;
/*     */   public static final int VT_VECTOR = 4096;
/*     */   public static final int VT_ARRAY = 8192;
/*     */   public static final int VT_BYREF = 16384;
/*     */   public static final int VT_RESERVED = 32768;
/*     */   public static final int VT_ILLEGAL = 65535;
/*     */   public static final int VT_ILLEGALMASKED = 4095;
/*     */   public static final int VT_TYPEMASK = 4095;
/* 106 */   public static final OaIdl.VARIANT_BOOL VARIANT_TRUE = new OaIdl.VARIANT_BOOL(65535L);
/* 107 */   public static final OaIdl.VARIANT_BOOL VARIANT_FALSE = new OaIdl.VARIANT_BOOL(0L);
/*     */   public static final long COM_DAYS_ADJUSTMENT = 25569L;
/*     */   public static final long MICRO_SECONDS_PER_DAY = 86400000L;
/*     */   
/*     */   public static class VARIANT
/*     */     extends Union
/*     */   {
/*     */     public _VARIANT _variant;
/*     */     public OaIdl.DECIMAL decVal;
/*     */     
/*     */     public static class ByReference
/*     */       extends VARIANT
/*     */       implements Structure.ByReference {
/* 120 */       public ByReference(Variant.VARIANT variant) { setValue(variant.getVarType(), variant.getValue()); }
/*     */ 
/*     */ 
/*     */       
/* 124 */       public ByReference(Pointer variant) { super(variant); }
/*     */ 
/*     */       
/*     */       public ByReference() {}
/*     */     }
/*     */ 
/*     */     
/*     */     public static class ByValue
/*     */       extends VARIANT
/*     */       implements Structure.ByValue
/*     */     {
/* 135 */       public ByValue(Variant.VARIANT variant) { setValue(variant.getVarType(), variant.getValue()); }
/*     */ 
/*     */ 
/*     */       
/* 139 */       public ByValue(Pointer variant) { super(variant); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public ByValue() {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VARIANT() {
/* 152 */       setType("_variant");
/* 153 */       read();
/*     */     }
/*     */     
/*     */     public VARIANT(Pointer pointer) {
/* 157 */       super(pointer);
/* 158 */       setType("_variant");
/* 159 */       read();
/*     */     }
/*     */     
/*     */     public VARIANT(WTypes.BSTR value) {
/* 163 */       this();
/* 164 */       setValue(8, value);
/*     */     }
/*     */     
/*     */     public VARIANT(WTypes.BSTRByReference value) {
/* 168 */       this();
/* 169 */       setValue(16392, value);
/*     */     }
/*     */     
/*     */     public VARIANT(OaIdl.VARIANT_BOOL value) {
/* 173 */       this();
/* 174 */       setValue(11, new WinDef.BOOL(value.intValue()));
/*     */     }
/*     */     
/*     */     public VARIANT(WinDef.BOOL value) {
/* 178 */       this();
/* 179 */       setValue(11, value);
/*     */     }
/*     */     
/*     */     public VARIANT(WinDef.LONG value) {
/* 183 */       this();
/* 184 */       setValue(3, value);
/*     */     }
/*     */     
/*     */     public VARIANT(WinDef.SHORT value) {
/* 188 */       this();
/* 189 */       setValue(2, value);
/*     */     }
/*     */     
/*     */     public VARIANT(OaIdl.DATE value) {
/* 193 */       this();
/* 194 */       setValue(7, value);
/*     */     }
/*     */ 
/*     */     
/* 198 */     public VARIANT(byte value) { this(new WinDef.BYTE(value)); }
/*     */ 
/*     */     
/*     */     public VARIANT(WinDef.BYTE value) {
/* 202 */       this();
/* 203 */       setValue(17, value);
/*     */     }
/*     */ 
/*     */     
/* 207 */     public VARIANT(char value) { this(new WinDef.CHAR(value)); }
/*     */ 
/*     */     
/*     */     public VARIANT(WinDef.CHAR value) {
/* 211 */       this();
/* 212 */       setValue(16, value);
/*     */     }
/*     */     
/*     */     public VARIANT(short value) {
/* 216 */       this();
/* 217 */       setValue(2, new WinDef.SHORT(value));
/*     */     }
/*     */     
/*     */     public VARIANT(int value) {
/* 221 */       this();
/* 222 */       setValue(3, new WinDef.LONG(value));
/*     */     }
/*     */     
/*     */     public VARIANT(long value) {
/* 226 */       this();
/* 227 */       setValue(20, new WinDef.LONGLONG(value));
/*     */     }
/*     */     
/*     */     public VARIANT(float value) {
/* 231 */       this();
/* 232 */       setValue(4, Float.valueOf(value));
/*     */     }
/*     */     
/*     */     public VARIANT(double value) {
/* 236 */       this();
/* 237 */       setValue(5, Double.valueOf(value));
/*     */     }
/*     */     
/*     */     public VARIANT(String value) {
/* 241 */       this();
/* 242 */       WTypes.BSTR bstrValue = OleAuto.INSTANCE.SysAllocString(value);
/* 243 */       setValue(8, bstrValue);
/*     */     }
/*     */     
/*     */     public VARIANT(boolean value) {
/* 247 */       this();
/* 248 */       if (value) {
/* 249 */         setValue(11, new WinDef.BOOL(Variant.VARIANT_TRUE.intValue()));
/*     */       } else {
/* 251 */         setValue(11, new WinDef.BOOL(Variant.VARIANT_FALSE.intValue()));
/*     */       } 
/*     */     }
/*     */     public VARIANT(IDispatch value) {
/* 255 */       this();
/* 256 */       setValue(9, value);
/*     */     }
/*     */     
/*     */     public VARIANT(Date value) {
/* 260 */       this();
/* 261 */       OaIdl.DATE date = fromJavaDate(value);
/* 262 */       setValue(7, date);
/*     */     }
/*     */     
/*     */     public WTypes.VARTYPE getVarType() {
/* 266 */       read();
/* 267 */       return this._variant.vt;
/*     */     }
/*     */ 
/*     */     
/* 271 */     public void setVarType(short vt) { this._variant.vt = new WTypes.VARTYPE(vt); }
/*     */ 
/*     */ 
/*     */     
/* 275 */     public void setValue(int vt, Object value) { setValue(new WTypes.VARTYPE(vt), value); }
/*     */ 
/*     */     
/*     */     public void setValue(WTypes.VARTYPE vt, Object value) {
/* 279 */       switch (vt.intValue()) {
/*     */         case 17:
/* 281 */           this._variant.__variant.writeField("bVal", value);
/*     */           break;
/*     */         case 2:
/* 284 */           this._variant.__variant.writeField("iVal", value);
/*     */           break;
/*     */         case 3:
/* 287 */           this._variant.__variant.writeField("lVal", value);
/*     */           break;
/*     */         case 20:
/* 290 */           this._variant.__variant.writeField("llVal", value);
/*     */           break;
/*     */         case 4:
/* 293 */           this._variant.__variant.writeField("fltVal", value);
/*     */           break;
/*     */         case 5:
/* 296 */           this._variant.__variant.writeField("dblVal", value);
/*     */           break;
/*     */         case 11:
/* 299 */           this._variant.__variant.writeField("boolVal", value);
/*     */           break;
/*     */         case 10:
/* 302 */           this._variant.__variant.writeField("scode", value);
/*     */           break;
/*     */         case 6:
/* 305 */           this._variant.__variant.writeField("cyVal", value);
/*     */           break;
/*     */         case 7:
/* 308 */           this._variant.__variant.writeField("date", value);
/*     */           break;
/*     */         case 8:
/* 311 */           this._variant.__variant.writeField("bstrVal", value);
/*     */           break;
/*     */         case 13:
/* 314 */           this._variant.__variant.writeField("punkVal", value);
/*     */           break;
/*     */         case 9:
/* 317 */           this._variant.__variant.writeField("pdispVal", value);
/*     */           break;
/*     */         case 27:
/* 320 */           this._variant.__variant.writeField("parray", value);
/*     */           break;
/*     */         case 8192:
/* 323 */           this._variant.__variant.writeField("parray", value);
/*     */           break;
/*     */         case 16401:
/* 326 */           this._variant.__variant.writeField("pbVal", value);
/*     */           break;
/*     */         case 16386:
/* 329 */           this._variant.__variant.writeField("piVal", value);
/*     */           break;
/*     */         case 16387:
/* 332 */           this._variant.__variant.writeField("plVal", value);
/*     */           break;
/*     */         case 16404:
/* 335 */           this._variant.__variant.writeField("pllVal", value);
/*     */           break;
/*     */         case 16388:
/* 338 */           this._variant.__variant.writeField("pfltVal", value);
/*     */           break;
/*     */         case 16389:
/* 341 */           this._variant.__variant.writeField("pdblVal", value);
/*     */           break;
/*     */         case 16395:
/* 344 */           this._variant.__variant.writeField("pboolVal", value);
/*     */           break;
/*     */         case 16394:
/* 347 */           this._variant.__variant.writeField("pscode", value);
/*     */           break;
/*     */         case 16390:
/* 350 */           this._variant.__variant.writeField("pcyVal", value);
/*     */           break;
/*     */         case 16391:
/* 353 */           this._variant.__variant.writeField("pdate", value);
/*     */           break;
/*     */         case 16392:
/* 356 */           this._variant.__variant.writeField("pbstrVal", value);
/*     */           break;
/*     */         case 16397:
/* 359 */           this._variant.__variant.writeField("ppunkVal", value);
/*     */           break;
/*     */         case 16393:
/* 362 */           this._variant.__variant.writeField("ppdispVal", value);
/*     */           break;
/*     */         case 24576:
/* 365 */           this._variant.__variant.writeField("pparray", value);
/*     */           break;
/*     */         case 16396:
/* 368 */           this._variant.__variant.writeField("pvarVal", value);
/*     */           break;
/*     */         case 16384:
/* 371 */           this._variant.__variant.writeField("byref", value);
/*     */           break;
/*     */         case 16:
/* 374 */           this._variant.__variant.writeField("cVal", value);
/*     */           break;
/*     */         case 18:
/* 377 */           this._variant.__variant.writeField("uiVal", value);
/*     */           break;
/*     */         case 19:
/* 380 */           this._variant.__variant.writeField("ulVal", value);
/*     */           break;
/*     */         case 21:
/* 383 */           this._variant.__variant.writeField("ullVal", value);
/*     */           break;
/*     */         case 22:
/* 386 */           this._variant.__variant.writeField("intVal", value);
/*     */           break;
/*     */         case 23:
/* 389 */           this._variant.__variant.writeField("uintVal", value);
/*     */           break;
/*     */         case 16398:
/* 392 */           this._variant.__variant.writeField("pdecVal", value);
/*     */           break;
/*     */         case 16400:
/* 395 */           this._variant.__variant.writeField("pcVal", value);
/*     */           break;
/*     */         case 16402:
/* 398 */           this._variant.__variant.writeField("puiVal", value);
/*     */           break;
/*     */         case 16403:
/* 401 */           this._variant.__variant.writeField("pulVal", value);
/*     */           break;
/*     */         case 16405:
/* 404 */           this._variant.__variant.writeField("pullVal", value);
/*     */           break;
/*     */         case 16406:
/* 407 */           this._variant.__variant.writeField("pintVal", value);
/*     */           break;
/*     */         case 16407:
/* 410 */           this._variant.__variant.writeField("puintVal", value);
/*     */           break;
/*     */         case 36:
/* 413 */           this._variant.__variant.writeField("pvRecord", value);
/*     */           break;
/*     */       } 
/*     */       
/* 417 */       this._variant.writeField("vt", vt);
/* 418 */       write();
/*     */     }
/*     */     
/*     */     public Object getValue() {
/* 422 */       read();
/* 423 */       switch (getVarType().intValue()) {
/*     */         case 2:
/* 425 */           return this._variant.__variant.readField("iVal");
/*     */         case 3:
/* 427 */           return this._variant.__variant.readField("lVal");
/*     */         case 20:
/* 429 */           return this._variant.__variant.readField("llVal");
/*     */         case 4:
/* 431 */           return this._variant.__variant.readField("fltVal");
/*     */         case 5:
/* 433 */           return this._variant.__variant.readField("dblVal");
/*     */         case 11:
/* 435 */           return this._variant.__variant.readField("boolVal");
/*     */         case 10:
/* 437 */           return this._variant.__variant.readField("scode");
/*     */         case 6:
/* 439 */           return this._variant.__variant.readField("cyVal");
/*     */         case 7:
/* 441 */           return this._variant.__variant.readField("date");
/*     */         case 8:
/* 443 */           return this._variant.__variant.readField("bstrVal");
/*     */         case 13:
/* 445 */           return this._variant.__variant.readField("punkVal");
/*     */         case 9:
/* 447 */           return this._variant.__variant.readField("pdispVal");
/*     */         case 27:
/* 449 */           return this._variant.__variant.readField("parray");
/*     */         case 8192:
/* 451 */           return this._variant.__variant.readField("parray");
/*     */         case 16401:
/* 453 */           return this._variant.__variant.readField("pbVal");
/*     */         case 16386:
/* 455 */           return this._variant.__variant.readField("piVal");
/*     */         case 16387:
/* 457 */           return this._variant.__variant.readField("plVal");
/*     */         case 16404:
/* 459 */           return this._variant.__variant.readField("pllVal");
/*     */         case 16388:
/* 461 */           return this._variant.__variant.readField("pfltVal");
/*     */         case 16389:
/* 463 */           return this._variant.__variant.readField("pdblVal");
/*     */         case 16395:
/* 465 */           return this._variant.__variant.readField("pboolVal");
/*     */         case 16394:
/* 467 */           return this._variant.__variant.readField("pscode");
/*     */         case 16390:
/* 469 */           return this._variant.__variant.readField("pcyVal");
/*     */         case 16391:
/* 471 */           return this._variant.__variant.readField("pdate");
/*     */         case 16392:
/* 473 */           return this._variant.__variant.readField("pbstrVal");
/*     */         case 16397:
/* 475 */           return this._variant.__variant.readField("ppunkVal");
/*     */         case 16393:
/* 477 */           return this._variant.__variant.readField("ppdispVal");
/*     */         case 24576:
/* 479 */           return this._variant.__variant.readField("pparray");
/*     */         case 16396:
/* 481 */           return this._variant.__variant.readField("pvarVal");
/*     */         case 16384:
/* 483 */           return this._variant.__variant.readField("byref");
/*     */         case 16:
/* 485 */           return this._variant.__variant.readField("cVal");
/*     */         case 18:
/* 487 */           return this._variant.__variant.readField("uiVal");
/*     */         case 19:
/* 489 */           return this._variant.__variant.readField("ulVal");
/*     */         case 21:
/* 491 */           return this._variant.__variant.readField("ullVal");
/*     */         case 22:
/* 493 */           return this._variant.__variant.readField("intVal");
/*     */         case 23:
/* 495 */           return this._variant.__variant.readField("uintVal");
/*     */         case 16398:
/* 497 */           return this._variant.__variant.readField("pdecVal");
/*     */         case 16400:
/* 499 */           return this._variant.__variant.readField("pcVal");
/*     */         case 16402:
/* 501 */           return this._variant.__variant.readField("puiVal");
/*     */         case 16403:
/* 503 */           return this._variant.__variant.readField("pulVal");
/*     */         case 16405:
/* 505 */           return this._variant.__variant.readField("pullVal");
/*     */         case 16406:
/* 507 */           return this._variant.__variant.readField("pintVal");
/*     */         case 16407:
/* 509 */           return this._variant.__variant.readField("puintVal");
/*     */         case 36:
/* 511 */           return this._variant.__variant.readField("pvRecord");
/*     */       } 
/* 513 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 518 */     public int shortValue() { return ((Short)getValue()).shortValue(); }
/*     */ 
/*     */ 
/*     */     
/* 522 */     public int intValue() { return ((Integer)getValue()).intValue(); }
/*     */ 
/*     */ 
/*     */     
/* 526 */     public long longValue() { return ((Long)getValue()).longValue(); }
/*     */ 
/*     */ 
/*     */     
/* 530 */     public float floatValue() { return ((Float)getValue()).floatValue(); }
/*     */ 
/*     */ 
/*     */     
/* 534 */     public double doubleValue() { return ((Double)getValue()).doubleValue(); }
/*     */ 
/*     */     
/*     */     public String stringValue() {
/* 538 */       WTypes.BSTR bstr = (WTypes.BSTR)getValue();
/* 539 */       return bstr.getValue();
/*     */     }
/*     */ 
/*     */     
/* 543 */     public boolean booleanValue() { return ((Boolean)getValue()).booleanValue(); }
/*     */ 
/*     */     
/*     */     public Date dateValue() {
/* 547 */       OaIdl.DATE varDate = (OaIdl.DATE)getValue();
/* 548 */       return toJavaDate(varDate);
/*     */     }
/*     */ 
/*     */     
/*     */     protected Date toJavaDate(OaIdl.DATE varDate) {
/* 553 */       double doubleDate = varDate.date;
/* 554 */       long longDate = (long)doubleDate;
/*     */       
/* 556 */       double doubleTime = doubleDate - longDate;
/* 557 */       long longTime = (long)doubleTime * 86400000L;
/*     */       
/* 559 */       return new Date((longDate - 25569L) * 86400000L + longTime);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     protected OaIdl.DATE fromJavaDate(Date javaDate) {
/* 565 */       long longTime = javaDate.getTime() % 86400000L;
/* 566 */       long longDate = (javaDate.getTime() - longTime) / 86400000L + 25569L;
/*     */ 
/*     */       
/* 569 */       float floatTime = (float)longTime / 8.64E7F;
/*     */       
/* 571 */       float floatDateTime = floatTime + (float)longDate;
/* 572 */       return new OaIdl.DATE(floatDateTime);
/*     */     }
/*     */     
/*     */     public static class _VARIANT
/*     */       extends Structure
/*     */     {
/*     */       public WTypes.VARTYPE vt;
/*     */       public short wReserved1;
/*     */       public short wReserved2;
/*     */       public short wReserved3;
/*     */       public __VARIANT __variant;
/*     */       
/*     */       public _VARIANT() {}
/*     */       
/*     */       public _VARIANT(Pointer pointer) {
/* 587 */         super(pointer);
/* 588 */         read();
/*     */       }
/*     */ 
/*     */       
/*     */       public static class __VARIANT
/*     */         extends Union
/*     */       {
/*     */         public WinDef.LONGLONG llVal;
/*     */         
/*     */         public WinDef.LONG lVal;
/*     */         
/*     */         public WinDef.BYTE bVal;
/*     */         
/*     */         public WinDef.SHORT iVal;
/*     */         
/*     */         public Float fltVal;
/*     */         
/*     */         public Double dblVal;
/*     */         
/*     */         public WinDef.BOOL boolVal;
/*     */         
/*     */         public WinDef.SCODE scode;
/*     */         
/*     */         public OaIdl.CURRENCY cyVal;
/*     */         
/*     */         public OaIdl.DATE date;
/*     */         
/*     */         public WTypes.BSTR bstrVal;
/*     */         
/*     */         public Unknown punkVal;
/*     */         
/*     */         public Dispatch pdispVal;
/*     */         
/*     */         public OaIdl.SAFEARRAY.ByReference parray;
/*     */         
/*     */         public ByteByReference pbVal;
/*     */         
/*     */         public ShortByReference piVal;
/*     */         
/*     */         public WinDef.LONGByReference plVal;
/*     */         
/*     */         public WinDef.LONGLONGByReference pllVal;
/*     */         
/*     */         public FloatByReference pfltVal;
/*     */         
/*     */         public DoubleByReference pdblVal;
/*     */         
/*     */         public OaIdl.VARIANT_BOOLByReference pboolVal;
/*     */         
/*     */         public OaIdl._VARIANT_BOOLByReference pbool;
/*     */         
/*     */         public WinDef.SCODEByReference pscode;
/*     */         
/*     */         public OaIdl.CURRENCY.ByReference pcyVal;
/*     */         
/*     */         public OaIdl.DATE.ByReference pdate;
/*     */         
/*     */         public WTypes.BSTR.ByReference pbstrVal;
/*     */         
/*     */         public Unknown.ByReference ppunkVal;
/*     */         
/*     */         public Dispatch.ByReference ppdispVal;
/*     */         
/*     */         public OaIdl.SAFEARRAY.ByReference pparray;
/*     */         
/*     */         public Variant.VARIANT.ByReference pvarVal;
/*     */         
/*     */         public WinDef.PVOID byref;
/*     */         
/*     */         public WinDef.CHAR cVal;
/*     */         
/*     */         public WinDef.USHORT uiVal;
/*     */         
/*     */         public WinDef.ULONG ulVal;
/*     */         
/*     */         public WinDef.ULONGLONG ullVal;
/*     */         
/*     */         public Integer intVal;
/*     */         
/*     */         public WinDef.UINT uintVal;
/*     */         
/*     */         public OaIdl.DECIMAL.ByReference pdecVal;
/*     */         
/*     */         public WinDef.CHARByReference pcVal;
/*     */         
/*     */         public WinDef.USHORTByReference puiVal;
/*     */         
/*     */         public WinDef.ULONGByReference pulVal;
/*     */         
/*     */         public WinDef.ULONGLONGByReference pullVal;
/*     */         
/*     */         public IntByReference pintVal;
/*     */         
/*     */         public WinDef.UINTByReference puintVal;
/*     */         public BRECORD pvRecord;
/*     */         
/*     */         public static class BRECORD
/*     */           extends Structure
/*     */         {
/*     */           public WinDef.PVOID pvRecord;
/*     */           public Pointer pRecInfo;
/*     */           
/*     */           public static class ByReference
/*     */             extends BRECORD
/*     */             implements Structure.ByReference {}
/*     */           
/*     */           public BRECORD() {}
/*     */           
/* 696 */           public BRECORD(Pointer pointer) { super(pointer); }
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 701 */           protected List getFieldOrder() { return Arrays.asList(new String[] { "pvRecord", "pRecInfo" }); }
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 708 */         public __VARIANT() { read(); }
/*     */ 
/*     */         
/*     */         public __VARIANT(Pointer pointer) {
/* 712 */           super(pointer);
/* 713 */           read();
/*     */         }
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 719 */       protected List getFieldOrder() { return Arrays.asList(new String[] { "vt", "wReserved1", "wReserved2", "wReserved3", "__variant" }); }
/*     */     }
/*     */     public static class __VARIANT extends Union { public WinDef.LONGLONG llVal; public WinDef.LONG lVal; public WinDef.BYTE bVal; public WinDef.SHORT iVal; public Float fltVal; public Double dblVal; public WinDef.BOOL boolVal; public WinDef.SCODE scode; public OaIdl.CURRENCY cyVal; public OaIdl.DATE date; public WTypes.BSTR bstrVal; public Unknown punkVal; public Dispatch pdispVal; public OaIdl.SAFEARRAY.ByReference parray; public ByteByReference pbVal; public ShortByReference piVal; public WinDef.LONGByReference plVal; public WinDef.LONGLONGByReference pllVal; public FloatByReference pfltVal; public DoubleByReference pdblVal; public OaIdl.VARIANT_BOOLByReference pboolVal; public OaIdl._VARIANT_BOOLByReference pbool; public WinDef.SCODEByReference pscode; public OaIdl.CURRENCY.ByReference pcyVal; public OaIdl.DATE.ByReference pdate; public WTypes.BSTR.ByReference pbstrVal; public Unknown.ByReference ppunkVal; public Dispatch.ByReference ppdispVal; public OaIdl.SAFEARRAY.ByReference pparray; public Variant.VARIANT.ByReference pvarVal; public WinDef.PVOID byref; public WinDef.CHAR cVal; public WinDef.USHORT uiVal; public WinDef.ULONG ulVal; public WinDef.ULONGLONG ullVal; public Integer intVal; public WinDef.UINT uintVal; public OaIdl.DECIMAL.ByReference pdecVal; public WinDef.CHARByReference pcVal; public WinDef.USHORTByReference puiVal; public WinDef.ULONGByReference pulVal; public WinDef.ULONGLONGByReference pullVal; public IntByReference pintVal; public WinDef.UINTByReference puintVal; public BRECORD pvRecord;
/*     */       public static class BRECORD extends Structure { public WinDef.PVOID pvRecord; public Pointer pRecInfo;
/*     */         public static class ByReference extends BRECORD implements Structure.ByReference {}
/*     */         public BRECORD() {}
/*     */         public BRECORD(Pointer pointer) { super(pointer); }
/*     */         protected List getFieldOrder() { return Arrays.asList(new String[] { "pvRecord", "pRecInfo" }); } }
/*     */       public __VARIANT() { read(); }
/*     */       public __VARIANT(Pointer pointer) {
/*     */         super(pointer);
/*     */         read();
/*     */       } }
/*     */   }
/* 733 */   public static class VariantArg extends Structure { public static class ByReference extends VariantArg implements Structure.ByReference { public ByReference(VARIANT[] variantArg) { this.variantArg = variantArg; }
/*     */       
/*     */       public ByReference() {} }
/*     */     
/* 737 */     public Variant.VARIANT[] variantArg = new Variant.VARIANT[1];
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public VariantArg() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 747 */     public VariantArg(Pointer pointer) { super(pointer); }
/*     */ 
/*     */ 
/*     */     
/* 751 */     public VariantArg(VARIANT[] variantArg) { this.variantArg = variantArg; }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 756 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "variantArg" }); }
/*     */ 
/*     */     
/*     */     public void setArraySize(int size) {
/* 760 */       this.variantArg = new Variant.VARIANT[size];
/* 761 */       read();
/*     */     } }
/*     */ 
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Variant.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */