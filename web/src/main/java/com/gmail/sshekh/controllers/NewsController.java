package com.gmail.sshekh.controllers;

import com.gmail.sshekh.controllers.properties.PageProperties;
import com.gmail.sshekh.service.CommentService;
import com.gmail.sshekh.service.NewsService;
import com.gmail.sshekh.service.UserService;
import com.gmail.sshekh.service.dto.CommentDTO;
import com.gmail.sshekh.service.dto.NewsDTO;
import com.gmail.sshekh.service.principal.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.gmail.sshekh.service.utils.PaginationUtil.*;
import static com.gmail.sshekh.service.utils.UsersLoginUtil.getLoggedInUser;

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
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS','VIEW_PROFILE')")
    public String getNews(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(newsService.countAllNews(), NEWS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        List<NewsDTO> newsList = newsService.findAll(page, NEWS_PER_PAGE);
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
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS','VIEW_PROFILE')")
    public String showOneNewsPage(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @PathVariable("id") Long id,
            ModelMap modelMap
    ) {
        Integer totalPages = getNumberOfPages(commentService.countCommentsPerArticle(id), COMMENTS_PER_PAGE);
        modelMap.addAttribute("pages", totalPages);
        NewsDTO news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        List<CommentDTO> comments = commentService.getCommentsByNewsId(id, page, COMMENTS_PER_PAGE);
        modelMap.addAttribute("comments", comments);
        return pageProperties.getOneNewsPage();
    }

    //Redirects to update page
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS')")
    public String getNews(@PathVariable("id") Long id, ModelMap modelMap) {
        NewsDTO news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        return pageProperties.getNewsUpdatePage();
    }

    //Updates article
    @PostMapping(value = "/{id}")
    @PreAuthorize("hasAnyAuthority('MANAGE_ITEMS')")
    public String updateNews(
            @PathVariable("id") Long id,
            @ModelAttribute NewsDTO news,
            ModelMap modelMap
    ) {
        news.setId(id);
        news = newsService.update(news);
        modelMap.addAttribute("news", news);
        return "redirect:/news";
    }
}
