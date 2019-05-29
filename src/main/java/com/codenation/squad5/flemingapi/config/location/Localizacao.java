package com.codenation.squad5.flemingapi.config.location;
import com.codenation.squad5.flemingapi.api.hospitais.model.Hospital;
import com.codenation.squad5.flemingapi.api.pacientes.model.Paciente;

import java.lang.*;
import java.util.List;

public class Localizacao {

    public Localizacao() {
    }

    private double getDistancia(double primeiraLatitude, double primeiraLongitude, double seguandaLatitude, double segundaLongitude) {
        double dist = Math.sin(degToRad(primeiraLatitude)) * Math.sin(degToRad(seguandaLatitude)) +
                Math.cos(degToRad(primeiraLatitude)) * Math.cos(degToRad(seguandaLatitude)) * Math.cos(degToRad(primeiraLongitude - segundaLongitude));
        dist = Math.acos(dist);
        dist = radToDeg(dist);
        dist = dist * 60;
        dist = dist * 1852;
        return (dist);
    }

    private double degToRad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double radToDeg(double rad) {
        return (rad * 180 / Math.PI);
    }

    public Hospital hospitalMaisProximo(Paciente enderecoPaciente, List<Hospital> listaDeHospitais) {
        Localizacao localizacao = new Localizacao();
        double proximidadeTemp = 0.0;
        Hospital hospital, hospitalResposta;

        hospital = listaDeHospitais.get(0);
        double proximidade = localizacao.getDistancia(
                enderecoPaciente.getEndereco().getLatitude(), enderecoPaciente.getEndereco().getLongitude(),
                hospital.getEndereco().getLatitude(), hospital.getEndereco().getLongitude());

        hospitalResposta = hospital;

        for (Hospital h : listaDeHospitais) {

            proximidadeTemp =  localizacao.getDistancia(
                    enderecoPaciente.getEndereco().getLatitude(), enderecoPaciente.getEndereco().getLongitude(),
                    h.getEndereco().getLatitude(), h.getEndereco().getLongitude());
            if (proximidadeTemp < proximidade) {
                proximidade = proximidadeTemp;
               hospitalResposta = hospital;
            }
        }
        return hospitalResposta;
    }

}

