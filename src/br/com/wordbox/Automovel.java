package br.com.wordbox;

public class Automovel {
	private int id;
	private String placa;
	private String modelo;
	private short ano;
	private float preco;
	
	public int getId() {
		return this.id;
	}
	
	public String getPlaca() {
		return this.placa;
	}
	
	public String getModelo() {
		return this.modelo;
	}
	
	public short getAno() {
		return this.ano;
	}
	
	public float getPreco() {
		return this.preco;
	}
	
	public Automovel(int id, String placa, String modelo, short ano, float preco) {
		this.id = id;
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.preco = preco;
	}
	
	public Automovel(String placa, String modelo, short ano, float preco) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.preco = preco;
	}
	
	public String toString() {
		return this.getModelo() + " - " + this.getAno();
	}
}
