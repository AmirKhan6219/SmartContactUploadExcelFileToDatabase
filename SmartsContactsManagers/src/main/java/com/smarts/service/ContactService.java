package com.smarts.service;

import java.util.List;

import com.smarts.dto.ContactDto;
import com.smarts.entity.Contact;

public interface ContactService {
	
	public List<Contact> getContacts();
	
	public Contact findByName(String firstName);
	
	public Contact addContact(Contact contact);
	
	public Contact updateContact(Contact contact);
	  
	public void deleteContact(int contactId);

	public Contact removeContact(int contactId);
	
	public Contact addContact(ContactDto contactDto);
	
	

}
