/*      */ package com.sun.jna.platform.win32;
/*      */ 
/*      */ import com.sun.jna.IntegerType;
/*      */ import com.sun.jna.Native;
/*      */ import com.sun.jna.Pointer;
/*      */ import com.sun.jna.PointerType;
/*      */ import com.sun.jna.Structure;
/*      */ import com.sun.jna.ptr.ByReference;
/*      */ import com.sun.jna.win32.StdCallLibrary;
/*      */ import java.awt.Rectangle;
/*      */ import java.util.Arrays;
/*      */ import java.util.List;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public interface WinDef
/*      */   extends StdCallLibrary
/*      */ {
/*      */   public static final int MAX_PATH = 260;
/*      */   
/*      */   public static class WORD
/*      */     extends IntegerType
/*      */     implements Comparable<WORD>
/*      */   {
/*      */     public static final int SIZE = 2;
/*      */     
/*   53 */     public WORD() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   63 */     public WORD(long value) { super(2, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*   68 */     public int compareTo(WORD other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WORDByReference
/*      */     extends ByReference
/*      */   {
/*   81 */     public WORDByReference() { this(new WinDef.WORD(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public WORDByReference(WinDef.WORD value) {
/*   90 */       super(2);
/*   91 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  100 */     public void setValue(WinDef.WORD value) { getPointer().setShort(0L, value.shortValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  109 */     public WinDef.WORD getValue() { return new WinDef.WORD(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DWORD
/*      */     extends IntegerType
/*      */     implements Comparable<DWORD>
/*      */   {
/*      */     public static final int SIZE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  125 */     public DWORD() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  135 */     public DWORD(long value) { super(4, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  144 */     public WinDef.WORD getLow() { return new WinDef.WORD(longValue() & 0xFFL); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  153 */     public WinDef.WORD getHigh() { return new WinDef.WORD(longValue() >> 16 & 0xFFL); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  158 */     public int compareTo(DWORD other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DWORDByReference
/*      */     extends ByReference
/*      */   {
/*  171 */     public DWORDByReference() { this(new WinDef.DWORD(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public DWORDByReference(WinDef.DWORD value) {
/*  180 */       super(4);
/*  181 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  190 */     public void setValue(WinDef.DWORD value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  199 */     public WinDef.DWORD getValue() { return new WinDef.DWORD(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LONG
/*      */     extends IntegerType
/*      */     implements Comparable<LONG>
/*      */   {
/*  209 */     public static final int SIZE = Native.LONG_SIZE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  215 */     public LONG() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  224 */     public LONG(long value) { super(SIZE, value); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  229 */     public int compareTo(LONG other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LONGByReference
/*      */     extends ByReference
/*      */   {
/*  242 */     public LONGByReference() { this(new WinDef.LONG(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public LONGByReference(WinDef.LONG value) {
/*  251 */       super(WinDef.LONG.SIZE);
/*  252 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  261 */     public void setValue(WinDef.LONG value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  270 */     public WinDef.LONG getValue() { return new WinDef.LONG(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LONGLONG
/*      */     extends IntegerType
/*      */     implements Comparable<LONGLONG>
/*      */   {
/*  280 */     public static final int SIZE = Native.LONG_SIZE * 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  286 */     public LONGLONG() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  295 */     public LONGLONG(long value) { super(8, value, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  300 */     public int compareTo(LONGLONG other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LONGLONGByReference
/*      */     extends ByReference
/*      */   {
/*  313 */     public LONGLONGByReference() { this(new WinDef.LONGLONG(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public LONGLONGByReference(WinDef.LONGLONG value) {
/*  322 */       super(WinDef.LONGLONG.SIZE);
/*  323 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  332 */     public void setValue(WinDef.LONGLONG value) { getPointer().setLong(0L, value.longValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  341 */     public WinDef.LONGLONG getValue() { return new WinDef.LONGLONG(getPointer().getLong(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HDC
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HDC() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  364 */     public HDC(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HICON
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HICON() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  389 */     public HICON(WinNT.HANDLE handle) { this(handle.getPointer()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  399 */     public HICON(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HCURSOR
/*      */     extends HICON
/*      */   {
/*      */     public HCURSOR() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  422 */     public HCURSOR(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HMENU
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HMENU() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  445 */     public HMENU(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HPEN
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HPEN() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  468 */     public HPEN(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HRSRC
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HRSRC() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  491 */     public HRSRC(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HPALETTE
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HPALETTE() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  514 */     public HPALETTE(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HBITMAP
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HBITMAP() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  537 */     public HBITMAP(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HRGN
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HRGN() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  560 */     public HRGN(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HWND
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HWND() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  583 */     public HWND(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HINSTANCE
/*      */     extends WinNT.HANDLE {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HMODULE
/*      */     extends HINSTANCE {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HFONT
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HFONT() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  620 */     public HFONT(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LPARAM
/*      */     extends BaseTSD.LONG_PTR
/*      */   {
/*  633 */     public LPARAM() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  643 */     public LPARAM(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LRESULT
/*      */     extends BaseTSD.LONG_PTR
/*      */   {
/*  656 */     public LRESULT() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  666 */     public LRESULT(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class INT_PTR
/*      */     extends IntegerType
/*      */   {
/*  677 */     public INT_PTR() { super(Pointer.SIZE); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  687 */     public INT_PTR(long value) { super(Pointer.SIZE, value); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  696 */     public Pointer toPointer() { return Pointer.createConstant(longValue()); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UINT_PTR
/*      */     extends IntegerType
/*      */   {
/*  709 */     public UINT_PTR() { super(Pointer.SIZE); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  719 */     public UINT_PTR(long value) { super(Pointer.SIZE, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  728 */     public Pointer toPointer() { return Pointer.createConstant(longValue()); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class WPARAM
/*      */     extends UINT_PTR
/*      */   {
/*  741 */     public WPARAM() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  751 */     public WPARAM(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class RECT
/*      */     extends Structure
/*      */   {
/*      */     public int left;
/*      */ 
/*      */ 
/*      */     
/*      */     public int top;
/*      */ 
/*      */     
/*      */     public int right;
/*      */ 
/*      */     
/*      */     public int bottom;
/*      */ 
/*      */ 
/*      */     
/*  774 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "left", "top", "right", "bottom" }); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  783 */     public Rectangle toRectangle() { return new Rectangle(this.left, this.top, this.right - this.left, this.bottom - this.top); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  788 */     public String toString() { return "[(" + this.left + "," + this.top + ")(" + this.right + "," + this.bottom + ")]"; }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ULONG
/*      */     extends IntegerType
/*      */     implements Comparable<ULONG>
/*      */   {
/*  798 */     public static final int SIZE = Native.LONG_SIZE;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  804 */     public ULONG() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  814 */     public ULONG(long value) { super(SIZE, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  819 */     public int compareTo(ULONG other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ULONGByReference
/*      */     extends ByReference
/*      */   {
/*  832 */     public ULONGByReference() { this(new WinDef.ULONG(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ULONGByReference(WinDef.ULONG value) {
/*  841 */       super(WinDef.ULONG.SIZE);
/*  842 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  851 */     public void setValue(WinDef.ULONG value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  860 */     public WinDef.ULONG getValue() { return new WinDef.ULONG(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ULONGLONG
/*      */     extends IntegerType
/*      */     implements Comparable<ULONGLONG>
/*      */   {
/*  870 */     public static final int SIZE = Native.LONG_SIZE * 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  876 */     public ULONGLONG() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  885 */     public ULONGLONG(long value) { super(SIZE, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  890 */     public int compareTo(ULONGLONG other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ULONGLONGByReference
/*      */     extends ByReference
/*      */   {
/*  903 */     public ULONGLONGByReference() { this(new WinDef.ULONGLONG(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ULONGLONGByReference(WinDef.ULONGLONG value) {
/*  912 */       super(WinDef.ULONGLONG.SIZE);
/*  913 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  922 */     public void setValue(WinDef.ULONGLONG value) { getPointer().setLong(0L, value.longValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  931 */     public WinDef.ULONGLONG getValue() { return new WinDef.ULONGLONG(getPointer().getLong(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class DWORDLONG
/*      */     extends IntegerType
/*      */     implements Comparable<DWORDLONG>
/*      */   {
/*      */     public static final int SIZE = 8;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  947 */     public DWORDLONG() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  957 */     public DWORDLONG(long value) { super(8, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  962 */     public int compareTo(DWORDLONG other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HBRUSH
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HBRUSH() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  985 */     public HBRUSH(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class ATOM
/*      */     extends WORD
/*      */   {
/*  998 */     public ATOM() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1008 */     public ATOM(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class PVOID
/*      */     extends PointerType
/*      */   {
/*      */     public PVOID() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1027 */     public PVOID(Pointer pointer) { super(pointer); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LPVOID
/*      */     extends PointerType
/*      */   {
/*      */     public LPVOID() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1048 */     public LPVOID(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class POINT
/*      */     extends Structure
/*      */   {
/*      */     public int x;
/*      */ 
/*      */ 
/*      */     
/*      */     public int y;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static class ByReference
/*      */       extends POINT
/*      */       implements Structure.ByReference {}
/*      */ 
/*      */ 
/*      */     
/*      */     public POINT() {}
/*      */ 
/*      */ 
/*      */     
/*      */     public POINT(Pointer memory) {
/* 1077 */       super(memory);
/* 1078 */       read();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public POINT(int x, int y) {
/* 1093 */       this.x = x;
/* 1094 */       this.y = y;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/* 1099 */     protected List<String> getFieldOrder() { return Arrays.asList(new String[] { "x", "y" }); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class USHORT
/*      */     extends IntegerType
/*      */     implements Comparable<USHORT>
/*      */   {
/*      */     public static final int SIZE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1115 */     public USHORT() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1125 */     public USHORT(long value) { super(2, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1130 */     public int compareTo(USHORT other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class USHORTByReference
/*      */     extends ByReference
/*      */   {
/* 1143 */     public USHORTByReference() { this(new WinDef.USHORT(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public USHORTByReference(WinDef.USHORT value) {
/* 1152 */       super(2);
/* 1153 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public USHORTByReference(short value) {
/* 1162 */       super(2);
/* 1163 */       setValue(new WinDef.USHORT(value));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1172 */     public void setValue(WinDef.USHORT value) { getPointer().setShort(0L, value.shortValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1181 */     public WinDef.USHORT getValue() { return new WinDef.USHORT(getPointer().getShort(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SHORT
/*      */     extends IntegerType
/*      */     implements Comparable<SHORT>
/*      */   {
/*      */     public static final int SIZE = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1197 */     public SHORT() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1207 */     public SHORT(long value) { super(2, value, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1212 */     public int compareTo(SHORT other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UINT
/*      */     extends IntegerType
/*      */     implements Comparable<UINT>
/*      */   {
/*      */     public static final int SIZE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1228 */     public UINT() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1238 */     public UINT(long value) { super(4, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1243 */     public int compareTo(UINT other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UINTByReference
/*      */     extends ByReference
/*      */   {
/* 1256 */     public UINTByReference() { this(new WinDef.UINT(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public UINTByReference(WinDef.UINT value) {
/* 1265 */       super(4);
/* 1266 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1275 */     public void setValue(WinDef.UINT value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1284 */     public WinDef.UINT getValue() { return new WinDef.UINT(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SCODE
/*      */     extends ULONG
/*      */   {
/* 1297 */     public SCODE() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1307 */     public SCODE(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class SCODEByReference
/*      */     extends ByReference
/*      */   {
/* 1320 */     public SCODEByReference() { this(new WinDef.SCODE(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public SCODEByReference(WinDef.SCODE value) {
/* 1329 */       super(WinDef.SCODE.SIZE);
/* 1330 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1339 */     public void setValue(WinDef.SCODE value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1348 */     public WinDef.SCODE getValue() { return new WinDef.SCODE(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class LCID
/*      */     extends DWORD
/*      */   {
/* 1361 */     public LCID() { super(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1370 */     public LCID(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BOOL
/*      */     extends IntegerType
/*      */     implements Comparable<BOOL>
/*      */   {
/*      */     public static final int SIZE = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1386 */     public BOOL() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1395 */     public BOOL(boolean value) { this(value ? 1L : 0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1404 */     public BOOL(long value) { super(4, value, false); }
/*      */ 
/*      */     
/*      */     public boolean booleanValue() {
/* 1408 */       if (intValue() > 0) {
/* 1409 */         return true;
/*      */       }
/* 1411 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1417 */     public String toString() { return Boolean.toString(booleanValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1422 */     public int compareTo(BOOL other) { return compare(this, other); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static int compare(BOOL v1, BOOL v2) {
/* 1439 */       if (v1 == v2)
/* 1440 */         return 0; 
/* 1441 */       if (v1 == null)
/* 1442 */         return 1; 
/* 1443 */       if (v2 == null) {
/* 1444 */         return -1;
/*      */       }
/* 1446 */       return compare(v1.booleanValue(), v2.booleanValue());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public static int compare(BOOL v1, boolean v2) {
/* 1462 */       if (v1 == null) {
/* 1463 */         return 1;
/*      */       }
/* 1465 */       return compare(v1.booleanValue(), v2);
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public static int compare(boolean v1, boolean v2) {
/* 1471 */       if (v1 == v2)
/* 1472 */         return 0; 
/* 1473 */       if (v1) {
/* 1474 */         return 1;
/*      */       }
/* 1476 */       return -1;
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BOOLByReference
/*      */     extends ByReference
/*      */   {
/* 1490 */     public BOOLByReference() { this(new WinDef.BOOL(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public BOOLByReference(WinDef.BOOL value) {
/* 1499 */       super(4);
/* 1500 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1509 */     public void setValue(WinDef.BOOL value) { getPointer().setInt(0L, value.intValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1518 */     public WinDef.BOOL getValue() { return new WinDef.BOOL(getPointer().getInt(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class UCHAR
/*      */     extends IntegerType
/*      */     implements Comparable<UCHAR>
/*      */   {
/*      */     public static final int SIZE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1534 */     public UCHAR() { this(0L); }
/*      */ 
/*      */ 
/*      */     
/* 1538 */     public UCHAR(char ch) { this((ch & 0xFF)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1547 */     public UCHAR(long value) { super(1, value, true); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1552 */     public int compareTo(UCHAR other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class BYTE
/*      */     extends UCHAR
/*      */   {
/* 1565 */     public BYTE() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1574 */     public BYTE(long value) { super(value); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class CHAR
/*      */     extends IntegerType
/*      */     implements Comparable<CHAR>
/*      */   {
/*      */     public static final int SIZE = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1590 */     public CHAR() { this(0L); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1599 */     public CHAR(char ch) { this((ch & 0xFF)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1608 */     public CHAR(long value) { super(1, value, false); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1613 */     public int compareTo(CHAR other) { return compare(this, other); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class CHARByReference
/*      */     extends ByReference
/*      */   {
/* 1626 */     public CHARByReference() { this(new WinDef.CHAR(0L)); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CHARByReference(WinDef.CHAR value) {
/* 1635 */       super(1);
/* 1636 */       setValue(value);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1645 */     public void setValue(WinDef.CHAR value) { getPointer().setByte(0L, value.byteValue()); }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1654 */     public WinDef.CHAR getValue() { return new WinDef.CHAR(getPointer().getChar(0L)); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HGLRC
/*      */     extends WinNT.HANDLE
/*      */   {
/*      */     public HGLRC() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1677 */     public HGLRC(Pointer p) { super(p); }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static class HGLRCByReference
/*      */     extends WinNT.HANDLEByReference
/*      */   {
/*      */     public HGLRCByReference() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1700 */     public HGLRCByReference(WinDef.HGLRC h) { super(h); }
/*      */   }
/*      */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinDef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */