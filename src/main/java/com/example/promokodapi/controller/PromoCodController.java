package com.example.promokodapi.controller;

import com.example.promokodapi.dto.CategoryDto;
import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.dto.ResponsePromo;
import com.example.promokodapi.dto.ResponseSearch;
import com.example.promokodapi.entity.CategoryEntity;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.entity.user.Statistics;
import com.example.promokodapi.repository.StatRepository;
import com.example.promokodapi.service.PromoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/promo")
@SecurityRequirement(name = "bearerAuth")
public class PromoCodController implements BaseController {
    @Autowired
    PromoService promoService;

    @Autowired
    StatRepository statRepository;

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
        Optional<Statistics> statistics = statRepository.findById(1);
        if (statistics.isPresent()) {
            Statistics stat = statistics.get();
            stat.setCount(stat.getCount() + 1);
            statRepository.save(stat);
        }
        promoService.checkPromoCodeDate();
        return ResponseEntity.ok(promoService.getAllPromoCode());
    }

    @Override
    @Operation(summary = "getAllCategory", description = "Get All Category")
    @GetMapping("/getAllCategory")
    public ResponseEntity<List<CategoryEntity>> getAllCategory() {
        Optional<Statistics> statistics = statRepository.findById(1);
        if (statistics.isPresent()) {
            Statistics stat = statistics.get();
            stat.setCount(stat.getCount() + 1);
            statRepository.save(stat);
        }
        return ResponseEntity.ok(promoService.getAllCategory());
    }

    @Override
    @Operation(summary = "createCategory", description = "Create Category")
    @PostMapping("/createCategory")
    public ResponseEntity<String> addCategory(@RequestBody CategoryDto categoryDto) {
        String result = promoService.createCategory(categoryDto);
        return ResponseEntity.ok().body(result);
    }

    @Override
    @Operation(summary = "getPromoById", description = "Get PromoCode By Id")
    @GetMapping("/getPromoById/{id}")
    public ResponseEntity<ResponsePromo> getPromoCodById(@PathVariable int id) {
        ResponsePromo promoCodeById = promoService.getPromoCodeById(id);
        return ResponseEntity.status(promoCodeById.getStatus()).body(promoCodeById);
    }

    @Override
    @Operation(summary = "updatePromo", description = "Update Promo Code")
    @PutMapping("/updatePromo/{id}")
    public ResponseEntity<ResponsePromo> updatePromoCod(@PathVariable int id,
                                                        @RequestBody PromoCodDto promoCodDto) {
        ResponsePromo responsePromo = promoService.updatePromoCode(id, promoCodDto);
        return ResponseEntity.status(responsePromo.getStatus()).body(responsePromo);
    }

    @Override
    @Operation(summary = "search", description = "Search")
    @GetMapping("/search/query")
    public ResponseEntity<ResponseSearch> search(@RequestParam String keyword) {
        ResponseSearch result = promoService.search(keyword);
        return ResponseEntity.status(result.getStatus()).body(result);
    }

    @Override
    @Operation(summary = "getCategoryById", description = "Get category by id")
    @GetMapping("/category/{id}")
    public ResponseEntity<ResponsePromo> getCategoryById(@PathVariable int id) {
        ResponsePromo categoryById = promoService.getCategoryById(id);
        return ResponseEntity.status(categoryById.getStatus()).body(categoryById);
    }

    @GetMapping("/getStatistics")
    public ResponseEntity getStatistics() {
        Statistics stat = new Statistics();
        Optional<Statistics> statistics = statRepository.findById(1);
        if (statistics.isPresent()) {
            stat = statistics.get();
        }
        return ResponseEntity.status(200).body("statistic is " + stat.getCount());
    }
}
