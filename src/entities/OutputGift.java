package entities;

import enums.Category;

public class OutputGift {
    private String productName;
    private Double price;
    private Category category;

    public OutputGift() {
    }

    public OutputGift(Gift gift) {
        productName = gift.getProductName();
        price = gift.getPrice();
        category = gift.getCategory();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
