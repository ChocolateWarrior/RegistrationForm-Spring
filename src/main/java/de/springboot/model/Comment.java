package de.springboot.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Comment {
    private User user;
    private RepairRequest repairRequest;
    private Master master;
    private String comment;
    private LocalDateTime date;
    private BigDecimal mark;
}
