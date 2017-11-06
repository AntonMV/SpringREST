package com.spring.pro4.controller;

import com.spring.pro4.model.Contact;
import com.spring.pro4.service.ContactService;
import com.spring.pro4.utils.Contacts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value="/restful/contact")
public class ContactRestController {

    final Logger logger = LoggerFactory.getLogger(ContactRestController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/listdata", method = RequestMethod.GET, produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Contacts listData() {
        return new Contacts(contactService.findAll());
    }

    @GetMapping(value="/{id}", produces = MediaType.APPLICATION_XML_VALUE)
    @ResponseBody
    public Contact findContactById(@PathVariable Integer id) {
        return contactService.findByid(id);
    }

    @PostMapping(value="/")
    @ResponseBody
    public Contact create(@RequestBody Contact contact) {
        logger.info("Creating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact created successfully with info: " + contact);
        return contact;
    }

    @PutMapping(value="/{id}")
    @ResponseBody
    public void update(@RequestBody Contact contact,
                      @PathVariable Long id) {
        logger.info("Updating contact: " + contact);
        contactService.save(contact);
        logger.info("Contact updated successfully with info: " + contact);
    }

    @DeleteMapping(value="/{id}")
    @ResponseBody
    public void delete(@PathVariable Integer id) {
        logger.info("Deleting contact with id: " + id);
        Contact contact = contactService.findByid(id);
        contactService.delete(contact);
        logger.info("Contact deleted successfully");
    }
}
