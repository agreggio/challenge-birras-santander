package com.agreggio.challenge.birras.santander.login.service;

import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;


public interface UserService {

	/**
	 * Find User by id
	 *
	 * @param username {@link String}
	 * @param password {@link String}
	 * @return {@link User}
	 */
	User findUser(String username, String password) throws ServiceException;

	/**
	 * Find User by id
	 *
	 * @param user {@link User}
	 * @return {@link User}
	 */
	User createUser(User user);
}
