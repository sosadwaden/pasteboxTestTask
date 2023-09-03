package com.sosadwaden.pasteboxtesttask.api.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@Schema(description = "Pastebin DAO")
public class PastebinResponse {

    @Schema(description = "Paste Data", example = "Text in paste")
    private final String data;

    @Schema(description = "Paste is public?", example = "true")
    private final boolean isPublic;
}
