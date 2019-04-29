package com.recruittask.githubuserstatistics.Service;


import com.recruittask.githubuserstatistics.Entity.UserRepository;
import com.recruittask.githubuserstatistics.Entity.UserStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class StatsService {

    private AuthService authService;
    private static final String GITHUB_API_USERS_REPOS = "https://api.github.com/users/{user}/repos?per_page=100";

    @Autowired
    public StatsService(AuthService authService){
        this.authService = authService;
    }

    public UserStats makeStats(List<UserRepository> userRepositories){
        UserStats stats = new UserStats();
        Map<String, Integer> languages = new HashMap<>();
        for(UserRepository i : userRepositories){
            String name = i.getLanguage();
            int size = i.getSize();
            if(languages.containsKey(name)){
                size += languages.get(name);
            }
            if(name == null){
                name = "Not labeled";
            }
            languages.put(name, size);

        }
        stats.setRepositoriesCount(userRepositories.size());
        stats.setLanguages(languages);
        return stats;
    }

    public UserStats getUserStats(String user){

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders requestHeader = authService.setRequestHeader();
        HttpEntity entity = new HttpEntity(requestHeader);

        ResponseEntity<UserRepository[]> responseEntity =
                restTemplate.exchange(GITHUB_API_USERS_REPOS,
                        HttpMethod.GET, entity, UserRepository[].class, user);
        List<UserRepository> repos = new ArrayList<>(Arrays.asList(responseEntity.getBody()));
        HttpHeaders headers = responseEntity.getHeaders();
        String next = headers.getFirst("Link");

        if(next != null){
            int page = 2;
            while (next.contains("rel=\"next\"")){
                responseEntity = restTemplate.exchange(GITHUB_API_USERS_REPOS+"&page="+page,
                        HttpMethod.GET, entity, UserRepository[].class, user);
                repos.addAll(new ArrayList<>(Arrays.asList(responseEntity.getBody())));
                headers = responseEntity.getHeaders();
                next = headers.getFirst("Link");
                page++;
            }
        }

        return makeStats(repos);
    }
}
