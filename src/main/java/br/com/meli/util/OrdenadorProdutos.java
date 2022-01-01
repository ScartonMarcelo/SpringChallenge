package br.com.meli.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.meli.entity.Produto;

/**
 * @author André Arroxellas
 * @description métodos de ordenação e filtro
 */
public class OrdenadorProdutos {
	List<Produto> ordenaPorAtributo(List<Produto> listaProdutos, Comparator<Produto> comparator) {
		return listaProdutos.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}

	List<Produto> filtraPorAtributo(List<Produto> listaProdutos, Predicate<Produto> selector) {
		return listaProdutos.stream()
				.filter(selector)
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
		FILTRA_BRAND_NAME(2),
		FILTRA_FREE_SHIPPING(3),
		FILTRA_NON_FREE_SHIPPING(4);

		private int valor;

		private Filtro(int valor) {
			this.valor = valor;
		}

		public int getValor() {
			return this.valor;
		}
	}

	public List<Produto> odernarProdutos(List<Produto> listaProdutos, Ordenador ordenador) {
		switch (ordenador) {
			case ORDENA_ALFABETICAMENTE_CRESCENTE:
				return this.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getName));
			case ORDENA_ALFABETICAMENTE_DECRESCENTE:
				return this.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getName).reversed());
			case ORDENA_MAIOR_PRECO:
				return this.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getPrice));
			case ORDENA_MENOR_PRECO:
				return this.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getPrice).reversed());
			default:
				throw new IllegalArgumentException();
		}
	}

	public List<Produto> filtrarProdutos(List<Produto> listaProdutos, String atributoString,
			Filtro filtro) {
		switch (filtro) {
			case FILTRA_CATEGORY_NAME:
				return this.filtraPorAtributo(listaProdutos,
						p -> p.getCategory().equalsIgnoreCase(atributoString));
			case FILTRA_PRODUCT_NAME:
				return this.filtraPorAtributo(listaProdutos,
						p -> p.getName().equalsIgnoreCase(atributoString));
			case FILTRA_BRAND_NAME:
				return this.filtraPorAtributo(listaProdutos,
						p -> p.getBrand().equalsIgnoreCase(atributoString));
			case FILTRA_FREE_SHIPPING:
				return this.filtraPorAtributo(listaProdutos, p -> p.getFreeShipping());
			case FILTRA_NON_FREE_SHIPPING:
				return this.filtraPorAtributo(listaProdutos, p -> !p.getFreeShipping());
			default:
				throw new IllegalArgumentException();
		}
	}

}
