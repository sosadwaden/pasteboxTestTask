package com.sosadwaden.pasteboxtesttask.api.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class PastebinUrlResponse {
    private final String url;
}
