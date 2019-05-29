package com.codenation.squad5.flemingapi.api.estoques.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.codenation.squad5.flemingapi.api.estoques.model.Estoque;
import com.codenation.squad5.flemingapi.api.hospitais.repository.HospitalRepository;
import org.springframework.stereotype.Service;

@Service
public class EstoqueServiceImpl implements EstoqueService {
	
	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public Estoque find(String idHospital) {
		return hospitalRepository.findById(idHospital).get().getEstoque();
	}
}
