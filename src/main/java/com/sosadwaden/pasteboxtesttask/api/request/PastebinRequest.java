package com.sosadwaden.pasteboxtesttask.api.request;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Pastebin DTO")
public class PastebinRequest {

    @Schema(description = "Paste data", example = "Text in paste")
    @NotBlank(message = "Empty or null value")
    private String data;

    @Schema(description = "The time during which the paste will be available", example = "30000")
    @Min(value = 600, message = "The transmitted time must be greater than or equal to 10 minutes")
    @Max(value = 2_678_400, message = "The transmitted time must be less than or equal to 1 month")
    private long expirationTimeSeconds;

    @Schema(description = "Paste status: public - available to everyone, unlisted - available only by link",
            example = "PUBLIC"
    )
    private Status status;
}
