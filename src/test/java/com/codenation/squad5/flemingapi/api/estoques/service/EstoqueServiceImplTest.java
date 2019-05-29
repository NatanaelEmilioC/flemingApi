package com.codenation.squad5.flemingapi.api.estoques.service;

import com.codenation.squad5.flemingapi.api.estoques.EstoqueController;
import com.codenation.squad5.flemingapi.api.estoques.dto.EstoqueDTO;
import com.codenation.squad5.flemingapi.api.estoques.model.Estoque;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EstoqueServiceImplTest {
/*
    @Autowired
    private EstoqueController estoqueController;

    private EstoqueDTO estoqueDTO = new EstoqueDTO();
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldMapEstoqueToDto(){
        Estoque estoque = this.setUp();
        estoqueDTO = modelMapper.map(estoque, EstoqueDTO.class);

        assertEquals(estoqueDTO, estoque);
    }

    @Test
    public void naoNulo(){
        assertNotNull(estoqueDTO);
    }

    @Test
    public void deveSalvar(){
        estoqueController.salvarEstoque(estoqueDTO);
        assertFalse(estoqueController.listarEstoques().contains(estoqueDTO));
    }

    public Estoque setUp(){
        Estoque estoque = new Estoque();
        estoque.setId();
        return estoque;
    }*/
}
