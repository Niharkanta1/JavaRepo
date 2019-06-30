/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Memory;
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import com.sun.jna.win32.StdCallLibrary;
/*     */ import com.sun.jna.win32.W32APIOptions;
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
/*     */ public interface Winspool
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int CCHDEVICENAME = 32;
/*     */   public static final int PRINTER_CHANGE_ADD_PRINTER = 1;
/*     */   public static final int PRINTER_CHANGE_SET_PRINTER = 2;
/*     */   public static final int PRINTER_CHANGE_DELETE_PRINTER = 4;
/*     */   public static final int PRINTER_CHANGE_FAILED_CONNECTION_PRINTER = 8;
/*     */   public static final int PRINTER_CHANGE_PRINTER = 255;
/*     */   public static final int PRINTER_CHANGE_ADD_JOB = 256;
/*     */   public static final int PRINTER_CHANGE_SET_JOB = 512;
/*     */   public static final int PRINTER_CHANGE_DELETE_JOB = 1024;
/*     */   public static final int PRINTER_CHANGE_WRITE_JOB = 2048;
/*     */   public static final int PRINTER_CHANGE_JOB = 65280;
/*     */   public static final int PRINTER_CHANGE_ADD_FORM = 65536;
/*     */   public static final int PRINTER_CHANGE_SET_FORM = 131072;
/*     */   public static final int PRINTER_CHANGE_DELETE_FORM = 262144;
/*     */   public static final int PRINTER_CHANGE_FORM = 458752;
/*     */   public static final int PRINTER_CHANGE_ADD_PORT = 1048576;
/*     */   public static final int PRINTER_CHANGE_CONFIGURE_PORT = 2097152;
/*     */   public static final int PRINTER_CHANGE_DELETE_PORT = 4194304;
/*     */   public static final int PRINTER_CHANGE_PORT = 7340032;
/*     */   public static final int PRINTER_CHANGE_ADD_PRINT_PROCESSOR = 16777216;
/*     */   public static final int PRINTER_CHANGE_DELETE_PRINT_PROCESSOR = 67108864;
/*     */   public static final int PRINTER_CHANGE_PRINT_PROCESSOR = 117440512;
/*     */   public static final int PRINTER_CHANGE_SERVER = 134217728;
/*     */   public static final int PRINTER_CHANGE_ADD_PRINTER_DRIVER = 268435456;
/*     */   public static final int PRINTER_CHANGE_SET_PRINTER_DRIVER = 536870912;
/*     */   public static final int PRINTER_CHANGE_DELETE_PRINTER_DRIVER = 1073741824;
/*     */   public static final int PRINTER_CHANGE_PRINTER_DRIVER = 1879048192;
/*     */   public static final int PRINTER_CHANGE_TIMEOUT = -2147483648;
/*     */   public static final int PRINTER_CHANGE_ALL_WIN7 = 2138570751;
/*     */   public static final int PRINTER_CHANGE_ALL = 2004353023;
/*     */   public static final int PRINTER_ENUM_DEFAULT = 1;
/*     */   public static final int PRINTER_ENUM_LOCAL = 2;
/*     */   public static final int PRINTER_ENUM_CONNECTIONS = 4;
/*     */   public static final int PRINTER_ENUM_FAVORITE = 4;
/*     */   public static final int PRINTER_ENUM_NAME = 8;
/*     */   public static final int PRINTER_ENUM_REMOTE = 16;
/*     */   public static final int PRINTER_ENUM_SHARED = 32;
/*     */   public static final int PRINTER_ENUM_NETWORK = 64;
/*     */   public static final int PRINTER_ENUM_EXPAND = 16384;
/*     */   public static final int PRINTER_ENUM_CONTAINER = 32768;
/*     */   public static final int PRINTER_ENUM_ICONMASK = 16711680;
/*     */   public static final int PRINTER_ENUM_ICON1 = 65536;
/*     */   public static final int PRINTER_ENUM_ICON2 = 131072;
/*     */   public static final int PRINTER_ENUM_ICON3 = 262144;
/*     */   public static final int PRINTER_ENUM_ICON4 = 524288;
/*     */   public static final int PRINTER_ENUM_ICON5 = 1048576;
/*     */   public static final int PRINTER_ENUM_ICON6 = 2097152;
/*     */   public static final int PRINTER_ENUM_ICON7 = 4194304;
/*     */   public static final int PRINTER_ENUM_ICON8 = 8388608;
/*     */   public static final int PRINTER_ENUM_HIDE = 16777216;
/*  95 */   public static final Winspool INSTANCE = (Winspool)Native.loadLibrary("Winspool.drv", Winspool.class, W32APIOptions.UNICODE_OPTIONS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean EnumPrinters(int paramInt1, String paramString, int paramInt2, Pointer paramPointer, int paramInt3, IntByReference paramIntByReference1, IntByReference paramIntByReference2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean OpenPrinter(String paramString, WinNT.HANDLEByReference paramHANDLEByReference, LPPRINTER_DEFAULTS paramLPPRINTER_DEFAULTS);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   WinNT.HANDLE FindFirstPrinterChangeNotification(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2, WinDef.LPVOID paramLPVOID);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean FindNextPrinterChangeNotification(WinNT.HANDLE paramHANDLE, WinDef.DWORDByReference paramDWORDByReference, WinDef.LPVOID paramLPVOID1, WinDef.LPVOID paramLPVOID2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean FindClosePrinterChangeNotification(WinNT.HANDLE paramHANDLE);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   boolean EnumJobs(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2, int paramInt3, Pointer paramPointer, int paramInt4, IntByReference paramIntByReference1, IntByReference paramIntByReference2);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class PRINTER_INFO_1
/*     */     extends Structure
/*     */   {
/*     */     public int Flags;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pDescription;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pName;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pComment;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 170 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "Flags", "pDescription", "pName", "pComment" }); }
/*     */ 
/*     */ 
/*     */     
/*     */     public PRINTER_INFO_1() {}
/*     */ 
/*     */ 
/*     */     
/* 178 */     public PRINTER_INFO_1(int size) { super(new Memory(size)); }
/*     */   }
/*     */   
/*     */   public static class PRINTER_INFO_4
/*     */     extends Structure
/*     */   {
/*     */     public String pPrinterName;
/*     */     public String pServerName;
/*     */     public WinDef.DWORD Attributes;
/*     */     
/* 188 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "pPrinterName", "pServerName", "Attributes" }); }
/*     */ 
/*     */ 
/*     */     
/*     */     public PRINTER_INFO_4() {}
/*     */ 
/*     */ 
/*     */     
/* 196 */     public PRINTER_INFO_4(int size) { super(new Memory(size)); }
/*     */   }
/*     */   
/*     */   public static class LPPRINTER_DEFAULTS
/*     */     extends Structure
/*     */   {
/*     */     public String pDatatype;
/*     */     WinDef.PVOID pDevMode;
/*     */     int DesiredAccess;
/*     */     
/* 206 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "pDatatype", "pDevMode", "DesiredAccess" }); }
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
/*     */   public static class JOB_INFO_1
/*     */     extends Structure
/*     */   {
/*     */     public int JobId;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pPrinterName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pMachineName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pUserName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pDocument;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pDatatype;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String pStatus;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int Status;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int Priority;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int Position;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int TotalPages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int PagesPrinted;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WinBase.SYSTEMTIME Submitted;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 424 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "JobId", "pPrinterName", "pMachineName", "pUserName", "pDocument", "pDatatype", "pStatus", "Status", "Priority", "Position", "TotalPages", "PagesPrinted", "Submitted" }); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public JOB_INFO_1() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 434 */     public JOB_INFO_1(int size) { super(new Memory(size)); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Winspool.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */