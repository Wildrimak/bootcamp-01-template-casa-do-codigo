package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.annotations.ValorUnicoAnnotation;

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
