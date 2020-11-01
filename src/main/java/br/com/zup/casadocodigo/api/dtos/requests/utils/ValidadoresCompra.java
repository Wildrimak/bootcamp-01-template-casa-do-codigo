package br.com.zup.casadocodigo.api.dtos.requests.utils;

import java.math.BigDecimal;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;

import br.com.zup.casadocodigo.api.dtos.requests.CupomCompraRequest;
import br.com.zup.casadocodigo.api.dtos.requests.PedidoRequest;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

public interface ValidadoresCompra {

	public static boolean validaDocumento(String documento) {

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null);
	}

	public static boolean validaTemEstado(Integer idEstado) {
		return idEstado != null;
	}

	// 1 e 2
	// Todo esse problema pq cupom está especificado para ser em compra e não em
	// pedido que é o lugar correto dele!
	public static boolean validaValorPedido(EntityManager entityManager, PedidoRequest pedido,
			CupomCompraRequest cupomRequest, CupomRepository cupomRepository) {

		BigDecimal totalEnviadoCliente = pedido.getTotal();
		BigDecimal totalCalculadoServidor = pedido.calculaValorTotalPedido(entityManager);

		// 3
		if (cupomRequest == null) {
			return totalCalculadoServidor.compareTo(totalEnviadoCliente) == 0;
		}

		Optional<Cupom> optional = cupomRepository.findByCodigo(cupomRequest.getCodigo());
		boolean ehValido = false;
		// 4
		if (optional.isPresent()) {

			BigDecimal valorDesconto = optional.get().getPercentual();
			BigDecimal valorDecimal = valorDesconto.divide(BigDecimal.valueOf(100));
			BigDecimal multiplicador = BigDecimal.ONE.subtract(valorDecimal);
			BigDecimal valorFinal = totalCalculadoServidor.multiply(multiplicador);

			ehValido = valorFinal.compareTo(totalEnviadoCliente) == 0;

		}

		return ehValido;
	}

}
