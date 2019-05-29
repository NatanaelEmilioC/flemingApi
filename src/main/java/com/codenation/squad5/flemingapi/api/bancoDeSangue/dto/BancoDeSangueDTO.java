package com.codenation.squad5.flemingapi.api.bancoDeSangue.dto;

import com.codenation.squad5.flemingapi.api.common.dto.EnderecoDTO;
import com.codenation.squad5.flemingapi.api.leitos.dto.LeitoDTO;

import java.util.ArrayList;
import java.util.List;

public class BancoDeSangueDTO {

	private String id;
	
	private String tipoSangue;
	
	private long quantidade;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTipoSangue() {
		return tipoSangue;
	}

	public void setTipoSangue(String tipoSangue) {
		this.tipoSangue = tipoSangue;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

}
