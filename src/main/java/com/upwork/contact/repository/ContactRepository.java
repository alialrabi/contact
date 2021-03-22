package com.upwork.contact.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.upwork.contact.model.Contact;

/**
 * 
 * @author ali
 *
 */
@SuppressWarnings("unused")
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	List<Contact> findByAddressPostalCode(String postalCode);
	

}
