/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.LastErrorException;
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.nio.ByteOrder;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Kernel32Util
/*     */   implements WinDef
/*     */ {
/*     */   public static final String VOLUME_GUID_PATH_PREFIX = "\\\\?\\Volume{";
/*     */   public static final String VOLUME_GUID_PATH_SUFFIX = "}\\";
/*     */   
/*     */   public static String getComputerName() {
/*  47 */     buffer = new char[WinBase.MAX_COMPUTERNAME_LENGTH + 1];
/*  48 */     IntByReference lpnSize = new IntByReference(buffer.length);
/*  49 */     if (!Kernel32.INSTANCE.GetComputerName(buffer, lpnSize)) {
/*  50 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*  52 */     return Native.toString(buffer);
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
/*     */   public static String formatMessage(int code) {
/*  64 */     PointerByReference buffer = new PointerByReference();
/*  65 */     if (0 == Kernel32.INSTANCE.FormatMessage(4864, null, code, 0, buffer, 0, null))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  72 */       throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*  74 */     String s = buffer.getValue().getWideString(0L);
/*  75 */     Kernel32.INSTANCE.LocalFree(buffer.getValue());
/*  76 */     return s.trim();
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
/*  87 */   public static String formatMessage(WinNT.HRESULT code) { return formatMessage(code.intValue()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   public static String formatMessageFromHR(WinNT.HRESULT code) { return formatMessage(code.intValue()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static String formatMessageFromLastErrorCode(int code) { return formatMessageFromHR(W32Errors.HRESULT_FROM_WIN32(code)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getLastErrorMessage() {
/* 115 */     return formatMessageFromLastErrorCode(Kernel32.INSTANCE
/* 116 */         .GetLastError());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getTempPath() {
/* 125 */     nBufferLength = new WinDef.DWORD(260L);
/* 126 */     char[] buffer = new char[nBufferLength.intValue()];
/* 127 */     if (Kernel32.INSTANCE.GetTempPath(nBufferLength, buffer).intValue() == 0) {
/* 128 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 130 */     return Native.toString(buffer);
/*     */   }
/*     */   
/*     */   public static void deleteFile(String filename) {
/* 134 */     if (!Kernel32.INSTANCE.DeleteFile(filename)) {
/* 135 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static List<String> getLogicalDriveStrings() {
/* 145 */     dwSize = Kernel32.INSTANCE.GetLogicalDriveStrings(new WinDef.DWORD(0L), null);
/* 146 */     if (dwSize.intValue() <= 0) {
/* 147 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/* 150 */     char[] buf = new char[dwSize.intValue()];
/* 151 */     dwSize = Kernel32.INSTANCE.GetLogicalDriveStrings(dwSize, buf);
/* 152 */     int bufSize = dwSize.intValue();
/* 153 */     if (bufSize <= 0) {
/* 154 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/* 157 */     return Native.toStringList(buf, 0, bufSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFileAttributes(String fileName) {
/* 168 */     int fileAttributes = Kernel32.INSTANCE.GetFileAttributes(fileName);
/* 169 */     if (fileAttributes == -1) {
/* 170 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 172 */     return fileAttributes;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getFileType(String fileName) {
/* 182 */     File f = new File(fileName);
/* 183 */     if (!f.exists()) {
/* 184 */       throw new FileNotFoundException(fileName);
/*     */     }
/*     */     
/* 187 */     hFile = null; try {
/*     */       int err;
/* 189 */       hFile = Kernel32.INSTANCE.CreateFile(fileName, -2147483648, 1, new WinBase.SECURITY_ATTRIBUTES(), 3, 128, (new WinNT.HANDLEByReference())
/*     */ 
/*     */           
/* 192 */           .getValue());
/*     */       
/* 194 */       if (WinBase.INVALID_HANDLE_VALUE.equals(hFile)) {
/* 195 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*     */       
/* 198 */       int type = Kernel32.INSTANCE.GetFileType(hFile);
/* 199 */       switch (type) {
/*     */         case 0:
/* 201 */           err = Kernel32.INSTANCE.GetLastError();
/* 202 */           switch (err) {
/*     */             case 0:
/*     */               break;
/*     */           } 
/* 206 */           throw new Win32Exception(err);
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 211 */       return type;
/*     */     } finally {
/*     */       
/* 214 */       if (hFile != null && 
/* 215 */         !Kernel32.INSTANCE.CloseHandle(hFile)) {
/* 216 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/* 227 */   public static int getDriveType(String rootName) { return Kernel32.INSTANCE.GetDriveType(rootName); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getEnvironmentVariable(String name) {
/* 239 */     int size = Kernel32.INSTANCE.GetEnvironmentVariable(name, null, 0);
/* 240 */     if (size == 0)
/* 241 */       return null; 
/* 242 */     if (size < 0) {
/* 243 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/* 246 */     char[] buffer = new char[size];
/* 247 */     size = Kernel32.INSTANCE.GetEnvironmentVariable(name, buffer, buffer.length);
/*     */     
/* 249 */     if (size <= 0) {
/* 250 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 252 */     return Native.toString(buffer);
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
/*     */   public static Map<String, String> getEnvironmentVariables() {
/* 264 */     lpszEnvironmentBlock = Kernel32.INSTANCE.GetEnvironmentStrings();
/* 265 */     if (lpszEnvironmentBlock == null) {
/* 266 */       throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/*     */     try {
/* 270 */       return getEnvironmentVariables(lpszEnvironmentBlock, 0L);
/*     */     } finally {
/* 272 */       if (!Kernel32.INSTANCE.FreeEnvironmentStrings(lpszEnvironmentBlock)) {
/* 273 */         throw new LastErrorException(Kernel32.INSTANCE.GetLastError());
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<String, String> getEnvironmentVariables(Pointer lpszEnvironmentBlock, long offset) {
/* 290 */     if (lpszEnvironmentBlock == null) {
/* 291 */       return null;
/*     */     }
/*     */     
/* 294 */     Map<String, String> vars = new TreeMap<String, String>();
/* 295 */     boolean asWideChars = isWideCharEnvironmentStringBlock(lpszEnvironmentBlock, offset);
/* 296 */     long stepFactor = asWideChars ? 2L : 1L;
/* 297 */     long curOffset = offset; while (true) {
/* 298 */       String nvp = readEnvironmentStringBlockEntry(lpszEnvironmentBlock, curOffset, asWideChars);
/* 299 */       int len = nvp.length();
/* 300 */       if (len == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 304 */       int pos = nvp.indexOf('=');
/* 305 */       if (pos < 0) {
/* 306 */         throw new IllegalArgumentException("Missing variable value separator in " + nvp);
/*     */       }
/*     */       
/* 309 */       String name = nvp.substring(0, pos), value = nvp.substring(pos + 1);
/* 310 */       vars.put(name, value);
/*     */       
/* 312 */       curOffset += (len + 1) * stepFactor;
/*     */     } 
/*     */     
/* 315 */     return vars;
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
/*     */   public static String readEnvironmentStringBlockEntry(Pointer lpszEnvironmentBlock, long offset, boolean asWideChars) {
/* 331 */     long endOffset = findEnvironmentStringBlockEntryEnd(lpszEnvironmentBlock, offset, asWideChars);
/* 332 */     int dataLen = (int)(endOffset - offset);
/* 333 */     if (dataLen == 0) {
/* 334 */       return "";
/*     */     }
/*     */     
/* 337 */     int charsLen = asWideChars ? (dataLen / 2) : dataLen;
/* 338 */     char[] chars = new char[charsLen];
/* 339 */     long curOffset = offset, stepSize = asWideChars ? 2L : 1L;
/* 340 */     ByteOrder byteOrder = ByteOrder.nativeOrder();
/* 341 */     for (int index = 0; index < chars.length; index++, curOffset += stepSize) {
/* 342 */       byte b = lpszEnvironmentBlock.getByte(curOffset);
/* 343 */       if (asWideChars) {
/* 344 */         byte x = lpszEnvironmentBlock.getByte(curOffset + 1L);
/* 345 */         if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
/* 346 */           chars[index] = (char)(x << 8 & 0xFF00 | b & 0xFF);
/*     */         } else {
/* 348 */           chars[index] = (char)(b << 8 & 0xFF00 | x & 0xFF);
/*     */         } 
/*     */       } else {
/* 351 */         chars[index] = (char)(b & 0xFF);
/*     */       } 
/*     */     } 
/*     */     
/* 355 */     return new String(chars);
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
/*     */   public static long findEnvironmentStringBlockEntryEnd(Pointer lpszEnvironmentBlock, long offset, boolean asWideChars) {
/*     */     long curOffset;
/*     */     long stepSize;
/* 371 */     for (curOffset = offset, stepSize = asWideChars ? 2L : 1L;; curOffset += stepSize) {
/* 372 */       byte b = lpszEnvironmentBlock.getByte(curOffset);
/* 373 */       if (b == 0) {
/* 374 */         return curOffset;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isWideCharEnvironmentStringBlock(Pointer lpszEnvironmentBlock, long offset) {
/* 407 */     byte b0 = lpszEnvironmentBlock.getByte(offset);
/* 408 */     byte b1 = lpszEnvironmentBlock.getByte(offset + 1L);
/* 409 */     ByteOrder byteOrder = ByteOrder.nativeOrder();
/* 410 */     if (ByteOrder.LITTLE_ENDIAN.equals(byteOrder)) {
/* 411 */       return isWideCharEnvironmentStringBlock(b1);
/*     */     }
/* 413 */     return isWideCharEnvironmentStringBlock(b0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean isWideCharEnvironmentStringBlock(byte charsetIndicator) {
/* 419 */     if (charsetIndicator != 0) {
/* 420 */       return false;
/*     */     }
/* 422 */     return true;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 448 */   public static final int getPrivateProfileInt(String appName, String keyName, int defaultValue, String fileName) { return Kernel32.INSTANCE.GetPrivateProfileInt(appName, keyName, defaultValue, fileName); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String getPrivateProfileString(String lpAppName, String lpKeyName, String lpDefault, String lpFileName) {
/* 503 */     char[] buffer = new char[1024];
/* 504 */     Kernel32.INSTANCE.GetPrivateProfileString(lpAppName, lpKeyName, lpDefault, buffer, new WinDef.DWORD(buffer.length), lpFileName);
/*     */     
/* 506 */     return Native.toString(buffer);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final void writePrivateProfileString(String appName, String keyName, String string, String fileName) {
/* 511 */     if (!Kernel32.INSTANCE.WritePrivateProfileString(appName, keyName, string, fileName))
/*     */     {
/* 513 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[] getLogicalProcessorInformation() {
/*     */     Memory memory;
/* 524 */     sizePerStruct = (new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION()).size();
/* 525 */     WinDef.DWORDByReference bufferSize = new WinDef.DWORDByReference(new WinDef.DWORD(sizePerStruct));
/*     */ 
/*     */     
/*     */     while (true) {
/* 529 */       memory = new Memory(bufferSize.getValue().intValue());
/* 530 */       if (!Kernel32.INSTANCE.GetLogicalProcessorInformation(memory, bufferSize)) {
/*     */         
/* 532 */         int err = Kernel32.INSTANCE.GetLastError();
/* 533 */         if (err != 122)
/* 534 */           throw new Win32Exception(err); 
/*     */         continue;
/*     */       } 
/*     */       break;
/*     */     } 
/* 539 */     WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION firstInformation = new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION(memory);
/*     */     
/* 541 */     int returnedStructCount = bufferSize.getValue().intValue() / sizePerStruct;
/*     */     
/* 543 */     return (SYSTEM_LOGICAL_PROCESSOR_INFORMATION[])firstInformation
/* 544 */       .toArray(new WinNT.SYSTEM_LOGICAL_PROCESSOR_INFORMATION[returnedStructCount]);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public static final String[] getPrivateProfileSection(String appName, String fileName) {
/* 565 */     char[] buffer = new char[32768];
/* 566 */     if (Kernel32.INSTANCE.GetPrivateProfileSection(appName, buffer, new WinDef.DWORD(buffer.length), fileName).intValue() == 0) {
/* 567 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 569 */     return (new String(buffer)).split("\000");
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
/*     */   public static final String[] getPrivateProfileSectionNames(String fileName) {
/* 584 */     char[] buffer = new char[65536];
/* 585 */     if (Kernel32.INSTANCE.GetPrivateProfileSectionNames(buffer, new WinDef.DWORD(buffer.length), fileName).intValue() == 0) {
/* 586 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/* 588 */     return (new String(buffer)).split("\000");
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
/*     */   public static final void writePrivateProfileSection(String appName, String[] strings, String fileName) {
/* 601 */     StringBuilder buffer = new StringBuilder();
/* 602 */     for (String string : strings)
/* 603 */       buffer.append(string).append(false); 
/* 604 */     buffer.append(false);
/* 605 */     if (!Kernel32.INSTANCE.WritePrivateProfileSection(appName, buffer.toString(), fileName)) {
/* 606 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
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
/*     */   public static final List<String> queryDosDevice(String lpszDeviceName, int maxTargetSize) {
/* 618 */     char[] lpTargetPath = new char[maxTargetSize];
/* 619 */     int dwSize = Kernel32.INSTANCE.QueryDosDevice(lpszDeviceName, lpTargetPath, lpTargetPath.length);
/* 620 */     if (dwSize == 0) {
/* 621 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */     }
/*     */     
/* 624 */     return Native.toStringList(lpTargetPath, 0, dwSize);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final List<String> getVolumePathNamesForVolumeName(String lpszVolumeName) {
/* 634 */     char[] lpszVolumePathNames = new char[261];
/* 635 */     IntByReference lpcchReturnLength = new IntByReference();
/*     */     
/* 637 */     if (!Kernel32.INSTANCE.GetVolumePathNamesForVolumeName(lpszVolumeName, lpszVolumePathNames, lpszVolumePathNames.length, lpcchReturnLength)) {
/* 638 */       int hr = Kernel32.INSTANCE.GetLastError();
/* 639 */       if (hr != 234) {
/* 640 */         throw new Win32Exception(hr);
/*     */       }
/*     */       
/* 643 */       int required = lpcchReturnLength.getValue();
/* 644 */       lpszVolumePathNames = new char[required];
/*     */       
/* 646 */       if (!Kernel32.INSTANCE.GetVolumePathNamesForVolumeName(lpszVolumeName, lpszVolumePathNames, lpszVolumePathNames.length, lpcchReturnLength)) {
/* 647 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*     */     } 
/*     */     
/* 651 */     int bufSize = lpcchReturnLength.getValue();
/* 652 */     return Native.toStringList(lpszVolumePathNames, 0, bufSize);
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
/*     */ 
/*     */   
/*     */   public static final String extractVolumeGUID(String volumeGUIDPath) {
/* 672 */     if (volumeGUIDPath == null || volumeGUIDPath
/* 673 */       .length() <= "\\\\?\\Volume{".length() + "}\\".length() || 
/* 674 */       !volumeGUIDPath.startsWith("\\\\?\\Volume{") || 
/* 675 */       !volumeGUIDPath.endsWith("}\\")) {
/* 676 */       throw new IllegalArgumentException("Bad volume GUID path format: " + volumeGUIDPath);
/*     */     }
/*     */     
/* 679 */     return volumeGUIDPath.substring("\\\\?\\Volume{".length(), volumeGUIDPath.length() - "}\\".length());
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Kernel32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */