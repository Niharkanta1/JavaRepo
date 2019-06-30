/*     */ package lombok;
/*     */ 
/*     */ import java.util.List;
/*     */ import lombok.core.configuration.CallSuperType;
/*     */ import lombok.core.configuration.ConfigurationKey;
/*     */ import lombok.core.configuration.FlagUsageType;
/*     */ import lombok.core.configuration.NullCheckExceptionType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ConfigurationKeys
/*     */ {
/*     */   @Deprecated
/*  51 */   public static final ConfigurationKey<Boolean> ADD_GENERATED_ANNOTATIONS = new ConfigurationKey<Boolean>("lombok.addGeneratedAnnotation", "Generate @javax.annotation.Generated on all generated code (default: true). Deprecated, use 'lombok.addJavaxGeneratedAnnotation' instead.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*  58 */   public static final ConfigurationKey<Boolean> ADD_JAVAX_GENERATED_ANNOTATIONS = new ConfigurationKey<Boolean>("lombok.addJavaxGeneratedAnnotation", "Generate @javax.annotation.Generated on all generated code (default: follow lombok.addGeneratedAnnotation).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*  65 */   public static final ConfigurationKey<Boolean> ADD_LOMBOK_GENERATED_ANNOTATIONS = new ConfigurationKey<Boolean>("lombok.addLombokGeneratedAnnotation", "Generate @lombok.Generated on all generated code (default: false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   public static final ConfigurationKey<Boolean> ADD_FINDBUGS_SUPPRESSWARNINGS_ANNOTATIONS = new ConfigurationKey<Boolean>("lombok.extern.findbugs.addSuppressFBWarnings", "Generate @edu.umd.cs.findbugs.annotations.SuppressFBWArnings on all generated code (default: false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  83 */   public static final ConfigurationKey<FlagUsageType> ANY_CONSTRUCTOR_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.anyConstructor.flagUsage", "Emit a warning or error if any of the XxxArgsConstructor annotations are used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  93 */   public static final ConfigurationKey<Boolean> ANY_CONSTRUCTOR_SUPPRESS_CONSTRUCTOR_PROPERTIES = new ConfigurationKey<Boolean>("lombok.anyConstructor.suppressConstructorProperties", "Suppress the generation of @ConstructorProperties for generated constructors (default: false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 100 */   public static final ConfigurationKey<FlagUsageType> ALL_ARGS_CONSTRUCTOR_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.allArgsConstructor.flagUsage", "Emit a warning or error if @AllArgsConstructor is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 107 */   public static final ConfigurationKey<FlagUsageType> NO_ARGS_CONSTRUCTOR_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.noArgsConstructor.flagUsage", "Emit a warning or error if @NoArgsConstructor is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 114 */   public static final ConfigurationKey<FlagUsageType> REQUIRED_ARGS_CONSTRUCTOR_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.requiredArgsConstructor.flagUsage", "Emit a warning or error if @RequiredArgsConstructor is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 125 */   public static final ConfigurationKey<FlagUsageType> DATA_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.data.flagUsage", "Emit a warning or error if @Data is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 134 */   public static final ConfigurationKey<FlagUsageType> VALUE_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.value.flagUsage", "Emit a warning or error if @Value is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 143 */   public static final ConfigurationKey<FlagUsageType> GETTER_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.getter.flagUsage", "Emit a warning or error if @Getter is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 150 */   public static final ConfigurationKey<FlagUsageType> GETTER_LAZY_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.getter.lazy.flagUsage", "Emit a warning or error if @Getter(lazy=true) is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 158 */   public static final ConfigurationKey<Boolean> GETTER_CONSEQUENT_BOOLEAN = new ConfigurationKey<Boolean>("lombok.getter.noIsPrefix", "If true, generate and use getFieldName() for boolean getters instead of isFieldName().")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 167 */   public static final ConfigurationKey<FlagUsageType> SETTER_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.setter.flagUsage", "Emit a warning or error if @Setter is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 176 */   public static final ConfigurationKey<Boolean> EQUALS_AND_HASH_CODE_DO_NOT_USE_GETTERS = new ConfigurationKey<Boolean>("lombok.equalsAndHashCode.doNotUseGetters", "Don't call the getters but use the fields directly in the generated equals and hashCode method (default = false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 183 */   public static final ConfigurationKey<CallSuperType> EQUALS_AND_HASH_CODE_CALL_SUPER = new ConfigurationKey<CallSuperType>("lombok.equalsAndHashCode.callSuper", "When generating equals and hashCode for classes that don't extend Object, either automatically take into account superclass implementation (call), or don't (skip), or warn and don't (warn). (default = warn).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 190 */   public static final ConfigurationKey<FlagUsageType> EQUALS_AND_HASH_CODE_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.equalsAndHashCode.flagUsage", "Emit a warning or error if @EqualsAndHashCode is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 199 */   public static final ConfigurationKey<Boolean> TO_STRING_DO_NOT_USE_GETTERS = new ConfigurationKey<Boolean>("lombok.toString.doNotUseGetters", "Don't call the getters but use the fields directly in the generated toString method (default = false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 206 */   public static final ConfigurationKey<FlagUsageType> TO_STRING_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.toString.flagUsage", "Emit a warning or error if @ToString is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 213 */   public static final ConfigurationKey<Boolean> TO_STRING_INCLUDE_FIELD_NAMES = new ConfigurationKey<Boolean>("lombok.toString.includeFieldNames", "Include the field names in the generated toString method (default = true).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 222 */   public static final ConfigurationKey<FlagUsageType> BUILDER_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.builder.flagUsage", "Emit a warning or error if @Builder is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 232 */   public static final ConfigurationKey<Boolean> SINGULAR_USE_GUAVA = new ConfigurationKey<Boolean>("lombok.singular.useGuava", "Generate backing immutable implementations for @Singular on java.util.* types by using guava's ImmutableList, etc. Normally java.util's mutable types are used and wrapped to make them immutable.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 239 */   public static final ConfigurationKey<Boolean> SINGULAR_AUTO = new ConfigurationKey<Boolean>("lombok.singular.auto", "If true (default): Automatically singularize the assumed-to-be-plural name of your variable/parameter when using {@code @Singular}.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 250 */   public static final ConfigurationKey<FlagUsageType> CLEANUP_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.cleanup.flagUsage", "Emit a warning or error if @Cleanup is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 259 */   public static final ConfigurationKey<FlagUsageType> DELEGATE_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.delegate.flagUsage", "Emit a warning or error if @Delegate is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 268 */   public static final ConfigurationKey<NullCheckExceptionType> NON_NULL_EXCEPTION_TYPE = new ConfigurationKey<NullCheckExceptionType>("lombok.nonNull.exceptionType", "The type of the exception to throw if a passed-in argument is null (Default: NullPointerException).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 277 */   public static final ConfigurationKey<FlagUsageType> NON_NULL_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.nonNull.flagUsage", "Emit a warning or error if @NonNull is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 286 */   public static final ConfigurationKey<FlagUsageType> SNEAKY_THROWS_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.sneakyThrows.flagUsage", "Emit a warning or error if @SneakyThrows is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 295 */   public static final ConfigurationKey<FlagUsageType> SYNCHRONIZED_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.synchronized.flagUsage", "Emit a warning or error if @Synchronized is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 304 */   public static final ConfigurationKey<FlagUsageType> VAL_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.val.flagUsage", "Emit a warning or error if 'val' is used.") {  }
/* 305 */   ; public static final ConfigurationKey<FlagUsageType> VAR_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.var.flagUsage", "Emit a warning or error if 'var' is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 315 */   public static final ConfigurationKey<FlagUsageType> LOG_ANY_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.flagUsage", "Emit a warning or error if any of the log annotations is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 322 */   public static final ConfigurationKey<FlagUsageType> LOG_COMMONS_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.apacheCommons.flagUsage", "Emit a warning or error if @CommonsLog is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 329 */   public static final ConfigurationKey<FlagUsageType> LOG_JUL_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.javaUtilLogging.flagUsage", "Emit a warning or error if @Log is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 336 */   public static final ConfigurationKey<FlagUsageType> LOG_LOG4J_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.log4j.flagUsage", "Emit a warning or error if @Log4j is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 343 */   public static final ConfigurationKey<FlagUsageType> LOG_LOG4J2_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.log4j2.flagUsage", "Emit a warning or error if @Log4j2 is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 350 */   public static final ConfigurationKey<FlagUsageType> LOG_SLF4J_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.slf4j.flagUsage", "Emit a warning or error if @Slf4j is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 357 */   public static final ConfigurationKey<FlagUsageType> LOG_XSLF4J_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.xslf4j.flagUsage", "Emit a warning or error if @XSlf4j is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 364 */   public static final ConfigurationKey<FlagUsageType> LOG_JBOSSLOG_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.log.jbosslog.flagUsage", "Emit a warning or error if @JBossLog is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 371 */   public static final ConfigurationKey<String> LOG_ANY_FIELD_NAME = new ConfigurationKey<String>("lombok.log.fieldName", "Use this name for the generated logger fields (default: 'log').")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 380 */   public static final ConfigurationKey<Boolean> LOG_ANY_FIELD_IS_STATIC = new ConfigurationKey<Boolean>("lombok.log.fieldIsStatic", "Make the generated logger fields static (default: true).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 390 */   public static final ConfigurationKey<FlagUsageType> EXPERIMENTAL_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.experimental.flagUsage", "Emit a warning or error if an experimental feature is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 399 */   public static final ConfigurationKey<FlagUsageType> ACCESSORS_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.accessors.flagUsage", "Emit a warning or error if @Accessors is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 406 */   public static final ConfigurationKey<List<String>> ACCESSORS_PREFIX = new ConfigurationKey<List<String>>("lombok.accessors.prefix", "Strip this field prefix, like 'f' or 'm_', from the names of generated getters and setters.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 413 */   public static final ConfigurationKey<Boolean> ACCESSORS_CHAIN = new ConfigurationKey<Boolean>("lombok.accessors.chain", "Generate setters that return 'this' instead of 'void' (default: false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 420 */   public static final ConfigurationKey<Boolean> ACCESSORS_FLUENT = new ConfigurationKey<Boolean>("lombok.accessors.fluent", "Generate getters and setters using only the field name (no get/set prefix) (default: false).")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 429 */   public static final ConfigurationKey<FlagUsageType> EXTENSION_METHOD_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.extensionMethod.flagUsage", "Emit a warning or error if @ExtensionMethod is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 438 */   public static final ConfigurationKey<Boolean> FIELD_DEFAULTS_PRIVATE_EVERYWHERE = new ConfigurationKey<Boolean>("lombok.fieldDefaults.defaultPrivate", "If true, fields without any access modifier, in any file (lombok annotated or not) are marked as private. Use @PackagePrivate or an explicit modifier to override this.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 445 */   public static final ConfigurationKey<Boolean> FIELD_DEFAULTS_FINAL_EVERYWHERE = new ConfigurationKey<Boolean>("lombok.fieldDefaults.defaultFinal", "If true, fields, in any file (lombok annotated or not) are marked as final. Use @NonFinal to override this.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */   
/* 452 */   public static final ConfigurationKey<FlagUsageType> FIELD_DEFAULTS_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.fieldDefaults.flagUsage", "Emit a warning or error if @FieldDefaults is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 461 */   public static final ConfigurationKey<FlagUsageType> HELPER_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.helper.flagUsage", "Emit a warning or error if @Helper is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 472 */   public static final ConfigurationKey<FlagUsageType> ON_X_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.onX.flagUsage", "Emit a warning or error if onX is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 481 */   public static final ConfigurationKey<FlagUsageType> UTILITY_CLASS_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.utilityClass.flagUsage", "Emit a warning or error if @UtilityClass is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 490 */   public static final ConfigurationKey<FlagUsageType> WITHER_FLAG_USAGE = new ConfigurationKey<FlagUsageType>("lombok.wither.flagUsage", "Emit a warning or error if @Wither is used.")
/*     */     {
/*     */     
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 501 */   public static final ConfigurationKey<Boolean> STOP_BUBBLING = new ConfigurationKey<Boolean>("config.stopBubbling", "Tell the configuration system it should stop looking for other configuration files (default: false).") {
/*     */     
/*     */     };
/*     */ }


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lombok\ConfigurationKeys.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */