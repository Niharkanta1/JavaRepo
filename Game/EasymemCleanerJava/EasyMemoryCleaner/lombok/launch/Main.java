/*    */ package lombok.launch;
/*    */ 
/*    */ import java.lang.reflect.InvocationTargetException;
/*    */ import java.util.Arrays;
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
/*    */ class Main
/*    */ {
/* 29 */   static ClassLoader createShadowClassLoader() { return new ShadowClassLoader(Main.class.getClassLoader(), "lombok", null, Arrays.asList(new String[0]), Arrays.asList(new String[] { "lombok.patcher.Symbols" })); }
/*    */ 
/*    */   
/*    */   public static void main(String[] args) throws Throwable {
/* 33 */     ClassLoader cl = createShadowClassLoader();
/* 34 */     Class<?> mc = cl.loadClass("lombok.core.Main");
/*    */     try {
/* 36 */       mc.getMethod("main", new Class[] { String[].class }).invoke(null, new Object[] { args });
/* 37 */     } catch (InvocationTargetException e) {
/* 38 */       throw e.getCause();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\launch\Main.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */