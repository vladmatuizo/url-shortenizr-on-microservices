package com.exposit.training.linksstatisticservice.controller;

import com.exposit.training.linksstatisticservice.model.LinkStatistic;
import com.exposit.training.linksstatisticservice.model.dto.LinkDto;
import com.exposit.training.linksstatisticservice.service.LinkStatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class LinkStatisticController {

    @Autowired
    private LinkStatisticService statisticService;

    @GetMapping("/statistic/{key}")
    public ResponseEntity<LinkStatistic> getStatistic(@PathVariable(name = "key") String key) {
        return ResponseEntity.ok(statisticService.getStatistic(key));
    }

    @PutMapping("/statistic")
    public ResponseEntity addStatistic(@RequestBody LinkDto linkDto) {
        System.out.println(linkDto.toString());

        return statisticService.addStatistic(linkDto.getSourceUrl(), linkDto.getKey())
                ? ResponseEntity.created(URI.create(
                        String.format("http://localhost:8100/statistic/%s", linkDto.getKey()))).build()
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @PostMapping("/statistic")
    public ResponseEntity updateStatistic(@RequestBody LinkDto linkDto/*@RequestHeader(name = "Location") String location*/) {
        System.out.println(linkDto.toString());

        return statisticService.updateStatistic(linkDto.getSourceUrl())
                ? ResponseEntity.ok().build()
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

}
