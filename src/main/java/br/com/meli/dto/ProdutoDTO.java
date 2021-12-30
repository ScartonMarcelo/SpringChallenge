package br.com.meli.dto;

import br.com.meli.entity.Articles;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import br.com.meli.entity.Produto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

	/**
	 * @Author Thomaz Ferreira, Thiago Henrique, Francisco Alves, Marcelo Scarton,
	 *         André Arroxellas
	 * @Description Model Produto DTO
	 */

	private Long productId;
	private String name;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String category;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String brand;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private BigDecimal price;
	private Integer quantity;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Boolean freeShipping;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String prestige;

	/**
	 * @Author Thomaz Ferreira
	 * @Description Converte obejto ProdutoDTO em Produto
	 * @param ProdutoDTO dto
	 * @return Produto
	 */

	public static Produto converte(ProdutoDTO dto) {
		return Produto.builder()
				.productId(dto.getProductId())
				.name(dto.getName())
				.category(dto.getCategory())
				.brand(dto.getBrand())
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.freeShipping(dto.getFreeShipping())
				.prestige(dto.getPrestige())
				.build();
	}

	/**
	 * @Author Thomaz Ferreira
	 * @Description Converte obejto Produto em ProdutoDTO
	 * @param Produto p
	 * @return ProdutoDTO
	 */
	public static ProdutoDTO converte(Produto p) {
		return ProdutoDTO.builder()
				.productId(p.getProductId())
				.name(p.getName())
				.category(p.getCategory())
				.brand(p.getBrand())
				.price(p.getPrice())
				.quantity(p.getQuantity())
				.freeShipping(p.getFreeShipping())
				.prestige(p.getPrestige())
				.build();
	}

	/**
	 * @author Thomaz Ferreira
	 * @Description Converte lista de objetos Produtos em uma lista de objetos
	 *              ProdutoDTO
	 * @param Articles articles
	 * @return List lista
	 */
	public static List<ProdutoDTO> converteLista(Articles articles) {
		List<ProdutoDTO> lista = new ArrayList<ProdutoDTO>();
		for (Produto p : articles.getArticles()) {
			lista.add(ProdutoDTO.converte(p));
		}
		return lista;
	}

	/**
	 * @author Thomaz Ferreira
	 * @Description Converte lista de objetos ProdutoDTO em uma lista de objetos
	 *              Produto
	 * @param Articles articles
	 * @return List lista
	 */
	public static List<Produto> converteLista(ArticlesDTO articles) {
		List<Produto> lista = new ArrayList<Produto>();
		for (ProdutoDTO p : articles.getArticlesDTO()) {
			lista.add(ProdutoDTO.converte(p));
		}
		return lista;
	}
}
