/*     */ package me.lemon.lemonware.utils;
/*     */ 
/*     */ import javax.swing.DefaultComboBoxModel;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.JCheckBox;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JFrame;
/*     */ import javax.swing.JLabel;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JSlider;
/*     */ import javax.swing.JTabbedPane;
/*     */ import javax.swing.event.ChangeEvent;
/*     */ import javax.swing.event.ChangeListener;
/*     */ import me.lemon.lemonware.Main;
/*     */ import me.lemon.lemonware.feature.Feature;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Gui
/*     */ {
/*     */   private JFrame externalGui;
/*     */   public JSlider fov;
/*     */   public JComboBox aimkey;
/*     */   public JComboBox bone;
/*     */   public JCheckBox smooth;
/*     */   public JCheckBox autoShoot;
/*     */   public JSlider speed;
/*     */   
/*  32 */   public Gui() { initialize(); }
/*     */ 
/*     */   
/*     */   private void initialize() {
/*  36 */     this.externalGui = new JFrame();
/*  37 */     this.externalGui.setResizable(false);
/*  38 */     this.externalGui.setTitle("lemon tf2 cheat Lol XDD");
/*  39 */     this.externalGui.setDefaultCloseOperation(3);
/*  40 */     this.externalGui.setBounds(100, 100, 415, 242);
/*     */     
/*  42 */     JTabbedPane tabbedPane = new JTabbedPane(true);
/*  43 */     GroupLayout groupLayout = new GroupLayout(this.externalGui.getContentPane());
/*  44 */     groupLayout.setHorizontalGroup(
/*  45 */         groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  46 */         .addComponent(tabbedPane, -1, 946, 32767));
/*     */     
/*  48 */     groupLayout.setVerticalGroup(
/*  49 */         groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
/*  50 */         .addComponent(tabbedPane, -1, 555, 32767));
/*     */ 
/*     */     
/*  53 */     JPanel panel = new JPanel();
/*  54 */     tabbedPane.addTab("aimbot", null, panel, null);
/*  55 */     panel.setLayout(null);
/*     */     
/*  57 */     JCheckBox chckbxEnabled = new JCheckBox("enabled");
/*  58 */     chckbxEnabled.setBounds(10, 7, 97, 23);
/*  59 */     chckbxEnabled.addChangeListener(new ChangeListener()
/*     */         {
/*     */           public void stateChanged(ChangeEvent e) {
/*  62 */             Main.INSTANCE.getFeatureManager().getFeature("aimbot").setEnabled(((JCheckBox)e.getSource()).isSelected());
/*     */           }
/*     */         });
/*  65 */     panel.add(chckbxEnabled);
/*     */     
/*  67 */     JLabel lblFov = new JLabel("fov:");
/*  68 */     lblFov.setHorizontalAlignment(2);
/*  69 */     lblFov.setBounds(14, 32, 400, 14);
/*  70 */     panel.add(lblFov);
/*     */     
/*  72 */     this.fov = new JSlider();
/*  73 */     this.fov.setMaximum(360);
/*  74 */     this.fov.setBounds(3, 45, 395, 50);
/*  75 */     this.fov.setMajorTickSpacing(45);
/*  76 */     this.fov.setMinorTickSpacing(5);
/*  77 */     this.fov.setPaintTicks(true);
/*  78 */     this.fov.setPaintLabels(true);
/*  79 */     this.fov.setValue(90);
/*  80 */     panel.add(this.fov);
/*     */     
/*  82 */     JLabel lblKey = new JLabel("key:");
/*  83 */     lblKey.setBounds(14, 99, 46, 14);
/*  84 */     panel.add(lblKey);
/*     */     
/*  86 */     this.aimkey = new JComboBox();
/*  87 */     this.aimkey.setModel(new DefaultComboBoxModel(new String[] { "Mouse1", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z" }));
/*  88 */     this.aimkey.setEditable(true);
/*  89 */     this.aimkey.setSelectedIndex(3);
/*  90 */     this.aimkey.setBounds(12, 117, 80, 20);
/*  91 */     panel.add(this.aimkey);
/*     */     
/*  93 */     this.autoShoot = new JCheckBox("autoshoot");
/*  94 */     this.autoShoot.setBounds(110, 11, 106, 14);
/*  95 */     panel.add(this.autoShoot);
/*     */     
/*  97 */     JLabel lblbn = new JLabel("bone:");
/*  98 */     lblbn.setBounds(14, 99, 46, 14);
/*     */ 
/*     */     
/* 101 */     this.bone = new JComboBox();
/* 102 */     this.bone.setModel(new DefaultComboBoxModel(EnumBone.values()));
/* 103 */     this.bone.setEditable(true);
/* 104 */     this.bone.setSelectedIndex(0);
/* 105 */     this.bone.setBounds(12, 117, 80, 20);
/*     */ 
/*     */     
/* 108 */     JPanel panel_1 = new JPanel();
/* 109 */     tabbedPane.addTab("visuals", null, panel_1, null);
/* 110 */     panel_1.setLayout(null);
/*     */     
/* 112 */     int Lol = 7;
/* 113 */     for (Feature feature : Main.INSTANCE.getFeatureManager().getFeatures()) {
/* 114 */       if (feature.getType() == Feature.FeatureType.VISUAL) {
/* 115 */         JCheckBox checkbox = new JCheckBox(feature.getName());
/* 116 */         checkbox.setBounds(10, Lol, 427, 23);
/* 117 */         checkbox.addChangeListener(new ChangeListener()
/*     */             {
/*     */               public void stateChanged(ChangeEvent e) {
/* 120 */                 feature.setEnabled(((JCheckBox)e.getSource()).isSelected());
/*     */               }
/*     */             });
/* 123 */         Lol += 24;
/* 124 */         panel_1.add(checkbox);
/*     */       } 
/*     */     } 
/*     */     
/* 128 */     JPanel panel_2 = new JPanel();
/* 129 */     tabbedPane.addTab("other", null, panel_2, null);
/* 130 */     panel_2.setLayout(null);
/*     */     
/* 132 */     int Lol2 = 7;
/* 133 */     for (Feature feature : Main.INSTANCE.getFeatureManager().getFeatures()) {
/* 134 */       if (feature.getType() == Feature.FeatureType.OTHER) {
/* 135 */         JCheckBox checkbox = new JCheckBox(feature.getName());
/* 136 */         checkbox.setBounds(10, Lol2, 427, 23);
/* 137 */         checkbox.addChangeListener(new ChangeListener()
/*     */             {
/*     */               public void stateChanged(ChangeEvent e) {
/* 140 */                 feature.setEnabled(((JCheckBox)e.getSource()).isSelected());
/*     */               }
/*     */             });
/* 143 */         Lol2 += 24;
/* 144 */         panel_2.add(checkbox);
/*     */       } 
/*     */     } 
/*     */     
/* 148 */     this.externalGui.getContentPane().setLayout(groupLayout);
/* 149 */     this.externalGui.setVisible(true);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\me\lemon\lemonwar\\utils\Gui.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */