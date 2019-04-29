package com.recruittask.githubuserstatistics.Entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRepository{

    private String language;
    private Integer size;

    public UserRepository(){

    }
    public UserRepository(String language, Integer size) {
        this.language = language;
        this.size = size;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
