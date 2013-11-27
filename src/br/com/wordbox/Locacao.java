package br.com.wordbox;

public class Locacao {
	private int id;
	private Cliente cliente;
	private Automovel automovel;
	private String dataLocacao;
	
	public int getId() {
		return this.id;
	}
	
	public Cliente getCliente() {
		return this.cliente;
	}
	
	public Automovel getAutomovel() {
		return this.automovel;
	}
	
	public String getDataLocacao() {
		return this.dataLocacao;
	}
	
	public Locacao(Cliente cliente, Automovel automovel, String dataLocacao) {
		this.cliente = cliente;
		this.automovel = automovel;
		this.dataLocacao = dataLocacao;
	}
	
	public Locacao(int id, Cliente cliente, Automovel automovel, String dataLocacao) {
		this.id = id;
		this.cliente = cliente;
		this.automovel = automovel;
		this.dataLocacao = dataLocacao;
	}
	
	public String toString() {
		return this.automovel.getModelo() + " - " + this.automovel.getAno() + " - " + this.dataLocacao; 
		
	}
}
