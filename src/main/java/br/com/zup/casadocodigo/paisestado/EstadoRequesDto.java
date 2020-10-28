package br.com.zup.casadocodigo.paisestado;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.zup.casadocodigo.annotations.ExisteIdAnnotation;
import br.com.zup.casadocodigo.annotations.ValorUnicoAnnotation;

public class EstadoRequesDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Estado.class)
	private String nome;

	@NotNull
	@ExisteIdAnnotation(atributo = "id", classe = Pais.class)
	private Integer idPais;

	public EstadoRequesDto(@NotEmpty String nome, @NotNull Integer idPais) {
		this.nome = nome;
		this.idPais = idPais;
	}

	public Estado toModel(EntityManager entityManager) {

		Estado estado = new Estado(this.nome, entityManager.find(Pais.class, this.idPais));

		return estado;
	}

}
