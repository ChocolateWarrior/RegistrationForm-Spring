package de.springboot.model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="seq_request", allocationSize = 100)
@Table(name = "requests", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class RepairRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_request")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "master_id")
    private int master;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "request_time", nullable = false)
    private LocalDateTime requestTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "active")
    private boolean active;

}
