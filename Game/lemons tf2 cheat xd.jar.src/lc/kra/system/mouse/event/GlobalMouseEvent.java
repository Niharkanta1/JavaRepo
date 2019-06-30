/*     */ package lc.kra.system.mouse.event;
/*     */ 
/*     */ import java.util.EventObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GlobalMouseEvent
/*     */   extends EventObject
/*     */ {
/*     */   private static final long serialVersionUID = -8194688548489965445L;
/*     */   public static final int TS_UP = 0;
/*     */   public static final int TS_DOWN = 1;
/*     */   public static final int TS_MOVE = 2;
/*     */   public static final int TS_WHEEL = 3;
/*     */   public static final int BUTTON_NO = 0;
/*     */   public static final int BUTTON_LEFT = 1;
/*     */   public static final int BUTTON_RIGHT = 2;
/*     */   public static final int BUTTON_MIDDLE = 16;
/*     */   public static final int WHEEL_DELTA = 120;
/*     */   private int transitionState;
/*     */   private int button;
/*     */   private int buttons;
/*     */   private int x;
/*     */   private int y;
/*     */   private int delta;
/*     */   private long deviceHandle;
/*     */   
/*     */   public GlobalMouseEvent(Object source, int transitionState, int button, int buttons, int x, int y, int delta, long deviceHandle) {
/*  56 */     super(source);
/*  57 */     this.transitionState = transitionState;
/*  58 */     this.button = button;
/*  59 */     this.buttons = buttons;
/*  60 */     this.x = x;
/*  61 */     this.y = y;
/*  62 */     this.delta = delta;
/*  63 */     this.deviceHandle = deviceHandle;
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
/*  75 */   public int getTransitionState() { return this.transitionState; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   public int getButton() { return this.button; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   public int getButtons() { return this.buttons; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   public int getX() { return this.x; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 108 */   public int getY() { return this.y; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 117 */   public int getDelta() { return this.delta; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 122 */   public long getDeviceHandle() { return this.deviceHandle; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 130 */     StringBuilder builder = (new StringBuilder()).append(this.x).append(',').append(this.y);
/* 131 */     if (this.buttons != 0 || this.transitionState == 3) {
/* 132 */       builder.append(" [");
/* 133 */       if ((this.buttons & true) != 0)
/* 134 */         builder.append("left,"); 
/* 135 */       if ((this.buttons & 0x2) != 0)
/* 136 */         builder.append("right,"); 
/* 137 */       if ((this.buttons & 0x10) != 0)
/* 138 */         builder.append("middle,"); 
/* 139 */       if (this.transitionState == 3)
/* 140 */         builder.append("delta ").append(this.delta).append(','); 
/* 141 */       return builder.deleteCharAt(builder.length() - 1).append(']').toString();
/* 142 */     }  return builder.toString();
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lc\kra\system\mouse\event\GlobalMouseEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */