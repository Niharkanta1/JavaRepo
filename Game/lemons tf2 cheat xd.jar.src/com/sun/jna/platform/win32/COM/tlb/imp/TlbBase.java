/*     */ package com.sun.jna.platform.win32.COM.tlb.imp;
/*     */ 
/*     */ import com.sun.jna.platform.win32.COM.TypeInfoUtil;
/*     */ import com.sun.jna.platform.win32.COM.TypeLibUtil;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public abstract class TlbBase
/*     */ {
/*     */   public static final String CR = "\n";
/*     */   public static final String CRCR = "\n\n";
/*     */   public static final String TAB = "\t";
/*     */   public static final String TABTAB = "\t\t";
/*     */   protected TypeLibUtil typeLibUtil;
/*     */   protected TypeInfoUtil typeInfoUtil;
/*     */   protected int index;
/*     */   protected StringBuffer templateBuffer;
/*     */   protected StringBuffer classBuffer;
/*     */   protected String content;
/*     */   protected String filename;
/*     */   protected String name;
/*  69 */   public static String[] IUNKNOWN_METHODS = { "QueryInterface", "AddRef", "Release" };
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static String[] IDISPATCH_METHODS = { "GetTypeInfoCount", "GetTypeInfo", "GetIDsOfNames", "Invoke" };
/*     */ 
/*     */   
/*     */   protected String bindingMode;
/*     */ 
/*     */   
/*  79 */   public TlbBase(int index, TypeLibUtil typeLibUtil, TypeInfoUtil typeInfoUtil) { this(index, typeLibUtil, typeInfoUtil, "dispid"); } public TlbBase(int index, TypeLibUtil typeLibUtil, TypeInfoUtil typeInfoUtil, String bindingMode) { this.content = "";
/*     */     this.filename = "DefaultFilename";
/*     */     this.name = "DefaultName";
/*     */     this.bindingMode = "dispid";
/*  83 */     this.index = index;
/*  84 */     this.typeLibUtil = typeLibUtil;
/*  85 */     this.typeInfoUtil = typeInfoUtil;
/*  86 */     this.bindingMode = bindingMode;
/*     */     
/*  88 */     String filename = getClassTemplate();
/*     */     try {
/*  90 */       readTemplateFile(filename);
/*  91 */       this.classBuffer = this.templateBuffer;
/*  92 */     } catch (IOException e) {
/*  93 */       e.printStackTrace();
/*     */     }  }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 104 */   public void logError(String msg) { log("ERROR", msg); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 114 */   public void logInfo(String msg) { log("INFO", msg); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 123 */   public StringBuffer getClassBuffer() { return this.classBuffer; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 133 */   public void createContent(String content) { replaceVariable("content", content); }
/*     */ 
/*     */   
/*     */   public void setFilename(String filename) {
/* 137 */     if (!filename.endsWith("java"))
/* 138 */       filename = filename + ".java"; 
/* 139 */     this.filename = filename;
/*     */   }
/*     */ 
/*     */   
/* 143 */   public String getFilename() { return this.filename; }
/*     */ 
/*     */ 
/*     */   
/* 147 */   public String getName() { return this.name; }
/*     */ 
/*     */ 
/*     */   
/* 151 */   public void setName(String name) { this.name = name; }
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
/*     */   protected void log(String level, String msg) {
/* 163 */     String _msg = level + " " + getTime() + " : " + msg;
/* 164 */     System.out.println(_msg);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getTime() {
/* 173 */     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
/* 174 */     return sdf.format(new Date());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract String getClassTemplate();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void readTemplateFile(String filename) {
/* 193 */     this.templateBuffer = new StringBuffer();
/* 194 */     reader = null;
/*     */     
/*     */     try {
/* 197 */       InputStream is = getClass().getClassLoader().getResourceAsStream(filename);
/* 198 */       reader = new BufferedReader(new InputStreamReader(is));
/* 199 */       String line = null;
/* 200 */       while ((line = reader.readLine()) != null)
/* 201 */         this.templateBuffer.append(line + "\n"); 
/*     */     } finally {
/* 203 */       if (reader != null) {
/* 204 */         reader.close();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void replaceVariable(String name, String value) {
/* 217 */     if (value == null) {
/* 218 */       value = "";
/*     */     }
/* 220 */     Pattern pattern = Pattern.compile("\\$\\{" + name + "\\}");
/* 221 */     Matcher matcher = pattern.matcher(this.classBuffer);
/* 222 */     String replacement = value;
/* 223 */     String result = "";
/*     */     
/* 225 */     while (matcher.find()) {
/* 226 */       result = matcher.replaceAll(replacement);
/*     */     }
/*     */     
/* 229 */     if (result.length() > 0) {
/* 230 */       this.classBuffer = new StringBuffer(result);
/*     */     }
/*     */   }
/*     */   
/* 234 */   protected void createPackageName(String packagename) { replaceVariable("packagename", packagename); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 244 */   protected void createClassName(String name) { replaceVariable("classname", name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isReservedMethod(String method) {
/* 255 */     for (i = 0; i < IUNKNOWN_METHODS.length; i++) {
/* 256 */       if (IUNKNOWN_METHODS[i].equalsIgnoreCase(method)) {
/* 257 */         return true;
/*     */       }
/*     */     } 
/* 260 */     for (int i = 0; i < IDISPATCH_METHODS.length; i++) {
/* 261 */       if (IDISPATCH_METHODS[i].equalsIgnoreCase(method)) {
/* 262 */         return true;
/*     */       }
/*     */     } 
/* 265 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isVTableMode() {
/* 269 */     if (this.bindingMode.equalsIgnoreCase("vtable")) {
/* 270 */       return true;
/*     */     }
/* 272 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean isDispIdMode() {
/* 276 */     if (this.bindingMode.equalsIgnoreCase("dispid")) {
/* 277 */       return true;
/*     */     }
/* 279 */     return false;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\tlb\imp\TlbBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */