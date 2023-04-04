package ar.bookstore.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends CrudRepository <Book, Long> {

    // fetch by year
	List<Book> findByYear(@Param("year") int year);
	
	// fetch by author
	List<Book> findByAuthor(@Param("author") String author);
    
}
