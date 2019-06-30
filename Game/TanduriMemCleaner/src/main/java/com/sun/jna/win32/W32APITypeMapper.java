/*    */ package com.sun.jna.win32;
/*    */ 
/*    */ import com.sun.jna.DefaultTypeMapper;
/*    */ import com.sun.jna.FromNativeContext;
/*    */ import com.sun.jna.StringArray;
/*    */ import com.sun.jna.ToNativeContext;
/*    */ import com.sun.jna.TypeConverter;
/*    */ import com.sun.jna.TypeMapper;
/*    */ import com.sun.jna.WString;
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
/*    */ public class W32APITypeMapper
/*    */   extends DefaultTypeMapper
/*    */ {
/* 33 */   public static final TypeMapper UNICODE = new W32APITypeMapper(true);
/* 34 */   public static final TypeMapper ASCII = new W32APITypeMapper(false);
/*    */   
/*    */   protected W32APITypeMapper(boolean unicode) {
/* 37 */     if (unicode) {
/* 38 */       TypeConverter stringConverter = new TypeConverter() {
/*    */           public Object toNative(Object value, ToNativeContext context) {
/* 40 */             if (value == null)
/* 41 */               return null; 
/* 42 */             if (value instanceof String[]) {
/* 43 */               return new StringArray((String[])value, true);
/*    */             }
/* 45 */             return new WString(value.toString());
/*    */           }
/*    */           public Object fromNative(Object value, FromNativeContext context) {
/* 48 */             if (value == null)
/* 49 */               return null; 
/* 50 */             return value.toString();
/*    */           }
/*    */           
/* 53 */           public Class nativeType() { return WString.class; }
/*    */         
/*    */         };
/* 56 */       addTypeConverter(String.class, stringConverter);
/* 57 */       addToNativeConverter(String[].class, stringConverter);
/*    */     } 
/* 59 */     TypeConverter booleanConverter = new TypeConverter()
/*    */       {
/* 61 */         public Object toNative(Object value, ToNativeContext context) { return new Integer(Boolean.TRUE.equals(value) ? 1 : 0); }
/*    */ 
/*    */         
/* 64 */         public Object fromNative(Object value, FromNativeContext context) { return (((Integer)value).intValue() != 0) ? Boolean.TRUE : Boolean.FALSE; }
/*    */ 
/*    */         
/*    */         public Class nativeType() {
/* 68 */           return Integer.class;
/*    */         }
/*    */       };
/* 71 */     addTypeConverter(Boolean.class, booleanConverter);
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\win32\W32APITypeMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */