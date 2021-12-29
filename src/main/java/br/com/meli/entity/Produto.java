package br.com.meli.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Produto {

    private long productId;
    private String name;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String category;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String brand;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private BigDecimal price;
    private int quantity;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean freeShipping;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String prestige;
}
