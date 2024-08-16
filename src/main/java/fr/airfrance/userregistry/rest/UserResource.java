package fr.airfrance.userregistry.rest;

import fr.airfrance.userregistry.exception.UserValidationException;
import fr.airfrance.userregistry.service.UserService;
import fr.airfrance.userregistry.service.dto.UserDTO;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

/**
 * REST controller for managing {@link fr.airfrance.userregistry.domain.User}.
 */
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserResource {

//    private final Logger log = LoggerFactory.getLogger(UserResource.class);

    private final UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    /**
     * {@code POST  /users} : Create a new user.
     *
     * @param userDTO the userDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new userDTO, or with status {@code 400 (Bad Request)} if the user has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<UserDTO> registerUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
//        log.debug("REST request to save User : {}", userDTO);
        if (userDTO.getId() != null) {
            throw new UserValidationException("A new user cannot already have an ID");
        }

        log.info("Example  info  message -> Received firstname {}", userDTO.getFirstName());
        log.debug("Example debug message -> Received firstname {}", userDTO.getFirstName());
        log.error("Example error message -> not found firstname {}", userDTO.getFirstName());

        userDTO = userService.save(userDTO);
        return ResponseEntity
                .created(new URI("/api/users/" + userDTO.getId()))
                .body(userDTO);
    }

    /**
     * {@code GET  /users/:id} : get the "id" user.
     *
     * @param id the id of the userDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getRegisteredUser(@PathVariable("id") String id) {
        log.debug("REST request to get User : {}", id);
        Optional<UserDTO> userDTO = userService.findOne(id);
        return userDTO
                .map(response -> ResponseEntity.ok().body(response))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
