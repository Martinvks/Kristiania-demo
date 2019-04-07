package com.example.kristianiademo.model;

public class Repo {
    private Integer id;
    private String name;
    private String html_url;

    public Repo(Integer id, String name, String html_url) {
        this.id = id;
        this.name = name;
        this.html_url = html_url;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml_url() {
        return html_url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }
}
