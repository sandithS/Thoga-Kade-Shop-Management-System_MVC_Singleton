package model.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class CartItem {

    private String itemCode;
    private String description;
    private int quantity;
    private double unitPrice;
    private double discount;
    private double totalPrice;
}
