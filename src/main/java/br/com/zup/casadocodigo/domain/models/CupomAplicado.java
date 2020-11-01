package br.com.zup.casadocodigo.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CupomAplicado {

	@NotEmpty
	private String codigoMomento;

	@Positive
	@NotNull
	private BigDecimal percentualMomento;

	@NotNull
	@Future
	private LocalDate validadeMomento;

	@ManyToOne
	private Cupom cupom; // 1

	public CupomAplicado() {
	}

	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.codigoMomento = cupom.getCodigo();
		this.percentualMomento = cupom.getPercentual();
		this.validadeMomento = cupom.getValidade();
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

	public Optional<Cupom> getOptionalCupom() {
		return Optional.of(cupom);
	}

}
