package com.stormy23.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    @Column(name = "document_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "document_name")
    private String name;

    @Column(name = "document_author")
    private String author;

    @Column(name = "document_is_extern")
    private boolean extern;

    public Document() {

    }

    public Document(String name, String author) {
        this.name = name;
        this.author = author;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public boolean getExtern() {
        return extern;
    }

    public void setExtern(boolean extern) {
        this.extern = extern;
    }
}
