package ua.pp.disik.tt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.Request;
import ua.pp.disik.tt.entities.Publication;
import ua.pp.disik.tt.entities.Topic;
import ua.pp.disik.tt.entities.User;
import ua.pp.disik.tt.repositories.PublicationRepository;
import ua.pp.disik.tt.repositories.UserRepository;
import ua.pp.disik.tt.services.ParentTestService;

import java.util.*;

/**
 * Created by disik on 4/6/17.
 */

@RestController
@RequestMapping("test")
public class TestController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PublicationRepository publicationRepository;

    @Autowired
    private ParentTestService parentTestService;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String, Object> test() {
        User user = new User("aaa", "aaa@test.test", "aaapass");
        userRepository.save(user);

        user = userRepository.findOne(user.getId());

        Set<Topic> topics = new HashSet<>(Arrays.asList(Topic.PURCHASE, Topic.SERVICE));

        Publication publication = new Publication(user.getId(), "title", "body", topics);
        publicationRepository.save(publication);

        publication = publicationRepository.findOne(publication.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("user", user);
        result.put("publication", publication);

        return result;
    }

    @RequestMapping(path = "parent-service", method = RequestMethod.GET)
    public Map<String, Object> abc() {
        Map<String, Object> result = new HashMap<>();

        result.put("serviceId", parentTestService.getId());
        result.put("childService", parentTestService.getChildTestService());

        return result;
    }
}
