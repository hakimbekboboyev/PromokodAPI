package com.example.promokodapi.service;

import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PromoService {
    @Autowired
    PromoRepository promoRepository;

    public String addPromoCode(PromoCodDto promoCodDto){
        PromoCodEntity promoCodEntity = PromoCodEntity.builder()
                .companyName(promoCodDto.getCompanyName())
                .promoName(promoCodDto.getPromoName())
                .isActive(true)
                .startPrice(promoCodDto.getStartPrice())
                .discountPrice(promoCodDto.getDiscountPrice())
                .expireDate(addData(promoCodDto.getExpireDate()))
                .build();
        try {
            promoRepository.save(promoCodEntity);
            return "Successful";
        }catch (Exception e){
            return "Warning!)";
        }

    }

    public List<PromoCodEntity> getAllPromoCod(){
        return promoRepository.getAll();
    }

    public void checkPromoCodeDate(){
        List<PromoCodEntity> promoCodEntities = promoRepository.getAll();
        for(PromoCodEntity promoCod : promoCodEntities){
            Date now = new Date();
            Date expireDate = promoCod.getExpireDate();
            if (now.after(expireDate)){
                promoCod.setIsActive(false);
            }
            promoRepository.save(promoCod);
        }
    }


    private Date addData(Integer date){
        Date now = new Date();
        Date expireDate = new Date();
        expireDate.setDate(now.getDate()+date);
        return expireDate;
    }
}
