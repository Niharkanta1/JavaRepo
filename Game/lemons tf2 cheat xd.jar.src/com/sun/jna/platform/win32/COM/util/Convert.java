/*    */ package com.sun.jna.platform.win32.COM.util;
/*    */ 
/*    */ import com.sun.jna.platform.win32.Variant;
/*    */ import com.sun.jna.platform.win32.WTypes;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import java.lang.reflect.InvocationHandler;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
/*    */ import java.lang.reflect.Proxy;
/*    */ import java.util.Date;
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
/*    */ public class Convert
/*    */ {
/*    */   public static Variant.VARIANT toVariant(Object value) {
/* 28 */     if (value instanceof Boolean)
/* 29 */       return new Variant.VARIANT(((Boolean)value).booleanValue()); 
/* 30 */     if (value instanceof Long)
/* 31 */       return new Variant.VARIANT(new WinDef.LONG(((Long)value).longValue())); 
/* 32 */     if (value instanceof Integer)
/* 33 */       return new Variant.VARIANT(((Integer)value).intValue()); 
/* 34 */     if (value instanceof Short)
/* 35 */       return new Variant.VARIANT(new WinDef.SHORT(((Short)value).shortValue())); 
/* 36 */     if (value instanceof Float)
/* 37 */       return new Variant.VARIANT(((Float)value).floatValue()); 
/* 38 */     if (value instanceof Double)
/* 39 */       return new Variant.VARIANT(((Double)value).doubleValue()); 
/* 40 */     if (value instanceof String)
/* 41 */       return new Variant.VARIANT((String)value); 
/* 42 */     if (value instanceof Date)
/* 43 */       return new Variant.VARIANT((Date)value); 
/* 44 */     if (value instanceof Proxy) {
/* 45 */       InvocationHandler ih = Proxy.getInvocationHandler(value);
/* 46 */       ProxyObject pobj = (ProxyObject)ih;
/* 47 */       return new Variant.VARIANT(pobj.getRawDispatch());
/*    */     } 
/* 49 */     if (value instanceof IComEnum) {
/* 50 */       IComEnum enm = (IComEnum)value;
/* 51 */       return new Variant.VARIANT(new WinDef.LONG(enm.getValue()));
/*    */     } 
/* 53 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public static Object toJavaObject(Variant.VARIANT value) {
/* 58 */     if (null == value) return null; 
/* 59 */     Object vobj = value.getValue();
/* 60 */     if (vobj instanceof WinDef.BOOL)
/* 61 */       return Boolean.valueOf(((WinDef.BOOL)vobj).booleanValue()); 
/* 62 */     if (vobj instanceof WinDef.LONG)
/* 63 */       return Long.valueOf(((WinDef.LONG)vobj).longValue()); 
/* 64 */     if (vobj instanceof WinDef.SHORT)
/* 65 */       return Short.valueOf(((WinDef.SHORT)vobj).shortValue()); 
/* 66 */     if (vobj instanceof WinDef.UINT)
/* 67 */       return Integer.valueOf(((WinDef.UINT)vobj).intValue()); 
/* 68 */     if (vobj instanceof WinDef.WORD)
/* 69 */       return Integer.valueOf(((WinDef.WORD)vobj).intValue()); 
/* 70 */     if (vobj instanceof WTypes.BSTR) {
/* 71 */       return ((WTypes.BSTR)vobj).getValue();
/*    */     }
/* 73 */     return vobj;
/*    */   }
/*    */   
/*    */   public static <T extends IComEnum> T toComEnum(Class<T> enumType, Object value) {
/*    */     
/* 78 */     try { Method m = enumType.getMethod("values", new Class[0]);
/* 79 */       T[] values = (T[])(IComEnum[])m.invoke(null, new Object[0]);
/* 80 */       for (T t : values) {
/* 81 */         if (value.equals(Long.valueOf(t.getValue()))) {
/* 82 */           return t;
/*    */         }
/*    */       }  }
/* 85 */     catch (NoSuchMethodException e) {  }
/* 86 */     catch (IllegalAccessException e) {  }
/* 87 */     catch (IllegalArgumentException e) {  }
/* 88 */     catch (InvocationTargetException e) {}
/*    */     
/* 90 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\CO\\util\Convert.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */