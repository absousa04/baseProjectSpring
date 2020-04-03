package br.com.vr.divulgacao.dto;

import java.io.Serializable;

import br.com.vr.divulgacao.entity.Canal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CanalDTO implements Serializable{

	private static final long serialVersionUID = -7594567105410693135L;

	private Long idCanal;
	
	private String descricao;
	
    public static CanalDTO getDTO(Canal canal){
        return CanalDTO.builder()
        		.idCanal(canal.getIdCanal())
        		.descricao(canal.getDescricao())
        	.build();        		
    }
    
    public Canal toEntity(){
        return Canal.builder()
        		.idCanal(idCanal)
        		.descricao(descricao)
        	.build();        		
    }

    
}
