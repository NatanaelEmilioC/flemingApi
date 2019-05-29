package com.codenation.squad5.flemingapi.api.produtos.service;

import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.produtos.model.Produtos;

import java.util.List;


public interface ProdutosService {

	List<Produtos> listAll(String idHospital);

	Produtos save(String idHospital,Produtos p);
	
	Produtos get(String idHospital,String id);

	Produtos delete(String idHospital, String id);
	
	Hospital update(String id, Produtos p);
}
