package com.hungdc.watchstore.dtos.watch;

import com.hungdc.watchstore.entities.embedded.EmbeddedCategory;
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
public class WatchDto {
    //code is unique
    private String code;

    private String name;

    private String origin;

    private String machine;

    private String waterResistant;

    private List<EmbeddedCategory> embeddedCategories = new ArrayList<>();
}
