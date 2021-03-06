package entities;

import enums.Category;

public final class Gift {
    private String productName;
    private Double price;
    private Category category;
    private Integer quantity;

    public Gift() {
    }

    public Gift(final Gift gift) {
        productName = gift.productName;
        price = gift.price;
        category = gift.category;
        quantity = gift.quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(final Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Gift{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category=" + category
                + ", quantity=" + quantity + '}';
    }
}
