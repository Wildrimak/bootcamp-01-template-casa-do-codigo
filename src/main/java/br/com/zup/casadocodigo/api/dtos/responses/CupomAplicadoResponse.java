package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.zup.casadocodigo.domain.models.CupomAplicado;

public class CupomAplicadoResponse {

	private String codigoMomento;
	private BigDecimal percentualMomento;
	private LocalDate validadeMomento;
	private BigDecimal valorTotalComDesconto;

	public CupomAplicadoResponse(CupomAplicado cupomAplicado, BigDecimal valorFinal) {
		this.codigoMomento = cupomAplicado.getCodigoMomento();
		this.percentualMomento = cupomAplicado.getPercentualMomento();
		this.validadeMomento = cupomAplicado.getValidadeMomento();
		this.valorTotalComDesconto = valorFinal;
	}

	public String getCodigoMomento() {
		return codigoMomento;
	}

	public BigDecimal getPercentualMomento() {
		return percentualMomento;
	}

	public LocalDate getValidadeMomento() {
		return validadeMomento;
	}

	public BigDecimal getValorTotalComDesconto() {
		return valorTotalComDesconto;
	}
}
