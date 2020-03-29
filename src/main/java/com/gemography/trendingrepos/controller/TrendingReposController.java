package com.gemography.trendingrepos.controller;

import com.gemography.trendingrepos.models.Item;
import com.gemography.trendingrepos.models.ReposAndCount;
import com.gemography.trendingrepos.service.TrendingReposService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Endpoint pour l'API trending Repositories
 */
@RestController
@RequestMapping("/repos")
public class TrendingReposController {

    @Autowired
    private TrendingReposService trendingReposService;

    /**
     * Endpoint pour intialiser la base de données avec les données d'API
     * @param creationDate
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public List<Item> create(@RequestParam("date") String creationDate) throws Exception {
        return trendingReposService.initItems(creationDate);
    }

    /**
     * page d'index
     * @return
     */
    public String index() {
        return "Welcome to the trending Repos API !!!";
    }

    /**
     * Endpoint pour l'affichage des contours des répertoires par language
     * @return
     */
    @RequestMapping("/languages/count")
    public Map<String, Long> getLanguagesCount() {
        return trendingReposService.getLanguagesCount();
    }

    /**
     * Endpoint pour l'affichage des répértoires par language
     * @return
     */
    @RequestMapping("/languages")
    public Map<String, List<Item>> getLanguageRepos() {
        return trendingReposService.getLanguageRepos();
    }

    /**
     * Endpoint pour l'affichage des coteurs ainsi que la liste des répértoires par language
     * @return
     */
    @RequestMapping("/languages-and-count")
    public Map<String, ReposAndCount> getReposAndTheirCountsPerLanguage() {
        return trendingReposService.getReposAndTheirCountsPerLanguage();
    }

}
