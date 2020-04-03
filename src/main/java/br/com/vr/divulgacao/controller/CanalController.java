package br.com.vr.divulgacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.vr.divulgacao.dto.CanalDTO;
import br.com.vr.divulgacao.response.ErrorResponse;
import br.com.vr.divulgacao.service.CanalService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/canal")
@CrossOrigin(origins = "*")
public class CanalController {

	@Autowired
	private CanalService canalService;

	@ApiOperation(value = "Busca Todos os Canais")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Canal encontrado", response = CanalDTO.class),
			@ApiResponse(code = 404, message = "Canal não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping
	public ResponseEntity<List<CanalDTO>> findAll() {
		List<CanalDTO> ListaCanalDto = this.canalService.findAll();
		return ResponseEntity.ok(ListaCanalDto);
	}

	@ApiOperation(value = "Busca Canal por ID")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "Identificador único do Canal", required = true, paramType = "path"), })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Canal encontrado", response = CanalDTO.class),
			@ApiResponse(code = 404, message = "Canal não encontrado", response = ErrorResponse.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@GetMapping("/{id}")
	public ResponseEntity<CanalDTO> findById(@PathVariable("id") Long id) {
		CanalDTO canalDto = this.canalService.findByIdCanal(id);
		return ResponseEntity.ok(canalDto);
	}

	@ApiOperation(value = "Cadastra Um Canal")
	@ApiResponses(value = { 
			@ApiResponse(code = 201, message = "Canal Creado", response = CanalDTO.class),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@PostMapping
	public ResponseEntity<CanalDTO> create(@RequestBody String descricao, UriComponentsBuilder builder) {

		CanalDTO canalDtoResponse = this.canalService.create(descricao);

		UriComponents uriComponents = builder.path("/{id}").buildAndExpand(canalDtoResponse.getIdCanal());

		return ResponseEntity.created(uriComponents.toUri()).body(canalDtoResponse);
	}

	@ApiOperation(value = "Altera um Canal existente")
	@ApiResponses(value = { 
			@ApiResponse(code = 204, message = "Canal Alterado"),
			@ApiResponse(code = 400, message = "The request has an error", response = ErrorResponse.class),
			@ApiResponse(code = 500, message = "Unexpected error in server", response = ErrorResponse.class) })
	@PutMapping
	public ResponseEntity<CanalDTO>  update(@RequestBody CanalDTO canalDTO, UriComponentsBuilder builder) {
		CanalDTO canalDto = this.canalService.update(canalDTO);


		UriComponents uriComponents = builder.path("/{id}").buildAndExpand(canalDto.getIdCanal());

		return ResponseEntity.created(uriComponents.toUri()).body(canalDto);
	}

}
