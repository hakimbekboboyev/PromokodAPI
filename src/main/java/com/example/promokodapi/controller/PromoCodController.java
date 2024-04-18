package com.example.promokodapi.controller;

import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.AttachmentContent;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.service.PromoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PromoCodController {
    @Autowired
    PromoService promoService;

    @PostMapping("/addPromoCod")
    public ResponseEntity<String> addPromoCod(@RequestBody PromoCodDto promoCodDto,
                                              MultipartHttpServletRequest multipartFile) throws IOException {
        AttachmentContent attachmentContent = promoService.uploadFile(multipartFile);
        String result = promoService.addPromoCode(promoCodDto,attachmentContent);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<PromoCodEntity>> getAll(){
        promoService.checkPromoCodeDate();
        return ResponseEntity.ok(promoService.getAllPromoCod());
    }
}
