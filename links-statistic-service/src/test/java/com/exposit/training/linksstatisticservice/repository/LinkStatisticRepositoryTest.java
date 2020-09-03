package com.exposit.training.linksstatisticservice.repository;


import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@ImportAutoConfiguration({RefreshAutoConfiguration.class})
public class LinkStatisticRepositoryTest {

    @Autowired
    private LinkStatisticRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void findBySourceLinkShouldReturnLinkStatistic() {
        LocalDateTime now = LocalDateTime.now();
        entityManager.persist(new LinkStatistic("UFVwZ73gc", 0, now,
                now, "https://google.com"));

        LinkStatistic actual = repo.findBySourceLink("https://google.com");

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getShortenizrKey()).isEqualTo("UFVwZ73gc");
        Assertions.assertThat(actual.getLinkFollowingCount()).isEqualTo(0);
        Assertions.assertThat(actual.getCreationDate()).isEqualTo(now);
        Assertions.assertThat(actual.getLastFollowingDate()).isEqualTo(now);
    }

    @Test
    public void findBySourceLinkShouldReturnNull() {

        LinkStatistic actual = repo.findBySourceLink("https://google.com");

        Assertions.assertThat(actual).isNull();
    }
}
