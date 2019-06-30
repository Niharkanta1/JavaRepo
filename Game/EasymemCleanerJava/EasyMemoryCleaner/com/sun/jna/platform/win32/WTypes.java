/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.PointerType;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.ptr.ByReference;
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
/*     */ public interface WTypes
/*     */ {
/*     */   public static final int CLSCTX_INPROC_SERVER = 1;
/*     */   public static final int CLSCTX_INPROC_HANDLER = 2;
/*     */   public static final int CLSCTX_LOCAL_SERVER = 4;
/*     */   public static final int CLSCTX_INPROC_SERVER16 = 8;
/*     */   public static final int CLSCTX_REMOTE_SERVER = 16;
/*     */   public static final int CLSCTX_INPROC_HANDLER16 = 32;
/*     */   public static final int CLSCTX_RESERVED1 = 64;
/*     */   public static final int CLSCTX_RESERVED2 = 128;
/*     */   public static final int CLSCTX_RESERVED3 = 256;
/*     */   public static final int CLSCTX_RESERVED4 = 512;
/*     */   public static final int CLSCTX_NO_CODE_DOWNLOAD = 1024;
/*     */   public static final int CLSCTX_RESERVED5 = 2048;
/*     */   public static final int CLSCTX_NO_CUSTOM_MARSHAL = 4096;
/*     */   public static final int CLSCTX_ENABLE_CODE_DOWNLOAD = 8192;
/*     */   public static final int CLSCTX_NO_FAILURE_LOG = 16384;
/*     */   public static final int CLSCTX_DISABLE_AAA = 32768;
/*     */   public static final int CLSCTX_ENABLE_AAA = 65536;
/*     */   public static final int CLSCTX_FROM_DEFAULT_CONTEXT = 131072;
/*     */   public static final int CLSCTX_ACTIVATE_32_BIT_SERVER = 262144;
/*     */   public static final int CLSCTX_ACTIVATE_64_BIT_SERVER = 524288;
/*     */   public static final int CLSCTX_ENABLE_CLOAKING = 1048576;
/*     */   public static final int CLSCTX_APPCONTAINER = 4194304;
/*     */   public static final int CLSCTX_ACTIVATE_AAA_AS_IU = 8388608;
/*     */   public static final int CLSCTX_PS_DLL = -2147483648;
/*     */   public static final int CLSCTX_SERVER = 21;
/*     */   public static final int CLSCTX_ALL = 7;
/*     */   
/*     */   public static class BSTR
/*     */     extends PointerType
/*     */   {
/*     */     public static class ByReference
/*     */       extends BSTR
/*     */       implements Structure.ByReference {}
/*     */     
/*  70 */     public BSTR() { super(new Memory(Pointer.SIZE)); }
/*     */ 
/*     */ 
/*     */     
/*  74 */     public BSTR(Pointer pointer) { super(pointer); }
/*     */ 
/*     */     
/*     */     public BSTR(String value) {
/*  78 */       super(new Memory((value.length() + 1L) * Native.WCHAR_SIZE));
/*  79 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/*  83 */     public void setValue(String value) { getPointer().setWideString(0L, value); }
/*     */ 
/*     */     
/*     */     public String getValue() {
/*  87 */       Pointer pointer = getPointer();
/*  88 */       String str = null;
/*  89 */       if (pointer != null) {
/*  90 */         str = pointer.getWideString(0L);
/*     */       }
/*  92 */       return str;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  97 */     public String toString() { return getValue(); }
/*     */   }
/*     */   
/*     */   public static class BSTRByReference
/*     */     extends ByReference
/*     */   {
/* 103 */     public BSTRByReference() { super(Pointer.SIZE); }
/*     */ 
/*     */     
/*     */     public BSTRByReference(WTypes.BSTR value) {
/* 107 */       this();
/* 108 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/* 112 */     public void setValue(WTypes.BSTR value) { getPointer().setPointer(0L, value.getPointer()); }
/*     */ 
/*     */ 
/*     */     
/* 116 */     public WTypes.BSTR getValue() { return new WTypes.BSTR(getPointer().getPointer(0L)); }
/*     */ 
/*     */ 
/*     */     
/* 120 */     public String getString() { return getValue().getValue(); }
/*     */   }
/*     */   
/*     */   public static class LPSTR
/*     */     extends PointerType
/*     */   {
/*     */     public static class ByReference
/*     */       extends WTypes.BSTR
/*     */       implements Structure.ByReference {}
/*     */     
/* 130 */     public LPSTR() { super(Pointer.NULL); }
/*     */ 
/*     */ 
/*     */     
/* 134 */     public LPSTR(Pointer pointer) { super(pointer); }
/*     */ 
/*     */     
/*     */     public LPSTR(String value) {
/* 138 */       this(new Memory((value.length() + 1L) * Native.WCHAR_SIZE));
/* 139 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/* 143 */     public void setValue(String value) { getPointer().setWideString(0L, value); }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 147 */       Pointer pointer = getPointer();
/* 148 */       String str = null;
/* 149 */       if (pointer != null) {
/* 150 */         str = pointer.getWideString(0L);
/*     */       }
/* 152 */       return str;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 157 */     public String toString() { return getValue(); }
/*     */   }
/*     */   
/*     */   public static class LPWSTR
/*     */     extends PointerType
/*     */   {
/*     */     public static class ByReference
/*     */       extends WTypes.BSTR
/*     */       implements Structure.ByReference {}
/*     */     
/* 167 */     public LPWSTR() { super(Pointer.NULL); }
/*     */ 
/*     */ 
/*     */     
/* 171 */     public LPWSTR(Pointer pointer) { super(pointer); }
/*     */ 
/*     */     
/*     */     public LPWSTR(String value) {
/* 175 */       this(new Memory((value.length() + 1L) * Native.WCHAR_SIZE));
/* 176 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/* 180 */     public void setValue(String value) { getPointer().setWideString(0L, value); }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 184 */       Pointer pointer = getPointer();
/* 185 */       String str = null;
/* 186 */       if (pointer != null) {
/* 187 */         str = pointer.getWideString(0L);
/*     */       }
/* 189 */       return str;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 194 */     public String toString() { return getValue(); }
/*     */   }
/*     */   
/*     */   public static class LPOLESTR
/*     */     extends PointerType
/*     */   {
/*     */     public static class ByReference
/*     */       extends WTypes.BSTR
/*     */       implements Structure.ByReference {}
/*     */     
/* 204 */     public LPOLESTR() { super(Pointer.NULL); }
/*     */ 
/*     */ 
/*     */     
/* 208 */     public LPOLESTR(Pointer pointer) { super(pointer); }
/*     */ 
/*     */     
/*     */     public LPOLESTR(String value) {
/* 212 */       super(new Memory((value.length() + 1L) * Native.WCHAR_SIZE));
/* 213 */       setValue(value);
/*     */     }
/*     */ 
/*     */     
/* 217 */     public void setValue(String value) { getPointer().setWideString(0L, value); }
/*     */ 
/*     */     
/*     */     public String getValue() {
/* 221 */       Pointer pointer = getPointer();
/* 222 */       String str = null;
/* 223 */       if (pointer != null) {
/* 224 */         str = pointer.getWideString(0L);
/*     */       }
/* 226 */       return str;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 231 */     public String toString() { return getValue(); }
/*     */   }
/*     */   
/*     */   public static class VARTYPE
/*     */     extends WinDef.USHORT
/*     */   {
/* 237 */     public VARTYPE() { this(0); }
/*     */ 
/*     */ 
/*     */     
/* 241 */     public VARTYPE(int value) { super(value); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WTypes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */