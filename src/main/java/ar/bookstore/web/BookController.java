package ar.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;

@Controller
public class BookController {
@Autowired
private BookRepository repository;

    @RequestMapping("/")
    public String Book(Model model){
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }

    @RequestMapping(value ="/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @PostMapping(value = "/add")
    public String saveBook(@ModelAttribute Book book) {
        repository.save(book);
        return "redirect:/add"; // Redirect to the desired page after saving the book
    }

}