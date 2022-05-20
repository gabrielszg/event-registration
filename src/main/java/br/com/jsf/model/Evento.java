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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

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
public class Evento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(min = 2, max = 50)
	@Column(nullable = false, length = 50)
	private String nome;

	@NotBlank
	@Size(min = 2, max = 50)
	@Column(nullable = false, length = 50)
	private String organizacao;

	@NotBlank
	@Size(min = 2, max = 50)
	@Column(nullable = false, length = 50)
	private String local;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Date data;

	@NotBlank
	@Column(nullable = false)
	private String horario;

	@Column(nullable = true, precision = 10, scale = 2)
	private BigDecimal valor = new BigDecimal("0");

	@NotBlank
	@Size(min = 2, max = 240)
	@Column(nullable = false, length = 240)
	private String descricao;

}
