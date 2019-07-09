package de.springboot.dto;

import de.springboot.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {

//    private User user;
    private String type;
    private String description;
    private LocalDateTime requestTime = LocalDateTime.now();
//    private boolean active;
}
