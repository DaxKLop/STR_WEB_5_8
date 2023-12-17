package com.hvalinski.storage.repository;

import com.hvalinski.storage.dataBase.Categories;
import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Categories,Long> {
}
