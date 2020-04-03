package br.com.vr.divulgacao.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.vr.divulgacao.dto.DirecionamentoEnvioDTO;
import br.com.vr.divulgacao.entity.DirecionamentoEnvio;
import br.com.vr.divulgacao.repository.DirecionamentoEnvioRepository;
import br.com.vr.divulgacao.service.DirecionamentoEnvioService;

@Service
public class DirecionamentoEnvioServiceImpl implements DirecionamentoEnvioService {

	@Autowired
	private DirecionamentoEnvioRepository direcionamentoEnvioRepository;

		@Override
	public void createOrUpdate(DirecionamentoEnvio direcionamentoEnvio) {
		 this.direcionamentoEnvioRepository.save(direcionamentoEnvio);
	}

	@Override
	public void findByIdDirecionamentoEnvio(String id) {
		this.direcionamentoEnvioRepository.findById(id).get();
	}
	
	@Override
	public boolean existsByDirecionamentoIdDirecionamentoAndCanalIdCanal(String idDirecionamento, Long idCanal) {
		return this.direcionamentoEnvioRepository.existsByDirecionamentoIdDirecionamentoAndCanalIdCanal(idDirecionamento, idCanal);
	}

	@Override
	public List<DirecionamentoEnvioDTO> findAll(Integer pagina, Integer tamanho) {
		PageRequest pageable = PageRequest.of(pagina, tamanho, Sort.by(Sort.Direction.DESC, "direcionamento"));

		Page<DirecionamentoEnvio> pageResult = direcionamentoEnvioRepository.findAll(pageable);

		List<DirecionamentoEnvioDTO> listaDirecionamentoDto = pageResult.stream().map(DirecionamentoEnvioDTO::getDTO)
				.collect(Collectors.toList());

		return listaDirecionamentoDto;
	}

	@Override
	public List<DirecionamentoEnvioDTO> findByIdDirecionamentoFluxo(String idDirecionamentoFluxo, Integer pagina,
			Integer tamanho) {

		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(Sort.Direction.DESC, "idDirecionamentoEnvio"));

		Page<DirecionamentoEnvio> pageResult = direcionamentoEnvioRepository.findByDirecionamentoIdDirecionamento(idDirecionamentoFluxo, pageable);

		List<DirecionamentoEnvioDTO> listaDirecionamentoDto = pageResult.stream().map(DirecionamentoEnvioDTO::getDTO)
				.collect(Collectors.toList());

		return listaDirecionamentoDto;
	}

}
