package br.com.jsf.controller;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import br.com.jsf.model.Evento;
import br.com.jsf.service.EventoService;
import br.com.jsf.util.jsf.FacesUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe de controle respnsavel pela comunicacao com a pagina de Cadastro de
 * Evento. Gerenciada pelo CDI. Escopo de visualizacao.
 */

@Named
@ViewScoped
@Getter
@Setter
public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Evento evento;

	@Inject
	private EventoService service;

	/**
	 * Metodo que verifica se e um novo cadastro ou uma atualizacao dos dados do
	 * objeto. Se o ID for nulo, cadastra novo evento, caso contrario atualiza o
	 * evento já cadastrado.
	 */
	@PostConstruct
	public void init() {
		System.out.println("CadastroEventoBean.init()");

		String idStr = FacesUtil.getParameter("evento");

		if (StringUtils.isNotBlank(idStr)) {
			Long id = Long.parseLong(idStr);
			this.evento = service.findById(id);

			System.out.println("Editando o Evento " + evento.getNome());
		} else {
			this.evento = new Evento();

			System.out.println("Cadastrando novo Evento");
		}
	}

	/**
	 * Metodo booleano para verifcar se o objeto esta vazio e de acordo com seu
	 * estado, atualizar a mensagem na pagina de cadastro.
	 */
	public boolean isNewEvent() {
		return !(evento != null && evento.getId() != null);
	}

	public void save() {
		System.out.println("CadastroEventoBean.save()");

		if (evento.getId() == null) {
			this.evento = service.saveEvent(this.evento);

			FacesUtil.addInfoMessage("Evento Cadastrado com sucesso!");
		} else if (service.findById(evento.getId()) != null) {
			this.evento = service.saveEvent(this.evento);

			FacesUtil.addInfoMessage("Evento " + evento.getNome() + " Atualizado com sucesso!");
		} else {
			FacesUtil.redirecionarPagina("CadastrarEvento.xhtml");
		}
	}
}
