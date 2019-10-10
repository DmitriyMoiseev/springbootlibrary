package group.springbootlibrary.service.interfaces;

import group.springbootlibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {
    List<Book> getAllBooks();
    void saveBook(Book book);
    void deleteBook(Book book);
    Book getBookById(Integer id);
}
