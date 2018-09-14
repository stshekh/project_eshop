package com.gmail.sshekh;

import com.gmail.sshekh.dto.NewsDTO;

import java.util.List;

public interface NewsService {
    NewsDTO save(NewsDTO newsDTO);

    NewsDTO update(NewsDTO newsDTO);

    List<NewsDTO> findAll();

    void delete(NewsDTO newsDTO);
}
