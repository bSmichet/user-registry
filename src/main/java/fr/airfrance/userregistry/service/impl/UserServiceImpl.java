package fr.airfrance.userregistry.service.impl;

import fr.airfrance.userregistry.domain.User;
import fr.airfrance.userregistry.repository.UserRepository;
import fr.airfrance.userregistry.service.UserService;
import fr.airfrance.userregistry.service.dto.UserDTO;
import fr.airfrance.userregistry.service.mapper.UserMapper;
import fr.airfrance.userregistry.service.validator.UserValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service Implementation for managing {@link fr.airfrance.userregistry.domain.User}.
 */
@Service
public class UserServiceImpl implements UserService {

    private final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    private final UserMapper userMapper;

    private final UserValidator userValidator;

    private static final String DEFAULT_POSTAL_CODE = "75000";

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, UserValidator userValidator) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userValidator = userValidator;
    }

    @Override
    public UserDTO save(UserDTO userDTO) {
        log.debug("Request to save User : {}", userDTO);

        // Functional validation of the registered user
        userValidator.validate(userDTO);

        // Set default value of postal code
        if(userDTO.getPostalCode() == null) {
            userDTO.setPostalCode(DEFAULT_POSTAL_CODE);
        }

        User user = userMapper.toEntity(userDTO);
        user = userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserDTO> findOne(String id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(userMapper::toDto);
    }

}
