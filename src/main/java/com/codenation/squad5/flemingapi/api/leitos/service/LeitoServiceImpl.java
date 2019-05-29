package com.codenation.squad5.flemingapi.api.leitos.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.hospitais.repository.HospitalRepository;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import org.springframework.stereotype.Service;

@Service
public class LeitoServiceImpl implements LeitoService {
	
	@Autowired
	private HospitalRepository hospitalRepository;

	@Override
	public Leito find(String idHospita, String idLeito) {
		return hospitalRepository.findById(idHospita).get().getLeitos()
				.stream().filter(leito -> leito.equals(idLeito)).findFirst().get();
	}

	@Override
	public List<Leito> findById(String idHospital) {
		return hospitalRepository.findById(idHospital).get().getLeitos().
				stream().filter(leito -> !leito.isOcupado()).collect(Collectors.toList());
	}

	@Override
	public Leito save(String idHospita, Leito leito) {
		Hospital hospital = hospitalRepository.findById(idHospita).get();
		Optional<Leito> leitoHospital = hospital.getLeitos().stream().
										filter(l -> l.getNumero() == leito.getNumero()).findFirst();
		
		if(leitoHospital.isPresent()) {
			hospital.getLeitos().remove(leito);
		}

		hospital.getLeitos().add(leito);
		hospitalRepository.save(hospital);
		return leito;
	}

	@Override
	public List<Leito> findAll(String idHospita) {
		return hospitalRepository.findById(idHospita).get().getLeitos();
	}
}
