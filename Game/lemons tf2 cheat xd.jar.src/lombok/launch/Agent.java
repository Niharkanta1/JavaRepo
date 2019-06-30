/*    */ package lombok.launch;
/*    */ 
/*    */ import java.lang.instrument.Instrumentation;
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.lang.reflect.Method;
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
/*    */ final class Agent
/*    */ {
/* 30 */   public static void agentmain(String agentArgs, Instrumentation instrumentation) throws Throwable { runLauncher(agentArgs, instrumentation, true); }
/*    */ 
/*    */ 
/*    */   
/* 34 */   public static void premain(String agentArgs, Instrumentation instrumentation) throws Throwable { runLauncher(agentArgs, instrumentation, false); }
/*    */ 
/*    */   
/*    */   private static void runLauncher(String agentArgs, Instrumentation instrumentation, boolean injected) throws Throwable {
/* 38 */     ClassLoader cl = Main.createShadowClassLoader();
/*    */     try {
/* 40 */       Class<?> c = cl.loadClass("lombok.core.AgentLauncher");
/* 41 */       Method m = c.getDeclaredMethod("runAgents", new Class[] { String.class, Instrumentation.class, boolean.class, Class.class });
/* 42 */       m.invoke(null, new Object[] { agentArgs, instrumentation, Boolean.valueOf(injected), Agent.class });
/* 43 */     } catch (InvocationTargetException e) {
/* 44 */       throw e.getCause();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\launch\Agent.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */