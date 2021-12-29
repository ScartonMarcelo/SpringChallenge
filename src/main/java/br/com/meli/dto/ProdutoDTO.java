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

    // TODO: Dicutir sobre isto
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private long productId;
    private String name;
    private String category;
    private String brand;
    private BigDecimal price;
    private int quantity;
    private boolean freeShipping;
    private String prestige;


		// Converte DTO em Model
		public static Produto converte(ProdutoDTO dto){
			return Produto.builder()
				.productId(dto.getProductId())
				.name(dto.getName())
				.category(dto.getCategory())
				.brand(dto.getBrand())
				.price(dto.getPrice())
				.quantity(dto.getQuantity())
				.freeShipping(dto.isFreeShipping())
				.prestige(dto.getPrestige())
				.build();
		}

		// Converte Model em DTO
		public static ProdutoDTO converte(Produto p){
			return ProdutoDTO.builder()
				.productId(p.getProductId())
				.name(p.getName())
				.category(p.getCategory())
				.brand(p.getBrand())
				.price(p.getPrice())
				.quantity(p.getQuantity())
				.freeShipping(p.isFreeShipping())
				.prestige(p.getPrestige())
				.build();
		}


		// Converte List<Produto> em List<ProdutoDTO>
  		public static List<ProdutoDTO> converte(List<Produto> produtos){
		  	return produtos.stream().map(p -> converte(p)).collect(Collectors.toList());
		}
}
