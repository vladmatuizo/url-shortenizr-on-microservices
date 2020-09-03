package com.exposit.training.shortenizr.service;

import com.exposit.training.shortenizr.model.Link;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@RunWith(SpringRunner.class)
public class UrlCreatorServiceTest {

    @MockBean
    private MessageDigest md5;

    private UrlCreatorService urlCreatorService;

    @Before
    public void initService() {
        urlCreatorService = new UrlCreatorService(md5);
    }

    @Test
    public void createShouldReturnLink() {
        String sourceUrl = "https://google.com";

        BDDMockito.given(md5.digest(sourceUrl.getBytes(StandardCharsets.UTF_8)))
                .willReturn(new byte[]{85,0,70,0,86,0,119,0,90,0,55,0,51,0,103,0,99});

        Link actual = urlCreatorService.create(sourceUrl);

        Assertions.assertThat(actual).isNotNull();
        Assertions.assertThat(actual.getKey()).isEqualTo("UFVwZ73gc");
        Assertions.assertThat(actual.getSourceUrl()).isEqualTo("https://google.com");
    }
}
