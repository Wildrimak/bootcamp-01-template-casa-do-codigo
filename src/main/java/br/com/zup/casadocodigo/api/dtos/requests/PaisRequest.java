package br.com.zup.casadocodigo.api.dtos.requests;

import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.domain.models.Pais;

public class PaisRequest { // cdd: 0

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Pais.class)
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
