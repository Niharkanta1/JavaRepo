/*    */ package com.sun.jna.platform.win32;
/*    */ 
/*    */ import com.sun.jna.Function;
/*    */ import com.sun.jna.Pointer;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class OpenGL32Util
/*    */ {
/*    */   public static Function wglGetProcAddress(String procName) {
/* 33 */     Pointer funcPointer = OpenGL32.INSTANCE.wglGetProcAddress("wglEnumGpusNV");
/* 34 */     return (funcPointer == null) ? null : Function.getFunction(funcPointer);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int countGpusNV() {
/* 43 */     hWnd = User32Util.createWindow("Message", null, 0, 0, 0, 0, 0, null, null, null, null);
/* 44 */     WinDef.HDC hdc = User32.INSTANCE.GetDC(hWnd);
/*    */ 
/*    */     
/* 47 */     WinGDI.PIXELFORMATDESCRIPTOR.ByReference pfd = new WinGDI.PIXELFORMATDESCRIPTOR.ByReference();
/* 48 */     pfd.nVersion = 1;
/* 49 */     pfd.dwFlags = 37;
/* 50 */     pfd.iPixelType = 0;
/* 51 */     pfd.cColorBits = 24;
/* 52 */     pfd.cDepthBits = 16;
/* 53 */     pfd.iLayerType = 0;
/* 54 */     GDI32.INSTANCE.SetPixelFormat(hdc, GDI32.INSTANCE.ChoosePixelFormat(hdc, pfd), pfd);
/*    */ 
/*    */     
/* 57 */     WinDef.HGLRC hGLRC = OpenGL32.INSTANCE.wglCreateContext(hdc);
/* 58 */     OpenGL32.INSTANCE.wglMakeCurrent(hdc, hGLRC);
/* 59 */     Pointer funcPointer = OpenGL32.INSTANCE.wglGetProcAddress("wglEnumGpusNV");
/* 60 */     Function fncEnumGpusNV = (funcPointer == null) ? null : Function.getFunction(funcPointer);
/* 61 */     OpenGL32.INSTANCE.wglDeleteContext(hGLRC);
/*    */ 
/*    */     
/* 64 */     User32.INSTANCE.ReleaseDC(hWnd, hdc);
/* 65 */     User32Util.destroyWindow(hWnd);
/*    */ 
/*    */     
/* 68 */     if (fncEnumGpusNV == null) return 0;
/*    */ 
/*    */     
/* 71 */     WinDef.HGLRCByReference hGPU = new WinDef.HGLRCByReference();
/* 72 */     for (int i = 0; i < 16; i++) {
/* 73 */       Boolean ok = (Boolean)fncEnumGpusNV.invoke(Boolean.class, new Object[] { Integer.valueOf(i), hGPU });
/* 74 */       if (!ok.booleanValue()) return i;
/*    */     
/*    */     } 
/* 77 */     return 0;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\platform\win32\OpenGL32Util.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */