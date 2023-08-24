package com.sosadwaden.pasteboxtesttask.api.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PastebinRequest {
    private String data;
    private long expirationTimeSeconds;
    private Status status;
}
