package net.miginfocom.layout;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.WeakHashMap;

public final class Grid {
  public static final boolean TEST_GAPS = true;
  
  private static final Float[] GROW_100 = { ResizeConstraint.WEIGHT_100 };
  
  private static final DimConstraint DOCK_DIM_CONSTRAINT = new DimConstraint();
  
  private static final int MAX_GRID = 30000;
  
  private static final int MAX_DOCK_GRID = 32767;
  
  private static final ResizeConstraint GAP_RC_CONST;
  
  private static final ResizeConstraint GAP_RC_CONST_PUSH;
  
  private final LC lc;
  
  private final ContainerWrapper container;
  
  private final LinkedHashMap<Integer, Cell> grid = new LinkedHashMap();
  
  private HashMap<Integer, BoundSize> wrapGapMap = null;
  
  private final TreeSet<Integer> rowIndexes = new TreeSet();
  
  private final TreeSet<Integer> colIndexes = new TreeSet();
  
  private final AC rowConstr;
  
  private final AC colConstr;
  
  private FlowSizeSpec colFlowSpecs = null;
  
  private FlowSizeSpec rowFlowSpecs = null;
  
  private final ArrayList<LinkedDimGroup>[] colGroupLists;
  
  private final ArrayList<LinkedDimGroup>[] rowGroupLists;
  
  private int[] width = null;
  
  private int[] height = null;
  
  private ArrayList<int[]> debugRects = null;
  
  private HashMap<String, Boolean> linkTargetIDs = null;
  
  private final int dockOffY;
  
  private final int dockOffX;
  
  private final Float[] pushXs;
  
  private final Float[] pushYs;
  
  private final ArrayList<LayoutCallback> callbackList;
  
  private static WeakHashMap[] PARENT_ROWCOL_SIZES_MAP;
  
  private static WeakHashMap<Object, LinkedHashMap<Integer, Cell>> PARENT_GRIDPOS_MAP;
  
  public Grid(ContainerWrapper paramContainerWrapper, LC paramLC, AC paramAC1, AC paramAC2, Map<ComponentWrapper, CC> paramMap, ArrayList<LayoutCallback> paramArrayList) {
    this.lc = paramLC;
    this.rowConstr = paramAC1;
    this.colConstr = paramAC2;
    this.container = paramContainerWrapper;
    this.callbackList = paramArrayList;
    int i = (paramLC.getWrapAfter() != 0) ? paramLC.getWrapAfter() : (paramLC.isFlowX() ? paramAC2 : paramAC1).getConstaints().length;
    ComponentWrapper[] arrayOfComponentWrapper = paramContainerWrapper.getComponents();
    boolean bool1 = false;
    boolean bool2 = false;
    boolean bool3 = false;
    boolean bool4 = false;
    int[] arrayOfInt1 = new int[2];
    ArrayList arrayList = new ArrayList(2);
    DimConstraint[] arrayOfDimConstraint = (paramLC.isFlowX() ? paramAC1 : paramAC2).getConstaints();
    byte b1 = 0;
    byte b2 = 0;
    int[] arrayOfInt2 = null;
    LinkHandler.clearTemporaryBounds(paramContainerWrapper.getLayout());
    null = 0;
    while (null < arrayOfComponentWrapper.length) {
      ComponentWrapper componentWrapper = arrayOfComponentWrapper[null];
      CC cC = getCC(componentWrapper, paramMap);
      addLinkIDs(cC);
      byte b = componentWrapper.isVisible() ? -1 : ((cC.getHideMode() != -1) ? cC.getHideMode() : paramLC.getHideMode());
      if (b == 3) {
        setLinkedBounds(componentWrapper, cC, componentWrapper.getX(), componentWrapper.getY(), componentWrapper.getWidth(), componentWrapper.getHeight(), cC.isExternal());
        null++;
        continue;
      } 
      if (cC.getHorizontal().getSizeGroup() != null)
        b1++; 
      if (cC.getVertical().getSizeGroup() != null)
        b2++; 
      UnitValue[] arrayOfUnitValue = getPos(componentWrapper, cC);
      BoundSize[] arrayOfBoundSize = getCallbackSize(componentWrapper);
      if (arrayOfUnitValue != null || cC.isExternal()) {
        CompWrap compWrap = new CompWrap(componentWrapper, cC, b, arrayOfUnitValue, arrayOfBoundSize, null);
        Cell cell1 = (Cell)this.grid.get(null);
        if (cell1 == null) {
          this.grid.put(null, new Cell(compWrap, null));
        } else {
          cell1.compWraps.add(compWrap);
        } 
        if (!cC.isBoundsInGrid() || cC.isExternal()) {
          setLinkedBounds(componentWrapper, cC, componentWrapper.getX(), componentWrapper.getY(), componentWrapper.getWidth(), componentWrapper.getHeight(), cC.isExternal());
          null++;
          continue;
        } 
      } 
      if (cC.getDockSide() != -1) {
        if (arrayOfInt2 == null)
          arrayOfInt2 = new int[] { -32767, -32767, 32767, 32767 }; 
        addDockingCell(arrayOfInt2, cC.getDockSide(), new CompWrap(componentWrapper, cC, b, arrayOfUnitValue, arrayOfBoundSize, null));
        null++;
        continue;
      } 
      Boolean bool = cC.getFlowX();
      Cell cell = null;
      if (cC.isNewline()) {
        wrap(arrayOfInt1, cC.getNewlineGapSize());
      } else if (bool4) {
        wrap(arrayOfInt1, null);
      } 
      bool4 = false;
      boolean bool6 = (paramLC.isNoGrid() || ((DimConstraint)LayoutUtil.getIndexSafe(arrayOfDimConstraint, paramLC.isFlowX() ? arrayOfInt1[1] : arrayOfInt1[0])).isNoGrid()) ? 1 : 0;
      int k = cC.getCellX();
      int m = cC.getCellY();
      if ((k < 0 || m < 0) && !bool6 && cC.getSkip() == 0) {
        while (!isCellFree(arrayOfInt1[1], arrayOfInt1[0], arrayList)) {
          if (Math.abs(increase(arrayOfInt1, 1)) >= i)
            wrap(arrayOfInt1, null); 
        } 
      } else {
        if (k >= 0 && m >= 0)
          if (m >= 0) {
            arrayOfInt1[0] = k;
            arrayOfInt1[1] = m;
          } else if (paramLC.isFlowX()) {
            arrayOfInt1[0] = k;
          } else {
            arrayOfInt1[1] = k;
          }  
        cell = getCell(arrayOfInt1[1], arrayOfInt1[0]);
      } 
      int n = 0;
      int i1 = cC.getSkip();
      while (n < i1) {
        do {
          if (Math.abs(increase(arrayOfInt1, 1)) < i)
            continue; 
          wrap(arrayOfInt1, null);
        } while (!isCellFree(arrayOfInt1[1], arrayOfInt1[0], arrayList));
        n++;
      } 
      if (cell == null) {
        n = Math.min((bool6 && paramLC.isFlowX()) ? 2097051 : cC.getSpanX(), 30000 - arrayOfInt1[0]);
        i1 = Math.min((bool6 && !paramLC.isFlowX()) ? 2097051 : cC.getSpanY(), 30000 - arrayOfInt1[1]);
        cell = new Cell(n, i1, (bool != null) ? bool.booleanValue() : paramLC.isFlowX(), null);
        setCell(arrayOfInt1[1], arrayOfInt1[0], cell);
        if (n > 1 || i1 > 1)
          arrayList.add(new int[] { arrayOfInt1[0], arrayOfInt1[1], n, i1 }); 
      } 
      n = 0;
      i1 = bool6 ? 2097051 : (cC.getSplit() - 1);
      boolean bool7 = false;
      boolean bool8 = ((paramLC.isFlowX() ? cC.getSpanX() : cC.getSpanY()) == 2097051) ? 1 : 0;
      while (i1 >= 0 && null < arrayOfComponentWrapper.length) {
        ComponentWrapper componentWrapper1 = arrayOfComponentWrapper[null];
        CC cC1 = getCC(componentWrapper1, paramMap);
        addLinkIDs(cC1);
        boolean bool9 = componentWrapper1.isVisible();
        b = bool9 ? -1 : ((cC1.getHideMode() != -1) ? cC1.getHideMode() : paramLC.getHideMode());
        if (cC1.isExternal() || b == 3) {
          null++;
          i1++;
        } else {
          bool2 |= (((bool9 || b > 1) && cC1.getPushX() != null) ? 1 : 0);
          bool3 |= (((bool9 || b > 1) && cC1.getPushY() != null) ? 1 : 0);
          if (cC1 != cC) {
            if (cC1.isNewline() || !cC1.isBoundsInGrid() || cC1.getDockSide() != -1)
              break; 
            if (i1 > 0 && cC1.getSkip() > 0) {
              bool7 = true;
              break;
            } 
            arrayOfUnitValue = getPos(componentWrapper1, cC1);
            arrayOfBoundSize = getCallbackSize(componentWrapper1);
          } 
          CompWrap compWrap = new CompWrap(componentWrapper1, cC1, b, arrayOfUnitValue, arrayOfBoundSize, null);
          cell.compWraps.add(compWrap);
          Cell.access$476(cell, (cC1.getTag() != null) ? 1 : 0);
          bool1 |= cell.hasTagged;
          if (cC1 != cC) {
            if (cC1.getHorizontal().getSizeGroup() != null)
              b1++; 
            if (cC1.getVertical().getSizeGroup() != null)
              b2++; 
          } 
          null++;
          if (cC1.isWrap() || (bool8 && i1 == 0)) {
            if (cC1.isWrap()) {
              wrap(arrayOfInt1, cC1.getWrapGapSize());
            } else {
              bool4 = true;
            } 
            n = 1;
            break;
          } 
        } 
        i1--;
      } 
      if (n == 0 && !bool6) {
        int i2 = paramLC.isFlowX() ? cell.spanx : cell.spany;
        if (Math.abs(paramLC.isFlowX() ? arrayOfInt1[0] : arrayOfInt1[1]) + i2 >= i) {
          bool4 = true;
          continue;
        } 
        increase(arrayOfInt1, bool7 ? (i2 - 1) : i2);
      } 
    } 
    if (b1 > 0 || b2 > 0) {
      HashMap hashMap1 = (b1 > 0) ? new HashMap(b1) : null;
      HashMap hashMap2 = (b2 > 0) ? new HashMap(b2) : null;
      ArrayList arrayList1 = new ArrayList(Math.max(b1, b2));
      for (Cell cell : this.grid.values()) {
        for (byte b4 = 0; b4 < cell.compWraps.size(); b4++) {
          CompWrap compWrap;
          String str1 = compWrap.cc.getHorizontal().getSizeGroup();
          String str2 = compWrap.cc.getVertical().getSizeGroup();
          if (str1 != null || str2 != null) {
            if (str1 != null && hashMap1 != null)
              addToSizeGroup(hashMap1, str1, compWrap.horSizes); 
            if (str2 != null && hashMap2 != null)
              addToSizeGroup(hashMap2, str2, compWrap.verSizes); 
            arrayList1.add(compWrap);
          } 
        } 
      } 
      for (byte b = 0; b < arrayList1.size(); b++) {
        CompWrap compWrap = (CompWrap)arrayList1.get(b);
        if (hashMap1 != null)
          compWrap.setSizes((int[])hashMap1.get(compWrap.cc.getHorizontal().getSizeGroup()), true); 
        if (hashMap2 != null)
          compWrap.setSizes((int[])hashMap2.get(compWrap.cc.getVertical().getSizeGroup()), false); 
      } 
    } 
    if (b1 > 0 || b2 > 0) {
      HashMap hashMap1 = (b1 > 0) ? new HashMap(b1) : null;
      HashMap hashMap2 = (b2 > 0) ? new HashMap(b2) : null;
      ArrayList arrayList1 = new ArrayList(Math.max(b1, b2));
      for (Cell cell : this.grid.values()) {
        for (byte b4 = 0; b4 < cell.compWraps.size(); b4++) {
          CompWrap compWrap;
          String str1 = compWrap.cc.getHorizontal().getSizeGroup();
          String str2 = compWrap.cc.getVertical().getSizeGroup();
          if (str1 != null || str2 != null) {
            if (str1 != null && hashMap1 != null)
              addToSizeGroup(hashMap1, str1, compWrap.horSizes); 
            if (str2 != null && hashMap2 != null)
              addToSizeGroup(hashMap2, str2, compWrap.verSizes); 
            arrayList1.add(compWrap);
          } 
        } 
      } 
      for (byte b = 0; b < arrayList1.size(); b++) {
        CompWrap compWrap = (CompWrap)arrayList1.get(b);
        if (hashMap1 != null)
          compWrap.setSizes((int[])hashMap1.get(compWrap.cc.getHorizontal().getSizeGroup()), true); 
        if (hashMap2 != null)
          compWrap.setSizes((int[])hashMap2.get(compWrap.cc.getVertical().getSizeGroup()), false); 
      } 
    } 
    if (bool1)
      sortCellsByPlatform(this.grid.values(), paramContainerWrapper); 
    boolean bool5 = LayoutUtil.isLeftToRight(paramLC, paramContainerWrapper);
    null = this.grid.values().iterator();
    while (null.hasNext()) {
      Cell cell;
      ArrayList arrayList1 = cell.compWraps;
      byte b = 0;
      int k = arrayList1.size() - 1;
      while (b <= k) {
        CompWrap compWrap;
        ComponentWrapper componentWrapper1 = (b > 0) ? ((CompWrap)arrayList1.get(b - 1)).comp : null;
        ComponentWrapper componentWrapper2 = (b < k) ? ((CompWrap)arrayList1.get(b + 1)).comp : null;
        String str = getCC(compWrap.comp, paramMap).getTag();
        CC cC1 = (componentWrapper1 != null) ? getCC(componentWrapper1, paramMap) : null;
        CC cC2 = (componentWrapper2 != null) ? getCC(componentWrapper2, paramMap) : null;
        compWrap.calcGaps(componentWrapper1, cC1, componentWrapper2, cC2, str, cell.flowx, bool5);
        b++;
      } 
    } 
    this.dockOffX = getDockInsets(this.colIndexes);
    this.dockOffY = getDockInsets(this.rowIndexes);
    byte b3 = 0;
    int j = paramAC1.getCount();
    while (b3 < j) {
      this.rowIndexes.add(Integer.valueOf(b3));
      b3++;
    } 
    b3 = 0;
    j = paramAC2.getCount();
    while (b3 < j) {
      this.colIndexes.add(Integer.valueOf(b3));
      b3++;
    } 
    this.colGroupLists = divideIntoLinkedGroups(false);
    this.rowGroupLists = divideIntoLinkedGroups(true);
    this.pushXs = (bool2 || paramLC.isFillX()) ? getDefaultPushWeights(false) : null;
    this.pushYs = (bool3 || paramLC.isFillY()) ? getDefaultPushWeights(true) : null;
    if (LayoutUtil.isDesignTime(paramContainerWrapper))
      saveGrid(paramContainerWrapper, this.grid); 
  }
  
  private static CC getCC(ComponentWrapper paramComponentWrapper, Map<ComponentWrapper, CC> paramMap) {
    CC cC = (CC)paramMap.get(paramComponentWrapper);
    return (cC != null) ? cC : new CC();
  }
  
  private void addLinkIDs(CC paramCC) {
    String[] arrayOfString = paramCC.getLinkTargets();
    for (byte b = 0; b < arrayOfString.length; b++) {
      if (this.linkTargetIDs == null)
        this.linkTargetIDs = new HashMap(); 
      this.linkTargetIDs.put(arrayOfString[b], null);
    } 
  }
  
  public void invalidateContainerSize() { this.colFlowSpecs = null; }
  
  public boolean layout(int[] paramArrayOfInt, UnitValue paramUnitValue1, UnitValue paramUnitValue2, boolean paramBoolean1, boolean paramBoolean2) {
    if (paramBoolean1)
      this.debugRects = new ArrayList(); 
    checkSizeCalcs();
    resetLinkValues(true, true);
    layoutInOneDim(paramArrayOfInt[2], paramUnitValue1, false, this.pushXs);
    layoutInOneDim(paramArrayOfInt[3], paramUnitValue2, true, this.pushYs);
    HashMap hashMap1 = null;
    HashMap hashMap2 = null;
    int i = this.container.getComponentCount();
    boolean bool = false;
    if (i > 0)
      for (byte b = 0; b < ((this.linkTargetIDs != null) ? 2 : 1); b++) {
        boolean bool1;
        byte b1 = 0;
        do {
          bool1 = false;
          Iterator iterator = this.grid.values().iterator();
          while (iterator.hasNext()) {
            ArrayList arrayList = ((Cell)iterator.next()).compWraps;
            byte b2 = 0;
            int j = arrayList.size();
            while (b2 < j) {
              CompWrap compWrap = (CompWrap)arrayList.get(b2);
              if (!b) {
                bool1 |= doAbsoluteCorrections(compWrap, paramArrayOfInt);
                if (!bool1) {
                  if (compWrap.cc.getHorizontal().getEndGroup() != null)
                    hashMap1 = addToEndGroup(hashMap1, compWrap.cc.getHorizontal().getEndGroup(), compWrap.x + compWrap.w); 
                  if (compWrap.cc.getVertical().getEndGroup() != null)
                    hashMap2 = addToEndGroup(hashMap2, compWrap.cc.getVertical().getEndGroup(), compWrap.y + compWrap.h); 
                } 
                if (this.linkTargetIDs != null && (this.linkTargetIDs.containsKey("visual") || this.linkTargetIDs.containsKey("container")))
                  bool = true; 
              } 
              if (this.linkTargetIDs == null || b == 1) {
                if (compWrap.cc.getHorizontal().getEndGroup() != null)
                  compWrap.w = ((Integer)hashMap1.get(compWrap.cc.getHorizontal().getEndGroup())).intValue() - compWrap.x; 
                if (compWrap.cc.getVertical().getEndGroup() != null)
                  compWrap.h = ((Integer)hashMap2.get(compWrap.cc.getVertical().getEndGroup())).intValue() - compWrap.y; 
                compWrap.x += paramArrayOfInt[0];
                compWrap.y += paramArrayOfInt[1];
                bool |= compWrap.transferBounds((paramBoolean2 && !bool));
                if (this.callbackList != null)
                  for (byte b3 = 0; b3 < this.callbackList.size(); b3++)
                    ((LayoutCallback)this.callbackList.get(b3)).correctBounds(compWrap.comp);  
              } 
              b2++;
            } 
          } 
          clearGroupLinkBounds();
          if (++b1 > (i << 3) + 10) {
            System.err.println("Unstable cyclic dependency in absolute linked values!");
            break;
          } 
        } while (bool1);
      }  
    if (paramBoolean1) {
      Collection collection = this.grid.values();
      Iterator iterator = collection.iterator();
      while (iterator.hasNext()) {
        ArrayList arrayList = ((Cell)iterator.next()).compWraps;
        byte b = 0;
        int j = arrayList.size();
        while (b < j) {
          CompWrap compWrap = (CompWrap)arrayList.get(b);
          LinkedDimGroup linkedDimGroup1 = getGroupContaining(this.colGroupLists, compWrap);
          LinkedDimGroup linkedDimGroup2 = getGroupContaining(this.rowGroupLists, compWrap);
          if (linkedDimGroup1 != null && linkedDimGroup2 != null)
            this.debugRects.add(new int[] { LinkedDimGroup.access$1900(linkedDimGroup1) + paramArrayOfInt[0] - (LinkedDimGroup.access$2000(linkedDimGroup1) ? LinkedDimGroup.access$2100(linkedDimGroup1) : 0), LinkedDimGroup.access$1900(linkedDimGroup2) + paramArrayOfInt[1] - (LinkedDimGroup.access$2000(linkedDimGroup2) ? LinkedDimGroup.access$2100(linkedDimGroup2) : 0), LinkedDimGroup.access$2100(linkedDimGroup1), LinkedDimGroup.access$2100(linkedDimGroup2) }); 
          b++;
        } 
      } 
    } 
    return bool;
  }
  
  public void paintDebug() {
    if (this.debugRects != null) {
      this.container.paintDebugOutline();
      ArrayList arrayList = new ArrayList();
      null = 0;
      int i = this.debugRects.size();
      while (null < i) {
        int[] arrayOfInt = (int[])this.debugRects.get(null);
        if (!arrayList.contains(arrayOfInt)) {
          this.container.paintDebugCell(arrayOfInt[0], arrayOfInt[1], arrayOfInt[2], arrayOfInt[3]);
          arrayList.add(arrayOfInt);
        } 
        null++;
      } 
      Iterator iterator = this.grid.values().iterator();
      while (iterator.hasNext()) {
        ArrayList arrayList1 = ((Cell)iterator.next()).compWraps;
        byte b = 0;
        int j = arrayList1.size();
        while (b < j) {
          ((CompWrap)arrayList1.get(b)).comp.paintDebugOutline();
          b++;
        } 
      } 
    } 
  }
  
  public ContainerWrapper getContainer() { return this.container; }
  
  public final int[] getWidth() {
    checkSizeCalcs();
    return (int[])this.width.clone();
  }
  
  public final int[] getHeight() {
    checkSizeCalcs();
    return (int[])this.height.clone();
  }
  
  private void checkSizeCalcs() {
    if (this.colFlowSpecs == null) {
      this.colFlowSpecs = calcRowsOrColsSizes(true);
      this.rowFlowSpecs = calcRowsOrColsSizes(false);
      this.width = getMinPrefMaxSumSize(true);
      this.height = getMinPrefMaxSumSize(false);
      if (this.linkTargetIDs == null) {
        resetLinkValues(false, true);
      } else {
        layout(new int[4], null, null, false, false);
        resetLinkValues(false, false);
      } 
      adjustSizeForAbsolute(true);
      adjustSizeForAbsolute(false);
    } 
  }
  
  private UnitValue[] getPos(ComponentWrapper paramComponentWrapper, CC paramCC) {
    UnitValue[] arrayOfUnitValue1 = null;
    if (this.callbackList != null)
      for (byte b1 = 0; b1 < this.callbackList.size() && arrayOfUnitValue1 == null; b1++)
        arrayOfUnitValue1 = ((LayoutCallback)this.callbackList.get(b1)).getPosition(paramComponentWrapper);  
    UnitValue[] arrayOfUnitValue2 = paramCC.getPos();
    if (arrayOfUnitValue1 == null || arrayOfUnitValue2 == null)
      return (arrayOfUnitValue1 != null) ? arrayOfUnitValue1 : arrayOfUnitValue2; 
    for (byte b = 0; b < 4; b++) {
      UnitValue unitValue = arrayOfUnitValue1[b];
      if (unitValue != null)
        arrayOfUnitValue2[b] = unitValue; 
    } 
    return arrayOfUnitValue2;
  }
  
  private BoundSize[] getCallbackSize(ComponentWrapper paramComponentWrapper) {
    if (this.callbackList != null)
      for (byte b = 0; b < this.callbackList.size(); b++) {
        BoundSize[] arrayOfBoundSize = ((LayoutCallback)this.callbackList.get(b)).getSize(paramComponentWrapper);
        if (arrayOfBoundSize != null)
          return arrayOfBoundSize; 
      }  
    return null;
  }
  
  private static int getDockInsets(TreeSet<Integer> paramTreeSet) {
    byte b = 0;
    Iterator iterator = paramTreeSet.iterator();
    while (iterator.hasNext() && ((Integer)iterator.next()).intValue() < -30000)
      b++; 
    return b;
  }
  
  private boolean setLinkedBounds(ComponentWrapper paramComponentWrapper, CC paramCC, int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean) {
    String str1 = (paramCC.getId() != null) ? paramCC.getId() : paramComponentWrapper.getLinkId();
    if (str1 == null)
      return false; 
    String str2 = null;
    int i = str1.indexOf('.');
    if (i != -1) {
      str2 = str1.substring(0, i);
      str1 = str1.substring(i + 1);
    } 
    Object object = this.container.getLayout();
    boolean bool = false;
    if (paramBoolean || (this.linkTargetIDs != null && this.linkTargetIDs.containsKey(str1)))
      bool = LinkHandler.setBounds(object, str1, paramInt1, paramInt2, paramInt3, paramInt4, !paramBoolean, false); 
    if (str2 != null && (paramBoolean || (this.linkTargetIDs != null && this.linkTargetIDs.containsKey(str2)))) {
      if (this.linkTargetIDs == null)
        this.linkTargetIDs = new HashMap(4); 
      this.linkTargetIDs.put(str2, Boolean.TRUE);
      bool |= LinkHandler.setBounds(object, str2, paramInt1, paramInt2, paramInt3, paramInt4, !paramBoolean, true);
    } 
    return bool;
  }
  
  private int increase(int[] paramArrayOfInt, int paramInt) { return this.lc.isFlowX() ? (paramArrayOfInt[0] = paramArrayOfInt[0] + paramInt) : (paramArrayOfInt[1] = paramArrayOfInt[1] + paramInt); }
  
  private void wrap(int[] paramArrayOfInt, BoundSize paramBoundSize) {
    boolean bool = this.lc.isFlowX();
    paramArrayOfInt[0] = bool ? 0 : (paramArrayOfInt[0] + 1);
    paramArrayOfInt[1] = bool ? (paramArrayOfInt[1] + 1) : 0;
    if (paramBoundSize != null) {
      if (this.wrapGapMap == null)
        this.wrapGapMap = new HashMap(8); 
      this.wrapGapMap.put(Integer.valueOf(paramArrayOfInt[bool ? 1 : 0]), paramBoundSize);
    } 
    if (bool) {
      this.rowIndexes.add(Integer.valueOf(paramArrayOfInt[1]));
    } else {
      this.colIndexes.add(Integer.valueOf(paramArrayOfInt[0]));
    } 
  }
  
  private static void sortCellsByPlatform(Collection<Cell> paramCollection, ContainerWrapper paramContainerWrapper) {
    String str1 = PlatformDefaults.getButtonOrder();
    String str2 = str1.toLowerCase();
    int i = PlatformDefaults.convertToPixels(1.0F, "u", true, 0.0F, paramContainerWrapper, null);
    if (i == -87654312)
      throw new IllegalArgumentException("'unrelated' not recognized by PlatformDefaults!"); 
    int[] arrayOfInt1 = { i, i, -2147471302 };
    int[] arrayOfInt2 = { 0, 0, -2147471302 };
    Iterator iterator = paramCollection.iterator();
    while (iterator.hasNext()) {
      Cell cell;
      if (!cell.hasTagged)
        continue; 
      CompWrap compWrap = null;
      boolean bool1 = false;
      boolean bool2 = false;
      ArrayList arrayList = new ArrayList(cell.compWraps.size());
      byte b = 0;
      int j = str2.length();
      while (b < j) {
        char c = str2.charAt(b);
        if (c == '+' || c == '_') {
          bool1 = true;
          if (c == '+')
            bool2 = true; 
        } else {
          String str = PlatformDefaults.getTagForChar(c);
          if (str != null) {
            byte b1 = 0;
            int k = cell.compWraps.size();
            while (b1 < k) {
              CompWrap compWrap1;
              if (str.equals(compWrap1.cc.getTag())) {
                if (Character.isUpperCase(str1.charAt(b))) {
                  int m = PlatformDefaults.getMinimumButtonWidth().getPixels(0.0F, paramContainerWrapper, compWrap1.comp);
                  if (m > compWrap1.horSizes[0])
                    compWrap1.horSizes[0] = m; 
                  correctMinMax(compWrap1.horSizes);
                } 
                arrayList.add(compWrap1);
                if (bool1) {
                  ((compWrap != null) ? compWrap : compWrap1).mergeGapSizes(arrayOfInt1, cell.flowx, (compWrap == null));
                  if (bool2) {
                    compWrap1.forcedPushGaps = 1;
                    bool1 = false;
                    bool2 = false;
                  } 
                } 
                if (c == 'u')
                  bool1 = true; 
                compWrap = compWrap1;
              } 
              b1++;
            } 
          } 
        } 
        b++;
      } 
      if (arrayList.size() > 0) {
        CompWrap compWrap1 = (CompWrap)arrayList.get(arrayList.size() - 1);
        if (bool1) {
          compWrap1.mergeGapSizes(arrayOfInt1, cell.flowx, false);
          if (bool2)
            compWrap1.forcedPushGaps |= 0x2; 
        } 
        if (compWrap1.cc.getHorizontal().getGapAfter() == null)
          compWrap1.setGaps(arrayOfInt2, 3); 
        if (compWrap1.cc.getHorizontal().getGapBefore() == null)
          compWrap1.setGaps(arrayOfInt2, 1); 
      } 
      if (cell.compWraps.size() == arrayList.size()) {
        cell.compWraps.clear();
      } else {
        cell.compWraps.removeAll(arrayList);
      } 
      cell.compWraps.addAll(arrayList);
    } 
  }
  
  private Float[] getDefaultPushWeights(boolean paramBoolean) {
    ArrayList[] arrayOfArrayList = paramBoolean ? this.rowGroupLists : this.colGroupLists;
    Float[] arrayOfFloat = GROW_100;
    byte b = 0;
    for (boolean bool = true; b < arrayOfArrayList.length; bool += true) {
      ArrayList arrayList = arrayOfArrayList[b];
      Float float = null;
      for (byte b1 = 0; b1 < arrayList.size(); b1++) {
        LinkedDimGroup linkedDimGroup = (LinkedDimGroup)arrayList.get(b1);
        for (byte b2 = 0; b2 < linkedDimGroup._compWraps.size(); b2++) {
          CompWrap compWrap;
          byte b3 = compWrap.comp.isVisible() ? -1 : ((compWrap.cc.getHideMode() != -1) ? compWrap.cc.getHideMode() : this.lc.getHideMode());
          Float float1 = (b3 < 2) ? (paramBoolean ? compWrap.cc.getPushY() : compWrap.cc.getPushX()) : null;
          if (float == null || (float1 != null && float1.floatValue() > float.floatValue()))
            float = float1; 
        } 
      } 
      if (float != null) {
        if (arrayOfFloat == GROW_100)
          arrayOfFloat = new Float[(arrayOfArrayList.length << 1) + 1]; 
        arrayOfFloat[bool] = float;
      } 
      b++;
    } 
    return arrayOfFloat;
  }
  
  private void clearGroupLinkBounds() {
    if (this.linkTargetIDs == null)
      return; 
    for (Map.Entry entry : this.linkTargetIDs.entrySet()) {
      if (entry.getValue() == Boolean.TRUE)
        LinkHandler.clearBounds(this.container.getLayout(), (String)entry.getKey()); 
    } 
  }
  
  private void resetLinkValues(boolean paramBoolean1, boolean paramBoolean2) {
    Object object = this.container.getLayout();
    if (paramBoolean2)
      LinkHandler.clearTemporaryBounds(object); 
    boolean bool = !hasDocks();
    int i = paramBoolean1 ? this.lc.getWidth().constrain(this.container.getWidth(), getParentSize(this.container, true), this.container) : 0;
    int j = paramBoolean1 ? this.lc.getHeight().constrain(this.container.getHeight(), getParentSize(this.container, false), this.container) : 0;
    int k = LayoutUtil.getInsets(this.lc, 0, bool).getPixels(0.0F, this.container, null);
    int m = LayoutUtil.getInsets(this.lc, 1, bool).getPixels(0.0F, this.container, null);
    int n = i - k - LayoutUtil.getInsets(this.lc, 2, bool).getPixels(0.0F, this.container, null);
    int i1 = j - m - LayoutUtil.getInsets(this.lc, 3, bool).getPixels(0.0F, this.container, null);
    LinkHandler.setBounds(object, "visual", k, m, n, i1, true, false);
    LinkHandler.setBounds(object, "container", 0, 0, i, j, true, false);
  }
  
  private static LinkedDimGroup getGroupContaining(ArrayList<LinkedDimGroup>[] paramArrayOfArrayList, CompWrap paramCompWrap) {
    for (byte b = 0; b < paramArrayOfArrayList.length; b++) {
      ArrayList<LinkedDimGroup> arrayList = paramArrayOfArrayList[b];
      byte b1 = 0;
      int i = arrayList.size();
      while (b1 < i) {
        ArrayList arrayList1 = ((LinkedDimGroup)arrayList.get(b1))._compWraps;
        byte b2 = 0;
        int j = arrayList1.size();
        while (b2 < j) {
          if (arrayList1.get(b2) == paramCompWrap)
            return (LinkedDimGroup)arrayList.get(b1); 
          b2++;
        } 
        b1++;
      } 
    } 
    return null;
  }
  
  private boolean doAbsoluteCorrections(CompWrap paramCompWrap, int[] paramArrayOfInt) {
    boolean bool = false;
    int[] arrayOfInt = getAbsoluteDimBounds(paramCompWrap, paramArrayOfInt[2], true);
    if (arrayOfInt != null)
      paramCompWrap.setDimBounds(arrayOfInt[0], arrayOfInt[1], true); 
    arrayOfInt = getAbsoluteDimBounds(paramCompWrap, paramArrayOfInt[3], false);
    if (arrayOfInt != null)
      paramCompWrap.setDimBounds(arrayOfInt[0], arrayOfInt[1], false); 
    if (this.linkTargetIDs != null)
      bool = setLinkedBounds(paramCompWrap.comp, paramCompWrap.cc, paramCompWrap.x, paramCompWrap.y, paramCompWrap.w, paramCompWrap.h, false); 
    return bool;
  }
  
  private void adjustSizeForAbsolute(boolean paramBoolean) {
    int[] arrayOfInt = paramBoolean ? this.width : this.height;
    Cell cell;
    if (cell == null || cell.compWraps.size() == 0)
      return; 
    ArrayList arrayList = cell.compWraps;
    int i = 0;
    byte b = 0;
    int j = cell.compWraps.size();
    while (b < j + 3) {
      boolean bool = false;
      for (byte b1 = 0; b1 < j; b1++) {
        CompWrap compWrap = (CompWrap)arrayList.get(b1);
        int[] arrayOfInt1 = getAbsoluteDimBounds(compWrap, 0, paramBoolean);
        int k = arrayOfInt1[0] + arrayOfInt1[1];
        if (i < k)
          i = k; 
        if (this.linkTargetIDs != null)
          bool |= setLinkedBounds(compWrap.comp, compWrap.cc, arrayOfInt1[0], arrayOfInt1[0], arrayOfInt1[1], arrayOfInt1[1], false); 
      } 
      if (!bool)
        break; 
      i = 0;
      clearGroupLinkBounds();
      b++;
    } 
    i += LayoutUtil.getInsets(this.lc, paramBoolean ? 3 : 2, !hasDocks()).getPixels(0.0F, this.container, null);
    if (arrayOfInt[0] < i)
      arrayOfInt[0] = i; 
    if (arrayOfInt[1] < i)
      arrayOfInt[1] = i; 
  }
  
  private int[] getAbsoluteDimBounds(CompWrap paramCompWrap, int paramInt, boolean paramBoolean) {
    if (paramCompWrap.cc.isExternal())
      return paramBoolean ? new int[] { CompWrap.access$1100(paramCompWrap).getX(), CompWrap.access$1100(paramCompWrap).getWidth() } : new int[] { CompWrap.access$1100(paramCompWrap).getY(), CompWrap.access$1100(paramCompWrap).getHeight() }; 
    int[] arrayOfInt = this.lc.isVisualPadding() ? paramCompWrap.comp.getVisualPadding() : null;
    UnitValue[] arrayOfUnitValue = paramCompWrap.cc.getPadding();
    if (paramCompWrap.pos == null && arrayOfInt == null && arrayOfUnitValue == null)
      return null; 
    int i = paramBoolean ? paramCompWrap.x : paramCompWrap.y;
    int j = paramBoolean ? paramCompWrap.w : paramCompWrap.h;
    if (paramCompWrap.pos != null) {
      UnitValue unitValue1 = (paramCompWrap.pos != null) ? paramCompWrap.pos[paramBoolean ? 0 : 1] : null;
      UnitValue unitValue2 = (paramCompWrap.pos != null) ? paramCompWrap.pos[paramBoolean ? 2 : 3] : null;
      int k = paramCompWrap.getSize(0, paramBoolean);
      int m = paramCompWrap.getSize(2, paramBoolean);
      j = Math.min(Math.max(paramCompWrap.getSize(1, paramBoolean), k), m);
      if (unitValue1 != null) {
        i = unitValue1.getPixels((unitValue1.getUnit() == 12) ? j : paramInt, this.container, paramCompWrap.comp);
        if (unitValue2 != null)
          j = Math.min(Math.max((paramBoolean ? (paramCompWrap.x + paramCompWrap.w) : (paramCompWrap.y + paramCompWrap.h)) - i, k), m); 
      } 
      if (unitValue2 != null)
        if (unitValue1 != null) {
          j = Math.min(Math.max(unitValue2.getPixels(paramInt, this.container, paramCompWrap.comp) - i, k), m);
        } else {
          i = unitValue2.getPixels(paramInt, this.container, paramCompWrap.comp) - j;
        }  
    } 
    if (arrayOfUnitValue != null) {
      UnitValue unitValue = arrayOfUnitValue[paramBoolean ? 1 : 0];
      int k = (unitValue != null) ? unitValue.getPixels(paramInt, this.container, paramCompWrap.comp) : 0;
      i += k;
      unitValue = arrayOfUnitValue[paramBoolean ? 3 : 2];
      j += -k + ((unitValue != null) ? unitValue.getPixels(paramInt, this.container, paramCompWrap.comp) : 0);
    } 
    if (arrayOfInt != null) {
      int k = arrayOfInt[paramBoolean ? 1 : 0];
      i += k;
      j += -k + arrayOfInt[paramBoolean ? 3 : 2];
    } 
    return new int[] { i, j };
  }
  
  private void layoutInOneDim(int paramInt, UnitValue paramUnitValue, boolean paramBoolean, Float[] paramArrayOfFloat) {
    if (paramBoolean ? this.lc.isTopToBottom() : LayoutUtil.isLeftToRight(this.lc, this.container)) {
    
    } else {
    
    } 
    boolean bool = true;
    DimConstraint[] arrayOfDimConstraint = (paramBoolean ? this.rowConstr : this.colConstr).getConstaints();
    FlowSizeSpec flowSizeSpec = paramBoolean ? this.rowFlowSpecs : this.colFlowSpecs;
    ArrayList[] arrayOfArrayList = paramBoolean ? this.rowGroupLists : this.colGroupLists;
    int[] arrayOfInt = LayoutUtil.calculateSerial(flowSizeSpec.sizes, flowSizeSpec.resConstsInclGaps, paramArrayOfFloat, 1, paramInt);
    if (LayoutUtil.isDesignTime(this.container)) {
      TreeSet treeSet = paramBoolean ? this.rowIndexes : this.colIndexes;
      int[] arrayOfInt1 = new int[treeSet.size()];
      byte b = 0;
      for (Integer integer : treeSet)
        arrayOfInt1[b++] = integer.intValue(); 
      putSizesAndIndexes(this.container.getComponent(), arrayOfInt, arrayOfInt1, paramBoolean);
    } 
    int i = (paramUnitValue != null) ? paramUnitValue.getPixels((paramInt - LayoutUtil.sum(arrayOfInt)), this.container, null) : 0;
    if (bool)
      i = paramInt - i; 
    for (int j = 0; j < arrayOfArrayList.length; j++) {
      ArrayList arrayList = arrayOfArrayList[j];
      int k = j - (paramBoolean ? this.dockOffY : this.dockOffX);
      int m = j << 1;
      int n = m + 1;
      i += (bool ? -arrayOfInt[m] : arrayOfInt[m]);
      DimConstraint dimConstraint = (k >= 0) ? arrayOfDimConstraint[(k >= arrayOfDimConstraint.length) ? (arrayOfDimConstraint.length - 1) : k] : DOCK_DIM_CONSTRAINT;
      int i1 = arrayOfInt[n];
      for (byte b = 0; b < arrayList.size(); b++) {
        LinkedDimGroup linkedDimGroup = (LinkedDimGroup)arrayList.get(b);
        int i2 = i1;
        if (linkedDimGroup.span > 1)
          i2 = LayoutUtil.sum(arrayOfInt, n, Math.min((linkedDimGroup.span << 1) - 1, arrayOfInt.length - n - 1)); 
        linkedDimGroup.layout(dimConstraint, i, i2, linkedDimGroup.span);
      } 
      i += (bool ? -i1 : i1);
    } 
  }
  
  private static void addToSizeGroup(HashMap<String, int[]> paramHashMap, String paramString, int[] paramArrayOfInt) {
    int[] arrayOfInt = (int[])paramHashMap.get(paramString);
    if (arrayOfInt == null) {
      paramHashMap.put(paramString, new int[] { paramArrayOfInt[0], paramArrayOfInt[1], paramArrayOfInt[2] });
    } else {
      arrayOfInt[0] = Math.max(paramArrayOfInt[0], arrayOfInt[0]);
      arrayOfInt[1] = Math.max(paramArrayOfInt[1], arrayOfInt[1]);
      arrayOfInt[2] = Math.min(paramArrayOfInt[2], arrayOfInt[2]);
    } 
  }
  
  private static HashMap<String, Integer> addToEndGroup(HashMap<String, Integer> paramHashMap, String paramString, int paramInt) {
    if (paramString != null) {
      if (paramHashMap == null)
        paramHashMap = new HashMap<String, Integer>(2); 
      Integer integer = (Integer)paramHashMap.get(paramString);
      if (integer == null || paramInt > integer.intValue())
        paramHashMap.put(paramString, Integer.valueOf(paramInt)); 
    } 
    return paramHashMap;
  }
  
  private FlowSizeSpec calcRowsOrColsSizes(boolean paramBoolean) {
    ArrayList[] arrayOfArrayList = paramBoolean ? this.colGroupLists : this.rowGroupLists;
    Float[] arrayOfFloat = paramBoolean ? this.pushXs : this.pushYs;
    int i = paramBoolean ? this.container.getWidth() : this.container.getHeight();
    BoundSize boundSize = paramBoolean ? this.lc.getWidth() : this.lc.getHeight();
    if (!boundSize.isUnset())
      i = boundSize.constrain(i, getParentSize(this.container, paramBoolean), this.container); 
    DimConstraint[] arrayOfDimConstraint1 = (paramBoolean ? this.colConstr : this.rowConstr).getConstaints();
    TreeSet treeSet = paramBoolean ? this.colIndexes : this.rowIndexes;
    int[][] arrayOfInt1 = new int[treeSet.size()][];
    HashMap hashMap = new HashMap(2);
    DimConstraint[] arrayOfDimConstraint2 = new DimConstraint[treeSet.size()];
    Iterator iterator = treeSet.iterator();
    byte b;
    for (b = 0; b < arrayOfInt1.length; b++) {
      int j = ((Integer)iterator.next()).intValue();
      int[] arrayOfInt3 = new int[3];
      if (j >= -30000 && j <= 30000) {
        arrayOfDimConstraint2[b] = arrayOfDimConstraint1[(j >= arrayOfDimConstraint1.length) ? (arrayOfDimConstraint1.length - 1) : j];
      } else {
        arrayOfDimConstraint2[b] = DOCK_DIM_CONSTRAINT;
      } 
      ArrayList arrayList = arrayOfArrayList[b];
      int[] arrayOfInt4 = { getTotalGroupsSizeParallel(arrayList, 0, false), getTotalGroupsSizeParallel(arrayList, 1, false), 2097051 };
      correctMinMax(arrayOfInt4);
      BoundSize boundSize1 = arrayOfDimConstraint2[b].getSize();
      for (byte b1 = 0; b1 <= 2; b1++) {
        int k = arrayOfInt4[b1];
        UnitValue unitValue = boundSize1.getSize(b1);
        if (unitValue != null) {
          int m = unitValue.getUnit();
          if (m == 14) {
            k = arrayOfInt4[1];
          } else if (m == 13) {
            k = arrayOfInt4[0];
          } else if (m == 15) {
            k = arrayOfInt4[2];
          } else {
            k = unitValue.getPixels(i, this.container, null);
          } 
        } else if (j >= -30000 && j <= 30000 && k == 0) {
          k = LayoutUtil.isDesignTime(this.container) ? LayoutUtil.getDesignTimeEmptySize() : 0;
        } 
        arrayOfInt3[b1] = k;
      } 
      correctMinMax(arrayOfInt3);
      addToSizeGroup(hashMap, arrayOfDimConstraint2[b].getSizeGroup(), arrayOfInt3);
      arrayOfInt1[b] = arrayOfInt3;
    } 
    if (hashMap.size() > 0)
      for (b = 0; b < arrayOfInt1.length; b++) {
        if (arrayOfDimConstraint2[b].getSizeGroup() != null)
          arrayOfInt1[b] = (int[])hashMap.get(arrayOfDimConstraint2[b].getSizeGroup()); 
      }  
    ResizeConstraint[] arrayOfResizeConstraint = getRowResizeConstraints(arrayOfDimConstraint2);
    boolean[] arrayOfBoolean = new boolean[arrayOfDimConstraint2.length + 1];
    int[][] arrayOfInt2 = getRowGaps(arrayOfDimConstraint2, i, paramBoolean, arrayOfBoolean);
    FlowSizeSpec flowSizeSpec = mergeSizesGapsAndResConstrs(arrayOfResizeConstraint, arrayOfBoolean, arrayOfInt1, arrayOfInt2);
    adjustMinPrefForSpanningComps(arrayOfDimConstraint2, arrayOfFloat, flowSizeSpec, arrayOfArrayList);
    return flowSizeSpec;
  }
  
  private static int getParentSize(ComponentWrapper paramComponentWrapper, boolean paramBoolean) {
    ContainerWrapper containerWrapper = paramComponentWrapper.getParent();
    return (containerWrapper != null) ? (paramBoolean ? paramComponentWrapper.getWidth() : paramComponentWrapper.getHeight()) : 0;
  }
  
  private int[] getMinPrefMaxSumSize(boolean paramBoolean) {
    int[][] arrayOfInt = paramBoolean ? this.colFlowSpecs.sizes : this.rowFlowSpecs.sizes;
    int[] arrayOfInt1 = new int[3];
    BoundSize boundSize = paramBoolean ? this.lc.getWidth() : this.lc.getHeight();
    for (byte b = 0; b < arrayOfInt.length; b++) {
      if (arrayOfInt[b] != null) {
        int[] arrayOfInt2 = arrayOfInt[b];
        for (byte b1 = 0; b1 <= 2; b1++) {
          if (boundSize.getSize(b1) != null) {
            if (!b)
              arrayOfInt1[b1] = boundSize.getSize(b1).getPixels(getParentSize(this.container, paramBoolean), this.container, null); 
          } else {
            int i = arrayOfInt2[b1];
            if (i != -2147471302) {
              if (b1 == 1) {
                int j = arrayOfInt2[2];
                if (j != -2147471302 && j < i)
                  i = j; 
                j = arrayOfInt2[0];
                if (j > i)
                  i = j; 
              } 
              arrayOfInt1[b1] = arrayOfInt1[b1] + i;
            } 
            if (arrayOfInt2[2] == -2147471302 || arrayOfInt1[2] > 2097051)
              arrayOfInt1[2] = 2097051; 
          } 
        } 
      } 
    } 
    correctMinMax(arrayOfInt1);
    return arrayOfInt1;
  }
  
  private static ResizeConstraint[] getRowResizeConstraints(DimConstraint[] paramArrayOfDimConstraint) {
    ResizeConstraint[] arrayOfResizeConstraint = new ResizeConstraint[paramArrayOfDimConstraint.length];
    for (byte b = 0; b < arrayOfResizeConstraint.length; b++)
      arrayOfResizeConstraint[b] = (paramArrayOfDimConstraint[b]).resize; 
    return arrayOfResizeConstraint;
  }
  
  private static ResizeConstraint[] getComponentResizeConstraints(ArrayList<CompWrap> paramArrayList, boolean paramBoolean) {
    ResizeConstraint[] arrayOfResizeConstraint = new ResizeConstraint[paramArrayList.size()];
    for (byte b = 0; b < arrayOfResizeConstraint.length; b++) {
      CC cC = ((CompWrap)paramArrayList.get(b)).cc;
      arrayOfResizeConstraint[b] = (cC.getDimConstraint(paramBoolean)).resize;
      int i = cC.getDockSide();
      if (paramBoolean ? (i == 0 || i == 2) : (i == 1 || i == 3)) {
        ResizeConstraint resizeConstraint = arrayOfResizeConstraint[b];
        arrayOfResizeConstraint[b] = new ResizeConstraint(resizeConstraint.shrinkPrio, resizeConstraint.shrink, resizeConstraint.growPrio, ResizeConstraint.WEIGHT_100);
      } 
    } 
    return arrayOfResizeConstraint;
  }
  
  private static boolean[] getComponentGapPush(ArrayList<CompWrap> paramArrayList, boolean paramBoolean) {
    boolean[] arrayOfBoolean = new boolean[paramArrayList.size() + 1];
    for (byte b = 0; b < arrayOfBoolean.length; b++) {
      boolean bool = b ? ((CompWrap)paramArrayList.get(b - true)).isPushGap(paramBoolean, false) : 0;
      if (!bool && b < arrayOfBoolean.length - 1)
        bool = ((CompWrap)paramArrayList.get(b)).isPushGap(paramBoolean, true); 
      arrayOfBoolean[b] = bool;
    } 
    return arrayOfBoolean;
  }
  
  private int[][] getRowGaps(DimConstraint[] paramArrayOfDimConstraint, int paramInt, boolean paramBoolean, boolean[] paramArrayOfBoolean) {
    BoundSize boundSize = paramBoolean ? this.lc.getGridGapX() : this.lc.getGridGapY();
    if (boundSize == null)
      boundSize = paramBoolean ? PlatformDefaults.getGridGapX() : PlatformDefaults.getGridGapY(); 
    int[] arrayOfInt = boundSize.getPixelSizes(paramInt, this.container, null);
    boolean bool = !hasDocks();
    UnitValue unitValue1 = LayoutUtil.getInsets(this.lc, paramBoolean ? 1 : 0, bool);
    UnitValue unitValue2 = LayoutUtil.getInsets(this.lc, paramBoolean ? 3 : 2, bool);
    int[][] arrayOfInt1 = new int[paramArrayOfDimConstraint.length + 1][];
    byte b1 = 0;
    byte b2 = 0;
    while (b1 < arrayOfInt1.length) {
      DimConstraint dimConstraint1 = b1 ? paramArrayOfDimConstraint[b1 - true] : null;
      DimConstraint dimConstraint2 = (b1 < paramArrayOfDimConstraint.length) ? paramArrayOfDimConstraint[b1] : null;
      boolean bool1 = (dimConstraint1 == DOCK_DIM_CONSTRAINT || dimConstraint1 == null) ? 1 : 0;
      boolean bool2 = (dimConstraint2 == DOCK_DIM_CONSTRAINT || dimConstraint2 == null) ? 1 : 0;
      if (!bool1 || !bool2) {
        BoundSize boundSize1 = (this.wrapGapMap == null || paramBoolean == this.lc.isFlowX()) ? null : (BoundSize)this.wrapGapMap.get(Integer.valueOf(b2++));
        if (boundSize1 == null) {
          int[] arrayOfInt2 = (dimConstraint1 != null) ? dimConstraint1.getRowGaps(this.container, null, paramInt, false) : null;
          int[] arrayOfInt3 = (dimConstraint2 != null) ? dimConstraint2.getRowGaps(this.container, null, paramInt, true) : null;
          if (bool1 && arrayOfInt3 == null && unitValue1 != null) {
            int i = unitValue1.getPixels(paramInt, this.container, null);
            new int[3][0] = i;
            new int[3][1] = i;
            new int[3][2] = i;
            arrayOfInt1[b1] = new int[3];
          } else if (bool2 && arrayOfInt2 == null && unitValue1 != null) {
            int i = unitValue2.getPixels(paramInt, this.container, null);
            new int[3][0] = i;
            new int[3][1] = i;
            new int[3][2] = i;
            arrayOfInt1[b1] = new int[3];
          } else {
            new int[3][0] = arrayOfInt[0];
            new int[3][1] = arrayOfInt[1];
            new int[3][2] = arrayOfInt[2];
            arrayOfInt1[b1] = (arrayOfInt3 != arrayOfInt2) ? mergeSizes(arrayOfInt3, arrayOfInt2) : new int[3];
          } 
          if ((dimConstraint1 != null && dimConstraint1.isGapAfterPush()) || (dimConstraint2 != null && dimConstraint2.isGapBeforePush()))
            paramArrayOfBoolean[b1] = true; 
        } else {
          if (boundSize1.isUnset()) {
            new int[3][0] = arrayOfInt[0];
            new int[3][1] = arrayOfInt[1];
            new int[3][2] = arrayOfInt[2];
            arrayOfInt1[b1] = new int[3];
          } else {
            arrayOfInt1[b1] = boundSize1.getPixelSizes(paramInt, this.container, null);
          } 
          paramArrayOfBoolean[b1] = boundSize1.getGapPush();
        } 
      } 
      b1++;
    } 
    return arrayOfInt1;
  }
  
  private static int[][] getGaps(ArrayList<CompWrap> paramArrayList, boolean paramBoolean) {
    int i = paramArrayList.size();
    int[][] arrayOfInt = new int[i + 1][];
    arrayOfInt[0] = ((CompWrap)paramArrayList.get(0)).getGaps(paramBoolean, true);
    for (byte b = 0; b < i; b++) {
      int[] arrayOfInt1 = ((CompWrap)paramArrayList.get(b)).getGaps(paramBoolean, false);
      int[] arrayOfInt2 = (b < i - 1) ? ((CompWrap)paramArrayList.get(b + 1)).getGaps(paramBoolean, true) : null;
      arrayOfInt[b + 1] = mergeSizes(arrayOfInt1, arrayOfInt2);
    } 
    return arrayOfInt;
  }
  
  private boolean hasDocks() { return (this.dockOffX > 0 || this.dockOffY > 0 || ((Integer)this.rowIndexes.last()).intValue() > 30000 || ((Integer)this.colIndexes.last()).intValue() > 30000); }
  
  private void adjustMinPrefForSpanningComps(DimConstraint[] paramArrayOfDimConstraint, Float[] paramArrayOfFloat, FlowSizeSpec paramFlowSizeSpec, ArrayList<LinkedDimGroup>[] paramArrayOfArrayList) {
    for (int i = paramArrayOfArrayList.length - 1; i >= 0; i--) {
      ArrayList<LinkedDimGroup> arrayList = paramArrayOfArrayList[i];
      for (byte b = 0; b < arrayList.size(); b++) {
        LinkedDimGroup linkedDimGroup;
        if (linkedDimGroup.span != 1) {
          int[] arrayOfInt = linkedDimGroup.getMinPrefMax();
          for (byte b1 = 0; b1 <= 1; b1++) {
            int j = arrayOfInt[b1];
            if (j != -2147471302) {
              int k = 0;
              int m = (i << 1) + 1;
              int n = Math.min(linkedDimGroup.span << 1, paramFlowSizeSpec.sizes.length - m) - 1;
              int i1;
              for (i1 = m; i1 < m + n; i1++) {
                int i2 = paramFlowSizeSpec.sizes[i1][b1];
                if (i2 != -2147471302)
                  k += i2; 
              } 
              if (k < j) {
                i1 = 0;
                int i2 = 0;
                while (i1 < 4 && i2 < j) {
                  i2 = paramFlowSizeSpec.expandSizes(paramArrayOfDimConstraint, paramArrayOfFloat, j, m, n, b1, i1);
                  i1++;
                } 
              } 
            } 
          } 
        } 
      } 
    } 
  }
  
  private ArrayList<LinkedDimGroup>[] divideIntoLinkedGroups(boolean paramBoolean) {
    if (paramBoolean ? this.lc.isTopToBottom() : LayoutUtil.isLeftToRight(this.lc, this.container)) {
    
    } else {
    
    } 
    boolean bool = true;
    TreeSet treeSet1 = paramBoolean ? this.rowIndexes : this.colIndexes;
    TreeSet treeSet2 = paramBoolean ? this.colIndexes : this.rowIndexes;
    DimConstraint[] arrayOfDimConstraint = (paramBoolean ? this.rowConstr : this.colConstr).getConstaints();
    ArrayList[] arrayOfArrayList = new ArrayList[treeSet1.size()];
    byte b = 0;
    Iterator iterator = treeSet1.iterator();
    while (iterator.hasNext()) {
      DimConstraint dimConstraint;
      int i = ((Integer)iterator.next()).intValue();
      if (i >= -30000 && i <= 30000) {
        dimConstraint = arrayOfDimConstraint[(i >= arrayOfDimConstraint.length) ? (arrayOfDimConstraint.length - 1) : i];
      } else {
        dimConstraint = DOCK_DIM_CONSTRAINT;
      } 
      ArrayList arrayList = new ArrayList(2);
      arrayOfArrayList[b++] = arrayList;
      Iterator iterator1 = treeSet2.iterator();
      while (iterator1.hasNext()) {
        int j = ((Integer)iterator1.next()).intValue();
        Cell cell;
        if (cell == null || cell.compWraps.size() == 0)
          continue; 
        int k = paramBoolean ? cell.spany : cell.spanx;
        if (k > 1)
          k = convertSpanToSparseGrid(i, k, treeSet1); 
        boolean bool1 = (cell.flowx == paramBoolean) ? 1 : 0;
        if ((!bool1 && cell.compWraps.size() > 1) || k > 1) {
          byte b2 = bool1 ? 1 : 0;
          LinkedDimGroup linkedDimGroup;
          linkedDimGroup.setCompWraps(cell.compWraps);
          arrayList.add(linkedDimGroup);
          continue;
        } 
        for (byte b1 = 0; b1 < cell.compWraps.size(); b1++) {
          CompWrap compWrap = (CompWrap)cell.compWraps.get(b1);
          boolean bool2 = (paramBoolean && this.lc.isTopToBottom() && dimConstraint.getAlignOrDefault(!paramBoolean ? true : false) == UnitValue.BASELINE_IDENTITY);
          boolean bool3 = (paramBoolean && compWrap.isBaselineAlign(bool2)) ? 1 : 0;
          String str = bool3 ? "baseline" : null;
          boolean bool4 = false;
          byte b2 = 0;
          int m = arrayList.size() - 1;
          while (b2 <= m) {
            LinkedDimGroup linkedDimGroup;
            if (linkedDimGroup.linkCtx == str || (str != null && str.equals(linkedDimGroup.linkCtx))) {
              linkedDimGroup.addCompWrap(compWrap);
              bool4 = true;
              break;
            } 
            b2++;
          } 
          if (!bool4) {
            b2 = bool3 ? 2 : 1;
            LinkedDimGroup linkedDimGroup;
            linkedDimGroup.addCompWrap(compWrap);
            arrayList.add(linkedDimGroup);
          } 
        } 
      } 
    } 
    return arrayOfArrayList;
  }
  
  private static int convertSpanToSparseGrid(int paramInt1, int paramInt2, TreeSet<Integer> paramTreeSet) {
    int i = paramInt1 + paramInt2;
    byte b = 1;
    Iterator iterator = paramTreeSet.iterator();
    while (iterator.hasNext()) {
      int j = ((Integer)iterator.next()).intValue();
      if (j <= paramInt1)
        continue; 
      if (j >= i)
        break; 
      b++;
    } 
    return b;
  }
  
  private final boolean isCellFree(int paramInt1, int paramInt2, ArrayList<int[]> paramArrayList) {
    if (getCell(paramInt1, paramInt2) != null)
      return false; 
    for (byte b = 0; b < paramArrayList.size(); b++) {
      int[] arrayOfInt = (int[])paramArrayList.get(b);
      if (arrayOfInt[0] <= paramInt2 && arrayOfInt[1] <= paramInt1 && arrayOfInt[0] + arrayOfInt[2] > paramInt2 && arrayOfInt[1] + arrayOfInt[3] > paramInt1)
        return false; 
    } 
    return true;
  }
  
  private Cell getCell(int paramInt1, int paramInt2) { return (Cell)this.grid.get(Integer.valueOf((paramInt1 << 16) + paramInt2)); }
  
  private void setCell(int paramInt1, int paramInt2, Cell paramCell) {
    if (paramInt2 < 0 || paramInt2 > 30000 || paramInt1 < 0 || paramInt1 > 30000)
      throw new IllegalArgumentException("Cell position out of bounds. row: " + paramInt1 + ", col: " + paramInt2); 
    this.rowIndexes.add(Integer.valueOf(paramInt1));
    this.colIndexes.add(Integer.valueOf(paramInt2));
    this.grid.put(Integer.valueOf((paramInt1 << 16) + paramInt2), paramCell);
  }
  
  private void addDockingCell(int[] paramArrayOfInt, int paramInt, CompWrap paramCompWrap) {
    int j;
    int i;
    int k = 1;
    int m = 1;
    switch (paramInt) {
      case 0:
      case 2:
        paramArrayOfInt[0] = paramArrayOfInt[0] + 1;
        paramArrayOfInt[2] = paramArrayOfInt[2] - 1;
        i = (paramInt == 0) ? paramArrayOfInt[0] : paramArrayOfInt[2];
        j = paramArrayOfInt[1];
        k = paramArrayOfInt[3] - paramArrayOfInt[1] + 1;
        this.colIndexes.add(Integer.valueOf(paramArrayOfInt[3]));
        break;
      case 1:
      case 3:
        paramArrayOfInt[1] = paramArrayOfInt[1] + 1;
        paramArrayOfInt[3] = paramArrayOfInt[3] - 1;
        j = (paramInt == 1) ? paramArrayOfInt[1] : paramArrayOfInt[3];
        i = paramArrayOfInt[0];
        m = paramArrayOfInt[2] - paramArrayOfInt[0] + 1;
        this.rowIndexes.add(Integer.valueOf(paramArrayOfInt[2]));
        break;
      default:
        throw new IllegalArgumentException("Internal error 123.");
    } 
    this.rowIndexes.add(Integer.valueOf(i));
    this.colIndexes.add(Integer.valueOf(j));
    this.grid.put(Integer.valueOf((i << 16) + j), new Cell(paramCompWrap, k, m, (k > 1) ? 1 : 0, null));
  }
  
  private static void layoutBaseline(ContainerWrapper paramContainerWrapper, ArrayList<CompWrap> paramArrayList, DimConstraint paramDimConstraint, int paramInt1, int paramInt2, int paramInt3, int paramInt4) {
    int[] arrayOfInt = getBaselineAboveBelow(paramArrayList, paramInt3, true);
    int i = arrayOfInt[0] + arrayOfInt[1];
    CC cC = ((CompWrap)paramArrayList.get(0)).cc;
    UnitValue unitValue = cC.getVertical().getAlign();
    if (paramInt4 == 1 && unitValue == null)
      unitValue = paramDimConstraint.getAlignOrDefault(false); 
    if (unitValue == UnitValue.BASELINE_IDENTITY)
      unitValue = UnitValue.CENTER; 
    int j = paramInt1 + arrayOfInt[0] + ((unitValue != null) ? Math.max(0, unitValue.getPixels((paramInt2 - i), paramContainerWrapper, null)) : 0);
    byte b = 0;
    int k = paramArrayList.size();
    while (b < k) {
      CompWrap compWrap;
      compWrap.y += j;
      if (compWrap.y + compWrap.h > paramInt1 + paramInt2)
        compWrap.h = paramInt1 + paramInt2 - compWrap.y; 
      b++;
    } 
  }
  
  private static void layoutSerial(ContainerWrapper paramContainerWrapper, ArrayList<CompWrap> paramArrayList, DimConstraint paramDimConstraint, int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2) {
    FlowSizeSpec flowSizeSpec = mergeSizesGapsAndResConstrs(getComponentResizeConstraints(paramArrayList, paramBoolean1), getComponentGapPush(paramArrayList, paramBoolean1), getComponentSizes(paramArrayList, paramBoolean1), getGaps(paramArrayList, paramBoolean1));
    Float[] arrayOfFloat = paramDimConstraint.isFill() ? GROW_100 : null;
    int[] arrayOfInt = LayoutUtil.calculateSerial(flowSizeSpec.sizes, flowSizeSpec.resConstsInclGaps, arrayOfFloat, 1, paramInt2);
    setCompWrapBounds(paramContainerWrapper, arrayOfInt, paramArrayList, paramDimConstraint.getAlignOrDefault(paramBoolean1), paramInt1, paramInt2, paramBoolean1, paramBoolean2);
  }
  
  private static void setCompWrapBounds(ContainerWrapper paramContainerWrapper, int[] paramArrayOfInt, ArrayList<CompWrap> paramArrayList, UnitValue paramUnitValue, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    int i = LayoutUtil.sum(paramArrayOfInt);
    CC cC = ((CompWrap)paramArrayList.get(0)).cc;
    UnitValue unitValue = correctAlign(cC, paramUnitValue, paramBoolean1, paramBoolean2);
    int j = paramInt1;
    int k = paramInt2 - i;
    if (k > 0 && unitValue != null) {
      int n = Math.min(k, Math.max(0, unitValue.getPixels(k, paramContainerWrapper, null)));
      j += (paramBoolean2 ? -n : n);
    } 
    byte b1 = 0;
    byte b2 = 0;
    int m = paramArrayList.size();
    while (b1 < m) {
      CompWrap compWrap = (CompWrap)paramArrayList.get(b1);
      if (paramBoolean2) {
        j -= paramArrayOfInt[b2++];
        compWrap.setDimBounds(j - paramArrayOfInt[b2], paramArrayOfInt[b2], paramBoolean1);
        j -= paramArrayOfInt[b2++];
      } else {
        j += paramArrayOfInt[b2++];
        compWrap.setDimBounds(j, paramArrayOfInt[b2], paramBoolean1);
        j += paramArrayOfInt[b2++];
      } 
      b1++;
    } 
  }
  
  private static void layoutParallel(ContainerWrapper paramContainerWrapper, ArrayList<CompWrap> paramArrayList, DimConstraint paramDimConstraint, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    int[][] arrayOfInt = new int[paramArrayList.size()][];
    for (byte b = 0; b < arrayOfInt.length; b++) {
      CompWrap compWrap;
      DimConstraint dimConstraint = compWrap.cc.getDimConstraint(paramBoolean1);
      ResizeConstraint[] arrayOfResizeConstraint = { compWrap.isPushGap(paramBoolean1, true) ? GAP_RC_CONST_PUSH : GAP_RC_CONST, dimConstraint.resize, compWrap.isPushGap(paramBoolean1, false) ? GAP_RC_CONST_PUSH : GAP_RC_CONST };
      int[][] arrayOfInt1 = { compWrap.getGaps(paramBoolean1, true), paramBoolean1 ? compWrap.horSizes : compWrap.verSizes, compWrap.getGaps(paramBoolean1, false) };
      Float[] arrayOfFloat = paramDimConstraint.isFill() ? GROW_100 : null;
      arrayOfInt[b] = LayoutUtil.calculateSerial(arrayOfInt1, arrayOfResizeConstraint, arrayOfFloat, 1, paramInt2);
    } 
    UnitValue unitValue = paramDimConstraint.getAlignOrDefault(paramBoolean1);
    setCompWrapBounds(paramContainerWrapper, arrayOfInt, paramArrayList, unitValue, paramInt1, paramInt2, paramBoolean1, paramBoolean2);
  }
  
  private static void setCompWrapBounds(ContainerWrapper paramContainerWrapper, int[][] paramArrayOfInt, ArrayList<CompWrap> paramArrayList, UnitValue paramUnitValue, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2) {
    for (byte b = 0; b < paramArrayOfInt.length; b++) {
      CompWrap compWrap;
      UnitValue unitValue = correctAlign(compWrap.cc, paramUnitValue, paramBoolean1, paramBoolean2);
      int[] arrayOfInt = paramArrayOfInt[b];
      int i = arrayOfInt[0];
      int j = arrayOfInt[1];
      int k = arrayOfInt[2];
      int m = paramBoolean2 ? (paramInt1 - i) : (paramInt1 + i);
      int n = paramInt2 - j - i - k;
      if (n > 0 && unitValue != null) {
        int i1 = Math.min(n, Math.max(0, unitValue.getPixels(n, paramContainerWrapper, null)));
        m += (paramBoolean2 ? -i1 : i1);
      } 
      compWrap.setDimBounds(paramBoolean2 ? (m - j) : m, j, paramBoolean1);
    } 
  }
  
  private static UnitValue correctAlign(CC paramCC, UnitValue paramUnitValue, boolean paramBoolean1, boolean paramBoolean2) {
    UnitValue unitValue = (paramBoolean1 ? paramCC.getHorizontal() : paramCC.getVertical()).getAlign();
    if (unitValue == null)
      unitValue = paramUnitValue; 
    if (unitValue == UnitValue.BASELINE_IDENTITY)
      unitValue = UnitValue.CENTER; 
    if (paramBoolean2)
      if (unitValue == UnitValue.LEFT) {
        unitValue = UnitValue.RIGHT;
      } else if (unitValue == UnitValue.RIGHT) {
        unitValue = UnitValue.LEFT;
      }  
    return unitValue;
  }
  
  private static int[] getBaselineAboveBelow(ArrayList<CompWrap> paramArrayList, int paramInt, boolean paramBoolean) {
    int i = -32768;
    int j = -32768;
    byte b = 0;
    int k = paramArrayList.size();
    while (b < k) {
      CompWrap compWrap;
      int m = compWrap.getSize(paramInt, false);
      if (m >= 2097051)
        return new int[] { 1048525, 1048525 }; 
      int n = compWrap.getBaseline(paramInt);
      int i1 = n + compWrap.getGapBefore(paramInt, false);
      i = Math.max(i1, i);
      j = Math.max(m - n + compWrap.getGapAfter(paramInt, false), j);
      if (paramBoolean)
        compWrap.setDimBounds(-n, m, false); 
      b++;
    } 
    return new int[] { i, j };
  }
  
  private static int getTotalSizeParallel(ArrayList<CompWrap> paramArrayList, int paramInt, boolean paramBoolean) {
    int i = (paramInt == 2) ? 2097051 : 0;
    byte b = 0;
    int j = paramArrayList.size();
    while (b < j) {
      CompWrap compWrap;
      int k = compWrap.getSizeInclGaps(paramInt, paramBoolean);
      if (k >= 2097051)
        return 2097051; 
      if ((paramInt == 2) ? (k < i) : (k > i))
        i = k; 
      b++;
    } 
    return constrainSize(i);
  }
  
  private static int getTotalSizeSerial(ArrayList<CompWrap> paramArrayList, int paramInt, boolean paramBoolean) {
    int i = 0;
    byte b = 0;
    int j = paramArrayList.size();
    int k = 0;
    while (b < j) {
      CompWrap compWrap;
      int m = compWrap.getGapBefore(paramInt, paramBoolean);
      if (m > k)
        i += m - k; 
      i += compWrap.getSize(paramInt, paramBoolean);
      i += (k = compWrap.getGapAfter(paramInt, paramBoolean));
      if (i >= 2097051)
        return 2097051; 
      b++;
    } 
    return constrainSize(i);
  }
  
  private static int getTotalGroupsSizeParallel(ArrayList<LinkedDimGroup> paramArrayList, int paramInt, boolean paramBoolean) {
    int i = (paramInt == 2) ? 2097051 : 0;
    byte b = 0;
    int j = paramArrayList.size();
    while (b < j) {
      LinkedDimGroup linkedDimGroup;
      if (paramBoolean || linkedDimGroup.span == 1) {
        int k = linkedDimGroup.getMinPrefMax()[paramInt];
        if (k >= 2097051)
          return 2097051; 
        if ((paramInt == 2) ? (k < i) : (k > i))
          i = k; 
      } 
      b++;
    } 
    return constrainSize(i);
  }
  
  private static int[][] getComponentSizes(ArrayList<CompWrap> paramArrayList, boolean paramBoolean) {
    int[][] arrayOfInt = new int[paramArrayList.size()][];
    for (byte b = 0; b < arrayOfInt.length; b++) {
      CompWrap compWrap;
      arrayOfInt[b] = paramBoolean ? compWrap.horSizes : compWrap.verSizes;
    } 
    return arrayOfInt;
  }
  
  private static FlowSizeSpec mergeSizesGapsAndResConstrs(ResizeConstraint[] paramArrayOfResizeConstraint, boolean[] paramArrayOfBoolean, int[][] paramArrayOfInt1, int[][] paramArrayOfInt2) {
    int[][] arrayOfInt = new int[(paramArrayOfInt1.length << 1) + 1][];
    ResizeConstraint[] arrayOfResizeConstraint = new ResizeConstraint[arrayOfInt.length];
    arrayOfInt[0] = paramArrayOfInt2[0];
    byte b = 0;
    for (boolean bool = true; b < paramArrayOfInt1.length; bool += true) {
      arrayOfResizeConstraint[bool] = paramArrayOfResizeConstraint[b];
      arrayOfInt[bool] = paramArrayOfInt1[b];
      arrayOfInt[bool + true] = paramArrayOfInt2[b + true];
      if (arrayOfInt[bool - true] != null)
        arrayOfResizeConstraint[bool - true] = paramArrayOfBoolean[(b < paramArrayOfBoolean.length) ? b : (paramArrayOfBoolean.length - 1)] ? GAP_RC_CONST_PUSH : GAP_RC_CONST; 
      if (b == paramArrayOfInt1.length - 1 && arrayOfInt[bool + true] != null)
        arrayOfResizeConstraint[bool + true] = paramArrayOfBoolean[(b + true < paramArrayOfBoolean.length) ? (b + true) : (paramArrayOfBoolean.length - 1)] ? GAP_RC_CONST_PUSH : GAP_RC_CONST; 
      b++;
    } 
    for (b = 0; b < arrayOfInt.length; b++) {
      if (arrayOfInt[b] == null)
        arrayOfInt[b] = new int[3]; 
    } 
    return new FlowSizeSpec(arrayOfInt, arrayOfResizeConstraint, null);
  }
  
  private static int[] mergeSizes(int[] paramArrayOfInt1, int[] paramArrayOfInt2) {
    if (paramArrayOfInt1 == null)
      return paramArrayOfInt2; 
    if (paramArrayOfInt2 == null)
      return paramArrayOfInt1; 
    int[] arrayOfInt = new int[paramArrayOfInt1.length];
    for (byte b = 0; b < arrayOfInt.length; b++)
      arrayOfInt[b] = mergeSizes(paramArrayOfInt1[b], paramArrayOfInt2[b], true); 
    return arrayOfInt;
  }
  
  private static int mergeSizes(int paramInt1, int paramInt2, boolean paramBoolean) { return (paramInt1 == -2147471302 || paramInt1 == paramInt2) ? paramInt2 : ((paramInt2 == -2147471302) ? paramInt1 : ((paramBoolean != ((paramInt1 > paramInt2))) ? paramInt2 : paramInt1)); }
  
  private static int constrainSize(int paramInt) { return (paramInt > 0) ? ((paramInt < 2097051) ? paramInt : 2097051) : 0; }
  
  private static void correctMinMax(int[] paramArrayOfInt) {
    if (paramArrayOfInt[0] > paramArrayOfInt[2])
      paramArrayOfInt[0] = paramArrayOfInt[2]; 
    if (paramArrayOfInt[1] < paramArrayOfInt[0])
      paramArrayOfInt[1] = paramArrayOfInt[0]; 
    if (paramArrayOfInt[1] > paramArrayOfInt[2])
      paramArrayOfInt[1] = paramArrayOfInt[2]; 
  }
  
  private static Float[] extractSubArray(DimConstraint[] paramArrayOfDimConstraint, Float[] paramArrayOfFloat, int paramInt1, int paramInt2) {
    if (paramArrayOfFloat == null || paramArrayOfFloat.length < paramInt1 + paramInt2) {
      Float[] arrayOfFloat1 = new Float[paramInt2];
      for (int j = paramInt1 + paramInt2 - 1; j >= 0; j -= 2) {
        int k = j >> 1;
        if (paramArrayOfDimConstraint[k] != DOCK_DIM_CONSTRAINT) {
          arrayOfFloat1[j - paramInt1] = ResizeConstraint.WEIGHT_100;
          return arrayOfFloat1;
        } 
      } 
      return arrayOfFloat1;
    } 
    Float[] arrayOfFloat = new Float[paramInt2];
    for (int i = 0; i < paramInt2; i++)
      arrayOfFloat[i] = paramArrayOfFloat[paramInt1 + i]; 
    return arrayOfFloat;
  }
  
  private static void putSizesAndIndexes(Object paramObject, int[] paramArrayOfInt1, int[] paramArrayOfInt2, boolean paramBoolean) {
    if (PARENT_ROWCOL_SIZES_MAP == null)
      PARENT_ROWCOL_SIZES_MAP = new WeakHashMap[] { new WeakHashMap(4), new WeakHashMap(4) }; 
    PARENT_ROWCOL_SIZES_MAP[paramBoolean ? 0 : 1].put(paramObject, new int[][] { paramArrayOfInt2, paramArrayOfInt1 });
  }
  
  static int[][] getSizesAndIndexes(Object paramObject, boolean paramBoolean) { return (PARENT_ROWCOL_SIZES_MAP == null) ? (int[][])null : (int[][])PARENT_ROWCOL_SIZES_MAP[paramBoolean ? 0 : 1].get(paramObject); }
  
  private static void saveGrid(ComponentWrapper paramComponentWrapper, LinkedHashMap<Integer, Cell> paramLinkedHashMap) {
    if (PARENT_GRIDPOS_MAP == null)
      PARENT_GRIDPOS_MAP = new WeakHashMap(); 
    PARENT_GRIDPOS_MAP.put(paramComponentWrapper.getComponent(), paramLinkedHashMap);
  }
  
  static HashMap<Object, int[]> getGridPositions(Object paramObject) {
    if (PARENT_GRIDPOS_MAP == null)
      return null; 
    LinkedHashMap linkedHashMap = (LinkedHashMap)PARENT_GRIDPOS_MAP.get(paramObject);
    if (linkedHashMap == null)
      return null; 
    HashMap hashMap = new HashMap();
    for (Map.Entry entry : linkedHashMap.entrySet()) {
      Cell cell = (Cell)entry.getValue();
      Integer integer = (Integer)entry.getKey();
      if (integer != null) {
        int i = integer.intValue();
        int j = i & 0xFFFF;
        int k = i >> 16;
        Iterator iterator = cell.compWraps.iterator();
        while (iterator.hasNext()) {
          CompWrap compWrap;
          hashMap.put(compWrap.comp.getComponent(), new int[] { j, k, Cell.access$500(cell), Cell.access$600(cell) });
        } 
      } 
    } 
    return hashMap;
  }
  
  static  {
    DOCK_DIM_CONSTRAINT.setGrowPriority(0);
    GAP_RC_CONST = new ResizeConstraint('È', ResizeConstraint.WEIGHT_100, 50, null);
    GAP_RC_CONST_PUSH = new ResizeConstraint('È', ResizeConstraint.WEIGHT_100, 50, ResizeConstraint.WEIGHT_100);
    PARENT_ROWCOL_SIZES_MAP = null;
    PARENT_GRIDPOS_MAP = null;
  }
  
  private static final class FlowSizeSpec {
    private final int[][] sizes;
    
    private final ResizeConstraint[] resConstsInclGaps;
    
    private FlowSizeSpec(int[][] param1ArrayOfInt, ResizeConstraint[] param1ArrayOfResizeConstraint) {
      this.sizes = param1ArrayOfInt;
      this.resConstsInclGaps = param1ArrayOfResizeConstraint;
    }
    
    private int expandSizes(DimConstraint[] param1ArrayOfDimConstraint, Float[] param1ArrayOfFloat, int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5) { // Byte code:
      //   0: iload #5
      //   2: anewarray net/miginfocom/layout/ResizeConstraint
      //   5: astore #8
      //   7: iload #5
      //   9: anewarray [I
      //   12: astore #9
      //   14: iconst_0
      //   15: istore #10
      //   17: iload #10
      //   19: iload #5
      //   21: if_icmpge -> 190
      //   24: aload_0
      //   25: getfield sizes : [[I
      //   28: iload #10
      //   30: iload #4
      //   32: iadd
      //   33: aaload
      //   34: astore #11
      //   36: aload #9
      //   38: iload #10
      //   40: iconst_3
      //   41: newarray int
      //   43: dup
      //   44: iconst_0
      //   45: aload #11
      //   47: iload #6
      //   49: iaload
      //   50: iastore
      //   51: dup
      //   52: iconst_1
      //   53: aload #11
      //   55: iconst_1
      //   56: iaload
      //   57: iastore
      //   58: dup
      //   59: iconst_2
      //   60: aload #11
      //   62: iconst_2
      //   63: iaload
      //   64: iastore
      //   65: aastore
      //   66: iload #7
      //   68: iconst_1
      //   69: if_icmpgt -> 164
      //   72: iload #10
      //   74: iconst_2
      //   75: irem
      //   76: ifne -> 164
      //   79: iload #10
      //   81: iload #4
      //   83: iadd
      //   84: iconst_1
      //   85: isub
      //   86: iconst_1
      //   87: ishr
      //   88: istore #12
      //   90: aload_1
      //   91: iload #12
      //   93: invokestatic getIndexSafe : ([Ljava/lang/Object;I)Ljava/lang/Object;
      //   96: checkcast net/miginfocom/layout/DimConstraint
      //   99: astore #13
      //   101: aload #13
      //   103: invokevirtual getSize : ()Lnet/miginfocom/layout/BoundSize;
      //   106: astore #14
      //   108: iload #6
      //   110: ifne -> 134
      //   113: aload #14
      //   115: invokevirtual getMin : ()Lnet/miginfocom/layout/UnitValue;
      //   118: ifnull -> 134
      //   121: aload #14
      //   123: invokevirtual getMin : ()Lnet/miginfocom/layout/UnitValue;
      //   126: invokevirtual getUnit : ()I
      //   129: bipush #13
      //   131: if_icmpne -> 184
      //   134: iload #6
      //   136: iconst_1
      //   137: if_icmpne -> 164
      //   140: aload #14
      //   142: invokevirtual getPreferred : ()Lnet/miginfocom/layout/UnitValue;
      //   145: ifnull -> 164
      //   148: aload #14
      //   150: invokevirtual getPreferred : ()Lnet/miginfocom/layout/UnitValue;
      //   153: invokevirtual getUnit : ()I
      //   156: bipush #14
      //   158: if_icmpeq -> 164
      //   161: goto -> 184
      //   164: aload #8
      //   166: iload #10
      //   168: aload_0
      //   169: getfield resConstsInclGaps : [Lnet/miginfocom/layout/ResizeConstraint;
      //   172: iload #10
      //   174: iload #4
      //   176: iadd
      //   177: invokestatic getIndexSafe : ([Ljava/lang/Object;I)Ljava/lang/Object;
      //   180: checkcast net/miginfocom/layout/ResizeConstraint
      //   183: aastore
      //   184: iinc #10, 1
      //   187: goto -> 17
      //   190: iload #7
      //   192: iconst_1
      //   193: if_icmpeq -> 202
      //   196: iload #7
      //   198: iconst_3
      //   199: if_icmpne -> 214
      //   202: aload_1
      //   203: aload_2
      //   204: iload #4
      //   206: iload #5
      //   208: invokestatic access$5600 : ([Lnet/miginfocom/layout/DimConstraint;[Ljava/lang/Float;II)[Ljava/lang/Float;
      //   211: goto -> 215
      //   214: aconst_null
      //   215: astore #10
      //   217: aload #9
      //   219: aload #8
      //   221: aload #10
      //   223: iconst_1
      //   224: iload_3
      //   225: invokestatic calculateSerial : ([[I[Lnet/miginfocom/layout/ResizeConstraint;[Ljava/lang/Float;II)[I
      //   228: astore #11
      //   230: iconst_0
      //   231: istore #12
      //   233: iconst_0
      //   234: istore #13
      //   236: iload #13
      //   238: iload #5
      //   240: if_icmpge -> 278
      //   243: aload #11
      //   245: iload #13
      //   247: iaload
      //   248: istore #14
      //   250: aload_0
      //   251: getfield sizes : [[I
      //   254: iload #13
      //   256: iload #4
      //   258: iadd
      //   259: aaload
      //   260: iload #6
      //   262: iload #14
      //   264: iastore
      //   265: iload #12
      //   267: iload #14
      //   269: iadd
      //   270: istore #12
      //   272: iinc #13, 1
      //   275: goto -> 236
      //   278: iload #12
      //   280: ireturn }
  }
  
  private static final class CompWrap {
    private final ComponentWrapper comp;
    
    private final CC cc;
    
    private final UnitValue[] pos;
    
    private int[][] gaps;
    
    private final int[] horSizes = new int[3];
    
    private final int[] verSizes = new int[3];
    
    private int x = -2147471302;
    
    private int y = -2147471302;
    
    private int w = -2147471302;
    
    private int h = -2147471302;
    
    private int forcedPushGaps = 0;
    
    private CompWrap(ComponentWrapper param1ComponentWrapper, CC param1CC, int param1Int, UnitValue[] param1ArrayOfUnitValue, BoundSize[] param1ArrayOfBoundSize) {
      this.comp = param1ComponentWrapper;
      this.cc = param1CC;
      this.pos = param1ArrayOfUnitValue;
      if (param1Int <= 0) {
        BoundSize boundSize1 = (param1ArrayOfBoundSize != null && param1ArrayOfBoundSize[false] != null) ? param1ArrayOfBoundSize[0] : param1CC.getHorizontal().getSize();
        BoundSize boundSize2 = (param1ArrayOfBoundSize != null && param1ArrayOfBoundSize[true] != null) ? param1ArrayOfBoundSize[1] : param1CC.getVertical().getSize();
        int i = -1;
        int j = -1;
        if (this.comp.getWidth() > 0 && this.comp.getHeight() > 0) {
          j = this.comp.getHeight();
          i = this.comp.getWidth();
        } 
        for (byte b = 0; b <= 2; b++) {
          this.horSizes[b] = getSize(boundSize1, b, true, j);
          this.verSizes[b] = getSize(boundSize2, b, false, (i > 0) ? i : this.horSizes[b]);
        } 
        Grid.correctMinMax(this.horSizes);
        Grid.correctMinMax(this.verSizes);
      } 
      if (param1Int > 1) {
        this.gaps = new int[4][];
        for (byte b = 0; b < this.gaps.length; b++)
          this.gaps[b] = new int[3]; 
      } 
    }
    
    private int getSize(BoundSize param1BoundSize, int param1Int1, boolean param1Boolean, int param1Int2) {
      if (param1BoundSize == null || param1BoundSize.getSize(param1Int1) == null) {
        switch (param1Int1) {
          case 0:
            return param1Boolean ? this.comp.getMinimumWidth(param1Int2) : this.comp.getMinimumHeight(param1Int2);
          case 1:
            return param1Boolean ? this.comp.getPreferredWidth(param1Int2) : this.comp.getPreferredHeight(param1Int2);
        } 
        return param1Boolean ? this.comp.getMaximumWidth(param1Int2) : this.comp.getMaximumHeight(param1Int2);
      } 
      ContainerWrapper containerWrapper = this.comp.getParent();
      return param1BoundSize.getSize(param1Int1).getPixels(param1Boolean ? containerWrapper.getWidth() : containerWrapper.getHeight(), containerWrapper, this.comp);
    }
    
    private void calcGaps(ComponentWrapper param1ComponentWrapper1, CC param1CC1, ComponentWrapper param1ComponentWrapper2, CC param1CC2, String param1String, boolean param1Boolean1, boolean param1Boolean2) {
      ContainerWrapper containerWrapper = this.comp.getParent();
      int i = containerWrapper.getWidth();
      int j = containerWrapper.getHeight();
      BoundSize boundSize1 = (param1ComponentWrapper1 != null) ? (param1Boolean1 ? param1CC1.getHorizontal() : param1CC1.getVertical()).getGapAfter() : null;
      BoundSize boundSize2 = (param1ComponentWrapper2 != null) ? (param1Boolean1 ? param1CC2.getHorizontal() : param1CC2.getVertical()).getGapBefore() : null;
      mergeGapSizes(this.cc.getVertical().getComponentGaps(containerWrapper, this.comp, boundSize1, param1Boolean1 ? null : param1ComponentWrapper1, param1String, j, 0, param1Boolean2), false, true);
      mergeGapSizes(this.cc.getHorizontal().getComponentGaps(containerWrapper, this.comp, boundSize1, param1Boolean1 ? param1ComponentWrapper1 : null, param1String, i, 1, param1Boolean2), true, true);
      mergeGapSizes(this.cc.getVertical().getComponentGaps(containerWrapper, this.comp, boundSize2, param1Boolean1 ? null : param1ComponentWrapper2, param1String, j, 2, param1Boolean2), false, false);
      mergeGapSizes(this.cc.getHorizontal().getComponentGaps(containerWrapper, this.comp, boundSize2, param1Boolean1 ? param1ComponentWrapper2 : null, param1String, i, 3, param1Boolean2), true, false);
    }
    
    private void setDimBounds(int param1Int1, int param1Int2, boolean param1Boolean) {
      if (param1Boolean) {
        this.x = param1Int1;
        this.w = param1Int2;
      } else {
        this.y = param1Int1;
        this.h = param1Int2;
      } 
    }
    
    private boolean isPushGap(boolean param1Boolean1, boolean param1Boolean2) {
      if (param1Boolean1 && ((param1Boolean2 ? 1 : 2) & this.forcedPushGaps) != 0)
        return true; 
      DimConstraint dimConstraint = this.cc.getDimConstraint(param1Boolean1);
      BoundSize boundSize = param1Boolean2 ? dimConstraint.getGapBefore() : dimConstraint.getGapAfter();
      return (boundSize != null && boundSize.getGapPush());
    }
    
    private boolean transferBounds(boolean param1Boolean) {
      this.comp.setBounds(this.x, this.y, this.w, this.h);
      if (param1Boolean && this.w != this.horSizes[1]) {
        BoundSize boundSize = this.cc.getVertical().getSize();
        if (boundSize.getPreferred() == null && this.comp.getPreferredHeight(-1) != this.verSizes[1])
          return true; 
      } 
      return false;
    }
    
    private void setSizes(int[] param1ArrayOfInt, boolean param1Boolean) {
      if (param1ArrayOfInt == null)
        return; 
      int[] arrayOfInt = param1Boolean ? this.horSizes : this.verSizes;
      arrayOfInt[0] = param1ArrayOfInt[0];
      arrayOfInt[1] = param1ArrayOfInt[1];
      arrayOfInt[2] = param1ArrayOfInt[2];
    }
    
    private void setGaps(int[] param1ArrayOfInt, int param1Int) {
      if (this.gaps == null)
        this.gaps = new int[][] { null, null, null, null }; 
      this.gaps[param1Int] = param1ArrayOfInt;
    }
    
    private void mergeGapSizes(int[] param1ArrayOfInt, boolean param1Boolean1, boolean param1Boolean2) {
      if (this.gaps == null)
        this.gaps = new int[][] { null, null, null, null }; 
      if (param1ArrayOfInt == null)
        return; 
      int i = getGapIx(param1Boolean1, param1Boolean2);
      int[] arrayOfInt = this.gaps[i];
      if (arrayOfInt == null) {
        arrayOfInt = new int[] { 0, 0, 2097051 };
        this.gaps[i] = arrayOfInt;
      } 
      arrayOfInt[0] = Math.max(param1ArrayOfInt[0], arrayOfInt[0]);
      arrayOfInt[1] = Math.max(param1ArrayOfInt[1], arrayOfInt[1]);
      arrayOfInt[2] = Math.min(param1ArrayOfInt[2], arrayOfInt[2]);
    }
    
    private int getGapIx(boolean param1Boolean1, boolean param1Boolean2) { return param1Boolean1 ? (param1Boolean2 ? 1 : 3) : (param1Boolean2 ? 0 : 2); }
    
    private int getSizeInclGaps(int param1Int, boolean param1Boolean) { return filter(param1Int, getGapBefore(param1Int, param1Boolean) + getSize(param1Int, param1Boolean) + getGapAfter(param1Int, param1Boolean)); }
    
    private int getSize(int param1Int, boolean param1Boolean) { return filter(param1Int, param1Boolean ? this.horSizes[param1Int] : this.verSizes[param1Int]); }
    
    private int getGapBefore(int param1Int, boolean param1Boolean) {
      int[] arrayOfInt = getGaps(param1Boolean, true);
      return (arrayOfInt != null) ? filter(param1Int, arrayOfInt[param1Int]) : 0;
    }
    
    private int getGapAfter(int param1Int, boolean param1Boolean) {
      int[] arrayOfInt = getGaps(param1Boolean, false);
      return (arrayOfInt != null) ? filter(param1Int, arrayOfInt[param1Int]) : 0;
    }
    
    private int[] getGaps(boolean param1Boolean1, boolean param1Boolean2) { return this.gaps[getGapIx(param1Boolean1, param1Boolean2)]; }
    
    private int filter(int param1Int1, int param1Int2) { return (param1Int2 == -2147471302) ? ((param1Int1 != 2) ? 0 : 2097051) : Grid.constrainSize(param1Int2); }
    
    private boolean isBaselineAlign(boolean param1Boolean) {
      Float float = this.cc.getVertical().getGrow();
      if (float != null && float.intValue() != 0)
        return false; 
      UnitValue unitValue = this.cc.getVertical().getAlign();
      return (((unitValue != null) ? (unitValue == UnitValue.BASELINE_IDENTITY) : param1Boolean) && this.comp.hasBaseline());
    }
    
    private int getBaseline(int param1Int) { return this.comp.getBaseline(getSize(param1Int, true), getSize(param1Int, false)); }
  }
  
  private static class LinkedDimGroup {
    private static final int TYPE_SERIAL = 0;
    
    private static final int TYPE_PARALLEL = 1;
    
    private static final int TYPE_BASELINE = 2;
    
    private final String linkCtx;
    
    private final int span;
    
    private final int linkType;
    
    private final boolean isHor;
    
    private final boolean fromEnd;
    
    private ArrayList<Grid.CompWrap> _compWraps = new ArrayList(4);
    
    private int[] sizes = null;
    
    private int lStart = 0;
    
    private int lSize = 0;
    
    private LinkedDimGroup(String param1String, int param1Int1, int param1Int2, boolean param1Boolean1, boolean param1Boolean2) {
      this.linkCtx = param1String;
      this.span = param1Int1;
      this.linkType = param1Int2;
      this.isHor = param1Boolean1;
      this.fromEnd = param1Boolean2;
    }
    
    private void addCompWrap(Grid.CompWrap param1CompWrap) {
      this._compWraps.add(param1CompWrap);
      this.sizes = null;
    }
    
    private void setCompWraps(ArrayList<Grid.CompWrap> param1ArrayList) {
      if (this._compWraps != param1ArrayList) {
        this._compWraps = param1ArrayList;
        this.sizes = null;
      } 
    }
    
    private void layout(DimConstraint param1DimConstraint, int param1Int1, int param1Int2, int param1Int3) {
      this.lStart = param1Int1;
      this.lSize = param1Int2;
      if (this._compWraps.size() == 0)
        return; 
      ContainerWrapper containerWrapper = ((Grid.CompWrap)this._compWraps.get(0)).comp.getParent();
      if (this.linkType == 1) {
        Grid.layoutParallel(containerWrapper, this._compWraps, param1DimConstraint, param1Int1, param1Int2, this.isHor, this.fromEnd);
      } else if (this.linkType == 2) {
        Grid.layoutBaseline(containerWrapper, this._compWraps, param1DimConstraint, param1Int1, param1Int2, 1, param1Int3);
      } else {
        Grid.layoutSerial(containerWrapper, this._compWraps, param1DimConstraint, param1Int1, param1Int2, this.isHor, param1Int3, this.fromEnd);
      } 
    }
    
    private int[] getMinPrefMax() {
      if (this.sizes == null && this._compWraps.size() > 0) {
        this.sizes = new int[3];
        for (byte b = 0; b <= 1; b++) {
          if (this.linkType == 1) {
            this.sizes[b] = Grid.getTotalSizeParallel(this._compWraps, b, this.isHor);
          } else if (this.linkType == 2) {
            int[] arrayOfInt = Grid.getBaselineAboveBelow(this._compWraps, b, false);
            this.sizes[b] = arrayOfInt[0] + arrayOfInt[1];
          } else {
            this.sizes[b] = Grid.getTotalSizeSerial(this._compWraps, b, this.isHor);
          } 
        } 
        this.sizes[2] = 2097051;
      } 
      return this.sizes;
    }
  }
  
  private static class Cell {
    private final int spanx;
    
    private final int spany;
    
    private final boolean flowx;
    
    private final ArrayList<Grid.CompWrap> compWraps = new ArrayList(true);
    
    private boolean hasTagged = false;
    
    private Cell(Grid.CompWrap param1CompWrap) { this(param1CompWrap, 1, 1, true); }
    
    private Cell(int param1Int1, int param1Int2, boolean param1Boolean) { this(null, param1Int1, param1Int2, param1Boolean); }
    
    private Cell(Grid.CompWrap param1CompWrap, int param1Int1, int param1Int2, boolean param1Boolean) {
      if (param1CompWrap != null)
        this.compWraps.add(param1CompWrap); 
      this.spanx = param1Int1;
      this.spany = param1Int2;
      this.flowx = param1Boolean;
    }
  }
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\miginfocom\layout\Grid.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */