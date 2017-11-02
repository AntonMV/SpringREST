package com.spring.pro4.service;

import com.spring.pro4.model.Contact;

import java.util.List;

public interface ContactService {
    List<Contact> findAll();
    List<Contact> findByFirstName(String firstName);
    Contact findByid(Integer id);
    Contact save(Contact contact);
    void delete(Contact contact);
}
