package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;

import br.com.zup.casadocodigo.domain.models.Livro;
import lombok.Getter;

@Getter
public class LivroDetalheResponse {

	private String titulo;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private AutorResponse autor;
	private Integer quantidadePaginas;
	private String isbn;

	public LivroDetalheResponse(Livro livro) {

		this.titulo = livro.getTitulo();
		this.preco = livro.getPreco();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.autor = new AutorResponse(livro.getAutor());
		this.quantidadePaginas = livro.getQuantidadePaginas();
		this.isbn = livro.getIsbn();

	}

}
