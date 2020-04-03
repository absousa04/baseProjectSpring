package br.com.vr.divulgacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.vr.divulgacao.dto.DirecionamentoEnvioDTO;
import br.com.vr.divulgacao.dto.DirecionamentoFluxoDTO;
import br.com.vr.divulgacao.response.ErrorResponse;
import br.com.vr.divulgacao.service.DirecionamentoEnvioService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/direcionamento-enviado")
@CrossOrigin(origins = "*")
public class DirecionamentoEnvioController {

	@Autowired
	private DirecionamentoEnvioService direcionamentoEnvioService;

	@ApiOperation(value = "Busca todas as Divulgação Enviadas de forma paginada")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "pagina", value = "Numero da pagina", required = true, defaultValue = "0", paramType = "query"),
        @ApiImplicitParam(name = "tamanho", value = "Quantidade de Direcionamento no response", defaultValue = "10", required = true, paramType = "query"), })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Direcionamento encontrado", response = DirecionamentoFluxoDTO.class),
			@ApiResponse(code = 404, message = "Direcionamento não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping
	public ResponseEntity<List<DirecionamentoEnvioDTO>> busca(@RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "10") Integer tamanho) {
		
		List<DirecionamentoEnvioDTO> listaDirecionamento = direcionamentoEnvioService.findAll(pagina, tamanho);
		return ResponseEntity.ok(listaDirecionamento);

	}
	
	@ApiOperation(value = "Busca todas as Divulgação Enviadas pelo Direcionamento de fluxo")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "idDirecionamentoFluxo", value = "CPF do beneficiario da Divulgação", required = true, paramType = "path"),
			@ApiImplicitParam(name = "pagina", value = "Numero da pagina", required = true, defaultValue = "0", paramType = "query"),
	        @ApiImplicitParam(name = "tamanho", value = "Quantidade de Direcionamento no response", defaultValue = "10", required = true, paramType = "query"),})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Direcionamento encontrado", response = DirecionamentoFluxoDTO.class),
			@ApiResponse(code = 404, message = "Direcionamento não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping("/{idDirecionamentoFluxo}")
	public ResponseEntity<List<DirecionamentoEnvioDTO>> buscaPorIdDirecionamentoFluxo(@PathVariable("idDirecionamentoFluxo") String idDirecionamentoFluxo, @RequestParam(name = "pagina", defaultValue = "0") Integer pagina,
            @RequestParam(name = "tamanho", defaultValue = "10") Integer tamanho) {
		
		List<DirecionamentoEnvioDTO> listaDirecionamento = direcionamentoEnvioService.findByIdDirecionamentoFluxo(idDirecionamentoFluxo, pagina, tamanho);
		return ResponseEntity.ok(listaDirecionamento);

	}

}
