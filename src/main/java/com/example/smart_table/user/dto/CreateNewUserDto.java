package com.example.smart_table.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class CreateNewUserDto {

    @NotBlank(message = "Name cannot be empty.")
    private String name;

    @NotBlank(message = "Role cannot be empty.")
    private String role;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "Email should be valid.")
    private String email;

}
