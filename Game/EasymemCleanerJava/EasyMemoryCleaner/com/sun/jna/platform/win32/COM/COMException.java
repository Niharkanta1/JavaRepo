/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class COMException
/*     */   extends RuntimeException
/*     */ {
/*     */   private OaIdl.EXCEPINFO pExcepInfo;
/*     */   private IntByReference puArgErr;
/*     */   private int uArgErr;
/*     */   
/*     */   public COMException() {}
/*     */   
/*  50 */   public COMException(String message, Throwable cause) { super(message, cause); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public COMException(String message) { super(message); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COMException(String message, OaIdl.EXCEPINFO pExcepInfo, IntByReference puArgErr) {
/*  75 */     super(message + " (puArgErr=" + ((null == puArgErr) ? "" : Integer.valueOf(puArgErr.getValue())) + ")");
/*  76 */     this.pExcepInfo = pExcepInfo;
/*  77 */     this.puArgErr = puArgErr;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public COMException(Throwable cause) { super(cause); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public OaIdl.EXCEPINFO getExcepInfo() { return this.pExcepInfo; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 105 */   public IntByReference getArgErr() { return this.puArgErr; }
/*     */ 
/*     */ 
/*     */   
/* 109 */   public int getuArgErr() { return this.uArgErr; }
/*     */ 
/*     */ 
/*     */   
/* 113 */   public void setuArgErr(int uArgErr) { this.uArgErr = uArgErr; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\COMException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */