package de.springboot.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EqualsAndHashCode
@SequenceGenerator(name="seq_master", initialValue = 1, allocationSize = 0)
@Table(name = "masters", uniqueConstraints = {@UniqueConstraint(columnNames = {"username"})})
public class Master
        implements UserDetails
{
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

    @Column(name = "username", nullable = false, unique = true)
    private String username;

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


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
