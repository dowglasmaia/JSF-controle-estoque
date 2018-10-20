package com.maia.ce.repository;

import javax.ejb.Stateless;

import com.maia.ce.entity.Produto;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * */

@Stateless
public class ProdutoDAO extends GenericDAO<Produto> {
	private static final long serialVersionUID = 1L;

	public ProdutoDAO() {
		super();
		classPersistente = Produto.class;
	}

}
