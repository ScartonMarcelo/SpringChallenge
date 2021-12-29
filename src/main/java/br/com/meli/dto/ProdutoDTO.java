package br.com.meli.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.meli.entity.Produto;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

	/**
	 * @Author Thomaz Ferreira, Thiago Henrique, Francisco Alves, Marcelo Scarton, André Arroxellas
	 * @Description Model Produto DTO
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
	 * @Author Thomaz Ferreira
	 * @Description Converte obejto ProdutoDTO em Produto
	 * @param ProdutoDTO dto
	 * @return Produto
	 */

	public static Produto converte(ProdutoDTO dto){
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
	public static ProdutoDTO converte(Produto p){
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
}
