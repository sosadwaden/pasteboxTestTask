package com.sosadwaden.pasteboxtesttask.service;

import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxUrlResponse;
import com.sosadwaden.pasteboxtesttask.api.request.PasteBoxRequest;

import java.util.List;

public interface PasteBoxService {
    PasteBoxResponse getPasteBoxByHash(String hash);

    List<PasteBoxResponse> getFirstPublicPasteBoxes();

    PasteBoxUrlResponse create(PasteBoxRequest request);
}
