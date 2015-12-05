package me.jonasxpx.vo;

import java.math.BigDecimal;

import org.bukkit.entity.Player;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;

public class Item {

	private double valor;
	private String descriçao;
	private String id;
	private String texto;
	private String nome;
	
	public Item(String id, double valor, String descriçao, String texto, String nome) {
		this.valor = valor;
		this.descriçao = descriçao;
		this.id = id;
		this.texto = texto;
		this.nome = nome;
	}
	
	public String getInformation(){
		return texto;
	}
	
	public double getValor(){
		return valor;
	}
	
	public String getID(){
		return id;
	}
	public String getNome(){
		return nome;
	}
	
	public void solicitar(final Player player){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					Checkout checkout = new Checkout();
					checkout.addItem(id, 
							descriçao +" - APELIDO: " + player.getName(), 
							1, 
							new BigDecimal(valor), 0L, new BigDecimal("0.00"));
					checkout.setCurrency(Currency.BRL);
				    try {  
				        String response = checkout.register(Manager.acc, false);  
				        System.out.println("[PAGSEGURO] " + Utils.getDate() + " gerado link para pagamento em apelido de " + player.getName() + "LINK: " + response);
				        System.out.println("[PAGSEGURO] " + Utils.getDate() + " IP do jogador " + player.getName() + ": " + player.getAddress().getAddress().toString());
				        player.sendMessage("&7&m->--------------------------------------<-\n"
				        		+ "\n"
				        		+ "§b Prosiga com o pagamento no link abaixo\n§o"
				        		+ Utils.urlParse(response) + "\n"
				        				+ "§bVocê ira receber seu código junto com o email do PagSeguro\n"
				        				+ "Para ativar use /ativarvip <código>\n"
				        				+ "\n"
				        				+ "&7&m->--------------------------------------<-");
				    } catch (Exception e) {  
				          System.err.println(e.getMessage());  
				    } 
				}
			}, "Thread Pagamento " + player.getName()).start();
		}
	
	

}
