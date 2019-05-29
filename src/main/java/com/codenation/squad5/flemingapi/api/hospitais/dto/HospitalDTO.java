package com.codenation.squad5.flemingapi.api.hospitais.dto;

import com.codenation.squad5.flemingapi.api.common.dto.EnderecoDTO;
import com.codenation.squad5.flemingapi.api.leitos.dto.LeitoDTO;
import com.codenation.squad5.flemingapi.api.pacientes.dto.PacienteDTO;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;

import java.util.ArrayList;
import java.util.List;

public class HospitalDTO {

    private String id;

    private String nome;

    private EnderecoDTO endereco;

    private List<LeitoDTO> leitos;

    private List<PacienteDTO> pacientes;

    public HospitalDTO() {
        leitos = new ArrayList<>();
        pacientes = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public EnderecoDTO getEndereco() {
        return endereco;
    }

    public void setEndereco(EnderecoDTO endereco) {
        this.endereco = endereco;
    }

    public List<LeitoDTO> getLeitos() {
        return leitos;
    }

    public void setLeitos(List<LeitoDTO> leitos) {
        this.leitos = leitos;
    }
}
