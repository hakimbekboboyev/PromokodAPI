package com.example.promokodapi.service;

import com.example.promokodapi.dto.CategoryDto;
import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.CategoryEntity;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.repository.CategoryRepository;
import com.example.promokodapi.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PromoService implements BaseService<PromoCodEntity> {



    @Autowired
    PromoRepository promoRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public String createCategory(CategoryDto categoryDto) {
        CategoryEntity categoryEntity = CategoryEntity.builder()
                .name(categoryDto.getName())
                .description(categoryDto.getDescription())
                .build();
        categoryRepository.save(categoryEntity);
        return "Successful";
    }

    @Override
    public List<PromoCodEntity> getAllPromoCode() {
        return promoRepository.getAll();
    }

    @Override
    public String addPromoCode(PromoCodDto promoCodDto) {
        CategoryEntity categoryEntity = new CategoryEntity();
        PromoCodEntity promoCodEntity = PromoCodEntity.builder()
                .companyName(promoCodDto.getCompanyName())
                .promoName(promoCodDto.getPromoName())
                .title(promoCodDto.getTitle())
                .image(promoCodDto.getImage())
                .isActive(true)
                .startPrice(promoCodDto.getStartPrice())
                .discountPrice(promoCodDto.getDiscountPrice())
                .expireDate(promoCodDto.getExpireDate())
                .build();
        Optional<CategoryEntity> optionalCategory = categoryRepository.findById(promoCodDto.getCategoryId());
        if (optionalCategory.isPresent()) {
            categoryEntity = optionalCategory.get();
            List<PromoCodEntity> promoCodEntityList = categoryEntity.getPromoCodList();
            promoCodEntityList.add(promoCodEntity);
            categoryEntity.setPromoCodList(promoCodEntityList);
        }


        try {
            promoRepository.save(promoCodEntity);

            categoryRepository.save(categoryEntity);
            return "Successful";
        } catch (Exception e) {
            return "Warning!)";
        }

    }

    @Override
    public void checkPromoCodeDate() {
        List<PromoCodEntity> promoCodEntities = promoRepository.getAll();
        for (PromoCodEntity promoCod : promoCodEntities) {
            Date now = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date expireDate = null;
            try {
                expireDate = dateFormat.parse(promoCod.getExpireDate());
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            if (now.after(expireDate)) {
                promoCod.setIsActive(false);
            }
            promoRepository.save(promoCod);
        }
    }

    @Override
    public List<CategoryEntity> getAllCategory() {
        return categoryRepository.findAll();
    }


}
