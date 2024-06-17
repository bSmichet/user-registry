package fr.airfrance.userregistry.service.validator;

import fr.airfrance.userregistry.domain.enumeration.Country;
import fr.airfrance.userregistry.exception.UserValidationException;
import fr.airfrance.userregistry.service.dto.UserDTO;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

/**
 * User Validator that validates functional requirements
 */
@Component
public class UserValidator {

    /**
     * Validate user functional requirements
     *
     * @param userDTO the user to validate
     */
    public void validate(UserDTO userDTO) {
        // Age validation (must be over 18 years)
        if (ChronoUnit.YEARS.between(userDTO.getBirthday().atZone(ZoneId.systemDefault()),
                Instant.now().atZone(ZoneId.systemDefault())) < 18) {
            throw new UserValidationException("Only adults (age > 18 years) can register");
        }
        // Country validation (must live in France)
        if (!Country.FRANCE.equals(userDTO.getCountry())) {
            throw new UserValidationException("Only users living in France can register");
        }
    }
}
