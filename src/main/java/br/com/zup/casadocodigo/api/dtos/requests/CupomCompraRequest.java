package br.com.zup.casadocodigo.api.dtos.requests;

import br.com.zup.casadocodigo.api.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.domain.models.Cupom;
import br.com.zup.casadocodigo.domain.models.CupomRepository;

public class CupomCompraRequest {

	@ExisteIdAnnotation(classe = Cupom.class, atributo = "codigo")
	private String codigo;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	// Não é nesse metodo que valida se o cupom é válido, e sim em uma annotation
	public Cupom toModel(CupomRepository cupomRepository) {

		Cupom cupom = cupomRepository.findByCodigo(codigo).orElse(null);
		return cupom;

	}

}
