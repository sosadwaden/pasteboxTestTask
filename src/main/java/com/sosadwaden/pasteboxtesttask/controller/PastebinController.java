package com.sosadwaden.pasteboxtesttask.controller;

import com.sosadwaden.pasteboxtesttask.api.request.PastebinRequest;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinUrlResponse;
import com.sosadwaden.pasteboxtesttask.service.PastebinService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PastebinController {

    private final PastebinService service;

    @GetMapping("/{hash}")
    public ResponseEntity<PastebinResponse> getPasteByHash(@PathVariable String hash) {
        return new ResponseEntity<>(service.getPasteBoxByHash(hash), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PastebinResponse>> getPublicPasteList() {
        return new ResponseEntity<>(service.getFirstPublicPasteBoxes(), HttpStatus.OK);
    }

    @PostMapping ResponseEntity<PastebinUrlResponse>  add(@RequestBody PastebinRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }
}
