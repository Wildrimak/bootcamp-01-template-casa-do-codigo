package br.com.zup.casadocodigo.realizarcompra.utils;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.zup.casadocodigo.cadastrolivro.Livro;
import br.com.zup.casadocodigo.realizarcompra.dtos.ItemPedidoRequest;
import br.com.zup.casadocodigo.realizarcompra.dtos.PedidoRequest;
import br.com.zup.casadocodigo.realizarcompra.models.ItemPedido;
import br.com.zup.casadocodigo.realizarcompra.models.Pedido;

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