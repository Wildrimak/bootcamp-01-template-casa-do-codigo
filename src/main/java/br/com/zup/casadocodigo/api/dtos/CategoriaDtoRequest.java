package br.com.zup.casadocodigo.api.dtos;

import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.domain.models.Categoria;

public class CategoriaDtoRequest {

	@ValorUnicoAnnotation(atributo = "nome", classe = Categoria.class)
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
