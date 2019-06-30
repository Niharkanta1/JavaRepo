package lc.kra.system.mouse.event;

import java.util.EventListener;

public interface GlobalMouseListener extends EventListener {
  void mousePressed(GlobalMouseEvent paramGlobalMouseEvent);
  
  void mouseReleased(GlobalMouseEvent paramGlobalMouseEvent);
  
  void mouseMoved(GlobalMouseEvent paramGlobalMouseEvent);
  
  void mouseWheel(GlobalMouseEvent paramGlobalMouseEvent);
}


/* Location:              D:\CODING\JAVA\Game\lemons tf2 cheat xd.jar!\lc\kra\system\mouse\event\GlobalMouseListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.4
 */