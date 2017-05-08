package ua.pp.disik.tt.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ua.pp.disik.tt.entities.Publication;
import ua.pp.disik.tt.entities.Topic;
import ua.pp.disik.tt.entities.User;
import ua.pp.disik.tt.exceptions.HttpForbiddenException;
import ua.pp.disik.tt.exceptions.HttpNotFoundException;
import ua.pp.disik.tt.repositories.PublicationRepository;
import ua.pp.disik.tt.services.PublicationSearchService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by disik on 4/7/17.
 */
@Controller
@RequestMapping("/publication")
public class PublicationController {
    private PublicationRepository publicationRepository;
    private PublicationSearchService publicationSearchService;

    @Autowired
    public PublicationController(PublicationRepository publicationRepository,
                                 PublicationSearchService publicationSearchService) {
        this.publicationRepository = publicationRepository;
        this.publicationSearchService = publicationSearchService;
    }

    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public String list(@RequestParam(name = "page", required = false) Integer currentPage,
                       @RequestParam(name = "rows", required = false) Integer rowCount,
                       @RequestParam(required = false) Topic topic,
                       @RequestParam(name = "user", required = false) String userNameQuery,
                       @RequestParam(required = false) String onlyMine,
                       Model model,
                       Authentication principal) {
        if (currentPage == null) {
            currentPage = 1;
        }
        if (rowCount == null) {
            rowCount = 5;
        }

        PageRequest pageRequest = new PageRequest(currentPage - 1, rowCount, Sort.Direction.DESC, "createdAt");
        publicationSearchService.setOnlyMine(onlyMine);
        publicationSearchService.setPrincipal(principal);
        publicationSearchService.setUserNameQuery(userNameQuery);
        publicationSearchService.setTopic(topic);
        Page<Publication> publicationPage = publicationSearchService.searchAndClear(pageRequest);

        int viewPageCount = 4;
        int totalPageCount = publicationPage.getTotalPages();
        int firstBlockPage = (((currentPage - 1) / viewPageCount) * viewPageCount) + 1;
        int lastBlockPage = firstBlockPage + viewPageCount - 1;
        if (lastBlockPage > totalPageCount) {
            lastBlockPage = totalPageCount;
        }
        List<Integer> pageBlock = new ArrayList<>();
        for (int i = firstBlockPage; i < lastBlockPage + 1; i++) {
            pageBlock.add(i);
        }

        model.addAttribute("publications", publicationPage.getContent());
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("pageBlock", pageBlock);
        model.addAttribute("rowCount", rowCount);
        model.addAttribute("isPaginationEnabled", totalPageCount != 1);
        model.addAttribute("isLeftEnabled", currentPage != 1);
        model.addAttribute("isRightEnabled", currentPage != totalPageCount);

        return "/publication/list";
    }

    @RequestMapping(path = "/create", method = RequestMethod.GET)
    public String createForm(Model model) {
        Publication publication = new Publication(null, "", "", null);

        model.addAttribute("publication", publication);

        return "/publication/create";
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String create(@Valid Publication publication,
                         BindingResult bindingResult,
                         Model model,
                         Authentication principal,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "/publication/create";
        }

        publication.setUser((User) principal.getPrincipal());
        publication.setCreatedAt();

        publicationRepository.save(publication);

        return "redirect:/publication/list";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.GET)
    public String updateForm(@PathVariable String id,
                             Model model,
                             Authentication principal) {
        Publication publication = publicationRepository.findOne(id);
        checkPublication(publication, principal);

        model.addAttribute("publication", publication);

        return "/publication/update";
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable String id,
                         @Valid Publication publication,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes,
                         Authentication principal) {
        Publication storedPublication = publicationRepository.findOne(id);
        checkPublication(storedPublication, principal);
        storedPublication.setBody(publication.getBody());

        if (bindingResult.hasFieldErrors("body")) {
            model.addAttribute("storedPublication", storedPublication);

            return "/publication/update";
        }

        publicationRepository.save(storedPublication);

        return "redirect:/publication/list";
    }

    @RequestMapping(path = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable String id,
                         Authentication principal,
                         RedirectAttributes redirectAttributes) {
        Publication publication = publicationRepository.findOne(id);
        if (publication != null) {
            if (publication.getUser().equals(principal.getPrincipal())) {
                publicationRepository.delete(id);
            } else {
                throw new HttpForbiddenException("It isn't your publication");
            }
        } else {
            throw new HttpNotFoundException("Publication isn't found");
        }

        return "redirect:/publication/list";
    }

    @ModelAttribute("topics")
    public Topic[] getTopics() {
        return Topic.values();
    }

    private void checkPublication(Publication publication,
                                     Authentication principal) {
        if (publication == null) {
            throw new HttpNotFoundException("Publication isn't found");
        } else if (! publication.getUser().equals(principal.getPrincipal())) {
            throw new HttpForbiddenException("It isn't your publication");
        }
    }
}
