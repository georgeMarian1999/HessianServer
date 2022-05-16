package org.hessian.inter;

import org.hessian.model.Author;
import org.hessian.model.Book;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

public interface HessianServerInterface {

    public String ping() throws UnknownHostException;

    public Author getAuthorById(Integer id);
    public List<Author> getAllAuthors();
    public List<Author> getAuthorsBySearch(String keyword);
    public void addAuthor(String name, Integer age);
    public void deleteAuthor(Integer id);
    public void editAuthor(Integer id, String name, Integer age);

    public Book getBookById(Integer id);
    public List<Book> getAllBooks();
    public List<Book> getBooksBySearch(String keyword);
    public List<Book> getBooksByAuthor(Integer id);
    public void addBook(String title, String description, Integer year, Integer authorId);
    public void deleteBook(Integer id);
    public void editBook(Integer id,String title, String description, Integer year, Integer authorId);
}
