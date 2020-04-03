package br.com.vr.divulgacao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.vr.divulgacao.dto.CanalDTO;
import br.com.vr.divulgacao.entity.Canal;
import br.com.vr.divulgacao.exception.ResourceNotFoundException;
import br.com.vr.divulgacao.repository.CanalRepository;
import br.com.vr.divulgacao.service.CanalService;

@Service
public class CanalServiceImpl implements CanalService {

	@Autowired
	private CanalRepository canalRepository;

	@Override
	public List<CanalDTO> findAll() throws ResourceNotFoundException {
		try {
			List<Canal> listaCanal = this.canalRepository.findAll();
			List<CanalDTO> listaCanalDTO = listaCanal.stream().map(CanalDTO::getDTO).collect(Collectors.toList());

			return listaCanalDTO;
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public CanalDTO findByIdCanal(Long id) {
		try {
			Canal canal = this.canalRepository.findByIdCanal(id);
			return CanalDTO.getDTO(canal);
		} catch (Exception e) {
			throw new ResourceNotFoundException();
		}
	}

	@Override
	public CanalDTO create(String descricao) {
		
		Canal canal = this.canalRepository.save(new CanalDTO(null, descricao).toEntity());

		return CanalDTO.getDTO(canal);
	}

	@Override
	public CanalDTO update(CanalDTO canalDto) {
			boolean isExist = this.canalRepository.existsByIdCanal(canalDto.getIdCanal());

			if (!isExist) {
				throw new ResourceNotFoundException("Canal não encontrado");
			}

			Canal canal = canalDto.toEntity();
			this.canalRepository.save(canal);
			return CanalDTO.getDTO(canal);

	}
	
	@Override
	public void delete(CanalDTO canalDto) {
			boolean isExist = this.canalRepository.existsByIdCanal(canalDto.getIdCanal());

			if (!isExist) {
				throw new ResourceNotFoundException("Canal não encontrado");
			}

			Canal canal = canalDto.toEntity();
			this.canalRepository.delete(canal);
	}

}
