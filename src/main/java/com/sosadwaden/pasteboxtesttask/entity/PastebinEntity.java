package com.sosadwaden.pasteboxtesttask.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pastebin")
public class PastebinEntity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "data")
    private String data;

    @Column(name = "hash")
    private String hash;

    @Column(name = "lifetime")
    private LocalDateTime lifetime;

    @Column(name = "isPublic")
    private boolean isPublic;
}
