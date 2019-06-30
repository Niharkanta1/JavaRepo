/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Memory;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface Sspi
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int MAX_TOKEN_SIZE = 12288;
/*     */   public static final int SECPKG_CRED_INBOUND = 1;
/*     */   public static final int SECPKG_CRED_OUTBOUND = 2;
/*     */   public static final int SECURITY_NATIVE_DREP = 16;
/*     */   public static final int ISC_REQ_ALLOCATE_MEMORY = 256;
/*     */   public static final int ISC_REQ_CONFIDENTIALITY = 16;
/*     */   public static final int ISC_REQ_CONNECTION = 2048;
/*     */   public static final int ISC_REQ_DELEGATE = 1;
/*     */   public static final int ISC_REQ_EXTENDED_ERROR = 16384;
/*     */   public static final int ISC_REQ_INTEGRITY = 65536;
/*     */   public static final int ISC_REQ_MUTUAL_AUTH = 2;
/*     */   public static final int ISC_REQ_REPLAY_DETECT = 4;
/*     */   public static final int ISC_REQ_SEQUENCE_DETECT = 8;
/*     */   public static final int ISC_REQ_STREAM = 32768;
/*     */   public static final int SECBUFFER_VERSION = 0;
/*     */   public static final int SECBUFFER_EMPTY = 0;
/*     */   public static final int SECBUFFER_DATA = 1;
/*     */   public static final int SECBUFFER_TOKEN = 2;
/*     */   
/*     */   public static class SecHandle
/*     */     extends Structure
/*     */   {
/*     */     public Pointer dwLower;
/*     */     public Pointer dwUpper;
/*     */     
/* 144 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwLower", "dwUpper" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends SecHandle
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 162 */     public boolean isNull() { return (this.dwLower == null && this.dwUpper == null); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PSecHandle
/*     */     extends Structure
/*     */   {
/*     */     public Sspi.SecHandle.ByReference secHandle;
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PSecHandle
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */     
/* 180 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "secHandle" }); }
/*     */ 
/*     */     
/*     */     public PSecHandle() {}
/*     */ 
/*     */     
/*     */     public PSecHandle(Sspi.SecHandle h) {
/* 187 */       super(h.getPointer());
/* 188 */       read();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CredHandle
/*     */     extends SecHandle {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CtxtHandle
/*     */     extends SecHandle {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SecBuffer
/*     */     extends Structure
/*     */   {
/*     */     public int cbBuffer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends SecBuffer
/*     */       implements Structure.ByReference
/*     */     {
/*     */       public ByReference() {}
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 228 */       public ByReference(int type, int size) { super(type, size); }
/*     */ 
/*     */ 
/*     */       
/* 232 */       public ByReference(int type, byte[] token) { super(type, token); }
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
/* 244 */     public int BufferType = 0;
/*     */ 
/*     */     
/*     */     public Pointer pvBuffer;
/*     */ 
/*     */ 
/*     */     
/* 251 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cbBuffer", "BufferType", "pvBuffer" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SecBuffer() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SecBuffer(int type, int size) {
/* 268 */       this.cbBuffer = size;
/* 269 */       this.pvBuffer = new Memory(size);
/* 270 */       this.BufferType = type;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SecBuffer(int type, byte[] token) {
/* 281 */       this.cbBuffer = token.length;
/* 282 */       this.pvBuffer = new Memory(token.length);
/* 283 */       this.pvBuffer.write(0L, token, 0, token.length);
/* 284 */       this.BufferType = type;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     public byte[] getBytes() { return (this.pvBuffer == null) ? null : this.pvBuffer.getByteArray(0L, this.cbBuffer); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SecBufferDesc
/*     */     extends Structure
/*     */   {
/* 302 */     public int ulVersion = 0;
/*     */ 
/*     */ 
/*     */     
/* 306 */     public int cBuffers = 1;
/*     */ 
/*     */ 
/*     */     
/* 310 */     public Sspi.SecBuffer.ByReference[] pBuffers = { new Sspi.SecBuffer.ByReference() };
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 315 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "ulVersion", "cBuffers", "pBuffers" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SecBufferDesc() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 332 */     public SecBufferDesc(int type, byte[] token) { this.pBuffers[0] = new Sspi.SecBuffer.ByReference(type, token); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 341 */     public SecBufferDesc(int type, int tokenSize) { this.pBuffers[0] = new Sspi.SecBuffer.ByReference(type, tokenSize); }
/*     */ 
/*     */ 
/*     */     
/* 345 */     public byte[] getBytes() { return this.pBuffers[0].getBytes(); }
/*     */   }
/*     */ 
/*     */   
/*     */   public static class SECURITY_INTEGER
/*     */     extends Structure
/*     */   {
/*     */     public int dwLower;
/*     */     
/*     */     public int dwUpper;
/*     */ 
/*     */     
/* 357 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwLower", "dwUpper" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class TimeStamp
/*     */     extends SECURITY_INTEGER {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PSecPkgInfo
/*     */     extends Structure
/*     */   {
/*     */     public Sspi.SecPkgInfo.ByReference pPkgInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PSecPkgInfo
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 388 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "pPkgInfo" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 398 */     public Sspi.SecPkgInfo.ByReference[] toArray(int size) { return (ByReference[])this.pPkgInfo.toArray(size); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SecPkgInfo
/*     */     extends Structure
/*     */   {
/*     */     public int fCapabilities;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends SecPkgInfo
/*     */       implements Structure.ByReference {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 421 */     public short wVersion = 1;
/*     */ 
/*     */ 
/*     */     
/*     */     public short wRPCID;
/*     */ 
/*     */ 
/*     */     
/*     */     public int cbMaxToken;
/*     */ 
/*     */ 
/*     */     
/*     */     public WString Name;
/*     */ 
/*     */ 
/*     */     
/*     */     public WString Comment;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 442 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "fCapabilities", "wVersion", "wRPCID", "cbMaxToken", "Name", "Comment" }); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Sspi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */