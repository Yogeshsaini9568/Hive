package com.hive.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Long id;
    private Long userId;
    private Long buildingId;
    private int rating; // e.g., 1 to 5
    private String comment;
    private String title; // Short summary or headline of the review
    private Date createdAt; // Date when the review was created
    private Date updatedAt; // Date when the review was last updated
    private String sentiment; // e.g., "positive", "neutral", "negative"
}
