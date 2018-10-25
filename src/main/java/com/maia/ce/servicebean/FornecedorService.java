package com.maia.ce.servicebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Fornecedor;
import com.maia.ce.repositorydao.FornecedorDAO;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Model
@ViewScoped
public class FornecedorService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private FornecedorDAO fDao;

	@Inject
	private Fornecedor fornecedor;

	private List<Fornecedor> fornecedores = new ArrayList<>();

	// Salvar ou Atualiza de acordo com a Regra
	public void saveOrUpdate() {
		try {
			if (this.fornecedor.getId() == null) {
				fDao.save(fornecedor);
				Messages.addGlobalInfo("Fornecedor Salvo com Sucesso!");
			} else {
				fDao.update(fornecedor);
				Messages.addGlobalInfo("Fornecedor Atualizado com Sucesso!");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Fornecedor!");
			e.printStackTrace();
		}
	}

	// listar
	public void listarFornecedores() {
		try {
			fornecedores = fDao.findAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Fornecedores!");
			e.printStackTrace();
		}	
	}

	// Getters e Setters

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

}
