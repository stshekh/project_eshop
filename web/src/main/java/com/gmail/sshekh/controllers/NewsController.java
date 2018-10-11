package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.NewsDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;
    private final UserService userService;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            NewsService newsService,
            UserService userService
    ) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
        this.userService = userService;
    }

    //ShowAllNews
    @GetMapping
    public String getNews(ModelMap modelMap) {
        List<NewsDTO> newsList = newsService.findAll();
        modelMap.addAttribute("newsList", newsList);
        return pageProperties.getNewsPagePath();
    }

    @GetMapping(value = "/create")
    @PreAuthorize("hasAuthority('MANAGE_ITEMS')")
    public String getCreatePage(ModelMap modelMap) {
        modelMap.addAttribute("news", new NewsDTO());
        return pageProperties.getNewsCreatePage();
    }

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
}
