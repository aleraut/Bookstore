package ar.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;
import ar.bookstore.domain.Category;
import ar.bookstore.domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {

			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Drama"));
			categoryRepository.save(new Category("Action"));
			categoryRepository.save(new Category("Fantasy"));

			bookRepository.save(new Book("Koira", "Samuel", 2020, "44444", 4.15, categoryRepository.findByName("Horror").get(0)));
			bookRepository.save(new Book("Kissa", "Kassu", 1988, "33333", 27.55, categoryRepository.findByName("Action").get(0)));

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}