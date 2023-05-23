package by.issoft.domain;

public class Product {
    private String name;
    private Double price;
    private Double rate;

    public Product(String name, Double price, Double rate) {
        this.name = name;
        this.price = price;
        this.rate = rate;
    }

    public Product() {

    }

    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }

    public Double getRate() {
        return rate;
    }

    @Override
    public String toString() {
        String productInfo = String.format("Name: '%s', Price: %s, Rate: %s", name, price, rate);
        return productInfo;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(Double price) {
        this.price = price;
    }
    public void setRate(Double rate) {
        this.rate = rate;
    }
}

