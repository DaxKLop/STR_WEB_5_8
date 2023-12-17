package com.hvalinski.storage.repository;

import com.hvalinski.storage.dataBase.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsersRepository extends CrudRepository<Users,Long> {
    Optional<Users> findByLogin(String login);
}
