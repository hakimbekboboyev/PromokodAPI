package com.example.promokodapi.repository;

import com.example.promokodapi.entity.user.Statistics;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatRepository extends JpaRepository<Statistics,Integer> {
}
