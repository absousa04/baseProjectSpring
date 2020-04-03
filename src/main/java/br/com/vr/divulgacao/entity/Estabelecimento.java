package br.com.vr.divulgacao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ESTABELECIMENTO")
public class Estabelecimento implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -5034712414870199479L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String idEstabelecimento;

	@Column(name = "CODIGO")
	private String codigo;

	@Column(name = "RAZAOSOCIAL")
	private String razaoSocial;

	@Column(name = "NOMEFANTASIA")
	private String nomeFantasia;

	@Column(name = "CNPJ")
	private String cnpj;
	
	@Column(name = "IDSNCORE")
	private String idSncore;

}
