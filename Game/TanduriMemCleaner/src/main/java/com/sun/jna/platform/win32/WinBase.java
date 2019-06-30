/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.Callback;
/*      */ import com.sun.jna.Platform;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.Structure;
/*      */ import com.sun.jna.Union;
/*      */ import com.sun.jna.ptr.ByteByReference;
/*      */ import com.sun.jna.win32.StdCallLibrary;
/*      */ import java.util.Arrays;
/*      */ import java.util.Date;
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
/*      */ public interface WinBase
/*      */   extends StdCallLibrary, WinDef, BaseTSD
/*      */ {
/*   36 */   public static final WinNT.HANDLE INVALID_HANDLE_VALUE = new WinNT.HANDLE(
/*   37 */       Pointer.createConstant((Pointer.SIZE == 8) ? -1L : 4294967295L));
/*      */ 
/*      */   
/*      */   public static final int WAIT_FAILED = -1;
/*      */ 
/*      */   
/*      */   public static final int WAIT_OBJECT_0 = 0;
/*      */   
/*      */   public static final int WAIT_ABANDONED = 128;
/*      */   
/*      */   public static final int WAIT_ABANDONED_0 = 128;
/*      */   
/*   49 */   public static final int MAX_COMPUTERNAME_LENGTH = Platform.isMac() ? 15 : 31;
/*      */   
/*      */   public static final int LOGON32_LOGON_INTERACTIVE = 2;
/*      */   
/*      */   public static final int LOGON32_LOGON_NETWORK = 3;
/*      */   public static final int LOGON32_LOGON_BATCH = 4;
/*      */   public static final int LOGON32_LOGON_SERVICE = 5;
/*      */   public static final int LOGON32_LOGON_UNLOCK = 7;
/*      */   public static final int LOGON32_LOGON_NETWORK_CLEARTEXT = 8;
/*      */   public static final int LOGON32_LOGON_NEW_CREDENTIALS = 9;
/*      */   public static final int LOGON32_PROVIDER_DEFAULT = 0;
/*      */   public static final int LOGON32_PROVIDER_WINNT35 = 1;
/*      */   public static final int LOGON32_PROVIDER_WINNT40 = 2;
/*      */   public static final int LOGON32_PROVIDER_WINNT50 = 3;
/*      */   public static final int HANDLE_FLAG_INHERIT = 1;
/*      */   public static final int HANDLE_FLAG_PROTECT_FROM_CLOSE = 2;
/*      */   public static final int STARTF_USESHOWWINDOW = 1;
/*      */   public static final int STARTF_USESIZE = 2;
/*      */   public static final int STARTF_USEPOSITION = 4;
/*      */   public static final int STARTF_USECOUNTCHARS = 8;
/*      */   public static final int STARTF_USEFILLATTRIBUTE = 16;
/*      */   public static final int STARTF_RUNFULLSCREEN = 32;
/*      */   public static final int STARTF_FORCEONFEEDBACK = 64;
/*      */   public static final int STARTF_FORCEOFFFEEDBACK = 128;
/*      */   public static final int STARTF_USESTDHANDLES = 256;
/*      */   public static final int DEBUG_PROCESS = 1;
/*      */   public static final int DEBUG_ONLY_THIS_PROCESS = 2;
/*      */   public static final int CREATE_SUSPENDED = 4;
/*      */   public static final int DETACHED_PROCESS = 8;
/*      */   public static final int CREATE_NEW_CONSOLE = 16;
/*      */   public static final int CREATE_NEW_PROCESS_GROUP = 512;
/*      */   public static final int CREATE_UNICODE_ENVIRONMENT = 1024;
/*      */   public static final int CREATE_SEPARATE_WOW_VDM = 2048;
/*      */   public static final int CREATE_SHARED_WOW_VDM = 4096;
/*      */   public static final int CREATE_FORCEDOS = 8192;
/*      */   public static final int INHERIT_PARENT_AFFINITY = 65536;
/*      */   public static final int CREATE_PROTECTED_PROCESS = 262144;
/*      */   public static final int EXTENDED_STARTUPINFO_PRESENT = 524288;
/*      */   public static final int CREATE_BREAKAWAY_FROM_JOB = 16777216;
/*      */   public static final int CREATE_PRESERVE_CODE_AUTHZ_LEVEL = 33554432;
/*      */   public static final int CREATE_DEFAULT_ERROR_MODE = 67108864;
/*      */   public static final int CREATE_NO_WINDOW = 134217728;
/*      */   public static final int FILE_ENCRYPTABLE = 0;
/*      */   public static final int FILE_IS_ENCRYPTED = 1;
/*      */   public static final int FILE_SYSTEM_ATTR = 2;
/*      */   public static final int FILE_ROOT_DIR = 3;
/*      */   public static final int FILE_SYSTEM_DIR = 4;
/*      */   public static final int FILE_UNKNOWN = 5;
/*      */   public static final int FILE_SYSTEM_NOT_SUPPORT = 6;
/*      */   public static final int FILE_USER_DISALLOWED = 7;
/*      */   public static final int FILE_READ_ONLY = 8;
/*      */   public static final int FILE_DIR_DISALOWED = 9;
/*      */   public static final int CREATE_FOR_IMPORT = 1;
/*      */   public static final int CREATE_FOR_DIR = 2;
/*      */   public static final int OVERWRITE_HIDDEN = 4;
/*      */   public static final int INVALID_FILE_SIZE = -1;
/*      */   public static final int INVALID_SET_FILE_POINTER = -1;
/*      */   public static final int INVALID_FILE_ATTRIBUTES = -1;
/*      */   public static final int STILL_ACTIVE = 259;
/*      */   public static final int LMEM_FIXED = 0;
/*      */   public static final int LMEM_MOVEABLE = 2;
/*      */   public static final int LMEM_NOCOMPACT = 16;
/*      */   public static final int LMEM_NODISCARD = 32;
/*      */   public static final int LMEM_ZEROINIT = 64;
/*      */   public static final int LMEM_MODIFY = 128;
/*      */   public static final int LMEM_DISCARDABLE = 3840;
/*      */   public static final int LMEM_VALID_FLAGS = 3954;
/*      */   public static final int LMEM_INVALID_HANDLE = 32768;
/*      */   public static final int LHND = 66;
/*      */   public static final int LPTR = 64;
/*      */   public static final int LMEM_DISCARDED = 16384;
/*      */   public static final int LMEM_LOCKCOUNT = 255;
/*      */   public static final int FORMAT_MESSAGE_ALLOCATE_BUFFER = 256;
/*      */   public static final int FORMAT_MESSAGE_IGNORE_INSERTS = 512;
/*      */   public static final int FORMAT_MESSAGE_FROM_STRING = 1024;
/*      */   public static final int FORMAT_MESSAGE_FROM_HMODULE = 2048;
/*      */   public static final int FORMAT_MESSAGE_FROM_SYSTEM = 4096;
/*      */   public static final int FORMAT_MESSAGE_ARGUMENT_ARRAY = 8192;
/*      */   public static final int DRIVE_UNKNOWN = 0;
/*      */   public static final int DRIVE_NO_ROOT_DIR = 1;
/*      */   public static final int DRIVE_REMOVABLE = 2;
/*      */   public static final int DRIVE_FIXED = 3;
/*      */   public static final int DRIVE_REMOTE = 4;
/*      */   public static final int DRIVE_CDROM = 5;
/*      */   public static final int DRIVE_RAMDISK = 6;
/*      */   public static final int INFINITE = -1;
/*      */   public static final int MOVEFILE_COPY_ALLOWED = 2;
/*      */   public static final int MOVEFILE_CREATE_HARDLINK = 16;
/*      */   public static final int MOVEFILE_DELAY_UNTIL_REBOOT = 4;
/*      */   public static final int MOVEFILE_FAIL_IF_NOT_TRACKABLE = 32;
/*      */   public static final int MOVEFILE_REPLACE_EXISTING = 1;
/*      */   public static final int MOVEFILE_WRITE_THROUGH = 8;
/*      */   public static final int PIPE_CLIENT_END = 0;
/*      */   public static final int PIPE_SERVER_END = 1;
/*      */   public static final int PIPE_ACCESS_DUPLEX = 3;
/*      */   public static final int PIPE_ACCESS_INBOUND = 1;
/*      */   public static final int PIPE_ACCESS_OUTBOUND = 2;
/*      */   public static final int PIPE_TYPE_BYTE = 0;
/*      */   public static final int PIPE_TYPE_MESSAGE = 4;
/*      */   public static final int PIPE_READMODE_BYTE = 0;
/*      */   public static final int PIPE_READMODE_MESSAGE = 2;
/*      */   public static final int PIPE_WAIT = 0;
/*      */   public static final int PIPE_NOWAIT = 1;
/*      */   public static final int PIPE_ACCEPT_REMOTE_CLIENTS = 0;
/*      */   public static final int PIPE_REJECT_REMOTE_CLIENTS = 8;
/*      */   public static final int PIPE_UNLIMITED_INSTANCES = 255;
/*      */   public static final int NMPWAIT_USE_DEFAULT_WAIT = 0;
/*      */   public static final int NMPWAIT_NOWAIT = 1;
/*      */   public static final int NMPWAIT_WAIT_FOREVER = -1;
/*      */   public static final int NOPARITY = 0;
/*      */   public static final int ODDPARITY = 1;
/*      */   public static final int EVENPARITY = 2;
/*      */   public static final int MARKPARITY = 3;
/*      */   public static final int SPACEPARITY = 4;
/*      */   public static final int ONESTOPBIT = 0;
/*      */   public static final int ONE5STOPBITS = 1;
/*      */   public static final int TWOSTOPBITS = 2;
/*      */   public static final int CBR_110 = 110;
/*      */   public static final int CBR_300 = 300;
/*      */   public static final int CBR_600 = 600;
/*      */   public static final int CBR_1200 = 1200;
/*      */   public static final int CBR_2400 = 2400;
/*      */   public static final int CBR_4800 = 4800;
/*      */   public static final int CBR_9600 = 9600;
/*      */   public static final int CBR_14400 = 14400;
/*      */   public static final int CBR_19200 = 19200;
/*      */   public static final int CBR_38400 = 38400;
/*      */   public static final int CBR_56000 = 56000;
/*      */   public static final int CBR_128000 = 128000;
/*      */   public static final int CBR_256000 = 256000;
/*      */   public static final int DTR_CONTROL_DISABLE = 0;
/*      */   public static final int DTR_CONTROL_ENABLE = 1;
/*      */   public static final int DTR_CONTROL_HANDSHAKE = 2;
/*      */   public static final int RTS_CONTROL_DISABLE = 0;
/*      */   public static final int RTS_CONTROL_ENABLE = 1;
/*      */   public static final int RTS_CONTROL_HANDSHAKE = 2;
/*      */   public static final int RTS_CONTROL_TOGGLE = 3;
/*      */   
/*      */   public static class FILETIME
/*      */     extends Structure
/*      */   {
/*      */     public int dwLowDateTime;
/*      */     public int dwHighDateTime;
/*      */     private static final long EPOCH_DIFF = 11644473600000L;
/*      */     
/*  194 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwLowDateTime", "dwHighDateTime" }); }
/*      */     
/*      */     public static class ByReference
/*      */       extends FILETIME
/*      */       implements Structure.ByReference
/*      */     {
/*      */       public ByReference() {}
/*      */       
/*  202 */       public ByReference(Pointer memory) { super(memory); }
/*      */     }
/*      */ 
/*      */     
/*      */     public FILETIME(Date date) {
/*  207 */       long rawValue = dateToFileTime(date);
/*  208 */       this.dwHighDateTime = (int)(rawValue >> 32 & 0xFFFFFFFFL);
/*  209 */       this.dwLowDateTime = (int)(rawValue & 0xFFFFFFFFL);
/*      */     }
/*      */ 
/*      */     
/*      */     public FILETIME() {}
/*      */     
/*      */     public FILETIME(Pointer memory) {
/*  216 */       super(memory);
/*  217 */       read();
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
/*      */     public static Date filetimeToDate(int high, int low) {
/*  243 */       long filetime = high << 32 | low & 0xFFFFFFFFL;
/*  244 */       long ms_since_16010101 = filetime / 10000L;
/*  245 */       long ms_since_19700101 = ms_since_16010101 - 11644473600000L;
/*  246 */       return new Date(ms_since_19700101);
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
/*      */     public static long dateToFileTime(Date date) {
/*  258 */       long ms_since_19700101 = date.getTime();
/*  259 */       long ms_since_16010101 = ms_since_19700101 + 11644473600000L;
/*  260 */       return ms_since_16010101 * 1000L * 10L;
/*      */     }
/*      */ 
/*      */     
/*  264 */     public Date toDate() { return filetimeToDate(this.dwHighDateTime, this.dwLowDateTime); }
/*      */ 
/*      */ 
/*      */     
/*  268 */     public long toLong() { return toDate().getTime(); }
/*      */ 
/*      */ 
/*      */     
/*  272 */     public String toString() { return super.toString() + ": " + toDate().toString(); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SYSTEMTIME
/*      */     extends Structure
/*      */   {
/*      */     public short wYear;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short wMonth;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short wDayOfWeek;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short wDay;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short wHour;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short wMinute;
/*      */ 
/*      */ 
/*      */     
/*      */     public short wSecond;
/*      */ 
/*      */ 
/*      */     
/*      */     public short wMilliseconds;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  320 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "wYear", "wMonth", "wDayOfWeek", "wDay", "wHour", "wMinute", "wSecond", "wMilliseconds" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class TIME_ZONE_INFORMATION
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.LONG Bias;
/*      */     
/*      */     public String StandardName;
/*      */     
/*      */     public WinBase.SYSTEMTIME StandardDate;
/*      */     
/*      */     public WinDef.LONG StandardBias;
/*      */     public String DaylightName;
/*      */     public WinBase.SYSTEMTIME DaylightDate;
/*      */     public WinDef.LONG DaylightBias;
/*      */     
/*  338 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Bias", "StandardName", "StandardDate", "StandardBias", "DaylightName", "DaylightDate", "DaylightBias" }); }
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
/*      */   public static class OVERLAPPED
/*      */     extends Structure
/*      */   {
/*      */     public BaseTSD.ULONG_PTR Internal;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BaseTSD.ULONG_PTR InternalHigh;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int Offset;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int OffsetHigh;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinNT.HANDLE hEvent;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  430 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Internal", "InternalHigh", "Offset", "OffsetHigh", "hEvent" }); }
/*      */   }
/*      */ 
/*      */   
/*      */   public static class SYSTEM_INFO
/*      */     extends Structure
/*      */   {
/*      */     public UNION processorArchitecture;
/*      */     
/*      */     public WinDef.DWORD dwPageSize;
/*      */     
/*      */     public Pointer lpMinimumApplicationAddress;
/*      */     
/*      */     public Pointer lpMaximumApplicationAddress;
/*      */     
/*      */     public BaseTSD.DWORD_PTR dwActiveProcessorMask;
/*      */     
/*      */     public WinDef.DWORD dwNumberOfProcessors;
/*      */     
/*      */     public WinDef.DWORD dwProcessorType;
/*      */     
/*      */     public WinDef.DWORD dwAllocationGranularity;
/*      */     
/*      */     public WinDef.WORD wProcessorLevel;
/*      */     public WinDef.WORD wProcessorRevision;
/*      */     
/*      */     public static class PI
/*      */       extends Structure
/*      */     {
/*      */       public WinDef.WORD wProcessorArchitecture;
/*      */       public WinDef.WORD wReserved;
/*      */       
/*      */       public static class ByReference
/*      */         extends PI
/*      */         implements Structure.ByReference {}
/*      */       
/*  466 */       protected List getFieldOrder() { return Arrays.asList(new String[] { "wProcessorArchitecture", "wReserved" }); }
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
/*      */     public static class UNION
/*      */       extends Union
/*      */     {
/*      */       public WinDef.DWORD dwOemID;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public WinBase.SYSTEM_INFO.PI pi;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  540 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "processorArchitecture", "dwPageSize", "lpMinimumApplicationAddress", "lpMaximumApplicationAddress", "dwActiveProcessorMask", "dwNumberOfProcessors", "dwProcessorType", "dwAllocationGranularity", "wProcessorLevel", "wProcessorRevision" }); }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class MEMORYSTATUSEX
/*      */     extends Structure
/*      */   {
/*  592 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwLength", "dwMemoryLoad", "ullTotalPhys", "ullAvailPhys", "ullTotalPageFile", "ullAvailPageFile", "ullTotalVirtual", "ullAvailVirtual", "ullAvailExtendedVirtual" }); }
/*      */ 
/*      */ 
/*      */     
/*  596 */     public WinDef.DWORD dwLength = new WinDef.DWORD(size());
/*      */ 
/*      */     
/*      */     public WinDef.DWORD dwMemoryLoad;
/*      */ 
/*      */     
/*      */     public WinDef.DWORDLONG ullTotalPhys;
/*      */ 
/*      */     
/*      */     public WinDef.DWORDLONG ullAvailPhys;
/*      */ 
/*      */     
/*      */     public WinDef.DWORDLONG ullTotalPageFile;
/*      */ 
/*      */     
/*      */     public WinDef.DWORDLONG ullAvailPageFile;
/*      */ 
/*      */     
/*      */     public WinDef.DWORDLONG ullTotalVirtual;
/*      */     
/*      */     public WinDef.DWORDLONG ullAvailVirtual;
/*      */     
/*      */     public WinDef.DWORDLONG ullAvailExtendedVirtual;
/*      */   }
/*      */ 
/*      */   
/*      */   public static class SECURITY_ATTRIBUTES
/*      */     extends Structure
/*      */   {
/*  625 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwLength", "lpSecurityDescriptor", "bInheritHandle" }); }
/*      */ 
/*      */ 
/*      */     
/*  629 */     public WinDef.DWORD dwLength = new WinDef.DWORD(size());
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Pointer lpSecurityDescriptor;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean bInheritHandle;
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class STARTUPINFO
/*      */     extends Structure
/*      */   {
/*  801 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "cb", "lpReserved", "lpDesktop", "lpTitle", "dwX", "dwY", "dwXSize", "dwYSize", "dwXCountChars", "dwYCountChars", "dwFillAttribute", "dwFlags", "wShowWindow", "cbReserved2", "lpReserved2", "hStdInput", "hStdOutput", "hStdError" }); }
/*      */ 
/*      */ 
/*      */     
/*  805 */     public WinDef.DWORD cb = new WinDef.DWORD(size());
/*      */     
/*      */     public String lpReserved;
/*      */     
/*      */     public String lpDesktop;
/*      */     
/*      */     public String lpTitle;
/*      */     
/*      */     public WinDef.DWORD dwX;
/*      */     
/*      */     public WinDef.DWORD dwY;
/*      */     
/*      */     public WinDef.DWORD dwXSize;
/*      */     
/*      */     public WinDef.DWORD dwYSize;
/*      */     
/*      */     public WinDef.DWORD dwXCountChars;
/*      */     
/*      */     public WinDef.DWORD dwYCountChars;
/*      */     
/*      */     public WinDef.DWORD dwFillAttribute;
/*      */     
/*      */     public int dwFlags;
/*      */     
/*      */     public WinDef.WORD wShowWindow;
/*      */     
/*      */     public WinDef.WORD cbReserved2;
/*      */     
/*      */     public ByteByReference lpReserved2;
/*      */     public WinNT.HANDLE hStdInput;
/*      */     public WinNT.HANDLE hStdOutput;
/*      */     public WinNT.HANDLE hStdError;
/*      */   }
/*      */   
/*      */   public static class PROCESS_INFORMATION
/*      */     extends Structure
/*      */   {
/*      */     public WinNT.HANDLE hProcess;
/*      */     public WinNT.HANDLE hThread;
/*      */     public WinDef.DWORD dwProcessId;
/*      */     public WinDef.DWORD dwThreadId;
/*      */     
/*  847 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "hProcess", "hThread", "dwProcessId", "dwThreadId" }); }
/*      */     
/*      */     public static class ByReference
/*      */       extends PROCESS_INFORMATION
/*      */       implements Structure.ByReference
/*      */     {
/*      */       public ByReference() {}
/*      */       
/*  855 */       public ByReference(Pointer memory) { super(memory); }
/*      */     }
/*      */ 
/*      */     
/*      */     public PROCESS_INFORMATION() {}
/*      */ 
/*      */     
/*      */     public PROCESS_INFORMATION(Pointer memory) {
/*  863 */       super(memory);
/*  864 */       read();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface THREAD_START_ROUTINE
/*      */     extends Callback
/*      */   {
/*      */     WinDef.DWORD apply(WinDef.LPVOID param1LPVOID);
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
/*      */   public static class FOREIGN_THREAD_START_ROUTINE
/*      */     extends Structure
/*      */   {
/*      */     WinDef.LPVOID foreignLocation;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  940 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "foreignLocation" }); }
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
/*      */   public static interface COMPUTER_NAME_FORMAT
/*      */   {
/*      */     public static final int ComputerNameNetBIOS = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNameDnsHostname = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNameDnsDomain = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNameDnsFullyQualified = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNamePhysicalNetBIOS = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNamePhysicalDnsHostname = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNamePhysicalDnsDomain = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNamePhysicalDnsFullyQualified = 7;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static final int ComputerNameMax = 8;
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
/*      */   public static interface FE_EXPORT_FUNC
/*      */     extends Callback
/*      */   {
/*      */     WinDef.DWORD callback(ByteByReference param1ByteByReference, Pointer param1Pointer, WinDef.ULONG param1ULONG);
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
/*      */   public static interface FE_IMPORT_FUNC
/*      */     extends Callback
/*      */   {
/*      */     WinDef.DWORD callback(ByteByReference param1ByteByReference, Pointer param1Pointer, WinDef.ULONGByReference param1ULONGByReference);
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
/*      */   public static class COMMTIMEOUTS
/*      */     extends Structure
/*      */   {
/*      */     public WinDef.DWORD ReadIntervalTimeout;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinDef.DWORD ReadTotalTimeoutMultiplier;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinDef.DWORD ReadTotalTimeoutConstant;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinDef.DWORD WriteTotalTimeoutMultiplier;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WinDef.DWORD WriteTotalTimeoutConstant;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1152 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "ReadIntervalTimeout", "ReadTotalTimeoutMultiplier", "ReadTotalTimeoutConstant", "WriteTotalTimeoutMultiplier", "WriteTotalTimeoutConstant" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DCB
/*      */     extends Structure
/*      */   {
/*      */     public static class DCBControllBits
/*      */       extends WinDef.DWORD
/*      */     {
/*      */       private static final long serialVersionUID = 8574966619718078579L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public String toString() {
/* 1172 */         StringBuilder stringBuilder = new StringBuilder();
/* 1173 */         stringBuilder.append('<');
/* 1174 */         stringBuilder.append("fBinary:1=");
/* 1175 */         stringBuilder.append(getfBinary() ? 49 : 48);
/* 1176 */         stringBuilder.append(", fParity:1=");
/* 1177 */         stringBuilder.append(getfParity() ? 49 : 48);
/* 1178 */         stringBuilder.append(", fOutxCtsFlow:1=");
/* 1179 */         stringBuilder.append(getfOutxCtsFlow() ? 49 : 48);
/* 1180 */         stringBuilder.append(", fOutxDsrFlow:1=");
/* 1181 */         stringBuilder.append(getfOutxDsrFlow() ? 49 : 48);
/* 1182 */         stringBuilder.append(", fDtrControl:2=");
/* 1183 */         stringBuilder.append(getfDtrControl());
/* 1184 */         stringBuilder.append(", fDsrSensitivity:1=");
/* 1185 */         stringBuilder.append(getfDsrSensitivity() ? 49 : 48);
/* 1186 */         stringBuilder.append(", fTXContinueOnXoff:1=");
/* 1187 */         stringBuilder.append(getfTXContinueOnXoff() ? 49 : 48);
/* 1188 */         stringBuilder.append(", fOutX:1=");
/* 1189 */         stringBuilder.append(getfOutX() ? 49 : 48);
/* 1190 */         stringBuilder.append(", fInX:1=");
/* 1191 */         stringBuilder.append(getfInX() ? 49 : 48);
/* 1192 */         stringBuilder.append(", fErrorChar:1=");
/* 1193 */         stringBuilder.append(getfErrorChar() ? 49 : 48);
/* 1194 */         stringBuilder.append(", fNull:1=");
/* 1195 */         stringBuilder.append(getfNull() ? 49 : 48);
/* 1196 */         stringBuilder.append(", fRtsControl:2=");
/* 1197 */         stringBuilder.append(getfRtsControl());
/* 1198 */         stringBuilder.append(", fAbortOnError:1=");
/* 1199 */         stringBuilder.append(getfAbortOnError() ? 49 : 48);
/* 1200 */         stringBuilder.append(", fDummy2:17=");
/* 1201 */         stringBuilder.append(getfDummy2());
/* 1202 */         stringBuilder.append('>');
/* 1203 */         return stringBuilder.toString();
/*      */       }
/*      */ 
/*      */       
/* 1207 */       public boolean getfAbortOnError() { return ((intValue() & 0x4000) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1211 */       public boolean getfBinary() { return ((intValue() & true) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1215 */       public boolean getfDsrSensitivity() { return ((intValue() & 0x40) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1219 */       public int getfDtrControl() { return intValue() >>> 4 & 0x3; }
/*      */ 
/*      */ 
/*      */       
/* 1223 */       public boolean getfErrorChar() { return ((intValue() & 0x400) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1227 */       public boolean getfInX() { return ((intValue() & 0x200) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1231 */       public boolean getfNull() { return ((intValue() & 0x800) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1235 */       public boolean getfOutX() { return ((intValue() & 0x100) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1239 */       public boolean getfOutxCtsFlow() { return ((intValue() & 0x4) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1243 */       public boolean getfOutxDsrFlow() { return ((intValue() & 0x8) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1247 */       public boolean getfParity() { return ((intValue() & 0x2) != 0); }
/*      */ 
/*      */ 
/*      */       
/* 1251 */       public int getfRtsControl() { return intValue() >>> 12 & 0x3; }
/*      */ 
/*      */ 
/*      */       
/* 1255 */       public int getfDummy2() { return intValue() >>> 15 & 0x1FFFF; }
/*      */ 
/*      */ 
/*      */       
/* 1259 */       public boolean getfTXContinueOnXoff() { return ((intValue() & 0x80) != 0); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfAbortOnError(boolean fAbortOnError) {
/* 1272 */         int tmp = leftShiftMask(fAbortOnError ? 1 : 0, (byte)14, 1, intValue());
/* 1273 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfBinary(boolean fBinary) {
/* 1284 */         int tmp = leftShiftMask(fBinary ? 1 : 0, (byte)0, 1, intValue());
/* 1285 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfDsrSensitivity(boolean fDsrSensitivity) {
/* 1297 */         int tmp = leftShiftMask(fDsrSensitivity ? 1 : 0, (byte)6, 1, intValue());
/* 1298 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfDtrControl(int fOutxDsrFlow) {
/* 1312 */         int tmp = leftShiftMask(fOutxDsrFlow, (byte)4, 3, intValue());
/* 1313 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfErrorChar(boolean fErrorChar) {
/* 1325 */         int tmp = leftShiftMask(fErrorChar ? 1 : 0, (byte)10, 1, intValue());
/* 1326 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfInX(boolean fInX) {
/* 1339 */         int tmp = leftShiftMask(fInX ? 1 : 0, (byte)9, 1, intValue());
/* 1340 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfNull(boolean fNull) {
/* 1349 */         int tmp = leftShiftMask(fNull ? 1 : 0, (byte)11, 1, intValue());
/* 1350 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfOutX(boolean fOutX) {
/* 1363 */         int tmp = leftShiftMask(fOutX ? 1 : 0, (byte)8, 1, intValue());
/* 1364 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfOutxCtsFlow(boolean fOutxCtsFlow) {
/* 1376 */         int tmp = leftShiftMask(fOutxCtsFlow ? 1 : 0, (byte)2, 1, intValue());
/* 1377 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfOutxDsrFlow(boolean fOutxDsrFlow) {
/* 1389 */         int tmp = leftShiftMask(fOutxDsrFlow ? 1 : 0, (byte)3, 1, intValue());
/* 1390 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfParity(boolean fParity) {
/* 1400 */         int tmp = leftShiftMask(fParity ? 1 : 0, (byte)1, 1, intValue());
/* 1401 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfRtsControl(int fRtsControl) {
/* 1416 */         int tmp = leftShiftMask(fRtsControl, (byte)12, 3, intValue());
/* 1417 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*      */       public void setfTXContinueOnXoff(boolean fTXContinueOnXoff) {
/* 1431 */         int tmp = leftShiftMask(fTXContinueOnXoff ? 1 : 0, (byte)7, 1, intValue());
/* 1432 */         setValue(tmp);
/*      */       }
/*      */ 
/*      */       
/*      */       private static int leftShiftMask(int valuetoset, byte shift, int mask, int storage) {
/* 1437 */         tmp = storage;
/* 1438 */         tmp &= (mask << shift ^ 0xFFFFFFFF);
/* 1439 */         return (valuetoset & mask) << shift;
/*      */       }
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1559 */     public WinDef.DWORD DCBlength = new WinDef.DWORD(size()); public WinDef.DWORD BaudRate;
/*      */     public DCBControllBits controllBits;
/*      */     public WinDef.WORD wReserved;
/*      */     
/* 1563 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "DCBlength", "BaudRate", "controllBits", "wReserved", "XonLim", "XoffLim", "ByteSize", "Parity", "StopBits", "XonChar", "XoffChar", "ErrorChar", "EofChar", "EvtChar", "wReserved1" }); }
/*      */     
/*      */     public WinDef.WORD XonLim;
/*      */     public WinDef.WORD XoffLim;
/*      */     public WinDef.BYTE ByteSize;
/*      */     public WinDef.BYTE Parity;
/*      */     public WinDef.BYTE StopBits;
/*      */     public char XonChar;
/*      */     public char XoffChar;
/*      */     public char ErrorChar;
/*      */     public char EofChar;
/*      */     public char EvtChar;
/*      */     public WinDef.WORD wReserved1;
/*      */   }
/*      */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */