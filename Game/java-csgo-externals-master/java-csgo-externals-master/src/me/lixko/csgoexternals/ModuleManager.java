package me.lixko.csgoexternals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

import me.lixko.csgoexternals.modules.*;
import me.lixko.csgoexternals.modules.Module;
import me.lixko.csgoexternals.util.DrawUtils;

public class ModuleManager {

	public ArrayList<Module> activeModules = new ArrayList<Module>();
	public HashMap<String, Boolean> toggledModules = new HashMap<String, Boolean>();
	public TreeMap<Integer, String> cachedStatusText = new TreeMap<Integer, String>();

	public ModuleManager() {
		registerModules();
	}

	public void registerModules() {
		//registerModule(new AimBot());
		//registerModule(new AimBotGhetto());
		registerModule(new AutoDefuse());
		// registerModule(new AutoPlant());
		registerModule(new Bunnyhop());
		registerModule(new DisablePP());
		//registerModule(new DownloadFixer());
		registerModule(new FOVChanger());
		registerModule(new Glow());
		registerModule(new NoFlash());
		registerModule(new NoHands());
		registerModule(new RCS());
		registerModule(new SkinChanger());
		registerModule(new TestModule());

		// TODO: Fix dependencies and NullPtrEx with DrawUtils.getScreenWidth()
		if (DrawUtils.enableOverlay) {
			registerModule(new VisibleTest());
			//registerModule(new AutoJoinCT());
			registerModule(new BoneESP());
			registerModule(new CrosshairDot());
			//registerModule(new MapRender());
			registerModule(new NameHUD());
			registerModule(new RankReveal());
			registerModule(new RecoilCross());
			registerModule(new Spectators());
		}
	}

	public void reloadManager() {
		this.activeModules.clear();
		registerModules();
		System.out.println("[LixkoPack] ModuleManager reloaded!");
	}

	public void registerModule(Module module) {
		this.activeModules.add(module);
		module.onRegister();
	}

	public boolean isLoaded(String moduleName) {
		for (Module eventModule : Client.theClient.moduleManager.activeModules) {
			if (eventModule.getName().equals(moduleName)) {
				return true;
			}
		}
		return false;
	}

	public int getKeybind(String moduleName) {
		for (Module eventModule : Client.theClient.moduleManager.activeModules) {
			if (eventModule.getName().equals(moduleName)) {
				return eventModule.getBind();
			}
		}

		return 0;
	}

	public boolean isToggled(String moduleName) {
		return (toggledModules.containsKey(moduleName) ? toggledModules.get(moduleName) : false);
	}

	public void setToggle(String moduleName, Boolean toggled) {
		this.toggledModules.put(moduleName, toggled);
	}

	public Module getModuleByKeybind(int key) {
		for (Module eventModule : Client.theClient.moduleManager.activeModules) {
			if (eventModule.getBind() == key) {
				return eventModule;
			}
		}

		return null;
	}

	public Module getModule(String moduleName) {
		for (Module eventModule : Client.theClient.moduleManager.activeModules) {
			if (eventModule.getName().equals(moduleName)) {
				return eventModule;
			}
		}

		return null;
	}

	public Module getModule(int moduleID) {
		if (moduleID > Client.theClient.moduleManager.activeModules.size())
			return null;
		else
			return Client.theClient.moduleManager.activeModules.get(moduleID);
	}

	public int moduleSize() {
		return Client.theClient.moduleManager.activeModules.size();
	}
}