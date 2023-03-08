package com.shifty.app.model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@SuppressWarnings("serial")
@Embeddable
@Data
// @NoArgsConstructor
public class UserApplicationId implements Serializable {

    private Long user_id;
    private Long application_id;

    public UserApplicationId(Long userId, Long applicationId) {
        this.user_id = userId;
        this.application_id = applicationId;
    }

    public UserApplicationId() {
    }

}
