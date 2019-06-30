/*     */ package lombok.launch;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import java.net.MalformedURLException;
/*     */ import java.net.URI;
/*     */ import java.net.URL;
/*     */ import java.net.URLDecoder;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.Enumeration;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.Vector;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
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
/*     */ class ShadowClassLoader
/*     */   extends ClassLoader
/*     */ {
/*     */   private static final String SELF_NAME = "lombok/launch/ShadowClassLoader.class";
/*  87 */   private static final ConcurrentMap<String, Class<?>> highlanderMap = new ConcurrentHashMap();
/*     */   
/*     */   private final String SELF_BASE;
/*     */   
/*     */   private final File SELF_BASE_FILE;
/*     */   private final int SELF_BASE_LENGTH;
/*  93 */   private final List<File> override = new ArrayList();
/*     */   private final String sclSuffix;
/*  95 */   private final List<String> parentExclusion = new ArrayList();
/*  96 */   private final List<String> highlanders = new ArrayList();
/*     */ 
/*     */ 
/*     */   
/*     */   private final Map<String, Object> mapJarPathToTracker;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ShadowClassLoader(ClassLoader source, String sclSuffix, String selfBase, List<String> parentExclusion, List<String> highlanders) {
/* 106 */     super(source);
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
/*     */ 
/*     */ 
/*     */     
/* 152 */     this.mapJarPathToTracker = new HashMap(); this.sclSuffix = sclSuffix; if (parentExclusion != null) for (String pe : parentExclusion) { pe = pe.replace(".", "/"); if (!pe.endsWith("/")) pe = pe + "/";  this.parentExclusion.add(pe); }   if (highlanders != null) for (String hl : highlanders) this.highlanders.add(hl);   if (selfBase != null) { this.SELF_BASE = selfBase; this.SELF_BASE_LENGTH = selfBase.length(); } else { String decoded; URL sclClassUrl = ShadowClassLoader.class.getResource("ShadowClassLoader.class"); String sclClassStr = (sclClassUrl == null) ? null : sclClassUrl.toString(); if (sclClassStr == null || !sclClassStr.endsWith("lombok/launch/ShadowClassLoader.class")) { decoded = ShadowClassLoader.class.getClassLoader(); throw new RuntimeException("ShadowLoader can't find itself. SCL loader type: " + ((decoded == null) ? "*NULL*" : decoded.getClass().toString())); }  this.SELF_BASE_LENGTH = sclClassStr.length() - "lombok/launch/ShadowClassLoader.class".length(); try { decoded = URLDecoder.decode(sclClassStr.substring(0, this.SELF_BASE_LENGTH), "UTF-8"); } catch (UnsupportedEncodingException e) { throw new InternalError("UTF-8 not available"); }  this.SELF_BASE = decoded; }  if (this.SELF_BASE.startsWith("jar:file:") && this.SELF_BASE.endsWith("!/")) { this.SELF_BASE_FILE = new File(this.SELF_BASE.substring(9, this.SELF_BASE.length() - 2)); } else if (this.SELF_BASE.startsWith("file:")) { this.SELF_BASE_FILE = new File(this.SELF_BASE.substring(5)); } else { this.SELF_BASE_FILE = new File(this.SELF_BASE); }  String scl = System.getProperty("shadow.override." + sclSuffix); if (scl != null && !scl.isEmpty())
/* 153 */       for (String part : scl.split("\\s*" + ((File.pathSeparatorChar == ';') ? ";" : ":") + "\\s*")) { if (part.endsWith("/*") || part.endsWith(File.separator + "*")) { addOverrideJarDir(part.substring(0, part.length() - 2)); } else { addOverrideClasspathEntry(part); }  }   } private static final Map<Object, String> mapTrackerToJarPath = new WeakHashMap();
/* 154 */   private static final Map<Object, Set<String>> mapTrackerToJarContents = new WeakHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Set<String> getOrMakeJarListing(String absolutePathToJar) {
/* 162 */     synchronized (mapTrackerToJarPath) {
/*     */ 
/*     */ 
/*     */       
/* 166 */       Object ourTracker = this.mapJarPathToTracker.get(absolutePathToJar);
/* 167 */       if (ourTracker != null)
/*     */       {
/*     */ 
/*     */         
/* 171 */         return (Set)mapTrackerToJarContents.get(ourTracker);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 177 */       for (Map.Entry<Object, String> entry : mapTrackerToJarPath.entrySet()) {
/* 178 */         if (((String)entry.getValue()).equals(absolutePathToJar)) {
/*     */ 
/*     */ 
/*     */           
/* 182 */           Object otherTracker = entry.getKey();
/* 183 */           this.mapJarPathToTracker.put(absolutePathToJar, otherTracker);
/* 184 */           return (Set)mapTrackerToJarContents.get(otherTracker);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 191 */       Object newTracker = new Object();
/* 192 */       Set<String> jarMembers = getJarMemberSet(absolutePathToJar);
/*     */       
/* 194 */       mapTrackerToJarContents.put(newTracker, jarMembers);
/* 195 */       mapTrackerToJarPath.put(newTracker, absolutePathToJar);
/* 196 */       this.mapJarPathToTracker.put(absolutePathToJar, newTracker);
/*     */       
/* 198 */       return jarMembers;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Set<String> getJarMemberSet(String absolutePathToJar) {
/*     */     try {
/* 218 */       int shiftBits = 1;
/* 219 */       jar = new JarFile(absolutePathToJar);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 224 */       int jarSizePower2 = Integer.highestOneBit(jar.size());
/* 225 */       if (jarSizePower2 != jar.size()) jarSizePower2 <<= 1; 
/* 226 */       if (jarSizePower2 == 0) jarSizePower2 = 1;
/*     */       
/* 228 */       Set<String> jarMembers = new HashSet<String>(jarSizePower2 >> shiftBits, (1 << shiftBits));
/*     */       try {
/* 230 */         Enumeration<JarEntry> entries = jar.entries();
/* 231 */         while (entries.hasMoreElements()) {
/* 232 */           JarEntry jarEntry = (JarEntry)entries.nextElement();
/* 233 */           if (jarEntry.isDirectory())
/* 234 */             continue;  jarMembers.add(jarEntry.getName());
/*     */         } 
/* 236 */       } catch (Exception exception) {
/*     */       
/*     */       } finally {
/* 239 */         jar.close();
/*     */       } 
/* 241 */       return jarMembers;
/*     */     }
/* 243 */     catch (Exception newJarFileException) {
/* 244 */       return Collections.emptySet();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private URL getResourceFromLocation(String name, String altName, File location) {
/*     */     File absoluteFile;
/* 252 */     if (location.isDirectory()) {
/*     */       try {
/* 254 */         if (altName != null) {
/* 255 */           File f = new File(location, altName);
/* 256 */           if (f.isFile() && f.canRead()) return f.toURI().toURL();
/*     */         
/*     */         } 
/* 259 */         absoluteFile = new File(location, name);
/* 260 */         if (absoluteFile.isFile() && absoluteFile.canRead()) return absoluteFile.toURI().toURL(); 
/* 261 */         return null;
/* 262 */       } catch (MalformedURLException e) {
/* 263 */         return null;
/*     */       } 
/*     */     }
/*     */     
/* 267 */     if (!location.isFile() || !location.canRead()) return null;
/*     */ 
/*     */     
/*     */     try {
/* 271 */       absoluteFile = location.getCanonicalFile();
/* 272 */     } catch (Exception e) {
/* 273 */       absoluteFile = location.getAbsoluteFile();
/*     */     } 
/*     */     
/* 276 */     Set<String> jarContents = getOrMakeJarListing(absoluteFile.getAbsolutePath());
/*     */     
/* 278 */     String absoluteUri = absoluteFile.toURI().toString();
/*     */     
/*     */     try {
/* 281 */       if (jarContents.contains(altName)) {
/* 282 */         return (new URI("jar:" + absoluteUri + "!/" + altName)).toURL();
/*     */       }
/* 284 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 289 */       if (jarContents.contains(name)) {
/* 290 */         return (new URI("jar:" + absoluteUri + "!/" + name)).toURL();
/*     */       }
/* 292 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */     
/* 296 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean inOwnBase(URL item, String name) {
/* 303 */     if (item == null) return false; 
/* 304 */     String itemString = item.toString();
/* 305 */     return (itemString.length() == this.SELF_BASE_LENGTH + name.length() && this.SELF_BASE.regionMatches(0, itemString, 0, this.SELF_BASE_LENGTH));
/*     */   }
/*     */   
/*     */   public Enumeration<URL> getResources(String name) throws IOException {
/* 309 */     String altName = null;
/* 310 */     if (name.endsWith(".class")) altName = name.substring(0, name.length() - 6) + ".SCL." + this.sclSuffix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     Vector<URL> vector = new Vector<URL>();
/*     */     
/* 319 */     for (File ce : this.override) {
/* 320 */       URL url = getResourceFromLocation(name, altName, ce);
/* 321 */       if (url != null) vector.add(url);
/*     */     
/*     */     } 
/* 324 */     if (this.override.isEmpty()) {
/* 325 */       URL fromSelf = getResourceFromLocation(name, altName, this.SELF_BASE_FILE);
/* 326 */       if (fromSelf != null) vector.add(fromSelf);
/*     */     
/*     */     } 
/* 329 */     Enumeration<URL> sec = super.getResources(name);
/* 330 */     while (sec.hasMoreElements()) {
/* 331 */       URL item = (URL)sec.nextElement();
/* 332 */       if (!inOwnBase(item, name)) vector.add(item);
/*     */     
/*     */     } 
/* 335 */     if (altName != null) {
/* 336 */       Enumeration<URL> tern = super.getResources(altName);
/* 337 */       while (tern.hasMoreElements()) {
/* 338 */         URL item = (URL)tern.nextElement();
/* 339 */         if (!inOwnBase(item, altName)) vector.add(item);
/*     */       
/*     */       } 
/*     */     } 
/* 343 */     return vector.elements();
/*     */   }
/*     */ 
/*     */   
/* 347 */   public URL getResource(String name) { return getResource_(name, false); }
/*     */ 
/*     */   
/*     */   private URL getResource_(String name, boolean noSuper) {
/* 351 */     String altName = null;
/* 352 */     if (name.endsWith(".class")) altName = name.substring(0, name.length() - 6) + ".SCL." + this.sclSuffix; 
/* 353 */     for (File ce : this.override) {
/* 354 */       URL url = getResourceFromLocation(name, altName, ce);
/* 355 */       if (url != null) return url;
/*     */     
/*     */     } 
/* 358 */     if (!this.override.isEmpty()) {
/* 359 */       if (noSuper) return null; 
/* 360 */       if (altName != null) {
/*     */         try {
/* 362 */           URL res = getResourceSkippingSelf(altName);
/* 363 */           if (res != null) return res; 
/* 364 */         } catch (IOException iOException) {}
/*     */       }
/*     */       
/*     */       try {
/* 368 */         return getResourceSkippingSelf(name);
/* 369 */       } catch (IOException e) {
/* 370 */         return null;
/*     */       } 
/*     */     } 
/*     */     
/* 374 */     URL url = getResourceFromLocation(name, altName, this.SELF_BASE_FILE);
/* 375 */     if (url != null) return url;
/*     */     
/* 377 */     if (altName != null) {
/* 378 */       URL res = super.getResource(altName);
/* 379 */       if (res != null && (!noSuper || inOwnBase(res, altName))) return res;
/*     */     
/*     */     } 
/* 382 */     URL res = super.getResource(name);
/* 383 */     if (res != null && (!noSuper || inOwnBase(res, name))) return res; 
/* 384 */     return null;
/*     */   }
/*     */   
/*     */   private boolean exclusionListMatch(String name) {
/* 388 */     for (String pe : this.parentExclusion) {
/* 389 */       if (name.startsWith(pe)) return true; 
/*     */     } 
/* 391 */     return false;
/*     */   }
/*     */   
/*     */   private URL getResourceSkippingSelf(String name) {
/* 395 */     URL candidate = super.getResource(name);
/* 396 */     if (candidate == null) return null; 
/* 397 */     if (!inOwnBase(candidate, name)) return candidate;
/*     */     
/* 399 */     Enumeration<URL> en = super.getResources(name);
/* 400 */     while (en.hasMoreElements()) {
/* 401 */       candidate = (URL)en.nextElement();
/* 402 */       if (!inOwnBase(candidate, name)) return candidate;
/*     */     
/*     */     } 
/* 405 */     return null;
/*     */   }
/*     */   
/*     */   public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
/*     */     byte[] b;
/* 410 */     alreadyLoaded = findLoadedClass(name);
/* 411 */     if (alreadyLoaded != null) return alreadyLoaded;
/*     */ 
/*     */     
/* 414 */     if (this.highlanders.contains(name)) {
/* 415 */       Class<?> c = (Class)highlanderMap.get(name);
/* 416 */       if (c != null) return c;
/*     */     
/*     */     } 
/* 419 */     String fileNameOfClass = name.replace(".", "/") + ".class";
/* 420 */     URL res = getResource_(fileNameOfClass, true);
/* 421 */     if (res == null) {
/* 422 */       if (!exclusionListMatch(fileNameOfClass)) return super.loadClass(name, resolve); 
/* 423 */       throw new ClassNotFoundException(name);
/*     */     } 
/*     */ 
/*     */     
/* 427 */     int p = 0;
/*     */     try {
/* 429 */       c = res.openStream();
/*     */       
/*     */       try {
/* 432 */         b = new byte[65536];
/*     */         while (true) {
/* 434 */           int r = c.read(b, p, b.length - p);
/* 435 */           if (r == -1)
/* 436 */             break;  p += r;
/* 437 */           if (p == b.length) {
/* 438 */             byte[] nb = new byte[b.length * 2];
/* 439 */             System.arraycopy(b, 0, nb, 0, p);
/* 440 */             b = nb;
/*     */           } 
/*     */         } 
/*     */       } finally {
/* 444 */         c.close();
/*     */       } 
/* 446 */     } catch (IOException e) {
/* 447 */       throw new ClassNotFoundException("I/O exception reading class " + name, c);
/*     */     } 
/*     */ 
/*     */     
/*     */     try {
/* 452 */       c = defineClass(name, b, 0, p);
/* 453 */     } catch (LinkageError e) {
/* 454 */       Class<?> c; if (this.highlanders.contains(name)) {
/* 455 */         Class<?> alreadyDefined = (Class)highlanderMap.get(name);
/* 456 */         if (alreadyDefined != null) return alreadyDefined; 
/*     */       } 
/*     */       try {
/* 459 */         c = findLoadedClass(name);
/* 460 */       } catch (LinkageError e2) {
/* 461 */         throw e;
/*     */       } 
/* 463 */       if (c == null) throw e;
/*     */     
/*     */     } 
/* 466 */     if (this.highlanders.contains(name)) {
/* 467 */       Class<?> alreadyDefined = (Class)highlanderMap.putIfAbsent(name, c);
/* 468 */       if (alreadyDefined != null) c = alreadyDefined;
/*     */     
/*     */     } 
/* 471 */     if (resolve) resolveClass(c); 
/* 472 */     return c;
/*     */   }
/*     */   
/*     */   public void addOverrideJarDir(String dir) {
/* 476 */     File f = new File(dir);
/* 477 */     for (File j : f.listFiles()) {
/* 478 */       if (j.getName().toLowerCase().endsWith(".jar") && j.canRead() && j.isFile()) this.override.add(j);
/*     */     
/*     */     } 
/*     */   }
/*     */   
/* 483 */   public void addOverrideClasspathEntry(String entry) { this.override.add(new File(entry)); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\launch\ShadowClassLoader.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */