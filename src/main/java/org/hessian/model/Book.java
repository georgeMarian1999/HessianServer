package org.hessian.model;


import java.io.Serializable;

public class Book implements Serializable {

    private Long bookId;
    private String title;
    private String description;
    private Integer year;
    private Long authorId;

    public Book() {

    }

    public Book(Long bookId, String title, String description, Integer year, Long authorId) {
        this.bookId = bookId;
        this.title = title;
        this.description = description;
        this.year = year;
        this.authorId = authorId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
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
