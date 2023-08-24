package com.sosadwaden.pasteboxtesttask.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "pastebin")
public class PastebinEntity {

    @Id
    private int id;

    private String data;
    private String hash;
    private LocalDateTime lifetime;
    private boolean isPublic;
}
