package com.hungdc.watchstore.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {
    //category Id
    private String path;

    private Boolean isThumbnail;
}
