package org.hessian.server;

import com.caucho.hessian.server.HessianServlet;
import org.hessian.inter.HessianServerInterface;
import org.hessian.model.Author;
import org.hessian.model.Book;
import org.hessian.repo.AuthorRepo;
import org.hessian.repo.BookRepo;

import javax.servlet.annotation.WebServlet;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class HessianServer extends HessianServlet implements HessianServerInterface {

    private BookRepo bookRepo;
    private AuthorRepo authorRepo;


    public HessianServer() {
        bookRepo = new BookRepo();
        authorRepo = new AuthorRepo();
    }


    @Override
    public String ping() throws UnknownHostException {
        InetAddress host = InetAddress.getLocalHost();
        return "Java Hessian Exec: " + host.getHostName()+" ("+host.getHostAddress()+": 8080), "+new Date();
    }

    @Override
    public Author getAuthorById(Integer id) {
        try{
            return this.authorRepo.getAuthorById(id);
        }catch (SQLException e){
            System.out.println(e);
            return null;
        }
    }


    @Override
    public List<Author> getAllAuthors() {
        try {
            return this.authorRepo.getAllAuthors();
        }catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Author> getAuthorsBySearch(String keyword) {
        try {
            return this.authorRepo.getAuthorsBySearch(keyword);
        }catch (SQLException e){
            System.out.println(e);
            return new ArrayList<>();
        }

    }

    @Override
    public void addAuthor(String name, Integer age) {
        try{
            this.authorRepo.addAuthor(name, age);
        }catch (SQLException e) {
            System.out.println("Error adding author " + e);
        }
    }

    @Override
    public void deleteAuthor(Integer id) {
        try {
            authorRepo.deleteAuthor(id);
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void editAuthor(Integer id, String name, Integer age) {
        try {
            authorRepo.editAuthor(id, name, age);
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public Book getBookById(Integer id) {
        try{
            return this.bookRepo.getBookById(id);
        }catch (SQLException e) {
            System.out.println(e);
            return new Book();
        }
    }

    @Override
    public List<Book> getAllBooks() {
       try{
           return this.bookRepo.getAllBooks();
       }catch (SQLException e) {
           System.out.println(e);
           return new ArrayList<>();
       }
    }

    @Override
    public List<Book> getBooksBySearch(String keyword)  {
        try{
            return this.bookRepo.getBooksBySearch(keyword);
        }catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public List<Book> getBooksByAuthor(Integer id) {
        try{
            return this.bookRepo.getBookByAuthor(id);
        }catch (SQLException e) {
            System.out.println(e);
            return new ArrayList<>();
        }
    }

    @Override
    public void addBook(String title, String description, Integer year, Integer authorId)  {
        try{
            this.bookRepo.addBook(title, description, year, authorId);
        }catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void deleteBook(Integer id) {
        try{
            this.bookRepo.deleteBook(id);
        }catch (SQLException e) {
            System.out.println(e);

        }
    }

    @Override
    public void editBook(Integer id, String title, String description, Integer year, Integer authorId) {
        try{
            this.bookRepo.editBook(id, title, description, year, authorId);
        }catch (SQLException e) {
            System.out.println(e);

        }
    }

}
