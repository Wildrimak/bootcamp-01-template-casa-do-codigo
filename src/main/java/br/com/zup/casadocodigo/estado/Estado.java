package br.com.zup.casadocodigo.estado;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotEmpty;

import br.com.zup.casadocodigo.pais.Pais;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Estado {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idEstado;

	@NotEmpty
	@Column(nullable = false)
	private String nome;
	
	private String alfa;
	private String beta;
	private String delta;
	private Date theta;

	@ManyToOne
	private Pais pais;

	@Deprecated
	public Estado() {
	}

	public Estado(@NotEmpty String nome, Pais pais) {
		this.nome = nome;
		this.pais = pais;
	}

}
