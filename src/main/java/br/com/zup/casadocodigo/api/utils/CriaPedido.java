package br.com.zup.casadocodigo.api.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.zup.casadocodigo.api.dtos.requests.ItemPedidoRequest;
import br.com.zup.casadocodigo.api.dtos.requests.PedidoRequest;
import br.com.zup.casadocodigo.domain.models.ItemPedido;
import br.com.zup.casadocodigo.domain.models.Livro;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class CriaPedido {

	public EntityManager entityManager;
	
	@Valid
	public PedidoRequest pedidoRequest;

	public CriaPedido(EntityManager entityManager, PedidoRequest compraRequest) {
		this.entityManager = entityManager;
		this.pedidoRequest = compraRequest;
	}

	public Pedido getPedido() {

		Pedido pedido = new Pedido();
		pedido.setItensPedidos(this.getItensPedidos());

		return pedido;
	}

	private List<ItemPedido> getItensPedidos() {

		List<ItemPedidoRequest> itens = pedidoRequest.getItens();
		List<ItemPedido> itensPedidos = new ArrayList<>();

		itens.forEach(itemRequest -> {

			Livro livro = entityManager.find(Livro.class, itemRequest.getIdLivro());
			ItemPedido itemPedido = new ItemPedido(livro, itemRequest.getQuantidade());
			itensPedidos.add(itemPedido);

		});

		return itensPedidos;
	}

}