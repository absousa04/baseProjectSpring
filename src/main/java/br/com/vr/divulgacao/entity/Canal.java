package br.com.vr.divulgacao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "CANAL")
public class Canal implements Serializable {

	private static final long serialVersionUID = -4988428196468691086L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "canal_sequence")
	@SequenceGenerator(name = "canal_sequence", sequenceName = "CANAL_SEQ", initialValue = 0, allocationSize = 1)
	@Column(name = "ID_CANAL", updatable = false, nullable = false)
	private Long idCanal;

	@Column(name = "DESCRICAO")
	private String descricao;

}
