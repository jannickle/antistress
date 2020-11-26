package com.example.antistressdagbog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserCredentials {

    @Id private String username;
    private String password;
    private boolean enabled;

    @ElementCollection
    @JoinTable(
            name = "authorities",
            joinColumns = {@JoinColumn(name = "username")}
    )
    @Column(name = "authority")
    private Set<String> roles;

    @OneToOne(targetEntity = Account.class, cascade = CascadeType.ALL)
    private Account account;

}
