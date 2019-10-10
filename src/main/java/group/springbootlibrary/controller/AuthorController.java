package group.springbootlibrary.controller;

import group.springbootlibrary.model.Author;
import group.springbootlibrary.model.Book;
import group.springbootlibrary.service.interfaces.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/author")
    public ModelAndView showAllAuthors() {
        List<Author> authors = authorService.getAllAuthors();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("authors");
        modelAndView.addObject("authorList", authors);
        return modelAndView;
    }

    @GetMapping("/author/edit/{id}")
    public ModelAndView showEditAuthorPage(@PathVariable("id") int id) {
        Author author = authorService.getAuthorById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editAuthorPage");
        modelAndView.addObject("author", author);
        return modelAndView;
    }

    @PostMapping("/author/edit")
    public ModelAndView editAuthor(@ModelAttribute("author") Author author) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/author/");
        authorService.saveAuthor(author);
        return modelAndView;
    }

    @GetMapping("/author/add")
    public ModelAndView showAuthorAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editAuthorPage");
        return modelAndView;
    }

    @PostMapping("/author/add")
    public ModelAndView addAuthor(@ModelAttribute("author") Author author) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/author");
        authorService.saveAuthor(author);
        return modelAndView;
    }

    @GetMapping("/author/delete/{id}")
    public ModelAndView deleteAuthor(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/author");
        Author author = authorService.getAuthorById(id);
        authorService.deleteAuthor(author);
        return modelAndView;
    }

    @GetMapping("/author/show/{id}")
    public ModelAndView showBooksOfAuthor(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        Author author = authorService.getAuthorById(id);
        List<Book> books = authorService.getBooks(author);
        modelAndView.addObject("bookList", books);
        return modelAndView;
    }
}
