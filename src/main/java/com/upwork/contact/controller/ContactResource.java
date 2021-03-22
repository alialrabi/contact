package com.upwork.contact.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.upwork.contact.model.Contact;
import com.upwork.contact.service.ContactService;

@RestController
@RequestMapping("/api")
public class ContactResource {

	private final Logger log = LoggerFactory.getLogger(ContactResource.class);
	
	private final ContactService contactService;

	public ContactResource(ContactService contactService) {
		super();
		this.contactService = contactService;
	}
	
	 @GetMapping("/")
	 public String home(){
	       return "contact for upwork";
     }
	 
	 
	@PostMapping("/contacts")
	public ResponseEntity<Contact> createContact(@Valid @RequestBody Contact  contact) throws URISyntaxException {
		log.info("REST request to save Contact : {}", contact);
		if (contact.getId() != null) {
			throw new NullPointerException();
		}
		
		Contact result = contactService.save(contact);

		return ResponseEntity.created(new URI("/api/contacts/" + result.getId()))
				.body(result);
	}
	
	@GetMapping("/contacts")
    public List<Contact> getAllPlans(Pageable pageable) {
        log.info("REST request to get a page of Contacts");
        List<Contact> list = contactService.findAll(pageable);
        return list;
    }
	
	@GetMapping("/contact/{postalCode}")
	public List<Contact> getcontact(@PathVariable String postalCode) {
		log.debug("REST request to get contact by postalcode : {}", postalCode);
		List<Contact> list = contactService.findByPostalCode(postalCode);
		return list;
	}

	
}
