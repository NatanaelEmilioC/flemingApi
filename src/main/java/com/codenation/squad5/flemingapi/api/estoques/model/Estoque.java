package com.codenation.squad5.flemingapi.api.estoques.model;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

import com.codenation.squad5.flemingapi.api.produtos.model.Produtos;
import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;


@Document(collection = "estoque")
public class Estoque {
	
	private List<BancoDeSangue> bancoDeSangue;
	
	private List<Produtos> produtos;

	public List<Produtos> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produtos> produtos) {
		this.produtos = produtos;
	}

	public List<BancoDeSangue> getBancoDeSangue() {
		return bancoDeSangue;
	}

	public void setBancoDeSangue(List<BancoDeSangue> bancoDeSangue) {
		this.bancoDeSangue = bancoDeSangue;
	}
}
