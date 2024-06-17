package fr.airfrance.userregistry.domain;

import fr.airfrance.userregistry.domain.enumeration.Country;
import jakarta.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.time.Instant;

/**
 * The User entity.
 * @author bSmichet.
 */
@Document(collection = "user")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    /**
     * The first name attribute.
     */
    @NotNull
    @Field("first_name")
    private String firstName;

    /**
     * The last name attribute.
     */
    @NotNull
    @Field("last_name")
    private String lastName;

    /**
     * The birthday attribute.
     */
    @NotNull
    @Field("birthday")
    private Instant birthday;

    /**
     * The address attribute.
     */
    @NotNull
    @Field("address")
    private String address;

    /**
     * The postalCode attribute.
     */
    @NotNull
    @Pattern(regexp = "^(?:0[1-9]|[1-8]\\d|9[0-8])\\d{3}$")
    @Field("postal_code")
    private String postalCode;

    /**
     * The country attribute.
     */
    @NotNull
    @Field("country")
    private Country country;

    /**
     * The email attribute.
     */
    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")
    @Field("email")
    private String email;

    /**
     * The phone number attribute.
     */
    @Pattern(regexp = "^(0|\\+33)[1-9]([-. ]?[0-9]{2}){4}$")
    @Field("phone_number")
    private String phoneNumber;

    public String getId() {
        return this.id;
    }

    public User id(String id) {
        this.setId(id);
        return this;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public User firstName(String firstName) {
        this.setFirstName(firstName);
        return this;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public User lastName(String lastName) {
        this.setLastName(lastName);
        return this;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Instant getBirthday() {
        return this.birthday;
    }

    public User birthday(Instant birthday) {
        this.setBirthday(birthday);
        return this;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return this.address;
    }

    public User address(String address) {
        this.setAddress(address);
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public User postalCode(String postalCode) {
        this.setPostalCode(postalCode);
        return this;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return this.country;
    }

    public User country(Country country) {
        this.setCountry(country);
        return this;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return this.email;
    }

    public User email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public User phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        return getId() != null && getId().equals(((User) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "User{" +
            "id=" + getId() +
            ", firstName='" + getFirstName() + "'" +
            ", lastName='" + getLastName() + "'" +
            ", birthday='" + getBirthday() + "'" +
            ", address='" + getAddress() + "'" +
            ", postalCode='" + getPostalCode() + "'" +
            ", country='" + getCountry() + "'" +
            ", email='" + getEmail() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            "}";
    }
}
