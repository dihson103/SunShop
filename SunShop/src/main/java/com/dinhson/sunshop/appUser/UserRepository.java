package com.dinhson.sunshop.appUser;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getById(Integer id);
}
