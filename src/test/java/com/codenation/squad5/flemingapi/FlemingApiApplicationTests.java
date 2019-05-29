package com.codenation.squad5.flemingapi;

import com.codenation.squad5.flemingapi.api.common.model.Endereco;
import com.codenation.squad5.flemingapi.api.hospitais.HospitalController;
import com.codenation.squad5.flemingapi.api.hospitais.dto.HospitalDTO;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import com.codenation.squad5.flemingapi.api.pacientes.PacienteController;
import com.codenation.squad5.flemingapi.api.pacientes.dto.PacienteDTO;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
import com.codenation.squad5.flemingapi.config.location.Localizacao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlemingApiApplicationTests {

    @Autowired
    private HospitalController hospitalController;
    @Autowired
    private PacienteController pacienteController;


    private PacienteDTO pacienteDto = new PacienteDTO();
    private ModelMapper modelMapper = new ModelMapper();
    private HospitalDTO hospitalDto = new HospitalDTO();
    private List<Hospital> listaDeHospitais = new ArrayList<>();
    private Paciente pacienteTest = new Paciente();

    @Before
    public void povoarBanco() {
        Paciente paciente = this.setUp();
        paciente.setEndereco(this.setUpEndereco());
        pacienteTest = paciente;
        pacienteDto = modelMapper.map(paciente, PacienteDTO.class);
        pacienteController.salvarPaciente(pacienteDto);


        Hospital hospital = this.setUpHospital();
            for (int i = 0; i < 10; i++) {
            hospital = this.setUpHospital();
            hospital.setEndereco(this.setUpEndereco());
            hospital.setLeitos(this.setUpLeitos());
            hospital.setId(String.valueOf(i));
            hospital.getEndereco().setLongitude(hospital.getEndereco().getLongitude() + i + 100);
            hospital.getEndereco().setLatitude(hospital.getEndereco().getLatitude() + i + 100);
            listaDeHospitais.add(hospital);
            hospitalDto = modelMapper.map(hospital, HospitalDTO.class);
            hospitalController.salvarHospital(hospitalDto);
        }
    }

    @Test
    public void hospitalMaisProximo() {
        Localizacao localizacao = new Localizacao();
        //System.out.println(localizacao.hospitalMaisProximo(pacienteTest,listaDeHospitais));
        assertEquals(localizacao.hospitalMaisProximo(pacienteTest,listaDeHospitais).getId(), "0");
    }

    private Paciente setUp() {
        Paciente paciente = new Paciente();
        paciente.setCpf("100.000.000-10");
        paciente.setIdade(19);
        paciente.setId("01");
        paciente.setNome("Fernando");
        paciente.setSobrenome("Machado");
        paciente.setTelefone("013912349635");
        return paciente;
    }

    public Endereco setUpEndereco() {
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

    public List<Leito> setUpLeitos() {
        List<Leito> leitos = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Leito leito = new Leito();
            leito.setNumero(i);
            leito.setOcupado(false);
            leitos.add(leito);
        }
        return leitos;
    }

    public Hospital setUpHospital() {
        Hospital hospital = new Hospital();
        hospital.setId("0");
        hospital.setNome("Santa Casa");
        return hospital;
    }
}
