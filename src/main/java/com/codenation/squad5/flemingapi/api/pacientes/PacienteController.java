package com.codenation.squad5.flemingapi.api.pacientes;

import com.codenation.squad5.flemingapi.api.hospitais.dto.HospitalDTO;
import com.codenation.squad5.flemingapi.api.hospitais.service.HospitalService;
import com.codenation.squad5.flemingapi.api.pacientes.dto.PacienteDTO;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
import com.codenation.squad5.flemingapi.api.pacientes.service.PacienteService;
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
@RequestMapping("paciente/")
@Api(value = "pacientes", produces = MediaType.APPLICATION_JSON_VALUE)
public class PacienteController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private ModelMapper mapper;
    private PacienteService pacienteService;

    @Autowired
    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
        this.mapper = new ModelMapper();
    }

    @ApiOperation(value = "cadastra paciente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Cadastro efetuado com sucesso"),
            @ApiResponse(code = 408, message = "Request timeout"),
            @ApiResponse(code = 422, message = "Validation error"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<PacienteDTO> salvarPaciente(@RequestBody PacienteDTO paciente) {
        Paciente saved = pacienteService.save(mapper.map(paciente, Paciente.class));
        return  ResponseEntity.ok().body(mapper.map(saved, PacienteDTO.class));
    }

    @ApiOperation(value = "buscar paciente")
    @ApiResponses(value = { @ApiResponse(code = 200, message = "retornar um paciente"),
            @ApiResponse(code = 408, message = "Request timeout"),
            @ApiResponse(code = 422, message = "Validation error"),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @RequestMapping(value="{idPaciente}", method = RequestMethod.GET)
    public ResponseEntity<PacienteDTO> buscarPacientes(@PathVariable("idPaciente")String idPaciente){
        Paciente paciente = pacienteService.get(idPaciente);
        return  ResponseEntity.ok().body(mapper.map(paciente, PacienteDTO.class));
    }
}

