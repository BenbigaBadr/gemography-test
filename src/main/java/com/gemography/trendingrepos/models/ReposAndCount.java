package com.gemography.trendingrepos.models;

import java.io.Serializable;
import java.util.List;

/**
 * classe utilitaire pour regrouper les conteurs des répértoires ainsi que ces dernieurs dans un seul objet
 */
public class ReposAndCount implements Serializable {

    private Integer count;

    private List<Item> repos;

    public ReposAndCount(List<Item> repos)
    {
        this.repos = repos;
        if(this.repos != null && !this.repos.isEmpty())
            this.count = this.repos.size();
    }

    public List<Item> getRepos() {
        return repos;
    }

    public void setRepos(List<Item> repos) {
        this.repos = repos;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
