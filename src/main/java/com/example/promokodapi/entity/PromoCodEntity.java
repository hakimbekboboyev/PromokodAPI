package com.example.promokodapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "_promokod")
public class PromoCodEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String companyName;

    private String promoName;

    private String title;

    private String startPrice;

    private String discountPrice;

    private Date expireDate;

    private Boolean isActive;

    @OneToOne
    private AttachmentContent image;

}
