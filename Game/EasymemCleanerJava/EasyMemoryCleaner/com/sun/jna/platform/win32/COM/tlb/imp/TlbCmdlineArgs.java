/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
/*     */ import java.util.Hashtable;
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
/*     */ public class TlbCmdlineArgs
/*     */   extends Hashtable<String, String>
/*     */   implements TlbConst
/*     */ {
/*  21 */   public TlbCmdlineArgs(String[] args) { readCmdArgs(args); }
/*     */ 
/*     */   
/*     */   public int getIntParam(String key) {
/*  25 */     String param = getRequiredParam(key);
/*  26 */     return (new Integer(param)).intValue();
/*     */   }
/*     */ 
/*     */   
/*  30 */   public String getParam(String key) { return (String)get(key); }
/*     */ 
/*     */   
/*     */   public String getRequiredParam(String key) {
/*  34 */     String param = getParam(key);
/*  35 */     if (param == null) {
/*  36 */       throw new TlbParameterNotFoundException("Commandline parameter not found: " + key);
/*     */     }
/*     */     
/*  39 */     return param;
/*     */   }
/*     */   
/*     */   private void readCmdArgs(String[] args) {
/*  43 */     if (args.length < 2) {
/*  44 */       showCmdHelp();
/*     */     }
/*  46 */     for (int i = 0; i < args.length; ) {
/*  47 */       String cmdName = args[i];
/*  48 */       String cmdValue = args[i + 1];
/*  49 */       if (cmdName.startsWith("-") && !cmdValue.startsWith("-")) {
/*  50 */         put(cmdName.substring(1), cmdValue);
/*  51 */         i += 2; continue;
/*     */       } 
/*  53 */       showCmdHelp();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   public boolean isTlbFile() { return containsKey("tlb.file"); }
/*     */ 
/*     */ 
/*     */   
/*  64 */   public boolean isTlbId() { return containsKey("tlb.id"); }
/*     */ 
/*     */   
/*     */   public String getBindingMode() {
/*  68 */     if (containsKey("bind.mode")) {
/*  69 */       return getParam("bind.mode");
/*     */     }
/*  71 */     return "vtable";
/*     */   }
/*     */   
/*     */   public void showCmdHelp() {
/*  75 */     String helpStr = "usage: TlbImp [-tlb.id -tlb.major.version -tlb.minor.version] [-tlb.file] [-bind.mode vTable, dispId] [-output.dir]\n\noptions:\n-tlb.id               The guid of the type library.\n-tlb.major.version    The major version of the type library.\n-tlb.minor.version    The minor version of the type library.\n-tlb.file             The file name containing the type library.\n-bind.mode            The binding mode used to create the Java code.\n-output.dir           The optional output directory, default is the user temp directory.\n\nsamples:\nMicrosoft Shell Controls And Automation:\n-tlb.file shell32.dll\n-tlb.id {50A7E9B0-70EF-11D1-B75A-00A0C90564FE} -tlb.major.version 1 -tlb.minor.version 0\n\nMicrosoft Word 12.0 Object Library:\n-tlb.id {00020905-0000-0000-C000-000000000046} -tlb.major.version 8 -tlb.minor.version 4\n\n";
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
/* 104 */     System.out.println(helpStr);
/* 105 */     System.exit(0);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbCmdlineArgs.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */