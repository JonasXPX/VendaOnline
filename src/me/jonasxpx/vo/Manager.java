package me.jonasxpx.vo;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

import br.com.uol.pagseguro.domain.AccountCredentials;
import br.com.uol.pagseguro.exception.PagSeguroServiceException;

import com.google.common.collect.Lists;

public class Manager {

	
	protected static List<Item> itens;
	protected static AccountCredentials acc;
	
	
	public static void loadConfig(FileConfiguration c){
		itens = Lists.newArrayList();
		
		for(String key : c.getConfigurationSection("Itens").getKeys(false)){
			itens.add(new Item(c.getString("Itens." + key + ".ID"),
					c.getDouble("Itens." + key + ".Valor"),
					ChatColor.translateAlternateColorCodes('&', c.getString("Itens." + key + ".Descricao")),
					ChatColor.translateAlternateColorCodes('&', c.getString("Itens." + key + ".Texto")),
					ChatColor.translateAlternateColorCodes('&', c.getString("Itens." + key + ".Nome"))));
		}
		try {
			acc = new AccountCredentials(c.getString("PagSeguro.Email"), c.getString("PagSeguro.Token"));
		} catch (PagSeguroServiceException e) {e.printStackTrace();}
	}
	
	
	public static Collection<String> listProducts(){
		Collection<String> products = new ArrayList<String>();
		int x = 0;
		for(Item i : itens){
			products.add("§7" + x + ". §6" + i.getNome() + " - Preço:§c " + NumberFormat.getInstance(Locale.forLanguageTag("pt-BR")).format(i.getValor()));
			x++;
		}
		return products;
	}
}
