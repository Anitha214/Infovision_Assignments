package com.AuthService.repository;
import com.AuthService.entity.RefreshToken;
import com.AuthService.entity.UserDE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByToken(String token);

    @Query(value="select * from public.refreshtoken where user_id = :userId",nativeQuery = true)
    Optional<RefreshToken >findByUser_Id(@Param("userId") Long userId);
    @Modifying
    int deleteByUser(UserDE user);
}