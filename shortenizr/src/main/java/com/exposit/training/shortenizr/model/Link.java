package com.exposit.training.shortenizr.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "link")
public class Link {

    @Id
    private String key;
    private String sourceUrl;

    public Link() {
    }

    public Link(String key, String sourceUrl) {
        this.key = key;
        this.sourceUrl = sourceUrl;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSourceUrl() {
        return sourceUrl;
    }

    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }
}
