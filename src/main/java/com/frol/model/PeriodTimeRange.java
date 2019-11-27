package com.frol.model;

import com.frol.service.task.TrendBarTask;
import com.frol.utils.TimeUtils;

import java.time.LocalDateTime;


public class PeriodTimeRange {

    private LocalDateTime prevTime;
    private LocalDateTime nextTime;

    public PeriodTimeRange(TrendBarTask tb) {
        TrendBarPeriod tbPeriod = tb.getPeriod();
        switch (tbPeriod) {
            case M1:
                prevTime = TimeUtils.getStartOfMinute(tb.getTimestamp());
                nextTime = TimeUtils.getNextMinute(tb.getTimestamp());
                break;
            case H1:
                prevTime = TimeUtils.getStartOfHour(tb.getTimestamp());
                nextTime = TimeUtils.getNextHour(tb.getTimestamp());
                break;
            case D1:
                prevTime = TimeUtils.getStartOfDay(tb.getTimestamp());
                nextTime = TimeUtils.getNextDay(tb.getTimestamp());
                break;
            default:
                break;
        }
    }

    public boolean timeIsInRange(LocalDateTime time) {
        return time.isAfter(prevTime) && (time.isBefore(nextTime) || time.isEqual(nextTime));
    }

}
