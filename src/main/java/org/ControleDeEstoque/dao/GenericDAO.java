package org.ControleDeEstoque.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * */

@Transactional(rollbackOn = { Exception.class })
public class GenericDAO<E> implements Serializable {
	private static final long serialVersionUID = 1L;

	@PersistenceContext
	protected EntityManager em;

	// Captuando a Class de Instancia
	protected Class classPersistente;

	public GenericDAO() {

	}

	// Salvar
	public void persiste(E obj) throws Exception {
		em.persist(obj);
		em.flush();
	}

	// Atualizar e Salvar os Dados
	public void update(E obj) throws Exception {
		em.merge(obj);
		em.flush();
	}

	// Remove
	public void remove(E obj) throws Exception {

	}

	// Buscar Todos
	public List<E> findAll() {
		String jpql = "from " + classPersistente.getSimpleName();
		return em.createQuery(jpql).getResultList();
	}

	// Buscar por ID
	public E findById(E id) {
		return (E) em.find(classPersistente, id);
	}

}
