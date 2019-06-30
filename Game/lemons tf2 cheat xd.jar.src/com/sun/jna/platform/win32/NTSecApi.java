/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.Union;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface NTSecApi
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int ForestTrustTopLevelName = 0;
/*     */   public static final int ForestTrustTopLevelNameEx = 1;
/*     */   public static final int ForestTrustDomainInfo = 2;
/*     */   
/*     */   public static class LSA_UNICODE_STRING
/*     */     extends Structure
/*     */   {
/*     */     public short Length;
/*     */     public short MaximumLength;
/*     */     public Pointer Buffer;
/*     */     
/*     */     public static class ByReference
/*     */       extends LSA_UNICODE_STRING
/*     */       implements Structure.ByReference {}
/*     */     
/*  59 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Length", "MaximumLength", "Buffer" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getString() {
/*  68 */       byte[] data = this.Buffer.getByteArray(0L, this.Length);
/*  69 */       if (data.length < 2 || data[data.length - 1] != 0) {
/*  70 */         Memory newdata = new Memory((data.length + 2));
/*  71 */         newdata.write(0L, data, 0, data.length);
/*  72 */         return newdata.getWideString(0L);
/*     */       } 
/*  74 */       return this.Buffer.getWideString(0L);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PLSA_UNICODE_STRING
/*     */   {
/*     */     public NTSecApi.LSA_UNICODE_STRING.ByReference s;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PLSA_UNICODE_STRING
/*     */       implements Structure.ByReference {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LSA_FOREST_TRUST_DOMAIN_INFO
/*     */     extends Structure
/*     */   {
/*     */     public WinNT.PSID.ByReference Sid;
/*     */ 
/*     */     
/*     */     public NTSecApi.LSA_UNICODE_STRING DnsName;
/*     */ 
/*     */     
/*     */     public NTSecApi.LSA_UNICODE_STRING NetbiosName;
/*     */ 
/*     */ 
/*     */     
/* 109 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Sid", "DnsName", "NetbiosName" }); }
/*     */   }
/*     */   
/*     */   public static class LSA_FOREST_TRUST_BINARY_DATA
/*     */     extends Structure {
/*     */     public int Length;
/*     */     public Pointer Buffer;
/*     */     
/* 117 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Length", "Buffer" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LSA_FOREST_TRUST_RECORD
/*     */     extends Structure
/*     */   {
/*     */     public int Flags;
/*     */ 
/*     */     
/*     */     public int ForestTrustType;
/*     */ 
/*     */     
/*     */     public WinNT.LARGE_INTEGER Time;
/*     */ 
/*     */     
/*     */     public UNION u;
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends LSA_FOREST_TRUST_RECORD
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */     
/*     */     public static class UNION
/*     */       extends Union
/*     */     {
/*     */       public NTSecApi.LSA_UNICODE_STRING TopLevelName;
/*     */ 
/*     */       
/*     */       public NTSecApi.LSA_FOREST_TRUST_DOMAIN_INFO DomainInfo;
/*     */ 
/*     */       
/*     */       public NTSecApi.LSA_FOREST_TRUST_BINARY_DATA Data;
/*     */ 
/*     */ 
/*     */       
/*     */       public static class ByReference
/*     */         extends UNION
/*     */         implements Structure.ByReference {}
/*     */     }
/*     */ 
/*     */     
/* 163 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Flags", "ForestTrustType", "Time", "u" }); }
/*     */ 
/*     */     
/*     */     public void read() {
/* 167 */       super.read();
/*     */       
/* 169 */       switch (this.ForestTrustType) {
/*     */         case 0:
/*     */         case 1:
/* 172 */           this.u.setType(NTSecApi.LSA_UNICODE_STRING.class);
/*     */           break;
/*     */         case 2:
/* 175 */           this.u.setType(NTSecApi.LSA_FOREST_TRUST_DOMAIN_INFO.class);
/*     */           break;
/*     */         default:
/* 178 */           this.u.setType(NTSecApi.LSA_FOREST_TRUST_BINARY_DATA.class);
/*     */           break;
/*     */       } 
/*     */       
/* 182 */       this.u.read();
/*     */     }
/*     */   }
/*     */   
/*     */   public static class PLSA_FOREST_TRUST_RECORD
/*     */     extends Structure {
/*     */     public NTSecApi.LSA_FOREST_TRUST_RECORD.ByReference tr;
/*     */     
/*     */     public static class ByReference
/*     */       extends PLSA_FOREST_TRUST_RECORD
/*     */       implements Structure.ByReference {}
/*     */     
/* 194 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "tr" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class LSA_FOREST_TRUST_INFORMATION
/*     */     extends Structure
/*     */   {
/*     */     public int RecordCount;
/*     */ 
/*     */     
/*     */     public NTSecApi.PLSA_FOREST_TRUST_RECORD.ByReference Entries;
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends LSA_FOREST_TRUST_INFORMATION
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */     
/* 216 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "RecordCount", "Entries" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 225 */     public NTSecApi.PLSA_FOREST_TRUST_RECORD[] getEntries() { return (PLSA_FOREST_TRUST_RECORD[])this.Entries.toArray(this.RecordCount); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PLSA_FOREST_TRUST_INFORMATION
/*     */     extends Structure
/*     */   {
/*     */     public NTSecApi.LSA_FOREST_TRUST_INFORMATION.ByReference fti;
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PLSA_FOREST_TRUST_INFORMATION
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */     
/* 241 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "fti" }); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\NTSecApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */