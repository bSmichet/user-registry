package fr.airfrance.userregistry.service.mapper;

import fr.airfrance.userregistry.domain.User;
import fr.airfrance.userregistry.service.dto.UserDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link User} and its DTO {@link UserDTO}.
 */
@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDTO, User> {}
