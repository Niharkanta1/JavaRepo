/*     */ package lombok.javac.apt;
/*     */ 
/*     */ import com.sun.tools.javac.processing.JavacProcessingEnvironment;
/*     */ import com.sun.tools.javac.util.Options;
/*     */ import java.io.File;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.StringWriter;
/*     */ import java.lang.reflect.Field;
/*     */ import java.net.URL;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.Map;
/*     */ import java.util.Properties;
/*     */ import java.util.Set;
/*     */ import javax.annotation.processing.AbstractProcessor;
/*     */ import javax.annotation.processing.Filer;
/*     */ import javax.annotation.processing.ProcessingEnvironment;
/*     */ import javax.annotation.processing.RoundEnvironment;
/*     */ import javax.annotation.processing.SupportedAnnotationTypes;
/*     */ import javax.lang.model.SourceVersion;
/*     */ import javax.lang.model.element.TypeElement;
/*     */ import javax.tools.Diagnostic;
/*     */ import javax.tools.JavaFileManager;
/*     */ import javax.tools.StandardLocation;
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
/*     */ @Deprecated
/*     */ @SupportedAnnotationTypes({"*"})
/*     */ public class Processor
/*     */   extends AbstractProcessor
/*     */ {
/*     */   public void init(ProcessingEnvironment procEnv) {
/*  66 */     super.init(procEnv);
/*  67 */     if (System.getProperty("lombok.disable") != null) {
/*     */       return;
/*     */     }
/*  70 */     procEnv.getMessager().printMessage(Diagnostic.Kind.WARNING, "Wrong usage of 'lombok.javac.apt.Processor'. " + report(procEnv));
/*     */   }
/*     */   
/*     */   private String report(ProcessingEnvironment procEnv) {
/*  74 */     String data = collectData(procEnv);
/*     */     try {
/*  76 */       return writeFile(data);
/*  77 */     } catch (Exception e) {
/*  78 */       return "Report:\n\n" + data;
/*     */     } 
/*     */   }
/*     */   
/*     */   private String writeFile(String data) throws IOException {
/*  83 */     File file = File.createTempFile("lombok-processor-report-", ".txt");
/*  84 */     OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file));
/*  85 */     writer.write(data);
/*  86 */     writer.close();
/*  87 */     return "Report written to '" + file.getCanonicalPath() + "'\n";
/*     */   }
/*     */   
/*     */   private String collectData(ProcessingEnvironment procEnv) {
/*  91 */     StringBuilder message = new StringBuilder();
/*  92 */     message.append("Problem report for usages of 'lombok.javac.apt.Processor'\n\n");
/*  93 */     listOptions(message, procEnv);
/*  94 */     findServices(message, procEnv.getFiler());
/*  95 */     addStacktrace(message);
/*  96 */     listProperties(message);
/*  97 */     return message.toString();
/*     */   }
/*     */   
/*     */   private void listOptions(StringBuilder message, ProcessingEnvironment procEnv) {
/*     */     try {
/* 102 */       JavacProcessingEnvironment environment = (JavacProcessingEnvironment)procEnv;
/* 103 */       Options instance = Options.instance(environment.getContext());
/* 104 */       Field field = Options.class.getDeclaredField("values");
/* 105 */       field.setAccessible(true);
/* 106 */       Map<String, String> values = (Map)field.get(instance);
/* 107 */       if (values.isEmpty()) {
/* 108 */         message.append("Options: empty\n\n");
/*     */         return;
/*     */       } 
/* 111 */       message.append("Compiler Options:\n");
/* 112 */       for (Map.Entry<String, String> value : values.entrySet()) {
/* 113 */         message.append("- ");
/* 114 */         string(message, (String)value.getKey());
/* 115 */         message.append(" = ");
/* 116 */         string(message, (String)value.getValue());
/* 117 */         message.append("\n");
/*     */       } 
/* 119 */       message.append("\n");
/* 120 */     } catch (Exception e) {
/* 121 */       message.append("No options available\n\n");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void findServices(StringBuilder message, Filer filer) {
/*     */     try {
/* 128 */       Field filerFileManagerField = com.sun.tools.javac.processing.JavacFiler.class.getDeclaredField("fileManager");
/* 129 */       filerFileManagerField.setAccessible(true);
/* 130 */       JavaFileManager jfm = (JavaFileManager)filerFileManagerField.get(filer);
/* 131 */       ClassLoader processorClassLoader = jfm.hasLocation(StandardLocation.ANNOTATION_PROCESSOR_PATH) ? jfm.getClassLoader(StandardLocation.ANNOTATION_PROCESSOR_PATH) : jfm.getClassLoader(StandardLocation.CLASS_PATH);
/* 132 */       Enumeration<URL> resources = processorClassLoader.getResources("META-INF/services/javax.annotation.processing.Processor");
/* 133 */       if (!resources.hasMoreElements()) {
/* 134 */         message.append("No processors discovered\n\n");
/*     */         return;
/*     */       } 
/* 137 */       message.append("Discovered processors:\n");
/* 138 */       while (resources.hasMoreElements()) {
/* 139 */         URL processorUrl = (URL)resources.nextElement();
/* 140 */         message.append("- '").append(processorUrl).append("'");
/* 141 */         content = (InputStream)processorUrl.getContent();
/* 142 */         if (content != null)
/* 143 */           try { InputStreamReader reader = new InputStreamReader(content, "UTF-8");
/* 144 */             StringWriter sw = new StringWriter();
/* 145 */             char[] buffer = new char[8192];
/* 146 */             int read = 0;
/* 147 */             while ((read = reader.read(buffer)) != -1) {
/* 148 */               sw.write(buffer, 0, read);
/*     */             }
/* 150 */             String wholeFile = sw.toString();
/* 151 */             if (wholeFile.contains("lombok.javac.apt.Processor")) {
/* 152 */               message.append(" <= problem\n");
/*     */             } else {
/* 154 */               message.append(" (ok)\n");
/*     */             } 
/* 156 */             message.append("    ").append(wholeFile.replace("\n", "\n    ")).append("\n"); }
/*     */           finally
/* 158 */           { content.close(); }
/*     */            
/*     */       } 
/* 161 */     } catch (Exception e) {
/* 162 */       message.append("Filer information unavailable\n");
/*     */     } 
/* 164 */     message.append("\n");
/*     */   }
/*     */   
/*     */   private void addStacktrace(StringBuilder message) {
/* 168 */     StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
/* 169 */     if (stackTraceElements != null) {
/* 170 */       message.append("Called from\n");
/* 171 */       for (int i = 1; i < stackTraceElements.length; i++) {
/* 172 */         StackTraceElement element = stackTraceElements[i];
/* 173 */         if (!element.getClassName().equals("lombok.javac.apt.Processor")) message.append("- ").append(element).append("\n"); 
/*     */       } 
/*     */     } else {
/* 176 */       message.append("No stacktrace available\n");
/*     */     } 
/* 178 */     message.append("\n");
/*     */   }
/*     */   
/*     */   private void listProperties(StringBuilder message) {
/* 182 */     Properties properties = System.getProperties();
/* 183 */     ArrayList<String> propertyNames = new ArrayList<String>(properties.stringPropertyNames());
/* 184 */     Collections.sort(propertyNames);
/* 185 */     message.append("Properties: \n");
/* 186 */     for (String propertyName : propertyNames) {
/* 187 */       if (propertyName.startsWith("user."))
/* 188 */         continue;  message.append("- ").append(propertyName).append(" = ");
/* 189 */       string(message, System.getProperty(propertyName));
/* 190 */       message.append("\n");
/*     */     } 
/* 192 */     message.append("\n");
/*     */   }
/*     */   
/*     */   private static void string(StringBuilder sb, String s) {
/* 196 */     if (s == null) {
/* 197 */       sb.append("null");
/*     */       return;
/*     */     } 
/* 200 */     sb.append("\"");
/* 201 */     for (int i = 0; i < s.length(); ) { sb.append(escape(s.charAt(i))); i++; }
/* 202 */      sb.append("\"");
/*     */   }
/*     */   
/*     */   private static String escape(char ch) {
/* 206 */     switch (ch) { case '\b':
/* 207 */         return "\\b";
/* 208 */       case '\f': return "\\f";
/* 209 */       case '\n': return "\\n";
/* 210 */       case '\r': return "\\r";
/* 211 */       case '\t': return "\\t";
/* 212 */       case '\'': return "\\'";
/* 213 */       case '"': return "\\\"";
/* 214 */       case '\\': return "\\\\"; }
/*     */     
/* 216 */     if (ch < ' ') return String.format("\\%03o", new Object[] { Integer.valueOf(ch) }); 
/* 217 */     return String.valueOf(ch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 225 */   public SourceVersion getSupportedSourceVersion() { return SourceVersion.values()[SourceVersion.values().length - 1]; }
/*     */ 
/*     */ 
/*     */   
/* 229 */   public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) { return false; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\javac\apt\Processor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */