/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.Memory;
/*      */ import com.sun.jna.Native;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.Structure;
/*      */ import com.sun.jna.Union;
/*      */ import com.sun.jna.win32.StdCallLibrary;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
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
/*      */ public interface WinRas
/*      */   extends StdCallLibrary
/*      */ {
/*      */   public static final int ERROR_BUFFER_TOO_SMALL = 603;
/*      */   public static final int ERROR_CANNOT_FIND_PHONEBOOK_ENTRY = 623;
/*      */   public static final int MAX_PATH = 260;
/*      */   public static final int UNLEN = 256;
/*      */   public static final int PWLEN = 256;
/*      */   public static final int DNLEN = 15;
/*      */   public static final int RAS_MaxEntryName = 256;
/*      */   public static final int RAS_MaxPhoneNumber = 128;
/*      */   public static final int RAS_MaxCallbackNumber = 128;
/*      */   public static final int RAS_MaxDeviceType = 16;
/*      */   public static final int RAS_MaxDeviceName = 128;
/*      */   public static final int RAS_MaxDnsSuffix = 256;
/*      */   public static final int RAS_MaxAreaCode = 10;
/*      */   public static final int RAS_MaxX25Address = 200;
/*      */   public static final int RAS_MaxIpAddress = 15;
/*      */   public static final int RAS_MaxFacilities = 200;
/*      */   public static final int RAS_MaxUserData = 200;
/*      */   public static final int RAS_MaxPadType = 32;
/*      */   public static final int RASCS_Connected = 8192;
/*      */   public static final int RASCS_Disconnected = 8193;
/*      */   public static final int RASCM_UserName = 1;
/*      */   public static final int RASCM_Password = 2;
/*      */   public static final int RASCM_Domain = 4;
/*      */   public static final int RASTUNNELENDPOINT_IPv4 = 1;
/*      */   public static final int RASTUNNELENDPOINT_IPv6 = 2;
/*      */   public static final String RASDT_Modem = "modem";
/*      */   
/*      */   public static class RASEAPINFO
/*      */     extends Structure
/*      */   {
/*      */     public int dwSizeofEapInfo;
/*      */     public Pointer pbEapInfo;
/*      */     
/*      */     public RASEAPINFO() {}
/*      */     
/*      */     public RASEAPINFO(Pointer memory) {
/*   78 */       super(memory);
/*   79 */       read();
/*      */     }
/*      */     
/*      */     public RASEAPINFO(byte[] data) {
/*   83 */       this.pbEapInfo = new Memory(data.length);
/*   84 */       this.pbEapInfo.write(0L, data, 0, data.length);
/*   85 */       this.dwSizeofEapInfo = data.length;
/*   86 */       allocateMemory();
/*      */     }
/*      */ 
/*      */     
/*   90 */     public RASEAPINFO(String s) { this(Native.toByteArray(s)); }
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
/*  105 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSizeofEapInfo", "pbEapInfo" }); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  113 */     public byte[] getData() { return (this.pbEapInfo == null) ? null : this.pbEapInfo.getByteArray(0L, this.dwSizeofEapInfo); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASDEVSPECIFICINFO
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */     
/*      */     public Pointer pbDevSpecificInfo;
/*      */     
/*      */     public RASDEVSPECIFICINFO() {}
/*      */     
/*      */     public RASDEVSPECIFICINFO(Pointer memory) {
/*  127 */       super(memory);
/*  128 */       read();
/*      */     }
/*      */     
/*      */     public RASDEVSPECIFICINFO(byte[] data) {
/*  132 */       this.pbDevSpecificInfo = new Memory(data.length);
/*  133 */       this.pbDevSpecificInfo.write(0L, data, 0, data.length);
/*  134 */       this.dwSize = data.length;
/*  135 */       allocateMemory();
/*      */     }
/*      */ 
/*      */     
/*  139 */     public RASDEVSPECIFICINFO(String s) { this(Native.toByteArray(s)); }
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
/*  154 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "pbDevSpecificInfo" }); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  162 */     public byte[] getData() { return (this.pbDevSpecificInfo == null) ? null : this.pbDevSpecificInfo.getByteArray(0L, this.dwSize); }
/*      */   }
/*      */   
/*      */   public static class RASDIALEXTENSIONS extends Structure {
/*      */     public int dwSize;
/*      */     public int dwfOptions;
/*      */     public WinDef.HWND hwndParent;
/*      */     public BaseTSD.ULONG_PTR reserved;
/*      */     public BaseTSD.ULONG_PTR reserved1;
/*      */     public WinRas.RASEAPINFO RasEapInfo;
/*      */     public WinDef.BOOL fSkipPppAuth;
/*      */     public WinRas.RASDEVSPECIFICINFO RasDevSpecificInfo;
/*      */     
/*  175 */     public RASDIALEXTENSIONS() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASDIALEXTENSIONS(Pointer memory) {
/*  179 */       super(memory);
/*  180 */       read();
/*      */     }
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
/*      */     public static class ByReference
/*      */       extends RASDIALEXTENSIONS
/*      */       implements Structure.ByReference {}
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
/*  225 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "dwfOptions", "hwndParent", "reserved", "reserved1", "RasEapInfo", "fSkipPppAuth", "RasDevSpecificInfo" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASDIALPARAMS
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */ 
/*      */     
/*  235 */     public RASDIALPARAMS() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASDIALPARAMS(Pointer memory) {
/*  239 */       super(memory);
/*  240 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends RASDIALPARAMS
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  254 */     public char[] szEntryName = new char[257];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  259 */     public char[] szPhoneNumber = new char[129];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  265 */     public char[] szCallbackNumber = new char[129];
/*      */ 
/*      */ 
/*      */     
/*  269 */     public char[] szUserName = new char[257];
/*      */ 
/*      */ 
/*      */     
/*  273 */     public char[] szPassword = new char[257];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  278 */     public char[] szDomain = new char[16];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  283 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "szEntryName", "szPhoneNumber", "szCallbackNumber", "szUserName", "szPassword", "szDomain" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASCONN
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */     public WinNT.HANDLE hrasconn;
/*      */     
/*  293 */     public RASCONN() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASCONN(Pointer memory) {
/*  297 */       super(memory);
/*  298 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends RASCONN
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  316 */     public char[] szEntryName = new char[257];
/*      */ 
/*      */ 
/*      */     
/*  320 */     public char[] szDeviceType = new char[17];
/*      */ 
/*      */ 
/*      */     
/*  324 */     public char[] szDeviceName = new char[129];
/*      */ 
/*      */ 
/*      */     
/*  328 */     public char[] szPhonebook = new char[260];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwSubEntry;
/*      */ 
/*      */ 
/*      */     
/*      */     public Guid.GUID guidEntry;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwFlags;
/*      */ 
/*      */ 
/*      */     
/*      */     public WinNT.LUID luid;
/*      */ 
/*      */ 
/*      */     
/*      */     public Guid.GUID guidCorrelationId;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  354 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "hrasconn", "szEntryName", "szDeviceType", "szDeviceName", "szPhonebook", "dwSubEntry", "guidEntry", "dwFlags", "luid", "guidCorrelationId" }); } }
/*      */   
/*      */   public static class RAS_STATS extends Structure { public int dwSize;
/*      */     public int dwBytesXmited;
/*      */     public int dwBytesRcved;
/*      */     public int dwFramesXmited;
/*      */     public int dwFramesRcved;
/*      */     public int dwCrcErr;
/*      */     public int dwTimeoutErr;
/*      */     
/*  364 */     public RAS_STATS() { this.dwSize = size(); }
/*      */     public int dwAlignmentErr; public int dwHardwareOverrunErr; public int dwFramingErr; public int dwBufferOverrunErr; public int dwCompressionRatioIn; public int dwCompressionRatioOut; public int dwBps; public int dwConnectDuration;
/*      */     
/*      */     public RAS_STATS(Pointer memory) {
/*  368 */       super(memory);
/*  369 */       read();
/*      */     }
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
/*  436 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "dwBytesXmited", "dwBytesRcved", "dwFramesXmited", "dwFramesRcved", "dwCrcErr", "dwTimeoutErr", "dwAlignmentErr", "dwHardwareOverrunErr", "dwFramingErr", "dwBufferOverrunErr", "dwCompressionRatioIn", "dwCompressionRatioOut", "dwBps", "dwConnectDuration" }); } }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RASIPV4ADDR
/*      */     extends Structure
/*      */   {
/*      */     public RASIPV4ADDR() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public RASIPV4ADDR(Pointer memory) {
/*  449 */       super(memory);
/*  450 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  456 */     public byte[] addr = new byte[8];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  461 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "addr" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RASIPV6ADDR
/*      */     extends Structure
/*      */   {
/*      */     public RASIPV6ADDR() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public RASIPV6ADDR(Pointer memory) {
/*  474 */       super(memory);
/*  475 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  481 */     public byte[] addr = new byte[16];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  486 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "addr" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASPPPIP
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */     public int dwError;
/*      */     
/*  496 */     public RASPPPIP() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASPPPIP(Pointer memory) {
/*  500 */       super(memory);
/*  501 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends RASPPPIP
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  520 */     public char[] szIpAddress = new char[16];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  527 */     public char[] szServerIpAddress = new char[16];
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwOptions;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwServerOptions;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  540 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "dwError", "szIpAddress", "szServerIpAddress", "dwOptions", "dwServerOptions" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASTUNNELENDPOINT
/*      */     extends Structure
/*      */   {
/*      */     public int dwType;
/*      */     public UNION u;
/*      */     
/*      */     public RASTUNNELENDPOINT() {}
/*      */     
/*      */     public RASTUNNELENDPOINT(Pointer memory) {
/*  553 */       super(memory);
/*  554 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class UNION
/*      */       extends Union
/*      */     {
/*      */       public WinRas.RASIPV4ADDR ipv4;
/*      */ 
/*      */ 
/*      */       
/*      */       public WinRas.RASIPV6ADDR ipv6;
/*      */ 
/*      */ 
/*      */       
/*      */       public static class ByReference
/*      */         extends UNION
/*      */         implements Structure.ByReference {}
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  578 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwType", "u" }); }
/*      */ 
/*      */ 
/*      */     
/*      */     public void read() {
/*  583 */       super.read();
/*      */       
/*  585 */       switch (this.dwType) {
/*      */         case 1:
/*  587 */           this.u.setType(WinRas.RASIPV4ADDR.class);
/*      */           break;
/*      */         case 2:
/*  590 */           this.u.setType(WinRas.RASIPV6ADDR.class);
/*      */           break;
/*      */         default:
/*  593 */           this.u.setType(WinRas.RASIPV4ADDR.class);
/*      */           break;
/*      */       } 
/*      */       
/*  597 */       this.u.read();
/*      */     }
/*      */   }
/*      */   
/*      */   public static class RASCONNSTATUS
/*      */     extends Structure {
/*      */     public int dwSize;
/*      */     public int rasconnstate;
/*      */     public int dwError;
/*      */     
/*  607 */     public RASCONNSTATUS() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASCONNSTATUS(Pointer memory) {
/*  611 */       super(memory);
/*  612 */       read();
/*      */     }
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
/*  633 */     public char[] szDeviceType = new char[17];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  639 */     public char[] szDeviceName = new char[129];
/*      */ 
/*      */ 
/*      */     
/*  643 */     public char[] szPhoneNumber = new char[129];
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASTUNNELENDPOINT localEndPoint;
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASTUNNELENDPOINT remoteEndPoint;
/*      */ 
/*      */ 
/*      */     
/*      */     public int rasconnsubstate;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  660 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "rasconnstate", "dwError", "szDeviceType", "szDeviceName", "szPhoneNumber", "localEndPoint", "remoteEndPoint", "rasconnsubstate" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class RASCREDENTIALS
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */     public int dwMask;
/*      */     
/*  670 */     public RASCREDENTIALS() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASCREDENTIALS(Pointer memory) {
/*  674 */       super(memory);
/*  675 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends RASCREDENTIALS
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  692 */     public char[] szUserName = new char[257];
/*      */ 
/*      */ 
/*      */     
/*  696 */     public char[] szPassword = new char[257];
/*      */ 
/*      */ 
/*      */     
/*  700 */     public char[] szDomain = new char[16];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  705 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "dwMask", "szUserName", "szPassword", "szDomain" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RASIPADDR
/*      */     extends Structure
/*      */   {
/*      */     public RASIPADDR() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public RASIPADDR(Pointer memory) {
/*  719 */       super(memory);
/*  720 */       read();
/*      */     }
/*      */     
/*  723 */     public byte[] addr = new byte[4];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  728 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "addr" }); }
/*      */   }
/*      */   
/*      */   public static class RASENTRY
/*      */     extends Structure
/*      */   {
/*      */     public int dwSize;
/*      */     public int dwfOptions;
/*      */     public int dwCountryID;
/*      */     public int dwCountryCode;
/*      */     
/*  739 */     public RASENTRY() { this.dwSize = size(); }
/*      */ 
/*      */     
/*      */     public RASENTRY(Pointer memory) {
/*  743 */       super(memory);
/*  744 */       read();
/*      */     }
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
/*      */     public static class ByReference
/*      */       extends RASENTRY
/*      */       implements Structure.ByReference {}
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
/*  777 */     public char[] szAreaCode = new char[11];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  782 */     public char[] szLocalPhoneNumber = new char[129];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwAlternateOffset;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPADDR ipaddr;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPADDR ipaddrDns;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPADDR ipaddrDnsAlt;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPADDR ipaddrWins;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPADDR ipaddrWinsAlt;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwFrameSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwfNetProtocols;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwFramingProtocol;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  832 */     public char[] szScript = new char[260];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  837 */     public char[] szAutodialDll = new char[260];
/*      */ 
/*      */ 
/*      */     
/*  841 */     public char[] szAutodialFunc = new char[260];
/*      */ 
/*      */ 
/*      */     
/*  845 */     public char[] szDeviceType = new char[17];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  850 */     public char[] szDeviceName = new char[129];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  855 */     public char[] szX25PadType = new char[33];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  860 */     public char[] szX25Address = new char[201];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  865 */     public char[] szX25Facilities = new char[201];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  870 */     public char[] szX25UserData = new char[201];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwChannels;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwReserved1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwReserved2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwSubEntries;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwDialMode;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwDialExtraPercent;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwDialExtraSampleSeconds;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwHangUpExtraPercent;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwHangUpExtraSampleSeconds;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwIdleDisconnectSeconds;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwEncryptionType;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwCustomAuthKey;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Guid.GUID guidId;
/*      */ 
/*      */ 
/*      */     
/*  944 */     public char[] szCustomDialDll = new char[260];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwVpnStrategy;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwfOptions2;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwfOptions3;
/*      */ 
/*      */ 
/*      */     
/*  962 */     public char[] szDnsSuffix = new char[256];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwTcpWindowSize;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  972 */     public char[] szPrerequisitePbk = new char[260];
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  979 */     public char[] szPrerequisiteEntry = new char[257];
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwRedialCount;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwRedialPause;
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPV6ADDR ipv6addrDns;
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPV6ADDR ipv6addrDnsAlt;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwIPv4InterfaceMetric;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwIPv6InterfaceMetric;
/*      */ 
/*      */ 
/*      */     
/*      */     public WinRas.RASIPV6ADDR ipv6addr;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwIPv6PrefixLength;
/*      */ 
/*      */ 
/*      */     
/*      */     public int dwNetworkOutageTime;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1022 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "dwfOptions", "dwCountryID", "dwCountryCode", "szAreaCode", "szLocalPhoneNumber", "dwAlternateOffset", "ipaddr", "ipaddrDns", "ipaddrDnsAlt", "ipaddrWins", "ipaddrWinsAlt", "dwFrameSize", "dwfNetProtocols", "dwFramingProtocol", "szScript", "szAutodialDll", "szAutodialFunc", "szDeviceType", "szDeviceName", "szX25PadType", "szX25Address", "szX25Facilities", "szX25UserData", "dwChannels", "dwReserved1", "dwReserved2", "dwSubEntries", "dwDialMode", "dwDialExtraPercent", "dwDialExtraSampleSeconds", "dwHangUpExtraPercent", "dwHangUpExtraSampleSeconds", "dwIdleDisconnectSeconds", "dwType", "dwEncryptionType", "dwCustomAuthKey", "guidId", "szCustomDialDll", "dwVpnStrategy", "dwfOptions2", "dwfOptions3", "szDnsSuffix", "dwTcpWindowSize", "szPrerequisitePbk", "szPrerequisiteEntry", "dwRedialCount", "dwRedialPause", "ipv6addrDns", "ipv6addrDnsAlt", "dwIPv4InterfaceMetric", "dwIPv6InterfaceMetric", "ipv6addr", "dwIPv6PrefixLength", "dwNetworkOutageTime" }); }
/*      */   }
/*      */   
/*      */   public static interface RasDialFunc2 extends StdCallLibrary.StdCallCallback {
/*      */     int dialNotification(int param1Int1, int param1Int2, WinNT.HANDLE param1HANDLE, int param1Int3, int param1Int4, int param1Int5, int param1Int6);
/*      */   }
/*      */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinRas.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */