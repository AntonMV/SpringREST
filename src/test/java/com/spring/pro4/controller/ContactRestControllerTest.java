package com.spring.pro4.controller;

import com.spring.pro4.model.Contact;
import com.spring.pro4.utils.Contacts;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class ContactRestControllerTest {
    private static final String URL_GET_ALL_CONTACTS =
            "http://localhost:8080/restfull/contact/listdata";
    private static final String URL_GET_CONTACT_BY_ID =
            "http://localhost:8080/restfull/contact/{id}";
    private static final String URL_UPDATE_CONTACT =
            "http://localhost:8080/restfull/contact/{id}";
    private static final String URL_DELETE_CONTACT =
            "http://localhost:8080/restfull/contact/{id}";
    private static final String URL_CREATE_CONTACT =
            "http://localhost:8080/restfull/contact/";

    private static RestTemplate restTemplate(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/restful-client-app-context.xml");
        ctx.refresh();
        return ctx.getBean("restTemplate", RestTemplate.class);
    }

    @Test
    public void listData() throws Exception {
        System.out.println("Testing retrieve all contacts:");
        Contacts contacts = restTemplate().getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        listContacts(contacts);
    }

    private static void listContacts(Contacts contacts){
        for (Contact contact: contacts.getContacts()){
            System.out.println(contact);
        }
        System.out.println("");
    }

    @Test
    public void findContactById() throws Exception {
        System.out.println("Testing retrieve а contact by id :");
        Contact contact = restTemplate().getForObject(URL_GET_CONTACT_BY_ID, Contact.class, 1);
        System.out.println(contact.toString());
        System.out.println("");
    }

    @Test
    public void update() throws Exception {
        Contact contact = restTemplate().getForObject(URL_UPDATE_CONTACT, Contact.class, 2);
        contact.setFirstName("John Doe");
        System.out.println("Testing update contact by id :");
        restTemplate().put(URL_UPDATE_CONTACT, contact, 1);
        System.out.println("Contact update successfully: " + contact);
        System.out.println("");
    }


    @Test
    public void delete() throws Exception {
        Contacts contacts = restTemplate().getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        restTemplate().delete(URL_DELETE_CONTACT, contacts.getContacts().size() - 1);
        System.out.println("Testing delete contact by id :");
        listContacts(contacts);
    }

    @Test
    public void create() throws Exception {
        final Random random = new Random();
        System.out.println("Testing create contact :");
        Contact contactNew = new Contact();
        contactNew.setFirstName("James"+random.nextInt(100));
        contactNew.setLastName("Gosling"+random.nextInt(100));
        contactNew.setBirthDate(new DateTime());
        contactNew.setVersion(3);
        contactNew = restTemplate().postForObject(URL_CREATE_CONTACT, contactNew, Contact.class);
        System.out.println("Contact created success fully: " + contactNew);
    }

    private static void listLenght(Contacts contacts){

        for (Contact contact: contacts.getContacts()){
            System.out.println(contact);
        }
        System.out.println("");
    }


}