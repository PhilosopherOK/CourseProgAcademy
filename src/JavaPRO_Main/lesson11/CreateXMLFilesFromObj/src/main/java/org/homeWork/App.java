package org.homeWork;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        try {
            List<Contact> contacts = new ArrayList<>();

            Contact contact1 = new Contact(null, "baby1", "cris1", "0908901", "asdfgasd1");
            Contact contact2 = new Contact(null, "baby2", "cris2", "0908902", "asdfgasd2");
            Contact contact3 = new Contact(null, "baby3", "cris3", "0908903", "asdfgasd3");

            contacts.add(contact1);
            contacts.add(contact2);
            contacts.add(contact3);

            // Создание объекта Marshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactList.class);
            Marshaller marshaller = jaxbContext.createMarshaller();

            // Преобразование списка контактов в XML
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new ContactList(contacts), new File("contacts.xml"));

            System.out.println("XML-файл создан успешно.");
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        getContactsFromFile();
    }

    public static void getContactsFromFile() {
        try {
            // Создание объекта Unmarshaller
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            // Распарсивание XML-файла в список контактов
            ContactList contactList = (ContactList) unmarshaller.unmarshal(new File("contacts.xml"));
            List<Contact> contacts = contactList.getContacts();

            // Вывод списка контактов
            for (Contact contact : contacts) {
                System.out.println(contact);
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
}
