package com.frol.service.task.storage;


import com.frol.model.Quote;
import org.springframework.stereotype.Component;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@Component
public class QuotesStorage {

    private final BlockingQueue<Quote> quotes = new ArrayBlockingQueue<>(100);

    public void add(Quote quote) {
        quotes.add(quote);
    }

    public Quote getQuote() throws InterruptedException {
        return quotes.take();
    }

}
