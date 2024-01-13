package Tsymbaliuk_Oleksandr_JavaOOP.HW1;

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
        Network network = new Network();

        Phone phone1 = new Phone(380966666666L);
        Phone phone2 = new Phone(380944444444L);
        Phone phone3 = new Phone(380933333333L);

        phone1.registrationInTheMobileOperatorNetwork(network);
        phone2.registrationInTheMobileOperatorNetwork(network);


//        phone1.outgoingCall(380944444444L);
//        phone2.outgoingCall(380933333333L);
        phone3.outgoingCall(380955555555L);
    }
}

class Phone {
    private long number;
    private Network network;
    public Phone(long number) {
        this.number = number;
    }

    public void registrationInTheMobileOperatorNetwork(Network network) {
        network.addNumFromNetwork(this);
        this.network = network;
    }

    public void outgoingCall(long numberOfAnyPhone) {
        if (network == null) {
            System.out.println("The current phone has not been registered on the network");
        } else if (!network.checkToRegistration(numberOfAnyPhone)) {
            System.out.println("Phone number to which you are making network call not registered");
        } else {
            for (int i = 0; i < network.getNumbersWhoHaveNetwork().length; i++) {
                if(network.getNumbersWhoHaveNetwork()[i].getNumber() == numberOfAnyPhone){
                    network.getNumbersWhoHaveNetwork()[i].incomingCall(this.getNumber());
                    return;
                }
            }

        }
    }

    public void incomingCall(long number) {
        System.out.println("Dead " + this.number + " the number is calling you: " + number);
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "number=" + number +
                '}';
    }
}

class Network {
    private Phone[] numbersWhoHaveNetwork;

    public Network() {
        numbersWhoHaveNetwork = new Phone[10];
    }

    public void addNumFromNetwork(Phone phone){
        for (int i = 0; i < numbersWhoHaveNetwork.length; i++) {
            if(numbersWhoHaveNetwork[i] == null){
                numbersWhoHaveNetwork[i] = phone;
                return;
            }
        }
        System.out.println("Network if full");
    }
    public boolean checkToRegistration(long number){
        for (int i = 0; i < numbersWhoHaveNetwork.length; i++) {
            if(numbersWhoHaveNetwork[i] != null && numbersWhoHaveNetwork[i].getNumber() == number){
                return true;
            }
        }
        return false;
    }

    public Phone[] getNumbersWhoHaveNetwork() {
        return numbersWhoHaveNetwork;
    }
}
