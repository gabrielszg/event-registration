package br.com.jsf.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe gerenciada pelo Hibernate. Representa a tabela evento no banco de
 * dados.
 */

@Entity
@Getter
@Setter
@EqualsAndHashCode
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String nome;

	@Column(nullable = false, length = 50)
	private String organizacao;

	@Column(nullable = false, length = 50)
	private String local;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(nullable = false)
	private String horario;

	@Column(nullable = true)
	private BigDecimal valor = new BigDecimal("0");

	@Column(nullable = false, length = 240)
	private String descricao;

}
