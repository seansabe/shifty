package com.shifty.app.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "job")
public class Job {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "posting_date")
	@Temporal(TemporalType.DATE)
    private LocalDate postingDate;
	
	@Column(name = "hour_rate")
	private double hourRate;
	
	@Column(name = "kind_of_job")
	private String kindOfJob;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "job_start_date")
	@Temporal(TemporalType.DATE)
	private LocalDate jobStartDate;
	
	@Column(name = "job_finish_date")
	@Temporal(TemporalType.DATE)
	private LocalDate jobFinishDate;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false) 
	@JsonIgnore
	private User jUser; 
	
	@OneToMany(mappedBy = "aJob",
			cascade = CascadeType.ALL,
			fetch = FetchType.LAZY)
	private Set<Application> applications = new HashSet<>();
	
	//Contructors and getters, setters
	public Job() {
		
	}
	
	public Job(User jUser, String title, LocalDate postingDate, double hourRate, String kindOfJob,
			String description, LocalDate jobStartDate, LocalDate jobFinishDate) {
		this.jUser = jUser;
		this.title = title;
		this.postingDate = postingDate;
		this.hourRate = hourRate;
		this.kindOfJob = kindOfJob;
		this.description = description;
		this.jobStartDate = jobStartDate;
		this.jobFinishDate = jobFinishDate;
	}

	public Job(String title, LocalDate postingDate, double hourRate, String kindOfJob,
			String description, LocalDate jobStartDate, LocalDate jobFinishDate) {
		this.title = title;
		this.postingDate = postingDate;
		this.hourRate = hourRate;
		this.kindOfJob = kindOfJob;
		this.description = description;
		this.jobStartDate = jobStartDate;
		this.jobFinishDate = jobFinishDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return jUser;
	}

	public void setUser(User jUser) {
		this.jUser = jUser;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public LocalDate getPostingDate() {
		return postingDate;
	}

	public void setPostingDate(LocalDate postingDate) {
		this.postingDate = postingDate;
	}

	public double getHourRate() {
		return hourRate;
	}

	public void setHourRate(double hourRate) {
		this.hourRate = hourRate;
	}

	public String getKindOfJob() {
		return kindOfJob;
	}

	public void setKindOfJob(String kindOfJob) {
		this.kindOfJob = kindOfJob;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getJobStartDate() {
		return jobStartDate;
	}

	public void setJobStartDate(LocalDate jobStartDate) {
		this.jobStartDate = jobStartDate;
	}

	public LocalDate getJobFinishDate() {
		return jobFinishDate;
	}

	public void setJobFinishDate(LocalDate jobFinishDate) {
		this.jobFinishDate = jobFinishDate;
	}
	
	public void addApplication(Application application) {
		this.applications.add(application);
		application.setJob(this);
	}
	
	public Set<Application> getApplications(){
		return applications;
	}
	
	public void setApplications(Set<Application> applications) {
		this.applications = applications;
	}
}
