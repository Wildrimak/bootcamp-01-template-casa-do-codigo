package br.com.zup.casadocodigo.api.dtos.responses;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.zup.casadocodigo.domain.models.Livro;
import lombok.Getter;

@Getter
public class LivroListadoResponse {

	private Integer id;
	private String titulo;
	private String resumo;
	private String sumario;
	private BigDecimal preco;
	private Integer quantidadePaginas;
	private String isbn;
	private LocalDate dataPublicacao;
	private Integer idCategoria;
	private Integer idAutor;

	public LivroListadoResponse(Livro livro) {

		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.quantidadePaginas = livro.getQuantidadePaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
		this.idCategoria = livro.getCategoria().getId();
		this.idAutor = livro.getAutor().getId();

	}

}
