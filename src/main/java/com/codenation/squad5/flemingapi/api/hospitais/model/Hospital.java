package com.codenation.squad5.flemingapi.api.hospitais.model;

import com.codenation.squad5.flemingapi.api.common.model.Endereco;
import com.codenation.squad5.flemingapi.api.estoques.model.Estoque;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

import static com.codenation.squad5.flemingapi.api.common.Predicates.not;


@Document(collection = "hospital")
public class Hospital {
	
	@Id
	private String id;
	
	private String nome;
	
	private Estoque estoque;
	
	private Endereco endereco;

	private List<Leito> leitos;

	private List<Paciente> pacientes;

	public Hospital(){
		leitos = new ArrayList<>();
		pacientes= new ArrayList<>();
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

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Leito> getLeitos() {
		return leitos;
	}

	public void setLeitos(List<Leito> leitos) {
		this.leitos = leitos;
	}

	public long totalDeLeitos() {
		return leitos.size();
	}

	public long leitosDisponiveis() {
		return leitos.stream().filter(not(Leito::isOcupado)).count();
	}

	public Estoque getEstoque() {
		return estoque;
	}
	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}


	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
}
