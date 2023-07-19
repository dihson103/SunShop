package com.dinhson.sunshop.appUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    User getById(Integer id);

    Optional<User> getUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.isActive = true")
    Optional<User> findUserByEmailAndActiveIsTrue(String email);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.role = :role AND u.name LIKE %:nameSearch%")
    Page<User> searchUserByAll(boolean isActive, Role role, String nameSearch, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.name LIKE %:nameSearch%")
    Page<User> searchUserByIsActiveAndSearch(boolean isActive, String nameSearch, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role = :role AND u.name LIKE %:nameSearch%")
    Page<User> searchUserByRoleAndSearch(Role role, String nameSearch, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive AND u.role = :role")
    Page<User> searchUserByIsActiveAndRole(boolean isActive, Role role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.isActive = :isActive")
    Page<User> searchUserByIsActive(boolean isActive, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.role = :role")
    Page<User> searchUserByRole(Role role, Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:searchName%")
    Page<User> searchByName(String searchName, Pageable pageable);

    @Query("SELECT u FROM User u")
    Page<User> getAll(Pageable pageable);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password AND u.isActive  = true")
    Optional<User> findUserByEmailAndPassword(String email, String password);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.enabled = true")
    Optional<User> findUserByEmailAndEnableIsTrue(String email);
}
