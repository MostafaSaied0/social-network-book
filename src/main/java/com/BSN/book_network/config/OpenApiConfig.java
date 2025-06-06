package com.BSN.book_network.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Mostafa Saied",
                        email = "mostafa.saied909@gmail.com"
                ),
                description = "OpenApi documentation for Spring Security",
                title = "OpenApi specification - Mostafa",
                version = "1.0",
                license = @License(
                        name ="License name",
                        url = "https://some-url.com"

                ),
                termsOfService = "terms of services"

        ),
        servers = {
                @Server(
                        description = "local",
                        url = "http://localhost:8088/api/v1"

        ),
                @Server(
                        description = "PRO",
                        url = "https://saied.com/1stTest"
                )
        },
        security = {
                @SecurityRequirement(
                    name="bearerAuth"
                )
        }


)
@SecurityScheme(
        name = "bearerAuth",
        description = "bearer auth description",
        scheme = "bearer",
        type = SecuritySchemeType.OAUTH2 ,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
