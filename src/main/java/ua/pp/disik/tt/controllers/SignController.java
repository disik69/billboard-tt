package ua.pp.disik.tt.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by disik on 4/7/17.
 */
@Controller
@RequestMapping("/sign")
public class SignController {
    @RequestMapping(path = "/in", method = RequestMethod.GET)
    public String inForm() {
        return "/sign/in";
    }

    @RequestMapping(path = "/in", method = RequestMethod.POST)
    public String in() {
        return "redirect:/sign/in";
    }

    @RequestMapping(path = "/up", method = RequestMethod.GET)
    public String upForm() {
        return "/sign/up";
    }

    @RequestMapping(path = "/up", method = RequestMethod.POST)
    public String up() {
        return "redirect:/sign/up";
    }

    @RequestMapping(path = "/out", method = RequestMethod.GET)
    public String out() {
        return "redirect:/";
    }
}
