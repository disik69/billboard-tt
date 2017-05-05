package ua.pp.disik.tt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pp.disik.tt.entities.User;
import ua.pp.disik.tt.repositories.UserRepository;

/**
 * Created by disik on 5/4/17.
 */

@Service
public class MongoUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public MongoUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Example<User> userExample = Example.of(new User(null, username, null));
        User user = userRepository.findOne(userExample);

        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User doesn't exist");
        }
    }
}
