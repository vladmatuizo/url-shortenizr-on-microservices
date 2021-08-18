package com.exposit.training.shortenizr.controller;

import com.exposit.training.shortenizr.model.Link;
import com.exposit.training.shortenizr.proxy.LinkStatisticExchangeServiceProxy;
import com.exposit.training.shortenizr.repository.LinkRepository;
import com.exposit.training.shortenizr.service.UrlCreatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.Optional;

@RestController
public class UrlController {

    private static final String HEADER_NAME = "Location";

    private final UrlCreatorService urlCreatorService;
    private final LinkRepository linkRepository;
    private final LinkStatisticExchangeServiceProxy proxy;

    @Autowired
    public UrlController(UrlCreatorService urlCreatorService, LinkRepository linkRepository, LinkStatisticExchangeServiceProxy proxy) {
        this.urlCreatorService = urlCreatorService;
        this.linkRepository = linkRepository;
        this.proxy = proxy;
    }

    @CrossOrigin
    @PutMapping("/createUrl")
    public ResponseEntity<String> createUrl(@RequestBody String url) {
        Link resLink = urlCreatorService.create(url);
        linkRepository.save(resLink);

        //With RestTemplate
        //new RestTemplate().put("http://localhost:8100/statistic", resLink);
        //With Feign proxy
        proxy.addStatistic(resLink);

        final String link = String.format("http://localhost:8000/%s", resLink.getKey());
        return ResponseEntity.created(URI.create(link))
                .body(link);
    }

    @CrossOrigin(origins = "*", methods = RequestMethod.GET)
    @GetMapping("/{key}")
    public ResponseEntity<?> redirect(@PathVariable("key") String key) {
        Optional<Link> linkOpt = linkRepository.findById(key);
        if (linkOpt.isPresent()) {
            Link link = linkOpt.get();
            //With RestTemplate
            //new RestTemplate().postForLocation("http://localhost:8100/statistic", link);
            //With Feign proxy
            proxy.updateStatistic(link);

            String location = link.getSourceUrl();
            final URI uri = URI.create(location);
            if (!uri.isAbsolute()) {
                location = "https://".concat(location);
            }
            return ResponseEntity.status(HttpStatus.MOVED_PERMANENTLY)
                    .header(HEADER_NAME, location)
                    .build();
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
