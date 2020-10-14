package br.com.zup.casadocodigo.api.dto.requests;

import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.domain.models.Categoria;

public class CategoriaDtoRequest {

	@NotEmpty
	private String nome;

	public CategoriaDtoRequest(@NotEmpty String nome) {
		this.nome = nome;
	}

	public Categoria toModel() {
		return new Categoria(nome);
	}

}
