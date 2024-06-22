package com.example.promokodapi.service;

import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PromoService {
    @Autowired
    PromoRepository promoRepository;


    public String addPromoCode(PromoCodDto promoCodDto) {
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
        try {
            promoRepository.save(promoCodEntity);
            return "Successful";
        } catch (Exception e) {
            return "Warning!)";
        }

    }

    public List<PromoCodEntity> getAllPromoCod() {
        return promoRepository.getAll();
    }


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

}
