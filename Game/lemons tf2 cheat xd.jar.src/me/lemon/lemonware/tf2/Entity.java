/*     */ package me.lemon.lemonware.tf2;
/*     */ 
/*     */ import me.lemon.lemonware.Main;
/*     */ import me.lemon.lemonware.utils.vector.Vector3f;
/*     */ 
/*     */ 
/*     */ public class Entity
/*     */ {
/*     */   private long address;
/*     */   private long index;
/*     */   private Vector3f pos;
/*     */   private Vector3f velocity;
/*     */   private Vector3f viewAngles;
/*     */   private Vector3f viewOffsets;
/*     */   
/*  16 */   public long getAddress() { return this.address; }
/*     */   private long hp; private long flags; private boolean spotted; private boolean dormant; private long team;
/*     */   private long boneMatrix;
/*     */   private long cloak;
/*     */   
/*  21 */   public long getIndex() { return this.index; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  26 */   public Vector3f getPos() { return this.pos; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  31 */   public Vector3f getVelocity() { return this.velocity; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public Vector3f getViewAngles() { return this.viewAngles; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public Vector3f getViewOffsets() { return this.viewOffsets; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public long getHp() { return this.hp; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public long getFlags() { return this.flags; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public boolean isSpotted() { return this.spotted; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public boolean isDormant() { return this.dormant; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public long getTeam() { return this.team; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public long getBoneMatrix() { return this.boneMatrix; }
/*     */   
/*  73 */   public long getCloak() { return this.cloak; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity(long adress, long index) {
/*  80 */     this.address = adress;
/*  81 */     this.index = index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void update() {
/*  89 */     float posX = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecOrigin").getAddress());
/*  90 */     float posY = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecOrigin").getAddress() + 4L);
/*  91 */     float posZ = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecOrigin").getAddress() + 8L);
/*  92 */     this.pos = new Vector3f(posX, posY, posZ);
/*     */ 
/*     */     
/*  95 */     float velX = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecVelocity").getAddress());
/*  96 */     float velY = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecVelocity").getAddress() + 4L);
/*  97 */     float velZ = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecVelocity").getAddress() + 8L);
/*  98 */     this.velocity = new Vector3f(velX, velY, velZ);
/*     */ 
/*     */     
/* 101 */     float pitch = Main.INSTANCE.getEngine().readFloat(Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress());
/* 102 */     float yaw = Main.INSTANCE.getEngine().readFloat((Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress() + 4));
/* 103 */     float roll = Main.INSTANCE.getEngine().readFloat((Main.INSTANCE.getMemory().getOffset("m_dwViewAngles").getAddress() + 8));
/* 104 */     this.viewAngles = new Vector3f(yaw, pitch, roll);
/*     */ 
/*     */     
/* 107 */     float viewOffsetX = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecViewOffset").getAddress());
/* 108 */     float viewOffsetY = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecViewOffset").getAddress() + 4L);
/* 109 */     float viewOffsetZ = Main.INSTANCE.getTF2Process().readFloat(getAddress() + Main.INSTANCE.getMemory().getOffset("m_vecViewOffset").getAddress() + 8L);
/* 110 */     this.viewOffsets = new Vector3f(viewOffsetX, viewOffsetY, viewOffsetZ);
/*     */ 
/*     */     
/* 113 */     this.hp = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_iHealth").getAddress());
/*     */ 
/*     */     
/* 116 */     this.flags = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_fFlags").getAddress());
/*     */ 
/*     */     
/* 119 */     this.dormant = Main.INSTANCE.getTF2Process().readBoolean(getAddress() + Main.INSTANCE.getMemory().getOffset("m_bDormant").getAddress());
/*     */ 
/*     */     
/* 122 */     this.team = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_iTeamNum").getAddress());
/*     */ 
/*     */     
/* 125 */     this.cloak = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_iCloaked").getAddress());
/*     */ 
/*     */     
/* 128 */     this.boneMatrix = Main.INSTANCE.getTF2Process().readUnsignedInt(getAddress() + Main.INSTANCE.getMemory().getOffset("m_dwBoneMatrix").getAddress());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 136 */   public boolean isOnGround() { return (getFlags() == 257L); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector3f getBonePos(Entity entity, int bone) {
/* 146 */     Vector3f bonePos = new Vector3f();
/* 147 */     bonePos.x = Main.INSTANCE.getTF2Process().readFloat(entity.getBoneMatrix() + (48 * bone) + 12L);
/* 148 */     bonePos.y = Main.INSTANCE.getTF2Process().readFloat(entity.getBoneMatrix() + (48 * bone) + 28L);
/* 149 */     bonePos.z = Main.INSTANCE.getTF2Process().readFloat(entity.getBoneMatrix() + (48 * bone) + 44L);
/* 150 */     return bonePos;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\tf2\Entity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */