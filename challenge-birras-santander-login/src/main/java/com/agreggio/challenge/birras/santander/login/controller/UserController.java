package com.agreggio.challenge.birras.santander.login.controller;

import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.login.dto.CredentialsDto;
import com.agreggio.challenge.birras.santander.login.service.impl.CredentialServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private CredentialServiceImpl credentialsServiceImpl;


	@ApiOperation(value = "Metodo encargado de devolver el clima para una meetUp",
			response = CredentialsDto.class,
			produces = "application/json")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "OK"),
			@ApiResponse(code = 500, message = "Internal Server Error"),
			@ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found")})
	@PostMapping("login")
	public ResponseEntity<CredentialsDto> login(@Valid @NotNull @RequestBody CredentialsDto credentialsDto) throws ServiceException {

		LOGGER.info("Login with username: " + credentialsDto.getUsername());

		return ResponseEntity.ok(credentialsServiceImpl.getCredentialsDto(credentialsDto));

	}


}


