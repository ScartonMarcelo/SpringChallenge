package br.com.meli.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticlesPurchaseDTO {

	private Long productId;
	private String name;
	private String brand;
	private Integer quantity;

}
