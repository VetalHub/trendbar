package com.frol.controller;


import com.frol.model.Quote;
import com.frol.service.TrendBarService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TrendBarController {

    private final TrendBarService trendBarService;

    @PostMapping(path = "/quote")
    public void updateByQuote(Quote quote) {

        log.info("Controller add quote");
        trendBarService.addQuote(quote);
    }

}
