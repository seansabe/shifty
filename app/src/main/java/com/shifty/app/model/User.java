package com.shifty.app.model;

import java.util.HashSet;
import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "address")
	private String address;

	@Column(name = "phone")
	private String phone;

	@Column(name = "email")
	private String email;

	@Column(name = "role")
	private String role;

	@Column(name = "password")
	private String password;

	@OneToMany(mappedBy = "user",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<UserApplication> userApplications = new HashSet<>();
	
	@OneToMany(mappedBy = "jobPoster",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<Job> userJobs = new HashSet<>(); 

	public User() {
		
	}

	public User(String firstName, String lastName, String address, String phone, String email, String role,
			String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.role = role;
		this.password = password;
	}

	public long getUserId() {
		return userId;
	}

	public void setUser_id(long userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public void addUserApplication(UserApplication userApplication) {
		this.userApplications.add(userApplication);
		userApplication.setUser(this);
	}
	
	public Set<UserApplication> getUserApplications(){
		return userApplications;
	}
	
	public void setUserApplications(Set<UserApplication> userApplications) {
		this.userApplications = userApplications;
	}
	
	public void addUserJob(Job job) {
		this.userJobs.add(job);
		job.setUser(this);
	}
	
	public Set<Job> getUserJobs(){
		return userJobs;
	}
	
	public void setUserJobs(Set<Job> jobs) {
		this.userJobs = jobs;
	}

}
