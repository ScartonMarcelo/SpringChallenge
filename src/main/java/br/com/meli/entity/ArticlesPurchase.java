package br.com.meli.entity;

import java.util.ArrayList;
import java.util.List;

import br.com.meli.dto.ArticlesPurchaseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticlesPurchase {
	private List<ArticlesPurchaseDTO> articlesPurchaseRequest = new ArrayList<>();
}
