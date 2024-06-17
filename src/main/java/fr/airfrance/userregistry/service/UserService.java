package fr.airfrance.userregistry.service;

import fr.airfrance.userregistry.service.dto.UserDTO;

import java.util.Optional;

/**
 * Service Interface for managing {@link fr.airfrance.userregistry.domain.User}.
 */
public interface UserService {
    /**
     * Save a user.
     *
     * @param userDTO the entity to save.
     * @return the persisted entity.
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Get the "id" user.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<UserDTO> findOne(String id);

}
