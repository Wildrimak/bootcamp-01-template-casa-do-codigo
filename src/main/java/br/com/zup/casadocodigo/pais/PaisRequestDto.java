package br.com.zup.casadocodigo.pais;

import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.compartilhado.ValorUnicoAnnotation;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaisRequestDto {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "nome", classe = Pais.class)
	private String nome;
	
}
