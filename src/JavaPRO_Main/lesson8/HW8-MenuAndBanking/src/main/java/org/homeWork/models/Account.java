package org.homeWork.models;

import javax.persistence.*;

@Entity
@Table(name = "Accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @OneToOne(mappedBy = "account")
    private User user;
    @Column(name = "uah")
    private Double UAH;
    @Column(name = "eur")
    private Double EUR;
    @Column(name = "usd")
    private Double USD;

    public Account() {
        this.UAH = 0.0;
        this.EUR = 0.0;
        this.USD = 0.0;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public Transactions getTransaction() {
//        return new Transactions();
//    }
//    public class Transactions {
//        public void topUpOrWithdraw(Double howMuch, String currency, boolean trueAddFalseMinus) {
//            switch (currency.toUpperCase()) {
//                case "UAH":
//                    setUAH(trueAddFalseMinus ? getUAH() + howMuch : getUAH() - howMuch);
//                case "EUR":
//                    setEUR(trueAddFalseMinus ? getEUR() + howMuch : getEUR() - howMuch);
//                case "USD":
//                    setUSD(trueAddFalseMinus ? getUSD() + howMuch : getUSD() - howMuch);
//                default:
//                    System.out.println("Wrong currency !");
//            }
//        }
//
//        public void transferMoneyTo(Double howMuch, String currency, Account account) {
//            topUpOrWithdraw(howMuch, currency, false);
//
//            Transactions transactions = account.getTransaction();
//            transactions.topUpOrWithdraw(howMuch, currency, true);
//        }
//
//        //method of obtaining currency conversion rate
//        public double exchangeRateForThisCurrency(String fromCurrency, String toCurrency){
//            Class c = ExchangeRates.class;
//            double exchangeRateForThisCurrency;
//            try {
//                exchangeRateForThisCurrency = (double) c.getField((fromCurrency.toUpperCase()+"to"+toCurrency.toUpperCase())).get(c);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                throw new RuntimeException(e);
//            }
//            return exchangeRateForThisCurrency;
//        }
//        public void currencyConversion(Double howMuch, String fromCurrency, String toCurrency){
//            double exchangeRateForThisCurrency = exchangeRateForThisCurrency(fromCurrency, toCurrency);
//
//            System.out.println(exchangeRateForThisCurrency);
//            double resultingRecalculation = exchangeRateForThisCurrency * howMuch;
//            System.out.println(resultingRecalculation);
//            topUpOrWithdraw(howMuch, fromCurrency, false);
//            topUpOrWithdraw(resultingRecalculation, toCurrency, true);
//        }
//
//        public double getAllMoneyInUAH(){
//            double summaryMoney = 0;
//            double UAHFromUSD = exchangeRateForThisCurrency("USD", "UAH") * getUSD();
//            double UAHFromEUR = exchangeRateForThisCurrency("EUR", "UAH") * getEUR();
//            summaryMoney += getUAH() + UAHFromEUR + UAHFromUSD;
//
//            return summaryMoney;
//        }
//    }

    public void setUAH(Double UAH) {
        this.UAH = UAH;
    }

    public void setEUR(Double EUR) {
        this.EUR = EUR;
    }

    public void setUSD(Double USD) {
        this.USD = USD;
    }

    public Double getUAH() {
        return UAH;
    }

    public Double getEUR() {
        return EUR;
    }

    public Double getUSD() {
        return USD;
    }

    @Override
    public String toString() {
        return "Accounts{" +
                "UAH=" + UAH +
                ", EUR=" + EUR +
                ", USD=" + USD +
                '}';
    }
}
