/*     */ package lombok.launch;
/*     */ 
/*     */ import java.io.BufferedOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.Field;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Stack;
/*     */ import lombok.eclipse.EclipseAugments;
/*     */ import org.eclipse.core.runtime.CoreException;
/*     */ import org.eclipse.jdt.core.IAnnotatable;
/*     */ import org.eclipse.jdt.core.IAnnotation;
/*     */ import org.eclipse.jdt.core.IField;
/*     */ import org.eclipse.jdt.core.IMethod;
/*     */ import org.eclipse.jdt.core.IType;
/*     */ import org.eclipse.jdt.core.JavaModelException;
/*     */ import org.eclipse.jdt.core.dom.ASTNode;
/*     */ import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
/*     */ import org.eclipse.jdt.core.dom.Annotation;
/*     */ import org.eclipse.jdt.core.dom.CompilationUnit;
/*     */ import org.eclipse.jdt.core.dom.MethodDeclaration;
/*     */ import org.eclipse.jdt.core.dom.Name;
/*     */ import org.eclipse.jdt.core.dom.NormalAnnotation;
/*     */ import org.eclipse.jdt.core.dom.SimpleName;
/*     */ import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
/*     */ import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
/*     */ import org.eclipse.jdt.core.search.SearchMatch;
/*     */ import org.eclipse.jdt.internal.compiler.ast.ASTNode;
/*     */ import org.eclipse.jdt.internal.compiler.ast.AbstractMethodDeclaration;
/*     */ import org.eclipse.jdt.internal.compiler.ast.Annotation;
/*     */ import org.eclipse.jdt.internal.compiler.ast.CompilationUnitDeclaration;
/*     */ import org.eclipse.jdt.internal.compiler.ast.Expression;
/*     */ import org.eclipse.jdt.internal.compiler.ast.FieldDeclaration;
/*     */ import org.eclipse.jdt.internal.compiler.ast.ForeachStatement;
/*     */ import org.eclipse.jdt.internal.compiler.ast.LocalDeclaration;
/*     */ import org.eclipse.jdt.internal.compiler.ast.MessageSend;
/*     */ import org.eclipse.jdt.internal.compiler.lookup.BlockScope;
/*     */ import org.eclipse.jdt.internal.compiler.lookup.MethodBinding;
/*     */ import org.eclipse.jdt.internal.compiler.lookup.Scope;
/*     */ import org.eclipse.jdt.internal.compiler.lookup.TypeBinding;
/*     */ import org.eclipse.jdt.internal.compiler.parser.Parser;
/*     */ import org.eclipse.jdt.internal.compiler.problem.ProblemReporter;
/*     */ import org.eclipse.jdt.internal.core.SourceField;
/*     */ import org.eclipse.jdt.internal.core.dom.rewrite.NodeRewriteEvent;
/*     */ import org.eclipse.jdt.internal.core.dom.rewrite.RewriteEvent;
/*     */ import org.eclipse.jdt.internal.core.dom.rewrite.TokenScanner;
/*     */ import org.eclipse.jdt.internal.corext.refactoring.SearchResultGroup;
/*     */ import org.eclipse.jdt.internal.corext.refactoring.structure.ASTNodeSearchUtil;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class PatchFixesHider
/*     */ {
/*     */   public static final class Util
/*     */   {
/*     */     private static ClassLoader shadowLoader;
/*     */     
/*     */     public static Class<?> shadowLoadClass(String name) {
/*     */       try {
/*  88 */         if (shadowLoader == null) {
/*     */           try {
/*  90 */             Class.forName("lombok.core.LombokNode");
/*     */             
/*  92 */             shadowLoader = Util.class.getClassLoader();
/*  93 */           } catch (ClassNotFoundException e) {
/*     */             
/*  95 */             shadowLoader = Main.createShadowClassLoader();
/*     */           } 
/*     */         }
/*     */         
/*  99 */         return Class.forName(name, true, shadowLoader);
/* 100 */       } catch (ClassNotFoundException e) {
/* 101 */         throw sneakyThrow(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public static Method findMethod(Class<?> type, String name, Class... parameterTypes) {
/*     */       try {
/* 107 */         return type.getDeclaredMethod(name, parameterTypes);
/* 108 */       } catch (NoSuchMethodException e) {
/* 109 */         throw sneakyThrow(e);
/*     */       } 
/*     */     }
/*     */     
/*     */     public static Object invokeMethod(Method method, Object... args) {
/*     */       try {
/* 115 */         return method.invoke(null, args);
/* 116 */       } catch (IllegalAccessException e) {
/* 117 */         throw sneakyThrow(e);
/* 118 */       } catch (InvocationTargetException e) {
/* 119 */         throw sneakyThrow(e.getCause());
/*     */       } 
/*     */     }
/*     */     
/*     */     private static RuntimeException sneakyThrow(Throwable t) {
/* 124 */       if (t == null) throw new NullPointerException("t"); 
/* 125 */       sneakyThrow0(t);
/* 126 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 131 */     private static <T extends Throwable> void sneakyThrow0(Throwable t) throws T { throw t; }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class LombokDeps
/*     */   {
/*     */     public static final Method ADD_LOMBOK_NOTES;
/*     */     public static final Method POST_COMPILER_BYTES_STRING;
/*     */     public static final Method POST_COMPILER_OUTPUTSTREAM;
/*     */     public static final Method POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING;
/*     */     
/*     */     static  {
/* 143 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.agent.PatchFixesShadowLoaded");
/* 144 */       ADD_LOMBOK_NOTES = PatchFixesHider.Util.findMethod(shadowed, "addLombokNotesToEclipseAboutDialog", new Class[] { String.class, String.class });
/* 145 */       POST_COMPILER_BYTES_STRING = PatchFixesHider.Util.findMethod(shadowed, "runPostCompiler", new Class[] { byte[].class, String.class });
/* 146 */       POST_COMPILER_OUTPUTSTREAM = PatchFixesHider.Util.findMethod(shadowed, "runPostCompiler", new Class[] { OutputStream.class });
/* 147 */       POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING = PatchFixesHider.Util.findMethod(shadowed, "runPostCompiler", new Class[] { BufferedOutputStream.class, String.class, String.class });
/*     */     }
/*     */ 
/*     */     
/* 151 */     public static String addLombokNotesToEclipseAboutDialog(String origReturnValue, String key) { return (String)PatchFixesHider.Util.invokeMethod(ADD_LOMBOK_NOTES, new Object[] { origReturnValue, key }); }
/*     */ 
/*     */ 
/*     */     
/* 155 */     public static byte[] runPostCompiler(byte[] bytes, String fileName) { return (byte[])PatchFixesHider.Util.invokeMethod(POST_COMPILER_BYTES_STRING, new Object[] { bytes, fileName }); }
/*     */ 
/*     */ 
/*     */     
/* 159 */     public static OutputStream runPostCompiler(OutputStream out) throws IOException { return (OutputStream)PatchFixesHider.Util.invokeMethod(POST_COMPILER_OUTPUTSTREAM, new Object[] { out }); }
/*     */ 
/*     */ 
/*     */     
/* 163 */     public static BufferedOutputStream runPostCompiler(BufferedOutputStream out, String path, String name) throws IOException { return (BufferedOutputStream)PatchFixesHider.Util.invokeMethod(POST_COMPILER_BUFFEREDOUTPUTSTREAM_STRING_STRING, new Object[] { out, path, name }); }
/*     */   }
/*     */   
/*     */   public static final class Transform
/*     */   {
/*     */     private static final Method TRANSFORM;
/*     */     private static final Method TRANSFORM_SWAPPED;
/*     */     
/*     */     static  {
/* 172 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.TransformEclipseAST");
/* 173 */       TRANSFORM = PatchFixesHider.Util.findMethod(shadowed, "transform", new Class[] { Parser.class, CompilationUnitDeclaration.class });
/* 174 */       TRANSFORM_SWAPPED = PatchFixesHider.Util.findMethod(shadowed, "transform_swapped", new Class[] { CompilationUnitDeclaration.class, Parser.class });
/*     */     }
/*     */ 
/*     */     
/* 178 */     public static void transform(Parser parser, CompilationUnitDeclaration ast) throws IOException { PatchFixesHider.Util.invokeMethod(TRANSFORM, new Object[] { parser, ast }); }
/*     */ 
/*     */ 
/*     */     
/* 182 */     public static void transform_swapped(CompilationUnitDeclaration ast, Parser parser) throws IOException { PatchFixesHider.Util.invokeMethod(TRANSFORM_SWAPPED, new Object[] { ast, parser }); }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Delegate
/*     */   {
/*     */     private static final Method HANDLE_DELEGATE_FOR_TYPE;
/*     */     
/*     */     static  {
/* 191 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.agent.PatchDelegatePortal");
/* 192 */       HANDLE_DELEGATE_FOR_TYPE = PatchFixesHider.Util.findMethod(shadowed, "handleDelegateForType", new Class[] { Object.class });
/*     */     }
/*     */ 
/*     */     
/* 196 */     public static boolean handleDelegateForType(Object classScope) { return ((Boolean)PatchFixesHider.Util.invokeMethod(HANDLE_DELEGATE_FOR_TYPE, new Object[] { classScope })).booleanValue(); }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class ValPortal
/*     */   {
/*     */     private static final Method COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE;
/*     */     private static final Method COPY_INITIALIZATION_OF_LOCAL_DECLARATION;
/*     */     private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT;
/*     */     private static final Method ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION;
/*     */     
/*     */     static  {
/* 208 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.agent.PatchValEclipsePortal");
/* 209 */       COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE = PatchFixesHider.Util.findMethod(shadowed, "copyInitializationOfForEachIterable", new Class[] { Object.class });
/* 210 */       COPY_INITIALIZATION_OF_LOCAL_DECLARATION = PatchFixesHider.Util.findMethod(shadowed, "copyInitializationOfLocalDeclaration", new Class[] { Object.class });
/* 211 */       ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT = PatchFixesHider.Util.findMethod(shadowed, "addFinalAndValAnnotationToVariableDeclarationStatement", new Class[] { Object.class, Object.class, Object.class });
/* 212 */       ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION = PatchFixesHider.Util.findMethod(shadowed, "addFinalAndValAnnotationToSingleVariableDeclaration", new Class[] { Object.class, Object.class, Object.class });
/*     */     }
/*     */ 
/*     */     
/* 216 */     public static void copyInitializationOfForEachIterable(Object parser) { PatchFixesHider.Util.invokeMethod(COPY_INITIALIZATION_OF_FOR_EACH_ITERABLE, new Object[] { parser }); }
/*     */ 
/*     */ 
/*     */     
/* 220 */     public static void copyInitializationOfLocalDeclaration(Object parser) { PatchFixesHider.Util.invokeMethod(COPY_INITIALIZATION_OF_LOCAL_DECLARATION, new Object[] { parser }); }
/*     */ 
/*     */ 
/*     */     
/* 224 */     public static void addFinalAndValAnnotationToVariableDeclarationStatement(Object converter, Object out, Object in) { PatchFixesHider.Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_VARIABLE_DECLARATION_STATEMENT, new Object[] { converter, out, in }); }
/*     */ 
/*     */ 
/*     */     
/* 228 */     public static void addFinalAndValAnnotationToSingleVariableDeclaration(Object converter, Object out, Object in) { PatchFixesHider.Util.invokeMethod(ADD_FINAL_AND_VAL_ANNOTATION_TO_SINGLE_VARIABLE_DECLARATION, new Object[] { converter, out, in }); }
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Val
/*     */   {
/*     */     private static final Method SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED;
/*     */     private static final Method SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2;
/*     */     private static final Method HANDLE_VAL_FOR_LOCAL_DECLARATION;
/*     */     private static final Method HANDLE_VAL_FOR_FOR_EACH;
/*     */     
/*     */     static  {
/* 240 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.agent.PatchVal");
/* 241 */       SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED = PatchFixesHider.Util.findMethod(shadowed, "skipResolveInitializerIfAlreadyCalled", new Class[] { Expression.class, BlockScope.class });
/* 242 */       SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2 = PatchFixesHider.Util.findMethod(shadowed, "skipResolveInitializerIfAlreadyCalled2", new Class[] { Expression.class, BlockScope.class, LocalDeclaration.class });
/* 243 */       HANDLE_VAL_FOR_LOCAL_DECLARATION = PatchFixesHider.Util.findMethod(shadowed, "handleValForLocalDeclaration", new Class[] { LocalDeclaration.class, BlockScope.class });
/* 244 */       HANDLE_VAL_FOR_FOR_EACH = PatchFixesHider.Util.findMethod(shadowed, "handleValForForEach", new Class[] { ForeachStatement.class, BlockScope.class });
/*     */     }
/*     */ 
/*     */     
/* 248 */     public static TypeBinding skipResolveInitializerIfAlreadyCalled(Expression expr, BlockScope scope) { return (TypeBinding)PatchFixesHider.Util.invokeMethod(SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED, new Object[] { expr, scope }); }
/*     */ 
/*     */ 
/*     */     
/* 252 */     public static TypeBinding skipResolveInitializerIfAlreadyCalled2(Expression expr, BlockScope scope, LocalDeclaration decl) { return (TypeBinding)PatchFixesHider.Util.invokeMethod(SKIP_RESOLVE_INITIALIZER_IF_ALREADY_CALLED2, new Object[] { expr, scope, decl }); }
/*     */ 
/*     */ 
/*     */     
/* 256 */     public static boolean handleValForLocalDeclaration(LocalDeclaration local, BlockScope scope) { return ((Boolean)PatchFixesHider.Util.invokeMethod(HANDLE_VAL_FOR_LOCAL_DECLARATION, new Object[] { local, scope })).booleanValue(); }
/*     */ 
/*     */ 
/*     */     
/* 260 */     public static boolean handleValForForEach(ForeachStatement forEach, BlockScope scope) { return ((Boolean)PatchFixesHider.Util.invokeMethod(HANDLE_VAL_FOR_FOR_EACH, new Object[] { forEach, scope })).booleanValue(); }
/*     */   }
/*     */   
/*     */   public static final class ExtensionMethod
/*     */   {
/*     */     private static final Method RESOLVE_TYPE;
/*     */     private static final Method ERROR_NO_METHOD_FOR;
/*     */     private static final Method INVALID_METHOD;
/*     */     private static final Method INVALID_METHOD2;
/*     */     
/*     */     static  {
/* 271 */       shadowed = PatchFixesHider.Util.shadowLoadClass("lombok.eclipse.agent.PatchExtensionMethod");
/* 272 */       RESOLVE_TYPE = PatchFixesHider.Util.findMethod(shadowed, "resolveType", new Class[] { TypeBinding.class, MessageSend.class, BlockScope.class });
/* 273 */       ERROR_NO_METHOD_FOR = PatchFixesHider.Util.findMethod(shadowed, "errorNoMethodFor", new Class[] { ProblemReporter.class, MessageSend.class, TypeBinding.class, TypeBinding[].class });
/* 274 */       INVALID_METHOD = PatchFixesHider.Util.findMethod(shadowed, "invalidMethod", new Class[] { ProblemReporter.class, MessageSend.class, MethodBinding.class });
/* 275 */       INVALID_METHOD2 = PatchFixesHider.Util.findMethod(shadowed, "invalidMethod", new Class[] { ProblemReporter.class, MessageSend.class, MethodBinding.class, Scope.class });
/*     */     }
/*     */ 
/*     */     
/* 279 */     public static TypeBinding resolveType(TypeBinding resolvedType, MessageSend methodCall, BlockScope scope) { return (TypeBinding)PatchFixesHider.Util.invokeMethod(RESOLVE_TYPE, new Object[] { resolvedType, methodCall, scope }); }
/*     */ 
/*     */ 
/*     */     
/* 283 */     public static void errorNoMethodFor(ProblemReporter problemReporter, MessageSend messageSend, TypeBinding recType, TypeBinding[] params) { PatchFixesHider.Util.invokeMethod(ERROR_NO_METHOD_FOR, new Object[] { problemReporter, messageSend, recType, params }); }
/*     */ 
/*     */ 
/*     */     
/* 287 */     public static void invalidMethod(ProblemReporter problemReporter, MessageSend messageSend, MethodBinding method) { PatchFixesHider.Util.invokeMethod(INVALID_METHOD, new Object[] { problemReporter, messageSend, method }); }
/*     */ 
/*     */ 
/*     */     
/* 291 */     public static void invalidMethod(ProblemReporter problemReporter, MessageSend messageSend, MethodBinding method, Scope scope) { PatchFixesHider.Util.invokeMethod(INVALID_METHOD2, new Object[] { problemReporter, messageSend, method, scope }); }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static final class PatchFixes
/*     */   {
/*     */     public static final int ALREADY_PROCESSED_FLAG = 8388608;
/*     */ 
/*     */ 
/*     */     
/*     */     public static boolean isGenerated(ASTNode node) {
/* 303 */       boolean result = false;
/*     */       try {
/* 305 */         result = ((Boolean)node.getClass().getField("$isGenerated").get(node)).booleanValue();
/* 306 */         if (!result && node.getParent() != null && node.getParent() instanceof org.eclipse.jdt.core.dom.QualifiedName) {
/* 307 */           result = isGenerated(node.getParent());
/*     */         }
/* 309 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 312 */       return result;
/*     */     }
/*     */ 
/*     */     
/* 316 */     public static boolean isListRewriteOnGeneratedNode(ListRewrite rewrite) { return isGenerated(rewrite.getParent()); }
/*     */ 
/*     */ 
/*     */     
/* 320 */     public static boolean returnFalse(Object object) { return false; }
/*     */ 
/*     */ 
/*     */     
/* 324 */     public static boolean returnTrue(Object object) { return true; }
/*     */ 
/*     */     
/*     */     public static List removeGeneratedNodes(List list) {
/*     */       try {
/* 329 */         List realNodes = new ArrayList(list.size());
/* 330 */         for (Object node : list) {
/* 331 */           if (!isGenerated((ASTNode)node)) {
/* 332 */             realNodes.add(node);
/*     */           }
/*     */         } 
/* 335 */         return realNodes;
/* 336 */       } catch (Exception exception) {
/*     */         
/* 338 */         return list;
/*     */       } 
/*     */     }
/*     */     public static String getRealMethodDeclarationSource(String original, Object processor, MethodDeclaration declaration) throws Exception {
/* 342 */       if (!isGenerated(declaration)) return original;
/*     */       
/* 344 */       List<Annotation> annotations = new ArrayList<Annotation>();
/* 345 */       for (Object modifier : declaration.modifiers()) {
/* 346 */         if (modifier instanceof Annotation) {
/* 347 */           Annotation annotation = (Annotation)modifier;
/* 348 */           String qualifiedAnnotationName = annotation.resolveTypeBinding().getQualifiedName();
/* 349 */           if (!"java.lang.Override".equals(qualifiedAnnotationName) && !"java.lang.SuppressWarnings".equals(qualifiedAnnotationName)) annotations.add(annotation);
/*     */         
/*     */         } 
/*     */       } 
/* 353 */       StringBuilder signature = new StringBuilder();
/* 354 */       addAnnotations(annotations, signature);
/*     */       
/* 356 */       if (((Boolean)processor.getClass().getDeclaredField("fPublic").get(processor)).booleanValue()) signature.append("public "); 
/* 357 */       if (((Boolean)processor.getClass().getDeclaredField("fAbstract").get(processor)).booleanValue()) signature.append("abstract ");
/*     */       
/* 359 */       signature
/* 360 */         .append(declaration.getReturnType2().toString())
/* 361 */         .append(" ").append(declaration.getName().getFullyQualifiedName())
/* 362 */         .append("(");
/*     */       
/* 364 */       boolean first = true;
/* 365 */       for (Object parameter : declaration.parameters()) {
/* 366 */         if (!first) signature.append(", "); 
/* 367 */         first = false;
/*     */         
/* 369 */         signature.append(parameter);
/*     */       } 
/*     */       
/* 372 */       signature.append(");");
/* 373 */       return signature.toString();
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
/*     */     public static void addAnnotations(List<Annotation> annotations, StringBuilder signature) {
/* 393 */       for (Annotation annotation : annotations) {
/* 394 */         List<String> values = new ArrayList<String>();
/* 395 */         if (annotation.isSingleMemberAnnotation()) {
/* 396 */           SingleMemberAnnotation smAnn = (SingleMemberAnnotation)annotation;
/* 397 */           values.add(smAnn.getValue().toString());
/* 398 */         } else if (annotation.isNormalAnnotation()) {
/* 399 */           NormalAnnotation normalAnn = (NormalAnnotation)annotation;
/* 400 */           for (Object value : normalAnn.values()) values.add(value.toString());
/*     */         
/*     */         } 
/* 403 */         signature.append("@").append(annotation.resolveTypeBinding().getQualifiedName());
/* 404 */         if (!values.isEmpty()) {
/* 405 */           signature.append("(");
/* 406 */           boolean first = true;
/* 407 */           for (String string : values) {
/* 408 */             if (!first) signature.append(", "); 
/* 409 */             first = false;
/* 410 */             signature.append('"').append(string).append('"');
/*     */           } 
/* 412 */           signature.append(")");
/*     */         } 
/* 414 */         signature.append(" ");
/*     */       } 
/*     */     }
/*     */     
/*     */     public static MethodDeclaration getRealMethodDeclarationNode(IMethod sourceMethod, CompilationUnit cuUnit) throws JavaModelException {
/* 419 */       MethodDeclaration methodDeclarationNode = ASTNodeSearchUtil.getMethodDeclarationNode(sourceMethod, cuUnit);
/* 420 */       if (isGenerated(methodDeclarationNode)) {
/* 421 */         IType declaringType = sourceMethod.getDeclaringType();
/* 422 */         Stack<IType> typeStack = new Stack<IType>();
/* 423 */         while (declaringType != null) {
/* 424 */           typeStack.push(declaringType);
/* 425 */           declaringType = declaringType.getDeclaringType();
/*     */         } 
/*     */         
/* 428 */         IType rootType = (IType)typeStack.pop();
/* 429 */         AbstractTypeDeclaration typeDeclaration = findTypeDeclaration(rootType, cuUnit.types());
/* 430 */         while (!typeStack.isEmpty() && typeDeclaration != null) {
/* 431 */           typeDeclaration = findTypeDeclaration((IType)typeStack.pop(), typeDeclaration.bodyDeclarations());
/*     */         }
/*     */         
/* 434 */         if (typeStack.isEmpty() && typeDeclaration != null) {
/* 435 */           String methodName = sourceMethod.getElementName();
/* 436 */           for (Object declaration : typeDeclaration.bodyDeclarations()) {
/* 437 */             if (declaration instanceof MethodDeclaration) {
/* 438 */               MethodDeclaration methodDeclaration = (MethodDeclaration)declaration;
/* 439 */               if (methodDeclaration.getName().toString().equals(methodName)) {
/* 440 */                 return methodDeclaration;
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/* 446 */       return methodDeclarationNode;
/*     */     }
/*     */ 
/*     */     
/*     */     public static AbstractTypeDeclaration findTypeDeclaration(IType searchType, List<?> nodes) {
/* 451 */       for (Object object : nodes) {
/* 452 */         if (object instanceof AbstractTypeDeclaration) {
/* 453 */           AbstractTypeDeclaration typeDeclaration = (AbstractTypeDeclaration)object;
/* 454 */           if (typeDeclaration.getName().toString().equals(searchType.getElementName()))
/* 455 */             return typeDeclaration; 
/*     */         } 
/*     */       } 
/* 458 */       return null;
/*     */     }
/*     */     
/*     */     public static int getSourceEndFixed(int sourceEnd, ASTNode node) throws Exception {
/* 462 */       if (sourceEnd == -1) {
/* 463 */         ASTNode object = (ASTNode)node.getClass().getField("$generatedBy").get(node);
/* 464 */         if (object != null) {
/* 465 */           return object.sourceEnd;
/*     */         }
/*     */       } 
/* 468 */       return sourceEnd;
/*     */     }
/*     */ 
/*     */     
/* 472 */     public static int fixRetrieveStartingCatchPosition(int original, int start) { return (original == -1) ? start : original; }
/*     */ 
/*     */ 
/*     */     
/* 476 */     public static int fixRetrieveIdentifierEndPosition(int original, int end) { return (original == -1) ? end : original; }
/*     */ 
/*     */ 
/*     */     
/* 480 */     public static int fixRetrieveEllipsisStartPosition(int original, int end) { return (original == -1) ? end : original; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 487 */     public static int fixRetrieveRightBraceOrSemiColonPosition(int original, int end) { return (original == -1) ? end : original; }
/*     */ 
/*     */     
/*     */     public static int fixRetrieveRightBraceOrSemiColonPosition(int retVal, AbstractMethodDeclaration amd) {
/* 491 */       if (retVal != -1 || amd == null) return retVal; 
/* 492 */       boolean isGenerated = (EclipseAugments.ASTNode_generatedBy.get(amd) != null);
/* 493 */       if (isGenerated) return amd.declarationSourceEnd; 
/* 494 */       return -1;
/*     */     }
/*     */     
/*     */     public static int fixRetrieveRightBraceOrSemiColonPosition(int retVal, FieldDeclaration fd) {
/* 498 */       if (retVal != -1 || fd == null) return retVal; 
/* 499 */       boolean isGenerated = (EclipseAugments.ASTNode_generatedBy.get(fd) != null);
/* 500 */       if (isGenerated) return fd.declarationSourceEnd; 
/* 501 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public static boolean checkBit24(Object node) {
/* 507 */       int bits = ((Integer)node.getClass().getField("bits").get(node)).intValue();
/* 508 */       return ((bits & 0x800000) != 0);
/*     */     }
/*     */ 
/*     */     
/* 512 */     public static boolean skipRewritingGeneratedNodes(ASTNode node) { return ((Boolean)node.getClass().getField("$isGenerated").get(node)).booleanValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static void setIsGeneratedFlag(ASTNode domNode, ASTNode internalNode) throws Exception {
/* 518 */       if (internalNode == null || domNode == null)
/* 519 */         return;  boolean isGenerated = (EclipseAugments.ASTNode_generatedBy.get(internalNode) != null);
/* 520 */       if (isGenerated) domNode.getClass().getField("$isGenerated").set(domNode, Boolean.valueOf(true)); 
/*     */     }
/*     */     
/*     */     public static void setIsGeneratedFlagForName(Name name, Object internalNode) throws Exception {
/* 524 */       if (internalNode instanceof ASTNode) {
/* 525 */         boolean isGenerated = (EclipseAugments.ASTNode_generatedBy.get((ASTNode)internalNode) != null);
/* 526 */         if (isGenerated) name.getClass().getField("$isGenerated").set(name, Boolean.valueOf(true)); 
/*     */       } 
/*     */     }
/*     */     
/*     */     public static RewriteEvent[] listRewriteHandleGeneratedMethods(RewriteEvent parent) {
/* 531 */       RewriteEvent[] children = parent.getChildren();
/* 532 */       List<RewriteEvent> newChildren = new ArrayList<RewriteEvent>();
/* 533 */       List<RewriteEvent> modifiedChildren = new ArrayList<RewriteEvent>();
/* 534 */       for (int i = 0; i < children.length; i++) {
/* 535 */         RewriteEvent child = children[i];
/* 536 */         boolean isGenerated = isGenerated((ASTNode)child.getOriginalValue());
/* 537 */         if (isGenerated) {
/* 538 */           boolean isReplacedOrRemoved = (child.getChangeKind() == 4 || child.getChangeKind() == 2);
/* 539 */           boolean convertingFromMethod = child.getOriginalValue() instanceof MethodDeclaration;
/* 540 */           if (isReplacedOrRemoved && convertingFromMethod && child.getNewValue() != null) {
/* 541 */             modifiedChildren.add(new NodeRewriteEvent(null, child.getNewValue()));
/*     */           }
/*     */         } else {
/* 544 */           newChildren.add(child);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 549 */       newChildren.addAll(modifiedChildren);
/* 550 */       return (RewriteEvent[])newChildren.toArray(new RewriteEvent[newChildren.size()]);
/*     */     }
/*     */     
/*     */     public static int getTokenEndOffsetFixed(TokenScanner scanner, int token, int startOffset, Object domNode) throws CoreException {
/* 554 */       boolean isGenerated = false;
/*     */       try {
/* 556 */         isGenerated = ((Boolean)domNode.getClass().getField("$isGenerated").get(domNode)).booleanValue();
/* 557 */       } catch (Exception exception) {}
/*     */ 
/*     */       
/* 560 */       if (isGenerated) return -1; 
/* 561 */       return scanner.getTokenEndOffset(token, startOffset);
/*     */     }
/*     */     
/*     */     public static IMethod[] removeGeneratedMethods(IMethod[] methods) throws Exception {
/* 565 */       List<IMethod> result = new ArrayList<IMethod>();
/* 566 */       for (IMethod m : methods) {
/* 567 */         if (m.getNameRange().getLength() > 0 && !m.getNameRange().equals(m.getSourceRange())) result.add(m); 
/*     */       } 
/* 569 */       return (result.size() == methods.length) ? methods : (IMethod[])result.toArray(new IMethod[result.size()]);
/*     */     }
/*     */     
/*     */     public static SearchMatch[] removeGenerated(SearchMatch[] returnValue) {
/* 573 */       List<SearchMatch> result = new ArrayList<SearchMatch>();
/* 574 */       for (int j = 0; j < returnValue.length; j++) {
/* 575 */         SearchMatch searchResult = returnValue[j];
/* 576 */         if (searchResult.getElement() instanceof IField) {
/* 577 */           IField field = (IField)searchResult.getElement();
/*     */ 
/*     */ 
/*     */           
/* 581 */           IAnnotation annotation = field.getAnnotation("Generated");
/* 582 */           if (annotation != null) {
/*     */             continue;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 588 */         result.add(searchResult);
/*     */       } 
/* 590 */       return (SearchMatch[])result.toArray(new SearchMatch[result.size()]);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static SearchResultGroup[] createFakeSearchResult(SearchResultGroup[] returnValue, Object processor) throws Exception {
/* 598 */       if (returnValue == null || returnValue.length == 0) {
/*     */         
/* 600 */         Field declaredField = processor.getClass().getDeclaredField("fField");
/* 601 */         if (declaredField != null) {
/* 602 */           declaredField.setAccessible(true);
/* 603 */           SourceField fField = (SourceField)declaredField.get(processor);
/* 604 */           IAnnotation dataAnnotation = fField.getDeclaringType().getAnnotation("Data");
/* 605 */           if (dataAnnotation != null)
/*     */           {
/* 607 */             return new SearchResultGroup[] { new SearchResultGroup(null, new SearchMatch[1]) };
/*     */           }
/*     */         } 
/*     */       } 
/* 611 */       return returnValue;
/*     */     }
/*     */     
/*     */     public static SimpleName[] removeGeneratedSimpleNames(SimpleName[] in) throws Exception {
/* 615 */       Field f = SimpleName.class.getField("$isGenerated");
/*     */       
/* 617 */       int count = 0;
/* 618 */       for (i = 0; i < in.length; i++) {
/* 619 */         if (in[i] == null || !((Boolean)f.get(in[i])).booleanValue()) count++; 
/*     */       } 
/* 621 */       if (count == in.length) return in; 
/* 622 */       SimpleName[] newSimpleNames = new SimpleName[count];
/* 623 */       count = 0;
/* 624 */       for (int i = 0; i < in.length; i++) {
/* 625 */         if (in[i] == null || !((Boolean)f.get(in[i])).booleanValue()) newSimpleNames[count++] = in[i]; 
/*     */       } 
/* 627 */       return newSimpleNames;
/*     */     }
/*     */ 
/*     */     
/*     */     public static Annotation[] convertAnnotations(Annotation[] out, IAnnotatable annotatable) {
/*     */       IAnnotation[] in;
/*     */       try {
/* 634 */         in = annotatable.getAnnotations();
/* 635 */       } catch (Exception e) {
/* 636 */         return out;
/*     */       } 
/*     */       
/* 639 */       if (out == null) return null; 
/* 640 */       int toWrite = 0;
/*     */       
/* 642 */       for (idx = 0; idx < out.length; idx++) {
/* 643 */         String oName = new String((out[idx]).type.getLastToken());
/* 644 */         boolean found = false;
/* 645 */         for (IAnnotation i : in) {
/* 646 */           String name = i.getElementName();
/* 647 */           int li = name.lastIndexOf('.');
/* 648 */           if (li > -1) name = name.substring(li + 1); 
/* 649 */           if (name.equals(oName)) {
/* 650 */             found = true;
/*     */             break;
/*     */           } 
/*     */         } 
/* 654 */         if (!found) { out[idx] = null; }
/* 655 */         else { toWrite++; }
/*     */       
/*     */       } 
/* 658 */       Annotation[] replace = out;
/* 659 */       if (toWrite < out.length) {
/* 660 */         replace = new Annotation[toWrite];
/* 661 */         int idx = 0;
/* 662 */         for (int i = 0; i < out.length; i++) {
/* 663 */           if (out[i] != null) {
/* 664 */             replace[idx++] = out[i];
/*     */           }
/*     */         } 
/*     */       } 
/* 668 */       return replace;
/*     */     }
/*     */   }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\launch\PatchFixesHider.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */