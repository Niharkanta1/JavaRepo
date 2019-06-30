/*     */ package me.lemon.lemonware;
/*     */ 
/*     */ import com.beaudoin.jmm.process.Module;
/*     */ import com.beaudoin.jmm.process.NativeProcess;
/*     */ import java.util.Arrays;
/*     */ import lc.kra.system.keyboard.GlobalKeyboardHook;
/*     */ import lc.kra.system.keyboard.event.GlobalKeyAdapter;
/*     */ import lc.kra.system.keyboard.event.GlobalKeyEvent;
/*     */ import lc.kra.system.mouse.GlobalMouseHook;
/*     */ import lc.kra.system.mouse.event.GlobalMouseAdapter;
/*     */ import lc.kra.system.mouse.event.GlobalMouseEvent;
/*     */ import me.lemon.lemonware.feature.Feature;
/*     */ import me.lemon.lemonware.feature.FeatureManager;
/*     */ import me.lemon.lemonware.memory.Memory;
/*     */ import me.lemon.lemonware.memory.Offset;
/*     */ import me.lemon.lemonware.tf2.Entities;
/*     */ import me.lemon.lemonware.tf2.LocalPlayer;
/*     */ import me.lemon.lemonware.utils.GlowUtil;
/*     */ import me.lemon.lemonware.utils.Gui;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public static enum Main
/*     */ {
/*  30 */   INSTANCE;
/*     */   
/*     */   public long lastUpdate;
/*     */   
/*     */   private Gui gui;
/*     */   private Entities entityList;
/*     */   private FeatureManager featureManager;
/*     */   
/*  38 */   public NativeProcess getTF2Process() { return this.TF2Process; }
/*     */   private Memory memory; private Module client;
/*     */   private Module engine;
/*     */   private NativeProcess TF2Process;
/*     */   
/*  43 */   public Module getEngine() { return this.engine; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   public Module getClient() { return this.client; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   public Memory getMemory() { return this.memory; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   public FeatureManager getFeatureManager() { return this.featureManager; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   public Entities getEntityList() { return this.entityList; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   public Gui getGui() { return this.gui; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(String[] args) {
/*  78 */     INSTANCE.lastUpdate = System.currentTimeMillis();
/*     */     
/*  80 */     System.out.println("Searching for TF2 process...");
/*     */     
/*  82 */     INSTANCE.TF2Process = NativeProcess.byName("hl2.exe");
/*  83 */     System.out.println("Found TF2, (id: " + INSTANCE.getTF2Process().id() + "), Searching for Engine DLL...");
/*     */     
/*  85 */     INSTANCE.engine = INSTANCE.getTF2Process().findModule("engine.dll");
/*  86 */     System.out.println("Found Engine DLL, (id: " + INSTANCE.getEngine().address() + "), Searching for Client DLL...");
/*     */     
/*  88 */     INSTANCE.client = INSTANCE.getTF2Process().findModule("client.dll");
/*  89 */     System.out.println("Found Client DLL, (id: " + INSTANCE.getClient().address() + ")");
/*     */ 
/*     */     
/*  92 */     INSTANCE.memory = new Memory()
/*     */       {
/*     */         public void addOffsets()
/*     */         {
/*  96 */           System.out.println("Adding Offsets...");
/*  97 */           getOffsets().addAll(Arrays.asList(new Offset[] { 
/*  98 */                   new Offset("m_dwEntityList", 12852508), 
/*  99 */                   new Offset("m_dwLocalPlayer", 12796432), 
/* 100 */                   new Offset("m_dwBoneMatrix", 'ְ'), 
/* 101 */                   new Offset("m_dwViewAngles", 4805732), 
/* 102 */                   new Offset("m_dwGlowObject", 12577276), 
/* 103 */                   new Offset("m_dwViewMatrix", 5912744), 
/* 104 */                   new Offset("m_iTeamNum", '°'), 
/* 105 */                   new Offset("m_bDormant", 'ƪ'), 
/* 106 */                   new Offset("m_vecViewOffset", 'ü'), 
/* 107 */                   new Offset("m_fFlags", 'ͼ'), 
/* 108 */                   new Offset("m_iHealth", '¨'), 
/* 109 */                   new Offset("m_vecVelocity", 'Ġ'), 
/* 110 */                   new Offset("m_vecOrigin", 'ͤ'), 
/* 111 */                   new Offset("m_bGlowEnabled", 'ල'), 
/* 112 */                   new Offset("m_iCrosshairID", '᝼'), 
/* 113 */                   new Offset("m_iCloaked", 'ᦸ') }));
/*     */         }
/*     */       };
/*     */ 
/*     */ 
/*     */     
/* 119 */     System.out.println("Initializing cheat features...");
/* 120 */     INSTANCE.featureManager = new FeatureManager();
/*     */ 
/*     */     
/* 123 */     System.out.println("Creating entity list...");
/* 124 */     INSTANCE.entityList = new Entities();
/*     */ 
/*     */     
/* 127 */     System.out.println("Creating GUI...");
/* 128 */     INSTANCE.gui = new Gui();
/*     */ 
/*     */     
/* 131 */     System.out.println("Creating a keyboard hook...");
/* 132 */     GlobalKeyboardHook keyboard = new GlobalKeyboardHook(true);
/*     */ 
/*     */     
/* 135 */     System.out.println("Adding key listeners...");
/* 136 */     keyboard.addKeyListener(new GlobalKeyAdapter()
/*     */         {
/*     */           
/*     */           public void keyPressed(GlobalKeyEvent event)
/*     */           {
/* 141 */             Main.INSTANCE.getFeatureManager().getFeatures().stream().filter(Feature::isEnabled).forEach(feature -> feature.onKeyPress(param1GlobalKeyEvent));
/*     */           }
/*     */ 
/*     */ 
/*     */           
/*     */           public void keyReleased(GlobalKeyEvent event) {
/* 147 */             Main.INSTANCE.getFeatureManager().getFeatures().stream().filter(Feature::isEnabled).forEach(feature -> feature.onKeyRelease(param1GlobalKeyEvent));
/*     */           }
/*     */         });
/*     */ 
/*     */     
/* 152 */     System.out.println("Creating a mouse hook...");
/* 153 */     GlobalMouseHook mouse = new GlobalMouseHook();
/*     */     
/* 155 */     System.out.println("Adding mouse listener...");
/* 156 */     mouse.addMouseListener(new GlobalMouseAdapter()
/*     */         {
/*     */           
/*     */           public void mousePressed(GlobalMouseEvent event)
/*     */           {
/* 161 */             LocalPlayer local = Main.INSTANCE.getEntityList().getLocalPlayer();
/* 162 */             if (local != null && local.getHp() > 0L) {
/* 163 */               Main.INSTANCE.getFeatureManager().getFeatures().stream().filter(Feature::isEnabled).forEach(feature -> feature.onMousePress(param1GlobalMouseEvent));
/*     */             }
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseReleased(GlobalMouseEvent event) {
/* 169 */             LocalPlayer local = Main.INSTANCE.getEntityList().getLocalPlayer();
/* 170 */             if (local != null && local.getHp() > 0L) {
/* 171 */               Main.INSTANCE.getFeatureManager().getFeatures().stream().filter(Feature::isEnabled).forEach(feature -> feature.onMouseRelease(param1GlobalMouseEvent));
/*     */             }
/*     */           }
/*     */         });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 180 */     System.out.println("Cheat started successfully!");
/*     */     
/*     */     while (true) {
/* 183 */       INSTANCE.getEntityList().findEntities();
/* 184 */       LocalPlayer local = INSTANCE.getEntityList().getLocalPlayer();
/* 185 */       if (local != null && local.getHp() > 0L) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 190 */         INSTANCE.getFeatureManager().getFeatures().stream().filter(Feature::isEnabled).forEach(feature -> feature.run(paramLocalPlayer));
/* 191 */         GlowUtil.doGlow(local);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonware\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */