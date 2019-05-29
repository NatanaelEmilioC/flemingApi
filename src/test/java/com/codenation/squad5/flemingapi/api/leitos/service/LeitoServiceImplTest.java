package com.codenation.squad5.flemingapi.api.leitos.service;

import com.codenation.squad5.flemingapi.api.leitos.LeitoController;
import com.codenation.squad5.flemingapi.api.leitos.dto.LeitoDTO;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LeitoServiceImplTest {
    /*@Autowired
    private LeitoController leitoController;

    private LeitoDTO leitoDTO = new LeitoDTO();
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void shouldMapLeitoToDto(){
        Leito leito = this.setUp();
        leitoDTO = modelMapper.map(leito, LeitoDTO.class);
        assertEquals( leitoDTO, leito);
    }

    @Test
    public void naoNulo(){
        assertNotNull(leitoDTO);
    }

    @Test
    public void deveSalvar(){
        leitoController.salvarLeito(leitoDTO);
        assertFalse(leitoController.listarLeitos().contains(leitoDTO));
    }

    private Leito setUp() {
        Leito leito = new Leito();
        leito.setNumero(1);
        leito.setOcupado(false);
        return leito;
    }

*/
}
