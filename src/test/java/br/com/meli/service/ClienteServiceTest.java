package br.com.meli.service;

import br.com.meli.dto.ClienteDTO;
import br.com.meli.repository.ClientesRepository;
import exception.ResponseEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

	private ClienteService mockService;
	private ClientesRepository mockRepository;

	@BeforeEach
	void mockFactory() {
		this.mockService = new ClienteService();
		this.mockRepository = new ClientesRepository();
	}

	/**
	 * Verifca se o retorno do service getAllClientes retorna todos os
	 * itens do documento
	 *
	 * @author Thiago Campos
	 */
	@Test
	void DeveRetornarTodosOsItensDoBanco() {
		List<ClienteDTO> mock = mockService.listaClientes();

		assertEquals(mockRepository.getAll().size(), mock.size());
	}

	/**
	 * Verificando se o filtre Cliente por estado possui o retorno
	 * correto, também verifica se o parametro passado possui
	 * tratamento de Case
	 *
	 * @author Thiago Campos
	 */
	@Test
	void DeveriaRetornarUmaLIstaDeClientesFiltradosPorEstado() {
		assertEquals(3, mockService.filteredByState("sp").size());
		assertEquals(2, mockService.filteredByState("mg").size());
		assertEquals(1, mockService.filteredByState("Rj").size());
	}

	/**
	 * Aguarda receber um Erro do tipo ResponseEntityException
	 *
	 * @author Thiago Campos
	 */
	@Test
	void DeveriaRetornarUmaExceptionQuandoOItemBuscadoPeloClienteNaoExiste() {
		assertThrows(ResponseEntityException.class, () -> mockService.filteredByState("mock Error"));
	}

	/**
	 * Verifica se a msg de erro corresponde ao aguardado pela regra de
	 * negócio
	 *
	 * @author Thiago Campos
	 */
	@Test
	void DeveriaRetornarOItemQUeOcasionouOErro() {
		try {
			mockService.filteredByState("mock Error");
			fail("A função não retornou uma ResponseEntityException");
		} catch (ResponseEntityException e) {
			assertEquals("A pesquisa pelo valor mock Error retornou nula", e.getMessage());
		}
	}

}
