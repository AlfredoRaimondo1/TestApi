package com.example.testapi.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddressDto {
    @Size(max = 40)
    private String address;
    private String city;
    private String countryCode;

    public AddressDto(String address, String city, String countryCode) {
        this.address = address;
        this.city = city;
        this.countryCode = countryCode;
    }
}
