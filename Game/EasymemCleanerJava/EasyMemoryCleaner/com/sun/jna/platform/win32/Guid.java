/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import java.security.SecureRandom;
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
/*     */ public interface Guid
/*     */ {
/*  31 */   public static final IID IID_NULL = new IID();
/*     */   
/*     */   public static class GUID
/*     */     extends Structure {
/*     */     public int Data1;
/*     */     public short Data2;
/*     */     public short Data3;
/*     */     
/*     */     public static class ByValue extends GUID implements Structure.ByValue {
/*     */       public ByValue() {}
/*     */       
/*     */       public ByValue(Guid.GUID guid) {
/*  43 */         super(guid.getPointer());
/*     */         
/*  45 */         this.Data1 = guid.Data1;
/*  46 */         this.Data2 = guid.Data2;
/*  47 */         this.Data3 = guid.Data3;
/*  48 */         this.Data4 = guid.Data4;
/*     */       }
/*     */       
/*  51 */       public ByValue(Pointer memory) { super(memory); }
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
/*     */     public static class ByReference
/*     */       extends GUID
/*     */       implements Structure.ByReference
/*     */     {
/*     */       public ByReference() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public ByReference(Guid.GUID guid) {
/*  76 */         super(guid.getPointer());
/*     */         
/*  78 */         this.Data1 = guid.Data1;
/*  79 */         this.Data2 = guid.Data2;
/*  80 */         this.Data3 = guid.Data3;
/*  81 */         this.Data4 = guid.Data4;
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  91 */       public ByReference(Pointer memory) { super(memory); }
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
/* 105 */     public byte[] Data4 = new byte[8];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GUID() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GUID(GUID guid) {
/* 120 */       this.Data1 = guid.Data1;
/* 121 */       this.Data2 = guid.Data2;
/* 122 */       this.Data3 = guid.Data3;
/* 123 */       this.Data4 = guid.Data4;
/*     */       
/* 125 */       writeFieldsToMemory();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     public GUID(String guid) { this(fromString(guid)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 145 */     public GUID(byte[] data) { this(fromBinary(data)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public GUID(Pointer memory) {
/* 155 */       super(memory);
/* 156 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static GUID fromBinary(byte[] data) {
/* 167 */       if (data.length != 16) {
/* 168 */         throw new IllegalArgumentException("Invalid data length: " + data.length);
/*     */       }
/*     */ 
/*     */       
/* 172 */       GUID newGuid = new GUID();
/* 173 */       long data1Temp = (data[0] & 0xFF);
/* 174 */       data1Temp <<= 8;
/* 175 */       data1Temp |= (data[1] & 0xFF);
/* 176 */       data1Temp <<= 8;
/* 177 */       data1Temp |= (data[2] & 0xFF);
/* 178 */       data1Temp <<= 8;
/* 179 */       data1Temp |= (data[3] & 0xFF);
/* 180 */       newGuid.Data1 = (int)data1Temp;
/*     */       
/* 182 */       int data2Temp = data[4] & 0xFF;
/* 183 */       data2Temp <<= 8;
/* 184 */       data2Temp |= data[5] & 0xFF;
/* 185 */       newGuid.Data2 = (short)data2Temp;
/*     */       
/* 187 */       int data3Temp = data[6] & 0xFF;
/* 188 */       data3Temp <<= 8;
/* 189 */       data3Temp |= data[7] & 0xFF;
/* 190 */       newGuid.Data3 = (short)data3Temp;
/*     */       
/* 192 */       newGuid.Data4[0] = data[8];
/* 193 */       newGuid.Data4[1] = data[9];
/* 194 */       newGuid.Data4[2] = data[10];
/* 195 */       newGuid.Data4[3] = data[11];
/* 196 */       newGuid.Data4[4] = data[12];
/* 197 */       newGuid.Data4[5] = data[13];
/* 198 */       newGuid.Data4[6] = data[14];
/* 199 */       newGuid.Data4[7] = data[15];
/*     */       
/* 201 */       newGuid.writeFieldsToMemory();
/*     */       
/* 203 */       return newGuid;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static GUID fromString(String guid) {
/* 214 */       int y = 0;
/* 215 */       char[] _cnewguid = new char[32];
/* 216 */       char[] _cguid = guid.toCharArray();
/* 217 */       byte[] bdata = new byte[16];
/* 218 */       GUID newGuid = new GUID();
/*     */ 
/*     */       
/* 221 */       if (guid.length() > 38) {
/* 222 */         throw new IllegalArgumentException("Invalid guid length: " + guid
/* 223 */             .length());
/*     */       }
/*     */ 
/*     */       
/* 227 */       for (i = 0; i < _cguid.length; i++) {
/* 228 */         if (_cguid[i] != '{' && _cguid[i] != '-' && _cguid[i] != '}')
/*     */         {
/* 230 */           _cnewguid[y++] = _cguid[i];
/*     */         }
/*     */       } 
/*     */       
/* 234 */       for (int i = 0; i < 32; i += 2) {
/* 235 */         bdata[i / 2] = 
/* 236 */           (byte)((Character.digit(_cnewguid[i], 16) << 4) + Character.digit(_cnewguid[i + 1], 16) & 0xFF);
/*     */       }
/*     */       
/* 239 */       if (bdata.length != 16) {
/* 240 */         throw new IllegalArgumentException("Invalid data length: " + bdata.length);
/*     */       }
/*     */ 
/*     */       
/* 244 */       long data1Temp = (bdata[0] & 0xFF);
/* 245 */       data1Temp <<= 8;
/* 246 */       data1Temp |= (bdata[1] & 0xFF);
/* 247 */       data1Temp <<= 8;
/* 248 */       data1Temp |= (bdata[2] & 0xFF);
/* 249 */       data1Temp <<= 8;
/* 250 */       data1Temp |= (bdata[3] & 0xFF);
/* 251 */       newGuid.Data1 = (int)data1Temp;
/*     */       
/* 253 */       int data2Temp = bdata[4] & 0xFF;
/* 254 */       data2Temp <<= 8;
/* 255 */       data2Temp |= bdata[5] & 0xFF;
/* 256 */       newGuid.Data2 = (short)data2Temp;
/*     */       
/* 258 */       int data3Temp = bdata[6] & 0xFF;
/* 259 */       data3Temp <<= 8;
/* 260 */       data3Temp |= bdata[7] & 0xFF;
/* 261 */       newGuid.Data3 = (short)data3Temp;
/*     */       
/* 263 */       newGuid.Data4[0] = bdata[8];
/* 264 */       newGuid.Data4[1] = bdata[9];
/* 265 */       newGuid.Data4[2] = bdata[10];
/* 266 */       newGuid.Data4[3] = bdata[11];
/* 267 */       newGuid.Data4[4] = bdata[12];
/* 268 */       newGuid.Data4[5] = bdata[13];
/* 269 */       newGuid.Data4[6] = bdata[14];
/* 270 */       newGuid.Data4[7] = bdata[15];
/*     */       
/* 272 */       newGuid.writeFieldsToMemory();
/*     */       
/* 274 */       return newGuid;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static GUID newGuid() {
/* 284 */       ng = new SecureRandom();
/* 285 */       byte[] randomBytes = new byte[16];
/*     */       
/* 287 */       ng.nextBytes(randomBytes);
/* 288 */       randomBytes[6] = (byte)(randomBytes[6] & 0xF);
/* 289 */       randomBytes[6] = (byte)(randomBytes[6] | 0x40);
/* 290 */       randomBytes[8] = (byte)(randomBytes[8] & 0x3F);
/* 291 */       randomBytes[8] = (byte)(randomBytes[8] | 0x80);
/*     */       
/* 293 */       return new GUID(randomBytes);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public byte[] toByteArray() {
/* 302 */       byte[] guid = new byte[16];
/*     */       
/* 304 */       byte[] bytes1 = new byte[4];
/* 305 */       bytes1[0] = (byte)(this.Data1 >> 24);
/* 306 */       bytes1[1] = (byte)(this.Data1 >> 16);
/* 307 */       bytes1[2] = (byte)(this.Data1 >> 8);
/* 308 */       bytes1[3] = (byte)(this.Data1 >> 0);
/*     */       
/* 310 */       byte[] bytes2 = new byte[4];
/* 311 */       bytes2[0] = (byte)(this.Data2 >> 24);
/* 312 */       bytes2[1] = (byte)(this.Data2 >> 16);
/* 313 */       bytes2[2] = (byte)(this.Data2 >> 8);
/* 314 */       bytes2[3] = (byte)(this.Data2 >> 0);
/*     */       
/* 316 */       byte[] bytes3 = new byte[4];
/* 317 */       bytes3[0] = (byte)(this.Data3 >> 24);
/* 318 */       bytes3[1] = (byte)(this.Data3 >> 16);
/* 319 */       bytes3[2] = (byte)(this.Data3 >> 8);
/* 320 */       bytes3[3] = (byte)(this.Data3 >> 0);
/*     */       
/* 322 */       System.arraycopy(bytes1, 0, guid, 0, 4);
/* 323 */       System.arraycopy(bytes2, 2, guid, 4, 2);
/* 324 */       System.arraycopy(bytes3, 2, guid, 6, 2);
/* 325 */       System.arraycopy(this.Data4, 0, guid, 8, 8);
/*     */       
/* 327 */       return guid;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toGuidString() {
/* 337 */       String HEXES = "0123456789ABCDEF";
/* 338 */       byte[] bGuid = toByteArray();
/*     */       
/* 340 */       StringBuilder hexStr = new StringBuilder(2 * bGuid.length);
/* 341 */       hexStr.append("{");
/*     */       
/* 343 */       for (int i = 0; i < bGuid.length; i++) {
/* 344 */         char ch1 = "0123456789ABCDEF".charAt((bGuid[i] & 0xF0) >> 4);
/* 345 */         char ch2 = "0123456789ABCDEF".charAt(bGuid[i] & 0xF);
/* 346 */         hexStr.append(ch1).append(ch2);
/*     */         
/* 348 */         if (i == 3 || i == 5 || i == 7 || i == 9) {
/* 349 */           hexStr.append("-");
/*     */         }
/*     */       } 
/* 352 */       hexStr.append("}");
/* 353 */       return hexStr.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void writeFieldsToMemory() {
/* 360 */       writeField("Data1");
/* 361 */       writeField("Data2");
/* 362 */       writeField("Data3");
/* 363 */       writeField("Data4");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Data1", "Data2", "Data3", "Data4" }); }
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
/*     */   public static class CLSID
/*     */     extends GUID
/*     */   {
/*     */     public static class ByReference
/*     */       extends Guid.GUID
/*     */     {
/*     */       public ByReference() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 404 */       public ByReference(Guid.GUID guid) { super(guid); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public ByReference(Pointer memory) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public CLSID() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 430 */     public CLSID(String guid) { super(guid); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 439 */     public CLSID(Guid.GUID guid) { super(guid); }
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
/*     */   public static class REFIID
/*     */     extends IID
/*     */   {
/*     */     public REFIID() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 464 */     public REFIID(Pointer memory) { super(memory); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 475 */     public REFIID(byte[] data) { super(data); }
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
/*     */   public static class IID
/*     */     extends GUID
/*     */   {
/*     */     public IID() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 502 */     public IID(Pointer memory) { super(memory); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 512 */     public IID(String iid) { super(iid); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 523 */     public IID(byte[] data) { super(data); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Guid.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */