package br.com.zup.casadocodigo.detalhelivro;

import br.com.zup.casadocodigo.cadastroautor.Autor;
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
