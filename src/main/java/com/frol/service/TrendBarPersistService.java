package com.frol.service;


import com.frol.repository.TrendBarRepository;
import com.frol.service.task.TrendBarTask;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrendBarPersistService {

    private final TrendBarRepository tbRepository;

    public void save(TrendBarTask trendBarTask) {
        log.info("Save TrednBar to DB");
        tbRepository.save(trendBarTask.getTrendBar());
    }

}
