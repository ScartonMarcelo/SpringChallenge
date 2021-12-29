package br.com.meli.entity;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

	private Long productId;
	private String name;
	private String category;
	private String brand;
	private BigDecimal price;
	private Integer quantity;
	private Boolean freeShipping;
	private String prestige;
}
