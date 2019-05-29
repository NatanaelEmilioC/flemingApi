package com.codenation.squad5.flemingapi.api.commons.service;

import com.codenation.squad5.flemingapi.api.common.dto.EnderecoDTO;
import com.codenation.squad5.flemingapi.api.common.model.Endereco;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CommonServiceImplTest {
/*
    @Autowired
    private EnderecoController enderecoController;

    private EnderecoDTO enderecoDTO = new EnderecoDTO();
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldMapEnderecoToDto(){
        Endereco endereco = this.setUp();
        enderecoDTO = modelMapper.map(endereco, EnderecoDTO.class);
        assertEquals( enderecoDTO, endereco);
    }

    @Test
    public void naoNulo(){
        assertNotNull(enderecoDTO);
    }

    @Test
    public void deveSalvar(){
        enderecoController.salvarEndereco(enderecoDTO);
        assertFalse(enderecoController.listarEnderecos().contains(enderecoDTO));
    }

    private Endereco setUp(){
        Endereco endereco = new Endereco();
        endereco.setBairro("Centro");
        endereco.setCep("35.960-000");
        endereco.setCidade("Santa Barbara");
        endereco.setComplemento("casa");
        endereco.setEstado("Minas Gerais");
        endereco.setNumero("75");
        endereco.setRua("Pio XII");
        endereco.setLatitude("-19.96366832");
        endereco.setLongitude("-43.409729");
        return endereco;
    }*/

}
