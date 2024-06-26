package com.stormy23.model;

import jakarta.persistence.*;

import java.io.File;

@Entity
public class Version {
    @Id
    @Column(name = "version_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "version_document_id")
    private Document document;

    @Column(name = "version_author")
    private String author;

    @Column(name = "version_content")

    private byte[] content;

    @Column(name = "version_number")
    private long number;

    public Version() {

    }

    public Version(Document document, String author, byte[] content, long number) {
        this.document = document;
        this.author = author;
        this.content = content;
        this.number = number;
    }

    public long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public Document getDocument() {
        return document;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public long getNumber() {
        return number;
    }
}

