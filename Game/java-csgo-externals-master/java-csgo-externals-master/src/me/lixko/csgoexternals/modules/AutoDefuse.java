package me.lixko.csgoexternals.modules;

import com.github.jonatino.misc.MemoryBuffer;
import com.sun.jna.Pointer;

import me.lixko.csgoexternals.Engine;
import me.lixko.csgoexternals.offsets.Netvars;
import me.lixko.csgoexternals.offsets.Offsets;
import me.lixko.csgoexternals.structs.CGlobalVars;
import me.lixko.csgoexternals.structs.CSPlayerResource;
import me.lixko.csgoexternals.structs.VectorMem;
import me.lixko.csgoexternals.util.ChatColor;
import me.lixko.csgoexternals.util.DrawUtils;
import me.lixko.csgoexternals.util.MathUtils;
import me.lixko.csgoexternals.util.TextAlign;

public class AutoDefuse extends Module {

	public CGlobalVars globalvars = new CGlobalVars();
	private MemoryBuffer globalvarsbuf = new MemoryBuffer(globalvars.size());
	public CSPlayerResource playerresobj = new CSPlayerResource();

	public VectorMem bombpos = new VectorMem();
	public VectorMem lppos = new VectorMem();
	private MemoryBuffer posbuf = new MemoryBuffer(bombpos.size() * 2);
	public boolean autodefuset = false;
	public long bombentityaddr = 0;

	public float bombtimer, deftime, countdown, bombDistance, bombDamage;
	public long defuser;
	public boolean defused, hasDefuseKit, hastimetodefuse;

	private Thread autodefuse = new Thread(new Runnable() {
		@Override
		public void run() {
			globalvars.setSource(globalvarsbuf);
			bombpos.setSource(posbuf);
			lppos.setSource(posbuf, bombpos.size());
			while (true) {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (!autodefuset || Offsets.m_dwLocalPlayer == 0)
					continue;
				// Offsets.m_dwGlobalVars = Engine.clientModule().readLong(Offsets.m_dwGlobalVarsPointer);
				Engine.clientModule().read(Offsets.m_dwGlobalVars, globalvarsbuf.size(), globalvarsbuf);
				Engine.clientModule().read(Offsets.m_dwLocalPlayer + Netvars.CBaseEntity.m_vecOrigin, posbuf.size(), posbuf);
				Engine.clientModule().read(bombentityaddr + Netvars.CBaseEntity.m_vecOrigin, bombpos.size(), posbuf);
				Engine.clientModule().read(Offsets.m_dwLocalPlayer + Netvars.CBaseEntity.m_vecOrigin, bombpos.size(), Pointer.nativeValue(posbuf) + bombpos.size());

				bombtimer = Engine.clientModule().readFloat(bombentityaddr + Netvars.CPlantedC4.m_flC4Blow) - globalvars.curtime.getFloat();
				deftime = Engine.clientModule().readFloat(bombentityaddr + 0x3010);
				countdown = Engine.clientModule().readFloat(bombentityaddr + 0x3024) - globalvars.curtime.getFloat();
				defuser = Engine.clientModule().readLong(bombentityaddr + 0x302c) & 0xFFF;
				defused = Engine.clientModule().readBoolean(bombentityaddr + 0x3028);
				hasDefuseKit = false;
				boolean LPhasDefuseKit = Engine.clientModule().readBoolean(Offsets.m_dwLocalPlayer + 0xb31c);

				float distance = (float) MathUtils.calculateDistance(bombpos.x.getFloat(), bombpos.z.getFloat(), lppos.x.getFloat(), lppos.z.getFloat());

				int team = Engine.clientModule().readInt(Offsets.m_dwLocalPlayer + Netvars.CBaseEntity.m_iTeamNum);
				int armorv = Engine.clientModule().readInt(Offsets.m_dwLocalPlayer + Netvars.CCSPlayer.m_ArmorValue);
				double d = ((distance - 75.68f) / 789.2f);
				float flDamage = (float) (450.7f * MathUtils.exp(-d * d));
				bombDamage = Math.max((int) Math.ceil(MathUtils.GetArmourHealth(flDamage, armorv)), 1) - 1;
				MemoryBuffer defuserbuf = Engine.clientModule().read(Offsets.m_dwPlayerResources + playerresobj.m_bHasDefuser.offset(), 64);
				boolean defuseKitOnTeam = false;
				for (byte b : defuserbuf.getByteArray()) {
					if (b != 0) {
						defuseKitOnTeam = true;
						break;
					}
				}

				if (defuser != 0xfff)
					hasDefuseKit = Engine.clientModule().readBoolean(Offsets.m_dwPlayerResources + playerresobj.m_bHasDefuser.offset() + defuser);

				if (bombtimer < 40 && bombtimer > 0 && !defused) {
					if (defuser != 0xfff) {
						if (bombtimer - countdown < 0.05f) {
							// System.out.println("NO TIME!!! " + bombtimer);
							hastimetodefuse = false;
						} else {
							hastimetodefuse = true;
						}
					} else if (bombtimer < (defuseKitOnTeam ? 5.0f : 10.0f)) {
						hastimetodefuse = false;
					} else if (bombtimer > (defuseKitOnTeam ? 5.0f : 10.0f)) {
						hastimetodefuse = true;
					}
					if (team == 3 && bombtimer < (LPhasDefuseKit ? 5.0f : 10.0f) + 0.1f && defuser == 0xfff && hastimetodefuse) {
						Engine.clientModule().writeInt(Offsets.input.use, 5);
					}
				}
				// System.out.println(defused ? "D!!! " : "" + StringFormat.hex(defuser) + " " + (hasDefuseKit ? "D" : "-") + " > bt: " + Math.floor(bombtimer*10)/10 + " deftime: " + countdown + " globaltime: " + globalvars.curtime.getFloat() + " " + (hastimetodefuse ? "TIME" : "NO TIME"));

				if (bombtimer < 0f || defused) {
					if (team == 3)
						Engine.clientModule().writeInt(Offsets.input.use, 4);
					bombentityaddr = 0;
					autodefuset = false;
					hastimetodefuse = false;
				}
			}
		}
	});

	@Override
	public void onEngineLoaded() {
		autodefuse.start();
	}

	@Override
	public void onUIRender() {

		if (!autodefuset || bombtimer < 0)
			return;
		int lphealth = Engine.clientModule().readInt(Offsets.m_dwLocalPlayer + Netvars.CBasePlayer.m_iHealth);
		int lpteam = Engine.clientModule().readInt(Offsets.m_dwLocalPlayer + Netvars.CBaseEntity.m_iTeamNum);

		DrawUtils.setStyle(ChatColor.LARGE);
		if (bombDamage >= lphealth)
			DrawUtils.setTextColor(1.0f, 0.1f, 0.1f);
		else
			DrawUtils.setTextColor(0.1f, 1.0f, 0.1f);

		DrawUtils.setAlign(TextAlign.CENTER);

		DrawUtils.setColor(0, 0, 0, 0.8f);
		DrawUtils.fillRectanglew(DrawUtils.getScreenWidth() / 2 - 30, DrawUtils.getScreenHeight() - 24, 65, 22);

		if (bombDamage >= lphealth) {
			DrawUtils.setLineWidth(2.0f);
			DrawUtils.setColor(1.0f, 0, 0, 0.8f);
			DrawUtils.drawRectanglew(DrawUtils.getScreenWidth() / 2 - 30, DrawUtils.getScreenHeight() - 24, 65, 22);
		}
		DrawUtils.disableStringBackground();
		DrawUtils.drawString(DrawUtils.getScreenWidth() / 2 + 2, DrawUtils.getScreenHeight() - 20, "" + (int) bombDamage);

		/*
		 * DrawUtils.enableStringBackground();
		 * DrawUtils.setTextColor(0f, 1f, 1f);
		 * DrawUtils.drawString(DrawUtils.getScreenWidth() / 2 + 2, 20, "" + Math.floor(bombtimer*10)/10);
		 */

		// DrawUtils.enableStringBackground();
		// DrawUtils.drawString(DrawUtils.getScreenWidth() / 2, DrawUtils.getScreenHeight() - 100, "Bomb: " + (int)bombDamage);

		DrawUtils.setStyle(ChatColor.MEDIUM);

		if (bombtimer < 5f || (defuser != 0xFFF && deftime > 0 && deftime < 10))
			if (hastimetodefuse) {
				DrawUtils.setTextureColor(0f, 1f, 0f);
				DrawUtils.drawTexture("defuser", DrawUtils.getScreenWidth() / 2 - 50, DrawUtils.getScreenHeight() - 200, 100, -1);
			} else {
				if (lpteam == 2)
					DrawUtils.setTextureColor(0f, 1f, 0f);
				else
					DrawUtils.setTextureColor(1f, 0f, 0f);
				DrawUtils.drawTexture("bomb", DrawUtils.getScreenWidth() / 2 - 90, DrawUtils.getScreenHeight() - 230, 100, -1);
			}
	}

}
