package ar.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

@Bean
public CommandLineRunner bookDemo(BookRepository repository) {
	return (args) -> {
		log.info("save a couple of books");
		repository.save(new Book("Kirija", "Tolkien", 2000, "0202020", 12));
		repository.save(new Book("Katoha", "Samuel", 2022, "1235455", 3));

		log.info("fetch books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
	};
}

}
