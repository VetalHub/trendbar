package com.frol.service.task.storage;

import com.frol.model.*;
import com.frol.service.TrendBarPersistService;
import com.frol.service.task.TrendBarTask;
import com.frol.utils.TimeUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class TrendBarStorage {

    private Map<Symbol, Map<TrendBarPeriod, TrendBarTask>> currentTrendBars = new HashMap<>();
    private final TrendBarPersistService tbPersistService;

    @PostConstruct
    private void init() {
        Map<TrendBarPeriod, TrendBarTask> symbolTrendBarMap = new HashMap<>();

        for (TrendBarPeriod period : TrendBarPeriod.values()) {
            symbolTrendBarMap.put(period, createEmptyTrendBar(period));
        }
        for (Symbol symbol : Symbol.values()) {
            currentTrendBars.put(symbol, symbolTrendBarMap);
        }
    }

    public void cleanStorage(){
    }

    //    вызывается при появлении новой Quote в очереди
    public void updateTrendBars(Quote quote) {
        Symbol symbol = quote.getSymbol();
        Map<TrendBarPeriod, TrendBarTask> trendBarsBySymbol = getTrendBarsBySymbol(symbol);

        if (trendBarsBySymbol != null) {
            updateTrendBarsForSymbol(trendBarsBySymbol, quote);
        }
    }

    private void updateTrendBarsForSymbol(Map<TrendBarPeriod, TrendBarTask> trendBarsBySymbol, Quote quote) {
        for (Map.Entry<TrendBarPeriod, TrendBarTask> entry : trendBarsBySymbol.entrySet()) {
            updateTB(entry.getValue(), quote);
        }
    }

    private void updateTB(TrendBarTask tb, Quote quote) {
        boolean quoteIsInPeriod = quoteIsInTBPeriod(tb, quote);

        if (!quoteIsInPeriod) {
            if (!tb.isComplete()) {
                tbPersistService.save(tb);
                tb.setStatus(TrendBarStatus.COMPLETE);
            }
            tb.clear();
            tb.setStatus(TrendBarStatus.INCOMPLETE);
            startTrendBar(tb, quote);
        } else {
            updateTrendBar(tb, quote);
        }
    }

    private void startTrendBar(TrendBarTask tb, Quote quote) {
        LocalDateTime dateTime = TimeUtils.getFromDate(quote.getTimestamp());
        tb.setHighPrice(quote.getNewPrice());
        tb.setLowPrice(quote.getNewPrice());
        tb.setOpenPrice(quote.getNewPrice());
        tb.setClosePrice(quote.getNewPrice());
        tb.setTimestamp(TimeUtils.getStartOfMinute(dateTime));
        tb.setStatus(TrendBarStatus.INCOMPLETE);
    }

    private void updateTrendBar(TrendBarTask tb, Quote quote) {
        tb.setHighPrice(quote.getNewPrice());
        tb.setLowPrice(quote.getNewPrice());
        tb.setClosePrice(quote.getNewPrice());
    }

    private boolean quoteIsInTBPeriod(TrendBarTask tb, Quote quote) {
        if (tb.getTimestamp() == null) {
            return false;
        }
        PeriodTimeRange timeRange = new PeriodTimeRange(tb);
        LocalDateTime quoteTime = TimeUtils.getFromDate(quote.getTimestamp());
        return timeRange.timeIsInRange(quoteTime);
    }

    private Map<TrendBarPeriod, TrendBarTask> getTrendBarsBySymbol(Symbol symbol) {
        return currentTrendBars.get(symbol);
    }

    private TrendBarTask createEmptyTrendBar(TrendBarPeriod period) {
        return new TrendBarTask(period, TrendBarStatus.COMPLETE);
    }

}
