package ua.pp.disik.tt.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import ua.pp.disik.tt.entities.User;

/**
 * Created by disik on 4/6/17.
 */
public interface UserRepository extends MongoRepository<User, String> {
}
