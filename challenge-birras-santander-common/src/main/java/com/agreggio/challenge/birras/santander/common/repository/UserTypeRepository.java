package com.agreggio.challenge.birras.santander.common.repository;

import com.agreggio.challenge.birras.santander.common.entitie.UserType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserTypeRepository extends CrudRepository<UserType, Long> {

    Optional<UserType> findUserTypeByIdAndDeleteDateIsNull(Long id);

}
