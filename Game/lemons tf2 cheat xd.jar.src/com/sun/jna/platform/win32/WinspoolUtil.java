/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ public abstract class WinspoolUtil
/*    */ {
/*    */   public static Winspool.PRINTER_INFO_1[] getPrinterInfo1() {
/* 29 */     pcbNeeded = new IntByReference();
/* 30 */     IntByReference pcReturned = new IntByReference();
/* 31 */     Winspool.INSTANCE.EnumPrinters(2, null, 1, null, 0, pcbNeeded, pcReturned);
/*    */     
/* 33 */     if (pcbNeeded.getValue() <= 0) {
/* 34 */       return new Winspool.PRINTER_INFO_1[0];
/*    */     }
/*    */     
/* 37 */     Winspool.PRINTER_INFO_1 pPrinterEnum = new Winspool.PRINTER_INFO_1(pcbNeeded.getValue());
/* 38 */     if (!Winspool.INSTANCE.EnumPrinters(2, null, 1, pPrinterEnum
/* 39 */         .getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned))
/*    */     {
/* 41 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 44 */     pPrinterEnum.read();
/*    */     
/* 46 */     return (PRINTER_INFO_1[])pPrinterEnum.toArray(pcReturned.getValue());
/*    */   }
/*    */   
/*    */   public static Winspool.PRINTER_INFO_4[] getPrinterInfo4() {
/* 50 */     pcbNeeded = new IntByReference();
/* 51 */     IntByReference pcReturned = new IntByReference();
/* 52 */     Winspool.INSTANCE.EnumPrinters(2, null, 4, null, 0, pcbNeeded, pcReturned);
/*    */     
/* 54 */     if (pcbNeeded.getValue() <= 0) {
/* 55 */       return new Winspool.PRINTER_INFO_4[0];
/*    */     }
/*    */     
/* 58 */     Winspool.PRINTER_INFO_4 pPrinterEnum = new Winspool.PRINTER_INFO_4(pcbNeeded.getValue());
/* 59 */     if (!Winspool.INSTANCE.EnumPrinters(2, null, 4, pPrinterEnum
/* 60 */         .getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned))
/*    */     {
/* 62 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 65 */     pPrinterEnum.read();
/*    */     
/* 67 */     return (PRINTER_INFO_4[])pPrinterEnum.toArray(pcReturned.getValue());
/*    */   }
/*    */   
/*    */   public static Winspool.JOB_INFO_1[] getJobInfo1(WinNT.HANDLEByReference phPrinter) {
/* 71 */     IntByReference pcbNeeded = new IntByReference();
/* 72 */     IntByReference pcReturned = new IntByReference();
/* 73 */     Winspool.INSTANCE.EnumJobs(phPrinter.getValue(), 0, 255, 1, null, 0, pcbNeeded, pcReturned);
/*    */     
/* 75 */     if (pcbNeeded.getValue() <= 0) {
/* 76 */       return new Winspool.JOB_INFO_1[0];
/*    */     }
/*    */     
/* 79 */     Winspool.JOB_INFO_1 pJobEnum = new Winspool.JOB_INFO_1(pcbNeeded.getValue());
/* 80 */     if (!Winspool.INSTANCE.EnumJobs(phPrinter.getValue(), 0, 255, 1, pJobEnum
/* 81 */         .getPointer(), pcbNeeded.getValue(), pcbNeeded, pcReturned))
/*    */     {
/* 83 */       throw new Win32Exception(Kernel32.INSTANCE.GetLastError());
/*    */     }
/*    */     
/* 86 */     pJobEnum.read();
/*    */     
/* 88 */     return (JOB_INFO_1[])pJobEnum.toArray(pcReturned.getValue());
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinspoolUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */