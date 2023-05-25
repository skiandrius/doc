package com.stormy23.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Card {
    @Id
    @Column(name = "card_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "card_document_id")
    private Document document;

    @Column(name = "card_intro_number")
    private String intro_number;

    @Column(name = "card_extern_number")
    private String extern_number;

    @Column(name = "card_date_intro")
    private Date date_intro;

    @Column(name = "card_date_extern")
    private Date date_extern;

    public Card() {

    }

    public Card(Document document, String intro_number) {
        this.document = document;
        this.intro_number = intro_number;
        this.date_intro = new Date();
    }

    public Document getDocument() {
        return document;
    }

    public long getId() {
        return id;
    }

    public String getIntro_number() {
        return intro_number;
    }

    public String getExtern_number() {
        return extern_number;
    }

    public void setExtern_number(String extern_number) {
        this.extern_number = extern_number;
    }

    public Date getDate_intro() {
        return date_intro;
    }

    public Date getDate_extern() {
        return date_extern;
    }

    public void setDate_extern(Date date_extern) {
        this.date_extern = date_extern;
    }
}
