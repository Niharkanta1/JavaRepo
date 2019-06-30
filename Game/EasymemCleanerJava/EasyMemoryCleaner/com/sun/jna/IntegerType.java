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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class IntegerType
/*     */   extends Number
/*     */   implements NativeMapped
/*     */ {
/*     */   private int size;
/*     */   private Number number;
/*     */   private boolean unsigned;
/*     */   private long value;
/*     */   
/*  38 */   public IntegerType(int size) { this(size, 0L, false); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  43 */   public IntegerType(int size, boolean unsigned) { this(size, 0L, unsigned); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public IntegerType(int size, long value) { this(size, value, false); }
/*     */ 
/*     */ 
/*     */   
/*     */   public IntegerType(int size, long value, boolean unsigned) {
/*  53 */     this.size = size;
/*  54 */     this.unsigned = unsigned;
/*  55 */     setValue(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(long value) {
/*  62 */     long truncated = value;
/*  63 */     this.value = value;
/*  64 */     switch (this.size) {
/*     */       case 1:
/*  66 */         if (this.unsigned) this.value = value & 0xFFL; 
/*  67 */         truncated = (byte)(int)value;
/*  68 */         this.number = new Byte((byte)(int)value);
/*     */         break;
/*     */       case 2:
/*  71 */         if (this.unsigned) this.value = value & 0xFFFFL; 
/*  72 */         truncated = (short)(int)value;
/*  73 */         this.number = new Short((short)(int)value);
/*     */         break;
/*     */       case 4:
/*  76 */         if (this.unsigned) this.value = value & 0xFFFFFFFFL; 
/*  77 */         truncated = (int)value;
/*  78 */         this.number = new Integer((int)value);
/*     */         break;
/*     */       case 8:
/*  81 */         this.number = new Long(value);
/*     */         break;
/*     */       default:
/*  84 */         throw new IllegalArgumentException("Unsupported size: " + this.size);
/*     */     } 
/*  86 */     if (this.size < 8) {
/*  87 */       long mask = (1L << this.size * 8) - 1L ^ 0xFFFFFFFFFFFFFFFFL;
/*  88 */       if ((value < 0L && truncated != value) || (value >= 0L && (mask & value) != 0L))
/*     */       {
/*  90 */         throw new IllegalArgumentException("Argument value 0x" + 
/*  91 */             Long.toHexString(value) + " exceeds native capacity (" + this.size + " bytes) mask=0x" + 
/*  92 */             Long.toHexString(mask));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  99 */   public Object toNative() { return this.number; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object fromNative(Object nativeValue, FromNativeContext context) {
/* 106 */     long value = (nativeValue == null) ? 0L : ((Number)nativeValue).longValue();
/*     */     try {
/* 108 */       IntegerType number = (IntegerType)getClass().newInstance();
/* 109 */       number.setValue(value);
/* 110 */       return number;
/*     */     }
/* 112 */     catch (InstantiationException e) {
/* 113 */       throw new IllegalArgumentException("Can't instantiate " + 
/* 114 */           getClass());
/*     */     }
/* 116 */     catch (IllegalAccessException e) {
/* 117 */       throw new IllegalArgumentException("Not allowed to instantiate " + 
/* 118 */           getClass());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 124 */   public Class nativeType() { return this.number.getClass(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 129 */   public int intValue() { return (int)this.value; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public long longValue() { return this.value; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 139 */   public float floatValue() { return this.number.floatValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 144 */   public double doubleValue() { return this.number.doubleValue(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object rhs) {
/* 149 */     return (rhs instanceof IntegerType && this.number
/* 150 */       .equals(((IntegerType)rhs).number));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 155 */   public String toString() { return this.number.toString(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public int hashCode() { return this.number.hashCode(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends IntegerType> int compare(T v1, T v2) {
/* 178 */     if (v1 == v2)
/* 179 */       return 0; 
/* 180 */     if (v1 == null)
/* 181 */       return 1; 
/* 182 */     if (v2 == null) {
/* 183 */       return -1;
/*     */     }
/* 185 */     return compare(v1.longValue(), v2.longValue());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int compare(IntegerType v1, long v2) {
/* 201 */     if (v1 == null) {
/* 202 */       return 1;
/*     */     }
/* 204 */     return compare(v1.longValue(), v2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int compare(long v1, long v2) {
/* 210 */     if (v1 == v2)
/* 211 */       return 0; 
/* 212 */     if (v1 < v2) {
/* 213 */       return -1;
/*     */     }
/* 215 */     return 1;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\IntegerType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */