package br.com.vr.divulgacao.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.vr.divulgacao.entity.Canal;
import br.com.vr.divulgacao.entity.DirecionamentoEnvio;
import br.com.vr.divulgacao.entity.DirecionamentoFluxo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirecionamentoEnvioDTO implements Serializable {

	private static final long serialVersionUID = -4354064312309739848L;

	private String idDirecionamentoEnvio;
	
	private CanalDTO canal;
	
	private DirecionamentoFluxoDTO direcionamento;

	private Date criadoEm;

	private Date atualizadoEm;

	public static DirecionamentoEnvioDTO getDTO(DirecionamentoEnvio direcionamentoEnvio) {
		//// @formatter:off
    	return DirecionamentoEnvioDTO.builder()
        		.idDirecionamentoEnvio(direcionamentoEnvio.getIdDirecionamentoEnvio())
        		.canal(CanalDTO.getDTO(direcionamentoEnvio.getCanal()))
        		.direcionamento(DirecionamentoFluxoDTO.getDTO(direcionamentoEnvio.getDirecionamento()))
        		.criadoEm(direcionamentoEnvio.getCriadoEm()).build();        		
    	// @formatter:on
    }

}
