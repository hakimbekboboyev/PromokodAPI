package com.example.promokodapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PromoCodDto {
    private String companyName;

    private String promoName;

    private String startPrice;

    private String discountPrice;

    private Integer expireDate;

    private Boolean isActive;
}
