/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Native;
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.ptr.IntByReference;
/*    */ import com.sun.jna.ptr.PointerByReference;
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
/*    */ public interface Kernel32
/*    */   extends WinNT, Wincon
/*    */ {
/* 30 */   public static final Kernel32 INSTANCE = (Kernel32)Native.loadLibrary("kernel32", Kernel32.class, W32APIOptions.UNICODE_OPTIONS);
/*    */   public static final int MAX_PIPE_NAME_LENGTH = 256;
/*    */   
/*    */   boolean ReadFile(WinNT.HANDLE paramHANDLE, byte[] paramArrayOfByte, int paramInt, IntByReference paramIntByReference, WinBase.OVERLAPPED paramOVERLAPPED);
/*    */   
/*    */   Pointer LocalFree(Pointer paramPointer);
/*    */   
/*    */   Pointer GlobalFree(Pointer paramPointer);
/*    */   
/*    */   WinDef.HMODULE GetModuleHandle(String paramString);
/*    */   
/*    */   void GetSystemTime(WinBase.SYSTEMTIME paramSYSTEMTIME);
/*    */   
/*    */   boolean SetSystemTime(WinBase.SYSTEMTIME paramSYSTEMTIME);
/*    */   
/*    */   void GetLocalTime(WinBase.SYSTEMTIME paramSYSTEMTIME);
/*    */   
/*    */   boolean SetLocalTime(WinBase.SYSTEMTIME paramSYSTEMTIME);
/*    */   
/*    */   int GetTickCount();
/*    */   
/*    */   int GetCurrentThreadId();
/*    */   
/*    */   WinNT.HANDLE GetCurrentThread();
/*    */   
/*    */   int GetCurrentProcessId();
/*    */   
/*    */   WinNT.HANDLE GetCurrentProcess();
/*    */   
/*    */   int GetProcessId(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   int GetProcessVersion(int paramInt);
/*    */   
/*    */   boolean GetExitCodeProcess(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
/*    */   
/*    */   boolean TerminateProcess(WinNT.HANDLE paramHANDLE, int paramInt);
/*    */   
/*    */   int GetLastError();
/*    */   
/*    */   void SetLastError(int paramInt);
/*    */   
/*    */   int GetDriveType(String paramString);
/*    */   
/*    */   int FormatMessage(int paramInt1, Pointer paramPointer1, int paramInt2, int paramInt3, PointerByReference paramPointerByReference, int paramInt4, Pointer paramPointer2);
/*    */   
/*    */   WinNT.HANDLE CreateFile(String paramString, int paramInt1, int paramInt2, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, int paramInt3, int paramInt4, WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean CopyFile(String paramString1, String paramString2, boolean paramBoolean);
/*    */   
/*    */   boolean MoveFile(String paramString1, String paramString2);
/*    */   
/*    */   boolean MoveFileEx(String paramString1, String paramString2, WinDef.DWORD paramDWORD);
/*    */   
/*    */   boolean CreateDirectory(String paramString, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES);
/*    */   
/*    */   WinNT.HANDLE CreateIoCompletionPort(WinNT.HANDLE paramHANDLE1, WinNT.HANDLE paramHANDLE2, Pointer paramPointer, int paramInt);
/*    */   
/*    */   boolean GetQueuedCompletionStatus(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference, BaseTSD.ULONG_PTRByReference paramULONG_PTRByReference, PointerByReference paramPointerByReference, int paramInt);
/*    */   
/*    */   boolean PostQueuedCompletionStatus(WinNT.HANDLE paramHANDLE, int paramInt, Pointer paramPointer, WinBase.OVERLAPPED paramOVERLAPPED);
/*    */   
/*    */   int WaitForSingleObject(WinNT.HANDLE paramHANDLE, int paramInt);
/*    */   
/*    */   int WaitForMultipleObjects(int paramInt1, WinNT.HANDLE[] paramArrayOfHANDLE, boolean paramBoolean, int paramInt2);
/*    */   
/*    */   boolean DuplicateHandle(WinNT.HANDLE paramHANDLE1, WinNT.HANDLE paramHANDLE2, WinNT.HANDLE paramHANDLE3, WinNT.HANDLEByReference paramHANDLEByReference, int paramInt1, boolean paramBoolean, int paramInt2);
/*    */   
/*    */   boolean CloseHandle(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean ReadDirectoryChangesW(WinNT.HANDLE paramHANDLE, WinNT.FILE_NOTIFY_INFORMATION paramFILE_NOTIFY_INFORMATION, int paramInt1, boolean paramBoolean, int paramInt2, IntByReference paramIntByReference, WinBase.OVERLAPPED paramOVERLAPPED, WinNT.OVERLAPPED_COMPLETION_ROUTINE paramOVERLAPPED_COMPLETION_ROUTINE);
/*    */   
/*    */   int GetShortPathName(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   Pointer LocalAlloc(int paramInt1, int paramInt2);
/*    */   
/*    */   boolean WriteFile(WinNT.HANDLE paramHANDLE, byte[] paramArrayOfByte, int paramInt, IntByReference paramIntByReference, WinBase.OVERLAPPED paramOVERLAPPED);
/*    */   
/*    */   boolean FlushFileBuffers(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   WinNT.HANDLE CreateEvent(WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, boolean paramBoolean1, boolean paramBoolean2, String paramString);
/*    */   
/*    */   boolean SetEvent(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean ResetEvent(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean PulseEvent(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   WinNT.HANDLE CreateFileMapping(WinNT.HANDLE paramHANDLE, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, int paramInt1, int paramInt2, int paramInt3, String paramString);
/*    */   
/*    */   Pointer MapViewOfFile(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
/*    */   
/*    */   boolean UnmapViewOfFile(Pointer paramPointer);
/*    */   
/*    */   boolean GetComputerName(char[] paramArrayOfChar, IntByReference paramIntByReference);
/*    */   
/*    */   boolean GetComputerNameEx(int paramInt, char[] paramArrayOfChar, IntByReference paramIntByReference);
/*    */   
/*    */   WinNT.HANDLE OpenThread(int paramInt1, boolean paramBoolean, int paramInt2);
/*    */   
/*    */   boolean CreateProcess(String paramString1, String paramString2, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES1, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES2, boolean paramBoolean, WinDef.DWORD paramDWORD, Pointer paramPointer, String paramString3, WinBase.STARTUPINFO paramSTARTUPINFO, WinBase.PROCESS_INFORMATION paramPROCESS_INFORMATION);
/*    */   
/*    */   boolean CreateProcessW(String paramString1, char[] paramArrayOfChar, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES1, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES2, boolean paramBoolean, WinDef.DWORD paramDWORD, Pointer paramPointer, String paramString2, WinBase.STARTUPINFO paramSTARTUPINFO, WinBase.PROCESS_INFORMATION paramPROCESS_INFORMATION);
/*    */   
/*    */   WinNT.HANDLE OpenProcess(int paramInt1, boolean paramBoolean, int paramInt2);
/*    */   
/*    */   WinDef.DWORD GetTempPath(WinDef.DWORD paramDWORD, char[] paramArrayOfChar);
/*    */   
/*    */   WinDef.DWORD GetVersion();
/*    */   
/*    */   boolean GetVersionEx(WinNT.OSVERSIONINFO paramOSVERSIONINFO);
/*    */   
/*    */   boolean GetVersionEx(WinNT.OSVERSIONINFOEX paramOSVERSIONINFOEX);
/*    */   
/*    */   void GetSystemInfo(WinBase.SYSTEM_INFO paramSYSTEM_INFO);
/*    */   
/*    */   void GetNativeSystemInfo(WinBase.SYSTEM_INFO paramSYSTEM_INFO);
/*    */   
/*    */   boolean IsWow64Process(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference);
/*    */   
/*    */   boolean GetLogicalProcessorInformation(Pointer paramPointer, WinDef.DWORDByReference paramDWORDByReference);
/*    */   
/*    */   boolean GlobalMemoryStatusEx(WinBase.MEMORYSTATUSEX paramMEMORYSTATUSEX);
/*    */   
/*    */   boolean GetFileTime(WinNT.HANDLE paramHANDLE, WinBase.FILETIME paramFILETIME1, WinBase.FILETIME paramFILETIME2, WinBase.FILETIME paramFILETIME3);
/*    */   
/*    */   int SetFileTime(WinNT.HANDLE paramHANDLE, WinBase.FILETIME paramFILETIME1, WinBase.FILETIME paramFILETIME2, WinBase.FILETIME paramFILETIME3);
/*    */   
/*    */   boolean SetFileAttributes(String paramString, WinDef.DWORD paramDWORD);
/*    */   
/*    */   WinDef.DWORD GetLogicalDriveStrings(WinDef.DWORD paramDWORD, char[] paramArrayOfChar);
/*    */   
/*    */   boolean GetDiskFreeSpace(String paramString, WinDef.DWORDByReference paramDWORDByReference1, WinDef.DWORDByReference paramDWORDByReference2, WinDef.DWORDByReference paramDWORDByReference3, WinDef.DWORDByReference paramDWORDByReference4);
/*    */   
/*    */   boolean GetDiskFreeSpaceEx(String paramString, WinNT.LARGE_INTEGER paramLARGE_INTEGER1, WinNT.LARGE_INTEGER paramLARGE_INTEGER2, WinNT.LARGE_INTEGER paramLARGE_INTEGER3);
/*    */   
/*    */   boolean DeleteFile(String paramString);
/*    */   
/*    */   boolean CreatePipe(WinNT.HANDLEByReference paramHANDLEByReference1, WinNT.HANDLEByReference paramHANDLEByReference2, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, int paramInt);
/*    */   
/*    */   boolean CallNamedPipe(String paramString, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, IntByReference paramIntByReference, int paramInt3);
/*    */   
/*    */   boolean ConnectNamedPipe(WinNT.HANDLE paramHANDLE, WinBase.OVERLAPPED paramOVERLAPPED);
/*    */   
/*    */   WinNT.HANDLE CreateNamedPipe(String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES);
/*    */   
/*    */   boolean DisconnectNamedPipe(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean GetNamedPipeClientComputerName(WinNT.HANDLE paramHANDLE, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean GetNamedPipeClientProcessId(WinNT.HANDLE paramHANDLE, WinDef.ULONGByReference paramULONGByReference);
/*    */   
/*    */   boolean GetNamedPipeClientSessionId(WinNT.HANDLE paramHANDLE, WinDef.ULONGByReference paramULONGByReference);
/*    */   
/*    */   boolean GetNamedPipeHandleState(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3, IntByReference paramIntByReference4, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean GetNamedPipeInfo(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3, IntByReference paramIntByReference4);
/*    */   
/*    */   boolean GetNamedPipeServerProcessId(WinNT.HANDLE paramHANDLE, WinDef.ULONGByReference paramULONGByReference);
/*    */   
/*    */   boolean GetNamedPipeServerSessionId(WinNT.HANDLE paramHANDLE, WinDef.ULONGByReference paramULONGByReference);
/*    */   
/*    */   boolean PeekNamedPipe(WinNT.HANDLE paramHANDLE, byte[] paramArrayOfByte, int paramInt, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   boolean SetNamedPipeHandleState(WinNT.HANDLE paramHANDLE, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3);
/*    */   
/*    */   boolean TransactNamedPipe(WinNT.HANDLE paramHANDLE, byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, IntByReference paramIntByReference, WinBase.OVERLAPPED paramOVERLAPPED);
/*    */   
/*    */   boolean WaitNamedPipe(String paramString, int paramInt);
/*    */   
/*    */   boolean SetHandleInformation(WinNT.HANDLE paramHANDLE, int paramInt1, int paramInt2);
/*    */   
/*    */   int GetFileAttributes(String paramString);
/*    */   
/*    */   int GetFileType(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean DeviceIoControl(WinNT.HANDLE paramHANDLE, int paramInt1, Pointer paramPointer1, int paramInt2, Pointer paramPointer2, int paramInt3, IntByReference paramIntByReference, Pointer paramPointer3);
/*    */   
/*    */   WinNT.HANDLE CreateToolhelp32Snapshot(WinDef.DWORD paramDWORD1, WinDef.DWORD paramDWORD2);
/*    */   
/*    */   boolean Process32First(WinNT.HANDLE paramHANDLE, Tlhelp32.PROCESSENTRY32 paramPROCESSENTRY32);
/*    */   
/*    */   boolean Process32Next(WinNT.HANDLE paramHANDLE, Tlhelp32.PROCESSENTRY32 paramPROCESSENTRY32);
/*    */   
/*    */   boolean SetEnvironmentVariable(String paramString1, String paramString2);
/*    */   
/*    */   int GetEnvironmentVariable(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   Pointer GetEnvironmentStrings();
/*    */   
/*    */   boolean FreeEnvironmentStrings(Pointer paramPointer);
/*    */   
/*    */   WinDef.LCID GetSystemDefaultLCID();
/*    */   
/*    */   WinDef.LCID GetUserDefaultLCID();
/*    */   
/*    */   int GetPrivateProfileInt(String paramString1, String paramString2, int paramInt, String paramString3);
/*    */   
/*    */   WinDef.DWORD GetPrivateProfileString(String paramString1, String paramString2, String paramString3, char[] paramArrayOfChar, WinDef.DWORD paramDWORD, String paramString4);
/*    */   
/*    */   boolean WritePrivateProfileString(String paramString1, String paramString2, String paramString3, String paramString4);
/*    */   
/*    */   WinDef.DWORD GetPrivateProfileSection(String paramString1, char[] paramArrayOfChar, WinDef.DWORD paramDWORD, String paramString2);
/*    */   
/*    */   WinDef.DWORD GetPrivateProfileSectionNames(char[] paramArrayOfChar, WinDef.DWORD paramDWORD, String paramString);
/*    */   
/*    */   boolean WritePrivateProfileSection(String paramString1, String paramString2, String paramString3);
/*    */   
/*    */   boolean FileTimeToLocalFileTime(WinBase.FILETIME paramFILETIME1, WinBase.FILETIME paramFILETIME2);
/*    */   
/*    */   boolean SystemTimeToTzSpecificLocalTime(WinBase.TIME_ZONE_INFORMATION paramTIME_ZONE_INFORMATION, WinBase.SYSTEMTIME paramSYSTEMTIME1, WinBase.SYSTEMTIME paramSYSTEMTIME2);
/*    */   
/*    */   boolean SystemTimeToFileTime(WinBase.SYSTEMTIME paramSYSTEMTIME, WinBase.FILETIME paramFILETIME);
/*    */   
/*    */   WinNT.HANDLE CreateRemoteThread(WinNT.HANDLE paramHANDLE, WinBase.SECURITY_ATTRIBUTES paramSECURITY_ATTRIBUTES, int paramInt, WinBase.FOREIGN_THREAD_START_ROUTINE paramFOREIGN_THREAD_START_ROUTINE, Pointer paramPointer1, WinDef.DWORD paramDWORD, Pointer paramPointer2);
/*    */   
/*    */   boolean WriteProcessMemory(WinNT.HANDLE paramHANDLE, Pointer paramPointer1, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
/*    */   
/*    */   boolean ReadProcessMemory(WinNT.HANDLE paramHANDLE, Pointer paramPointer1, Pointer paramPointer2, int paramInt, IntByReference paramIntByReference);
/*    */   
/*    */   BaseTSD.SIZE_T VirtualQueryEx(WinNT.HANDLE paramHANDLE, Pointer paramPointer, WinNT.MEMORY_BASIC_INFORMATION paramMEMORY_BASIC_INFORMATION, BaseTSD.SIZE_T paramSIZE_T);
/*    */   
/*    */   boolean DefineDosDevice(int paramInt, String paramString1, String paramString2);
/*    */   
/*    */   int QueryDosDevice(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   WinNT.HANDLE FindFirstVolumeMountPoint(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean FindNextVolumeMountPoint(WinNT.HANDLE paramHANDLE, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean FindVolumeMountPointClose(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean GetVolumeNameForVolumeMountPoint(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean SetVolumeLabel(String paramString1, String paramString2);
/*    */   
/*    */   boolean SetVolumeMountPoint(String paramString1, String paramString2);
/*    */   
/*    */   boolean DeleteVolumeMountPoint(String paramString);
/*    */   
/*    */   boolean GetVolumeInformation(String paramString, char[] paramArrayOfChar1, int paramInt1, IntByReference paramIntByReference1, IntByReference paramIntByReference2, IntByReference paramIntByReference3, char[] paramArrayOfChar2, int paramInt2);
/*    */   
/*    */   boolean GetVolumePathName(String paramString, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean GetVolumePathNamesForVolumeName(String paramString, char[] paramArrayOfChar, int paramInt, IntByReference paramIntByReference);
/*    */   
/*    */   WinNT.HANDLE FindFirstVolume(char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean FindNextVolume(WinNT.HANDLE paramHANDLE, char[] paramArrayOfChar, int paramInt);
/*    */   
/*    */   boolean FindVolumeClose(WinNT.HANDLE paramHANDLE);
/*    */   
/*    */   boolean GetCommState(WinNT.HANDLE paramHANDLE, WinBase.DCB paramDCB);
/*    */   
/*    */   boolean GetCommTimeouts(WinNT.HANDLE paramHANDLE, WinBase.COMMTIMEOUTS paramCOMMTIMEOUTS);
/*    */   
/*    */   boolean SetCommState(WinNT.HANDLE paramHANDLE, WinBase.DCB paramDCB);
/*    */   
/*    */   boolean SetCommTimeouts(WinNT.HANDLE paramHANDLE, WinBase.COMMTIMEOUTS paramCOMMTIMEOUTS);
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Kernel32.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */