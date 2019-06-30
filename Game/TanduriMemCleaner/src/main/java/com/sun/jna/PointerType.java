/*     */ package com.sun.jna;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PointerType
/*     */   implements NativeMapped
/*     */ {
/*     */   private Pointer pointer;
/*     */   
/*  25 */   protected PointerType() { this.pointer = Pointer.NULL; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   protected PointerType(Pointer p) { this.pointer = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   public Class nativeType() { return Pointer.class; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public Object toNative() { return getPointer(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  50 */   public Pointer getPointer() { return this.pointer; }
/*     */ 
/*     */ 
/*     */   
/*  54 */   public void setPointer(Pointer p) { this.pointer = p; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object fromNative(Object nativeValue, FromNativeContext context) {
/*  65 */     if (nativeValue == null) {
/*  66 */       return null;
/*     */     }
/*     */     try {
/*  69 */       PointerType pt = (PointerType)getClass().newInstance();
/*  70 */       pt.pointer = (Pointer)nativeValue;
/*  71 */       return pt;
/*     */     }
/*  73 */     catch (InstantiationException e) {
/*  74 */       throw new IllegalArgumentException("Can't instantiate " + getClass());
/*     */     }
/*  76 */     catch (IllegalAccessException e) {
/*  77 */       throw new IllegalArgumentException("Not allowed to instantiate " + getClass());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  85 */   public int hashCode() { return (this.pointer != null) ? this.pointer.hashCode() : 0; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  92 */     if (o == this) return true; 
/*  93 */     if (o instanceof PointerType) {
/*  94 */       Pointer p = ((PointerType)o).getPointer();
/*  95 */       if (this.pointer == null)
/*  96 */         return (p == null); 
/*  97 */       return this.pointer.equals(p);
/*     */     } 
/*  99 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 103 */   public String toString() { return (this.pointer == null) ? "NULL" : (this.pointer.toString() + " (" + super.toString() + ")"); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\PointerType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */