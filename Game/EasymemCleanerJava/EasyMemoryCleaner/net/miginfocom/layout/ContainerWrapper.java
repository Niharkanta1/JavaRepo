package net.miginfocom.layout;

public interface ContainerWrapper extends ComponentWrapper {
  ComponentWrapper[] getComponents();
  
  int getComponentCount();
  
  Object getLayout();
  
  boolean isLeftToRight();
  
  void paintDebugCell(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\miginfocom\layout\ContainerWrapper.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */