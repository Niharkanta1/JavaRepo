/*     */ package com.sun.jna.platform.win32;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Tlhelp32
/*     */   extends StdCallLibrary
/*     */ {
/*  29 */   public static final WinDef.DWORD TH32CS_SNAPHEAPLIST = new WinDef.DWORD(1L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  34 */   public static final WinDef.DWORD TH32CS_SNAPPROCESS = new WinDef.DWORD(2L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   public static final WinDef.DWORD TH32CS_SNAPTHREAD = new WinDef.DWORD(4L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  45 */   public static final WinDef.DWORD TH32CS_SNAPMODULE = new WinDef.DWORD(8L);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static final WinDef.DWORD TH32CS_SNAPMODULE32 = new WinDef.DWORD(16L);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final WinDef.DWORD TH32CS_SNAPALL = new WinDef.DWORD((TH32CS_SNAPHEAPLIST.intValue() | TH32CS_SNAPPROCESS
/*  58 */       .intValue() | TH32CS_SNAPTHREAD.intValue() | TH32CS_SNAPMODULE.intValue()));
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public static final WinDef.DWORD TH32CS_INHERIT = new WinDef.DWORD(-2147483648L);
/*     */   
/*     */   public static class PROCESSENTRY32 extends Structure { public WinDef.DWORD dwSize;
/*     */     public WinDef.DWORD cntUsage;
/*     */     public WinDef.DWORD th32ProcessID;
/*     */     public BaseTSD.ULONG_PTR th32DefaultHeapID;
/*     */     public WinDef.DWORD th32ModuleID;
/*     */     public WinDef.DWORD cntThreads;
/*     */     public WinDef.DWORD th32ParentProcessID;
/*     */     public WinDef.LONG pcPriClassBase;
/*     */     public WinDef.DWORD dwFlags;
/*     */     
/*  75 */     public static class ByReference extends PROCESSENTRY32 implements Structure.ByReference { public ByReference(Pointer memory) { super(memory); }
/*     */       
/*     */       public ByReference() {} }
/*     */ 
/*     */     
/*  80 */     public PROCESSENTRY32() { this.dwSize = new WinDef.DWORD(size()); }
/*     */ 
/*     */     
/*     */     public PROCESSENTRY32(Pointer memory) {
/*  84 */       super(memory);
/*  85 */       read();
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
/* 140 */     public char[] szExeFile = new char[260];
/*     */ 
/*     */     
/* 143 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "cntUsage", "th32ProcessID", "th32DefaultHeapID", "th32ModuleID", "cntThreads", "th32ParentProcessID", "pcPriClassBase", "dwFlags", "szExeFile" }); } }
/*     */ 
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Tlhelp32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */