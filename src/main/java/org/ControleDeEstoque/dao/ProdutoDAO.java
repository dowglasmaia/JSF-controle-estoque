package org.ControleDeEstoque.dao;

import javax.ejb.Stateless;

import org.ControleDeEstoque.entidade.Produto;

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
