package com.capstone.crdm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class QueryModel {

    private String query;

    private Integer page;

    private Integer size;

    private Map<String, Integer> sorts;

    public String getQuery() {
        if (this.query == null) {
            return "";
        }

        return this.query;
    }

}
