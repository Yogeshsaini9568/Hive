package com.hive.models;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Booking {
    private int id;
    private int userId;
    private int buildingId;
    private String checkinDate;
    private String status;
    private Date bookingDate;
    private int numberOfMonths;
    private Date checkoutDate;
    private int amount;
    private int transitionID;
    private String paymentMode;
}
