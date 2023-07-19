package com.dinhson.sunshop.appUser;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService underTest;

    @Test
    @Disabled
    void createNewUser() {
    }

    @Test
    void searchUserByAll() {
        //after
        String searchName = "on";
        String isActive = "true";
        String role = "USER";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers(isActive, role, searchName, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> !u.name().contains(searchName) || u.isActive() == false || !u.role().toString().equals(role));
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActiveAndSearch() {
        //after
        String searchName = "on";
        String isActive = "true";
        //when
        List<UserDTO> userDTOS = underTest.searchUsers(isActive, null, searchName, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> !u.name().contains(searchName) || u.isActive() == false);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByRoleAndSearch() {
        //after
        String searchName = "on";
        String role = "USER";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers("null", role, searchName, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> !u.name().contains(searchName) || !u.role().toString().equals(role));
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActiveAndRole() {
        //after
        String isActive = "true";
        String role = "USER";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers(isActive, role, null, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> u.isActive() == false || !u.role().toString().equals(role));
        assertEquals(false, actual);
    }

    @Test
    void searchUserByIsActive() {
        //after
        String isActive = "true";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers("null", isActive, null, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> u.isActive() == false);
        assertEquals(false, actual);
    }

    @Test
    void searchUserByRole() {
        //after
        String role = "USER";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers("null", role, null, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> !u.role().toString().equals(role));
        assertEquals(false, actual);
    }

    @Test
    void searchByName() {
        //after
        String searchName = "on";

        //when
        List<UserDTO> userDTOS = underTest.searchUsers("null", "null", searchName, 0, 2);

        //then
        boolean actual = userDTOS.stream()
                .anyMatch(u -> !u.name().contains(searchName));
        assertEquals(false, actual);
    }

    @Test
    void getAll() {
        //after

        //when
        List<UserDTO> userDTOS = underTest.searchUsers("null", "null", null, 0, 2);

        //then
        assertEquals(false, userDTOS.isEmpty());
    }

}