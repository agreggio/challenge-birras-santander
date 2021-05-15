package com.agreggio.challenge.birras.santander.meet.up.controller;


import com.agreggio.challenge.birras.santander.common.exception.ServiceException;
import com.agreggio.challenge.birras.santander.meet.up.dto.MeetUpDto;
import com.agreggio.challenge.birras.santander.meet.up.service.GuestService;
import com.agreggio.challenge.birras.santander.meet.up.service.MeetUpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/")
public class MeetUpController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MeetUpController.class);

    @Autowired
    MeetUpService meetUpService;

    @Autowired
    GuestService guestService;


    @GetMapping("amountBeer/{meetUpId}")
    @ApiOperation(value = "Retorna la cantidad de cajas de cervezas para aprovisionar la meetup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> amountBeer(@PathVariable("meetUpId") long meetUpId) throws ServiceException {

		LOGGER.info("Get amount of beer for meetUpId: "+meetUpId);

        return ResponseEntity.ok(meetUpService.getAmountBeer(meetUpId));

    }

    @GetMapping("weather/{meetUpId}")
    @ApiOperation(value = "Retorna la temperatura del dia de la meetup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> weather(@PathVariable("meetUpId") long meetUpId) throws ServiceException {

		LOGGER.info("Return weather by meetUpId: " + meetUpId);

        return ResponseEntity.ok(meetUpService.getTemperatureDay(meetUpId));

    }

    @GetMapping("confirmGuest/meetUp/{meetUpId}")
    @ApiOperation(value = "Confirma asistencia a la meetup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> confirmGuest(@PathVariable("meetUpId") long meetUpId) throws ServiceException {

        LOGGER.info("Confirm guest");

        return ResponseEntity.ok(guestService.saveGuest(meetUpId));

    }


    @GetMapping("userCheckIn/meetUp/{meetUpId}")
    @ApiOperation(value = "Confirma que el usuario asistio a una meetup")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> userCheckIn(@PathVariable("meetUpId") long meetUpId) throws ServiceException {

		LOGGER.info("CheckIn in MeetUp: "+meetUpId);

        return ResponseEntity.ok(guestService.updateGuest(meetUpId));

    }

    @PostMapping("newMeetUp")
    @ApiOperation(value = "Crea una meetUp")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 500, message = "Internal Server Error"),
            @ApiResponse(code = 400, message = "Bad Request"),
            @ApiResponse(code = 404, message = "Not Found")})
    public ResponseEntity<?> newMeetUp(@RequestBody @Valid @NotNull MeetUpDto meetUpDto) throws ServiceException {

        LOGGER.info("Create a new meetUp");

        return ResponseEntity.ok(meetUpService.createMeetUp(meetUpDto));

    }


}


