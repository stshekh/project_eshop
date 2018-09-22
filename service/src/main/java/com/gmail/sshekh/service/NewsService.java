package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO save(NewsDTO newsDTO);

    NewsDTO update(NewsDTO newsDTO);

    List<NewsDTO> findAll();

    void delete(NewsDTO newsDTO);
}
