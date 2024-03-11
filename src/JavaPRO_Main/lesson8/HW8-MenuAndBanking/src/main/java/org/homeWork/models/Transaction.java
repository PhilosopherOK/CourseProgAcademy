package org.homeWork.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name = "nameOfTransaction")
    private String nameOfTransaction;
    @OneToOne
    @JoinColumn(name = "from_user_id")
    private User from;
    @OneToOne
    @JoinColumn(name = "to_user_id")
    private User to;
    @Column(name = "howMuch")
    private Double howMuch;

    public Transaction() {
    }


    public void topUpOrWithdraw(User user, Double howMuch, String currency, boolean trueAddFalseMinus) {
        from = user;
        this.howMuch = howMuch;
        nameOfTransaction = "topUpOrWithdraw";
        Account account = user.getAccount();
        switch (currency.toUpperCase()) {
            case "UAH":
                account.setUAH(trueAddFalseMinus ? account.getUAH() + howMuch : account.getUAH() - howMuch);
                break;
            case "EUR":
                account.setEUR(trueAddFalseMinus ? account.getEUR() + howMuch : account.getEUR() - howMuch);
                break;
            case "USD":
                account.setUSD(trueAddFalseMinus ? account.getUSD() + howMuch : account.getUSD() - howMuch);
                break;
            default:
                System.out.println("Wrong currency !");
        }
    }

    public void transferMoneyTo(Double howMuch, String currency, User from, User to) {
        topUpOrWithdraw(from, howMuch, currency, false);
        topUpOrWithdraw(to, howMuch, currency, true);
        this.from = from;
        this.to = to;
        this.howMuch = howMuch;
        nameOfTransaction = "transferMoneyTo";
    }

    //method of obtaining currency conversion rate
    private Double exchangeRateForThisCurrency(List<ExchangeRates> exchangeRatesList, String fromCurrency, String toCurrency) {
        for (ExchangeRates er : exchangeRatesList) {
            if (er.getNameOfMoney().equals(fromCurrency + "to" + toCurrency)){
                return er.getValue();
            }
        }
        return null;
    }

    public void currencyConversion(User user, Double howMuch, String fromCurrency, String toCurrency, List<ExchangeRates> exchangeRatesList) {
        double exchangeRateForThisCurrency = exchangeRateForThisCurrency(exchangeRatesList, fromCurrency, toCurrency);

        double resultingRecalculation = exchangeRateForThisCurrency * howMuch;

        topUpOrWithdraw(user, howMuch, fromCurrency, false);
        topUpOrWithdraw(user, resultingRecalculation, toCurrency, true);
        from = user;
        this.howMuch = howMuch;
        nameOfTransaction = "currencyConversion";
    }

    public double getAllMoneyInUAH(User user,  List<ExchangeRates> exchangeRatesList) {
        double summaryMoney = 0;
        Account account = user.getAccount();
        double UAHFromUSD = exchangeRateForThisCurrency(exchangeRatesList,"USD", "UAH") * account.getUSD();
        double UAHFromEUR = exchangeRateForThisCurrency(exchangeRatesList,"EUR", "UAH") * account.getEUR();
        summaryMoney += account.getUAH() + UAHFromUSD + UAHFromEUR;
        howMuch = summaryMoney;

        from = user;
        nameOfTransaction = "getAllMoneyInUAH";
        return summaryMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameOfTransaction() {
        return nameOfTransaction;
    }

    public void setNameOfTransaction(String nameOfTransaction) {
        this.nameOfTransaction = nameOfTransaction;
    }

    public User getFrom() {
        return from;
    }

    public void setFrom(User from) {
        this.from = from;
    }

    public User getTo() {
        return to;
    }

    public void setTo(User to) {
        this.to = to;
    }

    public Double getHowMuch() {
        return howMuch;
    }

    public void setHowMuch(Double howMuch) {
        this.howMuch = howMuch;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", nameOfTransaction='" + nameOfTransaction + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", howMuch=" + howMuch +
                '}';
    }
}
