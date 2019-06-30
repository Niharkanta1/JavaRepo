/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Native;
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
/*     */ 
/*     */ public abstract class Secur32Util
/*     */ {
/*     */   public static class SecurityPackage
/*     */   {
/*     */     public String name;
/*     */     public String comment;
/*     */   }
/*     */   
/*     */   public static String getUserNameEx(int format) {
/*  50 */     char[] buffer = new char[128];
/*  51 */     IntByReference len = new IntByReference(buffer.length);
/*  52 */     boolean result = Secur32.INSTANCE.GetUserNameEx(format, buffer, len);
/*     */     
/*  54 */     if (!result) {
/*     */       
/*  56 */       int rc = Kernel32.INSTANCE.GetLastError();
/*     */       
/*  58 */       switch (rc) {
/*     */         case 234:
/*  60 */           buffer = new char[len.getValue() + 1];
/*     */           break;
/*     */         default:
/*  63 */           throw new Win32Exception(Native.getLastError());
/*     */       } 
/*     */       
/*  66 */       result = Secur32.INSTANCE.GetUserNameEx(format, buffer, len);
/*     */     } 
/*     */     
/*  69 */     if (!result) {
/*  70 */       throw new Win32Exception(Native.getLastError());
/*     */     }
/*     */     
/*  73 */     return Native.toString(buffer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SecurityPackage[] getSecurityPackages() {
/*  82 */     pcPackages = new IntByReference();
/*  83 */     Sspi.PSecPkgInfo pPackageInfo = new Sspi.PSecPkgInfo();
/*  84 */     int rc = Secur32.INSTANCE.EnumerateSecurityPackages(pcPackages, pPackageInfo);
/*  85 */     if (0 != rc) {
/*  86 */       throw new Win32Exception(rc);
/*     */     }
/*  88 */     Sspi.SecPkgInfo.ByReference[] arrayOfByReference = pPackageInfo.toArray(pcPackages.getValue());
/*  89 */     ArrayList<SecurityPackage> packages = new ArrayList<SecurityPackage>(pcPackages.getValue());
/*  90 */     for (Sspi.SecPkgInfo packageInfo : arrayOfByReference) {
/*  91 */       SecurityPackage securityPackage = new SecurityPackage();
/*  92 */       securityPackage.name = packageInfo.Name.toString();
/*  93 */       securityPackage.comment = packageInfo.Comment.toString();
/*  94 */       packages.add(securityPackage);
/*     */     } 
/*  96 */     rc = Secur32.INSTANCE.FreeContextBuffer(pPackageInfo.pPkgInfo.getPointer());
/*  97 */     if (0 != rc) {
/*  98 */       throw new Win32Exception(rc);
/*     */     }
/* 100 */     return (SecurityPackage[])packages.toArray(new SecurityPackage[0]);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Secur32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */