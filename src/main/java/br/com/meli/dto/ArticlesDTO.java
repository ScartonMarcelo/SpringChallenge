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
	 * Converte objeto Articles em ArticlesDTO
	 *
	 * @author Thomaz Ferreira
	 * @param article
	 * @return ArticlesDTO
	 */
	public static ArticlesDTO converte(Articles article) {
		return ArticlesDTO.builder()
				.articlesDTO(ProdutoDTO.converteLista(article))
				.build();
	}

	/**
	 * Converte objeto ArticleDTO em Article
	 *
	 * @author Thomaz Ferreira
	 * @param dto
	 * @return Articles
	 */
	public static Articles converte(ArticlesDTO dto) {
		return Articles.builder()
				.articles(ProdutoDTO.converteLista(dto))
				.build();
	}
}
