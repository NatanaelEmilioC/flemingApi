package com.codenation.squad5.flemingapi.api.pacientes.model;

import com.codenation.squad5.flemingapi.api.common.model.Endereco;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.Id;

@Document(collection = "paciente")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Paciente {
	@Id
	private String id;

	private String nome;
	
	private String sobrenome;
	
	private int idade;
	
	private String cpf;
	
	private String telefone;
	
	private Endereco endereco;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
