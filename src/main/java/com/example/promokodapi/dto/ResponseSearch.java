package com.example.promokodapi.dto;

import com.example.promokodapi.entity.PromoCodEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseSearch {
    private List<PromoCodEntity> promoCodEntityList;
    private Integer status;
    private String message;
}
