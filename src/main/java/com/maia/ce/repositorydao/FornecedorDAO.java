package com.maia.ce.repositorydao;

import javax.ejb.Stateless;

import com.maia.ce.entity.Fornecedor;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Stateless
public class FornecedorDAO extends GenericDAO<Fornecedor> {
	private static final long serialVersionUID = 1L;

	public FornecedorDAO() {
		super();
		classPersistente = Fornecedor.class;
	}

}
