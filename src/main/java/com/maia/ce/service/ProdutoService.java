package com.maia.ce.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.omnifaces.util.Messages;

import com.maia.ce.entity.Produto;
import com.maia.ce.repositorydao.ProdutoDAO;

@RequestScoped
public class ProdutoService implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoDAO pDao;

	@Inject
	private Produto produto;

	private List<Produto> produtos = new ArrayList<>();
	

	// Novo
	public void novo() {
		this.produto = new Produto();

	}

	// Salvar
	public void saveOrUpdate() {
		try {
			if (produto.getId() == null) {
				pDao.save(produto);
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
			pDao.remove(produto);
			Messages.addGlobalInfo("Produto Removido com Sucesso!");
		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Remover Produto!");
			e.printStackTrace();
		}
	}

}
