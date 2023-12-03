package Tsimbaliuk.HW2;

public class Human {
    public Human() {
    }

    String name;
    String lastName;
    Gender gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Human(String name, String lastName, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "name = " + name + "" + "\n" + "lastName = " + lastName + "\n" + "gender = " + gender;
    }

}
