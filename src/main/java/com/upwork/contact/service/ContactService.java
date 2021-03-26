package com.upwork.contact.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.upwork.contact.model.Contact;
import com.upwork.contact.repository.ContactRepository;

@Service
@Transactional
public class ContactService {

	private final Logger log = LoggerFactory.getLogger(ContactService.class);

    private final ContactRepository contactRepository;

    public ContactService(ContactRepository contactRepository) {
    	this.contactRepository = contactRepository;
    }

      
    public Contact save(Contact contact) {
        log.debug("Request to save contact : {}", contact);
        contact = contactRepository.save(contact);
        return contact;
    }	
    
    public List<Contact> findAll() {
        log.debug("Request to get all Contacts");
        return contactRepository.findAll();
    }


	public List<Contact> findByPostalCode(String postalCode) {
		log.debug("Request to get Contacts by postalCode"+ postalCode);
		return contactRepository.findByAddressPostalCode(postalCode);
	}
    

}
