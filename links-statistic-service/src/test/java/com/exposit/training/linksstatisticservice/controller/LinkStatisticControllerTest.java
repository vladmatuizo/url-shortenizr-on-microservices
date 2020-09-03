package com.exposit.training.linksstatisticservice.controller;

import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import com.exposit.training.linksstatisticservice.service.LinkStatisticService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@WebMvcTest(LinkStatisticController.class)
@ImportAutoConfiguration({RefreshAutoConfiguration.class})
public class LinkStatisticControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LinkStatisticService statisticService;

    @Test
    public void updateStatisticShouldReturnBadRequestStatus() throws Exception {
        String body = "{\n" +
                "\t\"key\":\"UFVwZ73gc\",\n" +
                "\t\"sourceUrl\":\"https://devcolibri.com/\"\n" +
                "\t\n" +
                "}";

        BDDMockito.given(this.statisticService.updateStatistic("https://devcolibri.com/"))
                .willReturn(false);

        this.mvc.perform(
                MockMvcRequestBuilders.post("/statistic")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void updateStatisticShouldReturnOkStatus() throws Exception {
        String body = "{\n" +
                "\t\"key\":\"UFVwZ73gc\",\n" +
                "\t\"sourceUrl\":\"https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80" +
                "-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/\"\n" +
                "\t\n" +
                "}";

        BDDMockito.given(this.statisticService.updateStatistic("https://devcolibri.com/md5" +
                "-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0" +
                "%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/"))
                .willReturn(true);

        this.mvc.perform(
                MockMvcRequestBuilders.post("/statistic")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void addStatisticShouldReturnCreatedStatusAndLocationHeader() throws Exception {
        String expectedLocation = "http://localhost:8100/statistic/UFVwZ73gc";
        String body = "{\n" +
                "\t\"key\":\"UFVwZ73gc\",\n" +
                "\t\"sourceUrl\":\"https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1%81%D0" +
                "%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/\"\n" +
                "\t\n" +
                "}";

        BDDMockito.given(this.statisticService.addStatistic("https://devcolibri.com" +
                "/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%" +
                "B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/", "UFVwZ73gc"))
                .willReturn(true);

        this.mvc.perform(
                MockMvcRequestBuilders.put("/statistic")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedLocation));
    }

    @Test
    public void addStatisticShouldReturnBadRequestStatus() throws Exception {
        String body = "{\n" +
                "\t\"key\":\"UFVwZ73gc\",\n" +
                "\t\"sourceUrl\":\"https://goo.gl/123zxc\"\n" +
                "\t\n" +
                "}";

        BDDMockito.given(this.statisticService.addStatistic("https://goo.gl/123zxc", "UFVwZ73gc"))
                .willReturn(false);

        this.mvc.perform(
                MockMvcRequestBuilders.put("/statistic")
                        .characterEncoding("UTF-8")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void getStatisticShouldReturnStatistic() throws Exception {
        String expected = "{\n" +
                "    \"shortenizrKey\": \"UFVwZ73gc\",\n" +
                "    \"linkFollowingCount\": 0,\n" +
                "    \"creationDate\": \"2020-07-13T12:51:38.668769\",\n" +
                "    \"lastFollowingDate\": \"2020-07-13T12:51:38.668769\",\n" +
                "    \"sourceLink\": \"https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1" +
                "%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/\"\n" +
                "}";
        String key = "UFVwZ73gc";

        BDDMockito.given(this.statisticService.getStatistic(key))
                .willReturn(
                        new LinkStatistic(key, 0,
                                LocalDateTime.parse("2020-07-13T12:51:38.668769"),
                                LocalDateTime.parse("2020-07-13T12:51:38.668769"),
                                "https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1" +
                                        "%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/")
                );

        this.mvc.perform(MockMvcRequestBuilders.get(String.format("/statistic/%s", key)).accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }

    @Test
    public void getAllStatisticShouldReturnAllStatistic() throws Exception {
        String expected = "[{\n" +
                "    \"shortenizrKey\": \"UFVwZ73gc\",\n" +
                "    \"linkFollowingCount\": 0,\n" +
                "    \"creationDate\": \"2020-07-13T12:51:38.668769\",\n" +
                "    \"lastFollowingDate\": \"2020-07-13T12:51:38.668769\",\n" +
                "    \"sourceLink\": \"https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1" +
                "%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/\"\n" +
                "},\n" +
                "{\n" +
                "    \"shortenizrKey\": \"PnVw6v3g1\",\n" +
                "    \"linkFollowingCount\": 0,\n" +
                "    \"creationDate\": \"2020-07-13T12:51:22.1\",\n" +
                "    \"lastFollowingDate\": \"2020-07-13T12:51:30.1\",\n" +
                "    \"sourceLink\": \"https://google.com/\"\n" +
                "}]";

        ArrayList<LinkStatistic> mockResults = new ArrayList<>();
        mockResults.add(new LinkStatistic("UFVwZ73gc", 0,
                LocalDateTime.parse("2020-07-13T12:51:38.668769"),
                LocalDateTime.parse("2020-07-13T12:51:38.668769"),
                "https://devcolibri.com/md5-%D0%BF%D1%80%D0%B8%D0%BC%D0%B5%D1%80-%D0%B8%D1" +
                        "%81%D0%BF%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D0%BD%D0%B8%D1%8F-%D0%B2-java/"));
        mockResults.add(new LinkStatistic("PnVw6v3g1", 0,
                LocalDateTime.parse("2020-07-13T12:51:22.1"),
                LocalDateTime.parse("2020-07-13T12:51:30.1"),
                "https://google.com/"));
        BDDMockito.given(this.statisticService.getAllStatistic())
                .willReturn(mockResults);

        this.mvc.perform(MockMvcRequestBuilders.get("/statistic").accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(expected));
    }
}
