package br.com.vr.divulgacao.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import br.com.vr.divulgacao.util.Guid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "DIRECIONAMENTO_ENVIO")
public class DirecionamentoEnvio implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 424313563099673L;

	@Id
	@Column(name = "ID_DIRECIONAMENTO_ENVIO")
	private String idDirecionamentoEnvio;
	
	@ManyToOne
	@JoinColumn(name = "ID_CANAL")
	private Canal canal;
	
	@ManyToOne
	@JoinColumn(name = "ID_DIRECIONAMENTO_FLUXO")
	private DirecionamentoFluxo direcionamento;

	@Column(name = "CRIADO_EM")
	private Date criadoEm;

	@Column(name = "ATUALIZADO_EM")
	private Date atualizadoEm;

    @PrePersist
    public void prePersist() {
    	idDirecionamentoEnvio = Guid.getString();
    	criadoEm = new Date();
    }
 
    @PreUpdate
    public void preUpdate() {
    	atualizadoEm = new Date();
    }
    
}
