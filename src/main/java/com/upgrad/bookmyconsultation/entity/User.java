package com.upgrad.bookmyconsultation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;


//Mark it with Data, Entity, Builder, NoArgsConstructor, AllArgsConstructor
//create a class named User
	//create firstName of type String
	//create lastName of type String
	//create dob of type String
	//create mobile of type String
	//create primary key 'emailId' of type String
	//create password of type String
	//create createdDate of type String
	//create salt of type String
	//all the mentioned members must be private

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String firstName;
    String lastName;
    String dob;
    String mobile;

    @Id
    String emailId;

    String password;
    String createdDate;
    String salt;


}