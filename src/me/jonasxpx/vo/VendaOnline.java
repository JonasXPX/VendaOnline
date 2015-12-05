package me.jonasxpx.vo;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

public class VendaOnline extends JavaPlugin{

	
	@Override
	public void onEnable() {
		if(!new File(getDataFolder() + "/config.yml").exists()){
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		Manager.loadConfig(this.getConfig());
		getCommand("comprar").setExecutor(new Commands());
	}
}
