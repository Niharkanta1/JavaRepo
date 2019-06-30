/*    */ package com.sun.jna;
/*    */ 
/*    */ import java.nio.CharBuffer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class WString
/*    */   implements CharSequence, Comparable
/*    */ {
/*    */   private String string;
/*    */   
/*    */   public WString(String s) {
/* 23 */     if (s == null) throw new NullPointerException("String initializer must be non-null"); 
/* 24 */     this.string = s;
/*    */   }
/*    */   
/* 27 */   public String toString() { return this.string; }
/*    */ 
/*    */   
/* 30 */   public boolean equals(Object o) { return (o instanceof WString && toString().equals(o.toString())); }
/*    */ 
/*    */   
/* 33 */   public int hashCode() { return toString().hashCode(); }
/*    */ 
/*    */   
/* 36 */   public int compareTo(Object o) { return toString().compareTo(o.toString()); }
/*    */ 
/*    */   
/* 39 */   public int length() { return toString().length(); }
/*    */ 
/*    */   
/* 42 */   public char charAt(int index) { return toString().charAt(index); }
/*    */ 
/*    */   
/* 45 */   public CharSequence subSequence(int start, int end) { return CharBuffer.wrap(toString()).subSequence(start, end); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\WString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */