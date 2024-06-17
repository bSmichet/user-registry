package fr.airfrance.userregistry.repository;

import fr.airfrance.userregistry.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data MongoDB repository for the User entity.
 */
@SuppressWarnings("unused")
@Repository
public interface UserRepository extends MongoRepository<User, String> {}
