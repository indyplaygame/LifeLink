package dev.indy.lifelink.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import dev.indy.lifelink.util.Util;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Access(AccessType.PROPERTY)
@Table(name = "people")
public class Person {
    private long _personId;
    private String _firstName;
    private String _middleName;
    private String _lastName;
    private String _phoneNumber;
    private Gender _gender;
    private Address _address;

    public enum Gender {
        MALE,
        FEMALE
    }

    protected Person() {}

    public Person(String firstName, String middleName, String lastName, String phoneNumber, Gender gender, Address address) {
        this._firstName = Util.capitalize(firstName);
        this._middleName = Util.capitalize(middleName);
        this._lastName = Util.capitalize(lastName);
        this._phoneNumber = phoneNumber;
        this._gender = gender;
        this._address = address;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personId", nullable = false, updatable = false)
    public long getPersonId() { return this._personId; }
    protected void setPersonId(long personId) { this._personId = personId; }

    @Column(name = "firstName", nullable = false, length = 50)
    public String getFirstName() { return this._firstName; }
    public void setFirstName(String firstName) { this._firstName = firstName; }

    @Column(name = "middleName", nullable = true, length = 50)
    public String getMiddleName() { return this._middleName; }
    public void setMiddleName(String middleName) { this._middleName = middleName; }

    @Column(name = "lastName", nullable = false, length = 50)
    public String getLastName() { return this._lastName; }
    public void setLastName(String lastName) { this._lastName = lastName; }

    @Column(name = "phoneNumber", nullable = false, length = 15)
    public String getPhoneNumber() { return this._phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this._phoneNumber = phoneNumber; }

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false, length = 10)
    public Gender getGender() { return this._gender; }
    public void setGender(Gender gender) { this._gender = gender; }

    @Embedded
    public Address getAddress() { return this._address; }
    public void setAddress(Address address) { this._address = address; }
}
