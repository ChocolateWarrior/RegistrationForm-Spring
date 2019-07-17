package de.springboot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@SequenceGenerator(name="seq_master", initialValue = 1, allocationSize = 100)
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

    //TODO connect as ManyToMany
    @ElementCollection(targetClass = Specification.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "master_specification",
            joinColumns = @JoinColumn(name = "master_id"))
    @Enumerated(EnumType.STRING)
    private Set<Specification> specifications;

//    @Column(name = "password", nullable = false)
//    private RepairRequest request;


    //TODO current request, OneToOne with requests

}
