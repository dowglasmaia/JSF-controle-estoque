package com.maia.ce.controllerview;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Fornecedor;
import com.maia.ce.entity.Produto;
import com.maia.ce.service.FornecedorService;
import com.maia.ce.service.ProdutoService;

@Named
@ViewScoped
public class ProdutoView implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private Produto produto;

	@Inject
	private ProdutoService proService;

	@Inject
	private FornecedorService fornService;

	private Integer qtdaSaida;

	private List<Produto> produtos = new ArrayList<>();

	private List<Fornecedor> fornecedores = new ArrayList<>();

	public ProdutoView() {

	}

	// novo
	public void novo() {
		proService.novo();
	}

	// Salvar ou Atualizar
	public void save() {
		proService.saveOrUpdate();
	}

	// listar
	public void listarTodos() {
		proService.ListarTodos();
	}

	// Listar Fornecedores
	public List<Fornecedor> listarFornecedores() {
		return fornecedores = fornService.listarFornecedores();

	}

	// Atualiza Estoque
	public void baixarEstoque() {
		if (produto.getEstoqueAtual() != 0 && qtdaSaida <= produto.getEstoqueAtual()) {
			produto.setEstoqueAtual(produto.getEstoqueAtual() - qtdaSaida);
			save();
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public List<Fornecedor> getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(List<Fornecedor> fornecedores) {
		this.fornecedores = fornecedores;
	}
	public Integer getQtdaSaida() {
		return qtdaSaida;
	}
	public void setQtdaSaida(Integer qtdaSaida) {
		this.qtdaSaida = qtdaSaida;
	}

}
