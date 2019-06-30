/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.platform.win32.Guid;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.ptr.PointerByReference;
/*    */ import java.util.Arrays;
/*    */ import java.util.List;
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
/*    */ public class UnknownListener
/*    */   extends Structure
/*    */ {
/*    */   public UnknownVTable.ByReference vtbl;
/*    */   
/*    */   public UnknownListener(IUnknownCallback callback) {
/* 29 */     this.vtbl = constructVTable();
/* 30 */     initVTable(callback);
/* 31 */     write();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 38 */   protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "vtbl" }); }
/*    */ 
/*    */ 
/*    */   
/* 42 */   protected UnknownVTable.ByReference constructVTable() { return new UnknownVTable.ByReference(); }
/*    */ 
/*    */   
/*    */   protected void initVTable(final IUnknownCallback callback) {
/* 46 */     this.vtbl.QueryInterfaceCallback = new UnknownVTable.QueryInterfaceCallback()
/*    */       {
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, Guid.GUID.ByValue refid, PointerByReference ppvObject) {
/* 49 */           return callback.QueryInterface(refid, ppvObject);
/*    */         }
/*    */       };
/* 52 */     this.vtbl.AddRefCallback = new UnknownVTable.AddRefCallback()
/*    */       {
/*    */         public int invoke(Pointer thisPointer) {
/* 55 */           return callback.AddRef();
/*    */         }
/*    */       };
/* 58 */     this.vtbl.ReleaseCallback = new UnknownVTable.ReleaseCallback()
/*    */       {
/*    */         public int invoke(Pointer thisPointer) {
/* 61 */           return callback.Release();
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\UnknownListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */