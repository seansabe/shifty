package com.shifty.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

//import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Data;

@Entity
@Table(name = "user_application")
@Data
@NoArgsConstructor
//@IdClass(UserApplicationId.class)
// @AllArgsConstructor
public class UserApplication {

	@EmbeddedId
	private UserApplicationId userApplicationId;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId("user_id")
	@JoinColumn(name = "user_id", nullable = false)
	@JsonIgnore 
	private User user;

	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@MapsId("application_id")
	@JoinColumn(name = "application_id", nullable = false)
	@JsonIgnore 
	private Application application;
	
	
    public UserApplication() {}
    
    public UserApplication(User user, Application application) {
        this.user = user;
        this.application = application;
        setUserApplicationId();
    }
    private void setUserApplicationId() {
    	this.userApplicationId= new UserApplicationId(this.user.getUserId(), this.application.getApplicationId());
    }
 
    public void setUser(User user) {
    	this.user = user;
    }
    
    public void setApplication(Application application){
    	this.application = application;
    }

	public User getUser() {
		return user;
	}

}

