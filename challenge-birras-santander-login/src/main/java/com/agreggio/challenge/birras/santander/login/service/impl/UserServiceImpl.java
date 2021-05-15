package com.agreggio.challenge.birras.santander.login.service.impl;

import com.agreggio.challenge.birras.santander.common.Util.EncodeUtil;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.common.repository.UserRepository;
import com.agreggio.challenge.birras.santander.login.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService{

	private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User findUser(String username, String password) throws ServiceException {

		LOGGER.info("Find user by username: "+ username + "and password");

		Optional<User> optionalUser = userRepository.findUserByUserNameAndDeleteDateIsNull(username);

		if(!optionalUser.isPresent()) {

			LOGGER.warn("Not found user by username: "+ username);
			throw new ServiceException("No se encontro usuario: " + username );
		}

		User user = optionalUser.get();

		if(!user.getPassword().equals(EncodeUtil.encode(password))) {
			LOGGER.warn("Wrong password");
			throw new ServiceException("El password es incorrecto");
		}

		return user;
	}


	@Override
	public User createUser(User user) {

		LOGGER.info("Create a new user: "+ user.getUserName());

		return userRepository.save(user);

	}
}
