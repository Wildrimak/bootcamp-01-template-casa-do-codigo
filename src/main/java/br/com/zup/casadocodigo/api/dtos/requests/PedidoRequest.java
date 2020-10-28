package br.com.zup.casadocodigo.api.dtos.requests;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class PedidoRequest {

	@NotNull
	@Positive
	private BigDecimal total;

	@Size(min = 1)
	@Valid
	private List<ItemPedidoRequest> itens;

	public PedidoRequest(@NotNull @Positive BigDecimal total, @Size(min = 1) List<ItemPedidoRequest> itens) {
		System.out.println("Estou tentando pelo menos criar o pedidoRequest");
		this.total = total;
		this.itens = itens;
	}

	public Pedido toModel(EntityManager entityManager) {

		Pedido pedido = new Pedido();

		System.out.println("Antes percorrer a lista de itens pedidos");
		
		List<ItemPedido> itensPedidos = itens.stream()
				.map(itemPedidoRequest -> itemPedidoRequest.toModel(entityManager)).collect(Collectors.toList());

		pedido.setItensPedidos(itensPedidos);

		return pedido;
	}

	public boolean totalPassadoEhIgualTotalNoServidor(EntityManager entityManager) {

		Pedido pedido = toModel(entityManager);

		return pedido.getValorTotalPedido().equals(total);

	}

}
