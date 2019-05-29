package com.codenation.squad5.flemingapi.api.produtos.service;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.hospitais.repository.HospitalRepository;
import com.codenation.squad5.flemingapi.api.produtos.model.Produtos;
import com.codenation.squad5.flemingapi.api.produtos.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutosServiceImpl implements ProdutosService {

	@Autowired
	private HospitalRepository hospitalRepository ;

	private static final Logger logger = LoggerFactory.getLogger(ProdutosServiceImpl.class);

	
	@Override
	public List<Produtos> listAll(String idHospital) {
		return hospitalRepository.findById(idHospital).get().getEstoque().getProdutos();
	}

	@Override
	public Produtos save(String idHospital, Produtos produto) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();

		ObjectId objId = new ObjectId().get();
		produto.setId(objId.toString());
		hospital.getEstoque().getProdutos().add(produto);
		hospitalRepository.save(hospital);

		return produto;
	}

	@Override
	public Produtos get(String idHospital, String id) {
		return  hospitalRepository.findById(idHospital).get().getEstoque().getProdutos().stream()
				.filter(prod -> prod.getId().equals(id)).
				findFirst().get();
	}

	@Override
	public Produtos delete(String idHospital, String id) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();
		Produtos p = hospital.getEstoque().getProdutos().stream()
		.filter(prod -> prod.getId().equals(id)).
		findFirst().get();	
		
		hospital.getEstoque().getProdutos().remove(p);
		hospitalRepository.save(hospital);
		return p;
	}

	@Override
	public Hospital update(String idHospital, Produtos produto) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();
		Produtos p = hospital.getEstoque().getProdutos().stream()
		.filter(prod -> prod.getId().equals(produto.getId())).
		findFirst().get();
		
		hospital.getEstoque().getProdutos().remove(p);
		hospital.getEstoque().getProdutos().add(produto);
		
		return hospitalRepository.save(hospital);
	}




}
