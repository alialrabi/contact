package com.upwork.contact.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * 
 * @author ali
 *
 */

@Entity
@Table(name = "contact")
public class Contact implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "date_of_birth")
    private ZonedDateTime dateOfBirth;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Address address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public Contact fullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public ZonedDateTime getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(ZonedDateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
        return address;
    }

    public Contact address(Address address) {
        this.address = address;
        return this;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Contact)) {
            return false;
        }
        return id != null && id.equals(((Contact) o).id);
    }

    @Override
    public String toString() {
        return "Contact{" +
            "id=" + getId() +
            ", fullName='" + getFullName() + "'" +
            ", hisdate='" + getDateOfBirth() + "'" +
            "}";
    }
}
