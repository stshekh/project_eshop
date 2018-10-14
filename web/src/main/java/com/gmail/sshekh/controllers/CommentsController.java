package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import static com.gmail.sshekh.controllers.utils.UsersLoginUtil.getLoggedInUser;

@Controller
@RequestMapping("/news/{idNews}")
public class CommentsController {
    private final CommentService commentService;
    private final UserService userService;
    private final PageProperties pageProperties;
    private final NewsService newsService;

    @Autowired
    public CommentsController(
            PageProperties pageProperties,
            CommentService commentService,
            UserService userService,
            NewsService newsService
    ) {
        this.pageProperties = pageProperties;
        this.commentService = commentService;
        this.userService = userService;
        this.newsService = newsService;
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
        return pageProperties.getCommentCreatePage();
    }

    @PostMapping(value = "/comments/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String createComments(
            @PathVariable("idNews") Long idNews,
            @ModelAttribute("comment") CommentDTO comment,
            ModelMap modelMap
    ) {
        UserPrincipal userPrincipal = getLoggedInUser();
        commentService.save(comment, idNews, userPrincipal.getId());
        modelMap.addAttribute("comment", comment);
        return "redirect:/news/show/{idNews}";
    }
}
