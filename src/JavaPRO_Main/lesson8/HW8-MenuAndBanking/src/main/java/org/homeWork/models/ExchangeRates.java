package org.homeWork.models;

import javax.persistence.*;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRates {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nameOfMoney", nullable = false)
    private String nameOfMoney;

    @Column(nullable = false)
    private Double value;

    public ExchangeRates() {
    }

    public ExchangeRates(String nameOfMoney, Double value) {
        this.nameOfMoney = nameOfMoney;
        this.value = value;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfMoney() {
        return nameOfMoney;
    }

    public void setNameOfMoney(String nameOfMoney) {
        this.nameOfMoney = nameOfMoney;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "ExchangeRates{" +
                "id=" + id +
                ", nameOfMoney='" + nameOfMoney + '\'' +
                ", value=" + value +
                '}';
    }
    //    public final static Double UAHtoUSD = 0.026;
//    public final static Double USDtoUAH = 38.0836;
//    public final static Double EURtoUAH = 41.4883;
//    public final static Double UAHtoEUR = 0.024;
//    public final static Double EURtoUSD = 1.09;
//    public final static Double USDtoEUR = 0.91;
}
