package group.springbootlibrary.service.interfaces;

import group.springbootlibrary.model.Author;
import group.springbootlibrary.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuthorService {
    List<Author> getAllAuthors();
    void saveAuthor(Author author);
    void deleteAuthor(Author author);
    Author getAuthorById(Integer id);
    List<Book> getBooks(Author author);
}
