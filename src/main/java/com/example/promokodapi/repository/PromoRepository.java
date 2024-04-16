package com.example.promokodapi.repository;

import com.example.promokodapi.entity.PromoCodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromoRepository extends JpaRepository<PromoCodEntity,Integer> {
    @Query(value = "select * from _promokod where _promokod.is_active = true order by _promokod.expire_date desc",nativeQuery = true)
    List<PromoCodEntity> getAll();
}
