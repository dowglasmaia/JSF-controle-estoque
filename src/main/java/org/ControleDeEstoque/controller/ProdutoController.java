package org.ControleDeEstoque.controller;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.ControleDeEstoque.dao.ProdutoDAO;
import org.ControleDeEstoque.entidade.Produto;
import org.omnifaces.util.Messages;

@Named
@SessionScoped
public class ProdutoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@EJB
	private ProdutoDAO pDao;

	@Inject
	private Produto produto;

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
				produto.setEstoqueAtual(produto.getEstoqueAtual() + produto.getQuantidade()); // Atualiza o Estoque do Produto
				
				Messages.addGlobalInfo("Produto Salvo com Sucesso!");
			} else {
				pDao.update(produto);
				produto.setEstoqueAtual(produto.getEstoqueAtual() + produto.getQuantidade()); // Atualiza o Estoque do Produto
				Messages.addGlobalInfo("Produto Atualizado com Sucesso!");
			}

		} catch (Exception e) {
			Messages.addGlobalError("Erro ao Tentar Salvar ou Atualizar o Produto!");
			e.printStackTrace();
		}
	}

	// ** Getters e Setters

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
