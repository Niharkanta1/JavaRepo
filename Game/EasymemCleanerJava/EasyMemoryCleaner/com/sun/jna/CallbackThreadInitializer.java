/*    */ package com.sun.jna;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CallbackThreadInitializer
/*    */ {
/*    */   private boolean daemon;
/*    */   private boolean detach;
/*    */   private String name;
/*    */   private ThreadGroup group;
/*    */   
/* 42 */   public CallbackThreadInitializer() { this(true); }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 48 */   public CallbackThreadInitializer(boolean daemon) { this(daemon, false); }
/*    */ 
/*    */ 
/*    */   
/* 52 */   public CallbackThreadInitializer(boolean daemon, boolean detach) { this(daemon, detach, null); }
/*    */ 
/*    */ 
/*    */   
/* 56 */   public CallbackThreadInitializer(boolean daemon, boolean detach, String name) { this(daemon, detach, name, null); }
/*    */ 
/*    */   
/*    */   public CallbackThreadInitializer(boolean daemon, boolean detach, String name, ThreadGroup group) {
/* 60 */     this.daemon = daemon;
/* 61 */     this.detach = detach;
/* 62 */     this.name = name;
/* 63 */     this.group = group;
/*    */   }
/*    */ 
/*    */   
/* 67 */   public String getName(Callback cb) { return this.name; }
/*    */   
/* 69 */   public ThreadGroup getThreadGroup(Callback cb) { return this.group; }
/*    */   
/* 71 */   public boolean isDaemon(Callback cb) { return this.daemon; }
/*    */ 
/*    */ 
/*    */   
/* 75 */   public boolean detach(Callback cb) { return this.detach; }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\CallbackThreadInitializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */