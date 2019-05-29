package com.codenation.squad5.flemingapi.api.leitos.service;
import java.util.List;

import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;

public interface LeitoService {
	
	List<Leito> findAll(String id);
	
	Leito save(String idHospita,Leito leito);

	Leito find(String idHospita, String idLeito);

	List<Leito> findById(String idHospital);
}
