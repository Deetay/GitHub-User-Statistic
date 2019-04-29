package com.recruittask.githubuserstatistics.Entity;

import java.util.Map;

public class UserStats {

    private Integer repositoriesCount;
    private Map<String, Integer> languages;

    public Integer getRepositoriesCount() {
        return repositoriesCount;
    }

    public void setRepositoriesCount(Integer repositoriesCount) {
        this.repositoriesCount = repositoriesCount;
    }

    public Map<String, Integer> getLanguages() {
        return languages;
    }

    public void setLanguages(Map<String, Integer> languages) {
        this.languages = languages;
    }
}
