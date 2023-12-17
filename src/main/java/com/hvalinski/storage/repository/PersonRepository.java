package com.hvalinski.storage.repository;

import com.hvalinski.storage.dataBase.Person;
import org.springframework.data.repository.CrudRepository;
public interface PersonRepository extends CrudRepository<Person,Long> {
}
