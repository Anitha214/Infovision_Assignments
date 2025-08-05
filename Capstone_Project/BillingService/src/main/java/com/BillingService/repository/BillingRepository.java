package com.BillingService.repository;

import com.BillingService.entity.Billing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillingRepository extends JpaRepository<Billing,Long> {
    List<Billing> findByUserId(Long userId);
}
