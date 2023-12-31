package com.dinhson.sunshop.appUser;

import com.dinhson.sunshop.appUser.profile.ProfileSecurityDTO;
import com.dinhson.sunshop.exception.ForgetPasswordEmailNotTrueException;
import com.dinhson.sunshop.exception.UserNotValidException;
import com.dinhson.sunshop.securityConfig.MyUserDetail;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.dinhson.sunshop.appUser.Role.USER;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private static Integer totalPages = 0;

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

    private Page<User> searchBy(Boolean isActive, Role role, String searchName, Integer pageIndex, Integer pageSize){
        if (isActive != null) {
            if (role != null && !searchName.isEmpty()) {
                // search by all
                return userRepository.searchUserByAll(isActive, role, searchName, PageRequest.of(pageIndex, pageSize));
            } else if (role != null) {
                // search by status and role
                return userRepository.searchUserByIsActiveAndRole(isActive, role, PageRequest.of(pageIndex, pageSize));
            } else if (!searchName.isEmpty()) {
                // search by status and search name
                return userRepository.searchUserByIsActiveAndSearch(isActive, searchName, PageRequest.of(pageIndex, pageSize));
            } else {
                // search by status
                return userRepository.searchUserByIsActive(isActive, PageRequest.of(pageIndex, pageSize));
            }
        } else {
            if (role != null && !searchName.isEmpty()) {
                // search by role and search name
                return userRepository.searchUserByRoleAndSearch(role, searchName, PageRequest.of(pageIndex, pageSize));
            } else if (role != null) {
                // search by role
                return userRepository.searchUserByRole(role, PageRequest.of(pageIndex, pageSize));
            } else if (!searchName.isEmpty()) {
                // search by search name
                return userRepository.searchByName(searchName, PageRequest.of(pageIndex, pageSize));
            } else {
                // get all
                return userRepository.getAll(PageRequest.of(pageIndex, pageSize));
            }
        }
    }

//    public List<UserDTO> getAll(){
//        return userRepository.getAll(PageRequest.of(0, 2)).getContent().stream()
//                .map(userDTOMapper)
//                .collect(Collectors.toList());
//    }

    private List<UserDTO> searchUsersAndMapToUserDTO(Boolean isActive, Role role, String searchName, Integer pageIndex, Integer pageSize){
        Page<User> users = searchBy(isActive, role, searchName, pageIndex, pageSize);
        totalPages = users.getTotalPages();
        return users.stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public List<UserDTO> searchUsers(String isActiveStr, String roleStr, String searchName, Integer pageIndex, Integer pageSize){
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
        return searchUsersAndMapToUserDTO(isActive, role, searchName, pageIndex, pageSize);
    }

    private User loginByEmailAndPassword(String email, String password){
        return userRepository.findUserByEmailAndPassword(email, password)
                .orElseThrow(() -> new IllegalArgumentException("Password is wrong!!!"));
    }

    public void changePassword(ProfileSecurityDTO profileSecurityDTO, MyUserDetail userDetail){
        if(!passwordEncoder.matches(profileSecurityDTO.password(), userDetail.getPassword())){
            throw new IllegalArgumentException("Password is wrong!");
        }

        if(profileSecurityDTO.newPassword().isEmpty() ||
                !profileSecurityDTO.newPassword().equals(profileSecurityDTO.rePassword())){
            throw new IllegalArgumentException("New Password and re-password must be the same!!!");
        }
        User user = userRepository.getUserByEmail(profileSecurityDTO.email())
                        .orElseThrow(() -> new IllegalArgumentException("Can not find user by email " + profileSecurityDTO.email()));
        user.setPassword(passwordEncoder.encode(profileSecurityDTO.newPassword()));
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
        User user = User.builder()
                .name(userDTO.getName())
                .email(userDTO.getEmail())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .enabled(false)
                .isActive(false)
                .role(USER)
                .build();
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
        User user = userRepository.findUserByEmailAndActiveIsTrue(userDTO.getEmail())
                        .orElseThrow(() -> new IllegalArgumentException("Can not find user!!!"));
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    public String checkAuthenticated(MyUserDetail user){
        if(user != null){
            switch (user.getRole()){
                case USER -> {
                    return "redirect:/home";
                }
                case ADMIN, MANAGER -> {
                    return "redirect:/admin/users";
                }
            }
        }
        return null;
    }

    public Integer getTotalPages() {
        return totalPages;
    }
}
