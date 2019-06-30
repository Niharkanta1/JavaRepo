/*     */ package com.sun.jna.platform.win32;
/*     */ 
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
/*     */ public abstract class Crypt32Util
/*     */ {
/*  33 */   public static byte[] cryptProtectData(byte[] data) { return cryptProtectData(data, 0); }
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
/*  46 */   public static byte[] cryptProtectData(byte[] data, int flags) { return cryptProtectData(data, null, flags, "", null); }
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
/*     */   public static byte[] cryptProtectData(byte[] data, byte[] entropy, int flags, String description, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT prompt) {
/*  66 */     WinCrypt.DATA_BLOB pDataIn = new WinCrypt.DATA_BLOB(data);
/*  67 */     pDataProtected = new WinCrypt.DATA_BLOB();
/*  68 */     WinCrypt.DATA_BLOB pEntropy = (entropy == null) ? null : new WinCrypt.DATA_BLOB(entropy);
/*     */     try {
/*  70 */       if (!Crypt32.INSTANCE.CryptProtectData(pDataIn, description, pEntropy, null, prompt, flags, pDataProtected))
/*     */       {
/*  72 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*  74 */       return pDataProtected.getData();
/*     */     } finally {
/*  76 */       if (pDataProtected.pbData != null) {
/*  77 */         Kernel32.INSTANCE.LocalFree(pDataProtected.pbData);
/*     */       }
/*     */     } 
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
/*  90 */   public static byte[] cryptUnprotectData(byte[] data) { return cryptUnprotectData(data, 0); }
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
/* 103 */   public static byte[] cryptUnprotectData(byte[] data, int flags) { return cryptUnprotectData(data, null, flags, null); }
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
/*     */   public static byte[] cryptUnprotectData(byte[] data, byte[] entropy, int flags, WinCrypt.CRYPTPROTECT_PROMPTSTRUCT prompt) {
/* 121 */     WinCrypt.DATA_BLOB pDataIn = new WinCrypt.DATA_BLOB(data);
/* 122 */     pDataUnprotected = new WinCrypt.DATA_BLOB();
/* 123 */     WinCrypt.DATA_BLOB pEntropy = (entropy == null) ? null : new WinCrypt.DATA_BLOB(entropy);
/* 124 */     pDescription = new PointerByReference();
/*     */     try {
/* 126 */       if (!Crypt32.INSTANCE.CryptUnprotectData(pDataIn, pDescription, pEntropy, null, prompt, flags, pDataUnprotected))
/*     */       {
/* 128 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/* 130 */       return pDataUnprotected.getData();
/*     */     } finally {
/* 132 */       if (pDataUnprotected.pbData != null) {
/* 133 */         Kernel32.INSTANCE.LocalFree(pDataUnprotected.pbData);
/*     */       }
/* 135 */       if (pDescription.getValue() != null)
/* 136 */         Kernel32.INSTANCE.LocalFree(pDescription.getValue()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Crypt32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */