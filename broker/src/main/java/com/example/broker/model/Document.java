package com.example.broker.model;

public class Document{
    private String document_number;
    private String document_revision;
    private String url;

    public Document() {
    }

    public String getDocument_number() {
        return document_number;
    }

    public void setDocument_number(String document_number) {
        this.document_number = document_number;
    }

    public String getDocument_revision() {
        return document_revision;
    }

    public void setDocument_revision(String document_revision) {
        this.document_revision = document_revision;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
