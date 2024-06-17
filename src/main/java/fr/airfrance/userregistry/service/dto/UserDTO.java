package fr.airfrance.userregistry.service.dto;

import fr.airfrance.userregistry.domain.enumeration.Country;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

/**
 * A DTO for the {@link fr.airfrance.userregistry.domain.User} entity.
 */
@Schema(description = "The User entity.\n@author bSmichet.")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class UserDTO implements Serializable {

    private String id;

    @NotNull(message = "firstName must not be null")
    @Schema(description = "The first name attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String firstName;

    @NotNull(message = "lastName must not be null")
    @Schema(description = "The last name attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String lastName;

    @NotNull(message = "birthday must not be null")
    @Schema(description = "The birthday attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private Instant birthday;

    @NotNull(message = "address must not be null")
    @Schema(description = "The address attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String address;

    @Pattern(regexp = "^(?:0[1-9]|[1-8]\\d|9[0-8])\\d{3}$", message = "postalCode not valid")
    @Schema(description = "The postalCode attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private String postalCode; // Default value postalCode is 75000

    @NotNull(message = "country must not be null")
    @Schema(description = "The country attribute.", requiredMode = Schema.RequiredMode.REQUIRED)
    private Country country;

    @Pattern(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email not valid")
    @Schema(description = "The email attribute.")
    private String email;

    @Pattern(regexp = "^(0|\\+33)[1-9]([-. ]?[0-9]{2}){4}$", message = "phoneNumber not valid")
    @Schema(description = "The phone number attribute.")
    private String phoneNumber;

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof UserDTO)) {
            return false;
        }

        UserDTO userDTO = (UserDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, userDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "UserDTO{" +
            "id='" + getId() + "'" +
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
