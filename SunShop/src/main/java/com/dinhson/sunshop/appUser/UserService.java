package com.dinhson.sunshop.appUser;

import com.dinhson.sunshop.appUser.profile.ProfileSecurityDTO;
import com.dinhson.sunshop.exception.UsersNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;

    public User findUserById(int id) {
        return userRepository.getById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found!!!"));
    }

    public UserDTO getUserDTO(int id){
        User user = findUserById(id);
        return userDTOMapper.apply(user);
    }

    private boolean checkEmailExist(String email){
        return userRepository.getUserByEmail(email).isPresent();
    }

    public void createNewUser(UserDTO userDTO){
        User user = new User(userDTO);
        if(checkEmailExist(userDTO.email()))
            throw new IllegalArgumentException("Email is already exist!!!");
        userRepository.save(user);
    }

    public UserDTO updateUser(UserDTO userDTO){
        User user = findUserById(userDTO.userId());
        user.setName(userDTO.name());
        user.setDob(userDTO.dob());
        user.setGender(userDTO.gender());
        user.setImage(userDTO.image());
        userRepository.save(user);
        return userDTOMapper.apply(user);
    }

    private List<User> searchBy(Boolean isActive, Role role, String searchName){
        if (isActive != null) {
            if (role != null && !searchName.isEmpty()) {
                // search by all
                return userRepository.searchUserByAll(isActive, role, searchName);
            } else if (role != null) {
                // search by status and role
                return userRepository.searchUserByIsActiveAndRole(isActive, role);
            } else if (!searchName.isEmpty()) {
                // search by status and search name
                return userRepository.searchUserByIsActiveAndSearch(isActive, searchName);
            } else {
                // search by status
                return userRepository.searchUserByIsActive(isActive);
            }
        } else {
            if (role != null && !searchName.isEmpty()) {
                // search by role and search name
                return userRepository.searchUserByRoleAndSearch(role, searchName);
            } else if (role != null) {
                // search by role
                return userRepository.searchUserByRole(role);
            } else if (!searchName.isEmpty()) {
                // search by search name
                return userRepository.searchByName(searchName);
            } else {
                // get all
                return userRepository.getAll();
            }
        }
    }

    public List<UserDTO> getAll(){
        return userRepository.getAll().stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    private List<UserDTO> searchUsersAndMapToUserDTO(Boolean isActive, Role role, String searchName){
        List<User> users = searchBy(isActive, role, searchName);
        return users.stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public List<UserDTO> searchUsers(String isActiveStr, String roleStr, String searchName){
        Role role = null;
        for (Role r: Role.values()) {
            if(r.toString().equals(roleStr)){
                role = r;
                break;
            }
        }
        Boolean isActive;
        if(isActiveStr.equals("true")){
            isActive = true;
        } else if (isActiveStr.equals("false")) {
            isActive = false;
        }else {
            isActive = null;
        }

        System.out.println("isA: " + isActive +" role: " + role + "search: " + searchName);
        return searchUsersAndMapToUserDTO(isActive, role, searchName);
    }

    private User loginByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Password is wrong!!!"));
    }

    public void changePassword(ProfileSecurityDTO profileSecurityDTO){
        if(profileSecurityDTO.newPassword().isEmpty() ||
                !profileSecurityDTO.newPassword().equals(profileSecurityDTO.rePassword())){
            throw new IllegalArgumentException("New Password and re-password must be the same!!!");
        }
        User user = loginByEmailAndPassword(profileSecurityDTO.email(), profileSecurityDTO.password());
        user.setPassword(profileSecurityDTO.newPassword());
        userRepository.save(user);
    }

}
