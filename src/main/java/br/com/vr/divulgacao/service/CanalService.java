package br.com.vr.divulgacao.service;

import java.util.List;

import br.com.vr.divulgacao.dto.CanalDTO;
import br.com.vr.divulgacao.exception.ResourceNotFoundException;

public interface CanalService {

	List<CanalDTO> findAll();

	CanalDTO findByIdCanal(Long id) throws ResourceNotFoundException;
	
	CanalDTO create(String descricao);
	
	CanalDTO update(CanalDTO canalDto);

	void delete(CanalDTO canalDto);

}
