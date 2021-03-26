package com.upwork.contact.controller;

import com.upwork.contact.model.Contact;
import com.upwork.contact.service.ContactService;
import com.upwork.contact.util.UtilTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ContactResource.class)
public class ContactResourceTest {

    @MockBean
    ContactService contactService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testContactCreation() throws Exception
    {

        Contact defaultContact = UtilTest.getTestContact();

        Mockito.when(contactService.save(Mockito.any(Contact.class))).thenReturn(defaultContact);

        MvcResult result = mockMvc.perform(post("/api/contacts").accept(MediaType.APPLICATION_JSON)
                .content(UtilTest.objectToJson(defaultContact))
                .contentType(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.CREATED.value(), status);

        verify(contactService).save(Mockito.any(Contact.class));

        Contact resultContact = UtilTest.jsonToObject(result.getResponse().getContentAsString(), Contact.class);
        assertNotNull(resultContact);
        assertEquals(resultContact.getFullName(), defaultContact.getFullName());

    }
    @Test
    public void testWithIdContactCreation() throws Exception {

        Contact defaultContactWithId = UtilTest.getTestContactHaveId();
        MvcResult result = mockMvc.perform(post("/api/contacts").accept(MediaType.APPLICATION_JSON)
                .content(UtilTest.objectToJson(defaultContactWithId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

    }

    @Test
    public void testWithoutAddressContactCreation() throws Exception {

        Contact defaultContactWithoutAddress = UtilTest.getTestContactWithoutAddress();
        MvcResult result = mockMvc.perform(post("/api/contacts").accept(MediaType.APPLICATION_JSON)
                .content(UtilTest.objectToJson(defaultContactWithoutAddress))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

    }

    @Test
    public void testWithAddressIdContactCreation() throws Exception {

        Contact defaultContactWithAddressId = UtilTest.getTestContactWithAddressId();
        MvcResult result = mockMvc.perform(post("/api/contacts").accept(MediaType.APPLICATION_JSON)
                .content(UtilTest.objectToJson(defaultContactWithAddressId))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.BAD_REQUEST.value(), status);

    }

    @Test
    public void getAllContacts() throws Exception
    {

        List<Contact> contactsList = UtilTest.getTestContacts(4);
        Mockito.when(contactService.findAll()).thenReturn(contactsList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/contacts")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        verify(contactService).findAll();

        List<Contact> contactResultList = UtilTest.jsonToObject(result.getResponse()
                .getContentAsString(), List.class);


        assertNotNull(contactResultList);
        assertEquals(4, contactResultList.size());
    }

    @Test
    public void getAllContactsByPostalCode() throws Exception
    {

        List<Contact> contactsList = UtilTest.getTestContacts(2);
        Mockito.when(contactService.findByPostalCode(Mockito.anyString())).thenReturn(contactsList);

        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/contact/"+"1253")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        verify(contactService).findByPostalCode("1253");

        List<Contact> contactResultList = UtilTest.jsonToObject(result.getResponse()
                .getContentAsString(), List.class);


        assertNotNull(contactResultList);
        assertEquals(2, contactResultList.size());
    }

    @Test
    public void home() throws Exception
    {

        String homeString = "contact for upwork";
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get("/api/")
                .accept(MediaType.APPLICATION_JSON))
                .andReturn();

        int status = result.getResponse().getStatus();
        assertEquals(HttpStatus.OK.value(), status);

        String resultString = result.getResponse()
                .getContentAsString();

        assertNotNull(resultString);
        assertEquals(resultString, homeString);
    }

}
