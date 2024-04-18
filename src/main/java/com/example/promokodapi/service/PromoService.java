package com.example.promokodapi.service;

import com.example.promokodapi.dto.PromoCodDto;
import com.example.promokodapi.entity.Attachment;
import com.example.promokodapi.entity.AttachmentContent;
import com.example.promokodapi.entity.PromoCodEntity;
import com.example.promokodapi.repository.AttachmentContentRepository;
import com.example.promokodapi.repository.AttachmentRepository;
import com.example.promokodapi.repository.PromoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class PromoService {
    @Autowired
    PromoRepository promoRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public String addPromoCode(PromoCodDto promoCodDto, AttachmentContent attachmentContent){
        PromoCodEntity promoCodEntity = PromoCodEntity.builder()
                .companyName(promoCodDto.getCompanyName())
                .promoName(promoCodDto.getPromoName())
                .title(promoCodDto.getTitle())
                .image(attachmentContent)
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

    public AttachmentContent uploadFile(MultipartHttpServletRequest servletRequest) throws IOException {
        Iterator<String> fileNames = servletRequest.getFileNames();
        if(fileNames.hasNext()) {
            MultipartFile file = servletRequest.getFile(fileNames.next());
            String originalFilename = file.getOriginalFilename();
            long size = file.getSize();
            String contentType = file.getContentType();
            byte[] bytes = file.getBytes();
            Attachment attachment = new Attachment();
            AttachmentContent attachmentContent = new AttachmentContent();
            attachment.setSize(size);
            attachment.setContentType(contentType);
            attachment.setOriginalName(originalFilename);
            attachmentContent.setContent(bytes);
            attachmentContent.setAttachment(attachment);
            attachmentRepository.save(attachment);
            AttachmentContent result = attachmentContentRepository.save(attachmentContent);

            return result;
        }
        return null;


    }


    private Date addData(Integer date){
        Date now = new Date();
        Date expireDate = new Date();
        expireDate.setDate(now.getDate()+date);
        return expireDate;
    }
}
