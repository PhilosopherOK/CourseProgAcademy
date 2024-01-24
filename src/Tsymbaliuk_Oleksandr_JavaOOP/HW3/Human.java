package Tsymbaliuk_Oleksandr_JavaOOP.HW3;

import java.util.Objects;

public class Human {
    private String name;
    private String lastName;
    private Gender gender;
    private int age;

    public Human() {
    }

    public Human(String name, String lastName, Gender gender) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
    }

    public Human(String name, String lastName, Gender gender, int age) {
        this.name = name;
        this.lastName = lastName;
        this.gender = gender;
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(lastName, human.lastName) && gender == human.gender;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, gender);
    }

    @Override
    public String toString() {
        return "name = " + name + "" + "\n" + "lastName = " + lastName + "\n" + "gender = " + gender;
    }
}
