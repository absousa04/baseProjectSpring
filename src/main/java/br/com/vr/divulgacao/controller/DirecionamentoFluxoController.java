package br.com.vr.divulgacao.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.divulgacao.dto.DirecionamentoFluxoDTO;
import br.com.vr.divulgacao.response.ErrorResponse;
import br.com.vr.divulgacao.service.DirecionamentoFluxoService;
import br.com.vr.divulgacao.util.CanalEnum;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/direcionamento")
@CrossOrigin(origins = "*")
public class DirecionamentoFluxoController {

	@Autowired
	private DirecionamentoFluxoService direcinamentoFluxoService;

	@ApiOperation(value = "Busca todas as Divulgação de forma paginada")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pagina", value = "Numero da pagina", required = true, defaultValue = "0", paramType = "query"),
        @ApiImplicitParam(name = "tamanho", value = "Quantidade de Direcionamento no response", defaultValue = "10", required = true, paramType = "query"), })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Direcionamento encontrado", response = DirecionamentoFluxoDTO.class),
			@ApiResponse(code = 404, message = "Direcionamento não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping
	public ResponseEntity<List<DirecionamentoFluxoDTO>> busca(@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "10") Integer tamanho) {
		
		List<DirecionamentoFluxoDTO> listaDirecionamento = direcinamentoFluxoService.findAll(pagina, tamanho);
		return ResponseEntity.ok(listaDirecionamento);

	}
	
	@ApiOperation(value = "Busca todas as Divulgações por CPF de forma paginada")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cpf", value = "CPF do beneficiario da Divulgação", required = true, paramType = "path"),
			@ApiImplicitParam(name = "pagina", value = "Numero da pagina", required = true, defaultValue = "0", paramType = "query"),
	        @ApiImplicitParam(name = "tamanho", value = "Quantidade de Direcionamento no response", defaultValue = "10", required = true, paramType = "query"),})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Direcionamento encontrado", response = DirecionamentoFluxoDTO.class),
			@ApiResponse(code = 404, message = "Direcionamento não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping("/{cpf}")
	public ResponseEntity<List<DirecionamentoFluxoDTO>> buscaPorCpf(@PathVariable("cpf") String cpf, @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "10") Integer tamanho) {
		
		List<DirecionamentoFluxoDTO> listaDirecionamento = direcinamentoFluxoService.findByCpfBeneficiario(cpf, pagina, tamanho);
		return ResponseEntity.ok(listaDirecionamento);

	}
	
	@ApiOperation(value = "Obtem ultima Divulgação em ordem de envio, salvando a mesma na tabela de enviados por canal")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "cpf", value = "CPF do beneficiario da Divulgação", required = true, paramType = "path"),
			@ApiImplicitParam(name = "data", value = "Data do dia da Divulgação (yyyy-MM-dd)", required = true, paramType = "path"),
			@ApiImplicitParam(name = "canal", value = "Canal de origem da solicitação", required = true, paramType = "path"),
			@ApiImplicitParam(name = "quantidade", value = "Quantidade de Direcionamentos enviado no response", required = true, paramType = "path"), })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Direcionamento encontrado", response = DirecionamentoFluxoDTO.class),
			@ApiResponse(code = 404, message = "Direcionamento não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping("/{cpf}/{data}/{canal}/{quantidade}")
	public ResponseEntity<List<DirecionamentoFluxoDTO>> obterDivulgacaoPorCanal(@PathVariable("cpf") String cpf, 
			@PathVariable("data") Date data,
			@PathVariable("canal") CanalEnum canal, @PathVariable("quantidade") Integer quantidade) {
		List<DirecionamentoFluxoDTO> direcionamento = direcinamentoFluxoService.obterDivulgacaoCanal(cpf, data, canal, quantidade);
		return ResponseEntity.ok(direcionamento);

	}

	
	
}
