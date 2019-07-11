package de.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "masters", uniqueConstraints = {@UniqueConstraint(columnNames = {"login"})})
public class Master {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ElementCollection(targetClass = Specification.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "master_specification",
            joinColumns = @JoinColumn(name = "master_id"))
    @Enumerated(EnumType.STRING)
    private Set<Specification> specifications;

}
