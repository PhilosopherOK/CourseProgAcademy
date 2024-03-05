package org.example.models;

import org.example.utils.Id;

/*
Спроектувати базу "Квартири". Кожен запис
у базі містить дані про квартиру (район,
адреса, площа, кільк. кімнат, ціна). Зробити
можливість вибірки квартир зі списку за
параметрів
 */
public class Apartment {
    @Id
    private Long id;
    private String district;
    private String addresses;
    private Integer area;
    private Integer roomCount;
    private Integer price;

    public Apartment() {
    }

    public Apartment(String district, String addresses, Integer area, Integer roomCount, Integer price) {
        this.district = district;
        this.addresses = addresses;
        this.area = area;
        this.roomCount = roomCount;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public void setRoomCount(Integer roomCount) {
        this.roomCount = roomCount;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "id=" + id +
                ", district='" + district + '\'' +
                ", addresses='" + addresses + '\'' +
                ", area=" + area +
                ", roomCount=" + roomCount +
                ", price=" + price +
                '}';
    }
}
