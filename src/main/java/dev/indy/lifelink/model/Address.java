package dev.indy.lifelink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Address {
    private String _country;
    private String _postalCode;
    private String _city;
    private String _street;
    private String _buildingNumber;

    protected Address() {}

    public Address(String country, String postalCode, String city, String street, String buildingNumber) {
        this._country = country.toUpperCase();
        this._postalCode = postalCode;
        this._city = city;
        this._street = street;
        this._buildingNumber = buildingNumber;
    }

    @Column(name = "country", nullable = false, length = 100)
    public String getCountry() { return this._country; }
    public void setCountry(String country) { this._country = country; }

    @Column(name = "postalCode", nullable = false, length = 9)
    public String getPostalCode() { return this._postalCode; }
    public void setPostalCode(String postalCode) { this._postalCode = postalCode; }

    @Column(name = "city", nullable = false, length = 100)
    public String getCity() { return this._city; }
    public void setCity(String city) { this._city = city; }

    @Column(name = "street", nullable = false, length = 100)
    public String getStreet() { return this._street; }
    public void setStreet(String street) { this._street = street; }

    @Column(name = "buildingNumber", nullable = false, length = 10)
    public String getBuildingNumber() { return this._buildingNumber; }
    public void setBuildingNumber(String buildingNumber) { this._buildingNumber = buildingNumber; }
}
