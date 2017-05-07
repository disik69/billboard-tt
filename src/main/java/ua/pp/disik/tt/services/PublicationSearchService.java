package ua.pp.disik.tt.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ua.pp.disik.tt.entities.Publication;
import ua.pp.disik.tt.entities.Topic;
import ua.pp.disik.tt.entities.User;
import ua.pp.disik.tt.repositories.PublicationRepository;
import ua.pp.disik.tt.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by disik on 5/7/17.
 */
@Service
public class PublicationSearchService {
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;

    private String userNameQuery;
    private String onlyMine;
    private Authentication principal;
    private Topic topic;

    @Autowired
    public PublicationSearchService(UserRepository userRepository, PublicationRepository publicationRepository) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
    }

    public void setUserNameQuery(String userNameQuery) {
        this.userNameQuery = userNameQuery;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public void setOnlyMine(String onlyMine) {
        this.onlyMine = onlyMine;
    }

    public void setPrincipal(Authentication principal) {
        this.principal = principal;
    }

    private Page<Publication> search(Pageable pageable) {
        if (onlyMine != null) {
            if (principal != null) {
                String userId = ((User) principal.getPrincipal()).getId();

                if (topic != null) {
                    return publicationRepository.findByUserIdAndTopic(userId, topic, pageable);
                } else {
                    return publicationRepository.findByUserId(userId, pageable);
                }
            } else if (topic != null) {
                return publicationRepository.findByTopic(topic, pageable);
            }
        } else if ((userNameQuery != null) && (! userNameQuery.isEmpty())) {
            List<User> users = userRepository.findByNameContaining(userNameQuery);
            List<String> userIds = new ArrayList<>();
            users.iterator().forEachRemaining((user) -> userIds.add(user.getId()));

            if (topic != null) {
                return publicationRepository.findByUserIdInAndTopic(userIds, topic, pageable);
            } else {
                return publicationRepository.findByUserIdIn(userIds, pageable);
            }
        } else if (topic != null) {
            return publicationRepository.findByTopic(topic, pageable);
        }

        return publicationRepository.findAll(pageable);
    }

    public Page<Publication> searchAndClear(Pageable pageable) {
        Page<Publication> result = search(pageable);

        onlyMine = null;
        principal = null;
        userNameQuery = null;
        topic = null;

        return result;
    }
}
