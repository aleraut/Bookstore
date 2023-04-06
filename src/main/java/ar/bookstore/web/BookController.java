package ar.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;
import ar.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // login
    @RequestMapping(value="/login")
    public String login() {
        return "login";
    }

    // Show all books
    @RequestMapping(value={"/booklist"})
	public String bookList(Model model) {

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = user.getUsername();
		System.out.println("USERNAME: " + username);

		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());

		return "booklist";
    }

    // RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
        return (List<Book>) bookRepository.findAll();
    }

    // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    // Add a book
    @RequestMapping(value ="/add")
    public String addBook(Model model){

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        
        return "addbook";
    }

    // Save a book
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {

        bookRepository.save(book);

        return "redirect:booklist";
    }

    // Delete a book
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    // Edit a book
    @RequestMapping(value ="/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long bookId, Model model){

        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        
        return "editbook";
    }

}