package br.com.meli.util;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.meli.entity.Produto;

/**
 * @author André Arroxellas
 * @description métodos de ordenação e filtro
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
				.filter(p -> p.getFreeShipping())
				.collect(Collectors.toList());
	}

	public List<Produto> filtraNonFreeShipping(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.filter(p -> !p.getFreeShipping())
				.collect(Collectors.toList());
	}

	enum Filtro {
		ORDENA_ALFABETICAMENTE_CRESCENTE(0),
		ORDENA_ALFABETICAMENTE_DECRESCENTE(1),
		ORDENA_MAIOR_PRECO(2),
		ORDENA_MENOR_PRECO(3),
		FILTRA_CATEGORY_NAME(4),
		FILTRA_BRAND_NAME(5),
		FILTRA_FREE_SHIPPING(6),
		FILTRA_NON_FREE_SHIPPING(7);

		private int valor;

		private Filtro(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}

	public List<Produto> OrdenarProdutos(List<Produto> listaProdutos, String nameCategoryOrBrand, Filtro filtro) {
		switch (filtro) {
			case ORDENA_ALFABETICAMENTE_CRESCENTE:
				return this.ordenaAlfabeticamenteCrescente(listaProdutos);
			case ORDENA_ALFABETICAMENTE_DECRESCENTE:
				return this.ordenaAlfabeticamenteDecrescente(listaProdutos);
			case ORDENA_MAIOR_PRECO:
				return this.ordenaPrecoMaior(listaProdutos);
			case ORDENA_MENOR_PRECO:
				return this.ordenaPrecoMenor(listaProdutos);
			case FILTRA_CATEGORY_NAME:
				return this.filtraCategoryName(listaProdutos, nameCategoryOrBrand);
			case FILTRA_BRAND_NAME:
				return this.filtraBrandName(listaProdutos, nameCategoryOrBrand);
			case FILTRA_FREE_SHIPPING:
				return this.filtraFreeShipping(listaProdutos);
			case FILTRA_NON_FREE_SHIPPING:
				return this.filtraNonFreeShipping(listaProdutos);
			default:
				throw new IllegalArgumentException();
		}
	}
}
