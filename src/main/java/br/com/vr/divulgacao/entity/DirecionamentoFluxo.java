package br.com.vr.divulgacao.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "DIRECIONAMENTO_FLUXO")
public class DirecionamentoFluxo implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 3370688701156078583L;

	@Id
	@GeneratedValue
	@Column(name = "ID_DIRECIONAMENTO_FLUXO")
	private String idDirecionamento;

	@ManyToOne
	@JoinColumn(name = "ID_PROMOCAO")
	private Promocao promocao;

	@ManyToOne
	@JoinColumn(name = "ID_PRODUTO")
	private Cartao idProduto;

	@Column(name = "CNPJ_ESTABELECIMENTO")
	private String cnpjEstabelecimento;

	@Column(name = "CPF_BENEFICIARIO")
	private String cpfBeneficiario;

	@Column(name = "TITULO")
	private String titulo;

	@Column(name = "MENSAGEM")
	private String mensagem;

	@Column(name = "ENVIAR_EM")
	private Date enviarEm;

	@Column(name = "PERIODO_ENVIAR")
	private String periodoEnviar;

	@Column(name = "ORDEM")
	private Long ordem;

	@Column(name = "CRIADO_EM")
	private Timestamp  criadoEm;

	@Column(name = "ATUALIZADO_EM")
	private Timestamp atualizadoEm;
	
	@Column(name = "IMAGEM")
	private String imagem;
	
	@OneToMany(mappedBy = "direcionamento")
    private List<DirecionamentoEnvio> direcionamentoEnvio;

}
