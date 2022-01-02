package br.com.meli.service;

import br.com.meli.entity.Cliente;
import br.com.meli.repository.ClienteRepository;
import exception.ResponseEntityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

	private ClienteService mockService;
	private ClienteRepository mockRepository;

	@BeforeEach
	void mockFactory(){
		this.mockService = new ClienteService();
		this.mockRepository = new ClienteRepository();
	}

	/**
	 * @author Thiago Campos
	 * @description Verifca se o retorno do service getAllClientes retorna todos os itens do documento
	 */
	@Test
	void DeveRetornarTodosOsItensDoBanco(){
		List<Cliente> mock = mockService.getAll();

		assertEquals(mockRepository.getAll().size(), mock.size());
	}

	/**
	 * @author Thiago Campos
	 * @description Verificando se o filtre Cliente por estado possui o retorno correto, também verifica se o parametro passado possui tratamento de Case
	 */
	@Test
	void DeveriaRetornarUmaLIstaDeClientesFiltradosPorEstado(){
		assertEquals(3, mockService.filteredByState("sp").size());
		assertEquals(2, mockService.filteredByState("mg").size());
		assertEquals(1, mockService.filteredByState("Rj").size());
	}

	/**
	 * @author Thiago Campos
	 * @description Aguarda receber um Erro do tipo ResponseEntityException
	 */
	@Test
	void DeveriaRetornarUmaExceptionQuandoOItemBuscadoPeloClienteNaoExiste(){
		assertThrows(ResponseEntityException.class, () -> mockService.filteredByState("mock Error"));
	}

	/**
	 * @author Thiago Campos
	 * @description Verifica se a msg de erro corresponde ao aguardado pela regra de negócio
	 */
	@Test
	void DeveriaRetornarOItemQUeOcasionouOErro(){
		try {
			mockService.filteredByState("mock Error");
			fail("A função não retornou uma ResponseEntityException");
		} catch (ResponseEntityException e){
			 assertEquals("A pesquisa pelo valor mock Error retornou nula", e.getMessage());
		}
	}

}
