package dev.indy.lifelink.model;

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
    private Address _address;

    protected Person() {}

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

    @Embedded
    public Address getAddress() { return this._address; }
    public void setAddress(Address address) { this._address = address; }
}
