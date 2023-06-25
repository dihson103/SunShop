package com.dinhson.sunshop.appUser;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> getById(Integer id);

    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.role = :role AND u.name LIKE %:nameSearch")
    List<User> searchUserByAll(boolean isActive, Role role, String nameSearch);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.name LIKE %:nameSearch")
    List<User> searchUserByIsActiveAndSearch(boolean isActive, String nameSearch);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.name LIKE %:nameSearch")
    List<User> searchUserByRoleAndSearch(Role role, String nameSearch);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.role = :role")
    List<User> searchUserByIsActiveAndRole(boolean isActive, Role role);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive")
    List<User> searchUserByIsActive(boolean isActive);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> searchUserByRole(Role role);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:searchName")
    List<User> searchByName(String searchName);

    @Query("SELECT u FROM User u")
    List<User> getAll();

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.isActive  = true")
    Optional<User> findUserByEmailAndPassword(String email, String password);
}
