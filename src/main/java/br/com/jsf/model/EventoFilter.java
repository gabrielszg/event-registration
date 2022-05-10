package br.com.jsf.model;

import java.io.Serializable;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe utilizada somente para filtrar eventos da lista por nome e local.
 */

@Getter
@Setter
public class EventoFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String local;
	private Date data;

}
