package com.sosadwaden.pasteboxtesttask.controller;

import com.sosadwaden.pasteboxtesttask.api.request.PasteBoxRequest;
import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxResponse;
import com.sosadwaden.pasteboxtesttask.api.response.PasteBoxUrlResponse;
import com.sosadwaden.pasteboxtesttask.service.PasteBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequiredArgsConstructor
public class PasteBoxController {

    private final PasteBoxService service;

    @GetMapping("/")
    public Collection<PasteBoxResponse> getPublicPasteList() {
        return service.getFirstPublicPasteBoxes();
    }

    @GetMapping("/{hash}")
    public PasteBoxResponse getPasteByHash(@PathVariable String hash) {
        return service.getPasteBoxByHash(hash);
    }

    @PostMapping("/")
    public PasteBoxUrlResponse add(@RequestBody PasteBoxRequest request) {
        return service.create(request);
    }

}
