package com.stormy23.model;

public class DocumentUploadForm {
    private String name;
    private String author;
    private String number;

    public DocumentUploadForm() {

    }

    public DocumentUploadForm(String name, String author, String number) {
        this.name = name;
        this.author = author;
        this.number = number;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
