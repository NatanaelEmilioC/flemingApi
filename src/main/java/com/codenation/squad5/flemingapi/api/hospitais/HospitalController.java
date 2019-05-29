package com.codenation.squad5.flemingapi.api.hospitais;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.dto.BancoDeSangueDTO;
import com.codenation.squad5.flemingapi.api.hospitais.dto.HospitalDTO;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.hospitais.service.HospitalService;
import com.codenation.squad5.flemingapi.api.pacientes.dto.PacienteDTO;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
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
@RequestMapping("hospitais")
@Api(value = "hospitais", produces = MediaType.APPLICATION_JSON_VALUE)
public class HospitalController {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private ModelMapper mapper;
	private HospitalService hospitalService;
	
	@Autowired
	public HospitalController(HospitalService hospitalService) {
		this.hospitalService = hospitalService;
		this.mapper = new ModelMapper();
	}
	
	@ApiOperation(value = "busca hospitais")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar uma lista de hospitais"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.GET)
	public List<HospitalDTO> listarHospitais(){
		Type listType = new TypeToken<List<HospitalDTO>>(){}.getType();
		return mapper.map(hospitalService.listAll(), listType);
	}
	
	@ApiOperation(value = "cadastra hospital")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro efetuado com sucesso"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<HospitalDTO> salvarHospital(@RequestBody HospitalDTO hospital) {
		Hospital saved = hospitalService.save(mapper.map(hospital, Hospital.class));
		return  ResponseEntity.ok().body(mapper.map(saved, HospitalDTO.class));
	}



	@ApiOperation(value = "busca hospital mais proximo")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar o hospital mais proximo do paciente"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="/paciente/{idPaciente}/proximo",method = RequestMethod.GET)
	public ResponseEntity<HospitalDTO> buscaHospitalProximo(@PathVariable("idPaciente")String idPaciente){
		Hospital hospital = hospitalService.findHospitalNear(idPaciente);
		return  ResponseEntity.ok().body(mapper.map(hospital, HospitalDTO.class));
	}

	@ApiOperation(value = "busca paciente do hospital")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar o paciente do hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{idHospital}/paciente/{idPaciente}",method = RequestMethod.GET)
	public ResponseEntity<PacienteDTO> buscarPacienteHospital(@PathVariable("idHospital")String idHospital,
															  @PathVariable("idPaciente")String idPaciente){
		Paciente paciente = hospitalService.findPaciente(idHospital, idHospital);
		return  ResponseEntity.ok().body(mapper.map(paciente, PacienteDTO.class));
	}

	@ApiOperation(value = "listar pacientes do hospital")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "retornar pacientes do hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{idHospital}/paciente/",method = RequestMethod.GET)
	public List<PacienteDTO> listarPacienteHospital(@PathVariable("idHospital")String idHospital){
		Type listType = new TypeToken<List<PacienteDTO>>(){}.getType();
		return mapper.map(hospitalService.listAllPaciente(idHospital), listType);
	}

	@ApiOperation(value = "fazer checkin do paciente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "faz checkin do paciente no hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{idHospital}/paciente/checkin",method = RequestMethod.POST)
	public ResponseEntity<PacienteDTO> checkInPacienteHospital(@PathVariable("idHospital")String idHospital,
													@RequestBody PacienteDTO pacienteDTO){
		Paciente paciente = hospitalService.checkIn(idHospital,mapper.map(pacienteDTO, Paciente.class));
		return  ResponseEntity.ok().body(mapper.map(paciente, PacienteDTO.class));
	}

	@ApiOperation(value = "fazer checkout do paciente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "faz checkout do paciente no hospital"),
			@ApiResponse(code = 408, message = "Request timeout"),
			@ApiResponse(code = 422, message = "Validation error"),
			@ApiResponse(code = 500, message = "Internal Server Error")})
	@RequestMapping(value="{idHospital}/paciente/{idPaciente}/checkout",method = RequestMethod.DELETE)
	public Boolean checkOutPacienteHospital(@PathVariable("idHospital")String idHospital,
															   @PathVariable("idPaciente")String idPaciente){
		return  hospitalService.checkOut(idHospital, idPaciente);
	}
}
