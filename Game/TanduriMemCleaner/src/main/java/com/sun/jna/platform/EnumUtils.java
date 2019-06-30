/*     */ package com.sun.jna.platform;
/*     */ 
/*     */ import com.sun.jna.platform.win32.FlagEnum;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
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
/*     */ public class EnumUtils
/*     */ {
/*     */   public static final int UNINITIALIZED = -1;
/*     */   
/*     */   public static <E extends Enum<E>> int toInteger(E val) {
/*  43 */     E[] vals = (E[])(Enum[])val.getClass().getEnumConstants();
/*     */     
/*  45 */     for (int idx = 0; idx < vals.length; idx++) {
/*     */       
/*  47 */       if (vals[idx] == val) {
/*  48 */         return idx;
/*     */       }
/*     */     } 
/*  51 */     throw new IllegalArgumentException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <E extends Enum<E>> E fromInteger(int idx, Class<E> clazz) {
/*  61 */     if (idx == -1) {
/*  62 */       return null;
/*     */     }
/*  64 */     E[] vals = (E[])(Enum[])clazz.getEnumConstants();
/*  65 */     return vals[idx];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends FlagEnum> Set<T> setFromInteger(int flags, Class<T> clazz) {
/*  75 */     T[] vals = (T[])(FlagEnum[])clazz.getEnumConstants();
/*  76 */     Set<T> result = new HashSet<T>();
/*     */     
/*  78 */     for (T val : vals) {
/*     */       
/*  80 */       if ((flags & val.getFlag()) != 0)
/*     */       {
/*  82 */         result.add(val);
/*     */       }
/*     */     } 
/*     */     
/*  86 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T extends FlagEnum> int setToInteger(Set<T> set) {
/*  94 */     int sum = 0;
/*     */     
/*  96 */     for (Iterator iterator = set.iterator(); iterator.hasNext(); ) { T t = (T)(FlagEnum)iterator.next();
/*     */       
/*  98 */       sum |= t.getFlag(); }
/*     */ 
/*     */     
/* 101 */     return sum;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\EnumUtils.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */