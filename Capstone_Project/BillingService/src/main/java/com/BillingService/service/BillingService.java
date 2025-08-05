package com.BillingService.service;

import com.BillingService.client.PlanServiceClient;
import com.BillingService.client.UsageServiceClient;
import com.BillingService.client.UserServiceClient;
import com.BillingService.dto.CustomerDto;
import com.BillingService.dto.PlansDto;
import com.BillingService.dto.UsageSummary;
import com.BillingService.entity.Billing;
import com.BillingService.repository.BillingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BillingService {

    @Autowired
    private UsageServiceClient usageServiceClient;

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private PlanServiceClient planServiceClient;

    @Autowired
    private BillingRepository billingRepository;

    public Billing generateBill(Long userId) {
        // Fetch usage details
        List<UsageSummary> usageList = usageServiceClient.getUsageSummary(userId);

        // Fetch user details
        CustomerDto user = userServiceClient.getUserDetails(userId);

        // Fetch plan details
        Optional<PlansDto> plan = planServiceClient.findByPlanId(user.getPlanId());

        double costPerGB;
        double costPerMinute;

        if (plan.isPresent()) {
            PlansDto planDetails = plan.get();
            costPerGB = planDetails.getCostPerGb();
            costPerMinute = planDetails.getCostPerMinute();
        } else {
            throw new RuntimeException("Plan not found for ID: " + user.getPlanId());
        }

        // Calculate total usage
        double totalDataUsed = usageList.stream().mapToDouble(UsageSummary::getMbUsed).sum();
        double totalCallTime = usageList.stream().mapToInt(UsageSummary::getMinutesUsed).sum();

        // Calculate cost
        double totalCost = (totalDataUsed * costPerGB) + (totalCallTime * costPerMinute);

        // Generate bill
        Billing bill = new Billing();
        bill.setUserId(userId);
        bill.setPlanId(user.getPlanId());
        bill.setTotalDataUsed(totalDataUsed);
        bill.setTotalTalkTime(totalCallTime);
        bill.setTotalAmount(totalCost);
        bill.setBillingDate(LocalDate.now());

        return billingRepository.save(bill);
    }

    public List<Billing> getBillsByUser(Long userId) {
        return billingRepository.findByUserId(userId);
    }
}
