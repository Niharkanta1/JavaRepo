/*     */ package me.lemon.lemonware.feature.features;
/*     */ 
/*     */ import java.awt.AWTException;
/*     */ import java.awt.Robot;
/*     */ import lc.kra.system.keyboard.event.GlobalKeyEvent;
/*     */ import lc.kra.system.mouse.event.GlobalMouseEvent;
/*     */ import me.lemon.lemonware.Main;
/*     */ import me.lemon.lemonware.feature.Feature;
/*     */ import me.lemon.lemonware.tf2.Entity;
/*     */ import me.lemon.lemonware.tf2.LocalPlayer;
/*     */ import me.lemon.lemonware.utils.EnumBone;
/*     */ import me.lemon.lemonware.utils.vector.Vector3f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Aimbot
/*     */   extends Feature
/*     */ {
/*     */   public boolean doAim;
/*     */   public boolean setupClick;
/*     */   public boolean snapBack;
/*     */   public Robot r;
/*     */   public Vector3f oldAngles;
/*     */   public int ticks;
/*     */   
/*     */   public Aimbot() {
/*  30 */     super("aimbot", Feature.FeatureType.AIMBOT);
/*     */     try {
/*  32 */       this.r = new Robot();
/*  33 */     } catch (AWTException aWTException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void run(LocalPlayer local) {
/*  40 */     this.ticks++;
/*     */     
/*  42 */     if (this.snapBack && this.ticks > 30) {
/*     */       
/*  44 */       local.setViewAngles(this.oldAngles);
/*     */       
/*  46 */       this.snapBack = false;
/*     */     }
/*  48 */     else if (!this.snapBack && this.ticks > 30) {
/*  49 */       this.ticks = 0;
/*     */     } 
/*     */     
/*  52 */     if (this.setupClick) {
/*     */       
/*  54 */       this.oldAngles = local.getViewAngles();
/*     */ 
/*     */       
/*  57 */       Entity target = getClosestToCrosshair(local);
/*     */       
/*  59 */       if (target != null) {
/*     */         
/*  61 */         int bone = ((EnumBone)(Main.INSTANCE.getGui()).bone.getSelectedItem()).getBoneID();
/*  62 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(target.getAddress() + 5452L) == 4L) {
/*  63 */           bone = 16;
/*     */         }
/*  65 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(target.getAddress() + 5452L) == 9L) {
/*  66 */           bone = 8;
/*     */         }
/*  68 */         Vector3f angles = getRotations(local, target, local.getBonePos(target, bone));
/*     */         
/*  70 */         local.setViewAngles(angles);
/*  71 */         this.snapBack = true;
/*  72 */         this.ticks = 0;
/*     */       } 
/*     */       
/*  75 */       this.setupClick = false;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  81 */     if (this.doAim) {
/*     */       
/*  83 */       Entity target = getClosestToCrosshair(local);
/*     */       
/*  85 */       if (target != null) {
/*     */         
/*  87 */         int bone = ((EnumBone)(Main.INSTANCE.getGui()).bone.getSelectedItem()).getBoneID();
/*  88 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(target.getAddress() + 5452L) == 4L) {
/*  89 */           bone = 16;
/*     */         }
/*  91 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(target.getAddress() + 5452L) == 9L) {
/*  92 */           bone = 8;
/*     */         }
/*  94 */         Vector3f angles = getRotations(local, target, local.getBonePos(target, bone));
/*     */         
/*  96 */         local.setViewAngles(angles);
/*     */         
/*  98 */         if (local.isCrosshairOverPlayer() && (Main.INSTANCE.getGui()).autoShoot.isSelected()) {
/*     */           
/* 100 */           this.r.mousePress(16);
/* 101 */           this.r.mouseRelease(16);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void onKeyPress(GlobalKeyEvent event) {
/* 109 */     if ((Main.INSTANCE.getGui()).aimkey.getSelectedItem() != "Mouse1" && 
/* 110 */       event.getKeyChar() == ((String)(Main.INSTANCE.getGui()).aimkey.getSelectedItem()).toLowerCase().charAt(0)) {
/* 111 */       this.doAim = true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onKeyRelease(GlobalKeyEvent event) {
/* 118 */     if ((Main.INSTANCE.getGui()).aimkey.getSelectedItem() != "Mouse1" && 
/* 119 */       event.getKeyChar() == ((String)(Main.INSTANCE.getGui()).aimkey.getSelectedItem()).toLowerCase().charAt(0)) {
/* 120 */       this.doAim = false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void onMousePress(GlobalMouseEvent event) {
/* 127 */     if ((Main.INSTANCE.getGui()).aimkey.getSelectedItem() == "Mouse1" && Main.INSTANCE.getEntityList().getLocalPlayer() != null && 
/* 128 */       event.getButton() == 1) {
/* 129 */       this.setupClick = true;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Entity getClosestToCrosshair(LocalPlayer local) {
/* 135 */     Entity entityaaa = null;
/* 136 */     float angle = (Main.INSTANCE.getGui()).fov.getValue();
/* 137 */     for (Entity entity : Main.INSTANCE.getEntityList().getEntities()) {
/* 138 */       if (entity != local && entity.getTeam() != local.getTeam()) {
/* 139 */         int bone = ((EnumBone)(Main.INSTANCE.getGui()).bone.getSelectedItem()).getBoneID();
/* 140 */         String lol = "this is a super gay fix for demoman and engineer head bone ids being different than everyone else";
/* 141 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(entity.getAddress() + 5452L) == 4L) {
/* 142 */           bone = 16;
/*     */         }
/* 144 */         if (bone == 6 && Main.INSTANCE.getTF2Process().readUnsignedInt(entity.getAddress() + 5452L) == 9L) {
/* 145 */           bone = 8;
/*     */         }
/* 147 */         Vector3f rots = getRotations(local, entity, local.getBonePos(entity, bone));
/* 148 */         float yawDifference = angleDifference((local.getViewAngles()).y, rots.y);
/* 149 */         float pitchDifference = angleDifference((local.getViewAngles()).x, rots.x);
/* 150 */         if (yawDifference <= angle || pitchDifference <= angle) {
/* 151 */           float angleDif = (yawDifference + pitchDifference) / 2.0F;
/* 152 */           if (angleDif <= angle) {
/* 153 */             angle = yawDifference;
/* 154 */             entityaaa = entity;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/* 159 */     return entityaaa;
/*     */   }
/*     */ 
/*     */   
/* 163 */   public float angleDifference(float ang1, float ang2) { return Math.abs(((ang1 - ang2 + 180.0F) % 360.0F + 360.0F) % 360.0F - 180.0F); }
/*     */ 
/*     */ 
/*     */   
/* 167 */   public float angleDifferenceNonAbs(float ang1, float ang2) { return ((ang1 - ang2 + 180.0F) % 360.0F + 360.0F) % 360.0F - 180.0F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3f normalizeRot(Vector3f angle) {
/* 176 */     if (angle.x > 89.0F && angle.y <= 180.0F) {
/* 177 */       angle.x = 89.0F;
/*     */     }
/* 179 */     if (angle.y > 180.0F) {
/* 180 */       angle.y -= 360.0F;
/*     */     }
/* 182 */     if (angle.x < -89.0F) {
/* 183 */       angle.x = -89.0F;
/*     */     }
/* 185 */     if (angle.y > 180.0F) {
/* 186 */       angle.y -= 360.0F;
/*     */     }
/* 188 */     if (angle.y < -180.0F) {
/* 189 */       angle.y += 360.0F;
/*     */     }
/* 191 */     if (angle.z != 0.0F) {
/* 192 */       angle.z = 0.0F;
/*     */     }
/* 194 */     return angle;
/*     */   }
/*     */   
/*     */   public Vector3f getRotations(LocalPlayer player, Entity e, Vector3f bonePos) {
/* 198 */     Vector3f angles = new Vector3f();
/* 199 */     float x = (player.getPos()).x - bonePos.x + 0.02F * (e.getVelocity()).x;
/* 200 */     float y = (player.getPos()).z + (player.getViewOffsets()).z - bonePos.z + 0.02F * (e.getVelocity()).z - 3.0F;
/* 201 */     float z = (player.getPos()).y - bonePos.y + 0.02F * (e.getVelocity()).y;
/* 202 */     double dist = Math.sqrt((x * x + z * z));
/* 203 */     angles.y = (float)(Math.atan(y / dist) * 57.29577951308232D);
/* 204 */     angles.x = (float)(Math.atan((z / x)) * 57.29577951308232D);
/* 205 */     angles.x += ((x >= 0.0F) ? '´' : false);
/*     */     
/* 207 */     return angles;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\feature\features\Aimbot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */