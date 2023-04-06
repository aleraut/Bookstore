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
import ar.bookstore.domain.User;
import ar.bookstore.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {

			categoryRepository.save(new Category("Horror"));
			categoryRepository.save(new Category("Drama"));
			categoryRepository.save(new Category("Action"));
			categoryRepository.save(new Category("Fantasy"));

			bookRepository.save(new Book("Koira", "Samuel", 2020, "44444", 4.15, categoryRepository.findByName("Horror").get(0)));
			bookRepository.save(new Book("Kissa", "Kassu", 1988, "33333", 27.55, categoryRepository.findByName("Action").get(0)));

			// Create users: admin/admin user/user
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6","user@bookstore.asd", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "admin@bookstore.asd", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);

			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
		};
	}

}