/*     */ package lc.kra.system;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.Locale;
/*     */ import java.util.zip.CRC32;
/*     */ import java.util.zip.Checksum;
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
/*     */ public class LibraryLoader
/*     */ {
/*     */   private static final String LIBRARY_NAME = "systemhook";
/*     */   private static boolean libraryLoad;
/*     */   
/*     */   public static void loadLibrary() {
/*  46 */     if (libraryLoad)
/*  47 */       return;  libName = System.getProperty("systemhook.lib.name", System.mapLibraryName("systemhook").replaceAll("\\.jnilib$", "\\.dylib"));
/*  48 */     String libPath = System.getProperty("systemhook.lib.path");
/*     */     
/*     */     try {
/*  51 */       if (libPath == null)
/*  52 */       { System.loadLibrary(libName); }
/*  53 */       else { System.load((new File(libPath, libName)).getAbsolutePath()); }
/*  54 */        libraryLoad = true;
/*     */       return;
/*  56 */     } catch (UnsatisfiedLinkError null) {
/*     */ 
/*     */       
/*  59 */       libName = System.mapLibraryName("systemhook-" + getOperatingSystemName() + '-' + getOperatingSystemArchitecture()).replaceAll("\\.jnilib$", "\\.dylib");
/*  60 */       String libNameExtension = libName.substring(libName.lastIndexOf('.'));
/*  61 */       String libResourcePath = LibraryLoader.class.getPackage().getName().replace('.', '/') + "/lib/" + libName;
/*     */       
/*  63 */       inputStream = null; outputStream = null;
/*     */       try {
/*  65 */         if ((inputStream = LibraryLoader.class.getClassLoader().getResourceAsStream(libResourcePath)) == null)
/*  66 */           throw new FileNotFoundException("lib: " + libName + " not found in lib directory"); 
/*  67 */         tempFile = File.createTempFile("systemhook-", libNameExtension);
/*     */         
/*  69 */         Checksum checksum = new CRC32();
/*  70 */         outputStream = new FileOutputStream(tempFile);
/*  71 */         byte[] buffer = new byte[1024]; int read;
/*  72 */         while ((read = inputStream.read(buffer)) != -1) {
/*  73 */           outputStream.write(buffer, 0, read);
/*  74 */           checksum.update(buffer, 0, read);
/*     */         } 
/*  76 */         outputStream.close();
/*     */         
/*  78 */         File libFile = new File(tempFile.getParentFile(), "systemhook+" + checksum.getValue() + libNameExtension);
/*  79 */         if (!libFile.exists())
/*  80 */         { tempFile.renameTo(libFile); }
/*  81 */         else { tempFile.delete(); }
/*     */         
/*  83 */         System.load(libFile.getAbsolutePath());
/*  84 */         libraryLoad = true;
/*  85 */       } catch (IOException e) {
/*  86 */         throw new UnsatisfiedLinkError(e.getMessage());
/*     */       } finally {
/*  88 */         if (inputStream != null) {
/*  89 */           try { inputStream.close(); }
/*  90 */           catch (IOException iOException) {}
/*     */         }
/*  92 */         if (outputStream != null)
/*  93 */           try { outputStream.close(); }
/*  94 */           catch (IOException iOException) {} 
/*     */       } 
/*     */       return;
/*     */     } 
/*     */   }
/*     */   private static String getOperatingSystemName() {
/* 100 */     osName = System.getProperty("os.name").toLowerCase(Locale.ROOT);
/* 101 */     if (osName.startsWith("windows"))
/* 102 */       return "windows"; 
/* 103 */     if (osName.startsWith("linux"))
/* 104 */       return "linux"; 
/* 105 */     if (osName.startsWith("mac os"))
/* 106 */       return "darwin"; 
/* 107 */     if (osName.startsWith("sunos") || osName.startsWith("solaris"))
/* 108 */       return "solaris"; 
/* 109 */     return osName;
/*     */   }
/*     */   private static String getOperatingSystemArchitecture() {
/* 112 */     osArch = System.getProperty("os.arch").toLowerCase(Locale.ROOT);
/* 113 */     if ((osArch.startsWith("i") || osArch.startsWith("x")) && osArch.endsWith("86"))
/* 114 */       return "x86"; 
/* 115 */     if ((osArch.equals("i86") || osArch.startsWith("amd")) && osArch.endsWith("64"))
/* 116 */       return "amd64"; 
/* 117 */     if (osArch.startsWith("arm"))
/* 118 */       return "arm"; 
/* 119 */     if (osArch.startsWith("sparc"))
/* 120 */       return !osArch.endsWith("64") ? "sparc" : "sparc64"; 
/* 121 */     if (osArch.startsWith("ppc"))
/* 122 */       return !osArch.endsWith("64") ? "ppc" : "ppc64"; 
/* 123 */     return osArch;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lc\kra\system\LibraryLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */