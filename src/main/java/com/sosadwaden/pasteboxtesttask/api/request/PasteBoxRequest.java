package com.sosadwaden.pasteboxtesttask.api.request;

import lombok.Data;

@Data
public class PasteBoxRequest {
    private String data;
    private long expirationTimeSeconds;
    private Status publicStatus;
}
