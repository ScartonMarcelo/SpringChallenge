package br.com.meli.service;

import br.com.meli.dto.ArticlesPurchaseDTO;
import br.com.meli.entity.Articles;
import br.com.meli.entity.ArticlesPurchase;
import br.com.meli.entity.Produto;
import br.com.meli.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ArticlesService {

	@Autowired
	private ArticleRepository articlesRepository;

    /**
    * @author Thomaz Ferreira
    * @description Chama função para serializar Produtos em JSON
    * @param Articles articles
    * @return void
    */   
	public void salvarProdutos(Articles articles){
		articlesRepository.serializaProdutos(articles.getArticles());
	}


    /**
    * @author Marcelo Scarton
    * @description Retorna lista de Produtos solicitados para compra
    * @param ArticlesPurchase articlesPurchaseList
    * @return List<Produto>
    */  
	public List<Produto> retornarProdutosPurchase(ArticlesPurchase articlesPurchaseList) {
		List<Produto> produtos = articlesRepository.desserializaProdutos();
		List<Produto> purchaseList = new ArrayList<>();
		for (ArticlesPurchaseDTO a : articlesPurchaseList.getArticlesPurchaseRequest()) {
			purchaseList.add(produtos.stream().filter(p -> p.getProductId() == a.getProductId()).findAny().orElse(null));
		}
		return purchaseList;
	}

    
    /**
    * @author Marcelo Scarton
    * @description Retorna valor total da lista de Produtos solicitados para compra
    * @param List<Produto> articles
    * @return BigDecimal
    */ 
	public BigDecimal retornarTotalPurchase(List<Produto> articles) {
		BigDecimal total = new BigDecimal("0");
		for (Produto p : articles) {
			total = total.add(p.getPrice().multiply(BigDecimal.valueOf(p.getQuantity())));
		}
		return total;
    }
    
    
    /**
    * @author Francisco Alves
    * @description Retorna todos os produtos cadastrados
    * @return List<Produto>
    */
	public List<Produto> getProdutos(){
		return articlesRepository.desserializaProdutos();
	}
}
