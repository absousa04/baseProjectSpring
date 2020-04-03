package br.com.vr.divulgacao.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ValidationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.vr.divulgacao.dto.DirecionamentoFluxoDTO;
import br.com.vr.divulgacao.entity.Canal;
import br.com.vr.divulgacao.entity.DirecionamentoEnvio;
import br.com.vr.divulgacao.entity.DirecionamentoFluxo;
import br.com.vr.divulgacao.repository.DirecionamentoFluxoRepository;
import br.com.vr.divulgacao.service.DirecionamentoFluxoService;
import br.com.vr.divulgacao.util.CanalEnum;
import br.com.vr.divulgacao.util.ValidarCPF;

@Service
public class DirecionamentoFluxoServiceImpl implements DirecionamentoFluxoService {

	@Autowired
	private DirecionamentoFluxoRepository direcionamentoFluxoRepository;

	@Autowired
	private DirecionamentoEnvioServiceImpl envioServiceImpl;

	@Override
	public DirecionamentoFluxoDTO findByEnviarEmAndcpfBeneficiario(Date enviarEm, String cpf) {
		return null;

	}

	@Override
	public List<DirecionamentoFluxoDTO> findAll(Integer pagina, Integer tamanho) {

		PageRequest pageable = PageRequest.of(pagina, tamanho, Sort.by(Sort.Direction.DESC, "idDirecionamento"));

		Page<DirecionamentoFluxo> pageResult = direcionamentoFluxoRepository.findAll(pageable);

		List<DirecionamentoFluxoDTO> listaDirecionamentoDto = pageResult.stream().map(DirecionamentoFluxoDTO::getDTO)
				.collect(Collectors.toList());

		return listaDirecionamentoDto;
	}

	@Override
	public List<DirecionamentoFluxoDTO> findByCpfBeneficiario(String cpf, Integer pagina, Integer tamanho) {
		if (!ValidarCPF.isCPF(cpf)) {

			throw new ValidationException("CPF invalido");
		}

		Pageable pageable = PageRequest.of(pagina, tamanho, Sort.by(Sort.Direction.DESC, "idDirecionamento"));

		Page<DirecionamentoFluxo> pageResult = direcionamentoFluxoRepository.findByCpfBeneficiario(cpf, pageable);

		List<DirecionamentoFluxoDTO> listaDirecionamentoDto = pageResult.stream().map(DirecionamentoFluxoDTO::getDTO)
				.collect(Collectors.toList());

		return listaDirecionamentoDto;
	}

	@Override
	public List<DirecionamentoFluxoDTO> obterDivulgacaoCanal(String cpf, Date data, CanalEnum canal,
			Integer quantidade) {

		if (!ValidarCPF.isCPF(cpf)) {

			throw new ValidationException("CPF invalido");
		}

		List<DirecionamentoFluxo> listaDirecionamento = direcionamentoFluxoRepository
				.findAllByCpfBeneficiarioAndEnviarEmOrderByOrdemAsc(cpf, data);

		List<DirecionamentoFluxo> listaFiltrada = new ArrayList<DirecionamentoFluxo>();

		switch (canal) {
		case WHATSAPP:
			listaFiltrada = getListaWhatsapp(listaDirecionamento, quantidade, CanalEnum.WHATSAPP.getId());
		case CARROCEL:
			// code
		case API_PROMOÇÃO:
			// code
		}

		List<DirecionamentoFluxoDTO> listaDirecionamentoFluxoDTO = listaFiltrada.stream()
				.map(DirecionamentoFluxoDTO::getDTO).collect(Collectors.toList());

		return listaDirecionamentoFluxoDTO;

	}

	private List<DirecionamentoFluxo> getListaWhatsapp(List<DirecionamentoFluxo> listaDirecionamento,
			Integer quantidade, Long idCanal) {

		int count = 0;
		List<DirecionamentoFluxo> listaFiltrada = new ArrayList<DirecionamentoFluxo>(); 
			for(DirecionamentoFluxo item : listaDirecionamento) {
				if (!envioServiceImpl.existsByDirecionamentoIdDirecionamentoAndCanalIdCanal(item.getIdDirecionamento(),idCanal)) {
					count++;
					listaFiltrada.add(item);
					envioServiceImpl.createOrUpdate(DirecionamentoEnvio.builder()
							.canal(Canal.builder().idCanal(idCanal).build())
							.direcionamento(DirecionamentoFluxo.builder().idDirecionamento(item.getIdDirecionamento()).build())
							.build());
				}
				if(count==quantidade) {
					break;
				}
			}
		
		return listaFiltrada;
	}

}
