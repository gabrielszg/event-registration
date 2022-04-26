package br.com.jsf.repository;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import br.com.jsf.model.Evento;
import br.com.jsf.model.EventoFilter;
import br.com.jsf.util.jpa.Transactional;

/**
 * Classe responsavel pela comunicacao direta com o banco de dados MySql.
 * Atraves da implementacao dos metodos da especificacao JPA (Hibernate).
 */
public class EventoRepository implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager manager;

	public Evento save(Evento evento) {
		return manager.merge(evento);
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Evento> find(EventoFilter filter) {
		Session session = manager.unwrap(Session.class);
		Criteria criteria = session.createCriteria(Evento.class);
		
		if (StringUtils.isNotBlank(filter.getNome())) {
			criteria.add(Restrictions.ilike("nome", filter.getNome(), MatchMode.ANYWHERE));
		}
		
		if (StringUtils.isNotBlank(filter.getLocal())) {
			criteria.add(Restrictions.ilike("local", filter.getLocal(), MatchMode.ANYWHERE));
		}
		
		return criteria.list();
	}

	@Transactional
	public void delete(Evento evento) throws IOException {
		evento = findById(evento.getId());
		manager.remove(evento);
		manager.flush();
	}

	public Evento findById(Long id) {
		return manager.find(Evento.class, id);
	}
}
