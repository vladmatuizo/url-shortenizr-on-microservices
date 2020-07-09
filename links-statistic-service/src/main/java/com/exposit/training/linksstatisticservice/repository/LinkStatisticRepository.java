package com.exposit.training.linksstatisticservice.repository;

import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LinkStatisticRepository extends JpaRepository<LinkStatistic, String> {

    LinkStatistic findBySourceLink(String sourceLink);
}
