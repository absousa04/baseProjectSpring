package br.com.vr.divulgacao.service;

import java.sql.Date;
import java.util.List;

import br.com.vr.divulgacao.dto.DirecionamentoFluxoDTO;
import br.com.vr.divulgacao.util.CanalEnum;

public interface DirecionamentoFluxoService {

	DirecionamentoFluxoDTO findByEnviarEmAndcpfBeneficiario(Date enviarEm, String cpf);

	List<DirecionamentoFluxoDTO> findAll(Integer pagina, Integer tamanho);

	List<DirecionamentoFluxoDTO> findByCpfBeneficiario(String cpf, Integer pagina, Integer tamanho);

	List<DirecionamentoFluxoDTO> obterDivulgacaoCanal(String cpf, Date data, CanalEnum canal, Integer quantidade);
	
}
