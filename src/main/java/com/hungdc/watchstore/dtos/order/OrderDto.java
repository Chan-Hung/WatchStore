package com.hungdc.watchstore.dtos.order;

import com.hungdc.watchstore.entities.embedded.EmbeddedCategory;
import com.hungdc.watchstore.entities.embedded.Item;
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
public class OrderDto {
    private String email;

    private Long shippingFee;

    private String address;

    private List<Item> items;
}
