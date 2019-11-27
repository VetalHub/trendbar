package com.frol.service;


import com.frol.model.Quote;
import com.frol.service.task.storage.QuotesStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrendBarService {

    private final QuotesStorage quotes;

//    private final TrendBarTaskHandler trendBarTaskHandler;

    public void addQuote(Quote quote) {
        log.info("Service add quote to queue");
        quotes.add(quote);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void EventListenerExecute(){
//        log.info("Application Ready Event is successfully Started");
//        trendBarTaskHandler.asyncUpdatingTrendbarsByQuotes();
//    }

}
