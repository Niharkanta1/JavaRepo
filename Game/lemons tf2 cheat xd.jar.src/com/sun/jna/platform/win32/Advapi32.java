/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.WString;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import com.sun.jna.ptr.LongByReference;
/*    */ import com.sun.jna.ptr.PointerByReference;
/*    */ import com.sun.jna.win32.StdCallLibrary;
/*    */ import com.sun.jna.win32.W32APIOptions;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface Advapi32
/*    */   extends StdCallLibrary
/*    */ {
/* 50 */   public static final Advapi32 INSTANCE = (Advapi32)Native.loadLibrary("Advapi32", Advapi32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   public static final int MAX_KEY_LENGTH = 255;
/*    */   public static final int MAX_VALUE_NAME = 16383;
/*    */   public static final int RRF_RT_ANY = 65535;
/*    */   public static final int RRF_RT_DWORD = 24;
/*    */   public static final int RRF_RT_QWORD = 72;
/*    */   public static final int RRF_RT_REG_BINARY = 8;
/*    */   public static final int RRF_RT_REG_DWORD = 16;
/*    */   public static final int RRF_RT_REG_EXPAND_SZ = 4;
/*    */   public static final int RRF_RT_REG_MULTI_SZ = 32;
/*    */   public static final int RRF_RT_REG_NONE = 1;
/*    */   public static final int RRF_RT_REG_QWORD = 64;
/*    */   public static final int RRF_RT_REG_SZ = 2;
/*    */   
/*    */   boolean GetUserNameW(char[] paramArrayOfChar, IntByReference paramIntByReference);
/*    */   
/*    */   boolean LookupAccountName(String paramString1, String paramString2, WinNT.PSID paramPSID, IntByReference paramIntByReference1, char[] paramArrayOfChar, IntByReference paramIntByReference2, PointerByReference paramPointerByReference);
/*    */   
/*    */   boolean LookupAccountSid(String paramString, WinNT.PSID paramPSID, char[] paramArrayOfChar1, IntByReference paramIntByReference1, char[] paramArrayOfChar2, IntByReference paramIntByReference2, PointerByReference paramPointerByReference);
/*    */   
/*    */   boolean ConvertSidToStringSid(WinNT.PSID paramPSID, PointerByReference paramPointerByReference);
/*    */   
/*    */   boolean ConvertStringSidToSid(String paramString, WinNT.PSIDByReference paramPSIDByReference);
/*    */   
/*    */   int GetLengthSid(WinNT.PSID paramPSID);
/*    */   
/*    */   boolean IsValidSid(WinNT.PSID paramPSID);
/*    */   
/*    */   boolean IsWellKnownSid(WinNT.PSID paramPSID, int paramInt);
/*    */   
/*    */   boolean CreateWellKnownSid(int paramInt, WinNT.PSID paramPSID1, WinNT.PSID paramPSID2, IntByReference paramIntByReference);
/*    */   
/*    */   boolean LogonUser(String paramString1, String paramString2, String paramString3, int paramInt1, int paramInt2, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   boolean OpenThreadToken(WinNT.HANDLE paramHANDLE, int paramInt, boolean paramBoolean, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   boolean SetThreadToken(WinNT.HANDLEByReference paramHANDLEByReference, WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean OpenProcessToken(WinNT.HANDLE paramHANDLE, int paramInt, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   boolean DuplicateToken(WinNT.HANDLE paramHANDLE, int paramInt, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   boolean DuplicateTokenEx(WinNT.HANDLE paramHANDLE, int paramInt1, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, int paramInt2, int paramInt3, WinNT.HANDLEByReference paramHANDLEByReference);
/*    */   
/*    */   boolean GetTokenInformation(WinNT.HANDLE paramHANDLE, int paramInt1, Structure paramStructure, int paramInt2, IntByReference paramIntByReference);
/*    */   
/*    */   boolean ImpersonateLoggedOnUser(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean ImpersonateSelf(int paramInt);
/*    */   
/*    */   boolean RevertToSelf();
/*    */   
/*    */   int RegOpenKeyEx(WinReg.HKEY paramHKEY, String paramString, int paramInt1, int paramInt2, WinReg.HKEYByReference paramHKEYByReference);
/*    */   
/*    */   int RegQueryValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt, IntByReference paramIntByReference1, char[] paramArrayOfChar, IntByReference paramIntByReference2);
/*    */   
/*    */   int RegQueryValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt, IntByReference paramIntByReference1, byte[] paramArrayOfByte, IntByReference paramIntByReference2);
/*    */   
/*    */   int RegQueryValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   int RegQueryValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt, IntByReference paramIntByReference1, LongByReference paramLongByReference, IntByReference paramIntByReference2);
/*    */   
/*    */   int RegQueryValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt, IntByReference paramIntByReference1, Pointer paramPointer, IntByReference paramIntByReference2);
/*    */   
/*    */   int RegCloseKey(WinReg.HKEY paramHKEY);
/*    */   
/*    */   int RegDeleteValue(WinReg.HKEY paramHKEY, String paramString);
/*    */   
/*    */   int RegSetValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt1, int paramInt2, char[] paramArrayOfChar, int paramInt3);
/*    */   
/*    */   int RegSetValueEx(WinReg.HKEY paramHKEY, String paramString, int paramInt1, int paramInt2, byte[] paramArrayOfByte, int paramInt3);
/*    */   
/*    */   int RegCreateKeyEx(WinReg.HKEY paramHKEY, String paramString1, int paramInt1, String paramString2, int paramInt2, int paramInt3, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, WinReg.HKEYByReference paramHKEYByReference, IntByReference paramIntByReference);
/*    */   
/*    */   int RegDeleteKey(WinReg.HKEY paramHKEY, String paramString);
/*    */   
/*    */   int RegEnumKeyEx(WinReg.HKEY paramHKEY, int paramInt, char[] paramArrayOfChar1, IntByReference paramIntByReference1, IntByReference paramIntByReference2, char[] paramArrayOfChar2, IntByReference paramIntByReference3, WinBase.FILETIME paramFILETIME);
/*    */   
/*    */   int RegEnumValue(WinReg.HKEY paramHKEY, int paramInt, char[] paramArrayOfChar, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3, byte[] paramArrayOfByte, IntByReference paramIntByReference4);
/*    */   
/*    */   int RegQueryInfoKey(WinReg.HKEY paramHKEY, char[] paramArrayOfChar, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3, IntByReference paramIntByReference4, IntByReference paramIntByReference5, IntByReference paramIntByReference6, IntByReference paramIntByReference7, IntByReference paramIntByReference8, IntByReference paramIntByReference9, WinBase.FILETIME paramFILETIME);
/*    */   
/*    */   int RegGetValue(WinReg.HKEY paramHKEY, String paramString1, String paramString2, int paramInt, IntByReference paramIntByReference1, byte[] paramArrayOfByte, IntByReference paramIntByReference2);
/*    */   
/*    */   WinNT.HANDLE RegisterEventSource(String paramString1, String paramString2);
/*    */   
/*    */   boolean DeregisterEventSource(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   WinNT.HANDLE OpenEventLog(String paramString1, String paramString2);
/*    */   
/*    */   boolean CloseEventLog(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean GetNumberOfEventLogRecords(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
/*    */   
/*    */   boolean ReportEvent(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2, int paramInt3, WinNT.PSID paramPSID, int paramInt4, int paramInt5, String[] paramArrayOfString, Pointer paramPointer);
/*    */   
/*    */   boolean ClearEventLog(WinNT.HANDLE paramHANDLE, String paramString);
/*    */   
/*    */   boolean BackupEventLog(WinNT.HANDLE paramHANDLE, String paramString);
/*    */   
/*    */   WinNT.HANDLE OpenBackupEventLog(String paramString1, String paramString2);
/*    */   
/*    */   boolean ReadEventLog(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2, Pointer paramPointer, int paramInt3, IntByReference paramIntByReference1, IntByReference paramIntByReference2);
/*    */   
/*    */   boolean GetOldestEventLogRecord(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
/*    */   
/*    */   boolean QueryServiceStatusEx(Winsvc.SC_HANDLE paramSC_HANDLE, int paramInt1, Winsvc.SERVICE_STATUS_PROCESS paramSERVICE_STATUS_PROCESS, int paramInt2, IntByReference paramIntByReference);
/*    */   
/*    */   boolean ControlService(Winsvc.SC_HANDLE paramSC_HANDLE, int paramInt, Winsvc.SERVICE_STATUS paramSERVICE_STATUS);
/*    */   
/*    */   boolean StartService(Winsvc.SC_HANDLE paramSC_HANDLE, int paramInt, String[] paramArrayOfString);
/*    */   
/*    */   boolean CloseServiceHandle(Winsvc.SC_HANDLE paramSC_HANDLE);
/*    */   
/*    */   Winsvc.SC_HANDLE OpenService(Winsvc.SC_HANDLE paramSC_HANDLE, String paramString, int paramInt);
/*    */   
/*    */   Winsvc.SC_HANDLE OpenSCManager(String paramString1, String paramString2, int paramInt);
/*    */   
/*    */   boolean CreateProcessAsUser(WinNT.HANDLE paramHANDLE, String paramString1, String paramString2, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES1, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES2, boolean paramBoolean, int paramInt, String paramString3, String paramString4, WinBase.STARTUPINFO paramSTARTUPINFO, WinBase.PROCESS_INFORMATION paramPROCESS_INFORMATION);
/*    */   
/*    */   boolean AdjustTokenPrivileges(WinNT.HANDLE paramHANDLE, boolean paramBoolean, WinNT.TOKEN_PRIVILEGES paramTOKEN_PRIVILEGES1, int paramInt, WinNT.TOKEN_PRIVILEGES paramTOKEN_PRIVILEGES2, IntByReference paramIntByReference);
/*    */   
/*    */   boolean LookupPrivilegeName(String paramString, WinNT.LUID paramLUID, char[] paramArrayOfChar, IntByReference paramIntByReference);
/*    */   
/*    */   boolean LookupPrivilegeValue(String paramString1, String paramString2, WinNT.LUID paramLUID);
/*    */   
/*    */   boolean GetFileSecurity(WString paramWString, int paramInt1, Pointer paramPointer, int paramInt2, IntByReference paramIntByReference);
/*    */   
/*    */   int GetNamedSecurityInfo(String paramString, int paramInt1, int paramInt2, PointerByReference paramPointerByReference1, PointerByReference paramPointerByReference2, PointerByReference paramPointerByReference3, PointerByReference paramPointerByReference4, PointerByReference paramPointerByReference5);
/*    */   
/*    */   int SetNamedSecurityInfo(String paramString, int paramInt1, int paramInt2, Pointer paramPointer1, Pointer paramPointer2, Pointer paramPointer3, Pointer paramPointer4);
/*    */   
/*    */   int GetSecurityDescriptorLength(Pointer paramPointer);
/*    */   
/*    */   boolean IsValidSecurityDescriptor(Pointer paramPointer);
/*    */   
/*    */   boolean IsValidAcl(Pointer paramPointer);
/*    */   
/*    */   void MapGenericMask(WinDef.DWORDByReference paramDWORDByReference, WinNT.GENERIC_MAPPING paramGENERIC_MAPPING);
/*    */   
/*    */   boolean AccessCheck(Pointer paramPointer, WinNT.HANDLE paramHANDLE, WinDef.DWORD paramDWORD, WinNT.GENERIC_MAPPING paramGENERIC_MAPPING, WinNT.PRIVILEGE_SET paramPRIVILEGE_SET, WinDef.DWORDByReference paramDWORDByReference1, WinDef.DWORDByReference paramDWORDByReference2, WinDef.BOOLByReference paramBOOLByReference);
/*    */   
/*    */   boolean EncryptFile(WString paramWString);
/*    */   
/*    */   boolean DecryptFile(WString paramWString, WinDef.DWORD paramDWORD);
/*    */   
/*    */   boolean FileEncryptionStatus(WString paramWString, WinDef.DWORDByReference paramDWORDByReference);
/*    */   
/*    */   boolean EncryptionDisable(WString paramWString, boolean paramBoolean);
/*    */   
/*    */   int OpenEncryptedFileRaw(WString paramWString, WinDef.ULONG paramULONG, PointerByReference paramPointerByReference);
/*    */   
/*    */   int ReadEncryptedFileRaw(WinBase.FE_EXPORT_FUNC paramFE_EXPORT_FUNC, Pointer paramPointer1, Pointer paramPointer2);
/*    */   
/*    */   int WriteEncryptedFileRaw(WinBase.FE_IMPORT_FUNC paramFE_IMPORT_FUNC, Pointer paramPointer1, Pointer paramPointer2);
/*    */   
/*    */   void CloseEncryptedFileRaw(Pointer paramPointer);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Advapi32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */