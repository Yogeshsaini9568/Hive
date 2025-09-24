package com.hive.models;

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
public class Listing {
    private Long id;
    private String availabilityStatus;
    private Long buildingId;
    private Long roomId;
}
