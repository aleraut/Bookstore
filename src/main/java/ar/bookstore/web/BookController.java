package ar.bookstore.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {
    
    @RequestMapping("/index")
    public String book(Model model) {
        
        return "book";
    }

}
