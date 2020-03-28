package com.gemography.trendingrepos.controller;

import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.ReposAndCount;
import com.gemography.trendingrepos.service.TrendingReposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/repos")
public class TrendingReposController {

    @Autowired
    private TrendingReposService trendingReposService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Item> create(@RequestParam("date") String creationDate) throws Exception {
        return trendingReposService.initItems(creationDate);
    }

    public String index() {
        return "Welcome to the trending Repos API !!!";
    }

    @RequestMapping("/languages/count")
    public Map<String, Long> getLanguagesCount() {
        return trendingReposService.getLanguagesCount();
    }

    @RequestMapping("/languages")
    public Map<String, List<Item>> getLanguageRepos() {
        return trendingReposService.getLanguageRepos();
    }

    @RequestMapping("/languages-and-count")
    public Map<String, ReposAndCount> getReposAndTheirCountsPerLanguage() {
        return trendingReposService.getReposAndTheirCountsPerLanguage();
    }

}
