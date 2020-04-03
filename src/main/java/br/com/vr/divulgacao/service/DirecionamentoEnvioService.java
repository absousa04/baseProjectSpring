package br.com.vr.divulgacao.service;

import java.util.List;

import br.com.vr.divulgacao.dto.DirecionamentoEnvioDTO;
import br.com.vr.divulgacao.entity.DirecionamentoEnvio;

public interface DirecionamentoEnvioService {

	void createOrUpdate(DirecionamentoEnvio direcionamentoEnvio);

	void findByIdDirecionamentoEnvio(String id);

	boolean existsByDirecionamentoIdDirecionamentoAndCanalIdCanal(String idDirecionamento, Long idCanal);

	List<DirecionamentoEnvioDTO> findAll(Integer pagina, Integer tamanho);

	List<DirecionamentoEnvioDTO> findByIdDirecionamentoFluxo(String idDirecionamentoFluxo, Integer pagina,
			Integer tamanho);

}
