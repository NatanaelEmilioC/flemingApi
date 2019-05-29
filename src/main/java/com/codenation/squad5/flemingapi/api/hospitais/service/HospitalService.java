package com.codenation.squad5.flemingapi.api.hospitais.service;

import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;

import java.util.List;


public interface HospitalService {

	List<Hospital> listAll();
	
	Hospital save(Hospital hospital);

	Hospital findHospitalNear(String idPaciente);

	Paciente findPaciente(String idHospital, String idPaciente);

	List<Paciente> listAllPaciente(String idHospital);

	Paciente checkIn(String idHospital, Paciente paciente);

	Boolean checkOut(String idHospital, String idPaciente);
}
