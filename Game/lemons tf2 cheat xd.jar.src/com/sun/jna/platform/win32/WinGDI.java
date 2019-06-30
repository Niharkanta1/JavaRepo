/*     */ package com.sun.jna.platform.win32;public interface WinGDI extends StdCallLibrary { public static final int RDH_RECTANGLES = 1; public static final int RGN_AND = 1; public static final int RGN_OR = 2; public static final int RGN_XOR = 3; public static final int RGN_DIFF = 4; public static final int RGN_COPY = 5;
/*     */   public static final int ERROR = 0;
/*     */   public static final int NULLREGION = 1;
/*     */   public static final int SIMPLEREGION = 2;
/*     */   public static final int COMPLEXREGION = 3;
/*     */   public static final int ALTERNATE = 1;
/*     */   public static final int WINDING = 2;
/*     */   public static final int BI_RGB = 0;
/*     */   public static final int BI_RLE8 = 1;
/*     */   public static final int BI_RLE4 = 2;
/*     */   public static final int BI_BITFIELDS = 3;
/*     */   public static final int BI_JPEG = 4;
/*     */   public static final int BI_PNG = 5;
/*     */   public static final int PFD_TYPE_RGBA = 0;
/*     */   public static final int PFD_TYPE_COLORINDEX = 1;
/*     */   public static final int PFD_MAIN_PLANE = 0;
/*     */   public static final int PFD_OVERLAY_PLANE = 1;
/*     */   public static final int PFD_UNDERLAY_PLANE = -1;
/*     */   public static final int PFD_DOUBLEBUFFER = 1;
/*     */   public static final int PFD_STEREO = 2;
/*     */   public static final int PFD_DRAW_TO_WINDOW = 4;
/*     */   public static final int PFD_DRAW_TO_BITMAP = 8;
/*     */   public static final int PFD_SUPPORT_GDI = 16;
/*     */   public static final int PFD_SUPPORT_OPENGL = 32;
/*     */   public static final int PFD_GENERIC_FORMAT = 64;
/*     */   public static final int PFD_NEED_PALETTE = 128;
/*     */   public static final int PFD_NEED_SYSTEM_PALETTE = 256;
/*     */   public static final int PFD_SWAP_EXCHANGE = 512;
/*     */   public static final int PFD_SWAP_COPY = 1024;
/*     */   public static final int PFD_SWAP_LAYER_BUFFERS = 2048;
/*     */   public static final int PFD_GENERIC_ACCELERATED = 4096;
/*     */   public static final int PFD_SUPPORT_DIRECTDRAW = 8192;
/*     */   public static final int DIB_RGB_COLORS = 0;
/*     */   public static final int DIB_PAL_COLORS = 1;
/*     */   
/*  36 */   public static class RGNDATAHEADER extends Structure { public int dwSize = size();
/*  37 */     public int iType = 1;
/*     */     
/*     */     public int nCount;
/*     */     public int nRgnSize;
/*     */     public WinDef.RECT rcBound;
/*     */     
/*  43 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dwSize", "iType", "nCount", "nRgnSize", "rcBound" }); } }
/*     */ 
/*     */   
/*     */   public static class RGNDATA
/*     */     extends Structure
/*     */   {
/*     */     public WinGDI.RGNDATAHEADER rdh;
/*     */     public byte[] Buffer;
/*     */     
/*  52 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "rdh", "Buffer" }); }
/*     */ 
/*     */ 
/*     */     
/*  56 */     public RGNDATA() { this(1); }
/*     */     
/*     */     public RGNDATA(int bufferSize) {
/*  59 */       this.Buffer = new byte[bufferSize];
/*  60 */       allocateMemory();
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
/*     */   public static class BITMAPINFOHEADER
/*     */     extends Structure
/*     */   {
/* 108 */     public int biSize = size();
/*     */     public int biWidth;
/*     */     public int biHeight;
/*     */     public short biPlanes;
/*     */     public short biBitCount;
/*     */     public int biCompression;
/*     */     public int biSizeImage;
/*     */     public int biXPelsPerMeter;
/*     */     public int biYPelsPerMeter;
/*     */     public int biClrUsed;
/*     */     public int biClrImportant;
/*     */     
/* 120 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "biSize", "biWidth", "biHeight", "biPlanes", "biBitCount", "biCompression", "biSizeImage", "biXPelsPerMeter", "biYPelsPerMeter", "biClrUsed", "biClrImportant" }); }
/*     */   }
/*     */   
/*     */   public static class RGBQUAD
/*     */     extends Structure {
/*     */     public byte rgbBlue;
/*     */     public byte rgbGreen;
/*     */     public byte rgbRed;
/* 128 */     public byte rgbReserved = 0;
/*     */     
/* 130 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "rgbBlue", "rgbGreen", "rgbRed", "rgbReserved" }); }
/*     */   }
/*     */   
/*     */   public static class BITMAPINFO
/*     */     extends Structure {
/*     */     public WinGDI.BITMAPINFOHEADER bmiHeader;
/*     */     public WinGDI.RGBQUAD[] bmiColors;
/*     */     
/* 138 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "bmiHeader", "bmiColors" }); }
/*     */     
/* 140 */     public BITMAPINFO() { this(1); } public BITMAPINFO(int size) { this.bmiHeader = new WinGDI.BITMAPINFOHEADER();
/*     */       this.bmiColors = new WinGDI.RGBQUAD[1];
/* 142 */       this.bmiColors = new WinGDI.RGBQUAD[size]; }
/*     */   
/*     */   }
/*     */   
/*     */   public static class ICONINFO extends Structure {
/*     */     public boolean fIcon;
/*     */     public int xHotspot;
/*     */     public int yHotspot;
/*     */     public WinDef.HBITMAP hbmMask;
/*     */     public WinDef.HBITMAP hbmColor;
/*     */     
/* 153 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "fIcon", "xHotspot", "yHotspot", "hbmMask", "hbmColor" }); }
/*     */   }
/*     */   
/*     */   public static class BITMAP
/*     */     extends Structure
/*     */   {
/*     */     public NativeLong bmType;
/*     */     public NativeLong bmWidth;
/*     */     public NativeLong bmHeight;
/*     */     public NativeLong bmWidthBytes;
/*     */     public short bmPlanes;
/*     */     public short bmBitsPixel;
/*     */     public Pointer bmBits;
/*     */     
/* 167 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "bmType", "bmWidth", "bmHeight", "bmWidthBytes", "bmPlanes", "bmBitsPixel", "bmBits" }); }
/*     */   }
/*     */   
/*     */   public static class DIBSECTION
/*     */     extends Structure
/*     */   {
/*     */     public WinGDI.BITMAP dsBm;
/*     */     public WinGDI.BITMAPINFOHEADER dsBmih;
/* 175 */     public int[] dsBitfields = new int[3];
/*     */     public WinNT.HANDLE dshSection;
/*     */     public int dsOffset;
/*     */     
/* 179 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "dsBm", "dsBmih", "dsBitfields", "dshSection", "dsOffset" }); } }
/*     */   public static class PIXELFORMATDESCRIPTOR extends Structure { public short nSize; public short nVersion; public int dwFlags;
/*     */     public byte iPixelType;
/*     */     public byte cColorBits;
/*     */     public byte cRedBits;
/*     */     public byte cRedShift;
/*     */     public byte cGreenBits;
/*     */     public byte cGreenShift;
/*     */     public byte cBlueBits;
/*     */     public byte cBlueShift;
/*     */     public byte cAlphaBits;
/*     */     public byte cAlphaShift;
/*     */     
/* 192 */     public PIXELFORMATDESCRIPTOR() { this.nSize = (short)size(); }
/*     */     public byte cAccumBits; public byte cAccumRedBits; public byte cAccumGreenBits; public byte cAccumBlueBits; public byte cAccumAlphaBits; public byte cDepthBits; public byte cStencilBits; public byte cAuxBuffers; public byte iLayerType; public byte bReserved; public int dwLayerMask; public int dwVisibleMask; public int dwDamageMask;
/*     */     
/*     */     public PIXELFORMATDESCRIPTOR(Pointer memory) {
/* 196 */       super(memory);
/* 197 */       read();
/*     */     }
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static class ByReference
/*     */       extends PIXELFORMATDESCRIPTOR
/*     */       implements Structure.ByReference {}
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 311 */     protected List getFieldOrder() { return Arrays.asList(new String[] { "nSize", "nVersion", "dwFlags", "iPixelType", "cColorBits", "cRedBits", "cRedShift", "cGreenBits", "cGreenShift", "cBlueBits", "cBlueShift", "cAlphaBits", "cAlphaShift", "cAccumBits", "cAccumRedBits", "cAccumGreenBits", "cAccumBlueBits", "cAccumAlphaBits", "cDepthBits", "cStencilBits", "cAuxBuffers", "iLayerType", "bReserved", "dwLayerMask", "dwVisibleMask", "dwDamageMask" }); } }
/*     */    }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\WinGDI.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */