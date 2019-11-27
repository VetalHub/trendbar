package com.frol.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Quote {

    private BigDecimal newPrice;

    private Symbol symbol;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private Date timestamp;

}
