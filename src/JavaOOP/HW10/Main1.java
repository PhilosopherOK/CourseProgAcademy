package JavaOOP.HW10;

/*
1. Список контактів. Розробіть програму для зберігання та управління списком
контактів (ім'я та номер телефону). Використайте стандартні колекції
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main1 {
    public static void main(String[] args) {
        ContactManagement contactManagement = new ContactManagement();
        contactManagement.createNewContact();
        contactManagement.createNewContact();
        Contact contact = contactManagement.findContactByName("Alex");
        contactManagement.findContactByMobileNumber(3809899999L);
        contactManagement.updateContact(contact);
        contactManagement.deleteContact(contact);
        contactManagement.showAllContact();

    }
}

class ContactManagement {
    List<Contact> contactList = new LinkedList<>();

    public Contact findContactByName(String name) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getName().equals(name)) {
                System.out.println(contactList.get(i));
                return contactList.get(i);
            }
        }
        System.out.println("Not found");
        return null;
    }

    public Contact findContactByMobileNumber(long mobileNumber) {
        for (int i = 0; i < contactList.size(); i++) {
            if (contactList.get(i).getMobileNumber() == mobileNumber) {
                System.out.println(contactList.get(i));
                return contactList.get(i);
            }
        }
        System.out.println("Not found");
        return null;
    }

    public void createNewContact() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter new name");
        String newName = scanner.nextLine();
        System.out.println("Enter new mobile number");
        long newMobileNumber = scanner.nextLong();
        contactList.add(new Contact(newName, newMobileNumber));
    }

    public void updateContact(Contact pastContact) {
        contactList.remove(pastContact);
        createNewContact();
    }

    public void deleteContact(Contact contact) {
        contactList.remove(contact);
    }

    public void showAllContact() {
        for (int i = 0; i < contactList.size(); i++) {
            System.out.println(contactList.get(i));

        }
    }
}

class Contact {
    private String name;
    private long mobileNumber;

    public Contact(String name, long mobileNumber) {
        this.name = name;
        this.mobileNumber = mobileNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(long mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}