package org.hessian.inter;

import org.hessian.model.Author;

import java.awt.print.Book;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.List;

public interface HessianServerInterface {

    public String ping() throws UnknownHostException;

    public List<Author> getAllAuthors();
    public List<Author> getAuthorsBySearch(String keyword);
    public void addAuthor(String name, Integer age);
    public void deleteAuthor(Integer id);
    public void editAuthor(Integer id, String name, Integer age);

    public List<Book> getAllBooks();
    public List<Book> getBooksBySearch(String keyword);
    public void addBook(String title, String description, Integer year, Integer authorId) ;
}
