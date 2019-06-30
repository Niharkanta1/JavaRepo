/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface WinCrypt
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int CRYPTPROTECT_PROMPT_ON_UNPROTECT = 1;
/*     */   public static final int CRYPTPROTECT_PROMPT_ON_PROTECT = 2;
/*     */   public static final int CRYPTPROTECT_PROMPT_RESERVED = 4;
/*     */   public static final int CRYPTPROTECT_PROMPT_STRONG = 8;
/*     */   public static final int CRYPTPROTECT_PROMPT_REQUIRE_STRONG = 16;
/*     */   public static final int CRYPTPROTECT_UI_FORBIDDEN = 1;
/*     */   public static final int CRYPTPROTECT_LOCAL_MACHINE = 4;
/*     */   public static final int CRYPTPROTECT_CRED_SYNC = 8;
/*     */   public static final int CRYPTPROTECT_AUDIT = 16;
/*     */   public static final int CRYPTPROTECT_NO_RECOVERY = 32;
/*     */   public static final int CRYPTPROTECT_VERIFY_PROTECTION = 64;
/*     */   public static final int CRYPTPROTECT_CRED_REGENERATE = 128;
/*     */   
/*     */   public static class DATA_BLOB
/*     */     extends Structure
/*     */   {
/*     */     public int cbData;
/*     */     public Pointer pbData;
/*     */     
/*     */     public DATA_BLOB() {}
/*     */     
/*     */     public DATA_BLOB(Pointer memory) {
/*  41 */       super(memory);
/*  42 */       read();
/*     */     }
/*     */     
/*     */     public DATA_BLOB(byte[] data) {
/*  46 */       this.pbData = new Memory(data.length);
/*  47 */       this.pbData.write(0L, data, 0, data.length);
/*  48 */       this.cbData = data.length;
/*  49 */       allocateMemory();
/*     */     }
/*     */ 
/*     */     
/*  53 */     public DATA_BLOB(String s) { this(Native.toByteArray(s)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cbData", "pbData" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  74 */     public byte[] getData() { return (this.pbData == null) ? null : this.pbData.getByteArray(0L, this.cbData); }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CRYPTPROTECT_PROMPTSTRUCT
/*     */     extends Structure
/*     */   {
/*     */     public int cbSize;
/*     */     public int dwPromptFlags;
/*     */     public WinDef.HWND hwndApp;
/*     */     public String szPrompt;
/*     */     
/*     */     public CRYPTPROTECT_PROMPTSTRUCT() {}
/*     */     
/*     */     public CRYPTPROTECT_PROMPTSTRUCT(Pointer memory) {
/*  89 */       super(memory);
/*  90 */       read();
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
/* 111 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cbSize", "dwPromptFlags", "hwndApp", "szPrompt" }); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinCrypt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */