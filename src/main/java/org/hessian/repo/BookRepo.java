package org.hessian.repo;

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
            try(PreparedStatement preStmt=con.prepareStatement("select * from \"Books\"")) {
                try(ResultSet result=preStmt.executeQuery()) {
                    while (result.next()) {
                        Long id = result.getLong("bookId");
                        String title,description;
                        Integer year;
                        Long authorId;
                        title = result.getString("title");
                        description = result.getString("description");
                        year = result.getInt("year");
                        authorId = result.getLong("authorId");
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
}
