package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class PedidoResponse {

	private BigDecimal valorTotalPedido;
	private List<ItemPedidoResponse> itens;

	public PedidoResponse(Pedido pedido) {
		this.valorTotalPedido = pedido.getValorTotalPedido();
		setItensPedidoResponse(pedido.getItensPedidos());
	}

	private void setItensPedidoResponse(List<ItemPedido> itensPedidos) {
		List<ItemPedidoResponse> itens = itensPedidos
				.stream()
				.map(itemPedido -> new ItemPedidoResponse(itemPedido))
				.collect(Collectors.toList());
		this.itens = itens;
	}

	public BigDecimal getValorTotalPedido() {
		return valorTotalPedido;
	}

	public List<ItemPedidoResponse> getItens() {
		return Collections.unmodifiableList(itens);
	}
}
