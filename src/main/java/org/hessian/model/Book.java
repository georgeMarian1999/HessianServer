package org.hessian.model;


import java.io.Serializable;

public class Book implements Serializable {

    private Integer bookId;
    private String title;
    private String description;
    private Integer year;
    private Integer authorId;

    public Book() {

    }

    public Book(Integer bookId, String title, String description, Integer year, Integer authorId) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.year = year;
        this.authorId = authorId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", year=" + year +
                ", authorId=" + authorId +
                '}';
    }
}
