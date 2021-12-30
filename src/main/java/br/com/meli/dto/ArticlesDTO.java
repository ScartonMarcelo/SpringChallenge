package br.com.meli.dto;

import br.com.meli.entity.Articles;
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

	private List<ProdutoDTO> articlesDTO = new ArrayList<>();

	/**
	 * @Author Thomaz Ferreira
	 * @Description Converte objeto Articles em ArticlesDTO
	 * @param Articles article
	 * @return ArticlesDTO
	 */
	public static ArticlesDTO converte(Articles article) {
		return ArticlesDTO.builder()
				.articlesDTO(ProdutoDTO.converteLista(article))
				.build();
	}

	/**
	 * @author Thomaz Ferreira
	 * @Description Converte objeto ArticleDTO em Article
	 * @param Articles article
	 * @return ArticlesDTO
	 */
	public static Articles converte(ArticlesDTO dto) {
		return Articles.builder()
				.articles(ProdutoDTO.converteLista(dto))
				.build();
	}
}
