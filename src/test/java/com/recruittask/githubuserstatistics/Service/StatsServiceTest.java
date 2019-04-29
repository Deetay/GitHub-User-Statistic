package com.recruittask.githubuserstatistics.Service;

import com.recruittask.githubuserstatistics.Entity.UserRepository;
import com.recruittask.githubuserstatistics.Entity.UserStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StatsServiceTest {

    @Mock
    private AuthService authService;

    private StatsService statsService;

    @BeforeEach
    void setUp(){
        statsService = new StatsService(authService);
    }

    @Test
    void makeStatsWhenServiceRespondedCorrectly() {
        //given
        UserRepository repo1 = new UserRepository("Java", 100);
        UserRepository repo2 = new UserRepository("C#", 50);
        UserRepository repo3 = new UserRepository("Java", 1200);
        UserRepository repo4 = new UserRepository("HTML", 200);

        List<UserRepository> list = new ArrayList<UserRepository>(Arrays.asList(repo1, repo2, repo3, repo4));
        //when
        UserStats stats = statsService.makeStats(list);
        //then
        assertNotNull(stats);
        assertNotNull(stats.getLanguages());
        assertNotNull(stats.getRepositoriesCount());
        assertEquals(3, stats.getLanguages().size());
        assertEquals(Integer.valueOf(4), stats.getRepositoriesCount());
        assertEquals(Integer.valueOf(1300), stats.getLanguages().get("Java"));
        assertEquals(Integer.valueOf(50), stats.getLanguages().get("C#"));
        assertEquals(Integer.valueOf(200), stats.getLanguages().get("HTML"));

    }
    @Test
    void makeStatsWhenServiceRespondedWithEmptyJson(){
        //given
        List<UserRepository> list = new ArrayList<UserRepository>();

        //when
        UserStats stats = statsService.makeStats(list);
        //then
        assertNotNull(stats);
        assertNotNull(stats.getLanguages());
        assertNotNull(stats.getRepositoriesCount());
        assertEquals(true, stats.getLanguages().isEmpty());
        assertEquals(Integer.valueOf(0), stats.getRepositoriesCount());
    }

    @Test
    void getUserStats() {
    }
}