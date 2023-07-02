package com.dinhson.sunshop.appUser;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserSecurityDTO {

    @NotEmpty(message = "Name can not be empty!!!")
    private String name;

    @Email(message = "Please enter right email format!!!")
    private String email;

    @NotEmpty(message = "Password can not be empty!!!")
    private String password;

    @NotEmpty(message = "Password can not be empty!!!")
    private String rePassword;

    public UserSecurityDTO(User user){
        name = user.getName();
        email = user.getEmail();
    }

    public boolean isEqualPassword(){
        return password.equals(rePassword);
    }
}
