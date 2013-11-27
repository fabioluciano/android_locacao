package br.com.wordbox;

public class Cliente {
	private long id;
	private String nome;
	private String telefone;
	private String endereco;
	
	public long getId() {
		return this.id;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getTelefone() {
		return this.telefone;
	}
	
	public String getEndereco() {
		return this.endereco;
	}
	
	public Cliente(long id, String nome, String telefone, String endereco) {
		this.id = id;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	public Cliente(String nome, String telefone, String endereco) {
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
	}
	
	public String toString() {
		return this.getNome();
	}
}
