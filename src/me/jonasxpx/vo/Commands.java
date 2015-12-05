package me.jonasxpx.vo;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor{

	
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if(args.length == 0){
			sender.sendMessage("§b * Veja os produtos abaixo, para comprar use /comprar <ID>");
			for(String products : Manager.listProducts()){
				sender.sendMessage(products);
			}
		}
		if(args.length >= 1){
			try{
			Manager.itens.get(Integer.parseInt(args[0])).solicitar((Player)sender);
			}catch(NumberFormatException e){
				sender.sendMessage("§cUse apenas números, /comprar <ID>, Ex: /comprar 2");
			}
		}
		return false;
	}
	
}
