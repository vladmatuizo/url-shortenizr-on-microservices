package com.exposit.training.linksstatisticservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "link_statistic")
public class LinkStatistic {

    @Id
    private String shortenizrKey;
    private long linkFollowingCount;
    private LocalDateTime creationDate;
    private LocalDateTime lastFollowingDate;
    private String sourceLink;

    public LinkStatistic() {
    }

    public LinkStatistic(String shortenizrKey, long linkFollowingCount, LocalDateTime creationDate,
                         LocalDateTime lastFollowingDate, String sourceLink) {
        this.shortenizrKey = shortenizrKey;
        this.linkFollowingCount = linkFollowingCount;
        this.creationDate = creationDate;
        this.lastFollowingDate = lastFollowingDate;
        this.sourceLink = sourceLink;
    }

    public String getShortenizrKey() {
        return shortenizrKey;
    }

    public void setShortenizrKey(String shortenizrKey) {
        this.shortenizrKey = shortenizrKey;
    }

    public long getLinkFollowingCount() {
        return linkFollowingCount;
    }

    public void setLinkFollowingCount(long linkFollowingCount) {
        this.linkFollowingCount = linkFollowingCount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastFollowingDate() {
        return lastFollowingDate;
    }

    public void setLastFollowingDate(LocalDateTime lastFollowingDate) {
        this.lastFollowingDate = lastFollowingDate;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }
}
