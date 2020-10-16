package br.com.zup.casadocodigo.detalhelivro;

import java.math.BigDecimal;

import br.com.zup.casadocodigo.cadastrolivro.Livro;
import lombok.Getter;

@Getter
public class LivroDtoResponse {

	private String titulo;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private AutorDtoResponse autor;
	private Integer quantidadePaginas;
	private String isbn;

	public LivroDtoResponse(Livro livro) {

		this.titulo = livro.getTitulo();
		this.preco = livro.getPreco();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.autor = new AutorDtoResponse(livro.getAutor());
		this.quantidadePaginas = livro.getQuantidadePaginas();
		this.isbn = livro.getIsbn();

	}

}
