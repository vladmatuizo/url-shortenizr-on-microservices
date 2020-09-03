package com.exposit.training.shortenizr.controller;

import com.exposit.training.shortenizr.model.Link;
import com.exposit.training.shortenizr.proxy.LinkStatisticExchangeServiceProxy;
import com.exposit.training.shortenizr.repository.LinkRepository;
import com.exposit.training.shortenizr.service.UrlCreatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

@RunWith(SpringRunner.class)
@WebMvcTest(UrlController.class)
public class UrlControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LinkRepository repo;

    @MockBean
    private UrlCreatorService urlCreatorService;

    @MockBean
    private LinkStatisticExchangeServiceProxy proxy;

    @Test
    public void createUrlShouldReturnCreatedStatusAndLocation() throws Exception {
        String expectedLocation = "http://localhost:8000/UFVwZ73gc";


        String sourceUrl = "https://google.com";
        Link link = new Link("UFVwZ73gc", sourceUrl);

        BDDMockito.given(urlCreatorService.create(sourceUrl))
                .willReturn(link);
        BDDMockito.given(repo.save(link)).willReturn(link);
        BDDMockito.given(proxy.addStatistic(link)).willReturn(null);

        this.mvc.perform(
                MockMvcRequestBuilders.put("/createUrl")
                        .characterEncoding("UTF-8")
                        .content(sourceUrl)
                        .contentType(MediaType.TEXT_PLAIN))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedLocation));
    }

    @Test
    public void redirectShouldReturnMovedStatus() throws Exception {
        String key = "UFVwZ73gc";
        Link link = new Link(key, "https://google.com");

        BDDMockito.given(repo.findById(key))
                .willReturn(Optional.of(link));
        BDDMockito.given(proxy.updateStatistic(link)).willReturn(null);

        this.mvc.perform(MockMvcRequestBuilders.get("/UFVwZ73gc"))
                .andExpect(MockMvcResultMatchers.status().isMovedPermanently())
                .andExpect(MockMvcResultMatchers.header().string("Location", "https://google.com"));
    }

    @Test
    public void redirectShouldReturnBadRequestStatus() throws Exception {
        String key = "UFVwZ73gc";

        BDDMockito.given(repo.findById(key))
                .willReturn(Optional.empty());

        this.mvc.perform(MockMvcRequestBuilders.get("/UFVwZ73gc"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }
}
