package br.com.lucasbdourado.library.rest.book;

import br.com.lucasbdourado.library.dto.book.BookResponse;
import br.com.lucasbdourado.library.entity.book.Book;
import br.com.lucasbdourado.library.exception.NotFoundException;
import br.com.lucasbdourado.library.mapper.book.BookMapper;
import br.com.lucasbdourado.library.service.book.IBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
@Tag(name = "Livros", description = "Operações relacionadas aos livros / obras")
public class BookREST
{
	private static final String NOT_FOUND = "Not Found";

	private final IBookService service;

	public BookREST(IBookService service)
	{
		this.service = service;
	}

	@GetMapping({"/", ""})
	@Operation(summary = "Listar todos os livros", description = "Retorna uma lista com todos os livros.")
	public ResponseEntity<Object> getBookList()
	{
		try
		{
			List<Book> bookList = service.findAll();

			List<BookResponse> bookResponseList = bookList.stream()
				.map(BookMapper::toResponse).toList();

			return ResponseEntity.ok().body(bookResponseList);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar um livro pelo ID", description = "Retorna o livro pelo Id, caso não encontre nenhum, retorna que não foi encontrado.")
	public ResponseEntity<Object> getBookById(@PathVariable UUID id)
	{
		try
		{
			Book book = service.findById(id);

			BookResponse bookResponse = BookMapper.toResponse(book);

			return ResponseEntity.ok().body(bookResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PostMapping({"/", ""})
	@Operation(summary = "Criar um livro")
	public ResponseEntity<Object> create(@RequestBody Book bookPayload)
	{
		try
		{
			Book book = service.persist(bookPayload);

			BookResponse bookResponse = BookMapper.toResponse(book);

			return ResponseEntity.ok().body(bookResponse);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um livro")
	public ResponseEntity<Object> update(@PathVariable UUID id, @RequestBody Book bookPayload)
	{
		try
		{
			Book book = service.update(id, bookPayload);

			BookResponse bookResponse = BookMapper.toResponse(book);

			return ResponseEntity.ok().body(bookResponse);
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um livro pelo Id")
	public ResponseEntity<Object> delete(@PathVariable UUID id)
	{
		try
		{
			service.delete(id);

			return ResponseEntity.ok().body("Deletado com sucesso!");
		}
		catch (NotFoundException e)
		{

			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(NOT_FOUND);
		}
		catch (Exception e)
		{

			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
