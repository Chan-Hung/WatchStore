package com.hungdc.watchstore.dtos.category;

import com.hungdc.watchstore.entities.Watch;
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
public class CategoryDto {
    private String code;

    private String name;
}
