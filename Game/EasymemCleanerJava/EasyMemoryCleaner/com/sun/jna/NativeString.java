/*     */ package com.sun.jna;
/*     */ 
/*     */ import java.nio.CharBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NativeString
/*     */   implements CharSequence, Comparable
/*     */ {
/*     */   static final String WIDE_STRING = "--WIDE-STRING--";
/*     */   private Pointer pointer;
/*     */   private String encoding;
/*     */   
/*     */   private class StringMemory
/*     */     extends Memory
/*     */   {
/*  31 */     public StringMemory(long size) { super(size); }
/*     */     
/*  33 */     public String toString() { return NativeString.this.toString(); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public NativeString(String string) { this(string, Native.getDefaultStringEncoding()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public NativeString(String string, boolean wide) { this(string, wide ? "--WIDE-STRING--" : Native.getDefaultStringEncoding()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public NativeString(WString string) { this(string.toString(), "--WIDE-STRING--"); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NativeString(String string, String encoding) {
/*  67 */     if (string == null) {
/*  68 */       throw new NullPointerException("String must not be null");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  73 */     this.encoding = encoding;
/*  74 */     if (this.encoding == "--WIDE-STRING--") {
/*  75 */       int len = (string.length() + 1) * Native.WCHAR_SIZE;
/*  76 */       this.pointer = new StringMemory(len);
/*  77 */       this.pointer.setWideString(0L, string);
/*     */     } else {
/*     */       
/*  80 */       byte[] data = Native.getBytes(string, encoding);
/*  81 */       this.pointer = new StringMemory((data.length + 1));
/*  82 */       this.pointer.write(0L, data, 0, data.length);
/*  83 */       this.pointer.setByte(data.length, (byte)0);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*  88 */   public int hashCode() { return toString().hashCode(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object other) {
/*  93 */     if (other instanceof CharSequence) {
/*  94 */       return (compareTo(other) == 0);
/*     */     }
/*  96 */     return false;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 100 */     boolean wide = (this.encoding == "--WIDE-STRING--");
/* 101 */     s = wide ? "const wchar_t*" : "const char*";
/* 102 */     return s + "(" + (wide ? this.pointer.getWideString(0L) : this.pointer.getString(0L, this.encoding)) + ")";
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 107 */   public Pointer getPointer() { return this.pointer; }
/*     */ 
/*     */ 
/*     */   
/* 111 */   public char charAt(int index) { return toString().charAt(index); }
/*     */ 
/*     */ 
/*     */   
/* 115 */   public int length() { return toString().length(); }
/*     */ 
/*     */ 
/*     */   
/* 119 */   public CharSequence subSequence(int start, int end) { return CharBuffer.wrap(toString()).subSequence(start, end); }
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareTo(Object other) {
/* 124 */     if (other == null) {
/* 125 */       return 1;
/*     */     }
/* 127 */     return toString().compareTo(other.toString());
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\NativeString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */