package com.exposit.training.linksstatisticservice.service;


import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import com.exposit.training.linksstatisticservice.repository.LinkStatisticRepository;
import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LinkStatisticServiceTest {

    @MockBean
    private LinkStatisticRepository repo;

    private LinkStatisticService service;

    @Before
    public void initService() {
        service = new LinkStatisticService(repo);
    }

    @Test
    public void addStatisticShouldReturnSuccess() {
        String key = "UFVwZ73gc";
        String sourceLink = "https://google.com";
        LinkStatistic linkStatistic = new LinkStatistic(key, 0,
                LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS),
                sourceLink);

        BDDMockito.given(repo.findById(key)).willReturn(Optional.empty());
        BDDMockito.given(repo.save(linkStatistic)).willReturn(linkStatistic);

        linkStatistic.setCreationDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        linkStatistic.setLastFollowingDate(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        boolean actual = service.addStatistic(sourceLink, key);

        Assert.assertTrue(actual);
    }

    @Test
    public void addStatisticShouldReturnFail() {
        String key = "UFVwZ73gc";
        String sourceLink = "https://google.com";

        BDDMockito.given(repo.findById(key))
                .willReturn(Optional.of(new LinkStatistic(key, 0, LocalDateTime.now(),
                        LocalDateTime.now(), sourceLink)));

        boolean actual = service.addStatistic(sourceLink, key);

        Assert.assertFalse(actual);
    }

    @Test
    public void getAllStatisticShouldReturnStatisticList() {

        ArrayList<LinkStatistic> mockResults = new ArrayList<>();
        mockResults.add(new LinkStatistic("UFVwZ73gc", 0,
                LocalDateTime.now(), LocalDateTime.now(), "https://google.com"));
        mockResults.add(new LinkStatistic("PnVw6v3g1", 0,
                LocalDateTime.now(), LocalDateTime.now(), "https://drive.google.com"));
        BDDMockito.given(repo.findAll()).willReturn(mockResults);

        List<LinkStatistic> actual = service.getAllStatistic();

        Assertions.assertThat(actual).size().isEqualTo(2);
        Assertions.assertThat(actual.get(0).getSourceLink()).isEqualTo("https://google.com");
        Assertions.assertThat(actual.get(0).getShortenizrKey()).isEqualTo("UFVwZ73gc");
        Assertions.assertThat(actual.get(1).getSourceLink()).isEqualTo("https://drive.google.com");
        Assertions.assertThat(actual.get(1).getShortenizrKey()).isEqualTo("PnVw6v3g1");
    }

    @Test
    public void getStatisticShouldReturnStatistic() {
        String key = "UFVwZ73gc";

        BDDMockito.given(repo.findById(key))
                .willReturn(Optional.of(new LinkStatistic(key, 0, LocalDateTime.now(),
                        LocalDateTime.now(), "https://google.com")));

        LinkStatistic actual = service.getStatistic(key);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getShortenizrKey()).isEqualTo(key);
        Assertions.assertThat(actual.getSourceLink()).isEqualTo("https://google.com");
    }

    @Test
    public void getStatisticShouldThrowNoSuchElementException() {
        String key = "UFVwZ73gc";

        BDDMockito.given(repo.findById(key))
                .willReturn(Optional.empty());
        try {
            service.getStatistic(key);
        } catch (Exception e) {
            Assertions.assertThat(e).isInstanceOf(EntityNotFoundException.class);
        }

    }

    @Test
    public void updateStatisticShouldReturnSuccess() {
        String sourceUrl = "https://google.com";
        LinkStatistic linkStatistic = new LinkStatistic("UFVwZ73gc", 0, LocalDateTime.now(),
                LocalDateTime.now(), sourceUrl);

        BDDMockito.given(repo.findBySourceLink(sourceUrl)).willReturn(linkStatistic);
        BDDMockito.given(repo.save(linkStatistic)).willReturn(linkStatistic);

        boolean actual = service.updateStatistic(sourceUrl);

        Assert.assertTrue(actual);
    }

    @Test
    public void updateStatisticShouldReturnFail() {
        String sourceUrl = "https://bad.url";
        LinkStatistic linkStatistic = new LinkStatistic("UFVwZ73gc", 0, LocalDateTime.now(),
                LocalDateTime.now(), sourceUrl);

        BDDMockito.given(repo.findBySourceLink(sourceUrl)).willReturn(null);
        BDDMockito.given(repo.save(linkStatistic)).willReturn(linkStatistic);

        boolean actual = service.updateStatistic(sourceUrl);

        Assert.assertFalse(actual);
    }
}
