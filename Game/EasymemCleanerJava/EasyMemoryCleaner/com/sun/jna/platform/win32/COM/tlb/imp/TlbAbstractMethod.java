/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.ITypeInfo;
/*     */ import com.sun.jna.platform.win32.COM.TypeInfoUtil;
/*     */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.Variant;
/*     */ import com.sun.jna.platform.win32.WTypes;
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
/*     */ public abstract class TlbAbstractMethod
/*     */   extends TlbBase
/*     */   implements Variant
/*     */ {
/*     */   protected TypeInfoUtil.TypeInfoDoc typeInfoDoc;
/*     */   protected String methodName;
/*     */   protected String docStr;
/*     */   protected short vtableId;
/*     */   protected OaIdl.MEMBERID memberid;
/*     */   protected short paramCount;
/*     */   protected String returnType;
/*  73 */   protected String methodparams = "";
/*     */   
/*  75 */   protected String methodvariables = "";
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
/*     */   public TlbAbstractMethod(int index, TypeLibUtil typeLibUtil, OaIdl.FUNCDESC funcDesc, TypeInfoUtil typeInfoUtil) {
/*  91 */     super(index, typeLibUtil, typeInfoUtil);
/*  92 */     this.typeInfoDoc = typeInfoUtil.getDocumentation(funcDesc.memid);
/*  93 */     this.methodName = this.typeInfoDoc.getName();
/*  94 */     this.docStr = this.typeInfoDoc.getDocString();
/*     */ 
/*     */     
/*  97 */     this.vtableId = funcDesc.oVft.shortValue();
/*  98 */     this.memberid = funcDesc.memid;
/*  99 */     this.paramCount = funcDesc.cParams.shortValue();
/* 100 */     this.returnType = getType(funcDesc);
/*     */   }
/*     */ 
/*     */   
/* 104 */   public TypeInfoUtil.TypeInfoDoc getTypeInfoDoc() { return this.typeInfoDoc; }
/*     */ 
/*     */ 
/*     */   
/* 108 */   public String getMethodName() { return this.methodName; }
/*     */ 
/*     */ 
/*     */   
/* 112 */   public String getDocStr() { return this.docStr; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getVarType(WTypes.VARTYPE vt) {
/* 123 */     switch (vt.intValue()) {
/*     */       case 0:
/* 125 */         return "";
/*     */       case 1:
/* 127 */         return "null";
/*     */       case 2:
/* 129 */         return "short";
/*     */       case 3:
/* 131 */         return "int";
/*     */       case 4:
/* 133 */         return "float";
/*     */       case 5:
/* 135 */         return "double";
/*     */       case 6:
/* 137 */         return OaIdl.CURRENCY.class.getSimpleName();
/*     */       case 7:
/* 139 */         return OaIdl.DATE.class.getSimpleName();
/*     */       case 8:
/* 141 */         return WTypes.BSTR.class.getSimpleName();
/*     */       case 9:
/* 143 */         return com.sun.jna.platform.win32.COM.IDispatch.class.getSimpleName();
/*     */       case 10:
/* 145 */         return com.sun.jna.platform.win32.WinDef.SCODE.class.getSimpleName();
/*     */       case 11:
/* 147 */         return com.sun.jna.platform.win32.WinDef.BOOL.class.getSimpleName();
/*     */       case 12:
/* 149 */         return Variant.VARIANT.class.getSimpleName();
/*     */       case 13:
/* 151 */         return com.sun.jna.platform.win32.COM.IUnknown.class.getSimpleName();
/*     */       case 14:
/* 153 */         return OaIdl.DECIMAL.class.getSimpleName();
/*     */       case 16:
/* 155 */         return com.sun.jna.platform.win32.WinDef.CHAR.class.getSimpleName();
/*     */       case 17:
/* 157 */         return com.sun.jna.platform.win32.WinDef.UCHAR.class.getSimpleName();
/*     */       case 18:
/* 159 */         return com.sun.jna.platform.win32.WinDef.USHORT.class.getSimpleName();
/*     */       case 19:
/* 161 */         return com.sun.jna.platform.win32.WinDef.UINT.class.getSimpleName();
/*     */       case 20:
/* 163 */         return com.sun.jna.platform.win32.WinDef.LONG.class.getSimpleName();
/*     */       case 21:
/* 165 */         return com.sun.jna.platform.win32.WinDef.ULONG.class.getSimpleName();
/*     */       case 22:
/* 167 */         return "int";
/*     */       case 23:
/* 169 */         return com.sun.jna.platform.win32.WinDef.UINT.class.getSimpleName();
/*     */       case 24:
/* 171 */         return com.sun.jna.platform.win32.WinDef.PVOID.class.getSimpleName();
/*     */       case 25:
/* 173 */         return com.sun.jna.platform.win32.WinNT.HRESULT.class.getSimpleName();
/*     */       case 26:
/* 175 */         return com.sun.jna.Pointer.class.getSimpleName();
/*     */       case 27:
/* 177 */         return "safearray";
/*     */       case 28:
/* 179 */         return "carray";
/*     */       case 29:
/* 181 */         return "userdefined";
/*     */       case 30:
/* 183 */         return WTypes.LPSTR.class.getSimpleName();
/*     */       case 31:
/* 185 */         return WTypes.LPWSTR.class.getSimpleName();
/*     */       case 36:
/* 187 */         return "record";
/*     */       case 37:
/* 189 */         return com.sun.jna.platform.win32.WinDef.INT_PTR.class.getSimpleName();
/*     */       case 38:
/* 191 */         return com.sun.jna.platform.win32.WinDef.UINT_PTR.class.getSimpleName();
/*     */       case 64:
/* 193 */         return com.sun.jna.platform.win32.WinBase.FILETIME.class.getSimpleName();
/*     */       case 66:
/* 195 */         return "steam";
/*     */       case 67:
/* 197 */         return "storage";
/*     */       case 68:
/* 199 */         return "steamed_object";
/*     */       case 69:
/* 201 */         return "stored_object";
/*     */       case 70:
/* 203 */         return "blob_object";
/*     */       case 71:
/* 205 */         return "cf";
/*     */       case 72:
/* 207 */         return com.sun.jna.platform.win32.Guid.CLSID.class.getSimpleName();
/*     */       case 73:
/* 209 */         return "";
/*     */ 
/*     */       
/*     */       case 4096:
/* 213 */         return "";
/*     */       case 8192:
/* 215 */         return "";
/*     */       case 16384:
/* 217 */         return com.sun.jna.platform.win32.WinDef.PVOID.class.getSimpleName();
/*     */       case 32768:
/* 219 */         return "";
/*     */       case 65535:
/* 221 */         return "illegal";
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 226 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getUserdefinedType(OaIdl.HREFTYPE hreftype) {
/* 231 */     ITypeInfo refTypeInfo = this.typeInfoUtil.getRefTypeInfo(hreftype);
/* 232 */     TypeInfoUtil typeInfoUtil = new TypeInfoUtil(refTypeInfo);
/*     */     
/* 234 */     TypeInfoUtil.TypeInfoDoc documentation = typeInfoUtil.getDocumentation(OaIdl.MEMBERID_NIL);
/* 235 */     return documentation.getName();
/*     */   }
/*     */   
/*     */   protected String getType(OaIdl.FUNCDESC funcDesc) {
/* 239 */     OaIdl.ELEMDESC elemDesc = funcDesc.elemdescFunc;
/* 240 */     return getType(elemDesc);
/*     */   }
/*     */   
/*     */   protected String getType(OaIdl.ELEMDESC elemDesc) {
/* 244 */     OaIdl.TYPEDESC _typeDesc = elemDesc.tdesc;
/* 245 */     return getType(_typeDesc);
/*     */   }
/*     */   
/*     */   protected String getType(OaIdl.TYPEDESC typeDesc) {
/* 249 */     WTypes.VARTYPE vt = typeDesc.vt;
/* 250 */     String type = "not_defined";
/*     */     
/* 252 */     if (vt.intValue() == 26) {
/* 253 */       OaIdl.TYPEDESC.ByReference byReference = typeDesc._typedesc.getLptdesc();
/* 254 */       type = getType(byReference);
/* 255 */     } else if (vt.intValue() == 27 || vt
/* 256 */       .intValue() == 28) {
/* 257 */       OaIdl.TYPEDESC tdescElem = (typeDesc._typedesc.getLpadesc()).tdescElem;
/* 258 */       type = getType(tdescElem);
/* 259 */     } else if (vt.intValue() == 29) {
/* 260 */       OaIdl.HREFTYPE hreftype = typeDesc._typedesc.hreftype;
/* 261 */       type = getUserdefinedType(hreftype);
/*     */     } else {
/* 263 */       type = getVarType(vt);
/*     */     } 
/*     */     
/* 266 */     return type;
/*     */   }
/*     */   
/*     */   protected String replaceJavaKeyword(String name) {
/* 270 */     if (name.equals("final"))
/* 271 */       return "_" + name; 
/* 272 */     if (name.equals("default"))
/* 273 */       return "_" + name; 
/* 274 */     if (name.equals("case"))
/* 275 */       return "_" + name; 
/* 276 */     if (name.equals("char"))
/* 277 */       return "_" + name; 
/* 278 */     if (name.equals("private"))
/* 279 */       return "_" + name; 
/* 280 */     if (name.equals("default")) {
/* 281 */       return "_" + name;
/*     */     }
/* 283 */     return name;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbAbstractMethod.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */