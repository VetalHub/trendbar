package com.frol.repository;

import com.frol.model.entity.TrendBar;
import feign.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TrendBarRepository extends CrudRepository<TrendBar, Long> {

    @Query("select t from TrendBar t where t.timestamp between ?1 and ?2")
    List<TrendBar> findAllByDateRange(@Param("fromDate") Date fromDate, @Param("toDate") Date toDate);
}
