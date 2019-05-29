package com.codenation.squad5.flemingapi.api.estoques;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.lang.reflect.Type;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.codenation.squad5.flemingapi.api.estoques.dto.EstoqueDTO;
import com.codenation.squad5.flemingapi.api.estoques.model.Estoque;
import com.codenation.squad5.flemingapi.api.estoques.service.EstoqueService;

@RestController
@RequestMapping("hospitais/")
@Api(value = "estoques", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstoqueController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());

	private ModelMapper mapper;
	private EstoqueService estoqueService;
	
	@Autowired
	public EstoqueController(EstoqueService estoqueService) {
		this.estoqueService = estoqueService;
		this.mapper = new ModelMapper();
	}
	
	@ApiOperation(value = "busca estoque")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar produtos e bancos de sangue dentro do estoque"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{id}/estoque",method = RequestMethod.GET)
	public ResponseEntity<EstoqueDTO> listarHospitais(@PathVariable("id")String id){
		Estoque estoque = estoqueService.find(id);
		return  ResponseEntity.ok().body(mapper.map(estoque, EstoqueDTO.class));
	}
}
