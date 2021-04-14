package com.elfak.whserver.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "secret_key")
    private String secretKey;

    @Column(name = "full_name")
    private String fullName;

    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true, mappedBy = "user")
    private List<WebHook> webHooks = new ArrayList<>();
}
