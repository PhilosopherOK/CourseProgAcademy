package hw.example.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private String login;
    private String pass;
    private AtomicInteger banana;
    private AtomicInteger orange;
    private AtomicInteger sleep;
    private AtomicInteger run;

    public Client() {
    }

    public Client(String login, String pass) {
        this.login = login;
        this.pass = pass;
        banana = new AtomicInteger(0);
        orange = new AtomicInteger(0);
        sleep = new AtomicInteger(0);
        run = new AtomicInteger(0);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public int getBanana() {
        return banana.get();
    }

    public int getOrange() {
        return orange.get();
    }

    public int getSleep() {
        return sleep.get();
    }

    public int getRun() {
        return run.get();
    }

    public void addedRun() {
        run.incrementAndGet();
    }

    public void addedSleep() {
        sleep.incrementAndGet();
    }

    public void addedBanana() {
        banana.incrementAndGet();
    }

    public void addedOrange() {
        orange.incrementAndGet();
    }

    @Override
    public String toString() {
        return "Client{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                ", banana=" + banana +
                ", orange=" + orange +
                ", sleep=" + sleep +
                ", run=" + run +
                '}';
    }
}
