/*     */ package com.sun.jna;
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
/*     */ public final class Platform
/*     */ {
/*     */   public static final int UNSPECIFIED = -1;
/*     */   public static final int MAC = 0;
/*     */   public static final int LINUX = 1;
/*     */   public static final int WINDOWS = 2;
/*     */   public static final int SOLARIS = 3;
/*     */   public static final int FREEBSD = 4;
/*     */   public static final int OPENBSD = 5;
/*     */   public static final int WINDOWSCE = 6;
/*     */   public static final int AIX = 7;
/*     */   public static final int ANDROID = 8;
/*     */   public static final int GNU = 9;
/*     */   public static final int KFREEBSD = 10;
/*     */   public static final int NETBSD = 11;
/*     */   public static final boolean RO_FIELDS;
/*     */   public static final boolean HAS_BUFFERS;
/*     */   public static final boolean HAS_AWT;
/*     */   public static final boolean HAS_JAWT;
/*     */   public static final String MATH_LIBRARY_NAME;
/*     */   public static final String C_LIBRARY_NAME;
/*     */   public static final boolean HAS_DLL_CALLBACKS;
/*     */   public static final String RESOURCE_PREFIX;
/*     */   private static final int osType;
/*     */   public static final String ARCH;
/*     */   
/*     */   static  {
/*  54 */     osName = System.getProperty("os.name");
/*  55 */     if (osName.startsWith("Linux")) {
/*  56 */       if ("dalvik".equals(System.getProperty("java.vm.name").toLowerCase())) {
/*  57 */         osType = 8;
/*     */         
/*  59 */         System.setProperty("jna.nounpack", "true");
/*     */       } else {
/*     */         
/*  62 */         osType = 1;
/*     */       }
/*     */     
/*  65 */     } else if (osName.startsWith("AIX")) {
/*  66 */       osType = 7;
/*     */     }
/*  68 */     else if (osName.startsWith("Mac") || osName.startsWith("Darwin")) {
/*  69 */       osType = 0;
/*     */     }
/*  71 */     else if (osName.startsWith("Windows CE")) {
/*  72 */       osType = 6;
/*     */     }
/*  74 */     else if (osName.startsWith("Windows")) {
/*  75 */       osType = 2;
/*     */     }
/*  77 */     else if (osName.startsWith("Solaris") || osName.startsWith("SunOS")) {
/*  78 */       osType = 3;
/*     */     }
/*  80 */     else if (osName.startsWith("FreeBSD")) {
/*  81 */       osType = 4;
/*     */     }
/*  83 */     else if (osName.startsWith("OpenBSD")) {
/*  84 */       osType = 5;
/*     */     }
/*  86 */     else if (osName.equalsIgnoreCase("gnu")) {
/*  87 */       osType = 9;
/*     */     }
/*  89 */     else if (osName.equalsIgnoreCase("gnu/kfreebsd")) {
/*  90 */       osType = 10;
/*     */     }
/*  92 */     else if (osName.equalsIgnoreCase("netbsd")) {
/*  93 */       osType = 11;
/*     */     } else {
/*     */       
/*  96 */       osType = -1;
/*     */     } 
/*  98 */     boolean hasBuffers = false;
/*     */     try {
/* 100 */       Class.forName("java.nio.Buffer");
/* 101 */       hasBuffers = true;
/*     */     }
/* 103 */     catch (ClassNotFoundException e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     HAS_AWT = (osType != 6 && osType != 8 && osType != 7);
/* 109 */     HAS_JAWT = (HAS_AWT && osType != 0);
/* 110 */     HAS_BUFFERS = hasBuffers;
/* 111 */     RO_FIELDS = (osType != 6);
/* 112 */     C_LIBRARY_NAME = (osType == 2) ? "msvcrt" : ((osType == 6) ? "coredll" : "c");
/* 113 */     MATH_LIBRARY_NAME = (osType == 2) ? "msvcrt" : ((osType == 6) ? "coredll" : "m");
/* 114 */     HAS_DLL_CALLBACKS = (osType == 2);
/* 115 */     ARCH = getCanonicalArchitecture(System.getProperty("os.arch"));
/* 116 */     RESOURCE_PREFIX = getNativeLibraryResourcePrefix();
/*     */   }
/*     */ 
/*     */   
/* 120 */   public static final int getOSType() { return osType; }
/*     */ 
/*     */   
/* 123 */   public static final boolean isMac() { return (osType == 0); }
/*     */ 
/*     */   
/* 126 */   public static final boolean isAndroid() { return (osType == 8); }
/*     */ 
/*     */   
/* 129 */   public static final boolean isLinux() { return (osType == 1); }
/*     */ 
/*     */   
/* 132 */   public static final boolean isAIX() { return (osType == 7); }
/*     */ 
/*     */ 
/*     */   
/* 136 */   public static final boolean isAix() { return isAIX(); }
/*     */ 
/*     */   
/* 139 */   public static final boolean isWindowsCE() { return (osType == 6); }
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final boolean isWindows() { return (osType == 2 || osType == 6); }
/*     */ 
/*     */   
/* 146 */   public static final boolean isSolaris() { return (osType == 3); }
/*     */ 
/*     */   
/* 149 */   public static final boolean isFreeBSD() { return (osType == 4); }
/*     */ 
/*     */   
/* 152 */   public static final boolean isOpenBSD() { return (osType == 5); }
/*     */ 
/*     */   
/* 155 */   public static final boolean isNetBSD() { return (osType == 11); }
/*     */ 
/*     */   
/* 158 */   public static final boolean isGNU() { return (osType == 9); }
/*     */ 
/*     */   
/* 161 */   public static final boolean iskFreeBSD() { return (osType == 10); }
/*     */ 
/*     */ 
/*     */   
/* 165 */   public static final boolean isX11() { return (!isWindows() && !isMac()); }
/*     */   
/*     */   public static final boolean hasRuntimeExec() {
/* 168 */     if (isWindowsCE() && "J9".equals(System.getProperty("java.vm.name")))
/* 169 */       return false; 
/* 170 */     return true;
/*     */   }
/*     */   public static final boolean is64Bit() {
/* 173 */     model = System.getProperty("sun.arch.data.model", 
/* 174 */         System.getProperty("com.ibm.vm.bitmode"));
/* 175 */     if (model != null) {
/* 176 */       return "64".equals(model);
/*     */     }
/* 178 */     if ("x86-64".equals(ARCH) || "ia64"
/* 179 */       .equals(ARCH) || "ppc64"
/* 180 */       .equals(ARCH) || "ppc64le".equals(ARCH) || "sparcv9"
/* 181 */       .equals(ARCH) || "amd64"
/* 182 */       .equals(ARCH)) {
/* 183 */       return true;
/*     */     }
/* 185 */     return (Native.POINTER_SIZE == 8);
/*     */   }
/*     */   
/*     */   public static final boolean isIntel() {
/* 189 */     if (ARCH.startsWith("x86")) {
/* 190 */       return true;
/*     */     }
/* 192 */     return false;
/*     */   }
/*     */   
/*     */   public static final boolean isPPC() {
/* 196 */     if (ARCH.startsWith("ppc")) {
/* 197 */       return true;
/*     */     }
/* 199 */     return false;
/*     */   }
/*     */ 
/*     */   
/* 203 */   public static final boolean isARM() { return ARCH.startsWith("arm"); }
/*     */ 
/*     */ 
/*     */   
/* 207 */   public static final boolean isSPARC() { return ARCH.startsWith("sparc"); }
/*     */ 
/*     */   
/*     */   static String getCanonicalArchitecture(String arch) {
/* 211 */     arch = arch.toLowerCase().trim();
/* 212 */     if ("powerpc".equals(arch)) {
/* 213 */       arch = "ppc";
/*     */     }
/* 215 */     else if ("powerpc64".equals(arch)) {
/* 216 */       arch = "ppc64";
/*     */     }
/* 218 */     else if ("i386".equals(arch) || "i686".equals(arch)) {
/* 219 */       arch = "x86";
/*     */     }
/* 221 */     else if ("x86_64".equals(arch) || "amd64".equals(arch)) {
/* 222 */       arch = "x86-64";
/*     */     } 
/*     */ 
/*     */     
/* 226 */     if ("ppc64".equals(arch) && "little".equals(System.getProperty("sun.cpu.endian"))) {
/* 227 */       arch = "ppc64le";
/*     */     }
/* 229 */     return arch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 236 */   static String getNativeLibraryResourcePrefix() { return getNativeLibraryResourcePrefix(getOSType(), System.getProperty("os.arch"), System.getProperty("os.name")); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String getNativeLibraryResourcePrefix(int osType, String arch, String name) {
/* 247 */     arch = getCanonicalArchitecture(arch);
/* 248 */     switch (osType) {
/*     */       case 8:
/* 250 */         if (arch.startsWith("arm")) {
/* 251 */           arch = "arm";
/*     */         }
/* 253 */         return "android-" + arch;
/*     */       
/*     */       case 2:
/* 256 */         return "win32-" + arch;
/*     */       
/*     */       case 6:
/* 259 */         return "w32ce-" + arch;
/*     */       
/*     */       case 0:
/* 262 */         return "darwin";
/*     */       
/*     */       case 1:
/* 265 */         return "linux-" + arch;
/*     */       
/*     */       case 3:
/* 268 */         return "sunos-" + arch;
/*     */       
/*     */       case 4:
/* 271 */         return "freebsd-" + arch;
/*     */       
/*     */       case 5:
/* 274 */         return "openbsd-" + arch;
/*     */       
/*     */       case 11:
/* 277 */         return "netbsd-" + arch;
/*     */       
/*     */       case 10:
/* 280 */         return "kfreebsd-" + arch;
/*     */     } 
/*     */     
/* 283 */     osPrefix = name.toLowerCase();
/* 284 */     int space = osPrefix.indexOf(" ");
/* 285 */     if (space != -1) {
/* 286 */       osPrefix = osPrefix.substring(0, space);
/*     */     }
/* 288 */     return osPrefix + "-" + arch;
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\Platform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */