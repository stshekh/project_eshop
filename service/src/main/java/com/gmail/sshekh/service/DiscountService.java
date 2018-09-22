package com.gmail.sshekh.service;

import com.gmail.sshekh.service.dto.DiscountDTO;

import java.math.BigDecimal;
import java.util.Set;

public interface DiscountService {
    DiscountDTO save(DiscountDTO discountDTO);

    Set<DiscountDTO> getDiscountOfRate(BigDecimal rate);

    void update(DiscountDTO discountDTO);

    Long getIdByRate(BigDecimal rate);

    DiscountDTO getRandomDiscount();
}
