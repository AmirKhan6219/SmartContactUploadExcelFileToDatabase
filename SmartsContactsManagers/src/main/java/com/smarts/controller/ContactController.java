package com.smarts.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.smarts.dto.ContactDto;
import com.smarts.entity.Contact;
import com.smarts.service.ContactServiceImpl;

@RestController
public class ContactController {
	
	@Autowired
	private ContactServiceImpl contactService;

	@GetMapping("/findAll")
	public List<Contact> getContacts(){
		
		return contactService.getContacts();
	}
	
	@GetMapping("/findByFirstName/{firstName}")
	public Contact findByname(@PathVariable String firstName) {
		
        return contactService.findByName(firstName);
	}
	
	@PostMapping("/addContact")
	public Contact addContact(@RequestBody Contact contact) {
		
		return contactService.addContact(contact);
	}
	
	@PostMapping("/addContactDto")
	public Contact addContact(@RequestBody ContactDto contactDto) {
	    ModelMapper mapper = new ModelMapper();
		Contact contact = mapper.map(contactDto, Contact.class);
		Contact addContact = contactService.addContact(contact);
		return addContact;
	
	}
	
	@PutMapping("/updateContact")
    public Contact updateContact(@RequestBody Contact contact) {
		
		return contactService.updateContact(contact);
	}
	
	@DeleteMapping("/deleteContact")
    public String deleteContact(int contactId) {
        contactService.deleteContact(contactId);
        return "Data deleted from database";
    }

    @PutMapping("/removeContact/{contactId}")
    public Contact removeContact(@PathVariable int contactId){
        return contactService.removeContact(contactId);
    }
	
}
