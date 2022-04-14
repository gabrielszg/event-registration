package br.com.jsf.service;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.com.jsf.model.Evento;
import br.com.jsf.model.EventoFilter;
import br.com.jsf.repository.EventoRepository;
import br.com.jsf.util.jpa.Transactional;

public class EventoService implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject
	private EventoRepository repository;
	
	@Transactional
	public Evento saveEvent(Evento evento) {
		return repository.save(evento);
	}
	
	public List<Evento> findEvent(EventoFilter filter) {
		return repository.find(filter);
	}
	
	@Transactional
	public void removeEvent(Evento evento) {
			repository.delete(evento);
	}
}
