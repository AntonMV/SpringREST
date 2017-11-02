package com.spring.pro4.repository;

import com.spring.pro4.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    @Query("select b from Contact b where b.firstName = ?1")
    List<Contact> findByFirstName(String firstName);
}
