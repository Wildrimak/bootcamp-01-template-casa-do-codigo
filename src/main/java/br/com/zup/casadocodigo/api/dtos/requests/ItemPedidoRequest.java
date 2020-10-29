	package br.com.zup.casadocodigo.api.dtos.requests;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Livro;

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

	public ItemPedido toModel(EntityManager entityManager) {

		Livro livro = entityManager.find(Livro.class, this.idLivro);
		ItemPedido itemPedido = new ItemPedido(livro, this.quantidade);

		return itemPedido;

	}

}
