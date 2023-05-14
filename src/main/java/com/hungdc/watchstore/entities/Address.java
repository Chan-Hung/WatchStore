package com.hungdc.watchstore.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "addresses")
public class Address {
    @Id
    private String id;

    private String email;

    private String homeNumber;

    private String ward;

    private String city;
}
