package br.com.zup.casadocodigo.realizarcompra.utils;

import javax.persistence.EntityManager;
import javax.validation.Valid;

import br.com.zup.casadocodigo.paisestado.Estado;
import br.com.zup.casadocodigo.paisestado.Pais;
import br.com.zup.casadocodigo.realizarcompra.dtos.CompraRequest;
import br.com.zup.casadocodigo.realizarcompra.models.Compra;

public class CriaCompra {

	private EntityManager entityManager;
	
	@Valid
	private CompraRequest compraRequest;

	public CriaCompra(EntityManager entityManager, CompraRequest compraRequest) {
		this.entityManager = entityManager;
		this.compraRequest = compraRequest;
	}
	
	public Compra getCompra() {
		
		Pais pais = entityManager.find(Pais.class, compraRequest.getIdPais());
		Estado estado = entityManager.find(Estado.class, compraRequest.getIdEstado());
		
		CriaPedido criaPedido = new CriaPedido(entityManager, compraRequest.getPedidoRequest());
		
		Compra compra = new Compra(
				compraRequest.getEmail(), 
				compraRequest.getNome(), 
				compraRequest.getSobrenome(), 
				compraRequest.getDocumento(), 
				compraRequest.getEndereco(), 
				compraRequest.getComplemento(), 
				compraRequest.getCidade(), 
				pais, 
				estado, 
				compraRequest.getTelefone(), 
				compraRequest.getCep(), 
				criaPedido.getPedido());
		
		return compra;
	}

}
