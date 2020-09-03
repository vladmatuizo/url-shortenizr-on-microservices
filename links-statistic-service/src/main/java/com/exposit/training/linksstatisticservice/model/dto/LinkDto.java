package com.exposit.training.linksstatisticservice.model.dto;

public class LinkDto {

    private String key;
    private String sourceUrl;

    public LinkDto() {
    }

    public LinkDto(String key, String sourceUrl) {
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

    @Override
    public String toString() {
        return "LinkDto{" +
                "key='" + key + '\'' +
                ", sourceUrl='" + sourceUrl + '\'' +
                '}';
    }
}
