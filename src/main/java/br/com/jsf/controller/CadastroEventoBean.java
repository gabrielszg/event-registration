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

@Named
@ViewScoped
public class CadastroEventoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Evento evento;
	
	@Inject
	private EventoService service;
	
	@PostConstruct
	public void init() {
		System.out.println("CadastroEventoBean.init()");
		
		String idStr = FacesUtil.getParameter("evento");
		
		if (StringUtils.isNotBlank(idStr)) {
			Long id = Long.parseLong(idStr);
			this.evento = service.findById(id);
			
			System.out.println("Editando o Evento " + evento.getNome());
		}else {
			this.evento = new Evento();
			
			System.out.println("Cadastrando novo Evento");
		}
	}
	
	public boolean isNewEvent() {
		return !(evento != null && evento.getId() != null);
	}

	public void save() {
		System.out.println("CadastroEventoBean.save()");
		
		if (evento.getId() == null) {
			this.evento = service.saveEvent(this.evento);
			FacesUtil.addInfoMessage("Evento Cadastrado com sucesso!");
		}else {
			this.evento = service.saveEvent(this.evento);
			FacesUtil.addInfoMessage("Evento " + evento.getNome() + " Atualizado com sucesso!");
		}
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
