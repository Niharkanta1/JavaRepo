/*    */ package lombok;
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
/*    */ public class Lombok
/*    */ {
/*    */   public static RuntimeException sneakyThrow(Throwable t) {
/* 50 */     if (t == null) throw new NullPointerException("t"); 
/* 51 */     sneakyThrow0(t);
/* 52 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 57 */   private static <T extends Throwable> void sneakyThrow0(Throwable t) throws T { throw t; }
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
/* 70 */   public static <T> T preventNullAnalysis(T value) { return value; }
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
/*    */   public static <T> T checkNotNull(T value, String message) {
/* 83 */     if (value == null) throw new NullPointerException(message); 
/* 84 */     return value;
/*    */   }
/*    */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\Lombok.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */