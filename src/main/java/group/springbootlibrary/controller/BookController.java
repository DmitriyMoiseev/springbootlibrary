package group.springbootlibrary.controller;

import group.springbootlibrary.model.Book;
import group.springbootlibrary.service.interfaces.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class BookController {

    private BookService bookService;

    @Autowired
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/book")
    public ModelAndView showAllBooks() {
        List<Book> books = bookService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("books");
        modelAndView.addObject("bookList", books);
        return modelAndView;
    }

    @GetMapping("/book/edit/{id}")
    public ModelAndView showEditBookPage(@PathVariable("id") int id) {
        Book book = bookService.getBookById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editBookPage");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    @PostMapping("/book/edit")
    public ModelAndView editBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/book/");
        bookService.saveBook(book);
        return modelAndView;
    }

    @GetMapping("/book/add")
    public ModelAndView showBookAddPage() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editBookPage");
        return modelAndView;
    }

    @PostMapping("/book/add")
    public ModelAndView addBook(@ModelAttribute("book") Book book) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/book");
        bookService.saveBook(book);
        return modelAndView;
    }

    @GetMapping("/book/delete/{id}")
    public ModelAndView deleteBook(@PathVariable("id") int id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/book");
        Book book = bookService.getBookById(id);
        bookService.deleteBook(book);
        return modelAndView;
    }
}
