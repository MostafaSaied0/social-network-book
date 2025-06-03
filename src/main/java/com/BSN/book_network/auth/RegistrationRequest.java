package com.BSN.book_network.auth;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class RegistrationRequest {

    @NotEmpty(message = "Firstname is Required!")
    @NotBlank(message = "Firstname is Required!")
    private String firstname;
    @NotEmpty(message = "Lastname is Required!")
    @NotBlank(message = "Lastname is Required!")
    private String lastname;
    @Email(message = "Email is not formatted --> @gmail.com")
    @NotEmpty(message = "Email is Required!")
    @NotBlank(message = "Email is Required!")
    private String email;
    @NotEmpty(message = "Password is Required!")
    @NotBlank(message = "Password is Required!")
    @Size(min = 8, message = "Password should be 8 characters long minimum")
    private String password;

}
