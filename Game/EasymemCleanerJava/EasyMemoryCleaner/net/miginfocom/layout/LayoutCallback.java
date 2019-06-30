package net.miginfocom.layout;

public abstract class LayoutCallback {
  public UnitValue[] getPosition(ComponentWrapper paramComponentWrapper) { return null; }
  
  public BoundSize[] getSize(ComponentWrapper paramComponentWrapper) { return null; }
  
  public void correctBounds(ComponentWrapper paramComponentWrapper) {}
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\net\miginfocom\layout\LayoutCallback.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.0.4
 */