package org.homeWork.models;

import java.util.Date;

public class JsonObj {
    private Date exchangedate;
    private Long r030;
    private String cc;
    private String txt;
    private String enname;
    private Double rate;
    private Long units;
    private Double rate_per_unit;
    private Long group;
    private Date calcdate;
    //"exchangedate":"10.03.2024",
    // "r030":840,
    // "cc":"USD",
    // "txt":"Долар США",
    // "enname":"US Dollar",
    // "rate":38.0836,
    // "units":1,
    // "rate_per_unit":38.0836,
    // "group":"1",
    // "calcdate":"07.03.2024"

    public JsonObj() {
    }


    public Date getExchangedate() {
        return exchangedate;
    }

    public void setExchangedate(Date exchangedate) {
        this.exchangedate = exchangedate;
    }

    public Date getCalcdate() {
        return calcdate;
    }

    public void setCalcdate(Date calcdate) {
        this.calcdate = calcdate;
    }

    public Long getR030() {
        return r030;
    }

    public void setR030(Long r030) {
        this.r030 = r030;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public Long getUnits() {
        return units;
    }

    public void setUnits(Long units) {
        this.units = units;
    }

    public Double getRate_per_unit() {
        return rate_per_unit;
    }

    public void setRate_per_unit(Double rate_per_unit) {
        this.rate_per_unit = rate_per_unit;
    }

    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }


    @Override
    public String toString() {
        return "DailyExchangeRate{" +
                "exchangedate=" + exchangedate +
                ", r030=" + r030 +
                ", cc='" + cc + '\'' +
                ", txt='" + txt + '\'' +
                ", enname='" + enname + '\'' +
                ", rate=" + rate +
                ", units=" + units +
                ", rate_per_unit=" + rate_per_unit +
                ", group=" + group +
                ", calcdate=" + calcdate +
                '}';
    }


}
