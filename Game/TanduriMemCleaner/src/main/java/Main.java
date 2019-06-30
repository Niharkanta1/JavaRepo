import com.beaudoin.jmm.process.Module;
import com.beaudoin.jmm.process.NativeProcess;
import java.util.Arrays;
import lc.kra.system.keyboard.GlobalKeyboardHook;
import lc.kra.system.keyboard.event.GlobalKeyAdapter;
import lc.kra.system.keyboard.event.GlobalKeyEvent;
import lc.kra.system.mouse.GlobalMouseHook;
import lc.kra.system.mouse.event.GlobalMouseAdapter;
import lc.kra.system.mouse.event.GlobalMouseEvent;

public class Main {
    INSTANCE;
    public long lastUpdate;
    private Gui gui;
    private Entities entityList;
    private FeatureManager featureManager;
    public NativeProcess getTF2Process() { return this.TF2Process; }
    private Memory memory; private Module client;
    private Module engine;
    private NativeProcess TF2Process;
    public Module getEngine() { return this.engine; }
    public Module getClient() { return this.client; }
    public Memory getMemory() { return this.memory; }
    public FeatureManager getFeatureManager() { return this.featureManager; }
    public Entities getEntityList() { return this.entityList; }
    public Gui getGui() { return this.gui; }

}
