package br.com.zup.casadocodigo.paisestado;

import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.annotations.ValorUnicoAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisRequestDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Pais.class)
	private String nome;
	
}
