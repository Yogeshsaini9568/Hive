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
public class Enquiry {
    private Long id;
    private String name;
    private String email;
    private String message;
    private String phone;
}
