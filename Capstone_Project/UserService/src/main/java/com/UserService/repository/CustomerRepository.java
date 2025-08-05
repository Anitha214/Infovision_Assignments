package com.UserService.repository;

import com.UserService.entity.Customer;
import com.UserService.dto.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query("SELECT new com.UserService.dto.CustomerDto(c.id, c.name, c.email, c.phone, c.planId) FROM Customer c WHERE c.id = :id")
    CustomerDto getCustomerById(@Param("id") Long id);

}
