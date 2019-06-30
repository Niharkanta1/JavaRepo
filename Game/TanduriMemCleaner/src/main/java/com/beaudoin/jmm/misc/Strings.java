/*    */ package com.beaudoin.jmm.misc;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
/*    */ import net.openhft.hashing.LongHashFunction;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class Strings
/*    */ {
/* 37 */   private static Map<Long, String> map = new HashMap('䉖');
/*    */   
/*    */   public static String transform(byte[] bytes) {
/* 40 */     long hash = LongHashFunction.xx_r39().hashBytes(bytes);
/* 41 */     if (map.containsKey(Long.valueOf(hash))) {
/* 42 */       return (String)map.get(Long.valueOf(hash));
/*    */     }
/* 44 */     for (int i = 0; i < bytes.length; i++) {
/* 45 */       if (bytes[i] == 0) {
/* 46 */         bytes[i] = 32;
/*    */       }
/*    */     } 
/* 49 */     String string = (new String(bytes)).split(" ")[0].trim().intern();
/* 50 */     map.put(Long.valueOf(hash), string);
/* 51 */     return string;
/*    */   }
/*    */ 
/*    */   
/* 55 */   public static String hex(int value) { return "0x" + Integer.toHexString(value).toUpperCase(); }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\misc\Strings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */