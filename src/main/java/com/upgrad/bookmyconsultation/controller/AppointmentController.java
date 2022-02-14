package com.upgrad.bookmyconsultation.controller;

import com.upgrad.bookmyconsultation.entity.Appointment;
import com.upgrad.bookmyconsultation.exception.InvalidInputException;
import com.upgrad.bookmyconsultation.repository.AppointmentRepository;
import com.upgrad.bookmyconsultation.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    AppointmentRepository appointmentRepository;

    //create a method post method named bookAppointment with return type ReponseEntity
    //method has paramter of type Appointment, use RequestBody Annotation for mapping

    //save the appointment details to the database and save the response from the method used
    //return http response using ResponseEntity

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity bookAppointment(@RequestBody Appointment reqAppointment) throws InvalidInputException {

        String savedAppointment = appointmentService.appointment(reqAppointment);

        return new ResponseEntity(savedAppointment, HttpStatus.CREATED);
    }


    //create a get method named getAppointment with return type as ResponseEntity
    //method has appointmentId of type String. Use PathVariable annotation to identity appointment using the parameter defined

    //get the appointment details using the appointmentId
    //save the response
    //return the response as an http response

    @GetMapping("/appointmentsId/{appointmentId}")
    public ResponseEntity getAppointment(@PathVariable String appointmentId) {

        Appointment appointmentDetails = appointmentService.getAppointment(appointmentId);

        appointmentRepository.save(appointmentDetails);
        return new ResponseEntity(appointmentDetails, HttpStatus.CREATED);
    }

}