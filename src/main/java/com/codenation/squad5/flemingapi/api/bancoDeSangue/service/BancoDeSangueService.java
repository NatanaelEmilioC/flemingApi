package com.codenation.squad5.flemingapi.api.bancoDeSangue.service;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;

import java.util.List;


public interface BancoDeSangueService {

	List<BancoDeSangue> listAll(String idHospital);

	BancoDeSangue save(String idHospital, BancoDeSangue bancoDeSangue);
	
	BancoDeSangue get(String id, String idHospital);
}
