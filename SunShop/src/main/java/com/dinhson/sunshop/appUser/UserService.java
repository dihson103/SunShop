package com.dinhson.sunshop.appUser;

import com.dinhson.sunshop.appUser.profile.ProfileSecurityDTO;
import com.dinhson.sunshop.exception.ForgetPasswordEmailNotTrueException;
import com.dinhson.sunshop.exception.UserNotValidException;
import com.dinhson.sunshop.exception.UsersNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public User findUserById(int id) {
        return userRepository.findById(id).
                orElseThrow(() -> new IllegalArgumentException("User not found!!!"));
    }

    public UserDTO getUserDTO(int id){
        User user = findUserById(id);
        return userDTOMapper.apply(user);
    }

    private boolean checkEmailExist(String email){
        return userRepository.getUserByEmail(email).isPresent();
    }

    public void createNewUser(User user){
        if(checkEmailExist(user.getEmail()))
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

    public void changeAccountStatus(Integer userId){
        User user = findUserById(userId);
        user.setIsActive(user.getIsActive() ? false : true);
        userRepository.save(user);
    }


    private void checkPassword(UserSecurityDTO userDTO){
        if(!userDTO.getPassword().equals(userDTO.getRePassword()))
            throw new UserNotValidException("Password and re-password must be same!!!", userDTO);
    }

    private void checkUserExist(UserSecurityDTO userDTO){
        Optional<User> userOptional = userRepository.findUserByEmailAndEnableIsTrue(userDTO.getEmail());
        if(userOptional.isPresent())
            throw new UserNotValidException("Email is already registries!!!", userDTO);
    }

    public User saveUser(UserSecurityDTO userDTO){
        checkPassword(userDTO);
        checkUserExist(userDTO);
        User user = new User(userDTO);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        return userRepository.save(user);
    }

    public void setEnable(User user) {
        user.setEnabled(true);
        user.setIsActive(true);
        userRepository.save(user);
    }

    public User getUserByEmail(String email){
        Optional<User> userOptional = userRepository.findUserByEmailAndActiveIsTrue(email);
        return userOptional.
                orElseThrow(() -> new ForgetPasswordEmailNotTrueException("Can not find account by this email!!!"));
    }

    public void changeUserPassword(UserSecurityDTO userDTO) {
        User user = userRepository.findUserByEmailAndActiveIsTrue(userDTO.getEmail()).get();
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

}
