package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.NewsDTO;
import com.gmail.sshekh.service.dto.UserDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            NewsService newsService,
            UserService userService,
            CommentService commentService
    ) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.userService = userService;
        this.commentService = commentService;
    }

    //ShowAllNews
    @GetMapping
    public String getNews(ModelMap modelMap) {
        List<NewsDTO> newsList = newsService.findAll();
        modelMap.addAttribute("newsList", newsList);
        return pageProperties.getNewsPagePath();
    }

    //Go to news create page
    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getCreatePage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePage();
    }

    //Creating news
    @PostMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String createNews(
            @ModelAttribute("news") NewsDTO news,
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        news.setUser(userService.findUserById(userPrincipal.getId()));
        news = newsService.save(news);
        modelMap.addAttribute("news", news);
        return "redirect:/news";
    }

    //Deleting news
    @PostMapping("/delete")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String deleteNews(
            @RequestParam("ids") Long[] ids
    ) {
        for (Long id : ids) {
            newsService.delete(id);
        }
        return "redirect:/news";
    }

    @GetMapping(value = "/show/{id}")
    public String showOneNews(@PathVariable("id") Long id, ModelMap modelMap) {
        NewsDTO news = newsService.findOne(id);
        Set<CommentDTO> comments = commentService.getCommentsByNewsId(id);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comments", comments);
        return pageProperties.getOneNewsPage();
    }

    //Shows one user page
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS')")
    public String getUser(@PathVariable("id") Long id, ModelMap modelMap) {
        NewsDTO news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsUpdatePage();
    }

    //Updates user
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS')")
    public String updateNews(
            @PathVariable("id") Long id,
            @ModelAttribute NewsDTO news,
            ModelMap modelMap
    ) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        UserDTO user = userService.findUserById(userPrincipal.getId());
        news.setUser(user);
        news.setId(id);
        news = newsService.update(news);
        modelMap.addAttribute("news", news);
        return "redirect:/news";

    }


}
