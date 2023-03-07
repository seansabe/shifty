package com.shifty.app.model;

import jakarta.persistence.Embeddable;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
public class UserApplicationId implements Serializable {
    private Long userId;
    private Long applicationId;

    public UserApplicationId(Long userId, Long applicationId) {
        this.userId = userId;
        this.applicationId = applicationId;
    }
}
