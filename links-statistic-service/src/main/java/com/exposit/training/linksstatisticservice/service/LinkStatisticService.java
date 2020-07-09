package com.exposit.training.linksstatisticservice.service;

import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import com.exposit.training.linksstatisticservice.repository.LinkStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class LinkStatisticService {

    @Autowired
    private LinkStatisticRepository repo;

    public boolean addStatistic(String sourceUrl, String shortenizrKey) {
        if (repo.findById(shortenizrKey).isEmpty()){
            LocalDateTime now = LocalDateTime.now();
            LinkStatistic res = repo.save(new LinkStatistic(shortenizrKey, 0, now, now, sourceUrl));
            return res != null;
        }
        return false;
    }

    public boolean updateStatistic(String sourceUrl){
        LinkStatistic linkStatistic = repo.findBySourceLink(sourceUrl);
        if (linkStatistic != null) {
            linkStatistic.setLastFollowingDate(LocalDateTime.now());
            linkStatistic.setLinkFollowingCount(linkStatistic.getLinkFollowingCount() + 1);
            repo.save(linkStatistic);
            return true;
        }
        return false;
    }

    public LinkStatistic getStatistic(String key) {
        return repo.findById(key).orElseThrow();
    }
}
