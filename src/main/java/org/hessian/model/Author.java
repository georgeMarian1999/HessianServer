package org.hessian.model;

import java.io.Serializable;

public class Author implements Serializable {

    private Integer authorId;
    private String name;
    private Integer age;

    public Author() {
    }

    public Author(Integer authorId, String name, Integer age) {
        this.authorId = authorId;
        this.name = name;

        this.age = age;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
