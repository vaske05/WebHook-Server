package com.elfak.whserver.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "web_hook")
public class WebHook {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "url")
    String url;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    User user;


}
