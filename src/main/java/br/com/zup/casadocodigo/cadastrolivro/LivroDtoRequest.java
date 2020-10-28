package br.com.zup.casadocodigo.cadastrolivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import br.com.zup.casadocodigo.annotations.ValorUnicoAnnotation;
import br.com.zup.casadocodigo.cadastroautor.Autor;
import br.com.zup.casadocodigo.cadastrocategoria.Categoria;


public class LivroDtoRequest {

	@NotEmpty
	@ValorUnicoAnnotation(atributo = "titulo", classe = Livro.class)
    private String titulo;

	@NotEmpty
	@Size(max = 500)
	private String resumo;

	private String sumario;
    
	@NotNull
	@Min(20)
	private BigDecimal preco;
    
	@NotNull
	@Min(100)
	private Integer quantidadePaginas;
    
	@NotEmpty
	@ValorUnicoAnnotation(atributo = "isbn", classe = Livro.class)
	private String isbn;

	@Future
	@NotNull
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate dataPublicacao;
    
	@NotNull
	private Integer idCategoria;
    
	@NotNull
	private Integer idAutor;
	
	public LivroDtoRequest(
			@NotEmpty String titulo,
			@NotEmpty @Size(max = 500) String resumo, 
			String sumario,
			@NotNull @Min(20) BigDecimal preco, 
			@Min(100) Integer quantidadePaginas,
			@NotEmpty String isbn, 
			@NotNull Integer idCategoria,
			@NotNull Integer idAutor) {

		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.quantidadePaginas = quantidadePaginas;
		this.isbn = isbn;
		this.idCategoria = idCategoria;
		this.idAutor = idAutor;
	}

	public void setDataPublicacao(LocalDate dataPublicacao) {
		this.dataPublicacao = dataPublicacao;
	}

	@Transactional
    public Livro toModel(EntityManager entityManager){
        
		System.out.println("DATA PUB: " + dataPublicacao);

        @NotNull Autor autor = entityManager.find(Autor.class, idAutor);
    	@NotNull Categoria categoria = entityManager.find(Categoria.class, idCategoria);
    	
    	Assert.state(autor != null, "Não existe Autor com id " + idAutor + " no banco de dados");
		Assert.state(categoria != null, "Não existe Livro com id " + idCategoria + " no banco de dados");
    	
    	return new Livro(titulo, resumo, sumario, preco, quantidadePaginas, isbn, dataPublicacao, categoria, autor);
   
    }


}
