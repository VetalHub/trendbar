package com.frol.service.task;

import com.frol.model.Quote;
import com.frol.service.task.storage.QuotesStorage;
import com.frol.service.task.storage.TrendBarStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component("trendBarTaskHandler")
public class TrendBarTaskHandler {

    private final QuotesStorage quotesStorage;

    private final TrendBarStorage trendBarStorage;

    @Scheduled(fixedDelay = 300)
    public void asyncUpdatingTrendbarsByQuotes() {

        try {
            log.info("TrendBarTaskHandler");
            Quote quote = quotesStorage.getQuote();
            trendBarStorage.updateTrendBars(quote);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
