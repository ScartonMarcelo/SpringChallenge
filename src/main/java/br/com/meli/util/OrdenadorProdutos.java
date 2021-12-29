package br.com.meli.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.meli.entity.Produto;

/**
 * @author André Arroxellas
 * @description métodos de ordenação
 */
public class OrdenadorProdutos {
	public List<Produto> ordenaAlfabeticamenteCrescente(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getName))
				.collect(Collectors.toList());
	}

	public List<Produto> ordenaAlfabeticamenteDecrescente(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getName).reversed())
				.collect(Collectors.toList());
	}

	public List<Produto> ordenaPrecoMaior(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getPrice))
				.collect(Collectors.toList());
	}

	public List<Produto> ordenaPrecoMenor(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getPrice).reversed())
				.collect(Collectors.toList());
	}

	public List<Produto> filtraCategoryName(List<Produto> listaProdutos, String categoryName) {
		return listaProdutos.stream()
				.filter(p -> p.getName().equals(categoryName))
				.collect(Collectors.toList());
	}

	public List<Produto> filtraBrandName(List<Produto> listaProdutos, String brandName) {
		return listaProdutos.stream()
				.filter(p -> p.getBrand().equals(brandName))
				.collect(Collectors.toList());
	}

	public List<Produto> filtraFreeShipping(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.filter(p -> p.isFreeShipping())
				.collect(Collectors.toList());
	}

	public List<Produto> filtraNonFreeShipping(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.filter(p -> !p.isFreeShipping())
				.collect(Collectors.toList());
	}
}
