package net.miginfocom.layout;

import java.beans.Beans;
import java.beans.ExceptionListener;
import java.beans.Introspector;
import java.beans.PersistenceDelegate;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.io.OutputStream;
import java.util.IdentityHashMap;
import java.util.TreeSet;
import java.util.WeakHashMap;

public final class LayoutUtil {
  static final int INF = 2097051;
  
  static final int NOT_SET = -2147471302;
  
  public static final int MIN = 0;
  
  public static final int PREF = 1;
  
  public static final int MAX = 2;
  
  private static int eSz = 0;
  
  private static int globalDebugMillis = 0;
  
  private static ByteArrayOutputStream writeOutputStream = null;
  
  private static byte[] readBuf = null;
  
  private static final IdentityHashMap<Object, Object> SER_MAP = new IdentityHashMap(2);
  
  public static String getVersion() { return "3.7.4"; }
  
  public static int getGlobalDebugMillis() { return globalDebugMillis; }
  
  public static void setGlobalDebugMillis(int paramInt) { globalDebugMillis = paramInt; }
  
  public static void setDesignTime(ContainerWrapper paramContainerWrapper, boolean paramBoolean) {
    if (DT_MAP == null)
      DT_MAP = new WeakHashMap(); 
    DT_MAP.put((paramContainerWrapper != null) ? paramContainerWrapper.getComponent() : null, Boolean.valueOf(paramBoolean));
  }
  
  public static boolean isDesignTime(ContainerWrapper paramContainerWrapper) {
    if (DT_MAP == null)
      return Beans.isDesignTime(); 
    if (paramContainerWrapper != null && !DT_MAP.containsKey(paramContainerWrapper.getComponent()))
      paramContainerWrapper = null; 
    Boolean bool = (Boolean)DT_MAP.get((paramContainerWrapper != null) ? paramContainerWrapper.getComponent() : null);
    return (bool != null && bool.booleanValue());
  }
  
  public static int getDesignTimeEmptySize() { return eSz; }
  
  public static void setDesignTimeEmptySize(int paramInt) { eSz = paramInt; }
  
  static void putCCString(Object paramObject, String paramString) {
    if (paramString != null && paramObject != null && isDesignTime(null)) {
      if (CR_MAP == null)
        CR_MAP = new WeakHashMap(64); 
      CR_MAP.put(paramObject, paramString);
    } 
  }
  
  static void setDelegate(Class paramClass, PersistenceDelegate paramPersistenceDelegate) {
    try {
      Introspector.getBeanInfo(paramClass, 3).getBeanDescriptor().setValue("persistenceDelegate", paramPersistenceDelegate);
    } catch (Exception exception) {}
  }
  
  static String getCCString(Object paramObject) { return (CR_MAP != null) ? (String)CR_MAP.get(paramObject) : null; }
  
  static void throwCC() { throw new IllegalStateException("setStoreConstraintData(true) must be set for strings to be saved."); }
  
  static int[] calculateSerial(int[][] paramArrayOfInt, ResizeConstraint[] paramArrayOfResizeConstraint, Float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
    float[] arrayOfFloat = new float[paramArrayOfInt.length];
    float f = 0.0F;
    int i;
    for (i = 0; i < paramArrayOfInt.length; i++) {
      if (paramArrayOfInt[i] != null) {
        float f1 = (paramArrayOfInt[i][paramInt1] != -2147471302) ? paramArrayOfInt[i][paramInt1] : 0.0F;
        int j = getBrokenBoundary(f1, paramArrayOfInt[i][0], paramArrayOfInt[i][2]);
        if (j != -2147471302)
          f1 = j; 
        f += f1;
        arrayOfFloat[i] = f1;
      } 
    } 
    i = Math.round(f);
    if (i != paramInt2 && paramArrayOfResizeConstraint != null) {
      boolean bool = (i < paramInt2) ? 1 : 0;
      TreeSet treeSet = new TreeSet();
      for (null = 0; null < paramArrayOfInt.length; null++) {
        ResizeConstraint resizeConstraint = (ResizeConstraint)getIndexSafe(paramArrayOfResizeConstraint, null);
        if (resizeConstraint != null)
          treeSet.add(Integer.valueOf(bool ? resizeConstraint.growPrio : resizeConstraint.shrinkPrio)); 
      } 
      Integer[] arrayOfInteger = (Integer[])treeSet.toArray(new Integer[treeSet.size()]);
      for (byte b = 0; b <= ((bool && paramArrayOfFloat != null) ? 1 : 0); b++) {
        for (int j = arrayOfInteger.length - 1; j >= 0; j--) {
          int k = arrayOfInteger[j].intValue();
          float f1 = 0.0F;
          Float[] arrayOfFloat1 = new Float[paramArrayOfInt.length];
          byte b1;
          for (b1 = 0; b1 < paramArrayOfInt.length; b1++) {
            if (paramArrayOfInt[b1] != null) {
              ResizeConstraint resizeConstraint = (ResizeConstraint)getIndexSafe(paramArrayOfResizeConstraint, b1);
              if (resizeConstraint != null) {
                int m = bool ? resizeConstraint.growPrio : resizeConstraint.shrinkPrio;
                if (k == m) {
                  if (bool) {
                    arrayOfFloat1[b1] = (!b || resizeConstraint.grow != null) ? resizeConstraint.grow : paramArrayOfFloat[(b1 < paramArrayOfFloat.length) ? b1 : (paramArrayOfFloat.length - 1)];
                  } else {
                    arrayOfFloat1[b1] = resizeConstraint.shrink;
                  } 
                  if (arrayOfFloat1[b1] != null)
                    f1 += arrayOfFloat1[b1].floatValue(); 
                } 
              } 
            } 
          } 
          if (f1 > 0.0F)
            do {
              float f2 = paramInt2 - f;
              b1 = 0;
              float f3 = 0.0F;
              for (byte b2 = 0; b2 < paramArrayOfInt.length && f1 > 1.0E-4F; b2++) {
                Float float = arrayOfFloat1[b2];
                if (float != null) {
                  float f4 = f2 * float.floatValue() / f1;
                  float f5 = arrayOfFloat[b2] + f4;
                  if (paramArrayOfInt[b2] != null) {
                    int m = getBrokenBoundary(f5, paramArrayOfInt[b2][0], paramArrayOfInt[b2][2]);
                    if (m != -2147471302) {
                      arrayOfFloat1[b2] = null;
                      b1 = 1;
                      f3 += float.floatValue();
                      f5 = m;
                      f4 = f5 - arrayOfFloat[b2];
                    } 
                  } 
                  arrayOfFloat[b2] = f5;
                  f += f4;
                } 
              } 
              f1 -= f3;
            } while (b1 != 0); 
        } 
      } 
    } 
    return roundSizes(arrayOfFloat);
  }
  
  static Object getIndexSafe(Object[] paramArrayOfObject, int paramInt) { return (paramArrayOfObject != null) ? paramArrayOfObject[(paramInt < paramArrayOfObject.length) ? paramInt : (paramArrayOfObject.length - 1)] : null; }
  
  private static int getBrokenBoundary(float paramFloat, int paramInt1, int paramInt2) {
    if (paramInt1 != -2147471302) {
      if (paramFloat < paramInt1)
        return paramInt1; 
    } else if (paramFloat < 0.0F) {
      return 0;
    } 
    return (paramInt2 != -2147471302 && paramFloat > paramInt2) ? paramInt2 : -2147471302;
  }
  
  static int sum(int[] paramArrayOfInt, int paramInt1, int paramInt2) {
    int i = 0;
    int j = paramInt1;
    int k = paramInt1 + paramInt2;
    while (j < k) {
      i += paramArrayOfInt[j];
      j++;
    } 
    return i;
  }
  
  static int sum(int[] paramArrayOfInt) { return sum(paramArrayOfInt, 0, paramArrayOfInt.length); }
  
  public static int getSizeSafe(int[] paramArrayOfInt, int paramInt) { return (paramArrayOfInt == null || paramArrayOfInt[paramInt] == -2147471302) ? ((paramInt == 2) ? 2097051 : 0) : paramArrayOfInt[paramInt]; }
  
  static BoundSize derive(BoundSize paramBoundSize, UnitValue paramUnitValue1, UnitValue paramUnitValue2, UnitValue paramUnitValue3) { return (paramBoundSize == null || paramBoundSize.isUnset()) ? new BoundSize(paramUnitValue1, paramUnitValue2, paramUnitValue3, null) : new BoundSize((paramUnitValue1 != null) ? paramUnitValue1 : paramBoundSize.getMin(), (paramUnitValue2 != null) ? paramUnitValue2 : paramBoundSize.getPreferred(), (paramUnitValue3 != null) ? paramUnitValue3 : paramBoundSize.getMax(), paramBoundSize.getGapPush(), null); }
  
  public static boolean isLeftToRight(LC paramLC, ContainerWrapper paramContainerWrapper) { return (paramLC != null && paramLC.getLeftToRight() != null) ? paramLC.getLeftToRight().booleanValue() : ((paramContainerWrapper == null || paramContainerWrapper.isLeftToRight()) ? 1 : 0); }
  
  static int[] roundSizes(float[] paramArrayOfFloat) {
    int[] arrayOfInt = new int[paramArrayOfFloat.length];
    float f = 0.0F;
    for (byte b = 0; b < arrayOfInt.length; b++) {
      int i = (int)(f + 0.5F);
      f += paramArrayOfFloat[b];
      arrayOfInt[b] = (int)(f + 0.5F) - i;
    } 
    return arrayOfInt;
  }
  
  static boolean equals(Object paramObject1, Object paramObject2) { return (paramObject1 == paramObject2 || (paramObject1 != null && paramObject2 != null && paramObject1.equals(paramObject2))); }
  
  static UnitValue getInsets(LC paramLC, int paramInt, boolean paramBoolean) {
    UnitValue[] arrayOfUnitValue = paramLC.getInsets();
    return (arrayOfUnitValue != null && arrayOfUnitValue[paramInt] != null) ? arrayOfUnitValue[paramInt] : (paramBoolean ? PlatformDefaults.getPanelInsets(paramInt) : UnitValue.ZERO);
  }
  
  static void writeXMLObject(OutputStream paramOutputStream, Object paramObject, ExceptionListener paramExceptionListener) {
    ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    Thread.currentThread().setContextClassLoader(LayoutUtil.class.getClassLoader());
    XMLEncoder xMLEncoder = new XMLEncoder(paramOutputStream);
    if (paramExceptionListener != null)
      xMLEncoder.setExceptionListener(paramExceptionListener); 
    xMLEncoder.writeObject(paramObject);
    xMLEncoder.close();
    Thread.currentThread().setContextClassLoader(classLoader);
  }
  
  public static void writeAsXML(ObjectOutput paramObjectOutput, Object paramObject) throws IOException {
    if (writeOutputStream == null)
      writeOutputStream = new ByteArrayOutputStream('䀀'); 
    writeOutputStream.reset();
    writeXMLObject(writeOutputStream, paramObject, new ExceptionListener() {
          public void exceptionThrown(Exception param1Exception) { param1Exception.printStackTrace(); }
        });
    byte[] arrayOfByte = writeOutputStream.toByteArray();
    paramObjectOutput.writeInt(arrayOfByte.length);
    paramObjectOutput.write(arrayOfByte);
  }
  
  public static Object readAsXML(ObjectInput paramObjectInput) throws IOException {
    if (readBuf == null)
      readBuf = new byte[16384]; 
    Thread thread = Thread.currentThread();
    ClassLoader classLoader = null;
    try {
      classLoader = thread.getContextClassLoader();
      thread.setContextClassLoader(LayoutUtil.class.getClassLoader());
    } catch (SecurityException securityException) {}
    Object object = null;
    try {
      int i = paramObjectInput.readInt();
      if (i > readBuf.length)
        readBuf = new byte[i]; 
      paramObjectInput.readFully(readBuf, 0, i);
      object = (new XMLDecoder(new ByteArrayInputStream(readBuf, false, i))).readObject();
    } catch (EOFException eOFException) {}
    if (classLoader != null)
      thread.setContextClassLoader(classLoader); 
    return object;
  }
  
  public static void setSerializedObject(Object paramObject1, Object paramObject2) {
    synchronized (SER_MAP) {
      SER_MAP.put(paramObject1, paramObject2);
    } 
  }
  
  public static Object getSerializedObject(Object paramObject) {
    synchronized (SER_MAP) {
      return SER_MAP.remove(paramObject);
    } 
  }
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\miginfocom\layout\LayoutUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */