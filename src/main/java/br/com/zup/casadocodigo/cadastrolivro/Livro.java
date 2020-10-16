package br.com.zup.casadocodigo.cadastrolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigo.cadastroautor.Autor;
import br.com.zup.casadocodigo.cadastrocategoria.Categoria;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotEmpty
	@Column(nullable = false)
    private String titulo;

	@NotEmpty
	@Size(max = 500)
	@Column(nullable = false, length = 500)
	private String resumo;

	private String sumario;
    
	@NotNull
	@Min(20)
	@Column(nullable = false)
	private BigDecimal preco;
    
	@NotNull
	@Min(100)
	@Column(nullable = false)
	private Integer quantidadePaginas;
    
	@NotEmpty
	@Column(nullable = false)
	private String isbn;

	@Future
	@NotNull
	@Column(nullable = false)
	private LocalDate dataPublicacao;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Autor autor;

	@Deprecated
	public Livro() {
	}

	public Livro(String titulo, String resumo, String sumario, BigDecimal preco, Integer quantidadePaginas, String isbn,
			LocalDate dataPublicacao, Categoria categoria, Autor autor) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.quantidadePaginas = quantidadePaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.categoria = categoria;
		this.autor = autor;
	}
}
