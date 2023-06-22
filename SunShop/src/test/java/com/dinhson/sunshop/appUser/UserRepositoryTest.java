package com.dinhson.sunshop.appUser;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository underTest;

    @Test
    void searchUserByAll() {
        //after
        String searchName = "Son";
        boolean status = true;
        Role role = Role.MANAGER;

        //when
        List<User> users = underTest.searchUserByAll(status, role, searchName);

        boolean actual = users.stream()
                .anyMatch(u -> !u.getName().contains(searchName) || u.getIsActive() == false || u.getRole() != role);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActiveAndSearch() {
        //after
        String searchName = "on";
        boolean status = true;

        //when
        List<User> users = underTest.searchUserByIsActiveAndSearch(status, searchName);

        //then
        boolean actual = users.stream().anyMatch(u -> !u.getName().contains(searchName) || u.getIsActive() == false);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByRoleAndSearch() {
        //after
        String searchName = "on";
        Role role = Role.USER;

        //when
        List<User> users = underTest.searchUserByRoleAndSearch(role, searchName);

        //then
        boolean actual = users.stream().anyMatch(u -> !u.getName().contains(searchName) || u.getRole() != role);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActiveAndRole() {
        //after
        boolean status = true;
        Role role = Role.USER;

        //when
        List<User> users = underTest.searchUserByIsActiveAndRole(status, role);

        //then
        boolean actual = users.stream().anyMatch(u -> u.getIsActive() == false || u.getRole() != role);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActive() {
        //after
        boolean status = true;

        //when
        List<User> users = underTest.searchUserByIsActive(status);

        //then
        boolean actual = users.stream().anyMatch(u -> u.getIsActive() == false);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByRole() {
        //after
        Role role = Role.USER;

        //when
        List<User> users = underTest.searchUserByRole(role);

        //then
        boolean actual = users.stream().anyMatch(u -> u.getRole() != role);
        assertEquals(false, actual);
    }

    @Test
    void searchByName() {
        //after
        String searchName = "on";

        //when
        List<User> users = underTest.searchByName(searchName);

        //then
        boolean actual = users.stream().anyMatch(u -> !u.getName().contains(searchName));
        assertEquals(false, actual);
    }

    @Test
    void getAll() {
        //after

        //when
        List<User> users = underTest.getAll();

        //then
        assertEquals(false, users.isEmpty());
    }
}