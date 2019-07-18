package de.springboot.model;

import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@SequenceGenerator(name="seq_master", initialValue = 1, allocationSize = 0)
@Table(name = "masters", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_master")
    @Column(name = "id")
    private int id;

    @Column(name = "active")
    private boolean active;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "login", nullable = false, unique = true)
    private String login;

    @Column(name = "password", nullable = false)
    private String password;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ElementCollection(targetClass = Specification.class, fetch = FetchType.LAZY)
    @CollectionTable(name = "master_specification",
            joinColumns = @JoinColumn(name = "master_id"))
    @Enumerated(EnumType.STRING)
    private Set<Specification> specifications;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private RepairRequest request;


}
