package org.hessian.repo;

import org.hessian.model.Book;
import org.hessian.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepo {
    private JDBC utils;


    public BookRepo() {
        utils = new JDBC();
    }

    public List<Book> getAllBooks() throws SQLException {
            Connection con=utils.getConnection();
            List<Book> books=new ArrayList<>();
            try(PreparedStatement preStmt=con.prepareStatement("select * from \"Book\"")) {
                try(ResultSet result=preStmt.executeQuery()) {
                    while (result.next()) {
                        Integer id = result.getInt("bookId");
                        String title,description;
                        Integer year;
                        Integer bookId;
                        title = result.getString("title");
                        description = result.getString("description");
                        year = result.getInt("year");
                        bookId = result.getInt("bookId");
                        Book book=new Book(id,title, description, year, bookId);
                        books.add(book);
                    }
                }
            }
        return books;
    }

    public Integer getNextId() throws SQLException {
        Connection con=utils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(A.\"bookId\") as maxId FROM \"Book\" A  ")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("maxId") + 1;
                }
            }
        }
        return 0;
    }
    

    public Book getBookById(Integer id) throws SQLException {
        Connection con=utils.getConnection();
        Book bookDefault = new Book();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Book\" where \"bookId\" = ?" )) {
            preStmt.setInt(1, id);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String title = result.getString("title");
                    String description = result.getString("description");
                    Integer year = result.getInt("year");
                    Integer authorId = result.getInt("authorId");
                    return new Book(id,title, description, year, authorId);
                }
            }
        }
        return bookDefault;
    }

    public List<Book> getBookByAuthor(Integer id) throws SQLException {
        Connection con=utils.getConnection();
        List<Book> bookList = new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Book\" where \"authorId\" = ?" )) {
            preStmt.setInt(1, id);
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    String title = result.getString("title");
                    String description = result.getString("description");
                    Integer year = result.getInt("year");
                    Integer authorId = result.getInt("authorId");
                    Book book=new Book(id,title, description, year, authorId);
                    bookList.add(book);
                }
            }
        }
        return bookList;
    }

    public List<Book> getBooksBySearch(String keyword) throws SQLException {
        Connection con=utils.getConnection();
        List<Book> books=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Book\" where title like ?" )) {
            preStmt.setString(1, "%" + keyword + "%");
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    Integer id = result.getInt("bookId");
                    String title = result.getString("title");
                    String description = result.getString("description");
                    Integer year = result.getInt("year");
                    Integer authorId = result.getInt("authorId");
                    Book book=new Book(id,title, description, year, authorId);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getAllBooks() BookRepo "+e);
            throw e;
        }
        return books;
    }

    public void addBook(String title, String description, Integer year, Integer authorId) throws SQLException {
        Connection con = utils.getConnection();

        int nextId = getNextId();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO \"Book\" VALUES (?,?,?,?,?)")) {
            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, authorId);
            int result = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
    }

    public void deleteBook(Integer id) throws SQLException {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM \"Book\" where \"bookId\" = ?")) {
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
        }
    }

    public void editBook(Integer id, String title, String description, Integer year, Integer authorId) throws SQLException {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE \"Book\" set \"title\" = ?, \"description\" = ? ,\"year\" = ?, \"authorId\" = ? where \"bookId\" = ? ")) {
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.setInt(4, year);
            preparedStatement.setInt(5, authorId);
            preparedStatement.setInt(3, id);

            int result = preparedStatement.executeUpdate();
        }
    }
}
