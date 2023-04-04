package ar.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import ar.bookstore.domain.BookRepository;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @RequestMapping("/")
    public String bookstore(Model model) {
        model.addAttribute("book", repository.findAll());
        return "bookstore";
    }

}
