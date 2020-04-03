package br.com.vr.divulgacao.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CanalEnum {

	WHATSAPP("WHATSAPP", 1L), 
	CARROCEL("CARROCEL", 2L), 
	API_PROMOÇÃO("API_PROMOÇÃO", 3L);

	private String description;
	private Long id;
}
