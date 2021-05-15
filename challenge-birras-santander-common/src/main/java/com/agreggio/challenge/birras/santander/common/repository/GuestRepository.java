package com.agreggio.challenge.birras.santander.common.repository;

import com.agreggio.challenge.birras.santander.common.entitie.Guest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface GuestRepository extends CrudRepository<Guest, Long> {

    int countByMeetUpIdAndDeleteDateIsNull(Long meetUpId);

    Optional<Guest> findByMeetUpIdAndUserIdAndDeleteDateIsNull(Long MeetUpId, Long UserId);

}
