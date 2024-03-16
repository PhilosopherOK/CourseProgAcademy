package org.homeWork.models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table(name = "DailyExchangeRate")
public class DailyExchangeRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(value = TemporalType.DATE)
    private Date exchangedate;
    private String currencyName;
    private Double rate;

    public DailyExchangeRate() {
    }

    public DailyExchangeRate(Date exchangedate, String currencyName, Double rate) {
        this.exchangedate = exchangedate;
        this.currencyName = currencyName;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(Date exchangedate) {
        this.exchangedate = exchangedate;
    }

    public String getCurrencyName() {
        return currencyName;
    }

    public void setCurrencyName(String currencyName) {
        this.currencyName = currencyName;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "DailyExchangeRate{" +
                "id=" + id +
                ", exchangedate=" + exchangedate +
                ", currencyName='" + currencyName + '\'' +
                ", rate=" + rate +
                '}';
    }
}
