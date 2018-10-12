package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
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
        return "redirect:/news";
    }

    @GetMapping("/news/{id}/comments/create")
    @PreAuthorize("hasAuthority('VIEW_PROFILE')")
    public String commentCreatePage(
            @PathVariable("id") Long id,
            ModelMap modelMap) {
        CommentDTO comment = new CommentDTO();
        comment.setNews(newsService.findOne(id));
        modelMap.addAttribute("comment", comment);
        return pageProperties.getCommentCreatePage();
    }

    @PostMapping(value = "/news/{id}/comments/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String createNews(
            @PathVariable("id") Long id,
            @ModelAttribute("comment") CommentDTO comment,
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO userDTO = userService.findUserById(userPrincipal.getId());
        comment.setNews(newsService.findOne(id));
        commentService.save(comment);
        userDTO.setComments((Set<CommentDTO>) comment);
        modelMap.addAttribute("comment", comment);
        return "redirect:/news";
    }
}
