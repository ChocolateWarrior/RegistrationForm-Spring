package de.springboot.dto;

import de.springboot.model.RequestState;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestMasterDTO {

    private BigDecimal price;
    private String state;
}
