package br.com.zup.casadocodigo.domain.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	private String codigo;

	@NotNull
	@Positive
	private BigDecimal percentual;

	@Future
	private LocalDate validade;

	public Cupom() {
	}

	public Cupom(@NotEmpty String codigo, @NotNull @Positive BigDecimal percentual, @Future LocalDate validade) {
		this.codigo = codigo;
		this.percentual = percentual;
		this.validade = validade;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}

	public LocalDate getValidade() {
		return validade;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Integer getId() {
		return id;
	}

	public void atualizarCupom(Cupom cupomAtualizado) {

		this.codigo = cupomAtualizado.getCodigo();
		this.percentual = cupomAtualizado.getPercentual();
		this.validade = cupomAtualizado.getValidade();

	}

}
