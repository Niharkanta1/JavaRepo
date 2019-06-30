/*    */ package me.lemon.lemonware.feature;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
/*    */ import me.lemon.lemonware.feature.features.Aimbot;
/*    */ import me.lemon.lemonware.feature.features.Bunnyhop;
/*    */ import me.lemon.lemonware.feature.features.GlowESP;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FeatureManager
/*    */ {
/*    */   private List<Feature> features;
/*    */   
/* 17 */   public List<Feature> getFeatures() { return this.features; }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public FeatureManager() {
/* 23 */     this.features = new ArrayList();
/* 24 */     this.features.addAll(Arrays.asList(new Feature[] {
/* 25 */             new Bunnyhop(), 
/* 26 */             new GlowESP(), 
/* 27 */             new Aimbot()
/*    */           }));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Feature getFeature(String name) {
/* 37 */     for (Feature f : getFeatures()) {
/*    */       
/* 39 */       if (f.getName().equalsIgnoreCase(name))
/*    */       {
/* 41 */         return f;
/*    */       }
/*    */     } 
/*    */     
/* 45 */     return null;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\feature\FeatureManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */