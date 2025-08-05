package com.BillingService.controller;

import com.BillingService.entity.Billing;
import com.BillingService.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/billing")
public class BillingController {

    @Autowired
    private BillingService billingService;

    @PostMapping("/generate/{userId}")
    public ResponseEntity<Billing> generateBill(@PathVariable Long userId) {
        return ResponseEntity.ok(billingService.generateBill(userId));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Billing>> getUserBills(@PathVariable Long userId) {
        return ResponseEntity.ok(billingService.getBillsByUser(userId));
    }

}
