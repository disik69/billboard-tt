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
    private UserRepository userRepository;
    private PublicationRepository publicationRepository;
    private ParentTestService parentTestService;

    @Autowired
    public TestController(UserRepository userRepository,
                          PublicationRepository publicationRepository,
                          ParentTestService parentTestService) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
        this.parentTestService = parentTestService;
    }

    @RequestMapping(path = "parent-service", method = RequestMethod.GET)
    public Map<String, Object> abc() {
        Map<String, Object> result = new HashMap<>();

        result.put("serviceId", parentTestService.getId());
        result.put("childService", parentTestService.getChildTestService());

        return result;
    }
}
