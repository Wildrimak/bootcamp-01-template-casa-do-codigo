package br.com.zup.casadocodigo.listarlivro;

import java.math.BigDecimal;
import java.time.LocalDate;

import br.com.zup.casadocodigo.cadastrolivro.Livro;
import lombok.Getter;

@Getter
public class LivroDtoResponse {

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

	public LivroDtoResponse(Livro livro) {

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
