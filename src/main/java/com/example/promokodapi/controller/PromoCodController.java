package com.example.promokodapi.controller;

import com.example.promokodapi.dto.CategoryDto;
import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.CategoryEntity;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.service.PromoService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class PromoCodController implements BaseController {
    @Autowired
    PromoService promoService;

    @Override
    @Operation(summary = "addPromoCode", description = "Add Promo")
    @PostMapping("/addPromoCod")
    public ResponseEntity<String> addPromoCod(@RequestBody PromoCodDto promoCodDto) {
        String result = promoService.addPromoCode(promoCodDto);
        return ResponseEntity.ok().body(result);
    }

    @Override
    @Operation(summary = "getAllPromo", description = "Get All Promo")
    @GetMapping("/getAllPromo")
    public ResponseEntity<List<PromoCodEntity>> getAllPromoCod(){
        promoService.checkPromoCodeDate();
        return ResponseEntity.ok(promoService.getAllPromoCode());
    }

    @Override
    @Operation(summary = "getAllCategory", description = "Get All Category")
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        return ResponseEntity.ok(promoService.getAllCategory());
    }

    @Override
    @Operation(summary = "createCategory", description = "Create Category")
    @PostMapping("/createCategory")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        String result = promoService.createCategory(categoryDto);
        return ResponseEntity.ok().body(result);
    }
}
