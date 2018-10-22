package com.maia.ce.repositorydao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.maia.ce.interfaces.GenericRepositoryDAO;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Transactional(rollbackOn = {Exception.class})
public class GenericDAO<E> implements GenericRepositoryDAO<E> {
	private static final long serialVersionUID = 1L;

	@PersistenceContext // Injeta uma Instancia do EntityManager
	protected EntityManager em;

	// Captuando a Class de Instancia
	protected Class classPersistente;

	public GenericDAO() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void save(E entidade) throws Exception {
		em.persist(entidade);
		em.flush();
	}

	@Override
	public void update(E entidade) throws Exception {
		em.merge(entidade);
		em.flush();

	}

	@Override
	public void remove(E entidade) throws Exception {
		entidade = em.merge(entidade);
		em.remove(entidade);
		em.flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() throws Exception {
		String jpql = "from " + classPersistente.getSimpleName();
		return em.createQuery(jpql).getResultList();
	}

	@Override
	public E findById(Integer id) throws Exception {
		return (E) em.find(classPersistente, id);
	}

}
