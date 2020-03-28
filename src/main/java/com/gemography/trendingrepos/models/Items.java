package com.gemography.trendingrepos.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.math.BigDecimal;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Items {

    private List<Item> items;

    public List<Item>getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
