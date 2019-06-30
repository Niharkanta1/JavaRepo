/*    */ package com.sun.jna.platform;
/*    */ 
/*    */ import com.sun.jna.FromNativeContext;
/*    */ import com.sun.jna.ToNativeContext;
/*    */ import com.sun.jna.TypeConverter;
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
/*    */ public class EnumConverter<T extends Enum<T>>
/*    */   extends Object
/*    */   implements TypeConverter
/*    */ {
/*    */   private final Class<T> clazz;
/*    */   
/* 38 */   public EnumConverter(Class<T> clazz) { this.clazz = clazz; }
/*    */ 
/*    */ 
/*    */   
/*    */   public T fromNative(Object input, FromNativeContext context) {
/* 43 */     Integer i = (Integer)input;
/*    */     
/* 45 */     T[] vals = (T[])(Enum[])this.clazz.getEnumConstants();
/* 46 */     return vals[i.intValue()];
/*    */   }
/*    */ 
/*    */   
/*    */   public Integer toNative(Object input, ToNativeContext context) {
/* 51 */     T t = (T)(Enum)this.clazz.cast(input);
/*    */     
/* 53 */     return Integer.valueOf(t.ordinal());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 58 */   public Class<Integer> nativeType() { return Integer.class; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\EnumConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */