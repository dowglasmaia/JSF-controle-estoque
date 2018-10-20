package com.maia.ce.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Produto;
import com.maia.ce.repository.ProdutoDAO;

@Named
@SessionScoped
public class ProdutoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoDAO pDao;

	@Inject
	private Produto produto;

	private List<Produto> produtos = new ArrayList<>();

	private Integer qtdaSaida;

	public ProdutoController() {

	}

	// Novo
	public void novo() {
		this.produto = new Produto();

	}

	// Salvar
	public void saveOrUpdate() {
		try {
			if (produto.getId() == null) {
				pDao.persiste(produto);
				produto.setEstoqueAtual(produto.getEstoqueAtual() + produto.getQuantidade()); // Atualiza o Estoque do
																								// Produto
				Messages.addGlobalInfo("Produto Salvo com Sucesso!");
			} else {
				pDao.update(produto);
				produto.setEstoqueAtual(produto.getEstoqueAtual() + produto.getQuantidade()); // Atualiza o Estoque do
																								// Produto
				Messages.addGlobalInfo("Produto Atualizado com Sucesso!");
			}
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Produto!");
			e.printStackTrace();
		}
	}

	// Listar Todos

	public void ListarTodos() {
		try {
			produtos = pDao.findAll();
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Realizar Consulta de Produtos!");
			e.printStackTrace();
		}
	}

	// Remove
	public void remove(Integer id) {
		try {
			produto = pDao.findById(id);
			pDao.remove(produto);
			Messages.addGlobalInfo("Produto Removido com Sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Remover Produto!");
			e.printStackTrace();
		}
	}

	// Atualiza Estoque
	public void baixarEstoque() {
		if (produto.getEstoqueAtual() != 0 && qtdaSaida <= produto.getEstoqueAtual()) {
			produto.setEstoqueAtual(produto.getEstoqueAtual() - qtdaSaida);
			saveOrUpdate();
		} else {
			Messages.addGlobalWarn("Erro ao TentarAtualizar Estoque!");
		}
	}

	// ** Getters e Setters
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

	public Integer getQtdaSaida() {
		return qtdaSaida;
	}

	public void setQtdaSaida(Integer qtdaSaida) {
		this.qtdaSaida = qtdaSaida;
	}

}
