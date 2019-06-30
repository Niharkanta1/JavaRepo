/*     */ package lc.kra.system.keyboard;
/*     */ 
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.BlockingQueue;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import lc.kra.system.LibraryLoader;
/*     */ import lc.kra.system.keyboard.event.GlobalKeyEvent;
/*     */ import lc.kra.system.keyboard.event.GlobalKeyListener;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlobalKeyboardHook
/*     */ {
/*     */   private static final int STATUS_SUCCESS = 0;
/*     */   private NativeKeyboardHook keyboardHook;
/*     */   private BlockingQueue<GlobalKeyEvent> inputBuffer;
/*     */   private boolean menuPressed;
/*     */   private boolean shiftPressed;
/*     */   private boolean controlPressed;
/*     */   private boolean extendedKey;
/*     */   private List<GlobalKeyListener> listeners;
/*     */   private Thread eventDispatcher;
/*     */   
/*  86 */   public GlobalKeyboardHook() throws UnsatisfiedLinkError { this(false); } public GlobalKeyboardHook(boolean raw) throws UnsatisfiedLinkError { this.inputBuffer = new LinkedBlockingQueue(); this.listeners = new CopyOnWriteArrayList();
/*     */     this.eventDispatcher = new Thread() { public void run() throws UnsatisfiedLinkError { try {
/*     */             while (GlobalKeyboardHook.this.isAlive()) {
/*     */               GlobalKeyEvent event = (GlobalKeyEvent)GlobalKeyboardHook.this.inputBuffer.take();
/*     */               if (event.getTransitionState() == 1) {
/*     */                 GlobalKeyboardHook.this.keyPressed(event);
/*     */                 continue;
/*     */               } 
/*     */               GlobalKeyboardHook.this.keyReleased(event);
/*     */             } 
/*     */           } catch (InterruptedException interruptedException) {} } }
/*     */       ;
/*  98 */     LibraryLoader.loadLibrary();
/*     */ 
/*     */     
/* 101 */     this.keyboardHook = new NativeKeyboardHook(raw)
/*     */       {
/*     */         
/*     */         public void handleKey(int virtualKeyCode, int transitionState, char keyChar, long deviceHandle)
/*     */         {
/* 106 */           GlobalKeyboardHook.this.switchControlKeys(virtualKeyCode, transitionState);
/* 107 */           GlobalKeyboardHook.this.inputBuffer.add(new GlobalKeyEvent(this, virtualKeyCode, transitionState, keyChar, GlobalKeyboardHook.this.menuPressed, GlobalKeyboardHook.this.shiftPressed, GlobalKeyboardHook.this.controlPressed, GlobalKeyboardHook.this.extendedKey, deviceHandle));
/*     */         }
/*     */       };
/*     */ 
/*     */     
/* 112 */     this.eventDispatcher.start(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 120 */   public void addKeyListener(GlobalKeyListener listener) { this.listeners.add(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 126 */   public void removeKeyListener(GlobalKeyListener listener) { this.listeners.remove(listener); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void keyPressed(GlobalKeyEvent event) {
/* 134 */     for (GlobalKeyListener listener : this.listeners) {
/* 135 */       listener.keyPressed(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void keyReleased(GlobalKeyEvent event) {
/* 143 */     for (GlobalKeyListener listener : this.listeners) {
/* 144 */       listener.keyReleased(event);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 152 */   public boolean isAlive() { return (this.keyboardHook != null && this.keyboardHook.isAlive()); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void shutdownHook() throws UnsatisfiedLinkError {
/* 159 */     if (isAlive()) {
/* 160 */       this.keyboardHook.unregisterHook(); try {
/* 161 */         this.keyboardHook.join();
/* 162 */       } catch (InterruptedException e) {
/* 163 */         throw new RuntimeException(e);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Map<Long, String> listKeyboards() throws UnsatisfiedLinkError {
/* 174 */     LibraryLoader.loadLibrary();
/* 175 */     return NativeKeyboardHook.listDevices();
/*     */   }
/*     */   
/*     */   private static abstract class NativeKeyboardHook extends Thread {
/*     */     private int status;
/*     */     private boolean raw;
/*     */     
/*     */     public NativeKeyboardHook(boolean raw) throws UnsatisfiedLinkError {
/* 183 */       super("Global Keyboard Hook Thread");
/* 184 */       setDaemon(false); setPriority(10);
/* 185 */       synchronized (this) {
/* 186 */         this.raw = raw; try {
/* 187 */           start(); wait();
/* 188 */         } catch (InterruptedException e) {
/* 189 */           throw new RuntimeException(e);
/*     */         } 
/*     */         
/* 192 */         if (this.status != 0)
/* 193 */           throw new RuntimeException("Low-level keyboard hook failed (" + this.status + ")"); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public void run() throws UnsatisfiedLinkError {
/* 198 */       this.status = registerHook(this.raw);
/* 199 */       synchronized (this) {
/* 200 */         notifyAll();
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public final native int registerHook(boolean param1Boolean);
/*     */     
/*     */     public final native void unregisterHook() throws UnsatisfiedLinkError;
/*     */     
/*     */     public static final native Map<Long, String> listDevices() throws UnsatisfiedLinkError;
/*     */     
/*     */     public abstract void handleKey(int param1Int1, int param1Int2, char param1Char, long param1Long);
/*     */   }
/*     */   
/*     */   private void switchControlKeys(int virtualKeyCode, int transitionState) {
/* 215 */     boolean downTransition = (transitionState == 1);
/* 216 */     switch (virtualKeyCode) { case 92:
/* 217 */         this.extendedKey = downTransition; break;
/* 218 */       case 165: this.extendedKey = downTransition;
/*     */       case 18: case 164:
/* 220 */         this.menuPressed = downTransition; break;
/*     */       case 161:
/* 222 */         this.extendedKey = downTransition;
/*     */       case 16: case 160:
/* 224 */         this.shiftPressed = downTransition; break;
/*     */       case 163:
/* 226 */         this.extendedKey = downTransition;
/*     */       case 17: case 162:
/* 228 */         this.controlPressed = downTransition;
/*     */         break; }
/*     */   
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lc\kra\system\keyboard\GlobalKeyboardHook.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */