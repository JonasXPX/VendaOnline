package me.jonasxpx.vo;

import java.math.BigDecimal;

import javax.annotation.Nullable;

import org.bukkit.entity.Player;

import br.com.uol.pagseguro.domain.checkout.Checkout;
import br.com.uol.pagseguro.enums.Currency;

public class Item {

	private String valor;
	private String descri�ao;
	private String id;
	private String texto;
	private String nome;
	private boolean visivel;

	public Item(String id, String valor, String descri�ao, String texto, String nome, boolean visivel) {
		this.valor = valor;
		this.descri�ao = descri�ao;
		this.id = id;
		this.texto = texto;
		this.nome = nome;
		this.visivel = visivel;
	}

	public String getInformation() {
		return texto;
	}

	public String getValor() {
		return valor;
	}

	public String getID() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public boolean isVisible() {
		return visivel;
	}

	public void solicitar(final Player player, final int quantia) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				Checkout checkout = new Checkout();
				checkout.addItem(id, descri�ao + " - APELIDO: "
						+ player.getName(), quantia, new BigDecimal(valor), 0L, new BigDecimal("0.00"));
				checkout.setCurrency(Currency.BRL);
				try {
					String response = checkout.register(Manager.acc, false);
					System.out.println("[PAGSEGURO] " + Utils.getDate() + " gerado link para pagamento em apelido de "
							+ player.getName() + " - LINK: " + response);
					System.out.println("[PAGSEGURO] " + Utils.getDate() + " IP do jogador " + player.getName() + ": "
							+ player.getAddress().getAddress().toString());
					player.sendMessage("�7�m->-------------------------------------------<-\n" + "\n"
							+ "�b  Prossiga com o pagamento no link abaixo\n�o  " + Utils.urlParse(response) + "\n"
							+ "  �bVoc� ira receber seu c�digo junto com o email do PagSeguro\n"
							+ "  �cPara ativar use " + VendaOnline.instance.cmdToExecute + "\n" + "\n"
							+ "�7�m->-------------------------------------------<-");
				} catch (Exception e) {
					System.err.println(e.getMessage());
					player.sendMessage("�cErro:" + e.getMessage());
				}
			}
		}, "Thread Pagamento " + player.getName()).start();
	}
}
