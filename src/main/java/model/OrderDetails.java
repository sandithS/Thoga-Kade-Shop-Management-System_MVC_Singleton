package model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class OrderDetails {

    private String OrderID;
    private String ItemCode;
    private Integer OrderQTY;
    private Integer Discount;

}
