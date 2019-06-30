/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.IntegerType;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.ptr.ByReference;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface BaseTSD
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static class LONG_PTR
/*     */     extends IntegerType
/*     */   {
/*  32 */     public LONG_PTR() { this(0L); }
/*     */ 
/*     */ 
/*     */     
/*  36 */     public LONG_PTR(long value) { super(Pointer.SIZE, value); }
/*     */ 
/*     */ 
/*     */     
/*  40 */     public Pointer toPointer() { return Pointer.createConstant(longValue()); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SSIZE_T
/*     */     extends LONG_PTR
/*     */   {
/*  49 */     public SSIZE_T() { this(0L); }
/*     */ 
/*     */ 
/*     */     
/*  53 */     public SSIZE_T(long value) { super(value); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ULONG_PTR
/*     */     extends IntegerType
/*     */   {
/*  62 */     public ULONG_PTR() { this(0L); }
/*     */ 
/*     */ 
/*     */     
/*  66 */     public ULONG_PTR(long value) { super(Pointer.SIZE, value, true); }
/*     */ 
/*     */ 
/*     */     
/*  70 */     public Pointer toPointer() { return Pointer.createConstant(longValue()); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class ULONG_PTRByReference
/*     */     extends ByReference
/*     */   {
/*  79 */     public ULONG_PTRByReference() { this(new BaseTSD.ULONG_PTR(0L)); }
/*     */     
/*     */     public ULONG_PTRByReference(BaseTSD.ULONG_PTR value) {
/*  82 */       super(Pointer.SIZE);
/*  83 */       setValue(value);
/*     */     }
/*     */     public void setValue(BaseTSD.ULONG_PTR value) {
/*  86 */       if (Pointer.SIZE == 4) {
/*  87 */         getPointer().setInt(0L, value.intValue());
/*     */       } else {
/*     */         
/*  90 */         getPointer().setLong(0L, value.longValue());
/*     */       } 
/*     */     }
/*     */     public BaseTSD.ULONG_PTR getValue() {
/*  94 */       return new BaseTSD.ULONG_PTR((Pointer.SIZE == 4) ? 
/*  95 */           getPointer().getInt(0L) : 
/*  96 */           getPointer().getLong(0L));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DWORD_PTR
/*     */     extends IntegerType
/*     */   {
/* 106 */     public DWORD_PTR() { this(0L); }
/*     */ 
/*     */ 
/*     */     
/* 110 */     public DWORD_PTR(long value) { super(Pointer.SIZE, value); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SIZE_T
/*     */     extends ULONG_PTR
/*     */   {
/* 120 */     public SIZE_T() { this(0L); }
/*     */ 
/*     */ 
/*     */     
/* 124 */     public SIZE_T(long value) { super(value); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\BaseTSD.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */