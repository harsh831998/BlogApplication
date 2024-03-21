package com.blog.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;

@Configuration
@OpenAPIDefinition(info = @Info(title = "REST API", version = "1.0",
        description = "REST API description...",
        contact = @Contact(name = "Rupam Kumari")),
        security = {@SecurityRequirement(name = "bearer")}
)
@SecuritySchemes({
        @SecurityScheme(name = "Bearer", type = SecuritySchemeType.HTTP, 
                        scheme = "bearer", bearerFormat = "JWT")
})
public class SwaggerConfig{
}