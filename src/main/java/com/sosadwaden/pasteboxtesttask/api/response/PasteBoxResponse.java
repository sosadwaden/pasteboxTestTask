package com.sosadwaden.pasteboxtesttask.api.response;

import com.sosadwaden.pasteboxtesttask.api.request.Status;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PasteBoxResponse {
    private final String data;
    private final boolean isPublic;
}
