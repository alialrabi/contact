package com.upwork.contact.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.upwork.contact.model.Contact;
import com.upwork.contact.model.Address;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;


public class UtilTest {

    public static <T> T jsonToObject(String json, Class<T> clazz) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.readValue(json, clazz);
    }


    public static <T> String objectToJson(T object) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        return mapper.writeValueAsString(object);
    }
	
	public static Contact getTestContact() {
        Contact contact = new Contact();
        contact.setFullName("ali");
        contact.setDateOfBirth(ZonedDateTime.now());

        Address address = new Address();
        address.setCity("tanta");
        address.setPostalCode("1253");

        contact.setAddress(address);

        return contact;
    }

    public static Contact getTestContactHaveId() {
        Contact contact = new Contact();
        contact.setId(Long.valueOf(1));
        contact.setFullName("ali");
        contact.setDateOfBirth(ZonedDateTime.now());

        Address address = new Address();
        address.setCity("tanta");
        address.setPostalCode("1253");

        contact.setAddress(address);

        return contact;
    }

    public static Contact getTestContactWithAddressId() {
        Contact contact = new Contact();
        contact.setFullName("ali");
        contact.setDateOfBirth(ZonedDateTime.now());

        Address address = new Address();
        address.setId(Long.valueOf(2));
        address.setCity("tanta");
        address.setPostalCode("1253");

        contact.setAddress(address);

        return contact;
    }

    public static Contact getTestContactWithoutAddress() {
        Contact contact = new Contact();
        contact.setFullName("ali");
        contact.setDateOfBirth(ZonedDateTime.now());

        return contact;
    }

    public static List<Contact> getTestContacts(Integer count) {
        List<Contact> contacts = new ArrayList<>();

        for (int i = 1; i <= count; i++) {
            Contact contact = new Contact();
            contact.setFullName("ali");
            contact.setDateOfBirth(ZonedDateTime.now());

            Address address = new Address();
            address.setCity("tanta");
            address.setPostalCode("1253");

            contact.setAddress(address);
            contacts.add(contact);
        }
        return contacts;
	}
	
}