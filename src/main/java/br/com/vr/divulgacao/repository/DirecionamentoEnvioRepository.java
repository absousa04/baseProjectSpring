package br.com.vr.divulgacao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.vr.divulgacao.entity.DirecionamentoEnvio;

public interface DirecionamentoEnvioRepository extends PagingAndSortingRepository<DirecionamentoEnvio, String>  {
	
	boolean existsByDirecionamentoIdDirecionamentoAndCanalIdCanal (String idDirecionamento, Long idCanal);

	Page<DirecionamentoEnvio> findByDirecionamentoIdDirecionamento(String idDirecionamentoFluxo, Pageable pageable);
}
