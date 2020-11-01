package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class PedidoResponse {

	private BigDecimal valorTotalPedido;
	private List<ItemPedidoResponse> itens; // 1

	public PedidoResponse(Pedido pedido) { // 1
		this.valorTotalPedido = pedido.getValorTotalPedido();
		setItensPedidoResponse(pedido.getItensPedidos());
	}

	// 1
	private void setItensPedidoResponse(List<ItemPedido> itensPedidos) {
		
		// 1
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
