package com.agreggio.challenge.birras.santander.common.repository;

import com.agreggio.challenge.birras.santander.common.entitie.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	Optional<User> findUserByUserNameAndDeleteDateIsNull(String userName);

	Optional<User> findUserByIdAndDeleteDateIsNull(Long id);



}
