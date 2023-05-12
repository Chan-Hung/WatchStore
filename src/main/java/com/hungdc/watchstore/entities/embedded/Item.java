package com.hungdc.watchstore.entities.embedded;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    //watch id
    private String watch;

    private Integer quantity;

    private Long price;
}
