package com.maia.ce.servicebean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.inject.Model;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Fornecedor;
import com.maia.ce.entity.Produto;
import com.maia.ce.repositorydao.FornecedorDAO;
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

	@EJB
	private FornecedorDAO fDao;

	@Inject
	private Produto produto;

	private Integer qtdaSaida;
	private Integer quantidade;

	private List<Fornecedor> fornecedores = new ArrayList<>();
	private List<Produto> produtos = new ArrayList<>();

	// Novo
	public void novo() {
		this.produto = new Produto();
	}

	// Salvar
	public void saveOrUpdate() {
		try {
			if (this.produto.getId() != null && produto.getId() != 0) {
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
			return produtos = pDao.findAll();
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

	@PostConstruct
	public void listarFornecedores() {
		try {
			this.fornecedores = fDao.findAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro de Sistema!" + Fornecedor.class);
			e.printStackTrace();
		}

	}

	// Atualiza Estoque
	public void baixarEstoque() {
		if (produto.getEstoqueAtual() != 0 && qtdaSaida <= produto.getEstoqueAtual()) {
			produto.setEstoqueAtual(produto.getEstoqueAtual() - qtdaSaida);
			try {
				pDao.update(produto);
				Messages.addGlobalInfo("Estoque Atualizado com Sucesso!");
			} catch (Exception e) {
				Messages.addGlobalWarn("Erro ao Tentar Atualizar Estoque!");
				e.printStackTrace();
			}
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

	public List<Produto> getProdutos() {
		return produtos;
	}

}
