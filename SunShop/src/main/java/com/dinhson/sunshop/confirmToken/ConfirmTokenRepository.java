package com.dinhson.sunshop.confirmToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface ConfirmTokenRepository extends CrudRepository<ConfirmToken, Integer> {

    Optional<ConfirmToken> getConfirmTokenByConfirmToken(String confirmToken);


}
