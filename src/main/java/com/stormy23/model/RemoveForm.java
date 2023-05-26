package com.stormy23.model;

public class RemoveForm {
    private String documentid;

    private String externnumber;

    public RemoveForm() {

    }

    public RemoveForm(String documentid, String externnumber) {
        this.documentid = documentid;
        this.externnumber = externnumber;
    }

    public String getDocumentid() {
        return documentid;
    }

    public void setDocumentid(String documentid) {
        this.documentid = documentid;
    }

    public String getExternnumber() {
        return externnumber;
    }

    public void setExternnumber(String externnumber) {
        this.externnumber = externnumber;
    }
}
