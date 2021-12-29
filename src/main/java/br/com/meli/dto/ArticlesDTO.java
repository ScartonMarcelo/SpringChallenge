package br.com.meli.dto;

import br.com.meli.entity.Articles;
import br.com.meli.entity.Produto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesDTO {

	private List<Produto> articles = new ArrayList<>();

	// Converte Model em DTO
	public static ArticlesDTO converte(Articles article){
	  	return ArticlesDTO.builder()
		  	.articles(article.getArticles())
		  	.build();
	}

	// Converte DTO em Model
  	public static Articles converte(ArticlesDTO dto){
	  	return Articles.builder()
		  	.articles(dto.getArticles())
		  	.build();
	}
}
