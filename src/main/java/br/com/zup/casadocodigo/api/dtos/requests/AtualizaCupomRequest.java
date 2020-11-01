package br.com.zup.casadocodigo.api.dtos.requests;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import br.com.zup.casadocodigo.api.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.domain.models.Cupom;

public class AtualizaCupomRequest {

	@NotEmpty
	@ValorUnicoAnnotation(classe = Cupom.class, atributo = "codigo")
	private String codigo;

	@NotNull
	@Positive
	private BigDecimal percentual;

	@Future
	private LocalDate validade;

	public AtualizaCupomRequest(@NotEmpty String codigo, @NotNull @Positive BigDecimal percentual, @Future LocalDate validade) {
		this.codigo = codigo;
		this.percentual = percentual;
		this.validade = validade;
	}

	// 1
	public Cupom toModel() {
		return new Cupom(codigo, percentual, validade);
	}

}
