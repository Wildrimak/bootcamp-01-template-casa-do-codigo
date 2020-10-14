package br.com.zup.casadocodigo.api.dto.requests;

import br.com.zup.casadocodigo.domain.models.Categoria;

public class CategoriaDtoRequest {

	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Categoria toModel() {
		return new Categoria(nome);
	}

}
