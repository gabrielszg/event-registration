package br.com.jsf.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	public CadastroEventoBean() {
		evento = new Evento();
	}

	public void save() {
		System.out.println("CadastroEventoBean.save()");
		this.evento = service.saveEvent(this.evento);
		
		FacesUtil.addInfoMessage("Evento Cadastrado com sucesso!");
	}
	
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
}
