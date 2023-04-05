package ar.bookstore.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.bookstore.domain.Book;
import ar.bookstore.domain.BookRepository;
import ar.bookstore.domain.CategoryRepository;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;


    @RequestMapping("/booklist")
	public String bookList(Model model) {

		model.addAttribute("books", bookRepository.findAll());
		model.addAttribute("categories", categoryRepository.findAll());

		return "booklist";
    }

    @RequestMapping(value = "/delete/{bookId}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("bookId") Long bookId) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
}

    @RequestMapping(value ="/add")
    public String addBook(Model model){

        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        
        return "addbook";
    }

    @RequestMapping(value ="/edit/{bookId}")
    public String editBook(@PathVariable("bookId") Long bookId, Model model){

        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        
        return "editbook";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveBook(Book book) {

        bookRepository.save(book);

        return "redirect:booklist";
    }

}