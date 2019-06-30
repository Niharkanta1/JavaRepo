/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface DsGetDC
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int DS_DOMAIN_IN_FOREST = 1;
/*     */   public static final int DS_DOMAIN_DIRECT_OUTBOUND = 2;
/*     */   public static final int DS_DOMAIN_TREE_ROOT = 4;
/*     */   public static final int DS_DOMAIN_PRIMARY = 8;
/*     */   public static final int DS_DOMAIN_NATIVE_MODE = 16;
/*     */   public static final int DS_DOMAIN_DIRECT_INBOUND = 32;
/*     */   public static final int DS_DOMAIN_VALID_FLAGS = 63;
/*     */   
/*     */   public static class DOMAIN_CONTROLLER_INFO
/*     */     extends Structure
/*     */   {
/*     */     public WString DomainControllerName;
/*     */     public WString DomainControllerAddress;
/*     */     public int DomainControllerAddressType;
/*     */     public Guid.GUID DomainGuid;
/*     */     public WString DomainName;
/*     */     public WString DnsForestName;
/*     */     public int Flags;
/*     */     public WString DcSiteName;
/*     */     public WString ClientSiteName;
/*     */     
/*     */     public static class ByReference
/*     */       extends DOMAIN_CONTROLLER_INFO
/*     */       implements Structure.ByReference {}
/*     */     
/*     */     public DOMAIN_CONTROLLER_INFO() {}
/*     */     
/*     */     public DOMAIN_CONTROLLER_INFO(Pointer memory) {
/*  46 */       super(memory);
/*  47 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "DomainControllerName", "DomainControllerAddress", "DomainControllerAddressType", "DomainGuid", "DomainName", "DnsForestName", "Flags", "DcSiteName", "ClientSiteName" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PDOMAIN_CONTROLLER_INFO
/*     */     extends Structure
/*     */   {
/*     */     public DsGetDC.DOMAIN_CONTROLLER_INFO.ByReference dci;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PDOMAIN_CONTROLLER_INFO
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */     
/* 136 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dci" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DS_DOMAIN_TRUSTS
/*     */     extends Structure
/*     */   {
/*     */     public WString NetbiosDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WString DnsDomainName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int Flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int ParentIndex;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int TrustType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int TrustAttributes;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinNT.PSID.ByReference DomainSid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID DomainGuid;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends DS_DOMAIN_TRUSTS
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "NetbiosDomainName", "DnsDomainName", "Flags", "ParentIndex", "TrustType", "TrustAttributes", "DomainSid", "DomainGuid" }); }
/*     */ 
/*     */ 
/*     */     
/*     */     public DS_DOMAIN_TRUSTS() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public DS_DOMAIN_TRUSTS(Pointer p) {
/* 234 */       super(p);
/* 235 */       read();
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\DsGetDC.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */