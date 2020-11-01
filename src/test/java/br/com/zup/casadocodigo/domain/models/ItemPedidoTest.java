package br.com.zup.casadocodigo.domain.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class ItemPedidoTest { // cdd: 2

	@Test
	public void testaCalculoValorTotal() {

		Livro livro = new Livro("livro", "livro", "sumario", BigDecimal.valueOf(19), 70, "ashkahsdfkja",
				LocalDate.now(), new Categoria("alba"), new Autor("wildrimak", "wildrimak", "descreve"));

		ItemPedido itemPedido = new ItemPedido(livro, 3);

		assertEquals(BigDecimal.valueOf(57), itemPedido.getValorTotalItem());
	}

}
