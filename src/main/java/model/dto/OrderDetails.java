package model.dto;

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
    private double Discount;

}
