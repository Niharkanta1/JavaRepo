/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.IntegerType;
/*      */ import com.sun.jna.NativeLong;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.Structure;
/*      */ import com.sun.jna.Union;
/*      */ import com.sun.jna.platform.win32.COM.TypeComp;
/*      */ import com.sun.jna.ptr.ByReference;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface OaIdl
/*      */ {
/*      */   public static class EXCEPINFO
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.WORD wCode;
/*      */     public WinDef.WORD wReserved;
/*      */     public WTypes.BSTR bstrSource;
/*      */     public WTypes.BSTR bstrDescription;
/*      */     public WTypes.BSTR bstrHelpFile;
/*      */     public WinDef.DWORD dwHelpContext;
/*      */     public WinDef.PVOID pvReserved;
/*      */     public ByReference pfnDeferredFillIn;
/*      */     public WinDef.SCODE scode;
/*      */     
/*      */     public static class ByReference
/*      */       extends EXCEPINFO
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public EXCEPINFO() {}
/*      */     
/*   95 */     public EXCEPINFO(Pointer p) { super(p); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected List getFieldOrder() {
/*  106 */       return Arrays.asList(new String[] { "wCode", "wReserved", "bstrSource", "bstrDescription", "bstrHelpFile", "dwHelpContext", "pvReserved", "pfnDeferredFillIn", "scode" });
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class VARIANT_BOOL
/*      */     extends IntegerType
/*      */   {
/*      */     public static final int SIZE = 2;
/*      */     
/*  116 */     public VARIANT_BOOL() { this(0L); }
/*      */ 
/*      */ 
/*      */     
/*  120 */     public VARIANT_BOOL(long value) { super(2, value); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class _VARIANT_BOOL
/*      */     extends VARIANT_BOOL
/*      */   {
/*  127 */     public _VARIANT_BOOL() { this(0L); }
/*      */ 
/*      */ 
/*      */     
/*  131 */     public _VARIANT_BOOL(long value) { super(value); }
/*      */   }
/*      */   
/*      */   public static class VARIANT_BOOLByReference
/*      */     extends ByReference
/*      */   {
/*  137 */     public VARIANT_BOOLByReference() { this(new OaIdl.VARIANT_BOOL(0L)); }
/*      */ 
/*      */     
/*      */     public VARIANT_BOOLByReference(OaIdl.VARIANT_BOOL value) {
/*  141 */       super(2);
/*  142 */       setValue(value);
/*      */     }
/*      */ 
/*      */     
/*  146 */     public void setValue(OaIdl.VARIANT_BOOL value) { getPointer().setShort(0L, value.shortValue()); }
/*      */ 
/*      */ 
/*      */     
/*  150 */     public OaIdl.VARIANT_BOOL getValue() { return new OaIdl.VARIANT_BOOL(getPointer().getShort(0L)); }
/*      */   }
/*      */   
/*      */   public static class _VARIANT_BOOLByReference
/*      */     extends ByReference
/*      */   {
/*  156 */     public _VARIANT_BOOLByReference() { this(new OaIdl.VARIANT_BOOL(0L)); }
/*      */ 
/*      */     
/*      */     public _VARIANT_BOOLByReference(OaIdl.VARIANT_BOOL value) {
/*  160 */       super(2);
/*  161 */       setValue(value);
/*      */     }
/*      */ 
/*      */     
/*  165 */     public void setValue(OaIdl.VARIANT_BOOL value) { getPointer().setShort(0L, value.shortValue()); }
/*      */ 
/*      */ 
/*      */     
/*  169 */     public OaIdl.VARIANT_BOOL getValue() { return new OaIdl.VARIANT_BOOL(getPointer().getShort(0L)); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class DATE
/*      */     extends Structure
/*      */   {
/*      */     public double date;
/*      */     
/*      */     public static class ByReference
/*      */       extends DATE
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public DATE() {}
/*      */     
/*  184 */     public DATE(double date) { this.date = date; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  194 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "date" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DISPID
/*      */     extends WinDef.LONG
/*      */   {
/*  203 */     public DISPID() { this(0); }
/*      */ 
/*      */ 
/*      */     
/*  207 */     public DISPID(int value) { super(value); }
/*      */   }
/*      */   
/*      */   public static class DISPIDByReference
/*      */     extends ByReference
/*      */   {
/*  213 */     public DISPIDByReference() { this(new OaIdl.DISPID(false)); }
/*      */ 
/*      */     
/*      */     public DISPIDByReference(OaIdl.DISPID value) {
/*  217 */       super(OaIdl.DISPID.SIZE);
/*  218 */       setValue(value);
/*      */     }
/*      */ 
/*      */     
/*  222 */     public void setValue(OaIdl.DISPID value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */     
/*  226 */     public OaIdl.DISPID getValue() { return new OaIdl.DISPID(getPointer().getInt(0L)); }
/*      */   }
/*      */   
/*      */   public static class MEMBERID
/*      */     extends DISPID
/*      */   {
/*  232 */     public MEMBERID() { this(0); }
/*      */ 
/*      */ 
/*      */     
/*  236 */     public MEMBERID(int value) { super(value); }
/*      */   }
/*      */   
/*      */   public static class MEMBERIDByReference
/*      */     extends ByReference
/*      */   {
/*  242 */     public MEMBERIDByReference() { this(new OaIdl.MEMBERID(false)); }
/*      */ 
/*      */     
/*      */     public MEMBERIDByReference(OaIdl.MEMBERID value) {
/*  246 */       super(OaIdl.MEMBERID.SIZE);
/*  247 */       setValue(value);
/*      */     }
/*      */ 
/*      */     
/*  251 */     public void setValue(OaIdl.MEMBERID value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */     
/*  255 */     public OaIdl.MEMBERID getValue() { return new OaIdl.MEMBERID(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  262 */   public static final DISPID DISPID_COLLECT = new DISPID(-8);
/*      */ 
/*      */ 
/*      */   
/*  266 */   public static final DISPID DISPID_CONSTRUCTOR = new DISPID(-6);
/*      */ 
/*      */ 
/*      */   
/*  270 */   public static final DISPID DISPID_DESTRUCTOR = new DISPID(-7);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  276 */   public static final DISPID DISPID_EVALUATE = new DISPID(-5);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  282 */   public static final DISPID DISPID_NEWENUM = new DISPID(-4);
/*      */ 
/*      */ 
/*      */   
/*  286 */   public static final DISPID DISPID_PROPERTYPUT = new DISPID(-3);
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  291 */   public static final DISPID DISPID_UNKNOWN = new DISPID(-1);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  297 */   public static final DISPID DISPID_VALUE = new DISPID(false);
/*      */   
/*  299 */   public static final MEMBERID MEMBERID_NIL = new MEMBERID(DISPID_UNKNOWN
/*  300 */       .intValue());
/*      */   
/*      */   public static final int FADF_AUTO = 1;
/*      */   
/*      */   public static final int FADF_STATIC = 2;
/*      */   
/*      */   public static final int FADF_EMBEDDED = 4;
/*      */   
/*      */   public static final int FADF_FIXEDSIZE = 16;
/*      */   
/*      */   public static final int FADF_RECORD = 32;
/*      */   
/*      */   public static final int FADF_HAVEIID = 64;
/*      */   
/*      */   public static final int FADF_HAVEVARTYPE = 128;
/*      */   
/*      */   public static final int FADF_BSTR = 256;
/*      */   
/*      */   public static final int FADF_UNKNOWN = 512;
/*      */   
/*      */   public static final int FADF_DISPATCH = 1024;
/*      */   
/*      */   public static final int FADF_VARIANT = 2048;
/*      */   
/*      */   public static final int FADF_RESERVED = 61448;
/*      */ 
/*      */   
/*      */   public static class TYPEKIND
/*      */     extends Structure
/*      */   {
/*      */     public int value;
/*      */     
/*      */     public static final int TKIND_ENUM = 0;
/*      */     
/*      */     public static final int TKIND_RECORD = 1;
/*      */     
/*      */     public static final int TKIND_MODULE = 2;
/*      */     
/*      */     public static final int TKIND_INTERFACE = 3;
/*      */     
/*      */     public static final int TKIND_DISPATCH = 4;
/*      */     
/*      */     public static final int TKIND_COCLASS = 5;
/*      */     
/*      */     public static final int TKIND_ALIAS = 6;
/*      */     
/*      */     public static final int TKIND_UNION = 7;
/*      */     
/*      */     public static final int TKIND_MAX = 8;
/*      */     
/*      */     public static class ByReference
/*      */       extends TYPEKIND
/*      */       implements Structure.ByReference
/*      */     {
/*      */       public ByReference() {}
/*      */       
/*  356 */       public ByReference(int value) { super(value); }
/*      */ 
/*      */       
/*      */       public ByReference(OaIdl.TYPEKIND typekind) {
/*  360 */         super(typekind.getPointer());
/*  361 */         this.value = typekind.value;
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public TYPEKIND() {}
/*      */ 
/*      */ 
/*      */     
/*  371 */     public TYPEKIND(int value) { this.value = value; }
/*      */ 
/*      */     
/*      */     public TYPEKIND(Pointer pointer) {
/*  375 */       super(pointer);
/*  376 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  400 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); } }
/*      */   
/*      */   public static class DESCKIND extends Structure {
/*      */     public int value;
/*      */     public static final int DESCKIND_NONE = 0;
/*      */     public static final int DESCKIND_FUNCDESC = 1;
/*      */     public static final int DESCKIND_VARDESC = 2;
/*      */     public static final int DESCKIND_TYPECOMP = 3;
/*      */     public static final int DESCKIND_IMPLICITAPPOBJ = 4;
/*      */     public static final int DESCKIND_MAX = 5;
/*      */     
/*      */     public static class ByReference extends DESCKIND implements Structure.ByReference {}
/*      */     
/*      */     public DESCKIND() {}
/*      */     
/*  415 */     public DESCKIND(int value) { this.value = value; }
/*      */ 
/*      */     
/*      */     public DESCKIND(Pointer pointer) {
/*  419 */       super(pointer);
/*  420 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  438 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */   
/*      */   public static class SAFEARRAY
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.USHORT cDims;
/*      */     public WinDef.USHORT fFeatures;
/*      */     public WinDef.ULONG cbElements;
/*      */     public WinDef.ULONG cLocks;
/*      */     public WinDef.PVOID pvData;
/*      */     
/*      */     public static class ByReference
/*      */       extends SAFEARRAY
/*      */       implements Structure.ByReference {}
/*      */     
/*  454 */     public OaIdl.SAFEARRAYBOUND[] rgsabound = { new OaIdl.SAFEARRAYBOUND() };
/*      */ 
/*      */     
/*      */     public SAFEARRAY() {}
/*      */     
/*      */     public SAFEARRAY(Pointer pointer) {
/*  460 */       super(pointer);
/*  461 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  466 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cDims", "fFeatures", "cbElements", "cLocks", "pvData", "rgsabound" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class SAFEARRAYBOUND
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.ULONG cElements;
/*      */     
/*      */     public WinDef.LONG lLbound;
/*      */     
/*      */     public static class ByReference
/*      */       extends SAFEARRAYBOUND
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public SAFEARRAYBOUND() {}
/*      */     
/*      */     public SAFEARRAYBOUND(Pointer pointer) {
/*  484 */       super(pointer);
/*  485 */       read();
/*      */     }
/*      */     
/*      */     public SAFEARRAYBOUND(int cElements, int lLbound) {
/*  489 */       this.cElements = new WinDef.ULONG(cElements);
/*  490 */       this.lLbound = new WinDef.LONG(lLbound);
/*  491 */       write();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  496 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cElements", "lLbound" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class CURRENCY
/*      */     extends Union
/*      */   {
/*      */     public _CURRENCY currency;
/*      */     
/*      */     public WinDef.LONGLONG int64;
/*      */     
/*      */     public static class ByReference
/*      */       extends CURRENCY
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public CURRENCY() {}
/*      */     
/*      */     public CURRENCY(Pointer pointer) {
/*  514 */       super(pointer);
/*  515 */       read();
/*      */     }
/*      */     
/*      */     public static class _CURRENCY
/*      */       extends Structure
/*      */     {
/*      */       public WinDef.ULONG Lo;
/*      */       public WinDef.LONG Hi;
/*      */       
/*      */       public _CURRENCY() {}
/*      */       
/*      */       public _CURRENCY(Pointer pointer) {
/*  527 */         super(pointer);
/*  528 */         read();
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*  533 */       protected List getFieldOrder() { return Arrays.asList(new String[] { "Lo", "Hi" }); }
/*      */     }
/*      */   }
/*      */   
/*      */   public static class DECIMAL
/*      */     extends Structure {
/*      */     public short wReserved;
/*      */     public _DECIMAL1 decimal1;
/*      */     public NativeLong Hi32;
/*      */     public _DECIMAL2 decimal2;
/*      */     
/*      */     public static class ByReference
/*      */       extends DECIMAL implements Structure.ByReference {}
/*      */     
/*      */     public DECIMAL() {}
/*      */     
/*  549 */     public DECIMAL(Pointer pointer) { super(pointer); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class _DECIMAL1
/*      */       extends Union
/*      */     {
/*      */       public WinDef.USHORT signscale;
/*      */ 
/*      */       
/*      */       public _DECIMAL1_DECIMAL decimal1_DECIMAL;
/*      */ 
/*      */ 
/*      */       
/*  564 */       public _DECIMAL1() { setType("signscale"); }
/*      */ 
/*      */       
/*      */       public _DECIMAL1(Pointer pointer) {
/*  568 */         super(pointer);
/*  569 */         setType("signscale");
/*  570 */         read();
/*      */       }
/*      */ 
/*      */       
/*      */       public static class _DECIMAL1_DECIMAL
/*      */         extends Structure
/*      */       {
/*      */         public WinDef.BYTE scale;
/*      */         public WinDef.BYTE sign;
/*      */         
/*      */         public _DECIMAL1_DECIMAL() {}
/*      */         
/*  582 */         public _DECIMAL1_DECIMAL(Pointer pointer) { super(pointer); }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  587 */         protected List getFieldOrder() { return Arrays.asList(new String[] { "scale", "sign" }); }
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public static class _DECIMAL2
/*      */       extends Union
/*      */     {
/*      */       public WinDef.ULONGLONG Lo64;
/*      */       public _DECIMAL2_DECIMAL decimal2_DECIMAL;
/*      */       
/*  598 */       public _DECIMAL2() { setType("Lo64"); }
/*      */ 
/*      */       
/*      */       public _DECIMAL2(Pointer pointer) {
/*  602 */         super(pointer);
/*  603 */         setType("Lo64");
/*  604 */         read();
/*      */       }
/*      */ 
/*      */       
/*      */       public static class _DECIMAL2_DECIMAL
/*      */         extends Structure
/*      */       {
/*      */         public WinDef.BYTE Lo32;
/*      */         public WinDef.BYTE Mid32;
/*      */         
/*      */         public _DECIMAL2_DECIMAL() {}
/*      */         
/*  616 */         public _DECIMAL2_DECIMAL(Pointer pointer) { super(pointer); }
/*      */ 
/*      */ 
/*      */ 
/*      */         
/*  621 */         protected List getFieldOrder() { return Arrays.asList(new String[] { "Lo32", "Mid32" }); }
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  628 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "wReserved", "decimal1", "Hi32", "decimal2" }); }
/*      */   }
/*      */   
/*      */   public static class SYSKIND
/*      */     extends Structure {
/*      */     public int value;
/*      */     public static final int SYS_WIN16 = 0;
/*      */     public static final int SYS_WIN32 = 1;
/*      */     public static final int SYS_MAC = 2;
/*      */     public static final int SYS_WIN64 = 3;
/*      */     
/*      */     public static class ByReference
/*      */       extends SYSKIND implements Structure.ByReference {}
/*      */     
/*      */     public SYSKIND() {}
/*      */     
/*  644 */     public SYSKIND(int value) { this.value = value; }
/*      */ 
/*      */     
/*      */     public SYSKIND(Pointer pointer) {
/*  648 */       super(pointer);
/*  649 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  659 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LIBFLAGS
/*      */     extends Structure
/*      */   {
/*      */     public int value;
/*      */     
/*      */     public static final int LIBFLAG_FRESTRICTED = 1;
/*      */     
/*      */     public static final int LIBFLAG_FCONTROL = 2;
/*      */ 
/*      */     
/*  674 */     public LIBFLAGS(int value) { this.value = value; } public static final int LIBFLAG_FHIDDEN = 4; public static final int LIBFLAG_FHASDISKIMAGE = 8;
/*      */     public static class ByReference extends LIBFLAGS implements Structure.ByReference {}
/*      */     public LIBFLAGS() {}
/*      */     public LIBFLAGS(Pointer pointer) {
/*  678 */       super(pointer);
/*  679 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  689 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); } }
/*      */   
/*      */   public static class TLIBATTR extends Structure {
/*      */     public Guid.GUID guid;
/*      */     public WinDef.LCID lcid;
/*      */     public OaIdl.SYSKIND syskind;
/*      */     public WinDef.WORD wMajorVerNum;
/*      */     public WinDef.WORD wMinorVerNum;
/*      */     public WinDef.WORD wLibFlags;
/*      */     
/*      */     public static class ByReference extends TLIBATTR implements Structure.ByReference {
/*      */       public ByReference(Pointer pointer) {
/*  701 */         super(pointer);
/*  702 */         read();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public ByReference() {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public TLIBATTR() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public TLIBATTR(Pointer pointer) {
/*  718 */       super(pointer);
/*  719 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  724 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "guid", "lcid", "syskind", "wMajorVerNum", "wMinorVerNum", "wLibFlags" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BINDPTR
/*      */     extends Union
/*      */   {
/*      */     public OaIdl.FUNCDESC lpfuncdesc;
/*      */     
/*      */     public OaIdl.VARDESC lpvardesc;
/*      */     
/*      */     public TypeComp lptcomp;
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends BINDPTR
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */     
/*      */     public BINDPTR() {}
/*      */ 
/*      */     
/*      */     public BINDPTR(OaIdl.VARDESC lpvardesc) {
/*  748 */       this.lpvardesc = lpvardesc;
/*  749 */       setType(OaIdl.VARDESC.class);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BINDPTR(TypeComp lptcomp) {
/*  755 */       this.lptcomp = lptcomp;
/*  756 */       setType(TypeComp.class);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public BINDPTR(OaIdl.FUNCDESC lpfuncdesc) {
/*  762 */       this.lpfuncdesc = lpfuncdesc;
/*  763 */       setType(OaIdl.FUNCDESC.class);
/*      */     }
/*      */   }
/*      */   
/*      */   public static class FUNCDESC
/*      */     extends Structure {
/*      */     public OaIdl.MEMBERID memid;
/*      */     public OaIdl.ScodeArg.ByReference lprgscode;
/*      */     public OaIdl.ElemDescArg.ByReference lprgelemdescParam;
/*      */     public OaIdl.FUNCKIND funckind;
/*      */     public OaIdl.INVOKEKIND invkind;
/*      */     public OaIdl.CALLCONV callconv;
/*      */     public WinDef.SHORT cParams;
/*      */     public WinDef.SHORT cParamsOpt;
/*      */     public WinDef.SHORT oVft;
/*      */     public WinDef.SHORT cScodes;
/*      */     public OaIdl.ELEMDESC elemdescFunc;
/*      */     public WinDef.WORD wFuncFlags;
/*      */     
/*      */     public static class ByReference
/*      */       extends FUNCDESC
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public FUNCDESC() {}
/*      */     
/*      */     public FUNCDESC(Pointer pointer) {
/*  789 */       super(pointer);
/*  790 */       read();
/*      */       
/*  792 */       if (this.cParams.shortValue() > 1) {
/*  793 */         this.lprgelemdescParam
/*  794 */           .elemDescArg = new OaIdl.ELEMDESC[this.cParams.shortValue()];
/*  795 */         this.lprgelemdescParam.read();
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  801 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "memid", "lprgscode", "lprgelemdescParam", "funckind", "invkind", "callconv", "cParams", "cParamsOpt", "oVft", "cScodes", "elemdescFunc", "wFuncFlags" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class ElemDescArg
/*      */     extends Structure
/*      */   {
/*      */     public static class ByReference
/*      */       extends ElemDescArg
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */     
/*  813 */     public OaIdl.ELEMDESC[] elemDescArg = { new OaIdl.ELEMDESC() };
/*      */ 
/*      */     
/*      */     public ElemDescArg() {}
/*      */     
/*      */     public ElemDescArg(Pointer pointer) {
/*  819 */       super(pointer);
/*  820 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  825 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "elemDescArg" }); }
/*      */   }
/*      */   
/*      */   public static class ScodeArg
/*      */     extends Structure {
/*      */     public static class ByReference
/*      */       extends ScodeArg
/*      */       implements Structure.ByReference {}
/*      */     
/*  834 */     public WinDef.SCODE[] scodeArg = { new WinDef.SCODE() };
/*      */ 
/*      */     
/*      */     public ScodeArg() {}
/*      */     
/*      */     public ScodeArg(Pointer pointer) {
/*  840 */       super(pointer);
/*  841 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  846 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "scodeArg" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class VARDESC
/*      */     extends Structure
/*      */   {
/*      */     public OaIdl.MEMBERID memid;
/*      */ 
/*      */     
/*      */     public WTypes.LPOLESTR lpstrSchema;
/*      */     
/*      */     public _VARDESC _vardesc;
/*      */     
/*      */     public OaIdl.ELEMDESC elemdescVar;
/*      */     
/*      */     public WinDef.WORD wVarFlags;
/*      */     
/*      */     public OaIdl.VARKIND varkind;
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends VARDESC
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */     
/*      */     public static class _VARDESC
/*      */       extends Union
/*      */     {
/*      */       public NativeLong oInst;
/*      */       
/*      */       public Variant.VARIANT.ByReference lpvarValue;
/*      */ 
/*      */       
/*      */       public static class ByReference
/*      */         extends _VARDESC
/*      */         implements Structure.ByReference {}
/*      */ 
/*      */       
/*      */       public _VARDESC() {
/*  887 */         setType("lpvarValue");
/*  888 */         read();
/*      */       }
/*      */       
/*      */       public _VARDESC(Pointer pointer) {
/*  892 */         super(pointer);
/*  893 */         setType("lpvarValue");
/*  894 */         read();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public _VARDESC(Variant.VARIANT.ByReference lpvarValue) {
/*  904 */         this.lpvarValue = lpvarValue;
/*  905 */         setType("lpvarValue");
/*      */       }
/*      */ 
/*      */ 
/*      */       
/*      */       public _VARDESC(NativeLong oInst) {
/*  911 */         this.oInst = oInst;
/*  912 */         setType("oInst");
/*      */       }
/*      */     }
/*      */ 
/*      */     
/*      */     public VARDESC() {}
/*      */ 
/*      */     
/*      */     public VARDESC(Pointer pointer) {
/*  921 */       super(pointer);
/*  922 */       this._vardesc.setType("lpvarValue");
/*  923 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  928 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "memid", "lpstrSchema", "_vardesc", "elemdescVar", "wVarFlags", "varkind" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ELEMDESC
/*      */     extends Structure
/*      */   {
/*      */     public OaIdl.TYPEDESC tdesc;
/*      */ 
/*      */     
/*      */     public _ELEMDESC _elemdesc;
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends ELEMDESC
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */     
/*      */     public static class _ELEMDESC
/*      */       extends Union
/*      */     {
/*      */       public OaIdl.IDLDESC idldesc;
/*      */ 
/*      */       
/*      */       public OaIdl.PARAMDESC paramdesc;
/*      */ 
/*      */ 
/*      */       
/*      */       public static class ByReference
/*      */         extends _ELEMDESC
/*      */         implements Structure.ByReference {}
/*      */ 
/*      */       
/*      */       public _ELEMDESC() {}
/*      */ 
/*      */       
/*      */       public _ELEMDESC(Pointer pointer) {
/*  968 */         super(pointer);
/*  969 */         setType("paramdesc");
/*  970 */         read();
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public _ELEMDESC(OaIdl.PARAMDESC paramdesc) {
/*  979 */         this.paramdesc = paramdesc;
/*  980 */         setType("paramdesc");
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public _ELEMDESC(OaIdl.IDLDESC idldesc) {
/*  989 */         this.idldesc = idldesc;
/*  990 */         setType("idldesc");
/*      */       }
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  996 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "tdesc", "_elemdesc" }); }
/*      */ 
/*      */     
/*      */     public ELEMDESC() {}
/*      */ 
/*      */     
/*      */     public ELEMDESC(Pointer pointer) {
/* 1003 */       super(pointer);
/* 1004 */       read();
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class FUNCKIND
/*      */     extends Structure
/*      */   {
/*      */     public static final int FUNC_VIRTUAL = 0;
/*      */     
/*      */     public static final int FUNC_PUREVIRTUAL = 1;
/*      */     
/*      */     public static final int FUNC_NONVIRTUAL = 2;
/*      */     
/*      */     public static final int FUNC_STATIC = 3;
/*      */     
/*      */     public static final int FUNC_DISPATCH = 4;
/*      */     
/*      */     public int value;
/*      */     
/*      */     public static class ByReference
/*      */       extends FUNCKIND
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public FUNCKIND() {}
/*      */     
/* 1030 */     public FUNCKIND(int value) { this.value = value; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1036 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */   
/*      */   public static class INVOKEKIND
/*      */     extends Structure
/*      */   {
/*      */     public static class ByReference
/*      */       extends INVOKEKIND
/*      */       implements Structure.ByReference {}
/*      */     
/* 1046 */     public static final INVOKEKIND INVOKE_FUNC = new INVOKEKIND(true);
/*      */     
/* 1048 */     public static final INVOKEKIND INVOKE_PROPERTYGET = new INVOKEKIND(2);
/*      */     
/* 1050 */     public static final INVOKEKIND INVOKE_PROPERTYPUT = new INVOKEKIND(4);
/*      */     
/* 1052 */     public static final INVOKEKIND INVOKE_PROPERTYPUTREF = new INVOKEKIND(8);
/*      */     
/*      */     public int value;
/*      */ 
/*      */     
/*      */     public INVOKEKIND() {}
/*      */ 
/*      */     
/* 1060 */     public INVOKEKIND(int value) { this.value = value; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1066 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class CALLCONV
/*      */     extends Structure
/*      */   {
/*      */     public static final int CC_FASTCALL = 0;
/*      */     
/*      */     public static final int CC_CDECL = 1;
/*      */     
/*      */     public static final int CC_MSCPASCAL = 2;
/*      */     
/*      */     public static final int CC_PASCAL = 2;
/*      */     
/*      */     public static final int CC_MACPASCAL = 3;
/*      */     
/*      */     public static final int CC_STDCALL = 4;
/*      */     
/*      */     public static final int CC_FPFASTCALL = 5;
/*      */     
/*      */     public static final int CC_SYSCALL = 6;
/*      */     
/*      */     public static final int CC_MPWCDECL = 7;
/*      */     
/*      */     public static final int CC_MPWPASCAL = 8;
/*      */     
/*      */     public static final int CC_MAX = 9;
/*      */     
/*      */     public int value;
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends CALLCONV
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public CALLCONV() {}
/*      */     
/* 1104 */     public CALLCONV(int value) { this.value = value; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1109 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class VARKIND
/*      */     extends Structure
/*      */   {
/*      */     public static final int VAR_PERINSTANCE = 0;
/*      */     
/*      */     public static final int VAR_STATIC = 1;
/*      */     
/*      */     public static final int VAR_CONST = 2;
/*      */     
/*      */     public static final int VAR_DISPATCH = 3;
/*      */     
/*      */     public int value;
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends VARKIND
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public VARKIND() {}
/*      */     
/* 1133 */     public VARKIND(int value) { this.value = value; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1138 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "value" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class TYPEDESC
/*      */     extends Structure
/*      */   {
/*      */     public _TYPEDESC _typedesc;
/*      */     public WTypes.VARTYPE vt;
/*      */     
/*      */     public static class ByReference
/*      */       extends TYPEDESC
/*      */       implements Structure.ByReference {}
/*      */     
/* 1152 */     public TYPEDESC() { read(); }
/*      */ 
/*      */     
/*      */     public TYPEDESC(Pointer pointer) {
/* 1156 */       super(pointer);
/* 1157 */       read();
/*      */     }
/*      */     
/*      */     public TYPEDESC(_TYPEDESC _typedesc, WTypes.VARTYPE vt) {
/* 1161 */       this._typedesc = _typedesc;
/* 1162 */       this.vt = vt;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class _TYPEDESC
/*      */       extends Union
/*      */     {
/*      */       public OaIdl.TYPEDESC.ByReference lptdesc;
/*      */ 
/*      */ 
/*      */       
/*      */       public OaIdl.ARRAYDESC.ByReference lpadesc;
/*      */ 
/*      */       
/*      */       public OaIdl.HREFTYPE hreftype;
/*      */ 
/*      */ 
/*      */       
/*      */       public _TYPEDESC() {
/* 1183 */         setType("hreftype");
/* 1184 */         read();
/*      */       }
/*      */       
/*      */       public _TYPEDESC(Pointer pointer) {
/* 1188 */         super(pointer);
/* 1189 */         setType("hreftype");
/* 1190 */         read();
/*      */       }
/*      */       
/*      */       public OaIdl.TYPEDESC.ByReference getLptdesc() {
/* 1194 */         setType("lptdesc");
/* 1195 */         read();
/* 1196 */         return this.lptdesc;
/*      */       }
/*      */       
/*      */       public OaIdl.ARRAYDESC.ByReference getLpadesc() {
/* 1200 */         setType("lpadesc");
/* 1201 */         read();
/* 1202 */         return this.lpadesc;
/*      */       }
/*      */       
/*      */       public OaIdl.HREFTYPE getHreftype() {
/* 1206 */         setType("hreftype");
/* 1207 */         read();
/* 1208 */         return this.hreftype;
/*      */       }
/*      */     }
/*      */ 
/*      */     
/* 1213 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "_typedesc", "vt" }); }
/*      */   }
/*      */   
/*      */   public static class IDLDESC
/*      */     extends Structure {
/*      */     public BaseTSD.ULONG_PTR dwReserved;
/*      */     public WinDef.USHORT wIDLFlags;
/*      */     
/*      */     public static class ByReference
/*      */       extends IDLDESC implements Structure.ByReference {
/*      */       public ByReference() {}
/*      */       
/* 1225 */       public ByReference(OaIdl.IDLDESC idldesc) { super(idldesc.dwReserved, idldesc.wIDLFlags); }
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public IDLDESC() {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public IDLDESC(Pointer pointer) {
/* 1238 */       super(pointer);
/* 1239 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public IDLDESC(BaseTSD.ULONG_PTR dwReserved, WinDef.USHORT wIDLFlags) {
/* 1245 */       this.dwReserved = dwReserved;
/* 1246 */       this.wIDLFlags = wIDLFlags;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1251 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwReserved", "wIDLFlags" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ARRAYDESC
/*      */     extends Structure
/*      */   {
/*      */     public OaIdl.TYPEDESC tdescElem;
/*      */     
/*      */     public short cDims;
/*      */     
/* 1263 */     public OaIdl.SAFEARRAYBOUND[] rgbounds = { new OaIdl.SAFEARRAYBOUND() };
/*      */ 
/*      */     
/*      */     public ARRAYDESC() {}
/*      */ 
/*      */     
/*      */     public ARRAYDESC(Pointer pointer) {
/* 1270 */       super(pointer);
/* 1271 */       read();
/*      */     }
/*      */ 
/*      */     
/* 1275 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "tdescElem", "cDims", "rgbounds" }); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ARRAYDESC(OaIdl.TYPEDESC tdescElem, short cDims, SAFEARRAYBOUND[] rgbounds) {
/* 1289 */       this.tdescElem = tdescElem;
/* 1290 */       this.cDims = cDims;
/* 1291 */       if (rgbounds.length != this.rgbounds.length)
/* 1292 */         throw new IllegalArgumentException("Wrong array size !"); 
/* 1293 */       this.rgbounds = rgbounds;
/*      */     }
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends ARRAYDESC
/*      */       implements Structure.ByReference {}
/*      */   }
/*      */ 
/*      */   
/*      */   public static class PARAMDESC
/*      */     extends Structure
/*      */   {
/*      */     public Pointer pparamdescex;
/*      */     
/*      */     public WinDef.USHORT wParamFlags;
/*      */     
/*      */     public static class ByReference
/*      */       extends PARAMDESC
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public PARAMDESC() {}
/*      */     
/*      */     public PARAMDESC(Pointer pointer) {
/* 1317 */       super(pointer);
/* 1318 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     protected List getFieldOrder() {
/* 1324 */       return Arrays.asList(new String[] { "pparamdescex", "wParamFlags" });
/*      */     }
/*      */   }
/*      */   
/*      */   public static class PARAMDESCEX
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.ULONG cBytes;
/*      */     public Variant.VariantArg varDefaultValue;
/*      */     
/*      */     public static class ByReference
/*      */       extends PARAMDESCEX
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public PARAMDESCEX() {}
/*      */     
/*      */     public PARAMDESCEX(Pointer pointer) {
/* 1341 */       super(pointer);
/* 1342 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1347 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "cBytes", "varDefaultValue" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class HREFTYPE
/*      */     extends WinDef.DWORD
/*      */   {
/*      */     public HREFTYPE() {}
/*      */ 
/*      */     
/* 1357 */     public HREFTYPE(long value) { super(value); }
/*      */   }
/*      */   
/*      */   public static class HREFTYPEByReference
/*      */     extends WinDef.DWORDByReference
/*      */   {
/* 1363 */     public HREFTYPEByReference() { this(new OaIdl.HREFTYPE(0L)); }
/*      */ 
/*      */ 
/*      */     
/* 1367 */     public HREFTYPEByReference(WinDef.DWORD value) { super(value); }
/*      */ 
/*      */ 
/*      */     
/* 1371 */     public void setValue(OaIdl.HREFTYPE value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */     
/* 1375 */     public OaIdl.HREFTYPE getValue() { return new OaIdl.HREFTYPE(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class TYPEATTR
/*      */     extends Structure
/*      */   {
/*      */     public Guid.GUID guid;
/*      */     
/*      */     public WinDef.LCID lcid;
/*      */     
/*      */     public WinDef.DWORD dwReserved;
/*      */     
/*      */     public OaIdl.MEMBERID memidConstructor;
/*      */     
/*      */     public OaIdl.MEMBERID memidDestructor;
/*      */     
/*      */     public WTypes.LPOLESTR lpstrSchema;
/*      */     
/*      */     public WinDef.ULONG cbSizeInstance;
/*      */     
/*      */     public OaIdl.TYPEKIND typekind;
/*      */     
/*      */     public WinDef.WORD cFuncs;
/*      */     public WinDef.WORD cVars;
/*      */     public WinDef.WORD cImplTypes;
/*      */     public WinDef.WORD cbSizeVft;
/*      */     public WinDef.WORD cbAlignment;
/*      */     public WinDef.WORD wTypeFlags;
/*      */     public WinDef.WORD wMajorVerNum;
/*      */     public WinDef.WORD wMinorVerNum;
/*      */     public OaIdl.TYPEDESC tdescAlias;
/*      */     public OaIdl.IDLDESC idldescType;
/*      */     
/*      */     public static class ByReference
/*      */       extends TYPEATTR
/*      */       implements Structure.ByReference {}
/*      */     
/*      */     public TYPEATTR() {}
/*      */     
/*      */     public TYPEATTR(Pointer pointer) {
/* 1416 */       super(pointer);
/* 1417 */       read();
/*      */     }
/*      */ 
/*      */     
/*      */     protected List getFieldOrder() {
/* 1422 */       return Arrays.asList(new String[] { 
/*      */             "guid", "lcid", "dwReserved", "memidConstructor", "memidDestructor", "lpstrSchema", "cbSizeInstance", "typekind", "cFuncs", "cVars", 
/*      */             "cImplTypes", "cbSizeVft", "cbAlignment", "wTypeFlags", "wMajorVerNum", "wMinorVerNum", "tdescAlias", "idldescType" });
/*      */     }
/*      */   }
/*      */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\OaIdl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */