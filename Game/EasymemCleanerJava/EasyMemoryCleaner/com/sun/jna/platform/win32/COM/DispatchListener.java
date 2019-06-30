/*    */ package com.sun.jna.platform.win32.COM;
/*    */ 
/*    */ import com.sun.jna.Pointer;
/*    */ import com.sun.jna.Structure;
/*    */ import com.sun.jna.WString;
/*    */ import com.sun.jna.platform.win32.Guid;
/*    */ import com.sun.jna.platform.win32.OaIdl;
/*    */ import com.sun.jna.platform.win32.OleAuto;
/*    */ import com.sun.jna.platform.win32.Variant;
/*    */ import com.sun.jna.platform.win32.WinDef;
/*    */ import com.sun.jna.platform.win32.WinNT;
/*    */ import com.sun.jna.ptr.IntByReference;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DispatchListener
/*    */   extends Structure
/*    */ {
/*    */   public DispatchVTable.ByReference vtbl;
/*    */   
/*    */   public DispatchListener(IDispatchCallback callback) {
/* 39 */     this.vtbl = constructVTable();
/* 40 */     initVTable(callback);
/* 41 */     write();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 47 */   protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "vtbl" }); }
/*    */ 
/*    */ 
/*    */   
/* 51 */   protected DispatchVTable.ByReference constructVTable() { return new DispatchVTable.ByReference(); }
/*    */ 
/*    */   
/*    */   protected void initVTable(final IDispatchCallback callback) {
/* 55 */     this.vtbl.QueryInterfaceCallback = new DispatchVTable.QueryInterfaceCallback()
/*    */       {
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, Guid.GUID.ByValue refid, PointerByReference ppvObject) {
/* 58 */           return callback.QueryInterface(refid, ppvObject);
/*    */         }
/*    */       };
/* 61 */     this.vtbl.AddRefCallback = new DispatchVTable.AddRefCallback()
/*    */       {
/*    */         public int invoke(Pointer thisPointer) {
/* 64 */           return callback.AddRef();
/*    */         }
/*    */       };
/* 67 */     this.vtbl.ReleaseCallback = new DispatchVTable.ReleaseCallback()
/*    */       {
/*    */         public int invoke(Pointer thisPointer) {
/* 70 */           return callback.Release();
/*    */         }
/*    */       };
/* 73 */     this.vtbl.GetTypeInfoCountCallback = new DispatchVTable.GetTypeInfoCountCallback()
/*    */       {
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, WinDef.UINTByReference pctinfo) {
/* 76 */           return callback.GetTypeInfoCount(pctinfo);
/*    */         }
/*    */       };
/* 79 */     this.vtbl.GetTypeInfoCallback = new DispatchVTable.GetTypeInfoCallback()
/*    */       {
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, WinDef.UINT iTInfo, WinDef.LCID lcid, PointerByReference ppTInfo) {
/* 82 */           return callback.GetTypeInfo(iTInfo, lcid, ppTInfo);
/*    */         }
/*    */       };
/* 85 */     this.vtbl.GetIDsOfNamesCallback = new DispatchVTable.GetIDsOfNamesCallback()
/*    */       {
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, Guid.GUID.ByValue riid, WString[] rgszNames, int cNames, WinDef.LCID lcid, OaIdl.DISPIDByReference rgDispId)
/*    */         {
/* 89 */           return callback.GetIDsOfNames(riid, rgszNames, cNames, lcid, rgDispId);
/*    */         }
/*    */       };
/* 92 */     this.vtbl.InvokeCallback = new DispatchVTable.InvokeCallback()
/*    */       {
/*    */ 
/*    */         
/*    */         public WinNT.HRESULT invoke(Pointer thisPointer, OaIdl.DISPID dispIdMember, Guid.GUID.ByValue riid, WinDef.LCID lcid, WinDef.WORD wFlags, OleAuto.DISPPARAMS.ByReference pDispParams, Variant.VARIANT.ByReference pVarResult, OaIdl.EXCEPINFO.ByReference pExcepInfo, IntByReference puArgErr)
/*    */         {
/* 98 */           return callback.Invoke(dispIdMember, riid, lcid, wFlags, pDispParams, pVarResult, pExcepInfo, puArgErr);
/*    */         }
/*    */       };
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\DispatchListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */