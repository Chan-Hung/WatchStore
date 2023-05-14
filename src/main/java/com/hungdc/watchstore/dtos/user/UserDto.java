package com.hungdc.watchstore.dtos.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String name;

    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY, value = "password")
    private String password;

    private String telephoneNumber;

    private List<String> roles = new ArrayList<>();
}
