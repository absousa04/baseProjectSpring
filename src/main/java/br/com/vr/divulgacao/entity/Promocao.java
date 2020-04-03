package br.com.vr.divulgacao.entity;

import java.io.Serializable;
import java.sql.Timestamp;

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
@Table(name = "PROMOCAO")
public class Promocao implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2799108894646991275L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private String id;

	@Column(name = "IDPRODUTO")
	private Long idproduto;

	@Column(name = "IDESTABELECIMENTO")
	private String idEstabelecimento;

	@Column(name = "DATA")
	private Timestamp dataCriacao;

	@Column(name = "VALIDADE")
	private Timestamp validade;

	@Column(name = "ICONE")
	private Long icone;
	
	@Column(name = "TITULO")
	private String titulo;
	
	@Column(name = "SUBTITULO")
	private String subtitulo;
	
	@Column(name = "MENSAGEM")
	private String mensagem;
	
	@Column(name = "DETALHE")
	private String DETALHE;
	
	@Column(name = "LINK")
	private String LINK;
	
	@Column(name = "ORDEM")
	private Long ORDEM;
	
	@Column(name = "DESTAQUE")
	private String destaque;
	
	@Column(name = "TEXTOLINK")
	private String textoLink;
	
	@Column(name = "DATAINATIVACAO")
	private Timestamp dataInativacao;
	
	@Column(name = "TIPOPROMOCAO")
	private String tiPopromocao;
	
	@Column(name = "ITEMPROMOCIONAL")
	private String itemPromocional;
	
	@Column(name = "DURACAOPROMOCAO")
	private Long duracaopromocao;
	
	@Column(name = "CONSUMOMINIMO")
	private String consumoMinimo;
	
	@Column(name = "NOMEIMAGEM")
	private String nomeImagem;

}
