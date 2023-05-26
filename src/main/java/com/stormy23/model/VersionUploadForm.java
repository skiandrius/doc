package com.stormy23.model;

public class VersionUploadForm {
    private String author;

    private String documentid;

    public VersionUploadForm() {

    }

    public VersionUploadForm(String author, String documentid) {
        this.author = author;
        this.documentid = documentid;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }
}
