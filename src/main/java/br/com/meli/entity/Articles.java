package br.com.meli.entity;

import java.util.ArrayList;
import java.util.List;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Articles {
	@NonNull
	private List<Produto> articles = new ArrayList<>();
}
