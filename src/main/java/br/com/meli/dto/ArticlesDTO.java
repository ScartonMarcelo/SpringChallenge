package br.com.meli.dto;

import br.com.meli.entity.Produto;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesDTO {

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

	public static ArticlesDTO converte(Produto produto) {
		return ArticlesDTO.builder()
			.productId(produto.getProductId())
			.name(produto.getName())
			.category(produto.getCategory())
			.brand(produto.getCategory())
			.price(produto.getPrice())
			.quantity(produto.getQuantity())
			.freeShipping(produto.getFreeShipping())
			.prestige(produto.getPrestige())
			.build();
	}


	public static List<ArticlesDTO> converte(List<Produto> articles) {
		return articles.stream().map(a -> converte(a)).collect(Collectors.toList());
	}
}
