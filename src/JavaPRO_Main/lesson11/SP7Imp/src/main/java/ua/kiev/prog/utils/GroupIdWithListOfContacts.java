package ua.kiev.prog.utils;

import java.util.List;

public class GroupIdWithListOfContacts {
    private Long groupId;
    private List<String> contacts;

    public GroupIdWithListOfContacts() {
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }

    @Override
    public String toString() {
        return "DeleteRequest{" +
                "groupId=" + groupId +
                ", contacts=" + contacts +
                '}';
    }
}