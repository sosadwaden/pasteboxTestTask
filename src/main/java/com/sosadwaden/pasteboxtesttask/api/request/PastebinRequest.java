package com.sosadwaden.pasteboxtesttask.api.request;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PastebinRequest {

    @NotBlank(message = "Empty or null value")
    private String data;

    @Min(value = 600, message = "The transmitted time must be greater than or equal to 10 minutes")
    @Max(value = 2_678_400, message = "The transmitted time must be less than or equal to 1 month")
    private long expirationTimeSeconds;

    private Status status;
}
