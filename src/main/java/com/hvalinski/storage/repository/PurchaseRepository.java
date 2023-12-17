package com.hvalinski.storage.repository;
import com.hvalinski.storage.dataBase.Purchases;
import org.springframework.data.repository.CrudRepository;
public interface PurchaseRepository extends CrudRepository<Purchases,Long> {
}
