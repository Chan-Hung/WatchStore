package com.hungdc.watchstore.repositories;

import com.hungdc.watchstore.entities.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query(value = "{'maDichVu': ?0}", exists = true)
    boolean kiemTraMaLoaiThuCung(String maLoaiThuCung);
}
