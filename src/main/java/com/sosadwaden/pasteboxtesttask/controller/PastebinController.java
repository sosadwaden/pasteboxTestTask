package com.sosadwaden.pasteboxtesttask.controller;

import com.sosadwaden.pasteboxtesttask.api.request.PastebinRequest;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PastebinUrlResponse;
import com.sosadwaden.pasteboxtesttask.service.PastebinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "PastebinController", description = "The only and main controller")
public class PastebinController {

    private final PastebinService service;

    @Operation(
            summary = "Get paste",
            description = "Returns paste by hash code"
    )
    @GetMapping("/{hash}")
    public ResponseEntity<PastebinResponse> getPasteByHash(@PathVariable @NotBlank String hash) {
        return new ResponseEntity<>(service.findPasteByHash(hash), HttpStatus.OK);
    }

    @Operation(
            summary = "Get a list of pastes",
            description = "Returns the last 10 pastes"
    )
    @GetMapping("/")
    public ResponseEntity<List<PastebinResponse>> getPublicPasteList() {
        return new ResponseEntity<>(service.findFirstPublicPastes(), HttpStatus.OK);
    }

    @Operation(
            summary = "Add paste",
            description = "Creates a new paste, stores it in the database and returns the hash code"
    )
    @PostMapping ResponseEntity<PastebinUrlResponse> add(@Valid @RequestBody PastebinRequest request) {
        return new ResponseEntity<>(service.create(request), HttpStatus.OK);
    }
}
