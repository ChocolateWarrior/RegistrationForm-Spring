package de.springboot.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@ToString
@SequenceGenerator(name="seq_request", initialValue = 1, allocationSize = 0)
@Table(name = "requests", uniqueConstraints = {@UniqueConstraint(columnNames = {"id"})})
public class RepairRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_request")
    @Column(name = "id", nullable = false, unique = true)
    private int id;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(mappedBy = "masterRequest", fetch = FetchType.LAZY)
    private User master;

    @Column(name = "specification", nullable = false)
    private String specification;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "request_time", nullable = false)
    private LocalDateTime requestTime;

    @Column(name = "finish_time")
    private LocalDateTime finishTime;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "rejection_message")
    private String rejectionMessage;

    @Column(name="comment")
    private String comment;

    @Column(name = "state")
    private RequestState state;

}
