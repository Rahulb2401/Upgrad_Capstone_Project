package com.upgrad.bookmyconsultation.service;

import com.upgrad.bookmyconsultation.entity.Doctor;
import com.upgrad.bookmyconsultation.entity.Rating;
import com.upgrad.bookmyconsultation.repository.DoctorRepository;
import com.upgrad.bookmyconsultation.repository.RatingsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


@Service
public class RatingsService {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RatingsRepository ratingsRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    //create a method name submitRatings with void return type and parameter of type Rating
    //set a UUID for the rating
    //save the rating to the database
    //get the doctor id from the rating object
    //find that specific doctor with the using doctor id
    //modify the average rating for that specific doctor by including the new rating
    //save the doctor object to the database

    public void submitRatings(Rating rating) {

        Rating updateRating = new Rating();

        updateRating.setId(UUID.randomUUID().toString());
        updateRating.setRating(rating.getRating());

        ratingsRepository.save(updateRating);


        List findRating = ratingsRepository.findByDoctorId(rating.getDoctorId());

        Integer sum = 0;
        Integer avg = 0;

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < findRating.size(); i++) {
            map.put(i, (Integer) findRating.get(i));
        }


        for (int j = 0; j < map.size(); j++) {
            if (map.containsKey(j)) {
                sum += map.get(j);
            }
        }
        System.out.println("Sum of Rating : "+ sum);

        avg = Integer.parseInt(String.valueOf(sum / map.size()));
        System.out.println("Average :" + avg);
        updateRating.setRating(avg);


        Doctor doctorInfo = new Doctor();

        doctorInfo.setId(rating.getDoctorId());
        doctorInfo.setRating(Double.parseDouble(String.valueOf(avg)));
        doctorRepository.save(doctorInfo);


    }
}
