package com.frol.service.task;


import com.frol.model.entity.TrendBar;
import com.frol.model.TrendBarPeriod;
import com.frol.model.TrendBarStatus;
import com.frol.utils.TimeUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Data
public class TrendBarTask {

    private LocalDateTime timestamp;
    private volatile TrendBarPeriod period;
    private volatile TrendBarStatus status;
    private volatile BigDecimal openPrice;
    private volatile BigDecimal closePrice;
    private volatile BigDecimal highPrice;
    private volatile BigDecimal lowPrice;

    public TrendBarTask(TrendBarPeriod period, TrendBarStatus status) {
        this.period = period;
        this.status = status;
    }

    public void setLowPrice(BigDecimal newPrice) {
        if (lowPrice != null) {
            lowPrice = lowPrice.min(newPrice);
        } else {
            lowPrice = newPrice;
        }
    }

    public void setHighPrice(BigDecimal newPrice) {
        if (highPrice != null) {
            highPrice = highPrice.max(newPrice);
        } else {
            highPrice = newPrice;
        }
    }

    public void clear() {
        openPrice = null;
        closePrice = null;
        highPrice = null;
        lowPrice = null;
        status = TrendBarStatus.COMPLETE;
    }

    public boolean isComplete() {
        return status.equals(TrendBarStatus.COMPLETE);
    }

    public void setTimestamp(LocalDateTime time) {
        switch (period) {
            case M1:
                timestamp = TimeUtils.getStartOfMinute(time);
                break;
            case H1:
                timestamp = TimeUtils.getStartOfHour(time);
                break;
            case D1:
                timestamp = TimeUtils.getStartOfDay(time);
                break;
            default:
                break;
        }
    }

    public TrendBar getTrendBar() {

        return TrendBar.builder()
                .timestamp(getTimestampAsDate())
                .period(this.period)
                .openPrice(this.openPrice)
                .highPrice(this.highPrice)
                .lowPrice(this.lowPrice)
                .closePrice(this.closePrice)
                .build();
    }

    private Date getTimestampAsDate() {
        LocalDateTime ldt = LocalDateTime.ofInstant(timestamp.toInstant(ZoneOffset.UTC), ZoneOffset.UTC);
        return Date.from(ldt.atZone(ZoneOffset.UTC).toInstant());
    }



}

