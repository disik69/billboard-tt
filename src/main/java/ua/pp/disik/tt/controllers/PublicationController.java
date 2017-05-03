package ua.pp.disik.tt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by disik on 4/7/17.
 */
@Controller
@RequestMapping("/publication")
public class PublicationController {
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list() {
        return "/publication/list";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createForm() {
        return "/publication/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create() {
        return "redirect:/publication/create";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String updateForm() {
        return "/publication/update";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable String id) {
        return "redirect:/publication/update/" + id;
    }
}
