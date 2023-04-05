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

			Category category1 = new Category(1, "Horror");
			Category category2 = new Category(2, "Drama");
			Category category3 = new Category(3, "Action");
			Category category4 = new Category(4, "Fantasy");
			categoryRepository.save(category1);
			categoryRepository.save(category2);
			categoryRepository.save(category3);
			categoryRepository.save(category4);

			bookRepository.save(new Book(0, "Koira", "Samuel", 2020, "44444", 4.15, category1));

			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}