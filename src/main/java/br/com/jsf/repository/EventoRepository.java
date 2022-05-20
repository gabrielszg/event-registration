package br.com.jsf.repository;

import java.io.IOException;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;

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

	public List<Evento> find(EventoFilter filter) {
		List<Evento> result = null;

		try {

			CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
			CriteriaQuery<Evento> criteriaQuery = criteriaBuilder.createQuery(Evento.class);

			Root<Evento> evento = criteriaQuery.from(Evento.class);
			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotBlank(filter.getNome())) {
				predicates.add(criteriaBuilder.like(evento.get("nome").as(String.class), "%" + filter.getNome() + "%"));
			}

			if (StringUtils.isNotBlank(filter.getLocal())) {
				predicates.add(criteriaBuilder.like(evento.get("local").as(String.class), "%" + filter.getLocal() + "%"));
			}

			if (filter.getData() != null) {
				predicates.add(criteriaBuilder.equal(evento.get("data").as(Date.class), filter.getData()));
			}

			criteriaQuery.where(predicates.toArray(new Predicate[] {}));

			TypedQuery<Evento> typedQuery = manager.createQuery(criteriaQuery);
			result = typedQuery.getResultList();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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
