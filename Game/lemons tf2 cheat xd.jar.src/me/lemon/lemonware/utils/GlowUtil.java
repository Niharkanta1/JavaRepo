/*    */ package me.lemon.lemonware.utils;
/*    */ 
/*    */ import me.lemon.lemonware.Main;
/*    */ import me.lemon.lemonware.tf2.Entity;
/*    */ import me.lemon.lemonware.tf2.LocalPlayer;
/*    */ 
/*    */ 
/*    */ public class GlowUtil
/*    */ {
/*    */   public static void doGlow(LocalPlayer local) {
/* 11 */     for (Entity entity : Main.INSTANCE.getEntityList().getEntities()) {
/*    */       
/* 13 */       if (entity != local && !entity.isDormant() && entity.getHp() > 0L)
/*    */       {
/* 15 */         Main.INSTANCE.getTF2Process().writeBoolean(
/* 16 */             entity.getAddress() + Main.INSTANCE.getMemory().getOffset("m_bGlowEnabled").getAddress(), 
/* 17 */             Main.INSTANCE.getFeatureManager().getFeature("glow").isEnabled());
/*    */       }
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonwar\\utils\GlowUtil.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */