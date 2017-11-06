package com.spring.pro4.controller;

import com.spring.pro4.model.Contact;
import com.spring.pro4.utils.Contacts;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.InterceptingClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthorizationInterceptor;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MappingJackson2XmlHttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class ContactRestControllerTest {

    private static final String URL_GET_ALL_CONTACTS =
            "http://localhost:8080/restful/contact/listdata";
    private static final String URL_GET_CONTACT_BY_ID =
            "http://localhost:8080/restful/contact/{id}";
    private static final String URL_UPDATE_CONTACT =
            "http://localhost:8080/restful/contact/{id}";
    private static final String URL_DELETE_CONTACT =
            "http://localhost:8080/restful/contact/{id}";
    private static final String URL_CREATE_CONTACT =
            "http://localhost:8080/restful/contact/";

    private static RestTemplate restTemplate(){
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("spring/restful-client-app-context.xml");
        ctx.refresh();
        RestTemplate restTemplate = ctx.getBean("restTemplate", RestTemplate.class);
        return restTemplate;
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
        Contact contact = restTemplate().getForObject(URL_UPDATE_CONTACT, Contact.class, 1);
        final Random random = new Random();
        contact.setFirstName("John Doe"+random.nextInt(100));
        System.out.println("Testing update contact by id :");
        restTemplate().put(URL_UPDATE_CONTACT, contact, 1);
        System.out.println("Contact update successfully: " + contact);
        System.out.println("");
    }


    @Test
    public void delete() throws Exception {
        Contacts contacts = restTemplate().getForObject(URL_GET_ALL_CONTACTS, Contacts.class);
        int count = 0;
        for (Contact contact: contacts.getContacts()) {
            if (contact.getId() > count){
                count = contact.getId();
            }
        }
        System.out.println("!!!!Count_Id: "+count);
        restTemplate().delete(URL_DELETE_CONTACT, count);
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

}