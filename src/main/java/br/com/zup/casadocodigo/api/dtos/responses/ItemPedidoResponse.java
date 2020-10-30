package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;

import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Livro;

public class ItemPedidoResponse {
	
	private Integer quantidade;
	private BigDecimal valorTotalItem;
	private LivroDetalheResponse livro;

	public ItemPedidoResponse(ItemPedido itemPedido) {
		this.quantidade = itemPedido.getQuantidade();
		this.valorTotalItem = itemPedido.getValorTotalItem();
		Livro livroReal = itemPedido.getLivro();
		this.livro = new LivroDetalheResponse(livroReal);
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public BigDecimal getValorTotalItem() {
		return valorTotalItem;
	}

	public LivroDetalheResponse getLivro() {
		return livro;
	}

}
