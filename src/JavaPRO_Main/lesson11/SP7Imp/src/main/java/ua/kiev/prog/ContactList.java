package ua.kiev.prog;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class ContactList {
    private List<Contact> contacts;

    public ContactList() {}

    public ContactList(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    @XmlElement(name = "contact")
    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}