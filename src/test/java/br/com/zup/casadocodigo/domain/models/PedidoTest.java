package br.com.zup.casadocodigo.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PedidoTest { // cdd: 3

	@Test
	public void verificaValorTotalPedido() {

		Livro l1 = new Livro();
		l1.setPreco(BigDecimal.valueOf(225));

		Livro l2 = new Livro();
		l2.setPreco(BigDecimal.valueOf(275));

		ItemPedido item1 = new ItemPedido();
		item1.setLivro(l1);
		item1.setQuantidade(10);

		ItemPedido item2 = new ItemPedido();
		item2.setLivro(l2);
		item2.setQuantidade(10);

		Pedido pedido = new Pedido();
		pedido.addItemPedido(item1);
		pedido.addItemPedido(item2);

		assertEquals(BigDecimal.valueOf(5000), pedido.getValorTotalPedido());

	}

}
