package br.com.meli.util;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import br.com.meli.entity.Produto;
import exception.ResponseEntityException;

/**
 * métodos de ordenação e filtro
 *
 * @author André Arroxellas
 */
public class OrdenadorProdutos {

	/**
	 * Método de ornenação
	 *
	 * @author André Arroxellas
	 * @param List<Produto>
	 * @param Comparator<Produto>
	 * @return List<Produto>
	 */
	static List<Produto> ordenaPorAtributo(List<Produto> listaProdutos, Comparator<Produto> comparator) {
		return listaProdutos.stream()
				.sorted(comparator)
				.collect(Collectors.toList());
	}

	/**
	 * Método de filtro
	 *
	 * @author André Arroxellas
	 * @param List<Produto>
	 * @param Predicate<Produto>
	 * @return List
	 */
	static List<Produto> filtraPorAtributo(List<Produto> listaProdutos, Predicate<Produto> selector) {
		return listaProdutos.stream()
				.filter(selector)
				.collect(Collectors.toList());
	}

	/**
	 * Lista de ordenadores disponíveis
	 *
	 * @author André Arroxellas
	 */
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

	/**
	 * Lista de filtros disponíveis
	 *
	 * @author André Arroxellas
	 */
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

	/**
	 * Método que passa método de ordenação conforme Enum Ordenador
	 * disponível
	 *
	 * @author André Arroxellas
	 * @param List<Produto>
	 * @param Ordenador
	 * @throws ResponseEntityException
	 * @return List
	 */
	public static List<Produto> odernarProdutos(List<Produto> listaProdutos, Ordenador ordenador) {
		switch (ordenador) {
			case ORDENA_ALFABETICAMENTE_CRESCENTE:
				return OrdenadorProdutos.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getName));
			case ORDENA_ALFABETICAMENTE_DECRESCENTE:
				return OrdenadorProdutos.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getName).reversed());
			case ORDENA_MAIOR_PRECO:
				return OrdenadorProdutos.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getPrice));
			case ORDENA_MENOR_PRECO:
				return OrdenadorProdutos.ordenaPorAtributo(listaProdutos,
						Comparator.comparing(Produto::getPrice).reversed());
			default:
				throw new ResponseEntityException("Internal System Error", "500");
		}
	}

	/**
	 * Método que passa método de filtro conforme Enum Filtro
	 *
	 * @author André Arroxellas
	 * @param List<Produto>
	 * @param String
	 * @param Filtro
	 * @throws ResponseEntityException
	 * @return List
	 */
	public static List<Produto> filtrarProdutos(List<Produto> listaProdutos, String atributoString,
			Filtro filtro) {
		switch (filtro) {
			case FILTRA_CATEGORY_NAME:
				return OrdenadorProdutos.filtraPorAtributo(listaProdutos,
						p -> p.getCategory().equalsIgnoreCase(atributoString));
			case FILTRA_PRODUCT_NAME:
				return OrdenadorProdutos.filtraPorAtributo(listaProdutos,
						p -> p.getName().equalsIgnoreCase(atributoString));
			case FILTRA_BRAND_NAME:
				return OrdenadorProdutos.filtraPorAtributo(listaProdutos,
						p -> p.getBrand().equalsIgnoreCase(atributoString));
			case FILTRA_FREE_SHIPPING:
				return OrdenadorProdutos.filtraPorAtributo(listaProdutos, p -> p.getFreeShipping());
			case FILTRA_NON_FREE_SHIPPING:
				return OrdenadorProdutos.filtraPorAtributo(listaProdutos, p -> !p.getFreeShipping());
			default:
				throw new ResponseEntityException("Internal System Error", "500");
		}
	}
}
