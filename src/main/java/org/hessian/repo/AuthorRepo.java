package org.hessian.repo;

import org.hessian.model.Author;
import org.hessian.model.Book;
import sun.security.util.AuthResources;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorRepo {

    private JDBC utils;


    public AuthorRepo() {
        utils = new JDBC();
    }

    public Integer getNextId() throws SQLException {
        Connection con=utils.getConnection();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT MAX(A.\"authorId\") as maxId FROM \"Author\" A  ")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt("maxId") + 1;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getAllAuthors() AuthorRepo "+e);
            throw e;
        }
        return 0;
    }


    public List<Author> getAllAuthors() throws SQLException {
        Connection con=utils.getConnection();
        List<Author> authors=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Author\"")) {
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("authorId");
                    String name;
                    Integer age;

                    name = result.getString("name");

                    age = result.getInt("age");

                    Author author=new Author(id,name, age);
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getAllAuthors() AuthorRepo "+e);
            throw e;
        }
        return authors;
    }

    public Author getAuthorById(Integer id) throws SQLException {
        Connection con=utils.getConnection();
        Author authorDefault = new Author();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Author\" where \"authorId\" = ?" )) {
            preStmt.setInt(1, id);
            try(ResultSet result=preStmt.executeQuery()) {
                if (result.next()) {
                    String name;
                    Integer age;
                    name = result.getString("name");
                    age = result.getInt("age");
                    Author author=new Author(id,name, age);
                    return author;
                }
            }
        }
        return authorDefault;
    }

    public List<Author> getAuthorsBySearch(String keyword) throws SQLException {
        Connection con=utils.getConnection();
        List<Author> authors=new ArrayList<>();
        try(PreparedStatement preStmt=con.prepareStatement("select * from \"Author\" where name like ?" )) {
            preStmt.setString(1, "%" + keyword + "%");
            try(ResultSet result=preStmt.executeQuery()) {
                while (result.next()) {
                    int id = result.getInt("authorId");
                    String name;
                    Integer age;

                    name = result.getString("name");

                    age = result.getInt("age");

                    Author author=new Author(id,name, age);
                    authors.add(author);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getAllAuthors() AuthorRepo "+e);
            throw e;
        }
        return authors;
    }

    public void addAuthor(String name, Integer age) throws SQLException {
        Connection con = utils.getConnection();

        int nextId = getNextId();

        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO \"Author\" VALUES (?,?,?)")) {
            preparedStatement.setInt(1, nextId);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3, age);
            int result = preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            throw e;
        }
    }

    public void deleteAuthor(Integer id) throws SQLException {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM \"Author\" where \"authorId\" = ?")) {
            preparedStatement.setInt(1, id);

            int result = preparedStatement.executeUpdate();
        }
    }

    public void editAuthor(Integer id, String name, Integer age) throws SQLException {
        Connection con = utils.getConnection();

        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE \"Author\" set \"name\" = ?, \"age\" = ? where \"authorId\" = ? ")) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setInt(3, id);

            int result = preparedStatement.executeUpdate();
        }
    }
}
