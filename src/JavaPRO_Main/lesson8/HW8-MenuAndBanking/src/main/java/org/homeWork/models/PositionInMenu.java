package org.homeWork.models;



import javax.persistence.*;

@Entity
@Table(name = "Menu")
public class PositionInMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "dish_name", nullable = false, unique = true)
    private String dishName;
    @Column(name = "price", nullable = false)
    private Integer price;
    @Column(name = "weight", nullable = false)
    private Integer weight;
    @Column(name = "discount", nullable = false)
    private Boolean discount;

    public PositionInMenu() {
    }

    public PositionInMenu(String dishName, Integer price, Integer weight, Boolean discount) {
        this.dishName = dishName;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDishName() {
        return dishName;
    }

    public void setDishName(String dishName) {
        this.dishName = dishName;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Boolean getDiscount() {
        return discount;
    }

    public void setDiscount(Boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dishName='" + dishName + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
