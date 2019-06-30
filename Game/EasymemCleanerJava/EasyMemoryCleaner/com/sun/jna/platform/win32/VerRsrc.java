/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ public interface VerRsrc
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static class VS_FIXEDFILEINFO
/*     */     extends Structure
/*     */   {
/*     */     public WinDef.DWORD dwSignature;
/*     */     public WinDef.DWORD dwStrucVersion;
/*     */     public WinDef.DWORD dwFileVersionMS;
/*     */     public WinDef.DWORD dwFileVersionLS;
/*     */     public WinDef.DWORD dwProductVersionMS;
/*     */     public WinDef.DWORD dwProductVersionLS;
/*     */     public WinDef.DWORD dwFileFlagsMask;
/*     */     public WinDef.DWORD dwFileFlags;
/*     */     public WinDef.DWORD dwFileOS;
/*     */     public WinDef.DWORD dwFileType;
/*     */     public WinDef.DWORD dwFileSubtype;
/*     */     public WinDef.DWORD dwFileDateMS;
/*     */     public WinDef.DWORD dwFileDateLS;
/*     */     
/*     */     public static class ByReference
/*     */       extends VS_FIXEDFILEINFO
/*     */       implements Structure.ByReference
/*     */     {
/*     */       public ByReference() {}
/*     */       
/*  35 */       public ByReference(Pointer memory) { super(memory); }
/*     */     }
/*     */ 
/*     */     
/*     */     public VS_FIXEDFILEINFO() {}
/*     */ 
/*     */     
/*     */     public VS_FIXEDFILEINFO(Pointer memory) {
/*  43 */       super(memory);
/*  44 */       read();
/*     */     }
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
/* 121 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSignature", "dwStrucVersion", "dwFileVersionMS", "dwFileVersionLS", "dwProductVersionMS", "dwProductVersionLS", "dwFileFlagsMask", "dwFileFlags", "dwFileOS", "dwFileType", "dwFileSubtype", "dwFileDateMS", "dwFileDateLS" }); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\VerRsrc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */