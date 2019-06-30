/*    */ package com.beaudoin.jmm.misc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.util.Arrays;
/*    */ import java.util.Scanner;
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
/*    */ public final class Utils
/*    */ {
/*    */   public static int exec(String... command) {
/*    */     try {
/* 38 */       return Integer.parseInt((new Scanner(Runtime.getRuntime().exec(command).getInputStream())).next());
/* 39 */     } catch (IOException e) {
/* 40 */       throw new RuntimeException("Failed to read output from " + Arrays.toString(command));
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\misc\Utils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */