package com.example.maogai.sqliteUtil;

public class SqlPage {
    private String chapter;
    private String name;
    private String url;

    public SqlPage(String chapter, String name, String url) {
        this.chapter = chapter;
        this.name = name;
        this.url = url;
    }

    public SqlPage(){};

    public String getChapter() {
        return chapter;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
