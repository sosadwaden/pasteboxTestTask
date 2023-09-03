package com.sosadwaden.pasteboxtesttask.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Pastebin",
                description = "Pastebin analog",
                version = "1.0.0",
                contact = @Contact(
                        name = "Kirill Pivovarov"
                )
        )
)
public class SwaggerConfig {
}
