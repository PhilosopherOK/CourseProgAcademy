package Tsimbaliuk.HW0;

import java.util.Random;

/*
1) Создайте класс Phone (Телефон) одним из свойств должен быть его номер. Создайте
класс Network (сеть мобильного оператора). В классе Телефон должны быть описаны
следующие методы:
● Регистрация в сети мобильного оператора
● Метод реализующий исходящий звонок. Данный метод принимает один параметр
(описывающий номер мобильного телефона). Логика работы этого метода такова:
если текущий телефон не прошел регистрацию в сети, то закончить работу метода с
сообщением об этом. Если текущий телефон прошел регистрацию и в сети также
зарегистрирован телефон на номер которого совершается вызов, то вызвать метод
входящий звонок у того телефона. Если телефон на номер которого вы совершаете
вызов в сети не зарегистрирован, то закончить работу метода с сообщением об
этом.
● Метод реализующий входящий звонок. Принимает параметр в виде номера с
которого произвели вызов на текущий. Вывести сообщение вида вам звонит номер
такой то.
 */
public class Main3 {
    public static void main(String[] args) {
        Phone phone1 = new Phone(380966666666L);
        Phone phone2 = new Phone(380944444444L);
        Phone phone3 = new Phone(380933333333L);

        phone1.outgoingCall(380944444444L);
        phone2.outgoingCall(380933333333L);
        phone3.outgoingCall(380955555555L);
    }
}

class Phone {
    private long number;

    public Phone(long number) {
        this.number = number;
    }

    public boolean registrationInTheMobileOperatorNetwork() {
        Network network = new Network(new long[]{380966666666L, 380955555555L, 380944444444L});

        long[] numbers = network.getNumbersWhoHaveNetwork();
        for (int i = 0; i < numbers.length; i++) {
            if (number == numbers[i]) {
                return true;
            }
        }
        return false;
    }

    public void outgoingCall(long numberOfAnyPhone) {
        Phone phone = new Phone(numberOfAnyPhone);
        if (!this.registrationInTheMobileOperatorNetwork()) {
            System.out.println("The current phone has not been registered on the network");
        } else if (!phone.registrationInTheMobileOperatorNetwork()) {
            System.out.println("Phone number to which you are making network call not registered");
        } else {
            phone.incomingCall(this.number);
        }
    }

    public void incomingCall(long number) {
        System.out.println("The number is calling you: " + number);
    }
}

class Network {
    private long[] numbersWhoHaveNetwork;

    public Network(long[] numbersWhoHaveNetwork) {
        this.numbersWhoHaveNetwork = numbersWhoHaveNetwork;
    }

    public long[] getNumbersWhoHaveNetwork() {
        return numbersWhoHaveNetwork;
    }
}
