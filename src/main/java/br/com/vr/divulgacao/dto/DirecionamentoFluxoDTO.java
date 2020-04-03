package br.com.vr.divulgacao.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.vr.divulgacao.entity.DirecionamentoFluxo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DirecionamentoFluxoDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String idDirecionamento;

	private String cnpjEstabelecimento;

	private String cpfBeneficiario;

	private String titulo;

	private String mensagem;

	private Date enviarEm;

	private String periodoEnviar;

	private Long ordem;

	private Date criadoEm;

	private Date atualizadoEm;

	private String imagem;

	public static DirecionamentoFluxoDTO getDTO(DirecionamentoFluxo direcionamentoFluxo) {
		//// @formatter:off
    	return DirecionamentoFluxoDTO.builder()
        		.titulo(direcionamentoFluxo.getTitulo())
        		.periodoEnviar(direcionamentoFluxo.getPeriodoEnviar())
        		.ordem(direcionamentoFluxo.getOrdem())
        		.mensagem(direcionamentoFluxo.getMensagem())
        		.idDirecionamento(direcionamentoFluxo.getIdDirecionamento())
        		.enviarEm(direcionamentoFluxo.getEnviarEm())
        		.criadoEm(new Date(direcionamentoFluxo.getCriadoEm().getTime()))
        		.cpfBeneficiario(direcionamentoFluxo.getCpfBeneficiario())
        		.cnpjEstabelecimento(direcionamentoFluxo.getCnpjEstabelecimento())
        		.imagem(direcionamentoFluxo.getImagem()).build();        		
    	// @formatter:on
    }

}
