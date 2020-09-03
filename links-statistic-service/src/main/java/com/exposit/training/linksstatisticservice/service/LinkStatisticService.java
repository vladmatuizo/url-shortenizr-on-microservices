package com.exposit.training.linksstatisticservice.service;

import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import com.exposit.training.linksstatisticservice.repository.LinkStatisticRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LinkStatisticService {

    private final LinkStatisticRepository repo;

    @Autowired
    public LinkStatisticService(LinkStatisticRepository repo) {
        this.repo = repo;
    }

    public boolean addStatistic(String sourceUrl, String shortenizrKey) {
        if (repo.findById(shortenizrKey).isPresent()) return false;
        repo.save(new LinkStatistic(shortenizrKey, 0,
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                sourceUrl));
        return true;
    }

    public boolean updateStatistic(String sourceUrl) {
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
        return repo.findById(key).orElseThrow(EntityNotFoundException::new);
    }

    public List<LinkStatistic> getAllStatistic() {
        return repo.findAll();
    }
}
