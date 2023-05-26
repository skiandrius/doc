package com.stormy23.model;

public class SearchForm {
    private String docname;

    private String intronumber;

    public SearchForm() {

    }

    public SearchForm(String docname, String intronumber) {
        this.docname = docname;
        this.intronumber = intronumber;
    }

    public String getDocname() {
        return docname;
    }

    public void setDocname(String docname) {
        this.docname = docname;
    }

    public String getIntronumber() {
        return intronumber;
    }

    public void setIntronumber(String intronumber) {
        this.intronumber = intronumber;
    }

}
