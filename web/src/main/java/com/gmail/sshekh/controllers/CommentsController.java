package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/news/{idNews}")
public class CommentsController {

    private final CommentService commentService;
    private final NewsService newsService;
    private final Validator commentValidator;

    @Autowired
    public CommentsController(
            CommentService commentService,
            NewsService newsService,
            Validator commentValidator
    ) {
        this.commentService = commentService;
        this.newsService = newsService;
        this.commentValidator = commentValidator;
    }


    @PostMapping("/comments/delete")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String deleteComments(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            commentService.delete(id);
        }
        return "redirect:/news/show/{idNews}";
    }

    @GetMapping("/comments/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String commentCreatePage(
            @PathVariable("idNews") Long idNews,
            ModelMap modelMap) {
        modelMap.addAttribute("comment", new CommentDTO());
        modelMap.addAttribute("news", newsService.findOne(idNews));
        return "comments.create";
    }

    @PostMapping(value = "/comments/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String createComments(
            @PathVariable("idNews") Long idNews,
            @ModelAttribute("comment") CommentDTO comment,
            ModelMap modelMap,
            BindingResult bindingResult
    ) {
        commentValidator.validate(comment, bindingResult);
        if (bindingResult.hasErrors()) {
            return "comments.create";
        } else {
            commentService.save(comment, idNews);
            modelMap.addAttribute("comment", comment);
            modelMap.addAttribute("news", newsService.findOne(idNews));
            return "redirect:/news/show/{idNews}";
        }
    }
}
