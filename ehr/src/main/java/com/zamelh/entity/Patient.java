package com.zamelh.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;


@Entity
@Table(name="patients")
public class Patient {

	// Attributes Definition "Column Names" :
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	
	@NotEmpty(message = "Please enter the first name")
	@Pattern(regexp="[^0-9]*")
	private String firstName;
	
	
	@NotEmpty(message = "Please enter the second name")
	@Pattern(regexp="[^0-9]*")
	private String secondName;
	
	
	@NotEmpty(message = "Please enter the last name")
	@Pattern(regexp="[^0-9]*")
	private String lastName;
	
	
	@NotNull
	@Temporal(TemporalType.DATE)
	private Date dob;
	
	@NotNull(message = "Please enter the heart rate")
	@Min(0)
	@Max(300)
	private int heartRate;
	
	@NotNull(message = "Please enter the temperature")
	private int temperature;
	
	@NotNull(message = "Please enter the systolic blood pressure")
	private int systolicBP;
	
	@NotNull(message = "Please enter the diastolic blood pressure")
	private int diastolicBP;

	
	// Getters & Setters:
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getHeartRate() {
		return heartRate;
	}

	public void setHeartRate(int heartRate) {
		this.heartRate = heartRate;
	}

	public int getTemperature() {
		return temperature;
	}

	public void setTemperature(int temperature) {
		this.temperature = temperature;
	}

	public int getSystolicBP() {
		return systolicBP;
	}

	public void setSystolicBP(int systolicBP) {
		this.systolicBP = systolicBP;
	}

	public int getDiastolicBP() {
		return diastolicBP;
	}

	public void setDiastolicBP(int diastolicBP) {
		this.diastolicBP = diastolicBP;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", secondName=" + secondName + ", lastName="
				+ lastName + ", dob=" + dob + ", heartRate=" + heartRate + ", temperature=" + temperature
				+ ", systolicBP=" + systolicBP + ", diastolicBP=" + diastolicBP + "]";
	}
	
	/* NOTE: No need to to annotate any of the attributes with @Column annotation as long as they have
	 the same name in the database.
	 */	

}
