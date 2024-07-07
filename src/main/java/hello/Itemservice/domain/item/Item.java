package hello.Itemservice.domain.item;

import lombok.Data;

@Data
public class Item {
    private Long Id;
    private String ItemName;
    private Integer price;
    private Integer quantity;


    public Item() {
    }

    public Item(String itemName, Integer price, Integer quantity) {
        ItemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
