package ar.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TestEntityManager em;

    @Test // Testing find by title from bookrepository
	public void findByTitleShouldReturnTitle() {
		List<Book> books =bookRepository.findByTitle("Kissa");
		
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Kassu");
		}

	@Test // Testing bookrepository save method
	public void createNewBook() {
		Book book = new Book("Jokin", "Kirja", 2020, "99999", 111, null);
		bookRepository.save(book); // sql-insert
		assertThat(book.getId()).isNotNull();
	}
	
	@Test // Testing bookrepository delete method
	public void DeleteBookTest() {
		Book book = new Book("Jokin", "Kirja", 2020, "99999", 111, null);
		final Long id = em.persistAndGetId(book, Long.class);
        bookRepository.deleteById(id);
        em.flush();
        Book after = em.find(Book.class, id);
		bookRepository.deleteById(book.getId());
		assertThat(after).isNull();
	}
    
}
