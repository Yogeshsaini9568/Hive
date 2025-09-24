package com.hive.models;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int userId;
    private int BuildingId;
    private int rating; // e.g., 1 to 5
    private String comment;
    private String title; // Short summary or headline of the review
    private Date createdAt; // Date when the review was created
    private Date updatedAt; // Date when the review was last updated
        // Optionally, you can also track user sentiment:
        private String sentiment; // e.g., "positive", "neutral", "negative"

}