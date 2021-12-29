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

	// TODO: Dicutir sobre isto
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private long id;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private int quantity;
	private boolean freeShipping;
	private String prestige;

	public static Produto convert(ProdutoDTO dto) {
		return Produto.builder()
			.id(dto.id)
			.name(dto.name)
			.category(dto.category)
			.brand(dto.brand)
			.price(dto.price)
			.quantity(dto.quantity)
			.freeShipping(dto.freeShipping)
			.prestige(dto.prestige).build();
	}

	public static ProdutoDTO convert(Produto produto){
		return ProdutoDTO.builder()
			.id(produto.getId())
			.name(produto.getName())
			.category(produto.getCategory())
			.brand(produto.getBrand())
			.price(produto.getPrice())
			.quantity(produto.getQuantity())
			.freeShipping(produto.isFreeShipping())
			.prestige(produto.getPrestige()).build();
	}

	public static List<ProdutoDTO> convert(List<Produto> list){
		return list.stream().map(produto -> convert(produto)).collect(Collectors.toList());
	}
}
