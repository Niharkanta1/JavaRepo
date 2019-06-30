/*     */ package com.sun.jna.platform.win32;
/*     */ 
/*     */ import com.sun.jna.Native;
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
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
/*     */ public interface DBT
/*     */   extends StdCallLibrary
/*     */ {
/*     */   public static final int DBT_NO_DISK_SPACE = 71;
/*     */   public static final int DBT_LOW_DISK_SPACE = 72;
/*     */   public static final int DBT_CONFIGMGPRIVATE = 32767;
/*     */   public static final int DBT_DEVICEARRIVAL = 32768;
/*     */   public static final int DBT_DEVICEQUERYREMOVE = 32769;
/*     */   public static final int DBT_DEVICEQUERYREMOVEFAILED = 32770;
/*     */   public static final int DBT_DEVICEREMOVEPENDING = 32771;
/*     */   public static final int DBT_DEVICEREMOVECOMPLETE = 32772;
/*     */   public static final int DBT_DEVNODES_CHANGED = 7;
/*     */   public static final int DBT_DEVICETYPESPECIFIC = 32773;
/*     */   public static final int DBT_CUSTOMEVENT = 32774;
/*  69 */   public static final Guid.GUID GUID_DEVINTERFACE_USB_DEVICE = new Guid.GUID("{A5DCBF10-6530-11D2-901F-00C04FB951ED}");
/*     */ 
/*     */ 
/*     */   
/*  73 */   public static final Guid.GUID GUID_DEVINTERFACE_HID = new Guid.GUID("{4D1E55B2-F16F-11CF-88CB-001111000030}");
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final Guid.GUID GUID_DEVINTERFACE_VOLUME = new Guid.GUID("{53F5630D-B6BF-11D0-94F2-00A0C91EFB8B}");
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final Guid.GUID GUID_DEVINTERFACE_KEYBOARD = new Guid.GUID("{884b96c3-56ef-11d1-bc8c-00a0c91405dd}"); public static final int DBT_DEVTYP_OEM = 0; public static final int DBT_DEVTYP_DEVNODE = 1; public static final int DBT_DEVTYP_VOLUME = 2;
/*     */   public static final int DBT_DEVTYP_PORT = 3;
/*     */   public static final int DBT_DEVTYP_NET = 4;
/*     */   public static final int DBT_DEVTYP_DEVICEINTERFACE = 5;
/*  85 */   public static final Guid.GUID GUID_DEVINTERFACE_MOUSE = new Guid.GUID("{378DE44C-56EF-11D1-BC8C-00A0C91405DD}");
/*     */   
/*     */   public static final int DBT_DEVTYP_HANDLE = 6;
/*     */   public static final int DBTF_MEDIA = 1;
/*     */   public static final int DBTF_NET = 2;
/*     */   
/*     */   public static class DEV_BROADCAST_HDR
/*     */     extends Structure
/*     */   {
/*  94 */     public int dbch_size = size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbch_devicetype;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbch_reserved;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_HDR() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 115 */     public DEV_BROADCAST_HDR(long pointer) { this(new Pointer(pointer)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_HDR(Pointer memory) {
/* 125 */       super(memory);
/* 126 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 135 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbch_size", "dbch_devicetype", "dbch_reserved" }); }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_OEM
/*     */     extends Structure
/*     */   {
/* 167 */     public int dbco_size = size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbco_devicetype;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbco_reserved;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbco_identifier;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbco_suppfunc;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_OEM() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_OEM(Pointer memory) {
/* 195 */       super(memory);
/* 196 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 205 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbco_size", "dbco_devicetype", "dbco_reserved", "dbco_identifier", "dbco_suppfunc" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_DEVNODE
/*     */     extends Structure
/*     */   {
/* 216 */     public int dbcd_size = size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcd_devicetype;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcd_reserved;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcd_devnode;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_DEVNODE() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_DEVNODE(Pointer memory) {
/* 241 */       super(memory);
/* 242 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 251 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbcd_size", "dbcd_devicetype", "dbcd_reserved", "dbcd_devnode" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_VOLUME
/*     */     extends Structure
/*     */   {
/* 262 */     public int dbcv_size = size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcv_devicetype;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcv_reserved;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcv_unitmask;
/*     */ 
/*     */ 
/*     */     
/*     */     public short dbcv_flags;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_VOLUME() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_VOLUME(Pointer memory) {
/* 290 */       super(memory);
/* 291 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 300 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbcv_size", "dbcv_devicetype", "dbcv_reserved", "dbcv_unitmask", "dbcv_flags" }); }
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
/*     */   public static class DEV_BROADCAST_PORT
/*     */     extends Structure
/*     */   {
/* 317 */     public int dbcp_size = size();
/*     */ 
/*     */     
/*     */     public int dbcp_devicetype;
/*     */ 
/*     */     
/*     */     public int dbcp_reserved;
/*     */ 
/*     */     
/* 326 */     public char[] dbcp_name = new char[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_PORT() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_PORT(Pointer memory) {
/* 342 */       super(memory);
/* 343 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 352 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbcp_size", "dbcp_devicetype", "dbcp_reserved", "dbcp_name" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_NET
/*     */     extends Structure
/*     */   {
/* 363 */     public int dbcn_size = size();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcn_devicetype;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcn_reserved;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcn_resource;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcn_flags;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_NET() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_NET(Pointer memory) {
/* 391 */       super(memory);
/* 392 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 401 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbcn_size", "dbcn_devicetype", "dbcn_reserved", "dbcn_resource", "dbcn_flags" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_DEVICEINTERFACE
/*     */     extends Structure
/*     */   {
/*     */     public int dbcc_size;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcc_devicetype;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbcc_reserved;
/*     */ 
/*     */     
/*     */     public Guid.GUID dbcc_classguid;
/*     */ 
/*     */     
/* 424 */     public char[] dbcc_name = new char[1];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_DEVICEINTERFACE() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 440 */     public DEV_BROADCAST_DEVICEINTERFACE(long pointer) { this(new Pointer(pointer)); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_DEVICEINTERFACE(Pointer memory) {
/* 450 */       super(memory);
/* 451 */       this.dbcc_size = ((Integer)readField("dbcc_size")).intValue();
/*     */       
/* 453 */       int len = 1 + this.dbcc_size - size();
/* 454 */       this.dbcc_name = new char[len];
/* 455 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 464 */     public String getDbcc_name() { return Native.toString(this.dbcc_name); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 473 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbcc_size", "dbcc_devicetype", "dbcc_reserved", "dbcc_classguid", "dbcc_name" }); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static class DEV_BROADCAST_HANDLE
/*     */     extends Structure
/*     */   {
/* 484 */     public int dbch_size = size();
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbch_devicetype;
/*     */ 
/*     */ 
/*     */     
/*     */     public int dbch_reserved;
/*     */ 
/*     */ 
/*     */     
/*     */     public WinNT.HANDLE dbch_handle;
/*     */ 
/*     */ 
/*     */     
/*     */     public WinUser.HDEVNOTIFY dbch_hdevnotify;
/*     */ 
/*     */ 
/*     */     
/*     */     public Guid.GUID dbch_eventguid;
/*     */ 
/*     */ 
/*     */     
/*     */     public WinDef.LONG dbch_nameoffset;
/*     */ 
/*     */ 
/*     */     
/*     */     public byte[] dbch_data;
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_HANDLE() {}
/*     */ 
/*     */ 
/*     */     
/*     */     public DEV_BROADCAST_HANDLE(Pointer memory) {
/* 521 */       super(memory);
/* 522 */       read();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 531 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dbch_size", "dbch_devicetype", "dbch_reserved", "dbch_handle", "dbch_hdevnotify", "dbch_eventguid", "dbch_nameoffset", "dbch_data" }); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\DBT.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */