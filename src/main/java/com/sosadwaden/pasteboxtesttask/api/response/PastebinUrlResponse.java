package com.sosadwaden.pasteboxtesttask.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "Pastebin URL")
public class PastebinUrlResponse {

    @Schema(description = "Paste hash code", example = "a")
    private final String url;
}
