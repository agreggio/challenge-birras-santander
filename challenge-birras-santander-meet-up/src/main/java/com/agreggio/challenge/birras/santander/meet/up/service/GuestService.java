package com.agreggio.challenge.birras.santander.meet.up.service;


import com.agreggio.challenge.birras.santander.common.entitie.Guest;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.dto.GuestDto;

public interface GuestService {

    /**
     * Get total guests by meetUpId
     *
     * @param meetUpId long
     * @return int
     */
    int getTotalGuests(long meetUpId) throws ServiceException;

    /**
     * Save a guest
     *
     * @param meetUpId long
     * @return {@link GuestDto}
     */
    GuestDto saveGuest(long meetUpId) throws ServiceException;

    /**
     * Update a guest
     *
     * @param meetUpId long
     * @return {@link GuestDto}
     */
    GuestDto updateGuest(long meetUpId) throws ServiceException;

    /**
     * Find guest by meetUpId and userId
     *
     * @param meetUpId long
     * @param userId long
     * @return {@link Guest}
     */
    Guest findGuest(long meetUpId, long userId) throws ServiceException;

}
