package viscarra.ronald.Product;

import java.util.Date;

public class Product {
    private int id;
    private String description;
    private Date expiresAt;
    private double price;

    private boolean enable;

    public Product(int id, String description, Date expiresAt, double price) {
        this.id = id;
        this.description = description;
        this.expiresAt = expiresAt;
        this.price = price;
        this.enable = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Date expiresAt) {
        this.expiresAt = expiresAt;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public String toString() {
        return "Product [description=" + description + ", enable=" + enable +
                ", expiresAt=" + expiresAt + ", id=" + id
                + ", price=" + price + "]";
    }

}
