/*    */ package me.lemon.lemonware.tf2;
/*    */ 
/*    */ import me.lemon.lemonware.Main;
/*    */ import me.lemon.lemonware.utils.vector.Vector3f;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LocalPlayer
/*    */   extends Entity
/*    */ {
/*    */   private long crosshair;
/*    */   
/* 17 */   public long getCrosshair() { return this.crosshair; }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 24 */   public LocalPlayer(long adress, long index) { super(adress, index); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void update() {
/* 32 */     super.update();
/*    */ 
/*    */     
/* 35 */     this.crosshair = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_iCrosshairID").getAddress());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector3f normalizeAngle(Vector3f angle) {
/* 44 */     if (angle.y > 89.0F && angle.x <= 180.0F) {
/* 45 */       angle.y = 89.0F;
/*    */     }
/* 47 */     if (angle.x > 180.0F) {
/* 48 */       angle.x -= 360.0F;
/*    */     }
/* 50 */     if (angle.y < -89.0F) {
/* 51 */       angle.y = -89.0F;
/*    */     }
/* 53 */     if (angle.x > 180.0F) {
/* 54 */       angle.x -= 360.0F;
/*    */     }
/* 56 */     if (angle.x < -180.0F) {
/* 57 */       angle.x += 360.0F;
/*    */     }
/* 59 */     if (angle.z != 0.0F) {
/* 60 */       angle.z = 0.0F;
/*    */     }
/* 62 */     return angle;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 70 */   public boolean isCrosshairOverPlayer() { return (this.crosshair > 0L && this.crosshair < 64L); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector3f setViewAngles(Vector3f vec) {
/* 79 */     normalizeAngle(vec);
/* 80 */     Main.INSTANCE.getEngine().writeFloat(Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress(), vec.getY());
/* 81 */     Main.INSTANCE.getEngine().writeFloat((Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress() + 4), vec.getX());
/* 82 */     return vec;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Vector3f setViewAnglesNoClamp(Vector3f vec) {
/* 91 */     Main.INSTANCE.getEngine().writeFloat(Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress(), vec.getY());
/* 92 */     Main.INSTANCE.getEngine().writeFloat((Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress() + 4), vec.getX());
/* 93 */     Main.INSTANCE.getEngine().writeFloat((Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress() + 8), vec.getZ());
/* 94 */     return vec;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\tf2\LocalPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */