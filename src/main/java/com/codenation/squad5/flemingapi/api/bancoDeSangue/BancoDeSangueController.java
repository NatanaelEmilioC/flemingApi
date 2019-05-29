package com.codenation.squad5.flemingapi.api.bancoDeSangue;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.dto.BancoDeSangueDTO;
import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;
import com.codenation.squad5.flemingapi.api.bancoDeSangue.service.BancoDeSangueService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.util.List;

@RestController
@RequestMapping("hospitais/{idHospital}/estoque/bancoDeSangue/")
@Api(value = "bancoDeSangue", produces = MediaType.APPLICATION_JSON_VALUE)
public class BancoDeSangueController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ModelMapper mapper;
	private BancoDeSangueService bancoDeSangueService;

	@Autowired
	public BancoDeSangueController(BancoDeSangueService bancoDeSangueService) {
		this.bancoDeSangueService = bancoDeSangueService;
		this.mapper = new ModelMapper();
	}
	
	@ApiOperation(value = "lista banco de sangue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar uma lista de banco de sangue no estoque do hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.GET)
	public List<BancoDeSangueDTO> listarBancoDeSangue(@PathVariable("idHospital")String idHospital){
		Type listType = new TypeToken<List<BancoDeSangueDTO>>(){}.getType();
		return mapper.map(bancoDeSangueService.listAll(idHospital), listType);
	}
	
	@ApiOperation(value = "cadastra banco de sangue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<BancoDeSangueDTO> salvarBancoDeSangue(@PathVariable("idHospital")String idHospital,
														   @RequestBody BancoDeSangueDTO bancoDeSangueDTO) {
		BancoDeSangue saved = bancoDeSangueService.save(idHospital,mapper.map(bancoDeSangueDTO, BancoDeSangue.class));
		return  ResponseEntity.ok().body(mapper.map(saved, BancoDeSangueDTO.class));
	}

	@ApiOperation(value = "buscar banco de sangue")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<BancoDeSangueDTO> buscarBancoDeSangue(@PathVariable("idHospital")String idHospital,
																@PathVariable("id")String id) {
		BancoDeSangue bancoDeSangue = bancoDeSangueService.get(idHospital, id);
		return  ResponseEntity.ok().body(mapper.map(bancoDeSangue, BancoDeSangueDTO.class));
	}

}
