package com.spring.pro4.model;


import static javax.persistence.GenerationType.IDENTITY;
import java.io.Serializable;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import javax.persistence.*;


@Entity
public class Contact implements Serializable {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Basic
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Basic
    @Column(name = "LAST_NAME")
    private String lastName;

    @Basic
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @Column(name = "BIRTH_DATE")
    private DateTime birthDate;

    @Basic
    @Column(name = "VERSION")
    private Integer version;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        if (id != contact.id) return false;
        if (version != contact.version) return false;
        if (firstName != null ? !firstName.equals(contact.firstName) : contact.firstName != null) return false;
        if (lastName != null ? !lastName.equals(contact.lastName) : contact.lastName != null) return false;
        if (birthDate != null ? !birthDate.equals(contact.birthDate) : contact.birthDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (birthDate != null ? birthDate.hashCode() : 0);
        result = 31 * result + version;
        return result;
    }

    @Override
    public String toString() {
        return "Contact - Id: " + id + ", First name: " + firstName
                + ", Last name: " + lastName + ", Birthday: " + birthDate;
    }
}
