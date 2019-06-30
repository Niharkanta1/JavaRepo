/*    */ package me.lemon.lemonware.feature;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import lc.kra.system.keyboard.event.GlobalKeyEvent;
/*    */ import lc.kra.system.mouse.event.GlobalMouseEvent;
/*    */ import me.lemon.lemonware.tf2.LocalPlayer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class Feature
/*    */ {
/*    */   private final String name;
/*    */   private final FeatureType type;
/*    */   private boolean enabled;
/*    */   
/* 20 */   public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */   
/* 24 */   public FeatureType getType() { return this.type; }
/*    */ 
/*    */ 
/*    */   
/* 28 */   public boolean isEnabled() { return this.enabled; } public void setEnabled(boolean enabled) { this.enabled = enabled; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Feature(String name, FeatureType type) {
/* 36 */     this.name = name;
/* 37 */     this.type = type;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void draw2D(LocalPlayer local, Graphics g) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onMousePress(GlobalMouseEvent event) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void onMouseRelease(GlobalMouseEvent event) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void run(LocalPlayer paramLocalPlayer);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void onKeyPress(GlobalKeyEvent paramGlobalKeyEvent);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public abstract void onKeyRelease(GlobalKeyEvent paramGlobalKeyEvent);
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public enum FeatureType
/*    */   {
/* 80 */     AIMBOT("aimbot"),
/* 81 */     VISUAL("visual"),
/* 82 */     OTHER("other");
/*    */     
/*    */     private final String name;
/*    */ 
/*    */     
/* 87 */     public String getName() { return this.name; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 94 */     FeatureType(String name) { this.name = name; }
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\feature\Feature.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */