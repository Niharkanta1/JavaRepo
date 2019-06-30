/*     */ package com.sun.jna.platform.win32.COM.tlb;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbBase;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbCmdlineArgs;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbCoClass;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbConst;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbDispInterface;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbEnum;
/*     */ import com.sun.jna.platform.win32.COM.tlb.imp.TlbInterface;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TlbImp
/*     */   implements TlbConst
/*     */ {
/*     */   private TypeLibUtil typeLibUtil;
/*     */   private File comRootDir;
/*     */   private File outputDir;
/*     */   private TlbCmdlineArgs cmdlineArgs;
/*     */   
/*  56 */   public static void main(String[] args) { new TlbImp(args); }
/*     */ 
/*     */   
/*     */   public TlbImp(String[] args) {
/*  60 */     this.cmdlineArgs = new TlbCmdlineArgs(args);
/*     */     
/*  62 */     if (this.cmdlineArgs.isTlbId()) {
/*  63 */       String clsid = this.cmdlineArgs.getRequiredParam("tlb.id");
/*     */       
/*  65 */       int majorVersion = this.cmdlineArgs.getIntParam("tlb.major.version");
/*     */       
/*  67 */       int minorVersion = this.cmdlineArgs.getIntParam("tlb.minor.version");
/*     */ 
/*     */ 
/*     */       
/*  71 */       this.typeLibUtil = new TypeLibUtil(clsid, majorVersion, minorVersion);
/*     */       
/*  73 */       startCOM2Java();
/*  74 */     } else if (this.cmdlineArgs.isTlbFile()) {
/*  75 */       String file = this.cmdlineArgs.getRequiredParam("tlb.file");
/*     */ 
/*     */       
/*  78 */       this.typeLibUtil = new TypeLibUtil(file);
/*  79 */       startCOM2Java();
/*     */     } else {
/*  81 */       this.cmdlineArgs.showCmdHelp();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void startCOM2Java() {
/*     */     try {
/*  90 */       createDir();
/*     */       
/*  92 */       String bindingMode = this.cmdlineArgs.getBindingMode();
/*     */       
/*  94 */       int typeInfoCount = this.typeLibUtil.getTypeInfoCount();
/*  95 */       for (int i = 0; i < typeInfoCount; i++) {
/*  96 */         OaIdl.TYPEKIND typekind = this.typeLibUtil.getTypeInfoType(i);
/*     */         
/*  98 */         if (typekind.value == 0) {
/*  99 */           createCOMEnum(i, getPackageName(), this.typeLibUtil);
/* 100 */         } else if (typekind.value == 1) {
/* 101 */           logInfo("'TKIND_RECORD' objects are currently not supported!");
/* 102 */         } else if (typekind.value == 2) {
/* 103 */           logInfo("'TKIND_MODULE' objects are currently not supported!");
/* 104 */         } else if (typekind.value == 3) {
/* 105 */           createCOMInterface(i, getPackageName(), this.typeLibUtil);
/*     */         }
/* 107 */         else if (typekind.value == 4) {
/* 108 */           createCOMDispInterface(i, getPackageName(), this.typeLibUtil);
/*     */         }
/* 110 */         else if (typekind.value == 5) {
/* 111 */           createCOMCoClass(i, getPackageName(), this.typeLibUtil, bindingMode);
/*     */         }
/* 113 */         else if (typekind.value == 6) {
/* 114 */           logInfo("'TKIND_ALIAS' objects are currently not supported!");
/* 115 */         } else if (typekind.value == 7) {
/* 116 */           logInfo("'TKIND_UNION' objects are currently not supported!");
/*     */         } 
/*     */       } 
/*     */       
/* 120 */       logInfo(typeInfoCount + " files sucessfully written to: " + this.comRootDir
/* 121 */           .toString());
/* 122 */     } catch (Exception e) {
/* 123 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */   
/*     */   private void createDir() {
/* 128 */     String _outputDir = this.cmdlineArgs.getParam("output.dir");
/*     */     
/* 130 */     String path = "_jnaCOM_" + System.currentTimeMillis() + "\\myPackage\\" + this.typeLibUtil.getName().toLowerCase() + "\\";
/*     */     
/* 132 */     if (_outputDir != null) {
/* 133 */       this.comRootDir = new File(_outputDir + "\\" + path);
/*     */     } else {
/* 135 */       String tmp = System.getProperty("java.io.tmpdir");
/* 136 */       this.comRootDir = new File(tmp + "\\" + path);
/*     */     } 
/*     */     
/* 139 */     if (this.comRootDir.exists()) {
/* 140 */       this.comRootDir.delete();
/*     */     }
/* 142 */     if (this.comRootDir.mkdirs()) {
/* 143 */       logInfo("Output directory sucessfully created.");
/*     */     } else {
/* 145 */       throw new FileNotFoundException("Output directory NOT sucessfully created to: " + this.comRootDir
/*     */           
/* 147 */           .toString());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 152 */   private String getPackageName() { return "myPackage." + this.typeLibUtil.getName().toLowerCase(); }
/*     */ 
/*     */   
/*     */   private void writeTextFile(String filename, String str) throws IOException {
/* 156 */     String file = this.comRootDir + File.separator + filename;
/* 157 */     BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
/*     */     
/* 159 */     bos.write(str.getBytes());
/* 160 */     bos.close();
/*     */   }
/*     */   
/*     */   private void writeTlbClass(TlbBase tlbBase) throws IOException {
/* 164 */     StringBuffer classBuffer = tlbBase.getClassBuffer();
/* 165 */     writeTextFile(tlbBase.getFilename(), classBuffer.toString());
/*     */   }
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
/*     */   private void createCOMEnum(int index, String packagename, TypeLibUtil typeLibUtil) throws IOException {
/* 179 */     TlbEnum tlbEnum = new TlbEnum(index, packagename, typeLibUtil);
/* 180 */     writeTlbClass(tlbEnum);
/*     */   }
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
/*     */   private void createCOMInterface(int index, String packagename, TypeLibUtil typeLibUtil) throws IOException {
/* 194 */     TlbInterface tlbInterface = new TlbInterface(index, packagename, typeLibUtil);
/*     */     
/* 196 */     writeTlbClass(tlbInterface);
/*     */   }
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
/*     */   private void createCOMDispInterface(int index, String packagename, TypeLibUtil typeLibUtil) throws IOException {
/* 210 */     TlbDispInterface tlbDispatch = new TlbDispInterface(index, packagename, typeLibUtil);
/*     */     
/* 212 */     writeTlbClass(tlbDispatch);
/*     */   }
/*     */ 
/*     */   
/*     */   private void createCOMCoClass(int index, String packagename, TypeLibUtil typeLibUtil, String bindingMode) throws IOException {
/* 217 */     TlbCoClass tlbCoClass = new TlbCoClass(index, getPackageName(), typeLibUtil, bindingMode);
/*     */     
/* 219 */     writeTlbClass(tlbCoClass);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 229 */   public static void logInfo(String msg) { System.out.println(msg); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\TlbImp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */