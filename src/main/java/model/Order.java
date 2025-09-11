package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class Order {

    private String OrderID;
    private String OrderDate;
    private String custID;

}
