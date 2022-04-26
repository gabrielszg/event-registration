package br.com.jsf.model;

import java.io.Serializable;

/** 
 * Classe utilizada somente para filtrar eventos da lista
 * por nome e local.
 */

public class EventoFilter implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private String nome;
	private String local;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	
	
}
