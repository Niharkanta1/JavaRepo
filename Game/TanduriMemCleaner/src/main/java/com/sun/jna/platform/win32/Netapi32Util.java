/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.ptr.PointerByReference;
/*     */ import java.util.ArrayList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Netapi32Util
/*     */ {
/*     */   public static class Group
/*     */   {
/*     */     public String name;
/*     */   }
/*     */   
/*     */   public static class User
/*     */   {
/*     */     public String name;
/*     */     public String comment;
/*     */   }
/*     */   
/*     */   public static class UserInfo
/*     */     extends User
/*     */   {
/*     */     public String fullName;
/*     */     public String sidString;
/*     */     public WinNT.PSID sid;
/*     */     public int flags;
/*     */   }
/*     */   
/*     */   public static class LocalGroup
/*     */     extends Group
/*     */   {
/*     */     public String comment;
/*     */   }
/*     */   
/*  94 */   public static String getDCName() { return getDCName(null, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDCName(String serverName, String domainName) {
/* 108 */     bufptr = new PointerByReference();
/*     */     try {
/* 110 */       int rc = Netapi32.INSTANCE.NetGetDCName(domainName, serverName, bufptr);
/* 111 */       if (0 != rc) {
/* 112 */         throw new Win32Exception(rc);
/*     */       }
/* 114 */       return bufptr.getValue().getWideString(0L);
/*     */     } finally {
/* 116 */       if (0 != Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue())) {
/* 117 */         throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 127 */   public static int getJoinStatus() { return getJoinStatus(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getJoinStatus(String computerName) {
/* 136 */     lpNameBuffer = new PointerByReference();
/* 137 */     IntByReference bufferType = new IntByReference();
/*     */     
/*     */     try {
/* 140 */       int rc = Netapi32.INSTANCE.NetGetJoinInformation(computerName, lpNameBuffer, bufferType);
/* 141 */       if (0 != rc) {
/* 142 */         throw new Win32Exception(rc);
/*     */       }
/* 144 */       return bufferType.getValue();
/*     */     } finally {
/* 146 */       if (lpNameBuffer.getPointer() != null) {
/* 147 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(lpNameBuffer.getValue());
/* 148 */         if (0 != rc) {
/* 149 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getDomainName(String computerName) {
/* 161 */     lpNameBuffer = new PointerByReference();
/* 162 */     IntByReference bufferType = new IntByReference();
/*     */     
/*     */     try {
/* 165 */       int rc = Netapi32.INSTANCE.NetGetJoinInformation(computerName, lpNameBuffer, bufferType);
/* 166 */       if (0 != rc) {
/* 167 */         throw new Win32Exception(rc);
/*     */       }
/*     */       
/* 170 */       return lpNameBuffer.getValue().getWideString(0L);
/*     */     } finally {
/* 172 */       if (lpNameBuffer.getPointer() != null) {
/* 173 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(lpNameBuffer.getValue());
/* 174 */         if (0 != rc) {
/* 175 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 186 */   public static LocalGroup[] getLocalGroups() { return getLocalGroups(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static LocalGroup[] getLocalGroups(String serverName) {
/* 195 */     bufptr = new PointerByReference();
/* 196 */     IntByReference entriesRead = new IntByReference();
/* 197 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 199 */       int rc = Netapi32.INSTANCE.NetLocalGroupEnum(serverName, 1, bufptr, -1, entriesRead, totalEntries, null);
/* 200 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 201 */         throw new Win32Exception(rc);
/*     */       }
/* 203 */       LMAccess.LOCALGROUP_INFO_1 group = new LMAccess.LOCALGROUP_INFO_1(bufptr.getValue());
/* 204 */       LOCALGROUP_INFO_1[] groups = (LOCALGROUP_INFO_1[])group.toArray(entriesRead.getValue());
/*     */       
/* 206 */       ArrayList<LocalGroup> result = new ArrayList<LocalGroup>();
/* 207 */       for (LMAccess.LOCALGROUP_INFO_1 lgpi : groups) {
/* 208 */         LocalGroup lgp = new LocalGroup();
/* 209 */         if (lgpi.lgrui1_name != null) {
/* 210 */           lgp.name = lgpi.lgrui1_name.toString();
/*     */         }
/* 212 */         if (lgpi.lgrui1_comment != null) {
/* 213 */           lgp.comment = lgpi.lgrui1_comment.toString();
/*     */         }
/* 215 */         result.add(lgp);
/*     */       } 
/* 217 */       return (LocalGroup[])result.toArray(new LocalGroup[0]);
/*     */     } finally {
/* 219 */       if (bufptr.getValue() != Pointer.NULL) {
/* 220 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 221 */         if (0 != rc) {
/* 222 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 233 */   public static Group[] getGlobalGroups() { return getGlobalGroups(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getGlobalGroups(String serverName) {
/* 242 */     bufptr = new PointerByReference();
/* 243 */     IntByReference entriesRead = new IntByReference();
/* 244 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 246 */       int rc = Netapi32.INSTANCE.NetGroupEnum(serverName, 1, bufptr, -1, entriesRead, totalEntries, null);
/*     */ 
/*     */       
/* 249 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 250 */         throw new Win32Exception(rc);
/*     */       }
/* 252 */       LMAccess.GROUP_INFO_1 group = new LMAccess.GROUP_INFO_1(bufptr.getValue());
/* 253 */       GROUP_INFO_1[] groups = (GROUP_INFO_1[])group.toArray(entriesRead.getValue());
/*     */       
/* 255 */       ArrayList<LocalGroup> result = new ArrayList<LocalGroup>();
/* 256 */       for (LMAccess.GROUP_INFO_1 lgpi : groups) {
/* 257 */         LocalGroup lgp = new LocalGroup();
/* 258 */         if (lgpi.grpi1_name != null) {
/* 259 */           lgp.name = lgpi.grpi1_name.toString();
/*     */         }
/* 261 */         if (lgpi.grpi1_comment != null) {
/* 262 */           lgp.comment = lgpi.grpi1_comment.toString();
/*     */         }
/* 264 */         result.add(lgp);
/*     */       } 
/* 266 */       return (Group[])result.toArray(new LocalGroup[0]);
/*     */     } finally {
/* 268 */       if (bufptr.getValue() != Pointer.NULL) {
/* 269 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 270 */         if (0 != rc) {
/* 271 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 282 */   public static User[] getUsers() { return getUsers(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static User[] getUsers(String serverName) {
/* 291 */     bufptr = new PointerByReference();
/* 292 */     IntByReference entriesRead = new IntByReference();
/* 293 */     IntByReference totalEntries = new IntByReference();
/*     */     try {
/* 295 */       int rc = Netapi32.INSTANCE.NetUserEnum(serverName, 1, 0, bufptr, -1, entriesRead, totalEntries, null);
/*     */ 
/*     */ 
/*     */       
/* 299 */       if (0 != rc || bufptr.getValue() == Pointer.NULL) {
/* 300 */         throw new Win32Exception(rc);
/*     */       }
/* 302 */       LMAccess.USER_INFO_1 user = new LMAccess.USER_INFO_1(bufptr.getValue());
/* 303 */       USER_INFO_1[] users = (USER_INFO_1[])user.toArray(entriesRead.getValue());
/* 304 */       ArrayList<User> result = new ArrayList<User>();
/* 305 */       for (LMAccess.USER_INFO_1 lu : users) {
/* 306 */         User auser = new User();
/* 307 */         if (lu.usri1_name != null) {
/* 308 */           auser.name = lu.usri1_name.toString();
/*     */         }
/* 310 */         result.add(auser);
/*     */       } 
/* 312 */       return (User[])result.toArray(new User[0]);
/*     */     } finally {
/* 314 */       if (bufptr.getValue() != Pointer.NULL) {
/* 315 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 316 */         if (0 != rc) {
/* 317 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 328 */   public static Group[] getCurrentUserLocalGroups() { return getUserLocalGroups(Secur32Util.getUserNameEx(2)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 337 */   public static Group[] getUserLocalGroups(String userName) { return getUserLocalGroups(userName, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getUserLocalGroups(String userName, String serverName) {
/* 347 */     bufptr = new PointerByReference();
/* 348 */     IntByReference entriesread = new IntByReference();
/* 349 */     IntByReference totalentries = new IntByReference();
/*     */     try {
/* 351 */       int rc = Netapi32.INSTANCE.NetUserGetLocalGroups(serverName, userName, 0, 0, bufptr, -1, entriesread, totalentries);
/*     */ 
/*     */       
/* 354 */       if (rc != 0) {
/* 355 */         throw new Win32Exception(rc);
/*     */       }
/* 357 */       LMAccess.LOCALGROUP_USERS_INFO_0 lgroup = new LMAccess.LOCALGROUP_USERS_INFO_0(bufptr.getValue());
/* 358 */       LOCALGROUP_USERS_INFO_0[] lgroups = (LOCALGROUP_USERS_INFO_0[])lgroup.toArray(entriesread.getValue());
/* 359 */       ArrayList<Group> result = new ArrayList<Group>();
/* 360 */       for (LMAccess.LOCALGROUP_USERS_INFO_0 lgpi : lgroups) {
/* 361 */         LocalGroup lgp = new LocalGroup();
/* 362 */         if (lgpi.lgrui0_name != null) {
/* 363 */           lgp.name = lgpi.lgrui0_name.toString();
/*     */         }
/* 365 */         result.add(lgp);
/*     */       } 
/* 367 */       return (Group[])result.toArray(new Group[0]);
/*     */     } finally {
/* 369 */       if (bufptr.getValue() != Pointer.NULL) {
/* 370 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 371 */         if (0 != rc) {
/* 372 */           throw new Win32Exception(rc);
/*     */         }
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
/* 384 */   public static Group[] getUserGroups(String userName) { return getUserGroups(userName, null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Group[] getUserGroups(String userName, String serverName) {
/* 394 */     bufptr = new PointerByReference();
/* 395 */     IntByReference entriesread = new IntByReference();
/* 396 */     IntByReference totalentries = new IntByReference();
/*     */     try {
/* 398 */       int rc = Netapi32.INSTANCE.NetUserGetGroups(serverName, userName, 0, bufptr, -1, entriesread, totalentries);
/*     */ 
/*     */       
/* 401 */       if (rc != 0) {
/* 402 */         throw new Win32Exception(rc);
/*     */       }
/* 404 */       LMAccess.GROUP_USERS_INFO_0 lgroup = new LMAccess.GROUP_USERS_INFO_0(bufptr.getValue());
/* 405 */       GROUP_USERS_INFO_0[] lgroups = (GROUP_USERS_INFO_0[])lgroup.toArray(entriesread.getValue());
/* 406 */       ArrayList<Group> result = new ArrayList<Group>();
/* 407 */       for (LMAccess.GROUP_USERS_INFO_0 lgpi : lgroups) {
/* 408 */         Group lgp = new Group();
/* 409 */         if (lgpi.grui0_name != null) {
/* 410 */           lgp.name = lgpi.grui0_name.toString();
/*     */         }
/* 412 */         result.add(lgp);
/*     */       } 
/* 414 */       return (Group[])result.toArray(new Group[0]);
/*     */     } finally {
/* 416 */       if (bufptr.getValue() != Pointer.NULL) {
/* 417 */         int rc = Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue());
/* 418 */         if (0 != rc) {
/* 419 */           throw new Win32Exception(rc);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DomainController
/*     */   {
/*     */     public String name;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String address;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int addressType;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID domainGuid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String domainName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String dnsForestName;
/*     */ 
/*     */ 
/*     */     
/*     */     public int flags;
/*     */ 
/*     */ 
/*     */     
/*     */     public String clientSiteName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DomainController getDC() {
/* 471 */     pdci = new DsGetDC.PDOMAIN_CONTROLLER_INFO();
/* 472 */     int rc = Netapi32.INSTANCE.DsGetDcName(null, null, null, null, 0, pdci);
/* 473 */     if (0 != rc) {
/* 474 */       throw new Win32Exception(rc);
/*     */     }
/* 476 */     DomainController dc = new DomainController();
/* 477 */     if (pdci.dci.DomainControllerAddress != null) {
/* 478 */       dc.address = pdci.dci.DomainControllerAddress.toString();
/*     */     }
/* 480 */     dc.addressType = pdci.dci.DomainControllerAddressType;
/* 481 */     if (pdci.dci.ClientSiteName != null) {
/* 482 */       dc.clientSiteName = pdci.dci.ClientSiteName.toString();
/*     */     }
/* 484 */     if (pdci.dci.DnsForestName != null) {
/* 485 */       dc.dnsForestName = pdci.dci.DnsForestName.toString();
/*     */     }
/* 487 */     dc.domainGuid = pdci.dci.DomainGuid;
/* 488 */     if (pdci.dci.DomainName != null) {
/* 489 */       dc.domainName = pdci.dci.DomainName.toString();
/*     */     }
/* 491 */     dc.flags = pdci.dci.Flags;
/* 492 */     if (pdci.dci.DomainControllerName != null) {
/* 493 */       dc.name = pdci.dci.DomainControllerName.toString();
/*     */     }
/* 495 */     rc = Netapi32.INSTANCE.NetApiBufferFree(pdci.dci.getPointer());
/* 496 */     if (0 != rc) {
/* 497 */       throw new Win32Exception(rc);
/*     */     }
/* 499 */     return dc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DomainTrust
/*     */   {
/*     */     public String NetbiosDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DnsDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinNT.PSID DomainSid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DomainSidString;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID DomainGuid;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String DomainGuidString;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private int flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 546 */     public boolean isInForest() { return ((this.flags & true) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 557 */     public boolean isOutbound() { return ((this.flags & 0x2) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 568 */     public boolean isRoot() { return ((this.flags & 0x4) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 578 */     public boolean isPrimary() { return ((this.flags & 0x8) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 587 */     public boolean isNativeMode() { return ((this.flags & 0x10) != 0); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 598 */     public boolean isInbound() { return ((this.flags & 0x20) != 0); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 608 */   public static DomainTrust[] getDomainTrusts() { return getDomainTrusts(null); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static DomainTrust[] getDomainTrusts(String serverName) {
/* 619 */     IntByReference domainTrustCount = new IntByReference();
/* 620 */     domainsPointerRef = new PointerByReference();
/* 621 */     rc = Netapi32.INSTANCE.DsEnumerateDomainTrusts(serverName, 63, domainsPointerRef, domainTrustCount);
/*     */     
/* 623 */     if (0 != rc) {
/* 624 */       throw new Win32Exception(rc);
/*     */     }
/*     */     try {
/* 627 */       DsGetDC.DS_DOMAIN_TRUSTS domainTrustRefs = new DsGetDC.DS_DOMAIN_TRUSTS(domainsPointerRef.getValue());
/* 628 */       DS_DOMAIN_TRUSTS[] domainTrusts = (DS_DOMAIN_TRUSTS[])domainTrustRefs.toArray(new DsGetDC.DS_DOMAIN_TRUSTS[domainTrustCount.getValue()]);
/* 629 */       ArrayList<DomainTrust> trusts = new ArrayList<DomainTrust>(domainTrustCount.getValue());
/* 630 */       for (DsGetDC.DS_DOMAIN_TRUSTS domainTrust : domainTrusts) {
/* 631 */         DomainTrust t = new DomainTrust();
/* 632 */         if (domainTrust.DnsDomainName != null) {
/* 633 */           t.DnsDomainName = domainTrust.DnsDomainName.toString();
/*     */         }
/* 635 */         if (domainTrust.NetbiosDomainName != null) {
/* 636 */           t.NetbiosDomainName = domainTrust.NetbiosDomainName.toString();
/*     */         }
/* 638 */         t.DomainSid = domainTrust.DomainSid;
/* 639 */         if (domainTrust.DomainSid != null) {
/* 640 */           t.DomainSidString = Advapi32Util.convertSidToStringSid(domainTrust.DomainSid);
/*     */         }
/* 642 */         t.DomainGuid = domainTrust.DomainGuid;
/* 643 */         if (domainTrust.DomainGuid != null) {
/* 644 */           t.DomainGuidString = Ole32Util.getStringFromGUID(domainTrust.DomainGuid);
/*     */         }
/* 646 */         t.flags = domainTrust.Flags;
/* 647 */         trusts.add(t);
/*     */       } 
/* 649 */       return (DomainTrust[])trusts.toArray(new DomainTrust[0]);
/*     */     } finally {
/* 651 */       rc = Netapi32.INSTANCE.NetApiBufferFree(domainsPointerRef.getValue());
/* 652 */       if (0 != rc) {
/* 653 */         throw new Win32Exception(rc);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/* 659 */   public static UserInfo getUserInfo(String accountName) { return getUserInfo(accountName, getDCName()); }
/*     */ 
/*     */   
/*     */   public static UserInfo getUserInfo(String accountName, String domainName) {
/* 663 */     bufptr = new PointerByReference();
/* 664 */     int rc = -1;
/*     */     try {
/* 666 */       rc = Netapi32.INSTANCE.NetUserGetInfo(domainName, accountName, 23, bufptr);
/* 667 */       if (rc == 0) {
/* 668 */         LMAccess.USER_INFO_23 info_23 = new LMAccess.USER_INFO_23(bufptr.getValue());
/* 669 */         UserInfo userInfo = new UserInfo();
/* 670 */         if (info_23.usri23_comment != null) {
/* 671 */           userInfo.comment = info_23.usri23_comment.toString();
/*     */         }
/* 673 */         userInfo.flags = info_23.usri23_flags;
/* 674 */         if (info_23.usri23_full_name != null) {
/* 675 */           userInfo.fullName = info_23.usri23_full_name.toString();
/*     */         }
/* 677 */         if (info_23.usri23_name != null) {
/* 678 */           userInfo.name = info_23.usri23_name.toString();
/*     */         }
/* 680 */         if (info_23.usri23_user_sid != null) {
/* 681 */           userInfo.sidString = Advapi32Util.convertSidToStringSid(info_23.usri23_user_sid);
/*     */         }
/* 683 */         userInfo.sid = info_23.usri23_user_sid;
/* 684 */         return userInfo;
/*     */       } 
/* 686 */       throw new Win32Exception(rc);
/*     */     } finally {
/*     */       
/* 689 */       if (bufptr.getValue() != Pointer.NULL)
/* 690 */         Netapi32.INSTANCE.NetApiBufferFree(bufptr.getValue()); 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Netapi32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */