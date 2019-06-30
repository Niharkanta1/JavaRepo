/*    */ package me.lemon.lemonware.feature.features;
/*    */ 
/*    */ import java.awt.AWTException;
/*    */ import java.awt.Robot;
/*    */ import lc.kra.system.keyboard.event.GlobalKeyEvent;
/*    */ import me.lemon.lemonware.feature.Feature;
/*    */ import me.lemon.lemonware.tf2.LocalPlayer;
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
/*    */ public class Bunnyhop
/*    */   extends Feature
/*    */ {
/*    */   private boolean ignoreKey;
/*    */   private boolean spaceDown;
/*    */   private long lastHop;
/*    */   private Robot robot;
/*    */   
/*    */   public Bunnyhop() {
/* 38 */     super("bunnyhop", Feature.FeatureType.OTHER);
/*    */     try {
/* 40 */       this.robot = new Robot();
/* 41 */     } catch (AWTException aWTException) {}
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void run(LocalPlayer local) {
/* 47 */     if (this.spaceDown && local.isOnGround() && System.currentTimeMillis() - this.lastHop >= 50L) {
/*    */       
/* 49 */       this.lastHop = System.currentTimeMillis();
/*    */       
/* 51 */       this.ignoreKey = true;
/*    */       
/* 53 */       this.robot.keyPress(32);
/* 54 */       this.robot.keyRelease(32);
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onKeyPress(GlobalKeyEvent event) {
/* 60 */     if (event.getVirtualKeyCode() == 32 && !this.ignoreKey) {
/*    */       
/* 62 */       this.lastHop = System.currentTimeMillis() + 100L;
/*    */       
/* 64 */       this.spaceDown = true;
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void onKeyRelease(GlobalKeyEvent event) {
/* 70 */     if (event.getVirtualKeyCode() == 32 && !this.ignoreKey) {
/*    */       
/* 72 */       this.spaceDown = false;
/*    */     } else {
/*    */       
/* 75 */       this.ignoreKey = false;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\feature\features\Bunnyhop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */