package com.agreggio.challenge.birras.santander.common.repository;

import com.agreggio.challenge.birras.santander.common.entitie.MeetUp;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


import java.util.Date;
import java.util.Optional;


@Repository
public interface MeetUpRepository extends CrudRepository<MeetUp, Long> {

    Optional<MeetUp> findMeetUpByIdAndDeleteDateIsNull(Long id);

    Optional<MeetUp> findMeetUpByEventDateAndDeleteDateIsNull(Date eventDate);


}
