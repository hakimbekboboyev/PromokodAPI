package com.example.promokodapi.service;

import com.example.promokodapi.dto.CategoryDto;
import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.dto.ResponsePromo;
import com.example.promokodapi.dto.ResponseSearch;
import com.example.promokodapi.entity.CategoryEntity;

import java.util.List;

public interface BaseService <T>{

    String createCategory(CategoryDto categoryDto);

    List<T> getAllPromoCode();

    String addPromoCode(PromoCodDto promoCodDto);

    void checkPromoCodeDate();

    List<CategoryEntity> getAllCategory();

    ResponsePromo getPromoCodeById(int id);

    ResponsePromo updatePromoCode(int id, PromoCodDto promoCodDto);

    ResponseSearch search(String query);
}
