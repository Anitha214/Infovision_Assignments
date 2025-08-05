/*
package com.UsageService.repository;

import com.UsageService.dto.UsageLogDto;
import com.UsageService.entity.UsageLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsageLogRepository extends MongoRepository<UsageLog, String> {
    @Query("SELECT new com.UsageService.dto.UsageLogDto(u.id, u.userId, u.planId, u.dataUsedInMb, u.callMinutesUsed, u.timeStamp) FROM UsageLog u WHERE u.userId = :id")
    List<UsageLogDto> getLogByUserId(@Param("id") Long id);
}
*/

package com.UsageService.repository;

import com.UsageService.entity.UsageLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UsageLogRepository extends MongoRepository<UsageLog, String> {

    List<UsageLog> findByCustomerId(Long customerId);

    List<UsageLog> findBySubscribedPlanId(Long subscribedPlanId);

    List<UsageLog> findByCustomerIdAndLoggedAtBetween(Long customerId, LocalDateTime start, LocalDateTime end);
}

