package com.spring.pro4.controller;

import com.spring.pro4.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ContactViewController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value="/address", method= RequestMethod.GET)
    public String AllContact(Model model) {
        model.addAttribute("contacts", contactService.findAll());
        return "contact";
    }


}
