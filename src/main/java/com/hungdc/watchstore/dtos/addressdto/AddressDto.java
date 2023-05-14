package com.hungdc.watchstore.dtos.addressdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String email;

    private String homeNumber;

    private String ward;

    private String city;
}
