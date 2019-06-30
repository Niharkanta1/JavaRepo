/*     */ package lc.kra.system.mouse;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import lc.kra.system.LibraryLoader;
/*     */ import lc.kra.system.mouse.event.GlobalMouseEvent;
/*     */ import lc.kra.system.mouse.event.GlobalMouseListener;
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
/*     */ public class GlobalMouseHook
/*     */ {
/*     */   private static final int STATUS_SUCCESS = 0;
/*     */   private NativeMouseHook mouseHook;
/*     */   private BlockingQueue<GlobalMouseEvent> inputBuffer;
/*     */   private int buttons;
/*     */   private List<GlobalMouseListener> listeners;
/*     */   private Thread eventDispatcher;
/*     */   
/*  91 */   public GlobalMouseHook() throws UnsatisfiedLinkError { this(false); } public GlobalMouseHook(boolean raw) throws UnsatisfiedLinkError { this.inputBuffer = new LinkedBlockingQueue(); this.buttons = 0; this.listeners = new CopyOnWriteArrayList(); this.eventDispatcher = new Thread() { public void run() throws UnsatisfiedLinkError { try { while (GlobalMouseHook.this.isAlive()) { GlobalMouseEvent event = (GlobalMouseEvent)GlobalMouseHook.this.inputBuffer.take(); switch (event.getTransitionState()) {
/*     */                 case 0:
/*     */                   GlobalMouseHook.this.mouseReleased(event); break;
/*     */                 case 1:
/*     */                   GlobalMouseHook.this.mousePressed(event); break;
/*     */                 case 2:
/*     */                   GlobalMouseHook.this.mouseMoved(event); break;
/*     */                 case 3:
/*     */                   GlobalMouseHook.this.mouseWheel(event); break;
/*     */               }  }
/*     */              }
/*     */           catch (InterruptedException interruptedException) {} } }
/* 103 */       ; LibraryLoader.loadLibrary();
/*     */ 
/*     */     
/* 106 */     this.mouseHook = new NativeMouseHook(raw)
/*     */       {
/*     */         
/*     */         public void handleMouse(int transitionState, int button, int x, int y, int delta, long deviceHandle)
/*     */         {
/* 111 */           GlobalMouseHook.this.inputBuffer.add(new GlobalMouseEvent(this, transitionState, button, GlobalMouseHook.this.buttons = GlobalMouseHook.this.buttons ^ button, x, y, delta, deviceHandle));
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 116 */     this.eventDispatcher.start(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 124 */   public void addMouseListener(GlobalMouseListener listener) { this.listeners.add(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 130 */   public void removeMouseListener(GlobalMouseListener listener) { this.listeners.remove(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mousePressed(GlobalMouseEvent event) {
/* 138 */     for (GlobalMouseListener listener : this.listeners) {
/* 139 */       listener.mousePressed(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mouseReleased(GlobalMouseEvent event) {
/* 147 */     for (GlobalMouseListener listener : this.listeners) {
/* 148 */       listener.mouseReleased(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mouseMoved(GlobalMouseEvent event) {
/* 156 */     for (GlobalMouseListener listener : this.listeners) {
/* 157 */       listener.mouseMoved(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mouseWheel(GlobalMouseEvent event) {
/* 165 */     for (GlobalMouseListener listener : this.listeners) {
/* 166 */       listener.mouseWheel(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 174 */   public boolean isAlive() { return (this.mouseHook != null && this.mouseHook.isAlive()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdownHook() throws UnsatisfiedLinkError {
/* 181 */     if (isAlive()) {
/* 182 */       this.mouseHook.unregisterHook(); try {
/* 183 */         this.mouseHook.join();
/* 184 */       } catch (InterruptedException e) {
/* 185 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Long, String> listMice() throws UnsatisfiedLinkError {
/* 196 */     LibraryLoader.loadLibrary();
/* 197 */     return NativeMouseHook.listDevices();
/*     */   }
/*     */   
/*     */   private static abstract class NativeMouseHook extends Thread {
/*     */     private int status;
/*     */     private boolean raw;
/*     */     
/*     */     public NativeMouseHook(boolean raw) throws UnsatisfiedLinkError {
/* 205 */       super("Global Mouse Hook Thread");
/* 206 */       setDaemon(false); setPriority(10);
/* 207 */       synchronized (this) {
/* 208 */         this.raw = raw; try {
/* 209 */           start(); wait();
/* 210 */         } catch (InterruptedException e) {
/* 211 */           throw new RuntimeException(e);
/*     */         } 
/*     */         
/* 214 */         if (this.status != 0)
/* 215 */           throw new RuntimeException("Low-level mouse hook failed (" + this.status + ")"); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void run() throws UnsatisfiedLinkError {
/* 220 */       this.status = registerHook(this.raw);
/* 221 */       synchronized (this) {
/* 222 */         notifyAll();
/*     */       } 
/*     */     }
/*     */     
/*     */     public final native int registerHook(boolean param1Boolean);
/*     */     
/*     */     public final native void unregisterHook() throws UnsatisfiedLinkError;
/*     */     
/*     */     public static final native Map<Long, String> listDevices() throws UnsatisfiedLinkError;
/*     */     
/*     */     public abstract void handleMouse(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, long param1Long);
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lc\kra\system\mouse\GlobalMouseHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */