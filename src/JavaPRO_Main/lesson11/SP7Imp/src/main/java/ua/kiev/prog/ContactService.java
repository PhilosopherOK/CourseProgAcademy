package ua.kiev.prog;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;


import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.util.List;
import java.util.Optional;

// c -> s -> r -> DB

@Service
public class ContactService {

    private final ContactRepository contactRepository;
    private final GroupRepository groupRepository;

    public ContactService(ContactRepository contactRepository,
                          GroupRepository groupRepository) {
        this.contactRepository = contactRepository;
        this.groupRepository = groupRepository;
    }

    @Transactional
    public void createContactsObjFromFile(MultipartFile multipartFile) {
        File xmlFile = convertMultipartFileToFile(multipartFile);
        if (xmlFile == null) {
            System.out.println("checkPoint service");
            return;
        }
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(ContactList.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            ContactList contactList = (ContactList) unmarshaller.unmarshal(xmlFile);
            List<Contact> contacts = contactList.getContacts();

            contactRepository.saveAll(contacts);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        xmlFile.delete();
    }

    @Transactional
    public void addContact(Contact contact) {
        contactRepository.save(contact);
    }

    @Transactional
    public void addGroup(Group group) {
        groupRepository.save(group);
    }

    @Transactional
    public void deleteContacts(long[] idList) {
        for (long id : idList)
            contactRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Contact findContactById(Long id){
        return contactRepository.findById(id).orElse(null);
    }
    @Transactional(readOnly = true)
    public List<Group> findGroups() {
        return groupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Contact> findAll(Pageable pageable) {
        return contactRepository.findAll(pageable).getContent();
    }

    @Transactional(readOnly = true)
    public List<Contact> findByGroup(Group group, Pageable pageable) {
        return contactRepository.findByGroup(group, pageable);
    }

    @Transactional(readOnly = true)
    public long countByGroup(Group group) {
        return contactRepository.countByGroup(group);
    }

    @Transactional(readOnly = true)
    public List<Contact> findByPattern(String pattern, Pageable pageable) {
        return contactRepository.findByNameStartingWith(pattern, pageable);
    }

    @Transactional(readOnly = true)
    public long countByPattern(String pattern) {
        return contactRepository.countByPattern(pattern);
    }

    @Transactional(readOnly = true)
    public long count() {
        return contactRepository.count();
    }

    @Transactional(readOnly = true)
    public Group findGroup(long id) {
        return groupRepository.findById(id).get();
    }

    @Transactional
    public void reset() {
        groupRepository.deleteAll();
        contactRepository.deleteAll();

        Group group = new Group("Test");
        Contact contact;

        addGroup(group);

        for (int i = 0; i < 13; i++) {
            contact = new Contact(null, "Name" + i, "Surname" + i, "1234567" + i, "user" + i + "@test.com");
            addContact(contact);
        }
        for (int i = 0; i < 10; i++) {
            contact = new Contact(group, "Other" + i, "OtherSurname" + i, "7654321" + i, "user" + i + "@other.com");
            addContact(contact);
        }
    }


    @Transactional
    public ResponseEntity<Void> switchGroupFromListContacts(Long groupId, List<String> contacts){
        Group group = findGroup(groupId);
        for (int i = 0; i < contacts.size(); i++) {
            Contact contact = findContactById(Long.parseLong(contacts.get(i)));
            if(contact != null){
                group.getContacts().add(findContactById(Long.parseLong(contacts.get(i))));
                contact.setGroup(group);
                contactRepository.save(contact);
            }else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        groupRepository.save(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());

        try (InputStream inputStream = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
