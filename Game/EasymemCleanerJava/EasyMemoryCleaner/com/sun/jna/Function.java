/*     */ package com.sun.jna;
/*     */ 
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Collections;
/*     */ import java.util.Map;
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
/*     */ public class Function
/*     */   extends Pointer
/*     */ {
/*     */   public static final int MAX_NARGS = 256;
/*     */   public static final int C_CONVENTION = 0;
/*     */   public static final int ALT_CONVENTION = 63;
/*     */   private static final int MASK_CC = 63;
/*     */   public static final int THROW_LAST_ERROR = 64;
/*  63 */   static final Integer INTEGER_TRUE = new Integer(-1);
/*  64 */   static final Integer INTEGER_FALSE = new Integer(false);
/*     */ 
/*     */   
/*     */   private NativeLibrary library;
/*     */ 
/*     */   
/*     */   private final String functionName;
/*     */ 
/*     */   
/*     */   final String encoding;
/*     */   
/*     */   final int callFlags;
/*     */   
/*     */   final Map options;
/*     */   
/*     */   static final String OPTION_INVOKING_METHOD = "invoking-method";
/*     */ 
/*     */   
/*  82 */   public static Function getFunction(String libraryName, String functionName) { return NativeLibrary.getInstance(libraryName).getFunction(functionName); }
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
/* 103 */   public static Function getFunction(String libraryName, String functionName, int callFlags) { return NativeLibrary.getInstance(libraryName).getFunction(functionName, callFlags, null); }
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
/* 127 */   public static Function getFunction(String libraryName, String functionName, int callFlags, String encoding) { return NativeLibrary.getInstance(libraryName).getFunction(functionName, callFlags, encoding); }
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
/* 142 */   public static Function getFunction(Pointer p) { return getFunction(p, 0); }
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
/* 160 */   public static Function getFunction(Pointer p, int callFlags) { return new Function(p, callFlags, null); }
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
/* 175 */   private static final VarArgsChecker IS_VARARGS = VarArgsChecker.create();
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
/*     */   Function(NativeLibrary library, String functionName, int callFlags, String encoding) {
/* 197 */     checkCallingConvention(callFlags & 0x3F);
/* 198 */     if (functionName == null)
/* 199 */       throw new NullPointerException("Function name must not be null"); 
/* 200 */     this.library = library;
/* 201 */     this.functionName = functionName;
/* 202 */     this.callFlags = callFlags;
/* 203 */     this.options = library.options;
/* 204 */     this
/* 205 */       .encoding = (encoding != null) ? encoding : Native.getDefaultStringEncoding();
/*     */     try {
/* 207 */       this.peer = library.getSymbolAddress(functionName);
/*     */     }
/* 209 */     catch (UnsatisfiedLinkError e) {
/* 210 */       throw new UnsatisfiedLinkError("Error looking up function '" + functionName + "': " + e
/*     */           
/* 212 */           .getMessage());
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Function(Pointer functionAddress, int callFlags, String encoding) {
/* 232 */     checkCallingConvention(callFlags & 0x3F);
/* 233 */     if (functionAddress == null || functionAddress.peer == 0L)
/*     */     {
/* 235 */       throw new NullPointerException("Function address may not be null");
/*     */     }
/* 237 */     this.functionName = functionAddress.toString();
/* 238 */     this.callFlags = callFlags;
/* 239 */     this.peer = functionAddress.peer;
/* 240 */     this.options = Collections.EMPTY_MAP;
/* 241 */     this
/* 242 */       .encoding = (encoding != null) ? encoding : Native.getDefaultStringEncoding();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkCallingConvention(int convention) throws IllegalArgumentException {
/* 248 */     if ((convention & 0x3F) != convention) {
/* 249 */       throw new IllegalArgumentException("Unrecognized calling convention: " + convention);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 255 */   public String getName() { return this.functionName; }
/*     */ 
/*     */ 
/*     */   
/* 259 */   public int getCallingConvention() { return this.callFlags & 0x3F; }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 266 */   public Object invoke(Class returnType, Object[] inArgs) { return invoke(returnType, inArgs, this.options); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object invoke(Class returnType, Object[] inArgs, Map options) {
/* 273 */     Method invokingMethod = (Method)options.get("invoking-method");
/* 274 */     Class[] paramTypes = (invokingMethod != null) ? invokingMethod.getParameterTypes() : null;
/* 275 */     return invoke(invokingMethod, paramTypes, returnType, inArgs, options);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   Object invoke(Method invokingMethod, Class[] paramTypes, Class returnType, Object[] inArgs, Map options) {
/* 286 */     Object[] args = new Object[0];
/* 287 */     if (inArgs != null) {
/* 288 */       if (inArgs.length > 256) {
/* 289 */         throw new UnsupportedOperationException("Maximum argument count is 256");
/*     */       }
/* 291 */       args = new Object[inArgs.length];
/* 292 */       System.arraycopy(inArgs, 0, args, 0, args.length);
/*     */     } 
/*     */ 
/*     */     
/* 296 */     TypeMapper mapper = (TypeMapper)options.get("type-mapper");
/* 297 */     boolean allowObjects = Boolean.TRUE.equals(options.get("allow-objects"));
/* 298 */     boolean isVarArgs = (args.length > 0 && invokingMethod != null) ? isVarArgs(invokingMethod) : 0;
/* 299 */     for (int i = 0; i < args.length; i++) {
/*     */ 
/*     */       
/* 302 */       Class paramType = (invokingMethod != null) ? ((isVarArgs && i >= paramTypes.length - 1) ? paramTypes[paramTypes.length - 1].getComponentType() : paramTypes[i]) : null;
/*     */ 
/*     */       
/* 305 */       args[i] = convertArgument(args, i, invokingMethod, mapper, allowObjects, paramType);
/*     */     } 
/*     */ 
/*     */     
/* 309 */     Class nativeReturnType = returnType;
/* 310 */     FromNativeConverter resultConverter = null;
/* 311 */     if (NativeMapped.class.isAssignableFrom(returnType)) {
/* 312 */       NativeMappedConverter tc = NativeMappedConverter.getInstance(returnType);
/* 313 */       resultConverter = tc;
/* 314 */       nativeReturnType = tc.nativeType();
/*     */     }
/* 316 */     else if (mapper != null) {
/* 317 */       resultConverter = mapper.getFromNativeConverter(returnType);
/* 318 */       if (resultConverter != null) {
/* 319 */         nativeReturnType = resultConverter.nativeType();
/*     */       }
/*     */     } 
/*     */     
/* 323 */     Object result = invoke(args, nativeReturnType, allowObjects);
/*     */ 
/*     */     
/* 326 */     if (resultConverter != null) {
/*     */       FromNativeContext context;
/* 328 */       if (invokingMethod != null) {
/* 329 */         context = new MethodResultContext(returnType, this, inArgs, invokingMethod);
/*     */       } else {
/* 331 */         context = new FunctionResultContext(returnType, this, inArgs);
/*     */       } 
/* 333 */       result = resultConverter.fromNative(result, context);
/*     */     } 
/*     */ 
/*     */     
/* 337 */     if (inArgs != null) {
/* 338 */       for (int i = 0; i < inArgs.length; i++) {
/* 339 */         Object inArg = inArgs[i];
/* 340 */         if (inArg != null)
/*     */         {
/* 342 */           if (inArg instanceof Structure) {
/* 343 */             if (!(inArg instanceof Structure.ByValue)) {
/* 344 */               ((Structure)inArg).autoRead();
/*     */             }
/*     */           }
/* 347 */           else if (args[i] instanceof PostCallRead) {
/* 348 */             ((PostCallRead)args[i]).read();
/* 349 */             if (args[i] instanceof PointerArray) {
/* 350 */               PointerArray array = (PointerArray)args[i];
/* 351 */               if (ByReference[].class.isAssignableFrom(inArg.getClass())) {
/* 352 */                 Class type = inArg.getClass().getComponentType();
/* 353 */                 Structure[] ss = (Structure[])inArg;
/* 354 */                 for (int si = 0; si < ss.length; si++) {
/* 355 */                   Pointer p = array.getPointer((Pointer.SIZE * si));
/* 356 */                   ss[si] = Structure.updateStructureByReference(type, ss[si], p);
/*     */                 }
/*     */               
/*     */               } 
/*     */             } 
/* 361 */           } else if (Structure[].class.isAssignableFrom(inArg.getClass())) {
/* 362 */             Structure.autoRead((Structure[])inArg);
/*     */           } 
/*     */         }
/*     */       } 
/*     */     }
/* 367 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   Object invoke(Object[] args, Class returnType, boolean allowObjects) {
/* 372 */     Object result = null;
/* 373 */     if (returnType == null || returnType == void.class || returnType == Void.class) {
/* 374 */       Native.invokeVoid(this.peer, this.callFlags, args);
/* 375 */       result = null;
/*     */     }
/* 377 */     else if (returnType == boolean.class || returnType == Boolean.class) {
/* 378 */       result = valueOf((Native.invokeInt(this.peer, this.callFlags, args) != 0));
/*     */     }
/* 380 */     else if (returnType == byte.class || returnType == Byte.class) {
/* 381 */       result = new Byte((byte)Native.invokeInt(this.peer, this.callFlags, args));
/*     */     }
/* 383 */     else if (returnType == short.class || returnType == Short.class) {
/* 384 */       result = new Short((short)Native.invokeInt(this.peer, this.callFlags, args));
/*     */     }
/* 386 */     else if (returnType == char.class || returnType == Character.class) {
/* 387 */       result = new Character((char)Native.invokeInt(this.peer, this.callFlags, args));
/*     */     }
/* 389 */     else if (returnType == int.class || returnType == Integer.class) {
/* 390 */       result = new Integer(Native.invokeInt(this.peer, this.callFlags, args));
/*     */     }
/* 392 */     else if (returnType == long.class || returnType == Long.class) {
/* 393 */       result = new Long(Native.invokeLong(this.peer, this.callFlags, args));
/*     */     }
/* 395 */     else if (returnType == float.class || returnType == Float.class) {
/* 396 */       result = new Float(Native.invokeFloat(this.peer, this.callFlags, args));
/*     */     }
/* 398 */     else if (returnType == double.class || returnType == Double.class) {
/* 399 */       result = new Double(Native.invokeDouble(this.peer, this.callFlags, args));
/*     */     }
/* 401 */     else if (returnType == String.class) {
/* 402 */       result = invokeString(this.callFlags, args, false);
/*     */     }
/* 404 */     else if (returnType == WString.class) {
/* 405 */       String s = invokeString(this.callFlags, args, true);
/* 406 */       if (s != null) {
/* 407 */         result = new WString(s);
/*     */       }
/*     */     } else {
/* 410 */       if (Pointer.class.isAssignableFrom(returnType)) {
/* 411 */         return invokePointer(this.callFlags, args);
/*     */       }
/* 413 */       if (Structure.class.isAssignableFrom(returnType)) {
/* 414 */         if (Structure.ByValue.class.isAssignableFrom(returnType)) {
/*     */           
/* 416 */           Structure s = Native.invokeStructure(this.peer, this.callFlags, args, 
/* 417 */               Structure.newInstance(returnType));
/* 418 */           s.autoRead();
/* 419 */           result = s;
/*     */         } else {
/*     */           
/* 422 */           result = invokePointer(this.callFlags, args);
/* 423 */           if (result != null) {
/* 424 */             Structure s = Structure.newInstance(returnType, (Pointer)result);
/* 425 */             s.conditionalAutoRead();
/* 426 */             result = s;
/*     */           }
/*     */         
/*     */         } 
/* 430 */       } else if (Callback.class.isAssignableFrom(returnType)) {
/* 431 */         result = invokePointer(this.callFlags, args);
/* 432 */         if (result != null) {
/* 433 */           result = CallbackReference.getCallback(returnType, (Pointer)result);
/*     */         }
/*     */       }
/* 436 */       else if (returnType == String[].class) {
/* 437 */         Pointer p = invokePointer(this.callFlags, args);
/* 438 */         if (p != null) {
/* 439 */           String[] arrayOfString = p.getStringArray(0L, this.encoding);
/*     */         }
/*     */       }
/* 442 */       else if (returnType == WString[].class) {
/* 443 */         Pointer p = invokePointer(this.callFlags, args);
/* 444 */         if (p != null) {
/* 445 */           String[] arr = p.getWideStringArray(0L);
/* 446 */           WString[] warr = new WString[arr.length];
/* 447 */           for (int i = 0; i < arr.length; i++) {
/* 448 */             warr[i] = new WString(arr[i]);
/*     */           }
/* 450 */           WString[] arrayOfWString = warr;
/*     */         }
/*     */       
/* 453 */       } else if (returnType == Pointer[].class) {
/* 454 */         Pointer p = invokePointer(this.callFlags, args);
/* 455 */         if (p != null) {
/* 456 */           Pointer[] arrayOfPointer = p.getPointerArray(0L);
/*     */         }
/*     */       }
/* 459 */       else if (allowObjects) {
/* 460 */         result = Native.invokeObject(this.peer, this.callFlags, args);
/* 461 */         if (result != null && 
/* 462 */           !returnType.isAssignableFrom(result.getClass())) {
/* 463 */           throw new ClassCastException("Return type " + returnType + " does not match result " + result
/*     */               
/* 465 */               .getClass());
/*     */         }
/*     */       } else {
/*     */         
/* 469 */         throw new IllegalArgumentException("Unsupported return type " + returnType + " in function " + 
/*     */             
/* 471 */             getName());
/*     */       } 
/* 473 */     }  return result;
/*     */   }
/*     */   
/*     */   private Pointer invokePointer(int callFlags, Object[] args) {
/* 477 */     long ptr = Native.invokePointer(this.peer, callFlags, args);
/* 478 */     return (ptr == 0L) ? null : new Pointer(ptr);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object convertArgument(Object[] args, int index, Method invokingMethod, TypeMapper mapper, boolean allowObjects, Class expectedType) {
/* 484 */     Object arg = args[index];
/* 485 */     if (arg != null) {
/* 486 */       Class type = arg.getClass();
/* 487 */       ToNativeConverter converter = null;
/* 488 */       if (NativeMapped.class.isAssignableFrom(type)) {
/* 489 */         converter = NativeMappedConverter.getInstance(type);
/*     */       }
/* 491 */       else if (mapper != null) {
/* 492 */         converter = mapper.getToNativeConverter(type);
/*     */       } 
/* 494 */       if (converter != null) {
/*     */         ToNativeContext context;
/* 496 */         if (invokingMethod != null) {
/* 497 */           context = new MethodParameterContext(this, args, index, invokingMethod);
/*     */         } else {
/*     */           
/* 500 */           context = new FunctionParameterContext(this, args, index);
/*     */         } 
/* 502 */         arg = converter.toNative(arg, context);
/*     */       } 
/*     */     } 
/* 505 */     if (arg == null || isPrimitiveArray(arg.getClass())) {
/* 506 */       return arg;
/*     */     }
/* 508 */     Class argClass = arg.getClass();
/*     */     
/* 510 */     if (arg instanceof Structure) {
/* 511 */       Structure struct = (Structure)arg;
/* 512 */       struct.autoWrite();
/* 513 */       if (struct instanceof Structure.ByValue) {
/*     */         
/* 515 */         Class ptype = struct.getClass();
/* 516 */         if (invokingMethod != null) {
/* 517 */           Class[] ptypes = invokingMethod.getParameterTypes();
/* 518 */           if (IS_VARARGS.isVarArgs(invokingMethod)) {
/* 519 */             if (index < ptypes.length - 1) {
/* 520 */               ptype = ptypes[index];
/*     */             } else {
/*     */               
/* 523 */               Class etype = ptypes[ptypes.length - 1].getComponentType();
/* 524 */               if (etype != Object.class) {
/* 525 */                 ptype = etype;
/*     */               }
/*     */             } 
/*     */           } else {
/*     */             
/* 530 */             ptype = ptypes[index];
/*     */           } 
/*     */         } 
/* 533 */         if (Structure.ByValue.class.isAssignableFrom(ptype)) {
/* 534 */           return struct;
/*     */         }
/*     */       } 
/* 537 */       return struct.getPointer();
/*     */     } 
/*     */     
/* 540 */     if (arg instanceof Callback) {
/* 541 */       return CallbackReference.getFunctionPointer((Callback)arg);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 547 */     if (arg instanceof String) {
/* 548 */       return (new NativeString((String)arg, false)).getPointer();
/*     */     }
/*     */     
/* 551 */     if (arg instanceof WString) {
/* 552 */       return (new NativeString(arg.toString(), true)).getPointer();
/*     */     }
/*     */ 
/*     */     
/* 556 */     if (arg instanceof Boolean) {
/* 557 */       return Boolean.TRUE.equals(arg) ? INTEGER_TRUE : INTEGER_FALSE;
/*     */     }
/* 559 */     if (String[].class == argClass) {
/* 560 */       return new StringArray((String[])arg, this.encoding);
/*     */     }
/* 562 */     if (WString[].class == argClass) {
/* 563 */       return new StringArray((WString[])arg);
/*     */     }
/* 565 */     if (Pointer[].class == argClass) {
/* 566 */       return new PointerArray((Pointer[])arg);
/*     */     }
/* 568 */     if (NativeMapped[].class.isAssignableFrom(argClass)) {
/* 569 */       return new NativeMappedArray((NativeMapped[])arg);
/*     */     }
/* 571 */     if (Structure[].class.isAssignableFrom(argClass)) {
/*     */ 
/*     */       
/* 574 */       Structure[] ss = (Structure[])arg;
/* 575 */       Class type = argClass.getComponentType();
/* 576 */       boolean byRef = Structure.ByReference.class.isAssignableFrom(type);
/* 577 */       if (expectedType != null && 
/* 578 */         !ByReference[].class.isAssignableFrom(expectedType)) {
/* 579 */         if (byRef) {
/* 580 */           throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + index + " but array of " + type + " was passed");
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 585 */         for (int i = 0; i < ss.length; i++) {
/* 586 */           if (ss[i] instanceof Structure.ByReference) {
/* 587 */             throw new IllegalArgumentException("Function " + getName() + " declared Structure[] at parameter " + index + " but element " + i + " is of Structure.ByReference type");
/*     */           }
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 595 */       if (byRef) {
/* 596 */         Structure.autoWrite(ss);
/* 597 */         Pointer[] pointers = new Pointer[ss.length + 1];
/* 598 */         for (int i = 0; i < ss.length; i++) {
/* 599 */           pointers[i] = (ss[i] != null) ? ss[i].getPointer() : null;
/*     */         }
/* 601 */         return new PointerArray(pointers);
/*     */       } 
/* 603 */       if (ss.length == 0) {
/* 604 */         throw new IllegalArgumentException("Structure array must have non-zero length");
/*     */       }
/* 606 */       if (ss[false] == null) {
/* 607 */         Structure.newInstance(type).toArray(ss);
/* 608 */         return ss[0].getPointer();
/*     */       } 
/*     */       
/* 611 */       Structure.autoWrite(ss);
/* 612 */       return ss[0].getPointer();
/*     */     } 
/*     */     
/* 615 */     if (argClass.isArray()) {
/* 616 */       throw new IllegalArgumentException("Unsupported array argument type: " + argClass
/* 617 */           .getComponentType());
/*     */     }
/* 619 */     if (allowObjects) {
/* 620 */       return arg;
/*     */     }
/* 622 */     if (!Native.isSupportedNativeType(arg.getClass())) {
/* 623 */       throw new IllegalArgumentException("Unsupported argument type " + arg
/* 624 */           .getClass().getName() + " at parameter " + index + " of function " + 
/*     */           
/* 626 */           getName());
/*     */     }
/* 628 */     return arg;
/*     */   }
/*     */   
/*     */   private boolean isPrimitiveArray(Class argClass) {
/* 632 */     return (argClass.isArray() && argClass
/* 633 */       .getComponentType().isPrimitive());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 643 */   public void invoke(Object[] args) { invoke(Void.class, args); }
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
/*     */   private String invokeString(int callFlags, Object[] args, boolean wide) {
/* 658 */     Pointer ptr = invokePointer(callFlags, args);
/* 659 */     String s = null;
/* 660 */     if (ptr != null) {
/* 661 */       if (wide) {
/* 662 */         s = ptr.getWideString(0L);
/*     */       } else {
/*     */         
/* 665 */         s = ptr.getString(0L, this.encoding);
/*     */       } 
/*     */     }
/* 668 */     return s;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 673 */     if (this.library != null) {
/* 674 */       return "native function " + this.functionName + "(" + this.library.getName() + ")@0x" + 
/* 675 */         Long.toHexString(this.peer);
/*     */     }
/* 677 */     return "native function@0x" + Long.toHexString(this.peer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 684 */   public Object invokeObject(Object[] args) { return invoke(Object.class, args); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 691 */   public Pointer invokePointer(Object[] args) { return (Pointer)invoke(Pointer.class, args); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String invokeString(Object[] args, boolean wide) {
/* 702 */     Object o = invoke(wide ? WString.class : String.class, args);
/* 703 */     return (o != null) ? o.toString() : null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 710 */   public int invokeInt(Object[] args) { return ((Integer)invoke(Integer.class, args)).intValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 716 */   public long invokeLong(Object[] args) { return ((Long)invoke(Long.class, args)).longValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 722 */   public float invokeFloat(Object[] args) { return ((Float)invoke(Float.class, args)).floatValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 728 */   public double invokeDouble(Object[] args) { return ((Double)invoke(Double.class, args)).doubleValue(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 734 */   public void invokeVoid(Object[] args) { invoke(Void.class, args); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 741 */     if (o == this) return true; 
/* 742 */     if (o == null) return false; 
/* 743 */     if (o.getClass() == getClass()) {
/* 744 */       Function other = (Function)o;
/* 745 */       return (other.callFlags == this.callFlags && other.options
/* 746 */         .equals(this.options) && other.peer == this.peer);
/*     */     } 
/*     */     
/* 749 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 756 */   public int hashCode() { return this.callFlags + this.options.hashCode() + super.hashCode(); }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Object[] concatenateVarArgs(Object[] inArgs) {
/* 766 */     if (inArgs != null && inArgs.length > 0) {
/* 767 */       Object lastArg = inArgs[inArgs.length - 1];
/* 768 */       Class argType = (lastArg != null) ? lastArg.getClass() : null;
/* 769 */       if (argType != null && argType.isArray()) {
/* 770 */         Object[] varArgs = (Object[])lastArg;
/* 771 */         Object[] fullArgs = new Object[inArgs.length + varArgs.length];
/* 772 */         System.arraycopy(inArgs, 0, fullArgs, 0, inArgs.length - 1);
/* 773 */         System.arraycopy(varArgs, 0, fullArgs, inArgs.length - 1, varArgs.length);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 779 */         fullArgs[fullArgs.length - 1] = null;
/* 780 */         inArgs = fullArgs;
/*     */       } 
/*     */     } 
/* 783 */     return inArgs;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 788 */   static boolean isVarArgs(Method m) { return IS_VARARGS.isVarArgs(m); }
/*     */   
/*     */   public static interface PostCallRead {
/*     */     void read(); }
/*     */   
/*     */   private static class NativeMappedArray extends Memory implements PostCallRead { public NativeMappedArray(NativeMapped[] arg) {
/* 794 */       super(Native.getNativeSize(arg.getClass(), arg));
/* 795 */       this.original = arg;
/* 796 */       setValue(0L, this.original, this.original.getClass());
/*     */     }
/*     */     private final NativeMapped[] original;
/* 799 */     public void read() { getValue(0L, this.original.getClass(), this.original); } }
/*     */ 
/*     */   
/*     */   private static class PointerArray extends Memory implements PostCallRead {
/*     */     private final Pointer[] original;
/*     */     
/*     */     public PointerArray(Pointer[] arg) {
/* 806 */       super((Pointer.SIZE * (arg.length + 1)));
/* 807 */       this.original = arg;
/* 808 */       for (int i = 0; i < arg.length; i++) {
/* 809 */         setPointer((i * Pointer.SIZE), arg[i]);
/*     */       }
/* 811 */       setPointer((Pointer.SIZE * arg.length), null);
/*     */     }
/*     */     
/* 814 */     public void read() { read(0L, this.original, 0, this.original.length); }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 820 */   static Boolean valueOf(boolean b) { return b ? Boolean.TRUE : Boolean.FALSE; }
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\com\sun\jna\Function.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */