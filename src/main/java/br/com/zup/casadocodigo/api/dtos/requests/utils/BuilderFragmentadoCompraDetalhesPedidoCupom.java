package br.com.zup.casadocodigo.api.dtos.requests.utils;

import javax.persistence.EntityManager;

import org.springframework.util.Assert;

import br.com.zup.casadocodigo.api.dtos.requests.PedidoRequest;
import br.com.zup.casadocodigo.domain.models.Compra;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomAplicado;
import br.com.zup.casadocodigo.domain.models.CupomRepository;
import br.com.zup.casadocodigo.domain.models.Pedido;

public class BuilderFragmentadoCompraDetalhesPedidoCupom { // cdd: 7

	private EntityManager entityManager;
	private CupomRepository cupomRepository; // 1
	private String codigoCupom;
	private PedidoRequest pedidoRequest; // 1
	private Compra compra; // 1

	public BuilderFragmentadoCompraDetalhesPedidoCupom(EntityManager entityManager, CupomRepository cupomRepository,
			Compra compra) {
		this.entityManager = entityManager;
		this.cupomRepository = cupomRepository;
		this.compra = compra;
	}

	public BuilderFragmentadoCompraDetalhesPedidoCupom withCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
		return this;
	}

	public BuilderFragmentadoCompraDetalhesPedidoCupom withPedidoRequest(PedidoRequest pedidoRequest) {
		this.pedidoRequest = pedidoRequest;
		return this;
	}

	public Compra build() { 

		Assert.state(this.pedidoRequest != null, "Não foi enviado os dados do pedido");

		Pedido pedido = getPedido(); // 1
		CupomAplicado cupomAplicado = getCupomAplicado(); // 1

		Compra compra = this.compra.cloneComDadosAtualizadosDoCupomPedido(pedido, cupomAplicado);
		return compra;
	}

	private CupomAplicado getCupomAplicado() {
		
		// branch -> 1
		if (codigoCupom == null) {
			return null;
		} 
		
		//Cupom -> 1 
		Cupom modelCupom = cupomRepository.findByCodigo(codigoCupom).get();
		CupomAplicado cupomAplicado = new CupomAplicado(modelCupom);

		return cupomAplicado;
	}

	private Pedido getPedido() {
		return pedidoRequest.toModel(entityManager);
	}

}
