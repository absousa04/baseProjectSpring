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
@Table(name = "CARTAO")
public class Cartao implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 8401285930758510295L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private Long idCartao;
	
	@Column(name = "APLICACAO")
	private String nomeProduto;

}
