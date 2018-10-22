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
import com.maia.ce.entity.Produto;
import com.maia.ce.repositorydao.ProdutoDAO;

/*
 * Autor: Dowglas Maia
 * Skype: live:dowglasmaia
 * E-mail:dowglasmaia@live.com
 * 
 * */

@Model
@ViewScoped
public class ProdutoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoDAO pDao;

	@Inject
	private Produto produto;

	@Inject
	private FornecedorService fornService;

	private Integer qtdaSaida;
	private Integer quantidade;

	private List<Fornecedor> fornecedores = new ArrayList<>();

	// Novo
	public void novo() {
		this.produto = new Produto();
	}

	// Salvar
	public void saveOrUpdate() {
		try {
			if (this.produto.getId() != null) {
				produto.setEstoqueAtual(quantidade + produto.getEstoqueAtual());
				pDao.update(produto);

				Messages.addGlobalInfo("Produto Atualizado com Sucesso!");
			} else {
				produto.setEstoqueAtual(quantidade);
				pDao.save(produto);
				Messages.addGlobalInfo("Produto Salvo com Sucesso!");

			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Produto!");
			e.printStackTrace();
		}
	}

	// Listar Todos
	public List<Produto> ListarTodos() {
		try {
			return pDao.findAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Produtos!");
			e.printStackTrace();
		}
		return null;
	}

	// Remove
	public void remove(Integer id) {
		try {
			pDao.remove(produto);
			Messages.addGlobalInfo("Produto Removido com Sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Remover Produto!");
			e.printStackTrace();
		}
	}

	// Listar Fornecedores
	public List<Fornecedor> listarFornecedores() {
		return this.fornecedores = fornService.listarFornecedores();

	}

	// Atualiza Estoque
	public void baixarEstoque(Integer qtda) {
		if (produto.getEstoqueAtual() != 0 && qtda <= produto.getEstoqueAtual()) {
			produto.setEstoqueAtual(produto.getEstoqueAtual() - qtda);
			saveOrUpdate();
		} else {
			Messages.addGlobalWarn("Erro ao Tentar Atualizar Estoque!");
		}
	}

	// **Getters e Setters**//
	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdaSaida() {
		return qtdaSaida;
	}

	public void setQtdaSaida(Integer qtdaSaida) {
		this.qtdaSaida = qtdaSaida;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

}
