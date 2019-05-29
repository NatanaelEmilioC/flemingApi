package com.codenation.squad5.flemingapi.api.pacientes.service;

import com.codenation.squad5.flemingapi.api.common.model.Endereco;
import com.codenation.squad5.flemingapi.api.pacientes.PacienteController;
import com.codenation.squad5.flemingapi.api.pacientes.dto.PacienteDTO;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PacienteServiceImplTest {
    @Autowired
    private PacienteController pacienteController;


    private PacienteDTO pacienteDto = new PacienteDTO();
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldMapPacienteToDto(){
        Paciente paciente  = this.setUp();
        paciente.setEndereco(this.setUpEndereco());

        pacienteDto = modelMapper.map(paciente, PacienteDTO.class);
        assertEquals( pacienteDto.getId(), "01");
    }

    @Test
    public void naoNulo(){
        assertNotNull(pacienteDto);
    }

   /* @Test
    public void deveSalvar(){
        Paciente paciente  = this.setUp();
        paciente.setEndereco(this.setUpEndereco());
        pacienteDto = modelMapper.map(paciente, PacienteDTO.class);
        pacienteController.salvarPaciente(pacienteDto);
        assertFalse(pacienteController.listarPacientes().contains(pacienteDto));
    }*/

    private Paciente setUp(){
        Paciente paciente = new Paciente();
        paciente.setCpf("100.000.000-10");
        paciente.setIdade(19);
        paciente.setId("01");
        paciente.setNome("Fernando");
        paciente.setSobrenome("Machado");
        paciente.setTelefone("013912349635");
        return paciente;
    }

    private Endereco setUpEndereco(){
        Endereco endereco = new Endereco();
        endereco.setBairro("Centro");
        endereco.setCep("35.960-000");
        endereco.setCidade("Santa Barbara");
        endereco.setComplemento("casa");
        endereco.setEstado("Minas Gerais");
        endereco.setNumero("75");
        endereco.setRua("Pio XII");
        endereco.setLatitude(-19.96366832);
        endereco.setLongitude(-43.409729);
        return endereco;
    }

}