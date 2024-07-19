package com.example.promokodapi.controller;

import com.example.promokodapi.dto.CategoryDto;
import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.CategoryEntity;
import com.example.promokodapi.entity.PromoCodEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseController {
    ResponseEntity<String> addPromoCod(PromoCodDto promoCodDto);
    ResponseEntity<List<PromoCodEntity>> getAllPromoCod();
    ResponseEntity<List<CategoryEntity>> getAllCategory();
    ResponseEntity<String> addCategory(CategoryDto categoryDto);
}
