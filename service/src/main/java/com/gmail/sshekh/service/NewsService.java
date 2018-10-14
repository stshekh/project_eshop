package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO save(NewsDTO newsDTO);

    NewsDTO update(NewsDTO newsDTO, Long userId);

    List<NewsDTO> findAll(int startPosition, int maxResult);

    void delete(Long id);

    NewsDTO findOne(Long id);

    Integer countAllNews();
}
