package com.sosadwaden.pasteboxtesttask.service;

import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinUrlResponse;
import com.sosadwaden.pasteboxtesttask.api.request.PastebinRequest;

import java.util.List;

public interface PastebinService {

    PastebinResponse findPasteByHash(String hash);

    List<PastebinResponse> findFirstPublicPastes();

    PastebinUrlResponse create(PastebinRequest request);
}
