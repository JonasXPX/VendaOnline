package me.jonasxpx.vo;

import java.io.File;
import java.util.Collection;

import org.bukkit.plugin.java.JavaPlugin;

public class VendaOnline extends JavaPlugin{

	public static VendaOnline instance;
	public String cmdToExecute;
	
	@Override
	public void onEnable() {
		instance = this;
		if(!new File(getDataFolder() + "/config.yml").exists()){
			getConfig().options().copyDefaults(true);
			saveConfig();
		}
		this.cmdToExecute = getConfig().getString("defaultcmd");

		Manager.loadConfig(this.getConfig());
		getCommand("comprar").setExecutor(new Commands());
	}
	
	public static Collection<Item> getItens(){
		return Manager.itens;
	}
	
	
	
}
