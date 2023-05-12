package com.hungdc.watchstore.entities;

import com.hungdc.watchstore.entities.embedded.EmbeddedCategory;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by: IntelliJ IDEA
 * UserRepository      : thangpx
 * Date      : 4/11/23
 * Time      : 9:35 PM
 * Filename  : WatchRepository
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "watches")
public class Watch {
    @Id
    private String id;

    //code is unique
    private String code;

    private String name;

    private String origin;

    private String machine;

    private String waterResistant;

    private List<EmbeddedCategory> embeddedCategories = new ArrayList<>();

    private boolean state = true;
}
