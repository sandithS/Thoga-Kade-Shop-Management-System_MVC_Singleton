package model;

import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Customer {

    private String custID;
    private String custTitle;
    private String custName;
    private String dob;
    private double salary;
    private String address;
    private String city;
    private String province;
    private String postalCode;

}
