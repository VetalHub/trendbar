package com.frol.model.entity;


import com.frol.model.TrendBarPeriod;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "trend_bar")
public class TrendBar {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @NonNull
    private Date timestamp;

    @NonNull
    @Column(name = "open_price")
    private BigDecimal openPrice;

    @NonNull
    @Column(name = "close_price")
    private BigDecimal closePrice;

    @NonNull
    @Column(name = "high_price")
    private BigDecimal highPrice;

    @NonNull
    @Column(name = "low_price")
    private BigDecimal lowPrice;

    @NonNull
    @Enumerated(EnumType.STRING)
    private TrendBarPeriod period;

}