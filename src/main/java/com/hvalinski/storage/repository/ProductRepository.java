package com.hvalinski.storage.repository;

import com.hvalinski.storage.dataBase.Products;
import org.springframework.data.repository.CrudRepository;
public interface ProductRepository extends CrudRepository<Products,Long> {
}
