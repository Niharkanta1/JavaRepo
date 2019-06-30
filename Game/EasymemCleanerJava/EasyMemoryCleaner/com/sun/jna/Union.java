/*     */ package com.sun.jna;
/*     */ 
/*     */ import java.lang.reflect.Field;
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
/*     */ 
/*     */ public abstract class Union
/*     */   extends Structure
/*     */ {
/*     */   private Structure.StructField activeField;
/*     */   
/*     */   protected Union() {}
/*     */   
/*  39 */   protected Union(Pointer p) { super(p); }
/*     */ 
/*     */ 
/*     */   
/*  43 */   protected Union(Pointer p, int alignType) { super(p, alignType); }
/*     */ 
/*     */ 
/*     */   
/*  47 */   protected Union(TypeMapper mapper) { super(mapper); }
/*     */ 
/*     */ 
/*     */   
/*  51 */   protected Union(Pointer p, int alignType, TypeMapper mapper) { super(p, alignType, mapper); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected List getFieldOrder() {
/*  58 */     List flist = getFieldList();
/*  59 */     ArrayList list = new ArrayList();
/*  60 */     for (Iterator i = flist.iterator(); i.hasNext(); ) {
/*  61 */       Field f = (Field)i.next();
/*  62 */       list.add(f.getName());
/*     */     } 
/*  64 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(Class type) {
/*  75 */     ensureAllocated();
/*  76 */     for (Iterator i = fields().values().iterator(); i.hasNext(); ) {
/*  77 */       Structure.StructField f = (Structure.StructField)i.next();
/*  78 */       if (f.type == type) {
/*  79 */         this.activeField = f;
/*     */         return;
/*     */       } 
/*     */     } 
/*  83 */     throw new IllegalArgumentException("No field of type " + type + " in " + this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(String fieldName) {
/*  93 */     ensureAllocated();
/*  94 */     Structure.StructField f = (Structure.StructField)fields().get(fieldName);
/*  95 */     if (f != null) {
/*  96 */       this.activeField = f;
/*     */     } else {
/*     */       
/*  99 */       throw new IllegalArgumentException("No field named " + fieldName + " in " + this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object readField(String fieldName) {
/* 109 */     ensureAllocated();
/* 110 */     setType(fieldName);
/* 111 */     return super.readField(fieldName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeField(String fieldName) {
/* 119 */     ensureAllocated();
/* 120 */     setType(fieldName);
/* 121 */     super.writeField(fieldName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeField(String fieldName, Object value) {
/* 129 */     ensureAllocated();
/* 130 */     setType(fieldName);
/* 131 */     super.writeField(fieldName, value);
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
/*     */   public Object getTypedValue(Class type) {
/* 147 */     ensureAllocated();
/* 148 */     for (Iterator i = fields().values().iterator(); i.hasNext(); ) {
/* 149 */       Structure.StructField f = (Structure.StructField)i.next();
/* 150 */       if (f.type == type) {
/* 151 */         this.activeField = f;
/* 152 */         read();
/* 153 */         return getFieldValue(this.activeField.field);
/*     */       } 
/*     */     } 
/* 156 */     throw new IllegalArgumentException("No field of type " + type + " in " + this);
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
/*     */   public Object setTypedValue(Object object) {
/* 170 */     Structure.StructField f = findField(object.getClass());
/* 171 */     if (f != null) {
/* 172 */       this.activeField = f;
/* 173 */       setFieldValue(f.field, object);
/* 174 */       return this;
/*     */     } 
/* 176 */     throw new IllegalArgumentException("No field of type " + object.getClass() + " in " + this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Structure.StructField findField(Class type) {
/* 185 */     ensureAllocated();
/* 186 */     for (Iterator i = fields().values().iterator(); i.hasNext(); ) {
/* 187 */       Structure.StructField f = (Structure.StructField)i.next();
/* 188 */       if (f.type.isAssignableFrom(type)) {
/* 189 */         return f;
/*     */       }
/*     */     } 
/* 192 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void writeField(Structure.StructField field) {
/* 197 */     if (field == this.activeField) {
/* 198 */       super.writeField(field);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object readField(Structure.StructField field) {
/* 207 */     if (field == this.activeField || (
/* 208 */       !Structure.class.isAssignableFrom(field.type) && 
/* 209 */       !String.class.isAssignableFrom(field.type) && 
/* 210 */       !WString.class.isAssignableFrom(field.type))) {
/* 211 */       return super.readField(field);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 217 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 222 */   protected int getNativeAlignment(Class type, Object value, boolean isFirstElement) { return super.getNativeAlignment(type, value, true); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\Union.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */