package com.codenation.squad5.flemingapi.api.bancoDeSangue.service;

import com.codenation.squad5.flemingapi.api.bancoDeSangue.model.BancoDeSangue;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.hospitais.repository.HospitalRepository;

import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BancoDeSangueServiceImpl implements BancoDeSangueService {

	@Autowired
	private HospitalRepository hospitalRepository;

	private static final Logger logger = LoggerFactory.getLogger(BancoDeSangueServiceImpl.class);

	@Override
	public List<BancoDeSangue> listAll(String idHospital) {
		return hospitalRepository.findById(idHospital).get().getEstoque().getBancoDeSangue();
	}
	
	@Override
	public BancoDeSangue save(String idHospital, BancoDeSangue bancoDeSangue) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();
		BancoDeSangue bds = hospital.getEstoque().getBancoDeSangue().stream()
		.filter(bdSangue -> bdSangue.getTipoSangue().equals(bancoDeSangue.getTipoSangue())).
		findFirst().get();

		ObjectId objId = new ObjectId().get();
		bancoDeSangue.setId(objId.toString());
		hospital.getEstoque().getBancoDeSangue().remove(bds);
		hospital.getEstoque().getBancoDeSangue().add(bancoDeSangue);
		hospitalRepository.save(hospital);

		return bancoDeSangue;
	}

	@Override
	public BancoDeSangue get(String idHospital, String id) {		
		return hospitalRepository.findById(idHospital).get().getEstoque().getBancoDeSangue()
				.stream().filter(bdSangue -> bdSangue.getId().equals(id)).findFirst().get();
	}


}
