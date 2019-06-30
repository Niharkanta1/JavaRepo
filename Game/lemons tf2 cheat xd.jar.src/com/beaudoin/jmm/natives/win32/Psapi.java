/*     */ package com.beaudoin.jmm.natives.win32;
/*     */ 
/*     */ import com.beaudoin.jmm.misc.Strings;
/*     */ import com.beaudoin.jmm.process.Module;
/*     */ import com.beaudoin.jmm.process.impl.win32.Win32Process;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.NativeLibrary;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinDef.HMODULE;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public final class Psapi
/*     */ {
/*  46 */   private static PsapiStdCall psapi = (PsapiStdCall)Native.loadLibrary("Psapi", PsapiStdCall.class); private static final WinDef.HMODULE[] lphModules; private static final IntByReference lpcbNeededs; private static final PsapiStdCall.LPMODULEINFO moduleInfo; private static final Map<String, Module> modules;
/*     */   private static byte[] lpImageFileName;
/*     */   
/*  49 */   static  { Native.register(NativeLibrary.getInstance("Psapi"));
/*     */ 
/*     */     
/*  52 */     lphModules = new WinDef.HMODULE[1024];
/*  53 */     lpcbNeededs = new IntByReference();
/*  54 */     moduleInfo = new PsapiStdCall.LPMODULEINFO();
/*  55 */     modules = new HashMap();
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
/*  77 */     lpImageFileName = new byte[128]; }
/*     */   public static Map<String, Module> getModules(Win32Process process) { modules.clear(); EnumProcessModulesEx(process.pointer(), lphModules, lphModules.length, lpcbNeededs, 3); for (int i = 0; i < lpcbNeededs.getValue() / 4; i++) { WinDef.HMODULE hModule = lphModules[i]; if (GetModuleInformation(process.pointer(), hModule, moduleInfo, moduleInfo.size()) && moduleInfo.lpBaseOfDll != null) { String moduleName = Strings.transform(GetModuleBaseNameA(process.pointer(), hModule)); modules.put(moduleName, new Module(process, moduleName, hModule.getPointer(), moduleInfo.SizeOfImage)); }
/*     */        }
/*  80 */      return modules; } private static byte[] GetModuleBaseNameA(Pointer hProcess, WinDef.HMODULE hModule) { GetModuleBaseNameA(hProcess, hModule, lpImageFileName, lpImageFileName.length);
/*  81 */     return lpImageFileName; }
/*     */ 
/*     */ 
/*     */   
/*  85 */   private static boolean EnumProcessModulesEx(Pointer hProcess, HMODULE[] lphModule, int cb, IntByReference lpcbNeededs, int flags) { return psapi.EnumProcessModulesEx(hProcess, lphModule, cb, lpcbNeededs, flags); }
/*     */   
/*     */   public static native boolean GetModuleInformation(Pointer paramPointer, WinDef.HMODULE paramHMODULE, PsapiStdCall.LPMODULEINFO paramLPMODULEINFO, int paramInt);
/*     */   
/*     */   public static native int GetModuleBaseNameA(Pointer paramPointer, WinDef.HMODULE paramHMODULE, byte[] paramArrayOfByte, int paramInt);
/*     */   
/*     */   private static interface PsapiStdCall
/*     */     extends StdCallLibrary {
/*     */     boolean EnumProcessModulesEx(Pointer param1Pointer, WinDef.HMODULE[] param1ArrayOfHMODULE, int param1Int1, IntByReference param1IntByReference, int param1Int2);
/*     */     
/*     */     public static class LPMODULEINFO extends Structure {
/*     */       public WinNT.HANDLE lpBaseOfDll;
/*     */       public int SizeOfImage;
/*     */       public WinNT.HANDLE EntryPoint;
/*     */       
/* 100 */       protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "lpBaseOfDll", "SizeOfImage", "EntryPoint" }); } } } public static class LPMODULEINFO extends Structure { public WinNT.HANDLE lpBaseOfDll; public int SizeOfImage; public WinNT.HANDLE EntryPoint; protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "lpBaseOfDll", "SizeOfImage", "EntryPoint" }); } }
/*     */ 
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\beaudoin\jmm\natives\win32\Psapi.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.0.4
 */