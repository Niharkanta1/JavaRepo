/*     */ package me.lemon.lemonware.tf2;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import me.lemon.lemonware.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Entities
/*     */ {
/*  18 */   public List<Entity> getEntities() { return this.entities; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  30 */   private List<Entity> entities = new CopyOnWriteArrayList();
/*  31 */   private long lastClear = System.currentTimeMillis();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void findEntities() {
/*  38 */     if (System.currentTimeMillis() - this.lastClear >= 5000L) {
/*  39 */       getEntities().clear();
/*  40 */       this.lastClear = System.currentTimeMillis();
/*     */     } 
/*     */     
/*  43 */     long localPlayer = Main.INSTANCE.getClient().readUnsignedInt(Main.INSTANCE.getMemory().getOffset("m_dwLocalPlayer").getAddress());
/*     */     
/*  45 */     if (localPlayer > 0L) {
/*     */       
/*  47 */       for (int i = 0; i < 32; i++) {
/*  48 */         long entityBase = Main.INSTANCE.getClient().readUnsignedInt((Main.INSTANCE.getMemory().getOffset("m_dwEntityList").getAddress() + i * 16));
/*  49 */         if (entityBase >= 512L) {
/*  50 */           if (entityBase != localPlayer && entityBase > 0L) {
/*  51 */             long team = Main.INSTANCE.getTF2Process().readUnsignedInt(entityBase + Main.INSTANCE.getMemory().getOffset("m_iTeamNum").getAddress());
/*  52 */             long hp = Main.INSTANCE.getTF2Process().readUnsignedInt(entityBase + Main.INSTANCE.getMemory().getOffset("m_iHealth").getAddress());
/*  53 */             boolean dormant = Main.INSTANCE.getTF2Process().readBoolean(entityBase + Main.INSTANCE.getMemory().getOffset("m_bDormant").getAddress());
/*  54 */             if (!hasEntity(entityBase) && (team == 2L || team == 3L) && hp > 0L && !dormant) {
/*  55 */               add(new Entity(entityBase, i));
/*     */             }
/*  57 */           } else if (entityBase == localPlayer && 
/*  58 */             !hasEntity(entityBase)) {
/*  59 */             add(new LocalPlayer(entityBase, i));
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/*  64 */       for (Entity entity : getEntities()) {
/*  65 */         entity.update();
/*  66 */         if (entity.isDormant() || (entity.getHp() <= 1L && entity != getLocalPlayer()) || (entity.getHp() <= 0L && entity == getLocalPlayer())) {
/*  67 */           remove(entity);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  78 */   public void add(Entity e) { this.entities.add(e); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public void remove(Entity e) { this.entities.remove(e); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasEntity(long address) {
/*  95 */     for (Entity entity : getEntities()) {
/*  96 */       if (entity.getAddress() == address) {
/*  97 */         return true;
/*     */       }
/*     */     } 
/* 100 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntity(long address) {
/* 107 */     for (Entity entity : getEntities()) {
/* 108 */       if (entity.getAddress() == address) {
/* 109 */         return entity;
/*     */       }
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Entity getEntityIndex(long index) {
/* 119 */     for (Entity entity : getEntities()) {
/* 120 */       if (entity.getIndex() == index) {
/* 121 */         return entity;
/*     */       }
/*     */     } 
/* 124 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public LocalPlayer getLocalPlayer() {
/* 132 */     for (Entity entity : getEntities()) {
/* 133 */       if (entity instanceof LocalPlayer) {
/* 134 */         return (LocalPlayer)entity;
/*     */       }
/*     */     } 
/* 137 */     return null;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\tf2\Entities.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */