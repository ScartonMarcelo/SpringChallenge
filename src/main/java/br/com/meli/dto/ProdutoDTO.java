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
public class ProdutoDTO {

	/**
	 * Author Thiago Campos
	 * Return ProdutoDTO
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Long productId;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer quantity;
	private Boolean freeShipping;
	private String prestige;

	/**
	 * @param ProdutoDTO
	 * @return Produto
	 * @Author Thiago Campos
	 * @Description Converte um ProdutoDTO em Produto
	 */
	public static Produto convert(ProdutoDTO dto) {
		return Produto.builder()
			.productId(dto.productId)
			.name(dto.name)
			.category(dto.category)
			.brand(dto.brand)
			.price(dto.price)
			.quantity(dto.quantity)
			.freeShipping(dto.freeShipping)
			.prestige(dto.prestige).build();
	}

	/**
	 * @param Produto
	 * @return ProdutoDTO
	 * @Author Thiago Campos
	 * @Description Converte um Produto em ProdutoDTO
	 */
	public static ProdutoDTO convert(Produto produto) {
		return ProdutoDTO.builder()
			.productId(produto.getProductId())
			.name(produto.getName())
			.category(produto.getCategory())
			.brand(produto.getBrand())
			.price(produto.getPrice())
			.quantity(produto.getQuantity())
			.freeShipping(produto.getFreeShipping())
			.prestige(produto.getPrestige()).build();
	}

	/**
	 * @param List<Produto>
	 * @return List<ProdutoDTO>
	 * @Author Thiago Campos
	 * @Description Converte uma lista de Produtos em ProdutosDTO
	 */
	public static List<ProdutoDTO> convert(List<Produto> list) {
		return list.stream().map(produto -> convert(produto)).collect(Collectors.toList());
	}
}
