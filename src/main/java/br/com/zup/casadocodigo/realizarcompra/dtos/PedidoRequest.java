package br.com.zup.casadocodigo.realizarcompra.dtos;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PedidoRequest {

	@NotNull
	@Positive
	private BigDecimal total;

	@Size(min = 1)
	@Valid
	private List<ItemPedidoRequest> itens;

	public PedidoRequest(@NotNull @Positive BigDecimal total, @Size(min = 1) List<ItemPedidoRequest> itens) {
		this.total = total;
		this.itens = itens;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public List<ItemPedidoRequest> getItens() {
		return Collections.unmodifiableList(itens);
	}

}
