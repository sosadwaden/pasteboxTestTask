package com.sosadwaden.pasteboxtesttask.controller;

import com.sosadwaden.pasteboxtesttask.api.request.PastebinRequest;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinUrlResponse;
import com.sosadwaden.pasteboxtesttask.service.PastebinService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class PastebinController {

    private final PastebinService service;

    @GetMapping("/{hash}")
    public ResponseEntity<PastebinResponse> getPasteByHash(@PathVariable @NotBlank String hash) {
        return new ResponseEntity<>(service.findPasteByHash(hash), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<PastebinResponse>> getPublicPasteList() {
        return new ResponseEntity<>(service.findFirstPublicPastes(), HttpStatus.OK);
    }

    @PostMapping ResponseEntity<PastebinUrlResponse> add(@Valid @RequestBody PastebinRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }
}
