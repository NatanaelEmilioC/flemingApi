package com.codenation.squad5.flemingapi.api.hospitais.service;

import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.hospitais.repository.HospitalRepository;
import com.codenation.squad5.flemingapi.api.leitos.model.Leito;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;
import com.codenation.squad5.flemingapi.api.pacientes.repository.PacienteRepository;
import com.codenation.squad5.flemingapi.config.location.Localizacao;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalServiceImpl implements HospitalService {

	@Autowired
	private HospitalRepository hospitalRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	private static final Logger logger = LoggerFactory.getLogger(HospitalServiceImpl.class);

	@Override
	public List<Hospital> listAll() {
		return hospitalRepository.findAll();
	}
	
	@Override
	public Hospital save(Hospital hospital) {
		hospitalRepository.save(hospital);

		return hospital;
	}

	@Override
	public Hospital findHospitalNear(String idPaciente) {
		Paciente paciente = pacienteRepository.findById(idPaciente).get();
		Localizacao localizacao = new Localizacao();

		return localizacao.hospitalMaisProximo(paciente, hospitalRepository.findAll());
	}

	@Override
	public Paciente findPaciente(String idHospital, String idPaciente) {
		return hospitalRepository.findById(idHospital).get().getPacientes().stream().
				filter(paciente -> paciente.getId().equals(idPaciente)).findFirst().get();
	}

	@Override
	public List<Paciente> listAllPaciente(String idHospital) {
		return hospitalRepository.findById(idHospital).get().getPacientes();
	}

	@Override
	public Paciente checkIn(String idHospital, Paciente paciente) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();
		Optional<Leito> leitoDisponivel = hospital.getLeitos().stream().
				filter(leito -> !leito.isOcupado()).findFirst();
		if(leitoDisponivel.isPresent()){

			hospital.getLeitos().remove(leitoDisponivel.get());
			paciente = pacienteRepository.save(paciente);
			leitoDisponivel.get().setOcupado(true);
			leitoDisponivel.get().setIdPaciente(paciente.getId());
			hospital.getLeitos().add(leitoDisponivel.get());
			hospital.getPacientes().add(paciente);
			hospitalRepository.save(hospital);
			return paciente;
		}
		return null;
	}

	@Override
	public Boolean checkOut(String idHospital, String idPaciente) {
		Hospital hospital = hospitalRepository.findById(idHospital).get();
		Optional<Leito> leitoDisponivel = hospital.getLeitos().stream().
				filter(leito -> idPaciente.equals(leito.getIdPaciente())).findFirst();
		if(leitoDisponivel.isPresent()){

			hospital.getLeitos().remove(leitoDisponivel.get());
			leitoDisponivel.get().setOcupado(false);
			leitoDisponivel.get().setIdPaciente(null);

			hospital.getLeitos().add(leitoDisponivel.get());
			Paciente paciente = hospital.getPacientes().stream().
								filter(p -> p.getId().equals(idPaciente)).findFirst().get();

			hospital.getPacientes().remove(paciente);
			hospitalRepository.save(hospital);
			pacienteRepository.deleteById(idPaciente);
			return true;
		}
		return false;
	}

}
