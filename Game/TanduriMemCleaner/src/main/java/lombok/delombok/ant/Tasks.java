/*     */ package lombok.delombok.ant;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import lombok.Lombok;
/*     */ import org.apache.tools.ant.BuildException;
/*     */ import org.apache.tools.ant.Location;
/*     */ import org.apache.tools.ant.Task;
/*     */ import org.apache.tools.ant.types.FileSet;
/*     */ import org.apache.tools.ant.types.Path;
/*     */ import org.apache.tools.ant.types.Reference;
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
/*     */ class Tasks
/*     */ {
/*     */   public static class Format
/*     */   {
/*     */     private String value;
/*     */     
/*     */     public int hashCode() {
/*  47 */       int prime = 31;
/*  48 */       result = 1;
/*  49 */       return 31 * result + ((this.value == null) ? 0 : this.value.hashCode());
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object obj) {
/*  54 */       if (this == obj) return true; 
/*  55 */       if (obj == null) return false; 
/*  56 */       if (getClass() != obj.getClass()) return false; 
/*  57 */       Format other = (Format)obj;
/*  58 */       if (this.value == null)
/*  59 */       { if (other.value != null) return false;  }
/*  60 */       else if (!this.value.equals(other.value)) { return false; }
/*  61 */        return true;
/*     */     }
/*     */ 
/*     */     
/*  65 */     public String toString() { return "FormatOption [value=" + this.value + "]"; }
/*     */ 
/*     */ 
/*     */     
/*  69 */     public String getValue() { return this.value; }
/*     */ 
/*     */ 
/*     */     
/*  73 */     public void setValue(String value) { this.value = value; }
/*     */   }
/*     */   
/*     */   public static class Delombok extends Task {
/*     */     private File fromDir;
/*     */     private File toDir;
/*     */     private Path classpath;
/*     */     private Path sourcepath;
/*     */     private boolean verbose;
/*     */     private String encoding;
/*     */     private Path path;
/*  84 */     private List<Tasks.Format> formatOptions = new ArrayList(); private static ClassLoader shadowLoader;
/*     */     
/*     */     public void setClasspath(Path classpath) {
/*  87 */       if (this.classpath == null) {
/*  88 */         this.classpath = classpath;
/*     */       } else {
/*  90 */         this.classpath.append(classpath);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Path createClasspath() {
/*  95 */       if (this.classpath == null) {
/*  96 */         this.classpath = new Path(getProject());
/*     */       }
/*  98 */       return this.classpath.createPath();
/*     */     }
/*     */ 
/*     */     
/* 102 */     public void setClasspathRef(Reference r) { createClasspath().setRefid(r); }
/*     */ 
/*     */     
/*     */     public void setSourcepath(Path sourcepath) {
/* 106 */       if (this.sourcepath == null) {
/* 107 */         this.sourcepath = sourcepath;
/*     */       } else {
/* 109 */         this.sourcepath.append(sourcepath);
/*     */       } 
/*     */     }
/*     */     
/*     */     public Path createSourcepath() {
/* 114 */       if (this.sourcepath == null) {
/* 115 */         this.sourcepath = new Path(getProject());
/*     */       }
/* 117 */       return this.sourcepath.createPath();
/*     */     }
/*     */ 
/*     */     
/* 121 */     public void setSourcepathRef(Reference r) { createSourcepath().setRefid(r); }
/*     */ 
/*     */ 
/*     */     
/* 125 */     public void setFrom(File dir) { this.fromDir = dir; }
/*     */ 
/*     */ 
/*     */     
/* 129 */     public void setTo(File dir) { this.toDir = dir; }
/*     */ 
/*     */ 
/*     */     
/* 133 */     public void setVerbose(boolean verbose) { this.verbose = verbose; }
/*     */ 
/*     */ 
/*     */     
/* 137 */     public void setEncoding(String encoding) { this.encoding = encoding; }
/*     */ 
/*     */     
/*     */     public void addFileset(FileSet set) {
/* 141 */       if (this.path == null) this.path = new Path(getProject()); 
/* 142 */       this.path.add(set);
/*     */     }
/*     */ 
/*     */     
/* 146 */     public Tasks.Format createFormat() { return new Tasks.Format(); }
/*     */ 
/*     */ 
/*     */     
/* 150 */     public void addFormat(Tasks.Format format) { this.formatOptions.add(format); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static Class<?> shadowLoadClass(String name) {
/*     */       try {
/* 157 */         if (shadowLoader == null) {
/*     */           try {
/* 159 */             Class.forName("lombok.core.LombokNode");
/*     */             
/* 161 */             shadowLoader = Delombok.class.getClassLoader();
/* 162 */           } catch (ClassNotFoundException e) {
/*     */             
/* 164 */             Class<?> launcherMain = Class.forName("lombok.launch.Main");
/* 165 */             Method m = launcherMain.getDeclaredMethod("createShadowClassLoader", new Class[0]);
/* 166 */             m.setAccessible(true);
/* 167 */             shadowLoader = (ClassLoader)m.invoke(null, new Object[0]);
/*     */           } 
/*     */         }
/*     */         
/* 171 */         return Class.forName(name, true, shadowLoader);
/* 172 */       } catch (Exception e) {
/* 173 */         throw Lombok.sneakyThrow(e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void execute() {
/* 179 */       Location loc = getLocation();
/*     */       
/*     */       try {
/* 182 */         Object instance = shadowLoadClass("lombok.delombok.ant.DelombokTaskImpl").newInstance();
/* 183 */         for (Field selfField : getClass().getDeclaredFields()) {
/* 184 */           if (!selfField.isSynthetic() && !Modifier.isStatic(selfField.getModifiers())) {
/* 185 */             Field otherField = instance.getClass().getDeclaredField(selfField.getName());
/* 186 */             otherField.setAccessible(true);
/* 187 */             selfField.setAccessible(true);
/* 188 */             if (selfField.getName().equals("formatOptions")) {
/* 189 */               List<String> rep = new ArrayList<String>();
/* 190 */               for (Tasks.Format f : this.formatOptions) {
/* 191 */                 if (f.getValue() == null) throw new BuildException("'value' property required for <format>"); 
/* 192 */                 rep.add(f.getValue());
/*     */               } 
/* 194 */               otherField.set(instance, rep);
/*     */             } else {
/* 196 */               otherField.set(instance, selfField.get(this));
/*     */             } 
/*     */           } 
/*     */         } 
/* 200 */         Method m = instance.getClass().getMethod("execute", new Class[] { Location.class });
/* 201 */         m.setAccessible(true);
/* 202 */         m.invoke(instance, new Object[] { loc });
/* 203 */       } catch (InvocationTargetException e) {
/* 204 */         throw Lombok.sneakyThrow(e.getCause());
/* 205 */       } catch (Exception e) {
/* 206 */         throw Lombok.sneakyThrow(e);
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\delombok\ant\Tasks.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */