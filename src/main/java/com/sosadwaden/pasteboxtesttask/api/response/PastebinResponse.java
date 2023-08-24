package com.sosadwaden.pasteboxtesttask.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PastebinResponse {
    private final String data;
    private final boolean isPublic;
}
