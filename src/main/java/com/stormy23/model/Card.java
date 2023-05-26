package com.stormy23.model;

import jakarta.persistence.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    private String intronumber;

    @Column(name = "card_extern_number")
    private String externnumber;

    @Column(name = "card_date_intro")
    private Date dateintro;

    @Column(name = "card_date_extern")
    private Date dateextern;

    public Card() {

    }

    public Card(Document document, String intronumber) {
        this.document = document;
        this.intronumber = intronumber;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateWithoutTime;
        try {
            dateWithoutTime = sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        this.dateintro = dateWithoutTime;
    }


    public long getId() {
        return id;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getIntronumber() {
        return intronumber;
    }

    public void setIntronumber(String intronumber) {
        this.intronumber = intronumber;
    }

    public String getExternnumber() {
        return externnumber;
    }

    public void setExternnumber(String externnumber) {
        this.externnumber = externnumber;
    }

    public Date getDateintro() {
        return dateintro;
    }

    public void setDateintro(Date dateintro) {
        this.dateintro = dateintro;
    }

    public Date getDateextern() {
        return dateextern;
    }

    public void setDateextern(Date dateextern) {
        this.dateextern = dateextern;
    }
}
