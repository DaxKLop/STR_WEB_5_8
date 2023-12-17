package com.hvalinski.storage.repository;
import com.hvalinski.storage.dataBase.Purchases_Item;
import org.springframework.data.repository.CrudRepository;
public interface PurchaseItemRepository extends CrudRepository<Purchases_Item,Long> {
}
