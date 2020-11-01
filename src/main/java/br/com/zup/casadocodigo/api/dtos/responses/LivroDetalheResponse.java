package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;

import br.com.zup.casadocodigo.domain.models.Livro;

public class LivroDetalheResponse {

	private String titulo;
	private BigDecimal preco;
	private String resumo;
	private String sumario;
	private AutorResponse autor; // 1
	private Integer quantidadePaginas;
	private String isbn;

	public LivroDetalheResponse(Livro livro) { // 1

		this.titulo = livro.getTitulo();
		this.preco = livro.getPreco();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.autor = new AutorResponse(livro.getAutor());
		this.quantidadePaginas = livro.getQuantidadePaginas();
		this.isbn = livro.getIsbn();

	}

	public String getTitulo() {
		return titulo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public AutorResponse getAutor() {
		return autor;
	}

	public Integer getQuantidadePaginas() {
		return quantidadePaginas;
	}

	public String getIsbn() {
		return isbn;
	}

}
