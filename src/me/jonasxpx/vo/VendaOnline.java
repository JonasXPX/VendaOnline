package me.jonasxpx.vo;

import org.bukkit.plugin.java.JavaPlugin;

public class VendaOnline extends JavaPlugin{

	
	@Override
	public void onEnable() {
		getConfig().options().copyDefaults(true);
		saveConfig();
		Manager.loadConfig(this.getConfig());
		getCommand("comprar").setExecutor(new Commands());
	}
}
