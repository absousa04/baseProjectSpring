package br.com.vr.divulgacao.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.vr.divulgacao.entity.DirecionamentoFluxo;

public interface DirecionamentoFluxoRepository extends PagingAndSortingRepository<DirecionamentoFluxo, String>  {
	
	List<DirecionamentoFluxo> findAllByCpfBeneficiarioAndEnviarEmOrderByOrdemAsc(String cpf, Date data);
	
	DirecionamentoFluxo findAllByEnviarEmAndCpfBeneficiario(Date enviarEm,String cpfBeneficiario);

	Page<DirecionamentoFluxo> findByCpfBeneficiario(String cpfBeneficiario, Pageable pageable); 
	 
}
 