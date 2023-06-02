package com.hungdc.watchstore.entities;

import com.hungdc.watchstore.entities.embedded.ProductImage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "watches")
public class Watch {
    @Id
    private String id;

    @Indexed(unique = true)
    private String code;

    private String name;

    private String origin;

    private String machine;

    private String waterResistant;

    private List<ProductImage> productImages = new ArrayList<>();

    private Long price;

    private String category;
    private Integer quantity;
}
