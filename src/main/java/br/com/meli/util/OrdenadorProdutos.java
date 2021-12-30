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
	List<Produto> ordenaAlfabeticamenteCrescente(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getName))
				.collect(Collectors.toList());
	}

	List<Produto> ordenaAlfabeticamenteDecrescente(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getName).reversed())
				.collect(Collectors.toList());
	}

	List<Produto> ordenaPrecoMaior(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getPrice))
				.collect(Collectors.toList());
	}

	List<Produto> ordenaPrecoMenor(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.sorted(Comparator.comparing(Produto::getPrice).reversed())
				.collect(Collectors.toList());
	}

	List<Produto> filtraCategoryName(List<Produto> listaProdutos, String categoryName) {
		return listaProdutos.stream()
				.filter(p -> p.getCategory().equalsIgnoreCase(categoryName))
				.collect(Collectors.toList());
	}

	List<Produto> filtraProductName(List<Produto> listaProdutos, String productName) {
		return listaProdutos.stream()
				.filter(p -> p.getName().equalsIgnoreCase(productName))
				.collect(Collectors.toList());
	}

	List<Produto> filtraBrandName(List<Produto> listaProdutos, String brandName) {
		return listaProdutos.stream()
				.filter(p -> p.getBrand().equalsIgnoreCase(brandName))
				.collect(Collectors.toList());
	}

	List<Produto> filtraFreeShipping(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.filter(p -> p.getFreeShipping())
				.collect(Collectors.toList());
	}

	List<Produto> filtraNonFreeShipping(List<Produto> listaProdutos) {
		return listaProdutos.stream()
				.filter(p -> !p.getFreeShipping())
				.collect(Collectors.toList());
	}

	public enum Ordenador {
		ORDENA_ALFABETICAMENTE_CRESCENTE(0),
		ORDENA_ALFABETICAMENTE_DECRESCENTE(1),
		ORDENA_MAIOR_PRECO(2),
		ORDENA_MENOR_PRECO(3);

		private int valor;

		private Ordenador(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}

	public enum Filtro {
		FILTRA_CATEGORY_NAME(0),
		FILTRA_PRODUCT_NAME(1),
		FILTRA_BRAND_NAME(2);

		private int valor;

		private Filtro(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}

	public enum Shipping {
		FILTRA_FREE_SHIPPING(0),
		FILTRA_NON_FREE_SHIPPING(1);

		private int valor;

		private Shipping(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}

	public List<Produto> odernarProdutos(List<Produto> listaProdutos, String nameCategoryOrBrand,
			Ordenador ordenador) {
		switch (ordenador) {
			case ORDENA_ALFABETICAMENTE_CRESCENTE:
				return this.ordenaAlfabeticamenteCrescente(listaProdutos);
			case ORDENA_ALFABETICAMENTE_DECRESCENTE:
				return this.ordenaAlfabeticamenteDecrescente(listaProdutos);
			case ORDENA_MAIOR_PRECO:
				return this.ordenaPrecoMaior(listaProdutos);
			case ORDENA_MENOR_PRECO:
				return this.ordenaPrecoMenor(listaProdutos);
			default:
				throw new IllegalArgumentException();
		}
	}

	public List<Produto> filtrarProdutos(List<Produto> listaProdutos, String nameCategoryOrBrand,
			Filtro filtro) {
		switch (filtro) {
			case FILTRA_CATEGORY_NAME:
				return this.filtraCategoryName(listaProdutos, nameCategoryOrBrand);
			case FILTRA_PRODUCT_NAME:
				return this.filtraProductName(listaProdutos, nameCategoryOrBrand);
			case FILTRA_BRAND_NAME:
				return this.filtraBrandName(listaProdutos, nameCategoryOrBrand);
			default:
				throw new IllegalArgumentException();
		}
	}

	public List<Produto> filtrarShipping(List<Produto> listaProdutos, String nameCategoryOrBrand,
			Shipping shipping) {
		switch (shipping) {
			case FILTRA_FREE_SHIPPING:
				return this.filtraFreeShipping(listaProdutos);
			case FILTRA_NON_FREE_SHIPPING:
				return this.filtraNonFreeShipping(listaProdutos);
			default:
				throw new IllegalArgumentException();
		}
	}
}
