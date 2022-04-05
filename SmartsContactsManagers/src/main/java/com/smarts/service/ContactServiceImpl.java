package com.smarts.service;

import java.util.List;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smarts.dto.ContactDto;
import com.smarts.entity.Contact;
import com.smarts.repository.ContactRepository;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactRepository contactRepository;

	@Override
	public List<Contact> getContacts() {

		return contactRepository.findAll();
	}

	@Override
	public Contact findByName(String firstName) {

		Contact findByFirstNames = contactRepository.findByFirstName(firstName);
		if (findByFirstNames.getIsActive() == "N") {
			return null;
		} else {
			return findByFirstNames;
		}
	}

	@Override
	public Contact addContact(Contact contact) {

		return contactRepository.save(contact);

	}
	
	@Override
	public Contact addContact(ContactDto contactDto) {

		return contactRepository.save(contactDto);

	}

	@Override
	public Contact updateContact(Contact contact) {

		return contactRepository.save(contact);

	}

	@Override
	public void deleteContact(int contactId) {

		contactRepository.deleteById(contactId);
	}

	@Override
    public Contact removeContact(int contactId) {
        Contact contact = contactRepository.findById(contactId)
                        .orElseThrow(() -> new ResourceNotFoundException("contact not found"+ contactId));
        
        contact.setIsActive("N");
        
        Contact save = contactRepository.save(contact);

        return save;
	}
}
