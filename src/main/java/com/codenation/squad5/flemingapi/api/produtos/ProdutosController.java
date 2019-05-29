package com.codenation.squad5.flemingapi.api.produtos;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.dto.BancoDeSangueDTO;
import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;
import com.codenation.squad5.flemingapi.api.bancoDeSangue.service.BancoDeSangueService;
import com.codenation.squad5.flemingapi.api.produtos.dto.ProdutosDTO;
import com.codenation.squad5.flemingapi.api.produtos.model.Produtos;
import com.codenation.squad5.flemingapi.api.produtos.service.ProdutosService;
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
@RequestMapping("hospitais/{idHospital}/estoque/produtos/")
@Api(value = "produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProdutosController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ModelMapper mapper;
	private ProdutosService produtosService;

	@Autowired
	public ProdutosController(ProdutosService produtosService) {
		this.produtosService = produtosService;
		this.mapper = new ModelMapper();
	}

	@ApiOperation(value = "listar produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar uma lista de produtos que estão no estoque do hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.GET)
	public List<ProdutosDTO> listarProdutos(@PathVariable("idHospital")String idHospital){
		Type listType = new TypeToken<List<ProdutosDTO>>(){}.getType();
		return mapper.map(produtosService.listAll(idHospital), listType);
	}

	@ApiOperation(value = "cadastra produtos")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<ProdutosDTO> salvarProdutos(@PathVariable("idHospital")String idHospital,
														   @RequestBody ProdutosDTO produtosDTO) {
		Produtos produtos = produtosService.save(idHospital,mapper.map(produtosDTO, Produtos.class));
		return  ResponseEntity.ok().body(mapper.map(produtos, ProdutosDTO.class));
	}

	@ApiOperation(value = "buscar produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Busca efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{id}",method = RequestMethod.GET)
	public ResponseEntity<ProdutosDTO> buscarProduto(@PathVariable("idHospital")String idHospital,
																@PathVariable("id")String id) {
		Produtos produtos = produtosService.get(idHospital, id);
		return  ResponseEntity.ok().body(mapper.map(produtos, ProdutosDTO.class));
	}
	@ApiOperation(value = "remover produto")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Produto removido com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{id}",method = RequestMethod.DELETE)
	public ResponseEntity<ProdutosDTO> removerProduto(@PathVariable("idHospital")String idHospital,
													 @PathVariable("id")String id) {

		Produtos produtos = produtosService.delete(idHospital,id);

		return  ResponseEntity.ok().body(mapper.map(produtos, ProdutosDTO.class));
	}

}
