/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.Memory;
/*      */ import com.sun.jna.Native;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.WString;
/*      */ import com.sun.jna.ptr.ByteByReference;
/*      */ import com.sun.jna.ptr.IntByReference;
/*      */ import com.sun.jna.ptr.LongByReference;
/*      */ import com.sun.jna.ptr.PointerByReference;
/*      */ import java.io.ByteArrayOutputStream;
/*      */ import java.io.File;
/*      */ import java.io.IOException;
/*      */ import java.util.ArrayList;
/*      */ import java.util.HashMap;
/*      */ import java.util.Iterator;
/*      */ import java.util.Map;
/*      */ import java.util.TreeMap;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public abstract class Advapi32Util
/*      */ {
/*      */   public static class Account
/*      */   {
/*      */     public String name;
/*      */     public String domain;
/*      */     public byte[] sid;
/*      */     public String sidString;
/*      */     public int accountType;
/*      */     public String fqn;
/*      */   }
/*      */   
/*      */   public static String getUserName() {
/*  101 */     buffer = new char[128];
/*  102 */     IntByReference len = new IntByReference(buffer.length);
/*  103 */     boolean result = Advapi32.INSTANCE.GetUserNameW(buffer, len);
/*      */     
/*  105 */     if (!result) {
/*  106 */       switch (Kernel32.INSTANCE.GetLastError()) {
/*      */         case 122:
/*  108 */           buffer = new char[len.getValue()];
/*      */           break;
/*      */         
/*      */         default:
/*  112 */           throw new Win32Exception(Native.getLastError());
/*      */       } 
/*      */       
/*  115 */       result = Advapi32.INSTANCE.GetUserNameW(buffer, len);
/*      */     } 
/*      */     
/*  118 */     if (!result) {
/*  119 */       throw new Win32Exception(Native.getLastError());
/*      */     }
/*      */     
/*  122 */     return Native.toString(buffer);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  134 */   public static Account getAccountByName(String accountName) { return getAccountByName(null, accountName); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account getAccountByName(String systemName, String accountName) {
/*  147 */     IntByReference pSid = new IntByReference(false);
/*  148 */     IntByReference cchDomainName = new IntByReference(false);
/*  149 */     PointerByReference peUse = new PointerByReference();
/*      */     
/*  151 */     if (Advapi32.INSTANCE.LookupAccountName(systemName, accountName, null, pSid, null, cchDomainName, peUse))
/*      */     {
/*  153 */       throw new RuntimeException("LookupAccountNameW was expected to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */ 
/*      */     
/*  157 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  158 */     if (pSid.getValue() == 0 || rc != 122) {
/*  159 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/*  162 */     Memory sidMemory = new Memory(pSid.getValue());
/*  163 */     WinNT.PSID result = new WinNT.PSID(sidMemory);
/*  164 */     char[] referencedDomainName = new char[cchDomainName.getValue() + 1];
/*      */     
/*  166 */     if (!Advapi32.INSTANCE.LookupAccountName(systemName, accountName, result, pSid, referencedDomainName, cchDomainName, peUse))
/*      */     {
/*  168 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  171 */     Account account = new Account();
/*  172 */     account.accountType = peUse.getPointer().getInt(0L);
/*  173 */     account.name = accountName;
/*      */     
/*  175 */     String[] accountNamePartsBs = accountName.split("\\\\", 2);
/*  176 */     String[] accountNamePartsAt = accountName.split("@", 2);
/*      */     
/*  178 */     if (accountNamePartsBs.length == 2) {
/*  179 */       account.name = accountNamePartsBs[1];
/*  180 */     } else if (accountNamePartsAt.length == 2) {
/*  181 */       account.name = accountNamePartsAt[0];
/*      */     } else {
/*  183 */       account.name = accountName;
/*      */     } 
/*      */     
/*  186 */     if (cchDomainName.getValue() > 0) {
/*  187 */       account.domain = Native.toString(referencedDomainName);
/*  188 */       account.fqn = account.domain + "\\" + account.name;
/*      */     } else {
/*  190 */       account.fqn = account.name;
/*      */     } 
/*      */     
/*  193 */     account.sid = result.getBytes();
/*  194 */     account.sidString = convertSidToStringSid(new WinNT.PSID(account.sid));
/*  195 */     return account;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  206 */   public static Account getAccountBySid(WinNT.PSID sid) { return getAccountBySid(null, sid); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account getAccountBySid(String systemName, WinNT.PSID sid) {
/*  219 */     IntByReference cchName = new IntByReference();
/*  220 */     IntByReference cchDomainName = new IntByReference();
/*  221 */     PointerByReference peUse = new PointerByReference();
/*      */     
/*  223 */     if (Advapi32.INSTANCE.LookupAccountSid(null, sid, null, cchName, null, cchDomainName, peUse))
/*      */     {
/*  225 */       throw new RuntimeException("LookupAccountSidW was expected to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */ 
/*      */     
/*  229 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  230 */     if (cchName.getValue() == 0 || rc != 122)
/*      */     {
/*  232 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/*  235 */     char[] domainName = new char[cchDomainName.getValue()];
/*  236 */     char[] name = new char[cchName.getValue()];
/*      */     
/*  238 */     if (!Advapi32.INSTANCE.LookupAccountSid(null, sid, name, cchName, domainName, cchDomainName, peUse))
/*      */     {
/*  240 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */     
/*  243 */     Account account = new Account();
/*  244 */     account.accountType = peUse.getPointer().getInt(0L);
/*  245 */     account.name = Native.toString(name);
/*      */     
/*  247 */     if (cchDomainName.getValue() > 0) {
/*  248 */       account.domain = Native.toString(domainName);
/*  249 */       account.fqn = account.domain + "\\" + account.name;
/*      */     } else {
/*  251 */       account.fqn = account.name;
/*      */     } 
/*      */     
/*  254 */     account.sid = sid.getBytes();
/*  255 */     account.sidString = convertSidToStringSid(sid);
/*  256 */     return account;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String convertSidToStringSid(WinNT.PSID sid) {
/*  268 */     PointerByReference stringSid = new PointerByReference();
/*  269 */     if (!Advapi32.INSTANCE.ConvertSidToStringSid(sid, stringSid)) {
/*  270 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  272 */     String result = stringSid.getValue().getWideString(0L);
/*  273 */     Kernel32.INSTANCE.LocalFree(stringSid.getValue());
/*  274 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] convertStringSidToSid(String sidString) {
/*  286 */     WinNT.PSIDByReference pSID = new WinNT.PSIDByReference();
/*  287 */     if (!Advapi32.INSTANCE.ConvertStringSidToSid(sidString, pSID)) {
/*  288 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  290 */     return pSID.getValue().getBytes();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isWellKnownSid(String sidString, int wellKnownSidType) {
/*  304 */     WinNT.PSIDByReference pSID = new WinNT.PSIDByReference();
/*  305 */     if (!Advapi32.INSTANCE.ConvertStringSidToSid(sidString, pSID)) {
/*  306 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  308 */     return Advapi32.INSTANCE.IsWellKnownSid(pSID.getValue(), wellKnownSidType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean isWellKnownSid(byte[] sidBytes, int wellKnownSidType) {
/*  323 */     WinNT.PSID pSID = new WinNT.PSID(sidBytes);
/*  324 */     return Advapi32.INSTANCE.IsWellKnownSid(pSID, wellKnownSidType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  335 */   public static Account getAccountBySid(String sidString) { return getAccountBySid(null, sidString); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  348 */   public static Account getAccountBySid(String systemName, String sidString) { return getAccountBySid(systemName, new WinNT.PSID(
/*  349 */           convertStringSidToSid(sidString))); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account[] getTokenGroups(WinNT.HANDLE hToken) {
/*  362 */     IntByReference tokenInformationLength = new IntByReference();
/*  363 */     if (Advapi32.INSTANCE.GetTokenInformation(hToken, 2, null, 0, tokenInformationLength))
/*      */     {
/*      */       
/*  366 */       throw new RuntimeException("Expected GetTokenInformation to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */     
/*  369 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  370 */     if (rc != 122) {
/*  371 */       throw new Win32Exception(rc);
/*      */     }
/*      */ 
/*      */     
/*  375 */     WinNT.TOKEN_GROUPS groups = new WinNT.TOKEN_GROUPS(tokenInformationLength.getValue());
/*  376 */     if (!Advapi32.INSTANCE.GetTokenInformation(hToken, 2, groups, tokenInformationLength
/*      */         
/*  378 */         .getValue(), tokenInformationLength)) {
/*  379 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  381 */     ArrayList<Account> userGroups = new ArrayList<Account>();
/*      */     
/*  383 */     for (WinNT.SID_AND_ATTRIBUTES sidAndAttribute : groups.getGroups()) {
/*  384 */       Account group = null;
/*      */       try {
/*  386 */         group = getAccountBySid(sidAndAttribute.Sid);
/*  387 */       } catch (Exception e) {
/*  388 */         group = new Account();
/*  389 */         group.sid = sidAndAttribute.Sid.getBytes();
/*  390 */         group
/*  391 */           .sidString = convertSidToStringSid(sidAndAttribute.Sid);
/*  392 */         group.name = group.sidString;
/*  393 */         group.fqn = group.sidString;
/*  394 */         group.accountType = 2;
/*      */       } 
/*  396 */       userGroups.add(group);
/*      */     } 
/*  398 */     return (Account[])userGroups.toArray(new Account[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account getTokenAccount(WinNT.HANDLE hToken) {
/*  411 */     IntByReference tokenInformationLength = new IntByReference();
/*  412 */     if (Advapi32.INSTANCE.GetTokenInformation(hToken, 1, null, 0, tokenInformationLength))
/*      */     {
/*      */       
/*  415 */       throw new RuntimeException("Expected GetTokenInformation to fail with ERROR_INSUFFICIENT_BUFFER");
/*      */     }
/*      */     
/*  418 */     int rc = Kernel32.INSTANCE.GetLastError();
/*  419 */     if (rc != 122) {
/*  420 */       throw new Win32Exception(rc);
/*      */     }
/*      */ 
/*      */     
/*  424 */     WinNT.TOKEN_USER user = new WinNT.TOKEN_USER(tokenInformationLength.getValue());
/*  425 */     if (!Advapi32.INSTANCE.GetTokenInformation(hToken, 1, user, tokenInformationLength
/*      */         
/*  427 */         .getValue(), tokenInformationLength)) {
/*  428 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*  430 */     return getAccountBySid(user.User.Sid);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Account[] getCurrentUserGroups() {
/*  439 */     phToken = new WinNT.HANDLEByReference();
/*      */     
/*      */     try {
/*  442 */       WinNT.HANDLE threadHandle = Kernel32.INSTANCE.GetCurrentThread();
/*  443 */       if (!Advapi32.INSTANCE.OpenThreadToken(threadHandle, 10, true, phToken)) {
/*      */         
/*  445 */         if (1008 != Kernel32.INSTANCE
/*  446 */           .GetLastError()) {
/*  447 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/*  449 */         WinNT.HANDLE processHandle = Kernel32.INSTANCE.GetCurrentProcess();
/*  450 */         if (!Advapi32.INSTANCE.OpenProcessToken(processHandle, 10, phToken))
/*      */         {
/*  452 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/*      */       } 
/*  455 */       return getTokenGroups(phToken.getValue());
/*      */     } finally {
/*  457 */       if (phToken.getValue() != WinBase.INVALID_HANDLE_VALUE && 
/*  458 */         !Kernel32.INSTANCE.CloseHandle(phToken.getValue())) {
/*  459 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean registryKeyExists(WinReg.HKEY root, String key) {
/*  475 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/*  476 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  478 */     switch (rc) {
/*      */       case 0:
/*  480 */         Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  481 */         return true;
/*      */       case 2:
/*  483 */         return false;
/*      */     } 
/*  485 */     throw new Win32Exception(rc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean registryValueExists(WinReg.HKEY root, String key, String value) {
/*  502 */     phkKey = new WinReg.HKEYByReference();
/*  503 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*      */     try {
/*  506 */       switch (rc) {
/*      */         case 0:
/*      */           break;
/*      */         case 2:
/*  510 */           return false;
/*      */         default:
/*  512 */           throw new Win32Exception(rc);
/*      */       } 
/*  514 */       IntByReference lpcbData = new IntByReference();
/*  515 */       IntByReference lpType = new IntByReference();
/*  516 */       rc = Advapi32.INSTANCE.RegQueryValueEx(phkKey.getValue(), value, 0, lpType, (char[])null, lpcbData);
/*      */       
/*  518 */       switch (rc) {
/*      */         case 0:
/*      */         case 122:
/*      */         case 234:
/*  522 */           return true;
/*      */         case 2:
/*  524 */           return false;
/*      */       } 
/*  526 */       throw new Win32Exception(rc);
/*      */     } finally {
/*      */       
/*  529 */       if (phkKey.getValue() != WinBase.INVALID_HANDLE_VALUE) {
/*  530 */         rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  531 */         if (rc != 0) {
/*  532 */           throw new Win32Exception(rc);
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String registryGetStringValue(WinReg.HKEY root, String key, String value) {
/*  551 */     phkKey = new WinReg.HKEYByReference();
/*  552 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  554 */     if (rc != 0) {
/*  555 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  558 */       return registryGetStringValue(phkKey.getValue(), value);
/*      */     } finally {
/*  560 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  561 */       if (rc != 0) {
/*  562 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String registryGetStringValue(WinReg.HKEY hKey, String value) {
/*  577 */     IntByReference lpcbData = new IntByReference();
/*  578 */     IntByReference lpType = new IntByReference();
/*  579 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  581 */     if (rc != 0 && rc != 122)
/*      */     {
/*  583 */       throw new Win32Exception(rc);
/*      */     }
/*  585 */     if (lpType.getValue() != 1 && lpType
/*  586 */       .getValue() != 2) {
/*  587 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  588 */           .getValue() + ", expected REG_SZ or REG_EXPAND_SZ");
/*      */     }
/*      */     
/*  591 */     char[] data = new char[lpcbData.getValue()];
/*  592 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  594 */     if (rc != 0 && rc != 122)
/*      */     {
/*  596 */       throw new Win32Exception(rc);
/*      */     }
/*  598 */     return Native.toString(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String registryGetExpandableStringValue(WinReg.HKEY root, String key, String value) {
/*  614 */     phkKey = new WinReg.HKEYByReference();
/*  615 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  617 */     if (rc != 0) {
/*  618 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  621 */       return registryGetExpandableStringValue(phkKey.getValue(), value);
/*      */     } finally {
/*  623 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  624 */       if (rc != 0) {
/*  625 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String registryGetExpandableStringValue(WinReg.HKEY hKey, String value) {
/*  640 */     IntByReference lpcbData = new IntByReference();
/*  641 */     IntByReference lpType = new IntByReference();
/*  642 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  644 */     if (rc != 0 && rc != 122)
/*      */     {
/*  646 */       throw new Win32Exception(rc);
/*      */     }
/*  648 */     if (lpType.getValue() != 2) {
/*  649 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  650 */           .getValue() + ", expected REG_SZ");
/*      */     }
/*  652 */     char[] data = new char[lpcbData.getValue()];
/*  653 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  655 */     if (rc != 0 && rc != 122)
/*      */     {
/*  657 */       throw new Win32Exception(rc);
/*      */     }
/*  659 */     return Native.toString(data);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] registryGetStringArray(WinReg.HKEY root, String key, String value) {
/*  675 */     phkKey = new WinReg.HKEYByReference();
/*  676 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  678 */     if (rc != 0) {
/*  679 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  682 */       return registryGetStringArray(phkKey.getValue(), value);
/*      */     } finally {
/*  684 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  685 */       if (rc != 0) {
/*  686 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] registryGetStringArray(WinReg.HKEY hKey, String value) {
/*  701 */     IntByReference lpcbData = new IntByReference();
/*  702 */     IntByReference lpType = new IntByReference();
/*  703 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  705 */     if (rc != 0 && rc != 122)
/*      */     {
/*  707 */       throw new Win32Exception(rc);
/*      */     }
/*  709 */     if (lpType.getValue() != 7) {
/*  710 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  711 */           .getValue() + ", expected REG_SZ");
/*      */     }
/*  713 */     Memory data = new Memory(lpcbData.getValue());
/*  714 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  716 */     if (rc != 0 && rc != 122)
/*      */     {
/*  718 */       throw new Win32Exception(rc);
/*      */     }
/*  720 */     ArrayList<String> result = new ArrayList<String>();
/*  721 */     int offset = 0;
/*  722 */     while (offset < data.size()) {
/*  723 */       String s = data.getWideString(offset);
/*  724 */       offset += s.length() * Native.WCHAR_SIZE;
/*  725 */       offset += Native.WCHAR_SIZE;
/*  726 */       if (s.length() == 0 && offset == data.size()) {
/*      */         continue;
/*      */       }
/*  729 */       result.add(s);
/*      */     } 
/*      */     
/*  732 */     return (String[])result.toArray(new String[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] registryGetBinaryValue(WinReg.HKEY root, String key, String value) {
/*  748 */     phkKey = new WinReg.HKEYByReference();
/*  749 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  751 */     if (rc != 0) {
/*  752 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  755 */       return registryGetBinaryValue(phkKey.getValue(), value);
/*      */     } finally {
/*  757 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  758 */       if (rc != 0) {
/*  759 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static byte[] registryGetBinaryValue(WinReg.HKEY hKey, String value) {
/*  774 */     IntByReference lpcbData = new IntByReference();
/*  775 */     IntByReference lpType = new IntByReference();
/*  776 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  778 */     if (rc != 0 && rc != 122)
/*      */     {
/*  780 */       throw new Win32Exception(rc);
/*      */     }
/*  782 */     if (lpType.getValue() != 3) {
/*  783 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  784 */           .getValue() + ", expected REG_BINARY");
/*      */     }
/*  786 */     byte[] data = new byte[lpcbData.getValue()];
/*  787 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  789 */     if (rc != 0 && rc != 122)
/*      */     {
/*  791 */       throw new Win32Exception(rc);
/*      */     }
/*  793 */     return data;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int registryGetIntValue(WinReg.HKEY root, String key, String value) {
/*  808 */     phkKey = new WinReg.HKEYByReference();
/*  809 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  811 */     if (rc != 0) {
/*  812 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  815 */       return registryGetIntValue(phkKey.getValue(), value);
/*      */     } finally {
/*  817 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  818 */       if (rc != 0) {
/*  819 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int registryGetIntValue(WinReg.HKEY hKey, String value) {
/*  834 */     IntByReference lpcbData = new IntByReference();
/*  835 */     IntByReference lpType = new IntByReference();
/*  836 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  838 */     if (rc != 0 && rc != 122)
/*      */     {
/*  840 */       throw new Win32Exception(rc);
/*      */     }
/*  842 */     if (lpType.getValue() != 4) {
/*  843 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  844 */           .getValue() + ", expected REG_DWORD");
/*      */     }
/*  846 */     IntByReference data = new IntByReference();
/*  847 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  849 */     if (rc != 0 && rc != 122)
/*      */     {
/*  851 */       throw new Win32Exception(rc);
/*      */     }
/*  853 */     return data.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long registryGetLongValue(WinReg.HKEY root, String key, String value) {
/*  868 */     phkKey = new WinReg.HKEYByReference();
/*  869 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, key, 0, 131097, phkKey);
/*      */     
/*  871 */     if (rc != 0) {
/*  872 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/*  875 */       return registryGetLongValue(phkKey.getValue(), value);
/*      */     } finally {
/*  877 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/*  878 */       if (rc != 0) {
/*  879 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static long registryGetLongValue(WinReg.HKEY hKey, String value) {
/*  894 */     IntByReference lpcbData = new IntByReference();
/*  895 */     IntByReference lpType = new IntByReference();
/*  896 */     int rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, (char[])null, lpcbData);
/*      */     
/*  898 */     if (rc != 0 && rc != 122)
/*      */     {
/*  900 */       throw new Win32Exception(rc);
/*      */     }
/*  902 */     if (lpType.getValue() != 11) {
/*  903 */       throw new RuntimeException("Unexpected registry type " + lpType
/*  904 */           .getValue() + ", expected REG_QWORD");
/*      */     }
/*  906 */     LongByReference data = new LongByReference();
/*  907 */     rc = Advapi32.INSTANCE.RegQueryValueEx(hKey, value, 0, lpType, data, lpcbData);
/*      */     
/*  909 */     if (rc != 0 && rc != 122)
/*      */     {
/*  911 */       throw new Win32Exception(rc);
/*      */     }
/*  913 */     return data.getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Object registryGetValue(WinReg.HKEY hkKey, String subKey, String lpValueName) {
/*  930 */     Object result = null;
/*  931 */     IntByReference lpType = new IntByReference();
/*  932 */     byte[] lpData = new byte[16383];
/*  933 */     IntByReference lpcbData = new IntByReference('㿿');
/*      */     
/*  935 */     int rc = Advapi32.INSTANCE.RegGetValue(hkKey, subKey, lpValueName, 65535, lpType, lpData, lpcbData);
/*      */ 
/*      */ 
/*      */     
/*  939 */     if (lpType.getValue() == 0) {
/*  940 */       return null;
/*      */     }
/*  942 */     if (rc != 0 && rc != 122)
/*      */     {
/*  944 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/*  947 */     Memory byteData = new Memory(lpcbData.getValue());
/*  948 */     byteData.write(0L, lpData, 0, lpcbData.getValue());
/*      */     
/*  950 */     if (lpType.getValue() == 4) {
/*  951 */       result = new Integer(byteData.getInt(0L));
/*  952 */     } else if (lpType.getValue() == 11) {
/*  953 */       result = new Long(byteData.getLong(0L));
/*  954 */     } else if (lpType.getValue() == 3) {
/*  955 */       byte[] arrayOfByte = byteData.getByteArray(0L, lpcbData.getValue());
/*  956 */     } else if (lpType.getValue() == 1 || lpType
/*  957 */       .getValue() == 2) {
/*  958 */       result = byteData.getWideString(0L);
/*      */     } 
/*      */     
/*  961 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean registryCreateKey(WinReg.HKEY hKey, String keyName) {
/*  974 */     WinReg.HKEYByReference phkResult = new WinReg.HKEYByReference();
/*  975 */     IntByReference lpdwDisposition = new IntByReference();
/*  976 */     int rc = Advapi32.INSTANCE.RegCreateKeyEx(hKey, keyName, 0, null, 0, 131097, null, phkResult, lpdwDisposition);
/*      */ 
/*      */     
/*  979 */     if (rc != 0) {
/*  980 */       throw new Win32Exception(rc);
/*      */     }
/*  982 */     rc = Advapi32.INSTANCE.RegCloseKey(phkResult.getValue());
/*  983 */     if (rc != 0) {
/*  984 */       throw new Win32Exception(rc);
/*      */     }
/*  986 */     return (1 == lpdwDisposition.getValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean registryCreateKey(WinReg.HKEY root, String parentPath, String keyName) {
/* 1002 */     phkKey = new WinReg.HKEYByReference();
/* 1003 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, parentPath, 0, 4, phkKey);
/*      */     
/* 1005 */     if (rc != 0) {
/* 1006 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1009 */       return registryCreateKey(phkKey.getValue(), keyName);
/*      */     } finally {
/* 1011 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1012 */       if (rc != 0) {
/* 1013 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetIntValue(WinReg.HKEY hKey, String name, int value) {
/* 1029 */     byte[] data = new byte[4];
/* 1030 */     data[0] = (byte)(value & 0xFF);
/* 1031 */     data[1] = (byte)(value >> 8 & 0xFF);
/* 1032 */     data[2] = (byte)(value >> 16 & 0xFF);
/* 1033 */     data[3] = (byte)(value >> 24 & 0xFF);
/* 1034 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 4, data, 4);
/*      */     
/* 1036 */     if (rc != 0) {
/* 1037 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetIntValue(WinReg.HKEY root, String keyPath, String name, int value) {
/* 1055 */     phkKey = new WinReg.HKEYByReference();
/* 1056 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1058 */     if (rc != 0) {
/* 1059 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1062 */       registrySetIntValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1064 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1065 */       if (rc != 0) {
/* 1066 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetLongValue(WinReg.HKEY hKey, String name, long value) {
/* 1082 */     byte[] data = new byte[8];
/* 1083 */     data[0] = (byte)(int)(value & 0xFFL);
/* 1084 */     data[1] = (byte)(int)(value >> 8 & 0xFFL);
/* 1085 */     data[2] = (byte)(int)(value >> 16 & 0xFFL);
/* 1086 */     data[3] = (byte)(int)(value >> 24 & 0xFFL);
/* 1087 */     data[4] = (byte)(int)(value >> 32 & 0xFFL);
/* 1088 */     data[5] = (byte)(int)(value >> 40 & 0xFFL);
/* 1089 */     data[6] = (byte)(int)(value >> 48 & 0xFFL);
/* 1090 */     data[7] = (byte)(int)(value >> 56 & 0xFFL);
/* 1091 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 11, data, 8);
/*      */     
/* 1093 */     if (rc != 0) {
/* 1094 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetLongValue(WinReg.HKEY root, String keyPath, String name, long value) {
/* 1112 */     phkKey = new WinReg.HKEYByReference();
/* 1113 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1115 */     if (rc != 0) {
/* 1116 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1119 */       registrySetLongValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1121 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1122 */       if (rc != 0) {
/* 1123 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetStringValue(WinReg.HKEY hKey, String name, String value) {
/* 1140 */     char[] data = Native.toCharArray(value);
/* 1141 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 1, data, data.length * Native.WCHAR_SIZE);
/*      */     
/* 1143 */     if (rc != 0) {
/* 1144 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetStringValue(WinReg.HKEY root, String keyPath, String name, String value) {
/* 1162 */     phkKey = new WinReg.HKEYByReference();
/* 1163 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1165 */     if (rc != 0) {
/* 1166 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1169 */       registrySetStringValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1171 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1172 */       if (rc != 0) {
/* 1173 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetExpandableStringValue(WinReg.HKEY hKey, String name, String value) {
/* 1190 */     char[] data = Native.toCharArray(value);
/* 1191 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 2, data, data.length * Native.WCHAR_SIZE);
/*      */     
/* 1193 */     if (rc != 0) {
/* 1194 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetExpandableStringValue(WinReg.HKEY root, String keyPath, String name, String value) {
/* 1212 */     phkKey = new WinReg.HKEYByReference();
/* 1213 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1215 */     if (rc != 0) {
/* 1216 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1219 */       registrySetExpandableStringValue(phkKey.getValue(), name, value);
/*      */     } finally {
/* 1221 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1222 */       if (rc != 0) {
/* 1223 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetStringArray(WinReg.HKEY hKey, String name, String[] arr) {
/* 1240 */     int size = 0;
/* 1241 */     for (String s : arr) {
/* 1242 */       size += s.length() * Native.WCHAR_SIZE;
/* 1243 */       size += Native.WCHAR_SIZE;
/*      */     } 
/* 1245 */     size += Native.WCHAR_SIZE;
/*      */     
/* 1247 */     int offset = 0;
/* 1248 */     Memory data = new Memory(size);
/* 1249 */     for (String s : arr) {
/* 1250 */       data.setWideString(offset, s);
/* 1251 */       offset += s.length() * Native.WCHAR_SIZE;
/* 1252 */       offset += Native.WCHAR_SIZE;
/*      */     } 
/* 1254 */     for (i = 0; i < Native.WCHAR_SIZE; i++) {
/* 1255 */       data.setByte(offset++, (byte)0);
/*      */     }
/*      */     
/* 1258 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 7, data
/* 1259 */         .getByteArray(0L, size), size);
/*      */     
/* 1261 */     if (rc != 0) {
/* 1262 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetStringArray(WinReg.HKEY root, String keyPath, String name, String[] arr) {
/* 1280 */     phkKey = new WinReg.HKEYByReference();
/* 1281 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1283 */     if (rc != 0) {
/* 1284 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1287 */       registrySetStringArray(phkKey.getValue(), name, arr);
/*      */     } finally {
/* 1289 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1290 */       if (rc != 0) {
/* 1291 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetBinaryValue(WinReg.HKEY hKey, String name, byte[] data) {
/* 1308 */     int rc = Advapi32.INSTANCE.RegSetValueEx(hKey, name, 0, 3, data, data.length);
/*      */     
/* 1310 */     if (rc != 0) {
/* 1311 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registrySetBinaryValue(WinReg.HKEY root, String keyPath, String name, byte[] data) {
/* 1329 */     phkKey = new WinReg.HKEYByReference();
/* 1330 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1332 */     if (rc != 0) {
/* 1333 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1336 */       registrySetBinaryValue(phkKey.getValue(), name, data);
/*      */     } finally {
/* 1338 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1339 */       if (rc != 0) {
/* 1340 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryDeleteKey(WinReg.HKEY hKey, String keyName) {
/* 1354 */     int rc = Advapi32.INSTANCE.RegDeleteKey(hKey, keyName);
/* 1355 */     if (rc != 0) {
/* 1356 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryDeleteKey(WinReg.HKEY root, String keyPath, String keyName) {
/* 1372 */     phkKey = new WinReg.HKEYByReference();
/* 1373 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1375 */     if (rc != 0) {
/* 1376 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1379 */       registryDeleteKey(phkKey.getValue(), keyName);
/*      */     } finally {
/* 1381 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1382 */       if (rc != 0) {
/* 1383 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryDeleteValue(WinReg.HKEY hKey, String valueName) {
/* 1397 */     int rc = Advapi32.INSTANCE.RegDeleteValue(hKey, valueName);
/* 1398 */     if (rc != 0) {
/* 1399 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryDeleteValue(WinReg.HKEY root, String keyPath, String valueName) {
/* 1415 */     phkKey = new WinReg.HKEYByReference();
/* 1416 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131103, phkKey);
/*      */     
/* 1418 */     if (rc != 0) {
/* 1419 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1422 */       registryDeleteValue(phkKey.getValue(), valueName);
/*      */     } finally {
/* 1424 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1425 */       if (rc != 0) {
/* 1426 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] registryGetKeys(WinReg.HKEY hKey) {
/* 1439 */     IntByReference lpcSubKeys = new IntByReference();
/* 1440 */     IntByReference lpcMaxSubKeyLen = new IntByReference();
/*      */     
/* 1442 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, null, null, null, lpcSubKeys, lpcMaxSubKeyLen, null, null, null, null, null, null);
/*      */     
/* 1444 */     if (rc != 0) {
/* 1445 */       throw new Win32Exception(rc);
/*      */     }
/* 1447 */     ArrayList<String> keys = new ArrayList<String>(lpcSubKeys.getValue());
/* 1448 */     char[] name = new char[lpcMaxSubKeyLen.getValue() + 1];
/* 1449 */     for (int i = 0; i < lpcSubKeys.getValue(); i++) {
/*      */       
/* 1451 */       IntByReference lpcchValueName = new IntByReference(lpcMaxSubKeyLen.getValue() + 1);
/* 1452 */       rc = Advapi32.INSTANCE.RegEnumKeyEx(hKey, i, name, lpcchValueName, null, null, null, null);
/*      */       
/* 1454 */       if (rc != 0) {
/* 1455 */         throw new Win32Exception(rc);
/*      */       }
/* 1457 */       keys.add(Native.toString(name));
/*      */     } 
/* 1459 */     return (String[])keys.toArray(new String[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String[] registryGetKeys(WinReg.HKEY root, String keyPath) {
/* 1472 */     phkKey = new WinReg.HKEYByReference();
/* 1473 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131097, phkKey);
/*      */     
/* 1475 */     if (rc != 0) {
/* 1476 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1479 */       return registryGetKeys(phkKey.getValue());
/*      */     } finally {
/* 1481 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1482 */       if (rc != 0) {
/* 1483 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WinReg.HKEYByReference registryGetKey(WinReg.HKEY root, String keyPath, int samDesired) {
/* 1503 */     WinReg.HKEYByReference phkKey = new WinReg.HKEYByReference();
/* 1504 */     int rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, samDesired, phkKey);
/*      */     
/* 1506 */     if (rc != 0) {
/* 1507 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 1510 */     return phkKey;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void registryCloseKey(WinReg.HKEY hKey) {
/* 1520 */     int rc = Advapi32.INSTANCE.RegCloseKey(hKey);
/* 1521 */     if (rc != 0) {
/* 1522 */       throw new Win32Exception(rc);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static TreeMap<String, Object> registryGetValues(WinReg.HKEY hKey) {
/* 1534 */     IntByReference lpcValues = new IntByReference();
/* 1535 */     IntByReference lpcMaxValueNameLen = new IntByReference();
/* 1536 */     IntByReference lpcMaxValueLen = new IntByReference();
/* 1537 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, null, null, null, null, null, null, lpcValues, lpcMaxValueNameLen, lpcMaxValueLen, null, null);
/*      */ 
/*      */     
/* 1540 */     if (rc != 0) {
/* 1541 */       throw new Win32Exception(rc);
/*      */     }
/* 1543 */     TreeMap<String, Object> keyValues = new TreeMap<String, Object>();
/* 1544 */     char[] name = new char[lpcMaxValueNameLen.getValue() + 1];
/* 1545 */     byte[] data = new byte[lpcMaxValueLen.getValue()];
/* 1546 */     for (int i = 0; i < lpcValues.getValue(); i++) {
/*      */       
/* 1548 */       IntByReference lpcchValueName = new IntByReference(lpcMaxValueNameLen.getValue() + 1);
/*      */       
/* 1550 */       IntByReference lpcbData = new IntByReference(lpcMaxValueLen.getValue());
/* 1551 */       IntByReference lpType = new IntByReference();
/* 1552 */       rc = Advapi32.INSTANCE.RegEnumValue(hKey, i, name, lpcchValueName, null, lpType, data, lpcbData);
/*      */       
/* 1554 */       if (rc != 0) {
/* 1555 */         throw new Win32Exception(rc);
/*      */       }
/*      */       
/* 1558 */       String nameString = Native.toString(name);
/*      */       
/* 1560 */       if (lpcbData.getValue() == 0) {
/* 1561 */         switch (lpType.getValue()) {
/*      */           case 3:
/* 1563 */             keyValues.put(nameString, new byte[0]);
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 2:
/* 1568 */             keyValues.put(nameString, new char[0]);
/*      */             break;
/*      */           
/*      */           case 7:
/* 1572 */             keyValues.put(nameString, new String[0]);
/*      */             break;
/*      */           
/*      */           case 0:
/* 1576 */             keyValues.put(nameString, null);
/*      */             break;
/*      */           
/*      */           default:
/* 1580 */             throw new RuntimeException("Unsupported empty type: " + lpType
/* 1581 */                 .getValue());
/*      */         } 
/*      */       } else {
/*      */         int offset;
/*      */         ArrayList<String> result;
/* 1586 */         Memory stringData, byteData = new Memory(lpcbData.getValue());
/* 1587 */         byteData.write(0L, data, 0, lpcbData.getValue());
/*      */         
/* 1589 */         switch (lpType.getValue()) {
/*      */           case 11:
/* 1591 */             keyValues.put(nameString, Long.valueOf(byteData.getLong(0L)));
/*      */             break;
/*      */           
/*      */           case 4:
/* 1595 */             keyValues.put(nameString, Integer.valueOf(byteData.getInt(0L)));
/*      */             break;
/*      */           
/*      */           case 1:
/*      */           case 2:
/* 1600 */             keyValues.put(nameString, byteData.getWideString(0L));
/*      */             break;
/*      */           
/*      */           case 3:
/* 1604 */             keyValues.put(nameString, byteData
/* 1605 */                 .getByteArray(0L, lpcbData.getValue()));
/*      */             break;
/*      */           
/*      */           case 7:
/* 1609 */             stringData = new Memory(lpcbData.getValue());
/* 1610 */             stringData.write(0L, data, 0, lpcbData.getValue());
/* 1611 */             result = new ArrayList<String>();
/* 1612 */             offset = 0;
/* 1613 */             while (offset < stringData.size()) {
/* 1614 */               String s = stringData.getWideString(offset);
/* 1615 */               offset += s.length() * Native.WCHAR_SIZE;
/* 1616 */               offset += Native.WCHAR_SIZE;
/* 1617 */               if (s.length() == 0 && offset == stringData.size()) {
/*      */                 continue;
/*      */               }
/* 1620 */               result.add(s);
/*      */             } 
/*      */             
/* 1623 */             keyValues.put(nameString, result.toArray(new String[0]));
/*      */             break;
/*      */           
/*      */           default:
/* 1627 */             throw new RuntimeException("Unsupported type: " + lpType
/* 1628 */                 .getValue());
/*      */         } 
/*      */       } 
/* 1631 */     }  return keyValues;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static TreeMap<String, Object> registryGetValues(WinReg.HKEY root, String keyPath) {
/* 1645 */     phkKey = new WinReg.HKEYByReference();
/* 1646 */     rc = Advapi32.INSTANCE.RegOpenKeyEx(root, keyPath, 0, 131097, phkKey);
/*      */     
/* 1648 */     if (rc != 0) {
/* 1649 */       throw new Win32Exception(rc);
/*      */     }
/*      */     try {
/* 1652 */       return registryGetValues(phkKey.getValue());
/*      */     } finally {
/* 1654 */       rc = Advapi32.INSTANCE.RegCloseKey(phkKey.getValue());
/* 1655 */       if (rc != 0) {
/* 1656 */         throw new Win32Exception(rc);
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static InfoKey registryQueryInfoKey(WinReg.HKEY hKey, int lpcbSecurityDescriptor) {
/* 1673 */     InfoKey infoKey = new InfoKey(hKey, lpcbSecurityDescriptor);
/* 1674 */     int rc = Advapi32.INSTANCE.RegQueryInfoKey(hKey, infoKey.lpClass, infoKey.lpcClass, null, infoKey.lpcSubKeys, infoKey.lpcMaxSubKeyLen, infoKey.lpcMaxClassLen, infoKey.lpcValues, infoKey.lpcMaxValueNameLen, infoKey.lpcMaxValueLen, infoKey.lpcbSecurityDescriptor, infoKey.lpftLastWriteTime);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1681 */     if (rc != 0) {
/* 1682 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 1685 */     return infoKey;
/*      */   }
/*      */   
/*      */   public static class InfoKey {
/*      */     public WinReg.HKEY hKey;
/* 1690 */     public char[] lpClass = new char[260];
/* 1691 */     public IntByReference lpcClass = new IntByReference('Ą');
/* 1692 */     public IntByReference lpcSubKeys = new IntByReference();
/* 1693 */     public IntByReference lpcMaxSubKeyLen = new IntByReference();
/* 1694 */     public IntByReference lpcMaxClassLen = new IntByReference();
/* 1695 */     public IntByReference lpcValues = new IntByReference();
/* 1696 */     public IntByReference lpcMaxValueNameLen = new IntByReference();
/* 1697 */     public IntByReference lpcMaxValueLen = new IntByReference();
/* 1698 */     public IntByReference lpcbSecurityDescriptor = new IntByReference();
/* 1699 */     public WinBase.FILETIME lpftLastWriteTime = new WinBase.FILETIME();
/*      */ 
/*      */     
/*      */     public InfoKey() {}
/*      */     
/*      */     public InfoKey(WinReg.HKEY hKey, int securityDescriptor) {
/* 1705 */       this.hKey = hKey;
/* 1706 */       this.lpcbSecurityDescriptor = new IntByReference(securityDescriptor);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static EnumKey registryRegEnumKey(WinReg.HKEY hKey, int dwIndex) {
/* 1720 */     EnumKey enumKey = new EnumKey(hKey, dwIndex);
/* 1721 */     int rc = Advapi32.INSTANCE.RegEnumKeyEx(hKey, enumKey.dwIndex, enumKey.lpName, enumKey.lpcName, null, enumKey.lpClass, enumKey.lpcbClass, enumKey.lpftLastWriteTime);
/*      */ 
/*      */ 
/*      */     
/* 1725 */     if (rc != 0) {
/* 1726 */       throw new Win32Exception(rc);
/*      */     }
/*      */     
/* 1729 */     return enumKey;
/*      */   }
/*      */   
/*      */   public static class EnumKey {
/*      */     public WinReg.HKEY hKey;
/* 1734 */     public int dwIndex = 0;
/* 1735 */     public char[] lpName = new char[255];
/* 1736 */     public IntByReference lpcName = new IntByReference('ÿ');
/*      */     
/* 1738 */     public char[] lpClass = new char[255];
/* 1739 */     public IntByReference lpcbClass = new IntByReference('ÿ');
/*      */     
/* 1741 */     public WinBase.FILETIME lpftLastWriteTime = new WinBase.FILETIME();
/*      */ 
/*      */     
/*      */     public EnumKey() {}
/*      */     
/*      */     public EnumKey(WinReg.HKEY hKey, int dwIndex) {
/* 1747 */       this.hKey = hKey;
/* 1748 */       this.dwIndex = dwIndex;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String getEnvironmentBlock(Map<String, String> environment) {
/* 1763 */     StringBuilder out = new StringBuilder(environment.size() * 32);
/* 1764 */     for (Map.Entry<String, String> entry : environment.entrySet()) {
/* 1765 */       String key = (String)entry.getKey(), value = (String)entry.getValue();
/* 1766 */       if (value != null) {
/* 1767 */         out.append(key).append("=").append(value).append(false);
/*      */       }
/*      */     } 
/* 1770 */     return out.append(false).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public enum EventLogType
/*      */   {
/* 1777 */     Error, Warning, Informational, AuditSuccess, AuditFailure;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class EventLogRecord
/*      */   {
/*      */     private WinNT.EVENTLOGRECORD _record;
/*      */ 
/*      */     
/*      */     private String _source;
/*      */ 
/*      */     
/*      */     private byte[] _data;
/*      */     
/*      */     private String[] _strings;
/*      */ 
/*      */     
/* 1795 */     public WinNT.EVENTLOGRECORD getRecord() { return this._record; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1804 */     public int getEventId() { return this._record.EventID.intValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1813 */     public String getSource() { return this._source; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1822 */     public int getStatusCode() { return this._record.EventID.intValue() & 0xFFFF; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1833 */     public int getRecordNumber() { return this._record.RecordNumber.intValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1842 */     public int getLength() { return this._record.Length.intValue(); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1851 */     public String[] getStrings() { return this._strings; }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Advapi32Util.EventLogType getType() {
/* 1860 */       switch (this._record.EventType.intValue()) {
/*      */         case 0:
/*      */         case 4:
/* 1863 */           return Advapi32Util.EventLogType.Informational;
/*      */         case 16:
/* 1865 */           return Advapi32Util.EventLogType.AuditFailure;
/*      */         case 8:
/* 1867 */           return Advapi32Util.EventLogType.AuditSuccess;
/*      */         case 1:
/* 1869 */           return Advapi32Util.EventLogType.Error;
/*      */         case 2:
/* 1871 */           return Advapi32Util.EventLogType.Warning;
/*      */       } 
/* 1873 */       throw new RuntimeException("Invalid type: " + this._record.EventType
/* 1874 */           .intValue());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1884 */     public byte[] getData() { return this._data; }
/*      */     
/*      */     public EventLogRecord(Pointer pevlr) {
/*      */       this._record = null;
/* 1888 */       this._record = new WinNT.EVENTLOGRECORD(pevlr);
/* 1889 */       this._source = pevlr.getWideString(this._record.size());
/*      */       
/* 1891 */       if (this._record.DataLength.intValue() > 0) {
/* 1892 */         this._data = pevlr.getByteArray(this._record.DataOffset.intValue(), this._record.DataLength
/* 1893 */             .intValue());
/*      */       }
/*      */       
/* 1896 */       if (this._record.NumStrings.intValue() > 0) {
/* 1897 */         ArrayList<String> strings = new ArrayList<String>();
/* 1898 */         int count = this._record.NumStrings.intValue();
/* 1899 */         long offset = this._record.StringOffset.intValue();
/* 1900 */         while (count > 0) {
/* 1901 */           String s = pevlr.getWideString(offset);
/* 1902 */           strings.add(s);
/* 1903 */           offset += (s.length() * Native.WCHAR_SIZE);
/* 1904 */           offset += Native.WCHAR_SIZE;
/* 1905 */           count--;
/*      */         } 
/* 1907 */         this._strings = (String[])strings.toArray(new String[0]);
/*      */       } 
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class EventLogIterator
/*      */     extends Object
/*      */     implements Iterable<EventLogRecord>, Iterator<EventLogRecord>
/*      */   {
/*      */     private WinNT.HANDLE _h;
/*      */     
/*      */     private Memory _buffer;
/*      */     
/*      */     private boolean _done;
/*      */     
/*      */     private int _dwRead;
/*      */     
/*      */     private Pointer _pevlr;
/*      */     private int _flags;
/*      */     
/* 1928 */     public EventLogIterator(String sourceName) { this(null, sourceName, 4); } public EventLogIterator(String serverName, String sourceName, int flags) { this._h = null; this._buffer = new Memory(65536L); this._done = false;
/*      */       this._dwRead = 0;
/*      */       this._pevlr = null;
/*      */       this._flags = 4;
/* 1932 */       this._flags = flags;
/* 1933 */       this._h = Advapi32.INSTANCE.OpenEventLog(serverName, sourceName);
/* 1934 */       if (this._h == null) {
/* 1935 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       } }
/*      */ 
/*      */ 
/*      */     
/*      */     private boolean read() {
/* 1941 */       if (this._done || this._dwRead > 0) {
/* 1942 */         return false;
/*      */       }
/*      */       
/* 1945 */       IntByReference pnBytesRead = new IntByReference();
/* 1946 */       IntByReference pnMinNumberOfBytesNeeded = new IntByReference();
/*      */ 
/*      */       
/* 1949 */       if (!Advapi32.INSTANCE.ReadEventLog(this._h, true | this._flags, 0, this._buffer, 
/* 1950 */           (int)this._buffer.size(), pnBytesRead, pnMinNumberOfBytesNeeded)) {
/*      */ 
/*      */         
/* 1953 */         int rc = Kernel32.INSTANCE.GetLastError();
/*      */ 
/*      */         
/* 1956 */         if (rc == 122) {
/* 1957 */           this._buffer = new Memory(pnMinNumberOfBytesNeeded.getValue());
/*      */           
/* 1959 */           if (!Advapi32.INSTANCE.ReadEventLog(this._h, true | this._flags, 0, this._buffer, 
/*      */               
/* 1961 */               (int)this._buffer.size(), pnBytesRead, pnMinNumberOfBytesNeeded))
/*      */           {
/* 1963 */             throw new Win32Exception(Kernel32.INSTANCE
/* 1964 */                 .GetLastError());
/*      */           }
/*      */         } else {
/*      */           
/* 1968 */           close();
/* 1969 */           if (rc != 38) {
/* 1970 */             throw new Win32Exception(rc);
/*      */           }
/* 1972 */           return false;
/*      */         } 
/*      */       } 
/*      */       
/* 1976 */       this._dwRead = pnBytesRead.getValue();
/* 1977 */       this._pevlr = this._buffer;
/* 1978 */       return true;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void close() {
/* 1986 */       this._done = true;
/* 1987 */       if (this._h != null) {
/* 1988 */         if (!Advapi32.INSTANCE.CloseEventLog(this._h)) {
/* 1989 */           throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */         }
/* 1991 */         this._h = null;
/*      */       } 
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1998 */     public Iterator<Advapi32Util.EventLogRecord> iterator() { return this; }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 2004 */       read();
/* 2005 */       return !this._done;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public Advapi32Util.EventLogRecord next() {
/* 2011 */       read();
/* 2012 */       Advapi32Util.EventLogRecord record = new Advapi32Util.EventLogRecord(this._pevlr);
/* 2013 */       this._dwRead -= record.getLength();
/* 2014 */       this._pevlr = this._pevlr.share(record.getLength());
/* 2015 */       return record;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {}
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static WinNT.ACCESS_ACEStructure[] getFileSecurity(String fileName, boolean compact) {
/* 2026 */     int infoType = 4;
/* 2027 */     int nLength = 1024;
/* 2028 */     boolean repeat = false;
/* 2029 */     Memory memory = null;
/*      */     
/*      */     do {
/* 2032 */       repeat = false;
/* 2033 */       memory = new Memory(nLength);
/* 2034 */       IntByReference lpnSize = new IntByReference();
/* 2035 */       boolean succeded = Advapi32.INSTANCE.GetFileSecurity(new WString(fileName), infoType, memory, nLength, lpnSize);
/*      */ 
/*      */       
/* 2038 */       if (!succeded) {
/* 2039 */         int lastError = Kernel32.INSTANCE.GetLastError();
/* 2040 */         memory.clear();
/* 2041 */         if (122 != lastError) {
/* 2042 */           throw new Win32Exception(lastError);
/*      */         }
/*      */       } 
/* 2045 */       int lengthNeeded = lpnSize.getValue();
/* 2046 */       if (nLength >= lengthNeeded)
/* 2047 */         continue;  repeat = true;
/* 2048 */       nLength = lengthNeeded;
/* 2049 */       memory.clear();
/*      */     }
/* 2051 */     while (repeat);
/*      */     
/* 2053 */     WinNT.SECURITY_DESCRIPTOR_RELATIVE sdr = new WinNT.SECURITY_DESCRIPTOR_RELATIVE(memory);
/*      */     
/* 2055 */     memory.clear();
/* 2056 */     WinNT.ACL dacl = sdr.getDiscretionaryACL();
/* 2057 */     WinNT.ACCESS_ACEStructure[] arrayOfACCESS_ACEStructure = dacl.getACEStructures();
/*      */     
/* 2059 */     if (compact) {
/* 2060 */       Map<String, WinNT.ACCESS_ACEStructure> aceMap = new HashMap<String, WinNT.ACCESS_ACEStructure>();
/* 2061 */       for (WinNT.ACCESS_ACEStructure aceStructure : arrayOfACCESS_ACEStructure) {
/* 2062 */         boolean inherted = ((aceStructure.AceFlags & 0x1F) != 0);
/*      */         
/* 2064 */         String key = aceStructure.getSidString() + "/" + inherted + "/" + aceStructure.getClass().getName();
/* 2065 */         WinNT.ACCESS_ACEStructure aceStructure2 = (WinNT.ACCESS_ACEStructure)aceMap.get(key);
/* 2066 */         if (aceStructure2 != null) {
/* 2067 */           int accessMask = aceStructure2.Mask;
/* 2068 */           accessMask |= aceStructure.Mask;
/* 2069 */           aceStructure2.Mask = accessMask;
/*      */         } else {
/* 2071 */           aceMap.put(key, aceStructure);
/*      */         } 
/*      */       } 
/* 2074 */       return (ACCESS_ACEStructure[])aceMap.values().toArray(
/* 2075 */           new WinNT.ACCESS_ACEStructure[aceMap.size()]);
/*      */     } 
/* 2077 */     return arrayOfACCESS_ACEStructure;
/*      */   }
/*      */   
/*      */   public enum AccessCheckPermission {
/* 2081 */     READ(-2147483648),
/* 2082 */     WRITE(1073741824),
/* 2083 */     EXECUTE(536870912);
/*      */     
/*      */     final int code;
/*      */ 
/*      */     
/* 2088 */     AccessCheckPermission(int code) { this.code = code; }
/*      */ 
/*      */ 
/*      */     
/* 2092 */     public int getCode() { return this.code; }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   private static Memory getSecurityDescriptorForFile(String absoluteFilePath) {
/* 2098 */     int infoType = 7;
/*      */ 
/*      */     
/* 2101 */     IntByReference lpnSize = new IntByReference();
/* 2102 */     boolean succeeded = Advapi32.INSTANCE.GetFileSecurity(new WString(absoluteFilePath), 7, null, 0, lpnSize);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2108 */     if (!succeeded) {
/* 2109 */       int lastError = Kernel32.INSTANCE.GetLastError();
/* 2110 */       if (122 != lastError) {
/* 2111 */         throw new Win32Exception(lastError);
/*      */       }
/*      */     } 
/*      */     
/* 2115 */     int nLength = lpnSize.getValue();
/* 2116 */     Memory securityDescriptorMemoryPointer = new Memory(nLength);
/* 2117 */     succeeded = Advapi32.INSTANCE.GetFileSecurity(new WString(absoluteFilePath), 7, securityDescriptorMemoryPointer, nLength, lpnSize);
/*      */ 
/*      */     
/* 2120 */     if (!succeeded) {
/* 2121 */       securityDescriptorMemoryPointer.clear();
/* 2122 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     } 
/*      */     
/* 2125 */     return securityDescriptorMemoryPointer;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static Memory getSecurityDescriptorForObject(String absoluteObjectPath, int objectType, boolean getSACL) {
/* 2143 */     int infoType = 0x7 | (getSACL ? 8 : 0);
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2148 */     PointerByReference ppSecurityDescriptor = new PointerByReference();
/*      */     
/* 2150 */     int lastError = Advapi32.INSTANCE.GetNamedSecurityInfo(absoluteObjectPath, objectType, infoType, null, null, null, null, ppSecurityDescriptor);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2160 */     if (lastError != 0) {
/* 2161 */       throw new Win32Exception(lastError);
/*      */     }
/*      */     
/* 2164 */     int nLength = Advapi32.INSTANCE.GetSecurityDescriptorLength(ppSecurityDescriptor.getValue());
/* 2165 */     Memory memory = new Memory(nLength);
/* 2166 */     memory.write(0L, ppSecurityDescriptor.getValue().getByteArray(0L, nLength), 0, nLength);
/* 2167 */     Kernel32.INSTANCE.LocalFree(ppSecurityDescriptor.getValue());
/* 2168 */     return memory;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void setSecurityDescriptorForObject(String absoluteObjectPath, int objectType, WinNT.SECURITY_DESCRIPTOR_RELATIVE securityDescriptor, boolean setOwner, boolean setGroup, boolean setDACL, boolean setSACL, boolean setDACLProtectedStatus, boolean setSACLProtectedStatus) {
/* 2211 */     WinNT.PSID psidOwner = securityDescriptor.getOwner();
/* 2212 */     WinNT.PSID psidGroup = securityDescriptor.getGroup();
/* 2213 */     WinNT.ACL dacl = securityDescriptor.getDiscretionaryACL();
/* 2214 */     WinNT.ACL sacl = securityDescriptor.getSystemACL();
/*      */     
/* 2216 */     int infoType = 0;
/*      */     
/* 2218 */     if (setOwner) {
/* 2219 */       if (psidOwner == null)
/* 2220 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain owner"); 
/* 2221 */       if (!Advapi32.INSTANCE.IsValidSid(psidOwner))
/* 2222 */         throw new IllegalArgumentException("Owner PSID is invalid"); 
/* 2223 */       infoType |= 0x1;
/*      */     } 
/*      */     
/* 2226 */     if (setGroup) {
/* 2227 */       if (psidGroup == null)
/* 2228 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain group"); 
/* 2229 */       if (!Advapi32.INSTANCE.IsValidSid(psidGroup))
/* 2230 */         throw new IllegalArgumentException("Group PSID is invalid"); 
/* 2231 */       infoType |= 0x2;
/*      */     } 
/*      */     
/* 2234 */     if (setDACL) {
/* 2235 */       if (dacl == null)
/* 2236 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain DACL"); 
/* 2237 */       if (!Advapi32.INSTANCE.IsValidAcl(dacl.getPointer()))
/* 2238 */         throw new IllegalArgumentException("DACL is invalid"); 
/* 2239 */       infoType |= 0x4;
/*      */     } 
/*      */     
/* 2242 */     if (setSACL) {
/* 2243 */       if (sacl == null)
/* 2244 */         throw new IllegalArgumentException("SECURITY_DESCRIPTOR_RELATIVE does not contain SACL"); 
/* 2245 */       if (!Advapi32.INSTANCE.IsValidAcl(sacl.getPointer()))
/* 2246 */         throw new IllegalArgumentException("SACL is invalid"); 
/* 2247 */       infoType |= 0x8;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 2255 */     if (setDACLProtectedStatus) {
/* 2256 */       if ((securityDescriptor.Control & 0x1000) != 0) {
/* 2257 */         infoType |= Integer.MIN_VALUE;
/*      */       }
/* 2259 */       else if ((securityDescriptor.Control & 0x1000) == 0) {
/* 2260 */         infoType |= 0x20000000;
/*      */       } 
/*      */     }
/*      */     
/* 2264 */     if (setSACLProtectedStatus) {
/* 2265 */       if ((securityDescriptor.Control & 0x2000) != 0) {
/* 2266 */         infoType |= 0x40000000;
/*      */       }
/* 2268 */       else if ((securityDescriptor.Control & 0x2000) == 0) {
/* 2269 */         infoType |= 0x10000000;
/*      */       } 
/*      */     }
/*      */     
/* 2273 */     int lastError = Advapi32.INSTANCE.SetNamedSecurityInfo(absoluteObjectPath, objectType, infoType, setOwner ? psidOwner
/*      */ 
/*      */ 
/*      */         
/* 2277 */         .getPointer() : null, setGroup ? psidGroup
/* 2278 */         .getPointer() : null, setDACL ? dacl
/* 2279 */         .getPointer() : null, setSACL ? sacl
/* 2280 */         .getPointer() : null);
/*      */     
/* 2282 */     if (lastError != 0) {
/* 2283 */       throw new Win32Exception(lastError);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean accessCheck(File file, AccessCheckPermission permissionToCheck) {
/* 2294 */     boolean hasAccess = false;
/* 2295 */     securityDescriptorMemoryPointer = getSecurityDescriptorForFile(file.getAbsolutePath().replaceAll("/", "\\"));
/*      */     
/* 2297 */     openedAccessToken = null;
/* 2298 */     duplicatedToken = new WinNT.HANDLEByReference();
/*      */     try {
/* 2300 */       openedAccessToken = new WinNT.HANDLEByReference();
/*      */       
/* 2302 */       int desireAccess = 131086;
/* 2303 */       if (!Advapi32.INSTANCE.OpenProcessToken(Kernel32.INSTANCE.GetCurrentProcess(), 131086, openedAccessToken)) {
/* 2304 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2307 */       if (!Advapi32.INSTANCE.DuplicateToken(openedAccessToken.getValue(), 2, duplicatedToken)) {
/* 2308 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2311 */       WinNT.GENERIC_MAPPING mapping = new WinNT.GENERIC_MAPPING();
/* 2312 */       mapping.genericRead = new WinDef.DWORD(1179785L);
/* 2313 */       mapping.genericWrite = new WinDef.DWORD(1179926L);
/* 2314 */       mapping.genericExecute = new WinDef.DWORD(1179808L);
/* 2315 */       mapping.genericAll = new WinDef.DWORD(2032127L);
/*      */       
/* 2317 */       WinDef.DWORDByReference rights = new WinDef.DWORDByReference(new WinDef.DWORD(permissionToCheck.getCode()));
/* 2318 */       Advapi32.INSTANCE.MapGenericMask(rights, mapping);
/*      */       
/* 2320 */       WinNT.PRIVILEGE_SET privileges = new WinNT.PRIVILEGE_SET(true);
/* 2321 */       privileges.PrivilegeCount = new WinDef.DWORD(0L);
/* 2322 */       WinDef.DWORDByReference privilegeLength = new WinDef.DWORDByReference(new WinDef.DWORD(privileges.size()));
/*      */       
/* 2324 */       WinDef.DWORDByReference grantedAccess = new WinDef.DWORDByReference();
/* 2325 */       WinDef.BOOLByReference result = new WinDef.BOOLByReference();
/* 2326 */       if (!Advapi32.INSTANCE.AccessCheck(securityDescriptorMemoryPointer, duplicatedToken
/* 2327 */           .getValue(), rights
/* 2328 */           .getValue(), mapping, privileges, privilegeLength, grantedAccess, result))
/*      */       {
/*      */         
/* 2331 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */       }
/*      */       
/* 2334 */       hasAccess = result.getValue().booleanValue();
/*      */     }
/*      */     finally {
/*      */       
/* 2338 */       if (openedAccessToken != null && openedAccessToken.getValue() != null) {
/* 2339 */         Kernel32.INSTANCE.CloseHandle(openedAccessToken.getValue());
/*      */       }
/*      */       
/* 2342 */       if (duplicatedToken != null && duplicatedToken.getValue() != null) {
/* 2343 */         Kernel32.INSTANCE.CloseHandle(duplicatedToken.getValue());
/*      */       }
/*      */       
/* 2346 */       if (securityDescriptorMemoryPointer != null) {
/* 2347 */         securityDescriptorMemoryPointer.clear();
/*      */       }
/*      */     } 
/*      */     
/* 2351 */     return hasAccess;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static WinNT.SECURITY_DESCRIPTOR_RELATIVE getFileSecurityDescriptor(File file, boolean getSACL) {
/* 2365 */     sdr = null;
/* 2366 */     Memory securityDesc = getSecurityDescriptorForObject(file.getAbsolutePath().replaceAll("/", "\\"), 1, getSACL);
/* 2367 */     return new WinNT.SECURITY_DESCRIPTOR_RELATIVE(securityDesc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/* 2400 */   public static void setFileSecurityDescriptor(File file, WinNT.SECURITY_DESCRIPTOR_RELATIVE securityDescriptor, boolean setOwner, boolean setGroup, boolean setDACL, boolean setSACL, boolean setDACLProtectedStatus, boolean setSACLProtectedStatus) { setSecurityDescriptorForObject(file.getAbsolutePath().replaceAll("/", "\\"), 1, securityDescriptor, setOwner, setGroup, setDACL, setSACL, setDACLProtectedStatus, setSACLProtectedStatus); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void encryptFile(File file) {
/* 2410 */     WString lpFileName = new WString(file.getAbsolutePath());
/* 2411 */     if (!Advapi32.INSTANCE.EncryptFile(lpFileName)) {
/* 2412 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void decryptFile(File file) {
/* 2423 */     WString lpFileName = new WString(file.getAbsolutePath());
/* 2424 */     if (!Advapi32.INSTANCE.DecryptFile(lpFileName, new WinDef.DWORD(0L))) {
/* 2425 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static int fileEncryptionStatus(File file) {
/* 2437 */     WinDef.DWORDByReference status = new WinDef.DWORDByReference();
/* 2438 */     WString lpFileName = new WString(file.getAbsolutePath());
/* 2439 */     if (!Advapi32.INSTANCE.FileEncryptionStatus(lpFileName, status)) {
/* 2440 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/* 2442 */     return status.getValue().intValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void disableEncryption(File directory, boolean disable) {
/* 2455 */     WString dirPath = new WString(directory.getAbsolutePath());
/* 2456 */     if (!Advapi32.INSTANCE.EncryptionDisable(dirPath, disable)) {
/* 2457 */       throw new Win32Exception(Native.getLastError());
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void backupEncryptedFile(File src, File destDir) {
/* 2475 */     if (!destDir.isDirectory()) {
/* 2476 */       throw new IllegalArgumentException("destDir must be a directory.");
/*      */     }
/*      */     
/* 2479 */     WinDef.ULONG readFlag = new WinDef.ULONG(0L);
/* 2480 */     WinDef.ULONG writeFlag = new WinDef.ULONG(1L);
/*      */     
/* 2482 */     if (src.isDirectory()) {
/* 2483 */       writeFlag.setValue(3L);
/*      */     }
/*      */ 
/*      */     
/* 2487 */     WString srcFileName = new WString(src.getAbsolutePath());
/* 2488 */     PointerByReference pvContext = new PointerByReference();
/* 2489 */     if (Advapi32.INSTANCE.OpenEncryptedFileRaw(srcFileName, readFlag, pvContext) != 0)
/*      */     {
/* 2491 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 2495 */     final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
/* 2496 */     WinBase.FE_EXPORT_FUNC pfExportCallback = new WinBase.FE_EXPORT_FUNC()
/*      */       {
/*      */         public WinDef.DWORD callback(ByteByReference pbData, Pointer pvCallbackContext, WinDef.ULONG ulLength)
/*      */         {
/* 2500 */           byte[] arr = pbData.getPointer().getByteArray(0L, ulLength.intValue());
/*      */           try {
/* 2502 */             outputStream.write(arr);
/* 2503 */           } catch (IOException e) {
/* 2504 */             throw new RuntimeException(e);
/*      */           } 
/* 2506 */           return new WinDef.DWORD(0L);
/*      */         }
/*      */       };
/*      */     
/* 2510 */     if (Advapi32.INSTANCE.ReadEncryptedFileRaw(pfExportCallback, null, pvContext
/* 2511 */         .getValue()) != 0) {
/* 2512 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/*      */     try {
/* 2517 */       outputStream.close();
/* 2518 */     } catch (IOException e) {
/* 2519 */       throw new RuntimeException(e);
/*      */     } 
/* 2521 */     Advapi32.INSTANCE.CloseEncryptedFileRaw(pvContext.getValue());
/*      */ 
/*      */ 
/*      */     
/* 2525 */     WString destFileName = new WString(destDir.getAbsolutePath() + File.separator + src.getName());
/* 2526 */     pvContext = new PointerByReference();
/* 2527 */     if (Advapi32.INSTANCE.OpenEncryptedFileRaw(destFileName, writeFlag, pvContext) != 0)
/*      */     {
/* 2529 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 2533 */     final IntByReference elementsReadWrapper = new IntByReference(false);
/* 2534 */     WinBase.FE_IMPORT_FUNC pfImportCallback = new WinBase.FE_IMPORT_FUNC()
/*      */       {
/*      */         public WinDef.DWORD callback(ByteByReference pbData, Pointer pvCallbackContext, WinDef.ULONGByReference ulLength)
/*      */         {
/* 2538 */           int elementsRead = elementsReadWrapper.getValue();
/* 2539 */           int remainingElements = outputStream.size() - elementsRead;
/* 2540 */           int length = Math.min(remainingElements, ulLength.getValue().intValue());
/* 2541 */           pbData.getPointer().write(0L, outputStream.toByteArray(), elementsRead, length);
/*      */           
/* 2543 */           elementsReadWrapper.setValue(elementsRead + length);
/* 2544 */           ulLength.setValue(new WinDef.ULONG(length));
/* 2545 */           return new WinDef.DWORD(0L);
/*      */         }
/*      */       };
/*      */     
/* 2549 */     if (Advapi32.INSTANCE.WriteEncryptedFileRaw(pfImportCallback, null, pvContext
/* 2550 */         .getValue()) != 0) {
/* 2551 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*      */     }
/*      */ 
/*      */     
/* 2555 */     Advapi32.INSTANCE.CloseEncryptedFileRaw(pvContext.getValue());
/*      */   }
/*      */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Advapi32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */