package br.com.jsf.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf.model.Evento;
import br.com.jsf.model.EventoFilter;
import br.com.jsf.service.EventoService;

@Named
@ViewScoped
public class PesquisaEventoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Evento evento;
	private EventoFilter filter;
	private List<Evento> eventos;
	
	@Inject
	private EventoService service;
	
	public PesquisaEventoBean() {
		this.filter = new EventoFilter();
	}
	
	public void find() {
		eventos = service.findEvent(filter);
	}
	
	public void remove() {
		service.removeEvent(evento);
		eventos.remove(evento);
	}
	
	public EventoFilter getFilter() {
		return filter;
	}
	
	public void setFilter(EventoFilter filter) {
		this.filter = filter;
	}

	public Evento getEvento() {
		return evento;
	}

	public List<Evento> getEventos() {
		return eventos;
	}

	public void setEventos(List<Evento> eventos) {
		this.eventos = eventos;
	}

	public EventoService getService() {
		return service;
	}

	public void setService(EventoService service) {
		this.service = service;
	}
	
}