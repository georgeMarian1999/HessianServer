package org.hessian;

import org.hessian.model.Author;
import org.hessian.repo.AuthorRepo;
import org.hessian.repo.JDBC;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        AuthorRepo authorRepo = new AuthorRepo();


        System.out.println(authorRepo.getAllAuthors());

    }
}