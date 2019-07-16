package de.springboot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestEditDTO {
    private int masterId;
    private BigDecimal price;
    private boolean isFinished;
}


