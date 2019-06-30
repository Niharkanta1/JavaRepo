/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.ptr.IntByReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class Rasapi32Util
/*     */ {
/*     */   private static final int RASP_PppIp = 32801;
/*  36 */   private static Object phoneBookMutex = new Object();
/*     */   
/*  38 */   public static final Map CONNECTION_STATE_TEXT = new HashMap();
/*     */   
/*     */   static  {
/*  41 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(0), "Opening the port...");
/*  42 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(1), "Port has been opened successfully");
/*  43 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(2), "Connecting to the device...");
/*  44 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(3), "The device has connected successfully.");
/*  45 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4), "All devices in the device chain have successfully connected.");
/*  46 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(5), "Verifying the user name and password...");
/*  47 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(6), "An authentication event has occurred.");
/*  48 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(7), "Requested another validation attempt with a new user.");
/*  49 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(8), "Server has requested a callback number.");
/*  50 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(9), "The client has requested to change the password");
/*  51 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(10), "Registering your computer on the network...");
/*  52 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(11), "The link-speed calculation phase is starting...");
/*  53 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(12), "An authentication request is being acknowledged.");
/*  54 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(13), "Reauthentication (after callback) is starting.");
/*  55 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(14), "The client has successfully completed authentication.");
/*  56 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(15), "The line is about to disconnect for callback.");
/*  57 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(16), "Delaying to give the modem time to reset for callback.");
/*  58 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(17), "Waiting for an incoming call from server.");
/*  59 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(18), "Projection result information is available.");
/*  60 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(19), "User authentication is being initiated or retried.");
/*  61 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(20), "Client has been called back and is about to resume authentication.");
/*  62 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(21), "Logging on to the network...");
/*  63 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(22), "Subentry has been connected");
/*  64 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(23), "Subentry has been disconnected");
/*  65 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4096), "Terminal state supported by RASPHONE.EXE.");
/*  66 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4097), "Retry authentication state supported by RASPHONE.EXE.");
/*  67 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4098), "Callback state supported by RASPHONE.EXE.");
/*  68 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4099), "Change password state supported by RASPHONE.EXE.");
/*  69 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(4100), "Displaying authentication UI");
/*  70 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(8192), "Connected to remote server successfully");
/*  71 */     CONNECTION_STATE_TEXT.put(Integer.valueOf(8193), "Disconnected");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class Ras32Exception
/*     */     extends RuntimeException
/*     */   {
/*     */     private static final long serialVersionUID = 1L;
/*     */ 
/*     */ 
/*     */     
/*     */     private final int code;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  89 */     public int getCode() { return this.code; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Ras32Exception(int code) {
/*  98 */       super(Rasapi32Util.getRasErrorString(code));
/*  99 */       this.code = code;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getRasErrorString(int code) {
/* 109 */     char[] msg = new char[1024];
/* 110 */     int err = Rasapi32.INSTANCE.RasGetErrorString(code, msg, msg.length);
/* 111 */     if (err != 0) return "Unknown error " + code; 
/* 112 */     int len = 0;
/* 113 */     for (; len < msg.length && msg[len] != '\000'; len++);
/* 114 */     return new String(msg, false, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String getRasConnectionStatusText(int connStatus) {
/* 123 */     if (!CONNECTION_STATE_TEXT.containsKey(Integer.valueOf(connStatus))) return Integer.toString(connStatus); 
/* 124 */     return (String)CONNECTION_STATE_TEXT.get(Integer.valueOf(connStatus));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WinNT.HANDLE getRasConnection(String connName) throws Ras32Exception {
/* 135 */     IntByReference lpcb = new IntByReference(false);
/* 136 */     IntByReference lpcConnections = new IntByReference();
/* 137 */     int err = Rasapi32.INSTANCE.RasEnumConnections(null, lpcb, lpcConnections);
/* 138 */     if (err != 0 && err != 603) throw new Ras32Exception(err); 
/* 139 */     if (lpcb.getValue() == 0) return null;
/*     */ 
/*     */     
/* 142 */     WinRas.RASCONN[] arrayOfRASCONN = new WinRas.RASCONN[lpcConnections.getValue()];
/* 143 */     for (i = 0; i < lpcConnections.getValue(); ) { arrayOfRASCONN[i] = new WinRas.RASCONN(); i++; }
/* 144 */      lpcb = new IntByReference((arrayOfRASCONN[0]).dwSize * lpcConnections.getValue());
/* 145 */     err = Rasapi32.INSTANCE.RasEnumConnections(arrayOfRASCONN, lpcb, lpcConnections);
/* 146 */     if (err != 0) throw new Ras32Exception(err);
/*     */ 
/*     */     
/* 149 */     for (int i = 0; i < lpcConnections.getValue(); i++) {
/* 150 */       if ((new String((arrayOfRASCONN[i]).szEntryName)).equals(connName)) return (arrayOfRASCONN[i]).hrasconn; 
/*     */     } 
/* 152 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hangupRasConnection(String connName) throws Ras32Exception {
/* 161 */     WinNT.HANDLE hrasConn = getRasConnection(connName);
/* 162 */     if (hrasConn == null)
/* 163 */       return;  int err = Rasapi32.INSTANCE.RasHangUp(hrasConn);
/* 164 */     if (err != 0) throw new Ras32Exception(err);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void hangupRasConnection(WinNT.HANDLE hrasConn) throws Ras32Exception {
/* 173 */     if (hrasConn == null)
/* 174 */       return;  int err = Rasapi32.INSTANCE.RasHangUp(hrasConn);
/* 175 */     if (err != 0) throw new Ras32Exception(err);
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WinRas.RASPPPIP getIPProjection(WinNT.HANDLE hrasConn) throws Ras32Exception {
/* 185 */     WinRas.RASPPPIP pppIpProjection = new WinRas.RASPPPIP();
/* 186 */     IntByReference lpcb = new IntByReference(pppIpProjection.size());
/* 187 */     pppIpProjection.write();
/* 188 */     int err = Rasapi32.INSTANCE.RasGetProjectionInfo(hrasConn, 32801, pppIpProjection.getPointer(), lpcb);
/* 189 */     if (err != 0) throw new Ras32Exception(err); 
/* 190 */     pppIpProjection.read();
/* 191 */     return pppIpProjection;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WinRas.RASENTRY.ByReference getPhoneBookEntry(String entryName) throws Ras32Exception {
/* 201 */     synchronized (phoneBookMutex) {
/* 202 */       WinRas.RASENTRY.ByReference rasEntry = new WinRas.RASENTRY.ByReference();
/* 203 */       IntByReference lpdwEntryInfoSize = new IntByReference(rasEntry.size());
/* 204 */       int err = Rasapi32.INSTANCE.RasGetEntryProperties(null, entryName, rasEntry, lpdwEntryInfoSize, null, null);
/* 205 */       if (err != 0) throw new Ras32Exception(err); 
/* 206 */       return rasEntry;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void setPhoneBookEntry(String entryName, WinRas.RASENTRY.ByReference rasEntry) throws Ras32Exception {
/* 217 */     synchronized (phoneBookMutex) {
/* 218 */       int err = Rasapi32.INSTANCE.RasSetEntryProperties(null, entryName, rasEntry, rasEntry.size(), null, 0);
/* 219 */       if (err != 0) throw new Ras32Exception(err);
/*     */     
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WinRas.RASDIALPARAMS getPhoneBookDialingParams(String entryName) throws Ras32Exception {
/* 230 */     synchronized (phoneBookMutex) {
/* 231 */       WinRas.RASDIALPARAMS.ByReference rasDialParams = new WinRas.RASDIALPARAMS.ByReference();
/* 232 */       System.arraycopy(rasDialParams.szEntryName, 0, entryName.toCharArray(), 0, entryName.length());
/* 233 */       WinDef.BOOLByReference lpfPassword = new WinDef.BOOLByReference();
/* 234 */       int err = Rasapi32.INSTANCE.RasGetEntryDialParams(null, rasDialParams, lpfPassword);
/* 235 */       if (err != 0) throw new Ras32Exception(err); 
/* 236 */       return rasDialParams;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static WinNT.HANDLE dialEntry(String entryName) throws Ras32Exception {
/* 248 */     WinRas.RASCREDENTIALS.ByReference credentials = new WinRas.RASCREDENTIALS.ByReference();
/* 249 */     synchronized (phoneBookMutex) {
/* 250 */       credentials.dwMask = 7;
/* 251 */       int err = Rasapi32.INSTANCE.RasGetCredentials(null, entryName, credentials);
/* 252 */       if (err != 0) throw new Ras32Exception(err);
/*     */     
/*     */     } 
/*     */     
/* 256 */     WinRas.RASDIALPARAMS.ByReference rasDialParams = new WinRas.RASDIALPARAMS.ByReference();
/* 257 */     System.arraycopy(entryName.toCharArray(), 0, rasDialParams.szEntryName, 0, entryName.length());
/* 258 */     System.arraycopy(credentials.szUserName, 0, rasDialParams.szUserName, 0, credentials.szUserName.length);
/* 259 */     System.arraycopy(credentials.szPassword, 0, rasDialParams.szPassword, 0, credentials.szPassword.length);
/* 260 */     System.arraycopy(credentials.szDomain, 0, rasDialParams.szDomain, 0, credentials.szDomain.length);
/*     */ 
/*     */     
/* 263 */     WinNT.HANDLEByReference hrasConn = new WinNT.HANDLEByReference();
/* 264 */     int err = Rasapi32.INSTANCE.RasDial(null, null, rasDialParams, 0, null, hrasConn);
/* 265 */     if (err != 0) {
/* 266 */       if (hrasConn.getValue() != null) Rasapi32.INSTANCE.RasHangUp(hrasConn.getValue()); 
/* 267 */       throw new Ras32Exception(err);
/*     */     } 
/* 269 */     return hrasConn.getValue();
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
/*     */   public static WinNT.HANDLE dialEntry(String entryName, WinRas.RasDialFunc2 func2) throws Ras32Exception {
/* 281 */     WinRas.RASCREDENTIALS.ByReference credentials = new WinRas.RASCREDENTIALS.ByReference();
/* 282 */     synchronized (phoneBookMutex) {
/* 283 */       credentials.dwMask = 7;
/* 284 */       int err = Rasapi32.INSTANCE.RasGetCredentials(null, entryName, credentials);
/* 285 */       if (err != 0) throw new Ras32Exception(err);
/*     */     
/*     */     } 
/*     */     
/* 289 */     WinRas.RASDIALPARAMS.ByReference rasDialParams = new WinRas.RASDIALPARAMS.ByReference();
/* 290 */     System.arraycopy(entryName.toCharArray(), 0, rasDialParams.szEntryName, 0, entryName.length());
/* 291 */     System.arraycopy(credentials.szUserName, 0, rasDialParams.szUserName, 0, credentials.szUserName.length);
/* 292 */     System.arraycopy(credentials.szPassword, 0, rasDialParams.szPassword, 0, credentials.szPassword.length);
/* 293 */     System.arraycopy(credentials.szDomain, 0, rasDialParams.szDomain, 0, credentials.szDomain.length);
/*     */ 
/*     */     
/* 296 */     WinNT.HANDLEByReference hrasConn = new WinNT.HANDLEByReference();
/* 297 */     int err = Rasapi32.INSTANCE.RasDial(null, null, rasDialParams, 2, func2, hrasConn);
/* 298 */     if (err != 0) {
/* 299 */       if (hrasConn.getValue() != null) Rasapi32.INSTANCE.RasHangUp(hrasConn.getValue()); 
/* 300 */       throw new Ras32Exception(err);
/*     */     } 
/* 302 */     return hrasConn.getValue();
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\Rasapi32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */