package br.com.zup.casadocodigo.api.dtos;

import br.com.zup.casadocodigo.domain.models.Autor;
import lombok.Getter;

@Getter
public class AutorDtoResponse {

	private String nome;
	private String descricao;

	public AutorDtoResponse(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

}
