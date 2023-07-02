package com.dinhson.sunshop.confirmToken;


import com.dinhson.sunshop.appUser.User;
import com.dinhson.sunshop.exception.ConfirmTokenException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ConfirmTokenService {

    private final ConfirmTokenRepository confirmTokenRepository;

    private String creatToken(){
        return UUID.randomUUID().toString();
    }

    public String createConfirmToken(User user){
        String token = creatToken();

        ConfirmToken confirmToken = new ConfirmToken(
                token,
                LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(10),
                user
        );

        confirmTokenRepository.save(confirmToken);

        return token;
    }

    public ConfirmToken confirm(String token){
        Optional<ConfirmToken> confirmToken = confirmTokenRepository.getConfirmTokenByConfirmToken(token);
        
        if(!confirmToken.isPresent()) {
            throw new ConfirmTokenException("Can not find confirm token!!!");
        } else if (confirmToken.get().getConfirmedAt() != null) {
            throw new ConfirmTokenException("Token was used!!!");
        } else if (LocalDateTime.now().isAfter(confirmToken.get().getEndAt())) {
            throw new ConfirmTokenException("Token was died!!!");
        }

        return confirmToken.get();
    }

    public User checkForgetPasswordToken(String token){
        ConfirmToken confirmToken = confirm(token);
        return confirmToken.getUser();
    }

    public ConfirmToken updateConfirmDate(ConfirmToken confirmToken){
        return confirmTokenRepository.save(confirmToken);
    }
}
