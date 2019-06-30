/*     */ package com.sun.jna;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DefaultTypeMapper
/*     */   implements TypeMapper
/*     */ {
/*     */   private static class Entry
/*     */   {
/*     */     public Class type;
/*     */     public Object converter;
/*     */     
/*     */     public Entry(Class type, Object converter) {
/*  39 */       this.type = type;
/*  40 */       this.converter = converter;
/*     */     }
/*     */   }
/*  43 */   private List toNativeConverters = new ArrayList();
/*  44 */   private List fromNativeConverters = new ArrayList();
/*     */   private Class getAltClass(Class cls) {
/*  46 */     if (cls == Boolean.class) {
/*  47 */       return boolean.class;
/*     */     }
/*  49 */     if (cls == boolean.class) {
/*  50 */       return Boolean.class;
/*     */     }
/*  52 */     if (cls == Byte.class) {
/*  53 */       return byte.class;
/*     */     }
/*  55 */     if (cls == byte.class) {
/*  56 */       return Byte.class;
/*     */     }
/*  58 */     if (cls == Character.class) {
/*  59 */       return char.class;
/*     */     }
/*  61 */     if (cls == char.class) {
/*  62 */       return Character.class;
/*     */     }
/*  64 */     if (cls == Short.class) {
/*  65 */       return short.class;
/*     */     }
/*  67 */     if (cls == short.class) {
/*  68 */       return Short.class;
/*     */     }
/*  70 */     if (cls == Integer.class) {
/*  71 */       return int.class;
/*     */     }
/*  73 */     if (cls == int.class) {
/*  74 */       return Integer.class;
/*     */     }
/*  76 */     if (cls == Long.class) {
/*  77 */       return long.class;
/*     */     }
/*  79 */     if (cls == long.class) {
/*  80 */       return Long.class;
/*     */     }
/*  82 */     if (cls == Float.class) {
/*  83 */       return float.class;
/*     */     }
/*  85 */     if (cls == float.class) {
/*  86 */       return Float.class;
/*     */     }
/*  88 */     if (cls == Double.class) {
/*  89 */       return double.class;
/*     */     }
/*  91 */     if (cls == double.class) {
/*  92 */       return Double.class;
/*     */     }
/*  94 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addToNativeConverter(Class cls, ToNativeConverter converter) {
/* 104 */     this.toNativeConverters.add(new Entry(cls, converter));
/* 105 */     Class alt = getAltClass(cls);
/* 106 */     if (alt != null) {
/* 107 */       this.toNativeConverters.add(new Entry(alt, converter));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addFromNativeConverter(Class cls, FromNativeConverter converter) {
/* 117 */     this.fromNativeConverters.add(new Entry(cls, converter));
/* 118 */     Class alt = getAltClass(cls);
/* 119 */     if (alt != null) {
/* 120 */       this.fromNativeConverters.add(new Entry(alt, converter));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTypeConverter(Class cls, TypeConverter converter) {
/* 130 */     addFromNativeConverter(cls, converter);
/* 131 */     addToNativeConverter(cls, converter);
/*     */   }
/*     */   
/*     */   private Object lookupConverter(Class javaClass, List converters) {
/* 135 */     for (Iterator i = converters.iterator(); i.hasNext(); ) {
/* 136 */       Entry entry = (Entry)i.next();
/* 137 */       if (entry.type.isAssignableFrom(javaClass)) {
/* 138 */         return entry.converter;
/*     */       }
/*     */     } 
/* 141 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 147 */   public FromNativeConverter getFromNativeConverter(Class javaType) { return (FromNativeConverter)lookupConverter(javaType, this.fromNativeConverters); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 153 */   public ToNativeConverter getToNativeConverter(Class javaType) { return (ToNativeConverter)lookupConverter(javaType, this.toNativeConverters); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\DefaultTypeMapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */