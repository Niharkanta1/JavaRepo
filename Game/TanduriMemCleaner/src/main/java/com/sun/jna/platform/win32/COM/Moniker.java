/*     */ package com.sun.jna.platform.win32.COM;
/*     */ 
/*     */ import com.sun.jna.Pointer;
/*     */ import com.sun.jna.Structure;
/*     */ import com.sun.jna.platform.win32.Guid;
/*     */ import com.sun.jna.platform.win32.WTypes;
/*     */ import com.sun.jna.platform.win32.WinNT;
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
/*     */ public class Moniker
/*     */   extends Unknown
/*     */   implements IMoniker
/*     */ {
/*     */   static final int vTableIdStart = 7;
/*     */   
/*     */   public static class ByReference
/*     */     extends Moniker
/*     */     implements Structure.ByReference {}
/*     */   
/*     */   public Moniker() {}
/*     */   
/*  33 */   public Moniker(Pointer pointer) { super(pointer); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void BindToObject() {
/*  41 */     int vTableId = 8;
/*     */     
/*  43 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void BindToStorage() {
/*  48 */     int vTableId = 9;
/*     */     
/*  50 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void Reduce() {
/*  55 */     int vTableId = 10;
/*     */     
/*  57 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void ComposeWith() {
/*  62 */     int vTableId = 11;
/*     */     
/*  64 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void Enum() {
/*  69 */     int vTableId = 12;
/*     */     
/*  71 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void IsEqual() {
/*  76 */     int vTableId = 13;
/*     */     
/*  78 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void Hash() {
/*  83 */     int vTableId = 14;
/*     */     
/*  85 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void IsRunning() {
/*  90 */     int vTableId = 15;
/*     */     
/*  92 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void GetTimeOfLastChange() {
/*  97 */     int vTableId = 16;
/*     */     
/*  99 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void Inverse() {
/* 104 */     int vTableId = 17;
/*     */     
/* 106 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void CommonPrefixWith() {
/* 111 */     int vTableId = 18;
/*     */     
/* 113 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void RelativePathTo() {
/* 118 */     int vTableId = 19;
/*     */     
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public WinNT.HRESULT GetDisplayName(Pointer pbc, Pointer pmkToLeft, WTypes.BSTRByReference ppszDisplayName) {
/* 125 */     int vTableId = 20;
/*     */     
/* 127 */     return (WinNT.HRESULT)_invokeNativeObject(20, new Object[] { getPointer(), pbc, pmkToLeft, ppszDisplayName }, WinNT.HRESULT.class);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void ParseDisplayName() {
/* 135 */     int vTableId = 21;
/*     */     
/* 137 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */   
/*     */   public void IsSystemMoniker() {
/* 142 */     int vTableId = 22;
/*     */     
/* 144 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 150 */   public boolean IsDirty() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 155 */   public void Load(IStream stm) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 160 */   public void Save(IStream stm) { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 165 */   public void GetSizeMax() { throw new UnsupportedOperationException(); }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 170 */   public Guid.CLSID GetClassID() { throw new UnsupportedOperationException(); }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\COM\Moniker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */