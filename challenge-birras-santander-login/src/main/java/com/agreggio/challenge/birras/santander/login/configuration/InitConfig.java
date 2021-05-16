package com.agreggio.challenge.birras.santander.login.configuration;

import javax.annotation.PostConstruct;


import com.agreggio.challenge.birras.santander.common.Util.EncodeUtil;
import com.agreggio.challenge.birras.santander.common.entitie.User;
import com.agreggio.challenge.birras.santander.common.entitie.UserType;
import com.agreggio.challenge.birras.santander.common.entitie.UserTypeEnum;
import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.login.service.UserTypeService;
import com.agreggio.challenge.birras.santander.login.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class InitConfig {

	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private UserTypeService userTypeService;


	@PostConstruct
	public void populateTable(){

		populateUserType();

		populateUser();

	}

	private void populateUser() {

		try {
			userService.createUser(new User(
					"Santander",
					"santander",
					"Rio",
					"santander1234",
					userTypeService.findUserType(UserTypeEnum.ADMIN.getUserTypeId())));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		try {
			userService.createUser(new User(
					"Marge",
					"msimpson",
					"Simpson",
					"¡Mmmhhh!",
					userTypeService.findUserType(UserTypeEnum.USER.getUserTypeId())));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		try {
			userService.createUser(new User(
					"Homero",
					"hsimpson",
					"Simpson",
					"Donuts",
					userTypeService.findUserType(UserTypeEnum.USER.getUserTypeId())));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

		try {
			userService.createUser(new User(
					"Bart",
					"bsimpson",
					"Simpson",
					"hayCaramba",
					userTypeService.findUserType(UserTypeEnum.USER.getUserTypeId())));
		} catch (ServiceException e) {
			e.printStackTrace();
		}

	}

	private void populateUserType(){
		userTypeService.newUserType(new UserType(1L, "ADMIN"));

		userTypeService.newUserType(new UserType(2L, "USER"));

	}



}
