package com.codenation.squad5.flemingapi.api.leitos;

import com.codenation.squad5.flemingapi.api.hospitais.dto.HospitalDTO;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.codenation.squad5.flemingapi.api.estoques.dto.EstoqueDTO;
import com.codenation.squad5.flemingapi.api.estoques.model.Estoque;
import com.codenation.squad5.flemingapi.api.estoques.service.EstoqueService;
import com.codenation.squad5.flemingapi.api.leitos.dto.LeitoDTO;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import com.codenation.squad5.flemingapi.api.leitos.service.LeitoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("hospitais/{idHospital}/leitos/")
@Api(value = "leitos", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeitoController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	private ModelMapper mapper;
	private LeitoService leitoService;
	
	@Autowired
	public LeitoController(LeitoService leitoService) {
		this.leitoService = leitoService;
		this.mapper = new ModelMapper();
	}
	
	@ApiOperation(value = "buscar leito")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "verificar se o leito está ocupado"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<LeitoDTO> listarHospitais(@PathVariable("idHospital")String idHospital,
			@PathVariable("id")String idLeito){
		
		Leito leito = leitoService.find(idHospital, idLeito);
		return  ResponseEntity.ok().body(mapper.map(leito, LeitoDTO.class));
	}

	@ApiOperation(value = "buscar leito disponiveis")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "verificar se o leito está disponivel"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.GET)
	public List<LeitoDTO> listarLeitosDisponiveis(@PathVariable("idHospital")String idHospital){
		Type listType = new TypeToken<List<LeitoDTO>>(){}.getType();
		return mapper.map(leitoService.findById(idHospital), listType);
	}

	@ApiOperation(value = "cadastra leito")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<LeitoDTO> salvar(@PathVariable("idHospital")String idHospital, @RequestBody LeitoDTO leitoDTO) {
		Leito leito = leitoService.save(idHospital, mapper.map(leitoDTO, Leito.class));
		return  ResponseEntity.ok().body(mapper.map(leito, LeitoDTO.class));
	}

}
