package com.upwork.contact.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.upwork.contact.model.Contact;
import com.upwork.contact.repository.ContactRepository;
import com.upwork.contact.util.UtilTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityNotFoundException;

@ExtendWith(SpringExtension.class)
public class ContactServiceTest
{
    @Mock
    private ContactRepository contactRepository;

    @InjectMocks
    private ContactService contactService;
    
    @Test
    public void testSave() throws EntityNotFoundException {
        
        Contact defaultContact = UtilTest.getTestContact();

        Mockito.when(contactRepository.save(defaultContact)).thenReturn(defaultContact);
        
        Contact contact = contactService.save(defaultContact);

        assertNotNull(contact);

        verify(contactRepository).save(any());
    }

    @Test
    public void testFindAll() {

        List<Contact> contacts = UtilTest.getTestContacts(3);
        Mockito.when(contactRepository.findAll()).thenReturn(contacts);
        List<Contact> result = this.contactService.findAll();
        assertEquals(contacts.size(), result.size());

        verify(contactRepository).findAll();


    }

    @Test
    public void testFindByPostalCode() {

        List<Contact> contacts = UtilTest.getTestContacts(3);
        Mockito.when(contactRepository.findByAddressPostalCode(any())).thenReturn(contacts);
        List<Contact> result = this.contactService.findByPostalCode("1253");
        assertEquals(contacts.size(), result.size());

        verify(contactRepository).findByAddressPostalCode(Mockito.anyString());

    }
    
}