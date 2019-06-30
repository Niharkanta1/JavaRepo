/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.WString;
/*     */ import com.sun.jna.platform.win32.OaIdl;
/*     */ import com.sun.jna.platform.win32.WinDef;
/*     */ import com.sun.jna.platform.win32.WinNT;
/*     */ import com.sun.jna.ptr.PointerByReference;
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
/*     */ public class TypeComp
/*     */   extends Unknown
/*     */ {
/*     */   public static class ByReference
/*     */     extends TypeComp
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public TypeComp() {}
/*     */   
/*  50 */   public TypeComp(Pointer pvInstance) { super(pvInstance); }
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
/*     */   public WinNT.HRESULT Bind(WString szName, WinDef.ULONG lHashVal, WinDef.WORD wFlags, PointerByReference ppTInfo, OaIdl.DESCKIND.ByReference pDescKind, OaIdl.BINDPTR.ByReference pBindPtr) {
/*  79 */     return (WinNT.HRESULT)_invokeNativeObject(3, new Object[] {
/*  80 */           getPointer(), szName, lHashVal, wFlags, ppTInfo, pDescKind, pBindPtr }, WinNT.HRESULT.class);
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
/*     */   public WinNT.HRESULT BindType(WString szName, WinDef.ULONG lHashVal, PointerByReference ppTInfo, PointerByReference ppTComp) {
/* 104 */     return (WinNT.HRESULT)_invokeNativeObject(4, new Object[] {
/* 105 */           getPointer(), szName, lHashVal, ppTInfo, ppTComp }, WinNT.HRESULT.class);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\TypeComp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */