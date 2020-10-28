package br.com.zup.casadocodigo.realizarcompra.dtos;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.casadocodigo.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.cadastrolivro.Livro;

public class ItemPedidoRequest {

	@NotNull
	@ExisteIdAnnotation(classe = Livro.class, atributo = "id")
	private Integer idLivro;

	@NotNull
	@Positive
	private Integer quantidade;

	public ItemPedidoRequest(@NotNull Integer idLivro, @NotNull @Positive Integer quantidade) {
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	public Integer getIdLivro() {
		return idLivro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

}
