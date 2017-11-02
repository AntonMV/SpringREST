package com.spring.pro4;

import com.spring.pro4.model.Contact;

import java.util.List;

public class Contacts  {
    private List<Contact> contacts;

    public Contacts() {
    }

    public Contacts(List<Contact> contacts){
        this.contacts = contacts;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
