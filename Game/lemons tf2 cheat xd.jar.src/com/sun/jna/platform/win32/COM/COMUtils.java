/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.platform.win32.Advapi32;
/*     */ import com.sun.jna.platform.win32.Advapi32Util;
/*     */ import com.sun.jna.platform.win32.Kernel32Util;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.platform.win32.WinReg;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import java.util.ArrayList;
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
/*     */ public abstract class COMUtils
/*     */ {
/*     */   public static final int S_OK = 0;
/*     */   public static final int S_FALSE = 1;
/*     */   public static final int E_UNEXPECTED = -2147418113;
/*     */   
/*  52 */   public static boolean SUCCEEDED(WinNT.HRESULT hr) { return SUCCEEDED(hr.intValue()); }
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
/*  63 */   public static boolean SUCCEEDED(int hr) { return (hr >= 0); }
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
/*  74 */   public static boolean FAILED(WinNT.HRESULT hr) { return FAILED(hr.intValue()); }
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
/*  85 */   public static boolean FAILED(int hr) { return (hr < 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public static void checkRC(WinNT.HRESULT hr) { checkRC(hr, null, null); }
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
/*     */   public static void checkRC(WinNT.HRESULT hr, OaIdl.EXCEPINFO pExcepInfo, IntByReference puArgErr) {
/* 110 */     if (FAILED(hr)) {
/* 111 */       String formatMessageFromHR = Kernel32Util.formatMessage(hr);
/* 112 */       throw new COMException(formatMessageFromHR, pExcepInfo, puArgErr);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static ArrayList<COMInfo> getAllCOMInfoOnSystem() {
/* 122 */     phkResult = new WinReg.HKEYByReference();
/* 123 */     phkResult2 = new WinReg.HKEYByReference();
/*     */     
/* 125 */     ArrayList<COMInfo> comInfos = new ArrayList<COMInfo>();
/*     */ 
/*     */     
/*     */     try {
/* 129 */       phkResult = Advapi32Util.registryGetKey(WinReg.HKEY_CLASSES_ROOT, "CLSID", 131097);
/*     */ 
/*     */       
/* 132 */       Advapi32Util.InfoKey infoKey = Advapi32Util.registryQueryInfoKey(phkResult
/* 133 */           .getValue(), 131097);
/*     */       
/* 135 */       for (int i = 0; i < infoKey.lpcSubKeys.getValue(); i++) {
/* 136 */         Advapi32Util.EnumKey enumKey = Advapi32Util.registryRegEnumKey(phkResult
/* 137 */             .getValue(), i);
/* 138 */         String subKey = Native.toString(enumKey.lpName);
/*     */         
/* 140 */         COMInfo comInfo = new COMInfo(subKey);
/*     */         
/* 142 */         phkResult2 = Advapi32Util.registryGetKey(phkResult.getValue(), subKey, 131097);
/*     */         
/* 144 */         Advapi32Util.InfoKey infoKey2 = Advapi32Util.registryQueryInfoKey(phkResult2
/* 145 */             .getValue(), 131097);
/*     */         
/* 147 */         for (int y = 0; y < infoKey2.lpcSubKeys.getValue(); y++) {
/* 148 */           Advapi32Util.EnumKey enumKey2 = Advapi32Util.registryRegEnumKey(phkResult2
/* 149 */               .getValue(), y);
/* 150 */           String subKey2 = Native.toString(enumKey2.lpName);
/*     */           
/* 152 */           if (subKey2.equals("InprocHandler32")) {
/* 153 */             comInfo
/* 154 */               .inprocHandler32 = (String)Advapi32Util.registryGetValue(phkResult2.getValue(), subKey2, null);
/*     */           }
/* 156 */           else if (subKey2.equals("InprocServer32")) {
/* 157 */             comInfo
/* 158 */               .inprocServer32 = (String)Advapi32Util.registryGetValue(phkResult2.getValue(), subKey2, null);
/*     */           }
/* 160 */           else if (subKey2.equals("LocalServer32")) {
/* 161 */             comInfo
/* 162 */               .localServer32 = (String)Advapi32Util.registryGetValue(phkResult2.getValue(), subKey2, null);
/*     */           }
/* 164 */           else if (subKey2.equals("ProgID")) {
/* 165 */             comInfo
/* 166 */               .progID = (String)Advapi32Util.registryGetValue(phkResult2.getValue(), subKey2, null);
/*     */           }
/* 168 */           else if (subKey2.equals("TypeLib")) {
/* 169 */             comInfo
/* 170 */               .typeLib = (String)Advapi32Util.registryGetValue(phkResult2.getValue(), subKey2, null);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 175 */         Advapi32.INSTANCE.RegCloseKey(phkResult2.getValue());
/* 176 */         comInfos.add(comInfo);
/*     */       } 
/*     */     } finally {
/* 179 */       Advapi32.INSTANCE.RegCloseKey(phkResult.getValue());
/* 180 */       Advapi32.INSTANCE.RegCloseKey(phkResult2.getValue());
/*     */     } 
/*     */     
/* 183 */     return comInfos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class COMInfo
/*     */   {
/*     */     public String clsid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String inprocHandler32;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String inprocServer32;
/*     */ 
/*     */ 
/*     */     
/*     */     public String localServer32;
/*     */ 
/*     */ 
/*     */     
/*     */     public String progID;
/*     */ 
/*     */ 
/*     */     
/*     */     public String typeLib;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public COMInfo() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 224 */     public COMInfo(String clsid) { this.clsid = clsid; }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */