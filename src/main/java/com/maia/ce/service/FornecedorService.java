package com.maia.ce.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Fornecedor;
import com.maia.ce.repositorydao.FornecedorDAO;

@RequestScoped
public class FornecedorService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private FornecedorDAO fDao;

	@Inject
	private Fornecedor fornecedor;

	private List<Fornecedor> fornecedores = new ArrayList<>();

	// listar
	public List<Fornecedor> listarFornecedores() {
		try {
			return fornecedores = fDao.findAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Fornecedores!");
			e.printStackTrace();
		}
		return null;
	}

	
	// Getters e Setters
	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

}
