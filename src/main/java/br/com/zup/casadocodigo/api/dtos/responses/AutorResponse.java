package br.com.zup.casadocodigo.api.dtos.responses;

import br.com.zup.casadocodigo.domain.models.Autor;

public class AutorResponse { // cdd: 1 

	private String nome;
	private String descricao;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public AutorResponse(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

}
