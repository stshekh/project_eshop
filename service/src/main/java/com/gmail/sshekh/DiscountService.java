package com.gmail.sshekh;

import com.gmail.sshekh.dto.DiscountDTO;

import java.util.Set;

public interface DiscountService {
    DiscountDTO save(DiscountDTO discountDTO);

    Set<DiscountDTO> getDiscountOfRate(Integer rate);

    void update(DiscountDTO discountDTO);

    Long getIdByRate(Integer rate);
}
