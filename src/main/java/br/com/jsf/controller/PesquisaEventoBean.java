package br.com.jsf.controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.jsf.model.Evento;
import br.com.jsf.model.EventoFilter;
import br.com.jsf.service.EventoService;
import br.com.jsf.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEventoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Evento eventoSelecionado;
	private EventoFilter filter;
	private List<Evento> eventos;
	
	@Inject
	private EventoService service;
	
	public PesquisaEventoBean() {
		this.filter = new EventoFilter();
	}
	
	public void find() {
		
		try {
			Thread.sleep(1000);
		}catch (InterruptedException e) {}
		
		eventos = service.findEvent(filter);
	}
	
	public void remove() {
		System.out.println("PesquisaEventoBean.remove()");
		
		try {
			service.removeEvent(eventoSelecionado);
			eventos.remove(eventoSelecionado);
			
			FacesUtil.addInfoMessage("Evento " + eventoSelecionado.getNome() + " excluído com sucesso!");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public EventoFilter getFilter() {
		return filter;
	}
	
	public void setFilter(EventoFilter filter) {
		this.filter = filter;
	}

	public Evento getEventoSelecionado() {
		return eventoSelecionado;
	}

	public void setEventoSelecionado(Evento eventoSelecionado) {
		this.eventoSelecionado = eventoSelecionado;
	}

	public List<Evento> getEventos() {
		return eventos;
	}
}