package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.dto.NewsDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {
    private final PageProperties pageProperties;
    private final NewsService newsService;

    @Autowired
    public NewsController(
            PageProperties pageProperties,
            NewsService newsService
    ) {
        this.pageProperties = pageProperties;
        this.newsService = newsService;
    }

    //ShowAllNews
    @GetMapping
    public String getNews(ModelMap modelMap) {
        List<NewsDTO> newsList = newsService.findAll();
        modelMap.addAttribute("newsList", newsList);
        return pageProperties.getNewsPagePath();
    }
}
