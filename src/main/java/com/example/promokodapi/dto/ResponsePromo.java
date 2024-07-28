package com.example.promokodapi.dto;

import com.example.promokodapi.entity.PromoCodEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.ResponseBody;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponsePromo {
    private Object responseEntity;

    private Integer status;

    private String message;
}
