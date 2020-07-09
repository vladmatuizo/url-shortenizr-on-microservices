package com.exposit.training.shortenizr.proxy;

import com.exposit.training.shortenizr.model.Link;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

//Use of Feign with hardcoded service location
//@FeignClient(name = "links-statistic-service", url = "localhost:8100")
//Use of Feign with Eureka naming server
//@FeignClient(name = "links-statistic-service")
//Through Zuul API Gateway
@FeignClient(name = "zuul-api-gateway-server")
@RibbonClient(name = "links-statistic-service")
public interface LinkStatisticExchangeServiceProxy {

    /*@PutMapping("/statistic")
    ResponseEntity addStatistic(@RequestBody Link link);

    @PostMapping("/statistic")
    ResponseEntity updateStatistic(@RequestBody Link link);*/

    //Requests through Zuul API Gateway
    @PutMapping("/links-statistic-service/statistic")
    ResponseEntity addStatistic(@RequestBody Link link);

    @PostMapping("/links-statistic-service/statistic")
    ResponseEntity updateStatistic(@RequestBody Link link);

}
