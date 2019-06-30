/*     */ package lombok.launch;
/*     */ 
/*     */ import java.util.Set;
/*     */ import javax.annotation.processing.AbstractProcessor;
/*     */ import javax.annotation.processing.Completion;
/*     */ import javax.annotation.processing.ProcessingEnvironment;
/*     */ import javax.annotation.processing.RoundEnvironment;
/*     */ import javax.annotation.processing.SupportedAnnotationTypes;
/*     */ import javax.lang.model.SourceVersion;
/*     */ import javax.lang.model.element.AnnotationMirror;
/*     */ import javax.lang.model.element.Element;
/*     */ import javax.lang.model.element.ExecutableElement;
/*     */ import javax.lang.model.element.TypeElement;
/*     */ import javax.lang.model.type.TypeMirror;
/*     */ import org.mapstruct.ap.spi.AstModifyingAnnotationProcessor;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class AnnotationProcessorHider
/*     */ {
/*     */   public static class AstModificationNotifier
/*     */     implements AstModifyingAnnotationProcessor
/*     */   {
/*     */     public boolean isTypeComplete(TypeMirror type) {
/*  43 */       if (System.getProperty("lombok.disable") != null) return true; 
/*  44 */       return AnnotationProcessorHider.AstModificationNotifierData.lombokInvoked;
/*     */     }
/*     */   }
/*     */   
/*     */   static class AstModificationNotifierData {}
/*     */   
/*     */   public static class AnnotationProcessor
/*     */     extends AbstractProcessor
/*     */   {
/*  53 */     private final AbstractProcessor instance = createWrappedInstance();
/*     */ 
/*     */     
/*  56 */     public Set<String> getSupportedOptions() { return this.instance.getSupportedOptions(); }
/*     */ 
/*     */ 
/*     */     
/*  60 */     public Set<String> getSupportedAnnotationTypes() { return this.instance.getSupportedAnnotationTypes(); }
/*     */ 
/*     */ 
/*     */     
/*  64 */     public SourceVersion getSupportedSourceVersion() { return this.instance.getSupportedSourceVersion(); }
/*     */ 
/*     */     
/*     */     public void init(ProcessingEnvironment processingEnv) {
/*  68 */       AnnotationProcessorHider.AstModificationNotifierData.lombokInvoked = true;
/*  69 */       this.instance.init(processingEnv);
/*  70 */       super.init(processingEnv);
/*     */     }
/*     */ 
/*     */     
/*  74 */     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) { return this.instance.process(annotations, roundEnv); }
/*     */ 
/*     */ 
/*     */     
/*  78 */     public Iterable<? extends Completion> getCompletions(Element element, AnnotationMirror annotation, ExecutableElement member, String userText) { return this.instance.getCompletions(element, annotation, member, userText); }
/*     */ 
/*     */     
/*     */     private static AbstractProcessor createWrappedInstance() {
/*  82 */       cl = Main.createShadowClassLoader();
/*     */       try {
/*  84 */         Class<?> mc = cl.loadClass("lombok.core.AnnotationProcessor");
/*  85 */         return (AbstractProcessor)mc.newInstance();
/*  86 */       } catch (Throwable t) {
/*  87 */         if (t instanceof Error) throw (Error)t; 
/*  88 */         if (t instanceof RuntimeException) throw (RuntimeException)t; 
/*  89 */         throw new RuntimeException(t);
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   @SupportedAnnotationTypes({"lombok.*"})
/*     */   public static class ClaimingProcessor
/*     */     extends AbstractProcessor {
/*  97 */     public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) { return true; }
/*     */ 
/*     */ 
/*     */     
/* 101 */     public SourceVersion getSupportedSourceVersion() { return SourceVersion.latest(); }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\launch\AnnotationProcessorHider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */