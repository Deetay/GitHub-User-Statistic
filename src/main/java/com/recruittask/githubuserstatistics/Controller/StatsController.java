package com.recruittask.githubuserstatistics.Controller;


import com.recruittask.githubuserstatistics.Entity.UserStats;
import com.recruittask.githubuserstatistics.Service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@RestController
@CrossOrigin(value = {"*"})
public class StatsController {

    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService){
        this.statsService = statsService;
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<String> httpError(HttpClientErrorException ex){
        return new ResponseEntity<>(ex.getMessage(), ex.getStatusCode());
    }


    @GetMapping(value = "/statistics/{user}")
    public ResponseEntity getPublicInfo(@PathVariable("user") String user) {

        UserStats stats =statsService.getUserStats(user);

        return ResponseEntity.ok(stats);
    }
}
