package br.com.zup.casadocodigo.api.dtos.responses;

import br.com.zup.casadocodigo.domain.models.Autor;
import lombok.Getter;

@Getter
public class AutorResponse {

	private String nome;
	private String descricao;

	public AutorResponse(Autor autor) {
		this.nome = autor.getNome();
		this.descricao = autor.getDescricao();
	}

}
